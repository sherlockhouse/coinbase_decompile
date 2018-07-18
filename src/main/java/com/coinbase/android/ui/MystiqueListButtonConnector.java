package com.coinbase.android.ui;

import rx.subjects.PublishSubject;

public class MystiqueListButtonConnector {
    private final PublishSubject<MystiqueListButtonEvent> mMystiqueListButtonSubject;

    public enum MystiqueListButtonEvent {
        ACTION,
        CLOSE
    }

    public MystiqueListButtonConnector() {
        this(PublishSubject.create());
    }

    public MystiqueListButtonConnector(PublishSubject<MystiqueListButtonEvent> mystiqueListButtonSubject) {
        this.mMystiqueListButtonSubject = mystiqueListButtonSubject;
    }

    public PublishSubject<MystiqueListButtonEvent> get() {
        return this.mMystiqueListButtonSubject;
    }
}
