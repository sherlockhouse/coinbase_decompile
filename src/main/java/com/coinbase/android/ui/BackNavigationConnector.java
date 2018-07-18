package com.coinbase.android.ui;

import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class BackNavigationConnector {
    private final PublishSubject<Void> mBackSubject;
    private final CompositeSubscription mBackSubscription;

    public BackNavigationConnector() {
        this(PublishSubject.create());
    }

    BackNavigationConnector(PublishSubject<Void> backSubject) {
        this.mBackSubscription = new CompositeSubscription();
        this.mBackSubject = backSubject;
    }

    public PublishSubject<Void> get() {
        return this.mBackSubject;
    }

    public void resubscribeBack(Subscription subscription) {
        this.mBackSubscription.clear();
        this.mBackSubscription.add(subscription);
    }

    public void clearBackSubscription() {
        this.mBackSubscription.clear();
    }
}
