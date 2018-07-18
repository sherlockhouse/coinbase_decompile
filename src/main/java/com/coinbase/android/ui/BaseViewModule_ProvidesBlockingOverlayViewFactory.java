package com.coinbase.android.ui;

import android.widget.RelativeLayout;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class BaseViewModule_ProvidesBlockingOverlayViewFactory implements Factory<RelativeLayout> {
    private final BaseViewModule module;
    private final Provider<Func0<RelativeLayout>> viewFuncProvider;

    public BaseViewModule_ProvidesBlockingOverlayViewFactory(BaseViewModule module, Provider<Func0<RelativeLayout>> viewFuncProvider) {
        this.module = module;
        this.viewFuncProvider = viewFuncProvider;
    }

    public RelativeLayout get() {
        return provideInstance(this.module, this.viewFuncProvider);
    }

    public static RelativeLayout provideInstance(BaseViewModule module, Provider<Func0<RelativeLayout>> viewFuncProvider) {
        return proxyProvidesBlockingOverlayView(module, (Func0) viewFuncProvider.get());
    }

    public static BaseViewModule_ProvidesBlockingOverlayViewFactory create(BaseViewModule module, Provider<Func0<RelativeLayout>> viewFuncProvider) {
        return new BaseViewModule_ProvidesBlockingOverlayViewFactory(module, viewFuncProvider);
    }

    public static RelativeLayout proxyProvidesBlockingOverlayView(BaseViewModule instance, Func0<RelativeLayout> viewFunc) {
        return (RelativeLayout) Preconditions.checkNotNull(instance.providesBlockingOverlayView(viewFunc), "Cannot return null from a non-@Nullable @Provides method");
    }
}
