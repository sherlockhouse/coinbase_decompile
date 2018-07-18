package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodResponse extends ResponseV2 {
    private static final long serialVersionUID = 9092224278859996820L;
    @JsonProperty("data")
    private PaymentMethod _paymentMethod;

    public PaymentMethod getPaymentMethod() {
        return this._paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this._paymentMethod = paymentMethod;
    }
}
