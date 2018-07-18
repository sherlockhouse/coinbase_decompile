package com.coinbase.android.settings;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$18 implements Action1 {
    private final AccountSettingsPresenter arg$1;

    private AccountSettingsPresenter$$Lambda$18(AccountSettingsPresenter accountSettingsPresenter) {
        this.arg$1 = accountSettingsPresenter;
    }

    public static Action1 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter) {
        return new AccountSettingsPresenter$$Lambda$18(accountSettingsPresenter);
    }

    public void call(Object obj) {
        AccountSettingsPresenter.lambda$onNativeCurrencyItemClicked$17(this.arg$1, (Pair) obj);
    }
}
