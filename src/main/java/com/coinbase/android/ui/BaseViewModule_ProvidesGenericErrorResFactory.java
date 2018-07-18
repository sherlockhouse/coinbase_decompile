package com.coinbase.android.ui;

import dagger.internal.Factory;

public final class BaseViewModule_ProvidesGenericErrorResFactory implements Factory<Integer> {
    private final BaseViewModule module;

    public BaseViewModule_ProvidesGenericErrorResFactory(BaseViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(BaseViewModule module) {
        return Integer.valueOf(proxyProvidesGenericErrorRes(module));
    }

    public static BaseViewModule_ProvidesGenericErrorResFactory create(BaseViewModule module) {
        return new BaseViewModule_ProvidesGenericErrorResFactory(module);
    }

    public static int proxyProvidesGenericErrorRes(BaseViewModule instance) {
        return instance.providesGenericErrorRes();
    }
}
