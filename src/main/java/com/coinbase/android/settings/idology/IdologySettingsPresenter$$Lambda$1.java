package com.coinbase.android.settings.idology;

import rx.functions.Func1;

final /* synthetic */ class IdologySettingsPresenter$$Lambda$1 implements Func1 {
    private static final IdologySettingsPresenter$$Lambda$1 instance = new IdologySettingsPresenter$$Lambda$1();

    private IdologySettingsPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologySettingsPresenter.lambda$onShow$0((Boolean) obj);
    }
}
