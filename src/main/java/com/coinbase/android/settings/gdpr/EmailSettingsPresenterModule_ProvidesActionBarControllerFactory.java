package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class EmailSettingsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final EmailSettingsPresenterModule module;

    public EmailSettingsPresenterModule_ProvidesActionBarControllerFactory(EmailSettingsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(EmailSettingsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static EmailSettingsPresenterModule_ProvidesActionBarControllerFactory create(EmailSettingsPresenterModule module) {
        return new EmailSettingsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(EmailSettingsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
