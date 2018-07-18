package com.coinbase.android;

import android.view.View;
import android.widget.RelativeLayout;
import com.coinbase.android.ui.RootViewWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class NonBottomNavActivityModule_ProvidesRootViewWrapperFactory implements Factory<RootViewWrapper> {
    private final Provider<Func0<RelativeLayout>> blockingOverlayViewProvider;
    private final NonBottomNavActivityModule module;
    private final Provider<Func0<View>> rootViewProvider;

    public NonBottomNavActivityModule_ProvidesRootViewWrapperFactory(NonBottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider) {
        this.module = module;
        this.rootViewProvider = rootViewProvider;
        this.blockingOverlayViewProvider = blockingOverlayViewProvider;
    }

    public RootViewWrapper get() {
        return provideInstance(this.module, this.rootViewProvider, this.blockingOverlayViewProvider);
    }

    public static RootViewWrapper provideInstance(NonBottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider) {
        return proxyProvidesRootViewWrapper(module, (Func0) rootViewProvider.get(), (Func0) blockingOverlayViewProvider.get());
    }

    public static NonBottomNavActivityModule_ProvidesRootViewWrapperFactory create(NonBottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider) {
        return new NonBottomNavActivityModule_ProvidesRootViewWrapperFactory(module, rootViewProvider, blockingOverlayViewProvider);
    }

    public static RootViewWrapper proxyProvidesRootViewWrapper(NonBottomNavActivityModule instance, Func0<View> rootView, Func0<RelativeLayout> blockingOverlayView) {
        return (RootViewWrapper) Preconditions.checkNotNull(instance.providesRootViewWrapper(rootView, blockingOverlayView), "Cannot return null from a non-@Nullable @Provides method");
    }
}
