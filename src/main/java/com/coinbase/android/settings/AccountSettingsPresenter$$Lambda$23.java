package com.coinbase.android.settings;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$23 implements Action1 {
    private final AccountSettingsPresenter arg$1;

    private AccountSettingsPresenter$$Lambda$23(AccountSettingsPresenter accountSettingsPresenter) {
        this.arg$1 = accountSettingsPresenter;
    }

    public static Action1 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter) {
        return new AccountSettingsPresenter$$Lambda$23(accountSettingsPresenter);
    }

    public void call(Object obj) {
        AccountSettingsPresenter.lambda$getVerificationList$22(this.arg$1, (Pair) obj);
    }
}
