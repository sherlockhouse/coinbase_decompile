package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory implements Factory<UpfrontKycIdentityDocumentScanScreen> {
    private final UpfrontKycIdentityDocumentScanPresenterModule module;

    public UpfrontKycIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory(UpfrontKycIdentityDocumentScanPresenterModule module) {
        this.module = module;
    }

    public UpfrontKycIdentityDocumentScanScreen get() {
        return provideInstance(this.module);
    }

    public static UpfrontKycIdentityDocumentScanScreen provideInstance(UpfrontKycIdentityDocumentScanPresenterModule module) {
        return proxyProvidesJumioDocumentScanScreen(module);
    }

    public static UpfrontKycIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory create(UpfrontKycIdentityDocumentScanPresenterModule module) {
        return new UpfrontKycIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory(module);
    }

    public static UpfrontKycIdentityDocumentScanScreen proxyProvidesJumioDocumentScanScreen(UpfrontKycIdentityDocumentScanPresenterModule instance) {
        return (UpfrontKycIdentityDocumentScanScreen) Preconditions.checkNotNull(instance.providesJumioDocumentScanScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
