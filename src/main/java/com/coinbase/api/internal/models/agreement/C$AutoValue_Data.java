package com.coinbase.api.internal.models.agreement;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final String html;

    C$AutoValue_Data(String html) {
        this.html = html;
    }

    @SerializedName("html")
    public String getHtml() {
        return this.html;
    }

    public String toString() {
        return "Data{html=" + this.html + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.html != null) {
            return this.html.equals(that.getHtml());
        }
        if (that.getHtml() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.html == null ? 0 : this.html.hashCode());
    }
}
