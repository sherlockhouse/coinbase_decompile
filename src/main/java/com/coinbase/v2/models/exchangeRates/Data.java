package com.coinbase.v2.models.exchangeRates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Data {
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("rates")
    @Expose
    private Map<String, String> rates;

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, String> getRates() {
        return this.rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }
}
