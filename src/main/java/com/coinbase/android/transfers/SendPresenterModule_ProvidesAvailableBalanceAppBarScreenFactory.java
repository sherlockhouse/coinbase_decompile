package com.coinbase.android.transfers;

import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SendPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory implements Factory<AvailableBalanceAppBarScreen> {
    private final SendPresenterModule module;

    public SendPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(SendPresenterModule module) {
        this.module = module;
    }

    public AvailableBalanceAppBarScreen get() {
        return provideInstance(this.module);
    }

    public static AvailableBalanceAppBarScreen provideInstance(SendPresenterModule module) {
        return proxyProvidesAvailableBalanceAppBarScreen(module);
    }

    public static SendPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory create(SendPresenterModule module) {
        return new SendPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(module);
    }

    public static AvailableBalanceAppBarScreen proxyProvidesAvailableBalanceAppBarScreen(SendPresenterModule instance) {
        return (AvailableBalanceAppBarScreen) Preconditions.checkNotNull(instance.providesAvailableBalanceAppBarScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
