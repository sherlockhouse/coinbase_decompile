package com.coinbase.android.settings.idology;

import rx.functions.Func1;

final /* synthetic */ class IdologySettingsPresenter$$Lambda$4 implements Func1 {
    private static final IdologySettingsPresenter$$Lambda$4 instance = new IdologySettingsPresenter$$Lambda$4();

    private IdologySettingsPresenter$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologySettingsPresenter.lambda$onShow$3((Boolean) obj);
    }
}
