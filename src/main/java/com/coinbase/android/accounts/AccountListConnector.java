package com.coinbase.android.accounts;

import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.v2.models.account.Data;
import java.util.ArrayList;
import java.util.List;
import rx.subjects.BehaviorSubject;

public class AccountListConnector implements ApplicationSignOutListener {
    private volatile BehaviorSubject<List<Data>> mSubject;

    public AccountListConnector() {
        this(BehaviorSubject.create(new ArrayList()));
    }

    public AccountListConnector(BehaviorSubject<List<Data>> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<List<Data>> get() {
        return this.mSubject;
    }

    public void onApplicationSignOut() {
        this.mSubject = BehaviorSubject.create(new ArrayList());
    }
}
