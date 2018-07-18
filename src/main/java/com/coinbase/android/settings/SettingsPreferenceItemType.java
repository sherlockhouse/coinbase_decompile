package com.coinbase.android.settings;

public enum SettingsPreferenceItemType {
    ITEM(0),
    HEADER(1),
    INFO(2),
    CONNECTED_ACCOUNTS(3);
    
    private int mValue;

    private SettingsPreferenceItemType(int value) {
        this.mValue = value;
    }

    int getValue() {
        return this.mValue;
    }
}
