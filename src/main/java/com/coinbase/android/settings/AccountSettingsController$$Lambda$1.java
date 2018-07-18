package com.coinbase.android.settings;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;

final /* synthetic */ class AccountSettingsController$$Lambda$1 implements OnOffsetChangedListener {
    private final AccountSettingsController arg$1;

    private AccountSettingsController$$Lambda$1(AccountSettingsController accountSettingsController) {
        this.arg$1 = accountSettingsController;
    }

    public static OnOffsetChangedListener lambdaFactory$(AccountSettingsController accountSettingsController) {
        return new AccountSettingsController$$Lambda$1(accountSettingsController);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        this.arg$1.handlePortfolioTitleHeaderVisibility(appBarLayout, i);
    }
}
