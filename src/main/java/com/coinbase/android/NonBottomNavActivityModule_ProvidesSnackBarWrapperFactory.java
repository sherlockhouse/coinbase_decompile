package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.ui.RootViewWrapper;
import com.coinbase.android.ui.SnackBarWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NonBottomNavActivityModule_ProvidesSnackBarWrapperFactory implements Factory<SnackBarWrapper> {
    private final Provider<Application> appProvider;
    private final Provider<Integer> genericErrorTryAgainResProvider;
    private final Provider<Integer> genericeErrorResProvider;
    private final NonBottomNavActivityModule module;
    private final Provider<RootViewWrapper> rootViewWrapperProvider;

    public NonBottomNavActivityModule_ProvidesSnackBarWrapperFactory(NonBottomNavActivityModule module, Provider<Application> appProvider, Provider<Integer> genericeErrorResProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<RootViewWrapper> rootViewWrapperProvider) {
        this.module = module;
        this.appProvider = appProvider;
        this.genericeErrorResProvider = genericeErrorResProvider;
        this.genericErrorTryAgainResProvider = genericErrorTryAgainResProvider;
        this.rootViewWrapperProvider = rootViewWrapperProvider;
    }

    public SnackBarWrapper get() {
        return provideInstance(this.module, this.appProvider, this.genericeErrorResProvider, this.genericErrorTryAgainResProvider, this.rootViewWrapperProvider);
    }

    public static SnackBarWrapper provideInstance(NonBottomNavActivityModule module, Provider<Application> appProvider, Provider<Integer> genericeErrorResProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<RootViewWrapper> rootViewWrapperProvider) {
        return proxyProvidesSnackBarWrapper(module, (Application) appProvider.get(), ((Integer) genericeErrorResProvider.get()).intValue(), ((Integer) genericErrorTryAgainResProvider.get()).intValue(), (RootViewWrapper) rootViewWrapperProvider.get());
    }

    public static NonBottomNavActivityModule_ProvidesSnackBarWrapperFactory create(NonBottomNavActivityModule module, Provider<Application> appProvider, Provider<Integer> genericeErrorResProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<RootViewWrapper> rootViewWrapperProvider) {
        return new NonBottomNavActivityModule_ProvidesSnackBarWrapperFactory(module, appProvider, genericeErrorResProvider, genericErrorTryAgainResProvider, rootViewWrapperProvider);
    }

    public static SnackBarWrapper proxyProvidesSnackBarWrapper(NonBottomNavActivityModule instance, Application app, int genericeErrorRes, int genericErrorTryAgainRes, RootViewWrapper rootViewWrapper) {
        return (SnackBarWrapper) Preconditions.checkNotNull(instance.providesSnackBarWrapper(app, genericeErrorRes, genericErrorTryAgainRes, rootViewWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
