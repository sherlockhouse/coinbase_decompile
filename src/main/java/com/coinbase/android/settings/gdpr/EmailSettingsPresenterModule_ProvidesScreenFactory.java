package com.coinbase.android.settings.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class EmailSettingsPresenterModule_ProvidesScreenFactory implements Factory<EmailSettingsScreen> {
    private final EmailSettingsPresenterModule module;

    public EmailSettingsPresenterModule_ProvidesScreenFactory(EmailSettingsPresenterModule module) {
        this.module = module;
    }

    public EmailSettingsScreen get() {
        return provideInstance(this.module);
    }

    public static EmailSettingsScreen provideInstance(EmailSettingsPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static EmailSettingsPresenterModule_ProvidesScreenFactory create(EmailSettingsPresenterModule module) {
        return new EmailSettingsPresenterModule_ProvidesScreenFactory(module);
    }

    public static EmailSettingsScreen proxyProvidesScreen(EmailSettingsPresenterModule instance) {
        return (EmailSettingsScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
