package com.coinbase.android;

import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseApplicationModule_ProvidesGetPaymentMethodsRxFactory implements Factory<GetPaymentMethodsTaskRx> {
    private final Provider<LoginManager> loginManagerProvider;
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesGetPaymentMethodsRxFactory(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider) {
        this.module = module;
        this.loginManagerProvider = loginManagerProvider;
    }

    public GetPaymentMethodsTaskRx get() {
        return provideInstance(this.module, this.loginManagerProvider);
    }

    public static GetPaymentMethodsTaskRx provideInstance(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider) {
        return proxyProvidesGetPaymentMethodsRx(module, (LoginManager) loginManagerProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesGetPaymentMethodsRxFactory create(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider) {
        return new CoinbaseApplicationModule_ProvidesGetPaymentMethodsRxFactory(module, loginManagerProvider);
    }

    public static GetPaymentMethodsTaskRx proxyProvidesGetPaymentMethodsRx(CoinbaseApplicationModule instance, LoginManager loginManager) {
        return (GetPaymentMethodsTaskRx) Preconditions.checkNotNull(instance.providesGetPaymentMethodsRx(loginManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
