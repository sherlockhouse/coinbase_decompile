package com.coinbase.android.dashboard;

import com.coinbase.api.internal.models.currency.Data;
import rx.subjects.PublishSubject;

public class DashboardPriceChartItemClickedConnector {
    private final PublishSubject<Data> mSubject;

    public DashboardPriceChartItemClickedConnector() {
        this(PublishSubject.create());
    }

    public DashboardPriceChartItemClickedConnector(PublishSubject<Data> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Data> get() {
        return this.mSubject;
    }
}
