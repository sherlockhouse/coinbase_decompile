package com.coinbase.v1.entity;

import java.io.Serializable;

public class IAVField implements Serializable {
    private static final long serialVersionUID = 801998909627208076L;
    String _id;
    String _name;
    Boolean _secure;

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public Boolean getSecure() {
        return this._secure;
    }

    public void setSecure(Boolean secure) {
        this._secure = secure;
    }
}
