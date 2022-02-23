package com.nbs.iais.cloud.zuul.filters.post;

import com.nbs.iais.cloud.zuul.config.FilterConfig;
import com.nbs.iais.cloud.zuul.jwt.repository.TokenRepository;
import com.nbs.iais.cloud.zuul.jwt.service.SecurityService;
import com.nbs.iais.cloud.zuul.utils.SecurityTools;
import com.netflix.zuul.context.RequestContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@Import(FilterConfig.class)
public class JwtSigninFilterTest {


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

    private PreDecorationFilter preDecorationFilter;
    private RibbonRoutingFilter ribbonRoutingFilter;

    @Autowired
    private SigninJwtFilter signinJwtFilter;
    @Autowired
    private SignupJwtFilter signupJwtFilter;
    private ZuulProperties zuulProperties;


    @BeforeAll
    public void setUp() {
        openMocks(this);

        zuulProperties = new ZuulProperties();

        final ServiceInstance serviceInstance = mock(DefaultServiceInstance.class);

        final DiscoveryClientRouteLocator routeLocator = new DiscoveryClientRouteLocator("/", discovery, zuulProperties, serviceInstance);
        preDecorationFilter = new PreDecorationFilter(routeLocator, "/", zuulProperties, proxyRequestHelper);

        final RequestContext ctx = RequestContext.getCurrentContext();
        ctx.clear();
        ctx.setRequest(request);
        ctx.setResponse(response);
    }

    @AfterAll
    static void clear() {
        RequestContext.getCurrentContext().clear();
    }

    @Test
    public void shouldFilterTest() {
        //Setup
        request.setRequestURI("/api/v1/security/signin");
        request.setMethod("POST");
        RequestContext.getCurrentContext();

        //Verify
        Assertions.assertTrue(signinJwtFilter.shouldFilter());
    }

    @Test
    public void jwtSigninFilterTest() throws UnsupportedEncodingException {

        zuulProperties.setPrefix("/api");
        request.setRequestURI("/api/v1/security/signin");
        request.setMethod("POST");
        final String jsonResponse = "{\n" +
                "    \"id\": \"60fa3ee5-c886-4f44-89ec-495b27ef9876\",\n" +
                "    \"name\": \"Florian Nika\",\n" +
                "    \"username\": \"florian\",\n" +
                "    \"email\": \"florian.nika@parstat.md\",\n" +
                "    \"role\": \"USER\",\n" +
                "    \"status\": \"ACTIVE\"\n" +
                "}";
        response.getWriter().write(jsonResponse);

        when(securityService.getToken(anyString())).thenReturn(Optional.of("jwt"));


        InputStream inputStream = new ByteArrayInputStream(jsonResponse.getBytes());
        //Run
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseDataStream(inputStream);

        preDecorationFilter.run();
        signinJwtFilter.run();


        //Verify
        Assertions.assertEquals(requestContext.getZuulResponseHeaders().get(0).first(), "jwt-auth");
    }

}
