package com.coinbase.android.notifications.priceAlerts;

import rx.subjects.PublishSubject;

public final class PriceAlertsConnector {
    private final PublishSubject<LocalPriceAlert> mSubject;

    public PriceAlertsConnector() {
        this(PublishSubject.create());
    }

    PriceAlertsConnector(PublishSubject<LocalPriceAlert> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<LocalPriceAlert> get() {
        return this.mSubject;
    }
}
