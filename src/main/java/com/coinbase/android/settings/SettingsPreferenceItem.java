package com.coinbase.android.settings;

public interface SettingsPreferenceItem {
    String getDisplayDescription();

    String getDisplayName();

    int getDisplayNameTextColor();

    String getDisplayValue();

    SettingsPreferenceItemType getType();

    boolean isNavigationEnabled();

    boolean isSwitchOn();

    void onClick();

    boolean showSwitch();
}
