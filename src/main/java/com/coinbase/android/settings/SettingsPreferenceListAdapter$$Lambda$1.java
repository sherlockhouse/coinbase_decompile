package com.coinbase.android.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SettingsPreferenceListAdapter$$Lambda$1 implements OnClickListener {
    private final SettingsPreferenceItem arg$1;

    private SettingsPreferenceListAdapter$$Lambda$1(SettingsPreferenceItem settingsPreferenceItem) {
        this.arg$1 = settingsPreferenceItem;
    }

    public static OnClickListener lambdaFactory$(SettingsPreferenceItem settingsPreferenceItem) {
        return new SettingsPreferenceListAdapter$$Lambda$1(settingsPreferenceItem);
    }

    public void onClick(View view) {
        this.arg$1.onClick();
    }
}
