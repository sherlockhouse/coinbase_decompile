package com.coinbase.android.wbl;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ExistingUserDialogPresenterModule_ProvidesScreenFactory implements Factory<ExistingUserDialogScreen> {
    private final ExistingUserDialogPresenterModule module;

    public ExistingUserDialogPresenterModule_ProvidesScreenFactory(ExistingUserDialogPresenterModule module) {
        this.module = module;
    }

    public ExistingUserDialogScreen get() {
        return provideInstance(this.module);
    }

    public static ExistingUserDialogScreen provideInstance(ExistingUserDialogPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static ExistingUserDialogPresenterModule_ProvidesScreenFactory create(ExistingUserDialogPresenterModule module) {
        return new ExistingUserDialogPresenterModule_ProvidesScreenFactory(module);
    }

    public static ExistingUserDialogScreen proxyProvidesScreen(ExistingUserDialogPresenterModule instance) {
        return (ExistingUserDialogScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
