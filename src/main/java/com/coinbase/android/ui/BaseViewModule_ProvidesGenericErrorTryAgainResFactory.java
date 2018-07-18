package com.coinbase.android.ui;

import dagger.internal.Factory;

public final class BaseViewModule_ProvidesGenericErrorTryAgainResFactory implements Factory<Integer> {
    private final BaseViewModule module;

    public BaseViewModule_ProvidesGenericErrorTryAgainResFactory(BaseViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(BaseViewModule module) {
        return Integer.valueOf(proxyProvidesGenericErrorTryAgainRes(module));
    }

    public static BaseViewModule_ProvidesGenericErrorTryAgainResFactory create(BaseViewModule module) {
        return new BaseViewModule_ProvidesGenericErrorTryAgainResFactory(module);
    }

    public static int proxyProvidesGenericErrorTryAgainRes(BaseViewModule instance) {
        return instance.providesGenericErrorTryAgainRes();
    }
}
