package com.coinbase.android.settings;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountSettingsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final AccountSettingsPresenterModule module;

    public AccountSettingsPresenterModule_ProvidesActionBarControllerFactory(AccountSettingsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AccountSettingsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static AccountSettingsPresenterModule_ProvidesActionBarControllerFactory create(AccountSettingsPresenterModule module) {
        return new AccountSettingsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(AccountSettingsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
