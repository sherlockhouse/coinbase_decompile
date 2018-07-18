package com.coinbase.android;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.coinbase.android.ui.RootViewWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesRootViewWrapperFactory implements Factory<RootViewWrapper> {
    private final Provider<Func0<RelativeLayout>> blockingOverlayViewProvider;
    private final Provider<Func0<View>> bottomNavigationViewProvider;
    private final Provider<Func0<ViewGroup>> modalViewProvider;
    private final BottomNavActivityModule module;
    private final Provider<Func0<View>> rootViewProvider;

    public BottomNavActivityModule_ProvidesRootViewWrapperFactory(BottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<View>> bottomNavigationViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider, Provider<Func0<ViewGroup>> modalViewProvider) {
        this.module = module;
        this.rootViewProvider = rootViewProvider;
        this.bottomNavigationViewProvider = bottomNavigationViewProvider;
        this.blockingOverlayViewProvider = blockingOverlayViewProvider;
        this.modalViewProvider = modalViewProvider;
    }

    public RootViewWrapper get() {
        return provideInstance(this.module, this.rootViewProvider, this.bottomNavigationViewProvider, this.blockingOverlayViewProvider, this.modalViewProvider);
    }

    public static RootViewWrapper provideInstance(BottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<View>> bottomNavigationViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider, Provider<Func0<ViewGroup>> modalViewProvider) {
        return proxyProvidesRootViewWrapper(module, (Func0) rootViewProvider.get(), (Func0) bottomNavigationViewProvider.get(), (Func0) blockingOverlayViewProvider.get(), (Func0) modalViewProvider.get());
    }

    public static BottomNavActivityModule_ProvidesRootViewWrapperFactory create(BottomNavActivityModule module, Provider<Func0<View>> rootViewProvider, Provider<Func0<View>> bottomNavigationViewProvider, Provider<Func0<RelativeLayout>> blockingOverlayViewProvider, Provider<Func0<ViewGroup>> modalViewProvider) {
        return new BottomNavActivityModule_ProvidesRootViewWrapperFactory(module, rootViewProvider, bottomNavigationViewProvider, blockingOverlayViewProvider, modalViewProvider);
    }

    public static RootViewWrapper proxyProvidesRootViewWrapper(BottomNavActivityModule instance, Func0<View> rootView, Func0<View> bottomNavigationView, Func0<RelativeLayout> blockingOverlayView, Func0<ViewGroup> modalView) {
        return (RootViewWrapper) Preconditions.checkNotNull(instance.providesRootViewWrapper(rootView, bottomNavigationView, blockingOverlayView, modalView), "Cannot return null from a non-@Nullable @Provides method");
    }
}
