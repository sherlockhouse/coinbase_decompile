package com.coinbase.api.internal.models.policyRestrictions;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Url extends Url {
    private final String link;
    private final String text;

    C$AutoValue_Url(String link, String text) {
        this.link = link;
        this.text = text;
    }

    @SerializedName("link")
    public String getLink() {
        return this.link;
    }

    @SerializedName("text")
    public String getText() {
        return this.text;
    }

    public String toString() {
        return "Url{link=" + this.link + ", text=" + this.text + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Url)) {
            return false;
        }
        Url that = (Url) o;
        if (this.link != null) {
            if (this.link.equals(that.getLink())) {
            }
            return false;
        }
        if (this.text == null) {
            if (that.getText() == null) {
                return true;
            }
        } else if (this.text.equals(that.getText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.link == null ? 0 : this.link.hashCode())) * 1000003;
        if (this.text != null) {
            i = this.text.hashCode();
        }
        return h ^ i;
    }
}
