package com.coinbase.api.internal.models.currency;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Image extends Image {
    private final String url;

    static final class Builder extends com.coinbase.api.internal.models.currency.Image.Builder {
        private String url;

        Builder() {
        }

        Builder(Image source) {
            this.url = source.getUrl();
        }

        public com.coinbase.api.internal.models.currency.Image.Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Image build() {
            String missing = "";
            if (this.url == null) {
                missing = missing + " url";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Image(this.url);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Image(String url) {
        if (url == null) {
            throw new NullPointerException("Null url");
        }
        this.url = url;
    }

    @SerializedName("url")
    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "Image{url=" + this.url + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        return this.url.equals(((Image) o).getUrl());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.url.hashCode();
    }
}
