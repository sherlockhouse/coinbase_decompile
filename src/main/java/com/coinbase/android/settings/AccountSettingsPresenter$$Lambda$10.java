package com.coinbase.android.settings;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$10 implements Func1 {
    private static final AccountSettingsPresenter$$Lambda$10 instance = new AccountSettingsPresenter$$Lambda$10();

    private AccountSettingsPresenter$$Lambda$10() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return AccountSettingsPresenter.lambda$onResume$9((ClassConsumableEvent) obj);
    }
}
