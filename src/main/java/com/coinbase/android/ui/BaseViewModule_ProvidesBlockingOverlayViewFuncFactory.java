package com.coinbase.android.ui;

import android.widget.RelativeLayout;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.functions.Func0;

public final class BaseViewModule_ProvidesBlockingOverlayViewFuncFactory implements Factory<Func0<RelativeLayout>> {
    private final BaseViewModule module;

    public BaseViewModule_ProvidesBlockingOverlayViewFuncFactory(BaseViewModule module) {
        this.module = module;
    }

    public Func0<RelativeLayout> get() {
        return provideInstance(this.module);
    }

    public static Func0<RelativeLayout> provideInstance(BaseViewModule module) {
        return proxyProvidesBlockingOverlayViewFunc(module);
    }

    public static BaseViewModule_ProvidesBlockingOverlayViewFuncFactory create(BaseViewModule module) {
        return new BaseViewModule_ProvidesBlockingOverlayViewFuncFactory(module);
    }

    public static Func0<RelativeLayout> proxyProvidesBlockingOverlayViewFunc(BaseViewModule instance) {
        return (Func0) Preconditions.checkNotNull(instance.providesBlockingOverlayViewFunc(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
