package com.coinbase.android.paymentmethods;

import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.android.event.ClassConsumableEvent;
import javax.inject.Inject;
import rx.subjects.BehaviorSubject;

@ApplicationScope
public class BankAccountsUpdatedConnector implements ApplicationSignOutListener {
    private BehaviorSubject<ClassConsumableEvent> mSubject;

    @Inject
    public BankAccountsUpdatedConnector() {
        this(BehaviorSubject.create());
    }

    BankAccountsUpdatedConnector(BehaviorSubject<ClassConsumableEvent> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<ClassConsumableEvent> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create();
    }
}
