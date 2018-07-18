package com.coinbase.android.settings;

import rx.subjects.PublishSubject;

public class SettingsPreferenceItemClickedConnector {
    private final PublishSubject<SettingsPreferenceItem> mSubject;

    public SettingsPreferenceItemClickedConnector() {
        this(PublishSubject.create());
    }

    public SettingsPreferenceItemClickedConnector(PublishSubject<SettingsPreferenceItem> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<SettingsPreferenceItem> get() {
        return this.mSubject;
    }
}
