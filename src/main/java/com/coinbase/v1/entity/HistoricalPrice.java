package com.coinbase.v1.entity;

import java.io.Serializable;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class HistoricalPrice implements Serializable {
    private static final long serialVersionUID = 7026579109955379515L;
    private Money _spotPrice;
    private DateTime _time;

    public DateTime getTime() {
        return this._time;
    }

    public void setTime(DateTime time) {
        this._time = time;
    }

    public Money getSpotPrice() {
        return this._spotPrice;
    }

    public void setSpotPrice(Money spotPrice) {
        this._spotPrice = spotPrice;
    }
}
