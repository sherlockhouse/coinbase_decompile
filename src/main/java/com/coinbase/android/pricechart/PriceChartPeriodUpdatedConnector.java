package com.coinbase.android.pricechart;

import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import rx.subjects.PublishSubject;

public final class PriceChartPeriodUpdatedConnector {
    private final PublishSubject<Period> mSubject;

    public PriceChartPeriodUpdatedConnector() {
        this(PublishSubject.create());
    }

    public PriceChartPeriodUpdatedConnector(PublishSubject<Period> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Period> get() {
        return this.mSubject;
    }
}
