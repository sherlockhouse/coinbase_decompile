package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.TransferUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BuySellSuccessPresenter_Factory implements Factory<BuySellSuccessPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BuySellSuccessScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransferUtils> transferUtilsProvider;

    public BuySellSuccessPresenter_Factory(Provider<Application> appProvider, Provider<BuySellSuccessScreen> screenProvider, Provider<TransferUtils> transferUtilsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        this.appProvider = appProvider;
        this.screenProvider = screenProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
    }

    public BuySellSuccessPresenter get() {
        return provideInstance(this.appProvider, this.screenProvider, this.transferUtilsProvider, this.snackBarWrapperProvider);
    }

    public static BuySellSuccessPresenter provideInstance(Provider<Application> appProvider, Provider<BuySellSuccessScreen> screenProvider, Provider<TransferUtils> transferUtilsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new BuySellSuccessPresenter((Application) appProvider.get(), (BuySellSuccessScreen) screenProvider.get(), (TransferUtils) transferUtilsProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get());
    }

    public static BuySellSuccessPresenter_Factory create(Provider<Application> appProvider, Provider<BuySellSuccessScreen> screenProvider, Provider<TransferUtils> transferUtilsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new BuySellSuccessPresenter_Factory(appProvider, screenProvider, transferUtilsProvider, snackBarWrapperProvider);
    }

    public static BuySellSuccessPresenter newBuySellSuccessPresenter(Application app, BuySellSuccessScreen screen, TransferUtils transferUtils, SnackBarWrapper snackBarWrapper) {
        return new BuySellSuccessPresenter(app, screen, transferUtils, snackBarWrapper);
    }
}
