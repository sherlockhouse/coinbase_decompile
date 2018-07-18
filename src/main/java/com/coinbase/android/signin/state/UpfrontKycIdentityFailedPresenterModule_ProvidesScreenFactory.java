package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityFailedPresenterModule_ProvidesScreenFactory implements Factory<UpfrontKycIdentityFailedScreen> {
    private final UpfrontKycIdentityFailedPresenterModule module;

    public UpfrontKycIdentityFailedPresenterModule_ProvidesScreenFactory(UpfrontKycIdentityFailedPresenterModule module) {
        this.module = module;
    }

    public UpfrontKycIdentityFailedScreen get() {
        return provideInstance(this.module);
    }

    public static UpfrontKycIdentityFailedScreen provideInstance(UpfrontKycIdentityFailedPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static UpfrontKycIdentityFailedPresenterModule_ProvidesScreenFactory create(UpfrontKycIdentityFailedPresenterModule module) {
        return new UpfrontKycIdentityFailedPresenterModule_ProvidesScreenFactory(module);
    }

    public static UpfrontKycIdentityFailedScreen proxyProvidesScreen(UpfrontKycIdentityFailedPresenterModule instance) {
        return (UpfrontKycIdentityFailedScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
