package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SellConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory implements Factory<AbstractBuySellConfirmationPresenter> {
    private final SellConfirmationPresenterModule module;
    private final Provider<SellConfirmationPresenter> sellConfirmationPresenterProvider;

    public SellConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory(SellConfirmationPresenterModule module, Provider<SellConfirmationPresenter> sellConfirmationPresenterProvider) {
        this.module = module;
        this.sellConfirmationPresenterProvider = sellConfirmationPresenterProvider;
    }

    public AbstractBuySellConfirmationPresenter get() {
        return provideInstance(this.module, this.sellConfirmationPresenterProvider);
    }

    public static AbstractBuySellConfirmationPresenter provideInstance(SellConfirmationPresenterModule module, Provider<SellConfirmationPresenter> sellConfirmationPresenterProvider) {
        return proxyProvidesBuySellConfirmationPresenter(module, (SellConfirmationPresenter) sellConfirmationPresenterProvider.get());
    }

    public static SellConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory create(SellConfirmationPresenterModule module, Provider<SellConfirmationPresenter> sellConfirmationPresenterProvider) {
        return new SellConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory(module, sellConfirmationPresenterProvider);
    }

    public static AbstractBuySellConfirmationPresenter proxyProvidesBuySellConfirmationPresenter(SellConfirmationPresenterModule instance, SellConfirmationPresenter sellConfirmationPresenter) {
        return (AbstractBuySellConfirmationPresenter) Preconditions.checkNotNull(instance.providesBuySellConfirmationPresenter(sellConfirmationPresenter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
