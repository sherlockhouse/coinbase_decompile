package com.coinbase.v1.entity;

public class UserResponse extends Response {
    private static final long serialVersionUID = 8847695815066590925L;
    private OAuthTokensResponse _oauth;
    private User _user;

    public User getUser() {
        return this._user;
    }

    public void setUser(User user) {
        this._user = user;
    }

    public OAuthTokensResponse getOauth() {
        return this._oauth;
    }

    public void setOauth(OAuthTokensResponse oauth) {
        this._oauth = oauth;
    }
}
