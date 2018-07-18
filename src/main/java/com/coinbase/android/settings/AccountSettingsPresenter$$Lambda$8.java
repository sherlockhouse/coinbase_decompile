package com.coinbase.android.settings;

import com.coinbase.api.internal.models.idology.Data;
import rx.functions.Action1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$8 implements Action1 {
    private final AccountSettingsPresenter arg$1;

    private AccountSettingsPresenter$$Lambda$8(AccountSettingsPresenter accountSettingsPresenter) {
        this.arg$1 = accountSettingsPresenter;
    }

    public static Action1 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter) {
        return new AccountSettingsPresenter$$Lambda$8(accountSettingsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.updateIdologyPrefList((Data) obj);
    }
}
