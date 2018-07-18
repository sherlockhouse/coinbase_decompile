package com.coinbase.android.identityverification;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InAppIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory implements Factory<IdentityAcceptableDocumentsScreen> {
    private final InAppIdentityAcceptableDocumentsPresenterModule module;

    public InAppIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory(InAppIdentityAcceptableDocumentsPresenterModule module) {
        this.module = module;
    }

    public IdentityAcceptableDocumentsScreen get() {
        return provideInstance(this.module);
    }

    public static IdentityAcceptableDocumentsScreen provideInstance(InAppIdentityAcceptableDocumentsPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static InAppIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory create(InAppIdentityAcceptableDocumentsPresenterModule module) {
        return new InAppIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory(module);
    }

    public static IdentityAcceptableDocumentsScreen proxyProvidesScreen(InAppIdentityAcceptableDocumentsPresenterModule instance) {
        return (IdentityAcceptableDocumentsScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
