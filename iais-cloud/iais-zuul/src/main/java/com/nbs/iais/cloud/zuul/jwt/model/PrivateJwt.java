package com.nbs.iais.cloud.zuul.jwt.model;
import java.io.Serializable;

public class PrivateJwt implements Serializable {

    /**
     * secret of the provided jwt
     */
    private String secret;

    /**
     * uses to invalidate provided tokens
     */
    private boolean valid = true;


    public PrivateJwt() {
        super();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
