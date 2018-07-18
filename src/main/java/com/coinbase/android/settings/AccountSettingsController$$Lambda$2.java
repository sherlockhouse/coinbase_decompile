package com.coinbase.android.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountSettingsController$$Lambda$2 implements OnClickListener {
    private final AccountSettingsController arg$1;

    private AccountSettingsController$$Lambda$2(AccountSettingsController accountSettingsController) {
        this.arg$1 = accountSettingsController;
    }

    public static OnClickListener lambdaFactory$(AccountSettingsController accountSettingsController) {
        return new AccountSettingsController$$Lambda$2(accountSettingsController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onIncreaseLimitsClicked(this.arg$1.mBinding.btnIncreaseYourLimits.getText().toString());
    }
}
