package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class GdprPrivacyRequestPresenterModule_ProvidesRequestRestrictionProcessingViewModelFactory implements Factory<Func0<PrivacyRequestViewModel>> {
    private final Provider<Application> applicationProvider;
    private final Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider;
    private final GdprPrivacyRequestPresenterModule module;

    public GdprPrivacyRequestPresenterModule_ProvidesRequestRestrictionProcessingViewModelFactory(GdprPrivacyRequestPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        this.module = module;
        this.applicationProvider = applicationProvider;
        this.itemClickedConnectorProvider = itemClickedConnectorProvider;
    }

    public Func0<PrivacyRequestViewModel> get() {
        return provideInstance(this.module, this.applicationProvider, this.itemClickedConnectorProvider);
    }

    public static Func0<PrivacyRequestViewModel> provideInstance(GdprPrivacyRequestPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        return proxyProvidesRequestRestrictionProcessingViewModel(module, (Application) applicationProvider.get(), (SettingsPreferenceItemClickedConnector) itemClickedConnectorProvider.get());
    }

    public static GdprPrivacyRequestPresenterModule_ProvidesRequestRestrictionProcessingViewModelFactory create(GdprPrivacyRequestPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        return new GdprPrivacyRequestPresenterModule_ProvidesRequestRestrictionProcessingViewModelFactory(module, applicationProvider, itemClickedConnectorProvider);
    }

    public static Func0<PrivacyRequestViewModel> proxyProvidesRequestRestrictionProcessingViewModel(GdprPrivacyRequestPresenterModule instance, Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return (Func0) Preconditions.checkNotNull(instance.providesRequestRestrictionProcessingViewModel(application, itemClickedConnector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
