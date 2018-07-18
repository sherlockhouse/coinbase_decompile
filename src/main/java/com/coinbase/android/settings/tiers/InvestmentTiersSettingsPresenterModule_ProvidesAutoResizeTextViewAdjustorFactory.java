package com.coinbase.android.settings.tiers;

import android.app.Application;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class InvestmentTiersSettingsPresenterModule_ProvidesAutoResizeTextViewAdjustorFactory implements Factory<AutoResizeTextViewAdjustor> {
    private final Provider<Application> appProvider;
    private final InvestmentTiersSettingsPresenterModule module;

    public InvestmentTiersSettingsPresenterModule_ProvidesAutoResizeTextViewAdjustorFactory(InvestmentTiersSettingsPresenterModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public AutoResizeTextViewAdjustor get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static AutoResizeTextViewAdjustor provideInstance(InvestmentTiersSettingsPresenterModule module, Provider<Application> appProvider) {
        return proxyProvidesAutoResizeTextViewAdjustor(module, (Application) appProvider.get());
    }

    public static InvestmentTiersSettingsPresenterModule_ProvidesAutoResizeTextViewAdjustorFactory create(InvestmentTiersSettingsPresenterModule module, Provider<Application> appProvider) {
        return new InvestmentTiersSettingsPresenterModule_ProvidesAutoResizeTextViewAdjustorFactory(module, appProvider);
    }

    public static AutoResizeTextViewAdjustor proxyProvidesAutoResizeTextViewAdjustor(InvestmentTiersSettingsPresenterModule instance, Application app) {
        return (AutoResizeTextViewAdjustor) Preconditions.checkNotNull(instance.providesAutoResizeTextViewAdjustor(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
