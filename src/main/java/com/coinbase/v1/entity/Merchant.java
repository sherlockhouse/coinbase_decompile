package com.coinbase.v1.entity;

import java.io.Serializable;

public class Merchant implements Serializable {
    private static final long serialVersionUID = -2561008208513493704L;
    private String _companyName;
    private Logo _logo;

    public static class Logo implements Serializable {
        private static final long serialVersionUID = -1227765058838128026L;
        private String medium;
        private String small;
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMedium() {
            return this.medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getSmall() {
            return this.small;
        }

        public void setSmall(String small) {
            this.small = small;
        }
    }

    public String getCompanyName() {
        return this._companyName;
    }

    public void setCompanyName(String companyName) {
        this._companyName = companyName;
    }

    public Logo getLogo() {
        return this._logo;
    }

    public void setLogo(Logo logo) {
        this._logo = logo;
    }
}
