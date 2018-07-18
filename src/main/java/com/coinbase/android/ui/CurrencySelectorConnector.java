package com.coinbase.android.ui;

import rx.subjects.PublishSubject;

public final class CurrencySelectorConnector {
    private final PublishSubject<String> mNativeCurrencySelectorSubject;

    public CurrencySelectorConnector() {
        this(PublishSubject.create());
    }

    public CurrencySelectorConnector(PublishSubject<String> nativeCurrencySelectorSubject) {
        this.mNativeCurrencySelectorSubject = nativeCurrencySelectorSubject;
    }

    public PublishSubject<String> getNative() {
        return this.mNativeCurrencySelectorSubject;
    }
}
