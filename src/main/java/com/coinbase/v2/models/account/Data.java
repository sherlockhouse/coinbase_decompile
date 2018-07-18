package com.coinbase.v2.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("balance")
    @Expose
    private Balance balance;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("currency")
    @Expose
    private Currency currency;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("native_balance")
    @Expose
    private NativeBalance nativeBalance;
    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("ready")
    @Expose
    private Boolean ready;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    private Type type;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public enum Type {
        WALLET,
        VAULT,
        MULTISIG_VAULT,
        MULTISIG,
        FORK_MULTISIG_VAULT,
        FIAT,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }

        public static Type toType(String value) {
            try {
                return valueOf(value.toUpperCase());
            } catch (Exception e) {
                return UNKNOWN;
            }
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return this.primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public NativeBalance getNativeBalance() {
        return this.nativeBalance;
    }

    public void setNativeBalance(NativeBalance nativeBalance) {
        this.nativeBalance = nativeBalance;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Boolean getReady() {
        return this.active;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
