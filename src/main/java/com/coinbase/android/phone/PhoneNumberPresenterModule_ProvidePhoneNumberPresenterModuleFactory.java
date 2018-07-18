package com.coinbase.android.phone;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory implements Factory<PhoneNumberScreen> {
    private final PhoneNumberPresenterModule module;

    public PhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(PhoneNumberPresenterModule module) {
        this.module = module;
    }

    public PhoneNumberScreen get() {
        return provideInstance(this.module);
    }

    public static PhoneNumberScreen provideInstance(PhoneNumberPresenterModule module) {
        return proxyProvidePhoneNumberPresenterModule(module);
    }

    public static PhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory create(PhoneNumberPresenterModule module) {
        return new PhoneNumberPresenterModule_ProvidePhoneNumberPresenterModuleFactory(module);
    }

    public static PhoneNumberScreen proxyProvidePhoneNumberPresenterModule(PhoneNumberPresenterModule instance) {
        return (PhoneNumberScreen) Preconditions.checkNotNull(instance.providePhoneNumberPresenterModule(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
