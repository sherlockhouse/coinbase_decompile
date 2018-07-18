package com.coinbase.android.billing;

import dagger.internal.Factory;

public final class AddBillingAddressViewModule_ProvidesCheckFieldsErrorResFactory implements Factory<Integer> {
    private final AddBillingAddressViewModule module;

    public AddBillingAddressViewModule_ProvidesCheckFieldsErrorResFactory(AddBillingAddressViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(AddBillingAddressViewModule module) {
        return Integer.valueOf(proxyProvidesCheckFieldsErrorRes(module));
    }

    public static AddBillingAddressViewModule_ProvidesCheckFieldsErrorResFactory create(AddBillingAddressViewModule module) {
        return new AddBillingAddressViewModule_ProvidesCheckFieldsErrorResFactory(module);
    }

    public static int proxyProvidesCheckFieldsErrorRes(AddBillingAddressViewModule instance) {
        return instance.providesCheckFieldsErrorRes();
    }
}
