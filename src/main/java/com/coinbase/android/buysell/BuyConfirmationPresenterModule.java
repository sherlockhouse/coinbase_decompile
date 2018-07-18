package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class BuyConfirmationPresenterModule {
    private final BuyConfirmationScreen mBuyScreen;
    private final AbstractBuySellConfirmationScreen mScreen;

    public BuyConfirmationPresenterModule(AbstractBuySellConfirmationScreen screen, BuyConfirmationScreen buyScreen) {
        this.mBuyScreen = buyScreen;
        this.mScreen = screen;
    }

    @ControllerScope
    public BuyConfirmationScreen provideBuyConfirmationScreen() {
        return this.mBuyScreen;
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
    AbstractBuySellConfirmationPresenter providesBuySellConfirmationPresenter(BuyConfirmationPresenter buyConfirmationPresenter) {
        return buyConfirmationPresenter;
    }
}
