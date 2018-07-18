package com.coinbase.android.ui;

import android.view.View;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.functions.Func0;

public final class BaseViewModule_ProvidesSnackBarRootViewFuncFactory implements Factory<Func0<View>> {
    private final BaseViewModule module;

    public BaseViewModule_ProvidesSnackBarRootViewFuncFactory(BaseViewModule module) {
        this.module = module;
    }

    public Func0<View> get() {
        return provideInstance(this.module);
    }

    public static Func0<View> provideInstance(BaseViewModule module) {
        return proxyProvidesSnackBarRootViewFunc(module);
    }

    public static BaseViewModule_ProvidesSnackBarRootViewFuncFactory create(BaseViewModule module) {
        return new BaseViewModule_ProvidesSnackBarRootViewFuncFactory(module);
    }

    public static Func0<View> proxyProvidesSnackBarRootViewFunc(BaseViewModule instance) {
        return (Func0) Preconditions.checkNotNull(instance.providesSnackBarRootViewFunc(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
