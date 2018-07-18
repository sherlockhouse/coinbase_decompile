package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class SellConfirmationPresenterModule {
    private final AbstractBuySellConfirmationScreen mScreen;
    private final SellConfirmationScreen mSellScreen;

    public SellConfirmationPresenterModule(AbstractBuySellConfirmationScreen screen, SellConfirmationScreen sellScreen) {
        this.mSellScreen = sellScreen;
        this.mScreen = screen;
    }

    @ControllerScope
    public SellConfirmationScreen provideSellConfirmationScreen() {
        return this.mSellScreen;
    }

    @ControllerScope
    public AbstractBuySellConfirmationScreen provideBuySellConfirmationScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController provideActionBarController() {
        return this.mScreen.getController();
    }

    @ControllerScope
    AbstractBuySellConfirmationPresenter providesBuySellConfirmationPresenter(SellConfirmationPresenter sellConfirmationPresenter) {
        return sellConfirmationPresenter;
    }
}
