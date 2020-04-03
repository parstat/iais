package com.nbs.iais.cloud.zuul.filters.pre;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.nbs.iais.cloud.zuul.config.FilterConfig;
import com.nbs.iais.cloud.zuul.filters.post.SigninJwtFilter;
import com.nbs.iais.cloud.zuul.filters.post.SignupJwtFilter;
import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import com.nbs.iais.cloud.zuul.jwt.service.SecurityService;
import com.nbs.iais.cloud.zuul.utils.SecurityTools;
import com.netflix.zuul.context.RequestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@Import(FilterConfig.class)
public class FilterTests {

    @Mock
    private DiscoveryClient discovery;
    @MockBean
    private SecurityService securityService;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private SecurityTools securityTools;

    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();
    private final ProxyRequestHelper proxyRequestHelper = new ProxyRequestHelper();
    private DiscoveryClientRouteLocator routeLocator;
    @Autowired
    private LanguageFilter languageFilter;
    @Autowired
    private AuthenticatedFilter authenticatedFilter;
    private PreDecorationFilter preDecorationFilter;
    private RibbonRoutingFilter ribbonRoutingFilter;
    @Autowired
    private XssFilter xssFilter;
    @Autowired
    private SigninJwtFilter signinJwtFilter;
    @Autowired
    private SignupJwtFilter signupJwtFilter;
    private ZuulProperties zuulProperties;


    @Before
    public void setUp() {
        initMocks(this);
        zuulProperties = new ZuulProperties();
        final RibbonCommandFactory factory = mock(RibbonCommandFactory.class);
        ribbonRoutingFilter = new RibbonRoutingFilter(new ProxyRequestHelper(), factory,
                Collections.emptyList());
        final ServiceInstance serviceInstance = mock(DefaultServiceInstance.class);
        routeLocator = new DiscoveryClientRouteLocator("/", discovery, zuulProperties, serviceInstance);
        preDecorationFilter = new PreDecorationFilter(routeLocator, "/", zuulProperties, proxyRequestHelper);
        final RequestContext ctx = RequestContext.getCurrentContext();
        ctx.clear();
        ctx.setRequest(request);
        ctx.setResponse(response);
    }

    @After
    public void clear() {
        RequestContext.getCurrentContext().clear();
    }


    @Test
    public void testShouldRunLanguageFilter() {
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/foo/1");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.addHeader("jwt-auth", "12345678");
        //request.setQueryString("?language=ENG");
        //this.request.addParameter("language", "en");
        routeLocator.addRoute(new ZuulProperties.ZuulRoute("foo", "/foo/**", "foo",
                null, false, null, null));
        preDecorationFilter.run();
        assertTrue(languageFilter.shouldFilter());
    }


    @Test
    public void testShouldRunSecurityFilter() {
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v1/closed/1");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.addHeader("jwt-auth",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdXRoZW50aWNhdGVkIjp0cnVlLCJyb2xlIjoiUk9PVCIsInZpc2l0SUQiOiJlM2VjNjJkOC1kZTljLTRkN2MtOTJmMi0yNzRhODllNTVlMjMiLCJwcm9maWxlSUQiOiIxYTk0YTc4NC0yM2M1LTQwMWYtYTA3Mi1iZGIyNTZmODk3YWMiLCJuYW1lIjoiRG9Ub2RheUFkbWluIiwiaXNzIjoiZG90b2RheSIsImV4cCI6MTUwMjM2NzkzOSwidXNlciI6IjIxIiwiaWF0IjoxNTAyMzY0MzM5LCJqdGkiOiJlY2M0YmY3My1mOGMyLTRiMDUtOTc5ZS0zYTAwYzFhNTc4MDIifQ.8A6CHyJ3SH7mmAfCTquKFBVRAQ3tWQ7xnY5EO9E5SZo");
        assertTrue(authenticatedFilter.shouldFilter());
    }


    @Test
    public void testSecurityFilterShouldFilter() {
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v1/closed/1");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.setMethod("OPTIONS");
        RequestContext.getCurrentContext();
        assertFalse(authenticatedFilter.shouldFilter());
    }

    @Test
    public void jwtSigninShouldFilter() {
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v1/security/signin");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.setMethod("POST");
        RequestContext.getCurrentContext();
        assertTrue(signinJwtFilter.shouldFilter());
    }

    @Test
    public void jwtSignupShouldFilter() {
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v1/security/signup");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.setMethod("POST");
        RequestContext.getCurrentContext();
        assertTrue(signupJwtFilter.shouldFilter());
    }

    @Test
    public void authenticatedFilterToenProblem() {

        //Setup
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v2.3.4/closed/1");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.addHeader("jwt-auth",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdXRoZW50aWNhdGVkIjp0cnVlLCJyb2xlIjoiUk9PVCIsInZpc2l0SUQiOiJlM2VjNjJkOC1kZTljLTRkN2MtOTJmMi0yNzRhODllNTVlMjMiLCJwcm9maWxlSUQiOiIxYTk0YTc4NC0yM2M1LTQwMWYtYTA3Mi1iZGIyNTZmODk3YWMiLCJuYW1lIjoiRG9Ub2RheUFkbWluIiwiaXNzIjoiZG90b2RheSIsImV4cCI6MTUwMjM2NzkzOSwidXNlciI6IjIxIiwiaWF0IjoxNTAyMzY0MzM5LCJqdGkiOiJlY2M0YmY3My1mOGMyLTRiMDUtOTc5ZS0zYTAwYzFhNTc4MDIifQ.8A6CHyJ3SH7mmAfCTquKFBVRAQ3tWQ7xnY5EO9E5SZo");
        final RequestContext requestContext = RequestContext.getCurrentContext();

        PrivateJwt privateJwt = new PrivateJwt();
        privateJwt.setValid(true);
        privateJwt.setSecret("secret");
        when(tokenRepository.getToken(anyString())).thenReturn(privateJwt);
        when(securityService.verifyJwt(anyString())).thenThrow(new SignatureVerificationException(Algorithm.HMAC256("secret")));

        //Call
        authenticatedFilter.run();

        //Verify
        assertEquals(401, requestContext.getResponse().getStatus());
        assertTrue(authenticatedFilter.shouldFilter());
    }

    @Test
    public void authenticatedFilterTokenOK() {

        //Setup
        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v2.3.4/closed/1");
        request.setRemoteAddr("5.6.7.8");
        request.setServerPort(8080);
        request.addHeader("jwt-auth",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdXRoZW50aWNhdGVkIjp0cnVlLCJyb2xlIjoiUk9PVCIsInZpc2l0SUQiOiJlM2VjNjJkOC1kZTljLTRkN2MtOTJmMi0yNzRhODllNTVlMjMiLCJwcm9maWxlSUQiOiIxYTk0YTc4NC0yM2M1LTQwMWYtYTA3Mi1iZGIyNTZmODk3YWMiLCJuYW1lIjoiRG9Ub2RheUFkbWluIiwiaXNzIjoiZG90b2RheSIsImV4cCI6MTUwMjM2NzkzOSwidXNlciI6IjIxIiwiaWF0IjoxNTAyMzY0MzM5LCJqdGkiOiJlY2M0YmY3My1mOGMyLTRiMDUtOTc5ZS0zYTAwYzFhNTc4MDIifQ.8A6CHyJ3SH7mmAfCTquKFBVRAQ3tWQ7xnY5EO9E5SZo");
        final RequestContext requestContext = RequestContext.getCurrentContext();

        PrivateJwt privateJwt = new PrivateJwt();
        privateJwt.setValid(true);
        privateJwt.setSecret("secret");
        when(tokenRepository.getToken(anyString())).thenReturn(privateJwt);
        when(securityService.verifyJwt(anyString())).thenReturn("jwt");

        //Call
        authenticatedFilter.run();

        //Verify
        assertEquals(200, requestContext.getResponse().getStatus());
        assertTrue(authenticatedFilter.shouldFilter());
    }
}
