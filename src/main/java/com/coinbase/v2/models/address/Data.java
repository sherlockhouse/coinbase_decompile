package com.coinbase.v2.models.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("callback_url")
    @Expose
    private String callbackUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("legacy_address")
    @Expose
    private String legacyAddress;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("uri_scheme")
    @Expose
    private String uriScheme;
    @SerializedName("warning_details")
    @Expose
    private String warningDetails;
    @SerializedName("warning_title")
    @Expose
    private String warningTitle;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegacyAddress() {
        return this.legacyAddress;
    }

    public void setLegacyAddress(String legacyAddress) {
        this.legacyAddress = legacyAddress;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUriScheme() {
        return this.uriScheme;
    }

    public void setUriScheme(String uriScheme) {
        this.uriScheme = uriScheme;
    }

    public String getWarningDetails() {
        return this.warningDetails;
    }

    public void setWarningDetails(String warningDetails) {
        this.warningDetails = warningDetails;
    }

    public String getWarningTitle() {
        return this.warningTitle;
    }

    public void setWarningTitle(String warningTitle) {
        this.warningTitle = warningTitle;
    }
}
