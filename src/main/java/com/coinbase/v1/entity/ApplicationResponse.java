package com.coinbase.v1.entity;

public class ApplicationResponse extends Response {
    private static final long serialVersionUID = -8421060498323905062L;
    private Application _application;

    public Application getApplication() {
        return this._application;
    }

    public void setApplication(Application application) {
        this._application = application;
    }
}
