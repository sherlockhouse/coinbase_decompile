package com.coinbase.android.deposits;

import dagger.internal.Factory;

public final class SepaDepositViewModule_ProvidesCopyAddressResFactory implements Factory<Integer> {
    private final SepaDepositViewModule module;

    public SepaDepositViewModule_ProvidesCopyAddressResFactory(SepaDepositViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(SepaDepositViewModule module) {
        return Integer.valueOf(proxyProvidesCopyAddressRes(module));
    }

    public static SepaDepositViewModule_ProvidesCopyAddressResFactory create(SepaDepositViewModule module) {
        return new SepaDepositViewModule_ProvidesCopyAddressResFactory(module);
    }

    public static int proxyProvidesCopyAddressRes(SepaDepositViewModule instance) {
        return instance.providesCopyAddressRes();
    }
}
