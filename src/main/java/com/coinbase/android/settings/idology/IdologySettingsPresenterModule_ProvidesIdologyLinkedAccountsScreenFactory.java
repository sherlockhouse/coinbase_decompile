package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySettingsPresenterModule_ProvidesIdologyLinkedAccountsScreenFactory implements Factory<IdologySettingsScreen> {
    private final IdologySettingsPresenterModule module;

    public IdologySettingsPresenterModule_ProvidesIdologyLinkedAccountsScreenFactory(IdologySettingsPresenterModule module) {
        this.module = module;
    }

    public IdologySettingsScreen get() {
        return provideInstance(this.module);
    }

    public static IdologySettingsScreen provideInstance(IdologySettingsPresenterModule module) {
        return proxyProvidesIdologyLinkedAccountsScreen(module);
    }

    public static IdologySettingsPresenterModule_ProvidesIdologyLinkedAccountsScreenFactory create(IdologySettingsPresenterModule module) {
        return new IdologySettingsPresenterModule_ProvidesIdologyLinkedAccountsScreenFactory(module);
    }

    public static IdologySettingsScreen proxyProvidesIdologyLinkedAccountsScreen(IdologySettingsPresenterModule instance) {
        return (IdologySettingsScreen) Preconditions.checkNotNull(instance.providesIdologyLinkedAccountsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
