package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AddBankPickerPresenter {
    public static final String PASS_THROUGH_TO_PLAID = "passthrough_to_plaid";
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(AddBankPickerController.class);
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final PlaidOnExitConnector mPlaidOnExitConnector;
    private final AddBankRouter mRouter;
    private final AddBankPickerScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public AddBankPickerPresenter(AddBankPickerScreen screen, MixpanelTracking mixpanelTracking, AddBankRouter router, @MainScheduler Scheduler mainScheduler, SnackBarWrapper snackBarWrapper, Application application, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, PlaidOnExitConnector plaidOnExitConnector) {
        this.mScreen = screen;
        this.mMixpanelTracking = mixpanelTracking;
        this.mRouter = router;
        this.mMainScheduler = mainScheduler;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mContext = application;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
        this.mPlaidOnExitConnector = plaidOnExitConnector;
    }

    void gotoPlaid() {
        if (Utils.isConnectedOrConnecting(this.mContext)) {
            this.mRouter.routePlaidWebView(false);
            this.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_TAPPED_PLAID, new String[0]);
            return;
        }
        this.mSnackBarWrapper.show((int) R.string.no_internet);
    }

    void gotoManualBankEntry() {
        this.mRouter.routeBankAccountManualEntry();
        this.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_TAPPED_CDV, new String[0]);
    }

    void onShow() {
        Bundle args = this.mScreen.getController().getArgs();
        if (args.getBoolean(PASS_THROUGH_TO_PLAID, false)) {
            args.putBoolean(PASS_THROUGH_TO_PLAID, false);
            if (Utils.isConnectedOrConnecting(this.mContext)) {
                this.mRouter.routePlaidWebView(true);
                this.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_PASSED_THROUGH_TO_PLAID, new String[0]);
                return;
            }
        }
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_VIEWED, new String[0]);
        this.mSubscription.clear();
        this.mSubscription.add(this.mBankAccountsUpdatedConnector.get().filter(AddBankPickerPresenter$$Lambda$1.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AddBankPickerPresenter$$Lambda$2.lambdaFactory$(this), AddBankPickerPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mPlaidOnExitConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AddBankPickerPresenter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(AddBankPickerController.class));
    }

    static /* synthetic */ void lambda$onShow$3(AddBankPickerPresenter this_, Void aVoid) {
        this_.mRouter.routeAddBankError();
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_PLAID_VIEWED_ERROR, new String[0]);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    private void gotoSettings() {
        this.mRouter.routeSettings();
    }
}
