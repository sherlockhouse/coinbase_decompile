package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class PrivacyRightsPresenterModule_ProvidesPrivacyRightsHeaderFactory implements Factory<Func0<SettingsPreferenceItem>> {
    private final Provider<Application> applicationProvider;
    private final PrivacyRightsPresenterModule module;

    public PrivacyRightsPresenterModule_ProvidesPrivacyRightsHeaderFactory(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider) {
        this.module = module;
        this.applicationProvider = applicationProvider;
    }

    public Func0<SettingsPreferenceItem> get() {
        return provideInstance(this.module, this.applicationProvider);
    }

    public static Func0<SettingsPreferenceItem> provideInstance(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider) {
        return proxyProvidesPrivacyRightsHeader(module, (Application) applicationProvider.get());
    }

    public static PrivacyRightsPresenterModule_ProvidesPrivacyRightsHeaderFactory create(PrivacyRightsPresenterModule module, Provider<Application> applicationProvider) {
        return new PrivacyRightsPresenterModule_ProvidesPrivacyRightsHeaderFactory(module, applicationProvider);
    }

    public static Func0<SettingsPreferenceItem> proxyProvidesPrivacyRightsHeader(PrivacyRightsPresenterModule instance, Application application) {
        return (Func0) Preconditions.checkNotNull(instance.providesPrivacyRightsHeader(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
