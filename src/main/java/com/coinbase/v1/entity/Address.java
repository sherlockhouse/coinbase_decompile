package com.coinbase.v1.entity;

import java.io.Serializable;
import org.joda.time.DateTime;

public class Address implements Serializable {
    private static final long serialVersionUID = -2215555555599527880L;
    private String _address;
    private String _callbackUrl;
    private DateTime _createdAt;
    private String _label;

    public String getAddress() {
        return this._address;
    }

    public void setAddress(String address) {
        this._address = address;
    }

    public String getCallbackUrl() {
        return this._callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this._callbackUrl = callbackUrl;
    }

    public String getLabel() {
        return this._label;
    }

    public void setLabel(String label) {
        this._label = label;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }
}
