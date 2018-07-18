package com.coinbase.android.ui;

import rx.subjects.PublishSubject;

public class MystiqueListSelectorConnector {
    private final PublishSubject<Integer> mMystiqueListSelectorSubject;

    public MystiqueListSelectorConnector() {
        this(PublishSubject.create());
    }

    public MystiqueListSelectorConnector(PublishSubject<Integer> listSelectorSubject) {
        this.mMystiqueListSelectorSubject = listSelectorSubject;
    }

    public PublishSubject<Integer> get() {
        return this.mMystiqueListSelectorSubject;
    }
}
