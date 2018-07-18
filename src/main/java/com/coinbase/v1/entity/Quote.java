package com.coinbase.v1.entity;

import com.coinbase.v1.deserializer.FeesCollector;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.HashMap;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Quote implements Serializable {
    private static final long serialVersionUID = -4797946450079069495L;
    private Money _btc;
    private HashMap<String, Money> _fees;
    private DateTime _payout_date;
    private Money _subtotal;
    private Money _total;

    public Money getBtc() {
        return this._btc;
    }

    public void setBtc(Money btc) {
        this._btc = btc;
    }

    public Money getSubtotal() {
        return this._subtotal;
    }

    public void setSubtotal(Money subtotal) {
        this._subtotal = subtotal;
    }

    public Money getTotal() {
        return this._total;
    }

    public void setTotal(Money total) {
        this._total = total;
    }

    public HashMap<String, Money> getFees() {
        return this._fees;
    }

    @JsonDeserialize(converter = FeesCollector.class)
    public void setFees(HashMap<String, Money> fees) {
        this._fees = fees;
    }

    public DateTime getPayoutDate() {
        return this._payout_date;
    }

    public void setPayoutDate(DateTime payout_date) {
        this._payout_date = payout_date;
    }
}
