package com.coinbase.android.buysell;

import android.content.Intent;
import android.util.Pair;
import com.coinbase.android.event.ClassConsumableEvent;
import rx.subjects.BehaviorSubject;

public class Buy3dsVerificationConnector {
    private final BehaviorSubject<Pair<ClassConsumableEvent, Pair<Integer, Intent>>> mSubject;

    public Buy3dsVerificationConnector() {
        this(BehaviorSubject.create());
    }

    public Buy3dsVerificationConnector(BehaviorSubject<Pair<ClassConsumableEvent, Pair<Integer, Intent>>> subject) {
        this.mSubject = subject;
    }

    public BehaviorSubject<Pair<ClassConsumableEvent, Pair<Integer, Intent>>> get() {
        return this.mSubject;
    }
}
