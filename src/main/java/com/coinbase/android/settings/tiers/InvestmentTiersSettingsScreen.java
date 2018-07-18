package com.coinbase.android.settings.tiers;

public interface InvestmentTiersSettingsScreen {
    void hideBuyDepositLimits();

    void hideCompleteAccountSetupHeader();

    void hideLifetimeLimit();

    void hideLimitsHeader();

    void hideLimitsHeaderCallToActionButton();

    void hideTierSuccess();

    void hideTiers();

    boolean isShown();

    boolean isTierSuccessShown();

    void notifyBuyLimitsAdapter();

    void notifyTiersAdapter();

    void setBuyLimitsHeaderText(String str);

    void setTiersHeader(String str);

    void showCompleteAccountSetupHeader(String str);

    void showLevelTitle(String str);

    void showLifetimeLimit(String str, String str2, String str3, int i);

    void showLifetimeLimitDescription(String str);

    void showLimitsHeader();

    void showLimitsHeaderCallToActionButton(String str);

    void showTierSuccess();
}
