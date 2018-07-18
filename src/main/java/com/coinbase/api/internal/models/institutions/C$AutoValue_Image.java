package com.coinbase.api.internal.models.institutions;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Image extends Image {
    private final String png;
    private final String svg;

    C$AutoValue_Image(String png, String svg) {
        this.png = png;
        this.svg = svg;
    }

    @SerializedName("png")
    public String getPng() {
        return this.png;
    }

    @SerializedName("svg")
    public String getSvg() {
        return this.svg;
    }

    public String toString() {
        return "Image{png=" + this.png + ", svg=" + this.svg + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        Image that = (Image) o;
        if (this.png != null) {
            if (this.png.equals(that.getPng())) {
            }
            return false;
        }
        if (this.svg == null) {
            if (that.getSvg() == null) {
                return true;
            }
        } else if (this.svg.equals(that.getSvg())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.png == null ? 0 : this.png.hashCode())) * 1000003;
        if (this.svg != null) {
            i = this.svg.hashCode();
        }
        return h ^ i;
    }
}
