package com.coinbase.v1.entity;

public class OAuthCodeResponse extends Response {
    private static final long serialVersionUID = -594668473446848581L;
    private String _code;

    public String getCode() {
        return this._code;
    }

    public void setCode(String code) {
        this._code = code;
    }
}
