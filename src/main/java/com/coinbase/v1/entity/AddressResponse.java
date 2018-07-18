package com.coinbase.v1.entity;

public class AddressResponse extends Response {
    private static final long serialVersionUID = -1558569647051796730L;
    private String _address;
    private String _callbackUrl;
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
}
