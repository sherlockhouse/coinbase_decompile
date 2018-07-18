package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityProcessingPresenterModule_ProvidesScreenFactory implements Factory<UpfrontKycIdentityProcessingScreen> {
    private final UpfrontKycIdentityProcessingPresenterModule module;

    public UpfrontKycIdentityProcessingPresenterModule_ProvidesScreenFactory(UpfrontKycIdentityProcessingPresenterModule module) {
        this.module = module;
    }

    public UpfrontKycIdentityProcessingScreen get() {
        return provideInstance(this.module);
    }

    public static UpfrontKycIdentityProcessingScreen provideInstance(UpfrontKycIdentityProcessingPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static UpfrontKycIdentityProcessingPresenterModule_ProvidesScreenFactory create(UpfrontKycIdentityProcessingPresenterModule module) {
        return new UpfrontKycIdentityProcessingPresenterModule_ProvidesScreenFactory(module);
    }

    public static UpfrontKycIdentityProcessingScreen proxyProvidesScreen(UpfrontKycIdentityProcessingPresenterModule instance) {
        return (UpfrontKycIdentityProcessingScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
