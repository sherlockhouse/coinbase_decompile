package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class BuyConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory implements Factory<AbstractBuySellConfirmationPresenter> {
    private final Provider<BuyConfirmationPresenter> buyConfirmationPresenterProvider;
    private final BuyConfirmationPresenterModule module;

    public BuyConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory(BuyConfirmationPresenterModule module, Provider<BuyConfirmationPresenter> buyConfirmationPresenterProvider) {
        this.module = module;
        this.buyConfirmationPresenterProvider = buyConfirmationPresenterProvider;
    }

    public AbstractBuySellConfirmationPresenter get() {
        return provideInstance(this.module, this.buyConfirmationPresenterProvider);
    }

    public static AbstractBuySellConfirmationPresenter provideInstance(BuyConfirmationPresenterModule module, Provider<BuyConfirmationPresenter> buyConfirmationPresenterProvider) {
        return proxyProvidesBuySellConfirmationPresenter(module, (BuyConfirmationPresenter) buyConfirmationPresenterProvider.get());
    }

    public static BuyConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory create(BuyConfirmationPresenterModule module, Provider<BuyConfirmationPresenter> buyConfirmationPresenterProvider) {
        return new BuyConfirmationPresenterModule_ProvidesBuySellConfirmationPresenterFactory(module, buyConfirmationPresenterProvider);
    }

    public static AbstractBuySellConfirmationPresenter proxyProvidesBuySellConfirmationPresenter(BuyConfirmationPresenterModule instance, BuyConfirmationPresenter buyConfirmationPresenter) {
        return (AbstractBuySellConfirmationPresenter) Preconditions.checkNotNull(instance.providesBuySellConfirmationPresenter(buyConfirmationPresenter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
