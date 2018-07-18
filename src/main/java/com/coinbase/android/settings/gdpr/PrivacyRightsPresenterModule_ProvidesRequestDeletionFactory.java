package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class PrivacyRightsPresenterModule_ProvidesRequestDeletionFactory implements Factory<Func0<SettingsPreferenceItem>> {
    private final Provider<Application> applicationProvider;
    private final Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider;
    private final PrivacyRightsPresenterModule module;

    public PrivacyRightsPresenterModule_ProvidesRequestDeletionFactory(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        this.module = module;
        this.applicationProvider = applicationProvider;
        this.itemClickedConnectorProvider = itemClickedConnectorProvider;
    }

    public Func0<SettingsPreferenceItem> get() {
        return provideInstance(this.module, this.applicationProvider, this.itemClickedConnectorProvider);
    }

    public static Func0<SettingsPreferenceItem> provideInstance(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        return proxyProvidesRequestDeletion(module, (Application) applicationProvider.get(), (SettingsPreferenceItemClickedConnector) itemClickedConnectorProvider.get());
    }

    public static PrivacyRightsPresenterModule_ProvidesRequestDeletionFactory create(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider, Provider<SettingsPreferenceItemClickedConnector> itemClickedConnectorProvider) {
        return new PrivacyRightsPresenterModule_ProvidesRequestDeletionFactory(module, applicationProvider, itemClickedConnectorProvider);
    }

    public static Func0<SettingsPreferenceItem> proxyProvidesRequestDeletion(PrivacyRightsPresenterModule instance, Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return (Func0) Preconditions.checkNotNull(instance.providesRequestDeletion(application, itemClickedConnector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
