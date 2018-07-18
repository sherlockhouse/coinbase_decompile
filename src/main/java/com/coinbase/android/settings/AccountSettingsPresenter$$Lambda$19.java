package com.coinbase.android.settings;

import rx.functions.Action1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$19 implements Action1 {
    private final AccountSettingsPresenter arg$1;

    private AccountSettingsPresenter$$Lambda$19(AccountSettingsPresenter accountSettingsPresenter) {
        this.arg$1 = accountSettingsPresenter;
    }

    public static Action1 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter) {
        return new AccountSettingsPresenter$$Lambda$19(accountSettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.handleErrorResponse((Throwable) obj);
    }
}
