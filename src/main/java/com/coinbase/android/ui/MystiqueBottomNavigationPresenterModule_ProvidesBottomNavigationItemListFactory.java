package com.coinbase.android.ui;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;

public final class MystiqueBottomNavigationPresenterModule_ProvidesBottomNavigationItemListFactory implements Factory<List<NavigationItem>> {
    private final Provider<Application> appProvider;
    private final MystiqueBottomNavigationPresenterModule module;

    public MystiqueBottomNavigationPresenterModule_ProvidesBottomNavigationItemListFactory(MystiqueBottomNavigationPresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public List<NavigationItem> get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static List<NavigationItem> provideInstance(MystiqueBottomNavigationPresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesBottomNavigationItemList(module, (Application) appProvider.get());
    }

    public static MystiqueBottomNavigationPresenterModule_ProvidesBottomNavigationItemListFactory create(MystiqueBottomNavigationPresenterModule module, Provider<Application> appProvider) {
        return new MystiqueBottomNavigationPresenterModule_ProvidesBottomNavigationItemListFactory(module, appProvider);
    }

    public static List<NavigationItem> proxyProvidesBottomNavigationItemList(MystiqueBottomNavigationPresenterModule instance, Application app) {
        return (List) Preconditions.checkNotNull(instance.providesBottomNavigationItemList(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
