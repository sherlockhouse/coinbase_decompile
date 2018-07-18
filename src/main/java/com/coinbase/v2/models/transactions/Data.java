package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("buy")
    @Expose
    private Trade buy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("delayed")
    @Expose
    private Boolean delayed;
    @SerializedName("delayed_send_date")
    @Expose
    private String delayedSendDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("exchange_deposit")
    @Expose
    private Trade exchangeDeposit;
    @SerializedName("exchange_withdrawal")
    @Expose
    private Trade exchangeWithdrawal;
    @SerializedName("fiat_deposit")
    @Expose
    private Trade fiatDeposit;
    @SerializedName("fiat_withdrawal")
    @Expose
    private Trade fiatWithdrawal;
    @SerializedName("from")
    @Expose
    private Entity from;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idem")
    @Expose
    private String idem;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("instant_exchange")
    @Expose
    private Boolean instantExchange;
    @SerializedName("native_amount")
    @Expose
    private NativeAmount nativeAmount;
    @SerializedName("network")
    @Expose
    private Network network;
    @SerializedName("request")
    @Expose
    private Trade request;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    @SerializedName("sell")
    @Expose
    private Trade sell;
    @SerializedName("send")
    @Expose
    private Trade send;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("to")
    @Expose
    private Entity to;
    @SerializedName("transfer")
    @Expose
    private Trade transfer;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("vault_withdrawal")
    @Expose
    private Trade vaultWithdrawal;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelayedSendDate() {
        return this.delayedSendDate;
    }

    public void setDelayedSendDate(String delayedSendDate) {
        this.delayedSendDate = delayedSendDate;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public NativeAmount getNativeAmount() {
        return this.nativeAmount;
    }

    public void setNativeAmount(NativeAmount nativeAmount) {
        this.nativeAmount = nativeAmount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Network getNetwork() {
        return this.network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getIdem() {
        return this.idem;
    }

    public void setIdem(String idem) {
        this.idem = idem;
    }

    public Entity getTo() {
        return this.to;
    }

    public void setTo(Entity to) {
        this.to = to;
    }

    public Entity getFrom() {
        return this.from;
    }

    public void setFrom(Entity from) {
        this.from = from;
    }

    public Trade getBuy() {
        return this.buy;
    }

    public void setBuy(Trade buy) {
        this.buy = buy;
    }

    public Trade getSell() {
        return this.sell;
    }

    public void setSell(Trade sell) {
        this.sell = sell;
    }

    public Trade getRequest() {
        return this.request;
    }

    public void setRequest(Trade request) {
        this.request = request;
    }

    public Trade getTransfer() {
        return this.transfer;
    }

    public void setTransfer(Trade transfer) {
        this.transfer = transfer;
    }

    public Trade getSend() {
        return this.send;
    }

    public void setSend(Trade send) {
        this.send = send;
    }

    public Trade getFiatWithdrawal() {
        return this.fiatWithdrawal;
    }

    public void setFiatWithdrawal(Trade fiatWithdrawal) {
        this.fiatWithdrawal = fiatWithdrawal;
    }

    public Trade getFiatDeposit() {
        return this.fiatDeposit;
    }

    public void setFiatDeposit(Trade fiatDeposit) {
        this.fiatDeposit = fiatDeposit;
    }

    public Trade getExchangeDeposit() {
        return this.exchangeDeposit;
    }

    public void setExchangeDeposit(Trade exchangeDeposit) {
        this.exchangeDeposit = exchangeDeposit;
    }

    public Trade getExchangeWithdrawal() {
        return this.exchangeWithdrawal;
    }

    public void setExchangeWithdrawal(Trade exchangeWithdrawal) {
        this.exchangeWithdrawal = exchangeWithdrawal;
    }

    public Trade getVaultWithdrawal() {
        return this.vaultWithdrawal;
    }

    public void setVaultWithdrawal(Trade vaultWithdrawal) {
        this.vaultWithdrawal = vaultWithdrawal;
    }

    public Boolean getDelayed() {
        return this.delayed;
    }

    public void setDelayed(Boolean delayed) {
        this.delayed = delayed;
    }

    public Boolean getInstantExchange() {
        return this.instantExchange;
    }

    public void setInstantExchange(Boolean instantExchange) {
        this.instantExchange = instantExchange;
    }

    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
