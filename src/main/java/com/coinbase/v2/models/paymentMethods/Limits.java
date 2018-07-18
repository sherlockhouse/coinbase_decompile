package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Limits {
    @SerializedName("buy")
    @Expose
    private List<Limit> buy = new ArrayList();
    @SerializedName("deposit")
    @Expose
    private List<Limit> deposit = new ArrayList();
    @SerializedName("instant_buy")
    @Expose
    private List<Limit> instantBuy = new ArrayList();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sell")
    @Expose
    private List<Limit> sell = new ArrayList();
    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Limit> getBuy() {
        return this.buy;
    }

    public void setBuy(List<Limit> buy) {
        this.buy = buy;
    }

    public List<Limit> getInstantBuy() {
        return this.instantBuy;
    }

    public void setInstantBuy(List<Limit> instantBuy) {
        this.instantBuy = instantBuy;
    }

    public List<Limit> getSell() {
        return this.sell;
    }

    public void setSell(List<Limit> sell) {
        this.sell = sell;
    }

    public List<Limit> getDeposit() {
        return this.deposit;
    }

    public void setDeposit(List<Limit> deposit) {
        this.deposit = deposit;
    }
}
