package com.coinbase.android.identityverification;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InAppIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory implements Factory<JumioDocumentScanScreen> {
    private final InAppIdentityDocumentScanPresenterModule module;

    public InAppIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory(InAppIdentityDocumentScanPresenterModule module) {
        this.module = module;
    }

    public JumioDocumentScanScreen get() {
        return provideInstance(this.module);
    }

    public static JumioDocumentScanScreen provideInstance(InAppIdentityDocumentScanPresenterModule module) {
        return proxyProvidesJumioDocumentScanScreen(module);
    }

    public static InAppIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory create(InAppIdentityDocumentScanPresenterModule module) {
        return new InAppIdentityDocumentScanPresenterModule_ProvidesJumioDocumentScanScreenFactory(module);
    }

    public static JumioDocumentScanScreen proxyProvidesJumioDocumentScanScreen(InAppIdentityDocumentScanPresenterModule instance) {
        return (JumioDocumentScanScreen) Preconditions.checkNotNull(instance.providesJumioDocumentScanScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
