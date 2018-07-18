package com.coinbase.android.settings.gdpr;

import com.coinbase.android.settings.gdpr.GdpPrivacyRequestViewModel.RequestActionType;
import java.util.List;

interface PrivacyRequestViewModel {
    RequestActionType getActionType();

    GdprSettingsEvent getAnalyticsEvent();

    String getLegalHeader();

    List<GdprPrivacyRequestSettingsPreferenceItem> getOptions();

    String getTitle();

    boolean isTerminalPage();
}
