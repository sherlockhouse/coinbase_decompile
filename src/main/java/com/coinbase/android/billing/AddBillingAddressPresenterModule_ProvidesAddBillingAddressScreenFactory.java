package com.coinbase.android.billing;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddBillingAddressPresenterModule_ProvidesAddBillingAddressScreenFactory implements Factory<AddBillingAddressScreen> {
    private final AddBillingAddressPresenterModule module;

    public AddBillingAddressPresenterModule_ProvidesAddBillingAddressScreenFactory(AddBillingAddressPresenterModule module) {
        this.module = module;
    }

    public AddBillingAddressScreen get() {
        return provideInstance(this.module);
    }

    public static AddBillingAddressScreen provideInstance(AddBillingAddressPresenterModule module) {
        return proxyProvidesAddBillingAddressScreen(module);
    }

    public static AddBillingAddressPresenterModule_ProvidesAddBillingAddressScreenFactory create(AddBillingAddressPresenterModule module) {
        return new AddBillingAddressPresenterModule_ProvidesAddBillingAddressScreenFactory(module);
    }

    public static AddBillingAddressScreen proxyProvidesAddBillingAddressScreen(AddBillingAddressPresenterModule instance) {
        return (AddBillingAddressScreen) Preconditions.checkNotNull(instance.providesAddBillingAddressScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
