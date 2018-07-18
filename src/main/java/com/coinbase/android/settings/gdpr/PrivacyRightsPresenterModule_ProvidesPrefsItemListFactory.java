package com.coinbase.android.settings.gdpr;

import com.coinbase.android.settings.SettingsPreferenceItem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import rx.functions.Func0;

public final class PrivacyRightsPresenterModule_ProvidesPrefsItemListFactory implements Factory<List<Func0<SettingsPreferenceItem>>> {
    private final PrivacyRightsPresenterModule module;
    private final Provider<Map<Integer, Func0<SettingsPreferenceItem>>> prefsItemMapProvider;

    public PrivacyRightsPresenterModule_ProvidesPrefsItemListFactory(PrivacyRightsPresenterModule module, Provider<Map<Integer, Func0<SettingsPreferenceItem>>> prefsItemMapProvider) {
        this.module = module;
        this.prefsItemMapProvider = prefsItemMapProvider;
    }

    public List<Func0<SettingsPreferenceItem>> get() {
        return provideInstance(this.module, this.prefsItemMapProvider);
    }

    public static List<Func0<SettingsPreferenceItem>> provideInstance(PrivacyRightsPresenterModule module, Provider<Map<Integer, Func0<SettingsPreferenceItem>>> prefsItemMapProvider) {
        return proxyProvidesPrefsItemList(module, (Map) prefsItemMapProvider.get());
    }

    public static PrivacyRightsPresenterModule_ProvidesPrefsItemListFactory create(PrivacyRightsPresenterModule module, Provider<Map<Integer, Func0<SettingsPreferenceItem>>> prefsItemMapProvider) {
        return new PrivacyRightsPresenterModule_ProvidesPrefsItemListFactory(module, prefsItemMapProvider);
    }

    public static List<Func0<SettingsPreferenceItem>> proxyProvidesPrefsItemList(PrivacyRightsPresenterModule instance, Map<Integer, Func0<SettingsPreferenceItem>> prefsItemMap) {
        return (List) Preconditions.checkNotNull(instance.providesPrefsItemList(prefsItemMap), "Cannot return null from a non-@Nullable @Provides method");
    }
}
