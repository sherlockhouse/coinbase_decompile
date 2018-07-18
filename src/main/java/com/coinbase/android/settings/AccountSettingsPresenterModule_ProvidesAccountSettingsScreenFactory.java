package com.coinbase.android.settings;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountSettingsPresenterModule_ProvidesAccountSettingsScreenFactory implements Factory<AccountSettingsScreen> {
    private final AccountSettingsPresenterModule module;

    public AccountSettingsPresenterModule_ProvidesAccountSettingsScreenFactory(AccountSettingsPresenterModule module) {
        this.module = module;
    }

    public AccountSettingsScreen get() {
        return provideInstance(this.module);
    }

    public static AccountSettingsScreen provideInstance(AccountSettingsPresenterModule module) {
        return proxyProvidesAccountSettingsScreen(module);
    }

    public static AccountSettingsPresenterModule_ProvidesAccountSettingsScreenFactory create(AccountSettingsPresenterModule module) {
        return new AccountSettingsPresenterModule_ProvidesAccountSettingsScreenFactory(module);
    }

    public static AccountSettingsScreen proxyProvidesAccountSettingsScreen(AccountSettingsPresenterModule instance) {
        return (AccountSettingsScreen) Preconditions.checkNotNull(instance.providesAccountSettingsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
