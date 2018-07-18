package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignInVerifyPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory implements Factory<SignInVerifyPhoneNumberScreen> {
    private final SignInVerifyPhoneNumberPresenterModule module;

    public SignInVerifyPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(SignInVerifyPhoneNumberPresenterModule module) {
        this.module = module;
    }

    public SignInVerifyPhoneNumberScreen get() {
        return provideInstance(this.module);
    }

    public static SignInVerifyPhoneNumberScreen provideInstance(SignInVerifyPhoneNumberPresenterModule module) {
        return proxyProvidePhoneNumberPresenterModule(module);
    }

    public static SignInVerifyPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory create(SignInVerifyPhoneNumberPresenterModule module) {
        return new SignInVerifyPhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(module);
    }

    public static SignInVerifyPhoneNumberScreen proxyProvidePhoneNumberPresenterModule(SignInVerifyPhoneNumberPresenterModule instance) {
        return (SignInVerifyPhoneNumberScreen) Preconditions.checkNotNull(instance.providePhoneNumberPresenterModule(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
