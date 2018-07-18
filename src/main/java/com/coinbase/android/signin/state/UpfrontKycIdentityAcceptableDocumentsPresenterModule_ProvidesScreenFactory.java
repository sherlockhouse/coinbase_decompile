package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory implements Factory<UpfrontKycIdentityAcceptableDocumentsScreen> {
    private final UpfrontKycIdentityAcceptableDocumentsPresenterModule module;

    public UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        this.module = module;
    }

    public UpfrontKycIdentityAcceptableDocumentsScreen get() {
        return provideInstance(this.module);
    }

    public static UpfrontKycIdentityAcceptableDocumentsScreen provideInstance(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory create(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesScreenFactory(module);
    }

    public static UpfrontKycIdentityAcceptableDocumentsScreen proxyProvidesScreen(UpfrontKycIdentityAcceptableDocumentsPresenterModule instance) {
        return (UpfrontKycIdentityAcceptableDocumentsScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
