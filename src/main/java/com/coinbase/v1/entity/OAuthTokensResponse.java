package com.coinbase.v1.entity;

public class OAuthTokensResponse extends Response {
    private static final long serialVersionUID = -6431207976215527058L;
    private String _accessToken;
    private Integer _expireIn;
    private String _refreshToken;
    private String _scope;
    private String _tokenType;

    public String getAccessToken() {
        return this._accessToken;
    }

    public void setAccessToken(String accessToken) {
        this._accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this._refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this._refreshToken = refreshToken;
    }

    public String getTokenType() {
        return this._tokenType;
    }

    public void setTokenType(String tokenType) {
        this._tokenType = tokenType;
    }

    public String getScope() {
        return this._scope;
    }

    public void setScope(String scope) {
        this._scope = scope;
    }

    public Integer getExpireIn() {
        return this._expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this._expireIn = expireIn;
    }
}
