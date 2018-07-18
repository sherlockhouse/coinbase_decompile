package com.coinbase.android;

import android.view.View;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesBottomNavigationViewFactory implements Factory<View> {
    private final BottomNavActivityModule module;
    private final Provider<Func0<View>> viewFuncProvider;

    public BottomNavActivityModule_ProvidesBottomNavigationViewFactory(BottomNavActivityModule module, Provider<Func0<View>> viewFuncProvider) {
        this.module = module;
        this.viewFuncProvider = viewFuncProvider;
    }

    public View get() {
        return provideInstance(this.module, this.viewFuncProvider);
    }

    public static View provideInstance(BottomNavActivityModule module, Provider<Func0<View>> viewFuncProvider) {
        return proxyProvidesBottomNavigationView(module, (Func0) viewFuncProvider.get());
    }

    public static BottomNavActivityModule_ProvidesBottomNavigationViewFactory create(BottomNavActivityModule module, Provider<Func0<View>> viewFuncProvider) {
        return new BottomNavActivityModule_ProvidesBottomNavigationViewFactory(module, viewFuncProvider);
    }

    public static View proxyProvidesBottomNavigationView(BottomNavActivityModule instance, Func0<View> viewFunc) {
        return instance.providesBottomNavigationView(viewFunc);
    }
}
