package com.coinbase.v1.entity;

import com.coinbase.ApiConstants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

public class OAuthTokensRequest implements Serializable {
    private static final long serialVersionUID = 1267065019984284435L;
    private String clientId;
    private String clientSecret;
    private String code;
    private GrantType grantType;
    private String redirectUri;
    private String refreshToken;

    public enum GrantType {
        AUTHORIZATION_CODE("authorization_code"),
        REFRESH_TOKEN(ApiConstants.REFRESH_TOKEN);
        
        private String _value;

        private GrantType(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static GrantType create(String val) {
            for (GrantType type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public GrantType getGrantType() {
        return this.grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
