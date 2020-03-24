package com.nbs.iais.cloud.zuul.jwt.repository;

import com.nbs.iais.cloud.zuul.jwt.model.PrivateJwt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;

public class TokenRepository  {

    private final static Logger LOG = LogManager.getLogger(TokenRepository.class);

    private final Cache tokenCache;

    public TokenRepository(final Cache tokenCache) {
        this.tokenCache = tokenCache;
    }

    public PrivateJwt getToken(final String tokenKey) {
        LOG.debug("Not using cache to get token");

        final Cache.ValueWrapper valueWrapper = tokenCache.get(tokenKey);
        if (valueWrapper == null || valueWrapper.get() == null) {
            return null;
        }
        return (PrivateJwt) valueWrapper.get();
    }

    public void deleteToken(final String tokenKey) {
        LOG.debug("Deleting token");
        tokenCache.evict(tokenKey);
    }

    public PrivateJwt savePrivateToken(final String tokenKey, final PrivateJwt jwtPrivate) {
        LOG.debug("Adding token to cache");
        // maybe we can use this statement instead
        // return (JwtPrivate) tokenCache.putIfAbsent(tokenKey,
        // jwtPrivate).get();
        tokenCache.put(tokenKey, jwtPrivate);
        return jwtPrivate;
    }

}