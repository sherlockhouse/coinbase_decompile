package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SuccessRouter;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AddBankErrorPresenter {
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private final CompositeSubscription mIAVLoginSubscription = new CompositeSubscription();
    private final Logger mLogger = LoggerFactory.getLogger(AddBankPickerController.class);
    private final Scheduler mMainScheduler;
    private final AddBankErrorScreen mScreen;
    private final SuccessRouter mSuccessRouter;

    @Inject
    public AddBankErrorPresenter(AddBankErrorScreen screen, SuccessRouter successRouter, @MainScheduler Scheduler mainScheduler, BankAccountsUpdatedConnector bankAccountsUpdatedConnector) {
        this.mScreen = screen;
        this.mSuccessRouter = successRouter;
        this.mMainScheduler = mainScheduler;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
    }

    void onShow() {
        this.mIAVLoginSubscription.clear();
        this.mIAVLoginSubscription.add(this.mBankAccountsUpdatedConnector.get().filter(AddBankErrorPresenter$$Lambda$1.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AddBankErrorPresenter$$Lambda$2.lambdaFactory$(this), AddBankErrorPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(AddBankErrorController.class));
    }

    void onHide() {
        this.mIAVLoginSubscription.clear();
    }

    private void gotoSettings() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
        }
    }
}
