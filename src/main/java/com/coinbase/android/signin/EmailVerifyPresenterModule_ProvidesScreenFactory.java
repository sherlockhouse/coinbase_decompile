package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class EmailVerifyPresenterModule_ProvidesScreenFactory implements Factory<EmailVerifyScreen> {
    private final EmailVerifyPresenterModule module;

    public EmailVerifyPresenterModule_ProvidesScreenFactory(EmailVerifyPresenterModule module) {
        this.module = module;
    }

    public EmailVerifyScreen get() {
        return provideInstance(this.module);
    }

    public static EmailVerifyScreen provideInstance(EmailVerifyPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static EmailVerifyPresenterModule_ProvidesScreenFactory create(EmailVerifyPresenterModule module) {
        return new EmailVerifyPresenterModule_ProvidesScreenFactory(module);
    }

    public static EmailVerifyScreen proxyProvidesScreen(EmailVerifyPresenterModule instance) {
        return (EmailVerifyScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
