package com.coinbase.android.settings;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$17 implements Func1 {
    private final AccountSettingsPresenter arg$1;
    private final String arg$2;

    private AccountSettingsPresenter$$Lambda$17(AccountSettingsPresenter accountSettingsPresenter, String str) {
        this.arg$1 = accountSettingsPresenter;
        this.arg$2 = str;
    }

    public static Func1 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter, String str) {
        return new AccountSettingsPresenter$$Lambda$17(accountSettingsPresenter, str);
    }

    public Object call(Object obj) {
        return AccountSettingsPresenter.lambda$onNativeCurrencyItemClicked$16(this.arg$1, this.arg$2, (Pair) obj);
    }
}
