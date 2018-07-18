package com.coinbase.android.idology;

import android.util.Pair;
import rx.subjects.PublishSubject;

public class IdologyAnswerSelectedConnector {
    private final PublishSubject<Pair<String, String>> mSubject;

    public IdologyAnswerSelectedConnector() {
        this(PublishSubject.create());
    }

    public IdologyAnswerSelectedConnector(PublishSubject<Pair<String, String>> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Pair<String, String>> get() {
        return this.mSubject;
    }
}
