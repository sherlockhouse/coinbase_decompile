package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignInPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory implements Factory<SignInPhoneNumberScreen> {
    private final SignInPhoneNumberPresenterModule module;

    public SignInPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(SignInPhoneNumberPresenterModule module) {
        this.module = module;
    }

    public SignInPhoneNumberScreen get() {
        return provideInstance(this.module);
    }

    public static SignInPhoneNumberScreen provideInstance(SignInPhoneNumberPresenterModule module) {
        return proxyProvidePhoneNumberPresenterModule(module);
    }

    public static SignInPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory create(SignInPhoneNumberPresenterModule module) {
        return new SignInPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(module);
    }

    public static SignInPhoneNumberScreen proxyProvidePhoneNumberPresenterModule(SignInPhoneNumberPresenterModule instance) {
        return (SignInPhoneNumberScreen) Preconditions.checkNotNull(instance.providePhoneNumberPresenterModule(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
