package com.coinbase.api.internal.models.alerts;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<String> currencies;
    private final String description;
    private final Boolean dismissable;
    private final String id;
    private final String imageUrl;
    private final List<String> paymentMethods;
    private final String title;
    private final String type;
    private final String url;
    private final List<String> views;

    C$AutoValue_Data(String title, String description, String url, String imageUrl, String type, List<String> views, List<String> paymentMethods, List<String> currencies, Boolean dismissable, String id) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.type = type;
        this.views = views;
        this.paymentMethods = paymentMethods;
        this.currencies = currencies;
        this.dismissable = dismissable;
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.id = id;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("url")
    public String getUrl() {
        return this.url;
    }

    @SerializedName("image_url")
    public String getImageUrl() {
        return this.imageUrl;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("views")
    public List<String> getViews() {
        return this.views;
    }

    @SerializedName("payment_methods")
    public List<String> getPaymentMethods() {
        return this.paymentMethods;
    }

    @SerializedName("currencies")
    public List<String> getCurrencies() {
        return this.currencies;
    }

    @SerializedName("dismissable")
    public Boolean getDismissable() {
        return this.dismissable;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    public String toString() {
        return "Data{title=" + this.title + ", description=" + this.description + ", url=" + this.url + ", imageUrl=" + this.imageUrl + ", type=" + this.type + ", views=" + this.views + ", paymentMethods=" + this.paymentMethods + ", currencies=" + this.currencies + ", dismissable=" + this.dismissable + ", id=" + this.id + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(that.getUrl())) {
            }
            return false;
        }
        if (this.imageUrl != null) {
            if (this.imageUrl.equals(that.getImageUrl())) {
            }
            return false;
        }
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.views != null) {
            if (this.views.equals(that.getViews())) {
            }
            return false;
        }
        if (this.paymentMethods != null) {
            if (this.paymentMethods.equals(that.getPaymentMethods())) {
            }
            return false;
        }
        if (this.currencies != null) {
            if (this.currencies.equals(that.getCurrencies())) {
            }
            return false;
        }
        if (this.dismissable != null) {
            if (this.dismissable.equals(that.getDismissable())) {
            }
            return false;
        }
        if (this.id.equals(that.getId())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((1 * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.url == null ? 0 : this.url.hashCode())) * 1000003) ^ (this.imageUrl == null ? 0 : this.imageUrl.hashCode())) * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.views == null ? 0 : this.views.hashCode())) * 1000003) ^ (this.paymentMethods == null ? 0 : this.paymentMethods.hashCode())) * 1000003) ^ (this.currencies == null ? 0 : this.currencies.hashCode())) * 1000003;
        if (this.dismissable != null) {
            i = this.dismissable.hashCode();
        }
        return ((h ^ i) * 1000003) ^ this.id.hashCode();
    }
}
