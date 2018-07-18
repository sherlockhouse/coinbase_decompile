package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaymentMethodsResponse extends ResponseV2 {
    private static final long serialVersionUID = 4752885593284986181L;
    private String _defaultBuy;
    private String _defaultSell;
    @JsonProperty("data")
    private List<PaymentMethod> _paymentMethods;

    public String getDefaultBuy() {
        return this._defaultBuy;
    }

    public void setDefaultBuy(String defaultBuy) {
        this._defaultBuy = defaultBuy;
    }

    public String getDefaultSell() {
        return this._defaultSell;
    }

    public void setDefaultSell(String defaultSell) {
        this._defaultSell = defaultSell;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this._paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this._paymentMethods = paymentMethods;
    }
}
