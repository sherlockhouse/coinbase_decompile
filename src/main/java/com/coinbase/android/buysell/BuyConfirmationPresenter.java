package com.coinbase.android.buysell;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.Analytics;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.paymentmethods.card.WorldPayPollingWrapper;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.CoinbaseResources.GenericErrorTryAgainMessage;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.transfers.Data;
import com.coinbase.v2.models.transfers.Transfer;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class BuyConfirmationPresenter extends AbstractBuySellConfirmationPresenter {
    private static final String COMMIT_TRANSFER = "commit_transfer";
    private static final String STATUS_CANCELED = "canceled";
    private static final String STATUS_COMPLETED = "completed";
    private static final String STATUS_CREATED = "created";
    private static final String STATUS_STARTED = "started";
    private final Analytics mAnalytics;
    private Bundle mBundle;
    private final Buy3dsVerificationConnector mBuy3dsVerificationConnector;
    private final BuyConfirmationScreen mBuyConfirmationScreen;
    private final BuyRouter mBuyRouter;
    private final BuySellMadeConnector mBuySellMadeConnector;
    private CompositeSubscription mBuySubscription = new CompositeSubscription();
    private Data mCommitTransfer;
    private final Context mContext;
    private final FeatureFlags mFeatureFlags;
    private final int mGenericErrorRes;
    private final LinkedAccountConnector mLinkedAccountConnector;
    private final Logger mLogger = LoggerFactory.getLogger(BuyConfirmationPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private long mPollingStartTime = 0;
    private CompositeSubscription mSubscription = new CompositeSubscription();
    private final WorldPayPollingWrapper mWorldPayPollingWrapper;

    public /* bridge */ /* synthetic */ List getDetailList() {
        return super.getDetailList();
    }

    public /* bridge */ /* synthetic */ void onFeeHelpClicked() {
        super.onFeeHelpClicked();
    }

    @Inject
    public BuyConfirmationPresenter(Application app, LoginManager loginManager, BuyConfirmationScreen screen, AbstractBuySellConfirmationScreen abstractBuySellConfirmationScreen, BuyRouter buyRouter, BuySellMadeConnector buySellMadeConnector, Buy3dsVerificationConnector buy3dsVerificationConnector, LinkedAccountConnector linkedAccountConnector, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, Analytics analytics, FeatureFlags featureFlags, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, SplitTesting splitTesting, @GenericErrorTryAgainMessage int genericErrorTryAgainRes, WorldPayPollingWrapper worldPayPollingWrapper, @MainScheduler Scheduler mainScheduler) {
        super(app, abstractBuySellConfirmationScreen, paymentMethodUtils, transferUtils, mixpanelTracking, snackBarWrapper, splitTesting);
        this.mContext = app;
        this.mLoginManager = loginManager;
        this.mBuyConfirmationScreen = screen;
        this.mBuyRouter = buyRouter;
        this.mBuySellMadeConnector = buySellMadeConnector;
        this.mBuy3dsVerificationConnector = buy3dsVerificationConnector;
        this.mLinkedAccountConnector = linkedAccountConnector;
        this.mMainScheduler = mainScheduler;
        this.mAnalytics = analytics;
        this.mFeatureFlags = featureFlags;
        this.mGenericErrorRes = genericErrorTryAgainRes;
        this.mWorldPayPollingWrapper = worldPayPollingWrapper;
    }

    public String getTitle() {
        return this.mContext.getString(R.string.confirm_cta, new Object[]{getString(R.string.buy_action)});
    }

    public void onConfirmClicked() {
        trackEvent(MixpanelTracking.EVENT_BUY_CONFIRMATION_CLICK, MixpanelTracking.PROPERTY_PAYMENT_METHOD_TYPE, this.mPaymentMethod.getType().toString());
        trackEvent(MixpanelTracking.EVENT_BUY_TAPPED_CONFIRM, "currency", this.mTransferData.getAmount().getCurrency());
        commitBuy();
    }

    protected String getPaymentMethodHeader() {
        return getString(R.string.buy_new_confirm_payment_method_header);
    }

    protected String getAvailableHeader() {
        return getString(R.string.confirm_available_header_buy);
    }

    public void onPaymentMethodClicked() {
        if (this.mPaymentMethodList == null) {
            showGenericError();
        } else {
            this.mBuyRouter.routeToLinkedAccountsPicker(this.mPaymentMethodList, this.mPaymentMethod);
        }
    }

    void onViewCreated(Bundle args) {
        super.onViewCreated(args);
        updateWorldPayInfo();
        this.mBundle = args;
        loadCommitTransferFromBundle();
    }

    void onShow() {
        this.mSubscription.add(this.mBuy3dsVerificationConnector.get().filter(BuyConfirmationPresenter$$Lambda$1.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).first().subscribe(BuyConfirmationPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mLinkedAccountConnector.getPaymentMethodSelectedSubject().filter(BuyConfirmationPresenter$$Lambda$3.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(BuyConfirmationPresenter$$Lambda$4.lambdaFactory$(this), BuyConfirmationPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Pair v) {
        boolean z = (v == null || ((ClassConsumableEvent) v.first).consumeIfNotConsumed(BuyConfirmationPresenter.class)) ? false : true;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$2(Pair v) {
        boolean z = (v == null || ((ClassConsumableEvent) v.first).consumeIfNotConsumed(BuyConfirmationPresenter.class) || v.second == null) ? false : true;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
        this.mBuySubscription.clear();
    }

    private void commitBuy() {
        if (this.mAccount == null || this.mTransferData == null || this.mTransferData.getAmount() == null || this.mTransferData.getAmount().getAmount() == null) {
            this.mBuyRouter.routeToError(null, getString(R.string.an_error_occurred));
            return;
        }
        String dialogMessage = getString(R.string.buying_progress);
        if (this.mTransferData.getRequiresCompletionStep().booleanValue()) {
            dialogMessage = getString(R.string.verifying_progress);
        }
        this.mBuyConfirmationScreen.showProgressDialog(dialogMessage);
        Observable<Pair<Response<Transfer>, Retrofit>> commitBuyBitcoinRx = this.mLoginManager.getClient().commitBuyBitcoinRx(this.mAccount.getId(), this.mTransferData.getId());
        this.mBuyConfirmationScreen.showBuyButtonProgress();
        this.mBuySubscription.clear();
        this.mBuySubscription.add(commitBuyBitcoinRx.observeOn(this.mMainScheduler).subscribe(BuyConfirmationPresenter$$Lambda$6.lambdaFactory$(this), BuyConfirmationPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$commitBuy$5(BuyConfirmationPresenter this_, Pair pair) {
        this_.mBuyConfirmationScreen.hideProgressDialog();
        Response response = pair.first;
        if (response.isSuccessful()) {
            Data transferData = ((Transfer) response.body()).getData();
            if (transferData == null) {
                this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
                this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                return;
            }
            Boolean isFirstBuy = transferData.getIsFirstBuy();
            if (isFirstBuy != null && isFirstBuy.booleanValue()) {
                this_.mAnalytics.trackAttributionEvent("first_buy");
            }
            this_.cacheCommitTransfer(transferData);
            if (this_.mFeatureFlags.hasFeature("tEsTdIsAbLeD")) {
                String buyId = transferData.getId();
                String accountId = this_.mAccount.getId();
                if (TextUtils.isEmpty(buyId) || TextUtils.isEmpty(accountId)) {
                    this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
                    this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                    return;
                }
                this_.mPollingStartTime = System.currentTimeMillis();
                this_.pollForWorldpayStatus(accountId, buyId);
                return;
            } else if (transferData.getRequiresCompletionStep().booleanValue()) {
                this_.mBuyRouter.routeSecure3D(transferData);
                return;
            } else {
                this_.routeToSuccess(((Transfer) response.body()).getData());
                return;
            }
        }
        this_.mBuyRouter.routeToError(null, Utils.getErrorMessage(response));
        this_.mBuyConfirmationScreen.hideBuyButtonProgress();
    }

    static /* synthetic */ void lambda$commitBuy$6(BuyConfirmationPresenter this_, Throwable t) {
        this_.mBuyConfirmationScreen.hideProgressDialog();
        this_.mBuyRouter.routeToError(null, Utils.getMessage(this_.mContext, t));
        this_.mBuyConfirmationScreen.hideBuyButtonProgress();
    }

    private void pollForWorldpayStatus(String accountId, String buyId) {
        this.mBuySubscription.add(this.mLoginManager.getClient().getCommitBuyStatusRx(accountId, buyId).observeOn(this.mMainScheduler).subscribe(BuyConfirmationPresenter$$Lambda$8.lambdaFactory$(this, accountId, buyId), BuyConfirmationPresenter$$Lambda$9.lambdaFactory$(this)));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$pollForWorldpayStatus$9(BuyConfirmationPresenter this_, String accountId, String buyId, Pair pair) {
        Object obj = null;
        Response response = pair.first;
        if (response.isSuccessful()) {
            Data transfer = ((Transfer) response.body()).getData();
            if (transfer != null) {
                String status = transfer.getStatus();
                if (!TextUtils.isEmpty(status)) {
                    boolean requiresCompletion;
                    if (transfer.getRequiresCompletionStep() == null || !transfer.getRequiresCompletionStep().booleanValue()) {
                        requiresCompletion = false;
                    } else {
                        requiresCompletion = true;
                    }
                    switch (status.hashCode()) {
                        case -1897185151:
                            if (status.equals(STATUS_STARTED)) {
                                int i = 1;
                                break;
                            }
                        case -1402931637:
                            if (status.equals("completed")) {
                                obj = 2;
                                break;
                            }
                        case -123173735:
                            if (status.equals(STATUS_CANCELED)) {
                                obj = 3;
                                break;
                            }
                        case 1028554472:
                            if (status.equals(STATUS_CREATED)) {
                                break;
                            }
                        default:
                            obj = -1;
                            break;
                    }
                    switch (obj) {
                        case null:
                        case 1:
                            if (requiresCompletion) {
                                this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                                this_.mBuyRouter.routeSecure3D(transfer);
                                return;
                            } else if (System.currentTimeMillis() - this_.mPollingStartTime < this_.mWorldPayPollingWrapper.getPollMaxTime()) {
                                this_.mBuySubscription.add(Observable.just(null).delay(this_.mWorldPayPollingWrapper.getPollDelay(), TimeUnit.SECONDS).subscribe(BuyConfirmationPresenter$$Lambda$14.lambdaFactory$(this_, accountId, buyId), BuyConfirmationPresenter$$Lambda$15.lambdaFactory$(this_, accountId, buyId)));
                                return;
                            } else {
                                this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
                                this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                                return;
                            }
                        case 2:
                            this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                            this_.routeToSuccess(this_.mCommitTransfer);
                            return;
                        default:
                            this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                            this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
                            return;
                    }
                }
            }
            this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
            this_.mBuyConfirmationScreen.hideBuyButtonProgress();
            return;
        }
        this_.mBuyRouter.routeToError(null, Utils.getErrorMessage(response));
        this_.mBuyConfirmationScreen.hideBuyButtonProgress();
    }

    static /* synthetic */ void lambda$pollForWorldpayStatus$10(BuyConfirmationPresenter this_, Throwable t) {
        this_.mBuyConfirmationScreen.hideProgressDialog();
        this_.mBuyRouter.routeToError(null, Utils.getMessage(this_.mContext, t));
        this_.mBuyConfirmationScreen.hideBuyButtonProgress();
    }

    private void completeBuyAfterSecure3D(int resultCode, Intent data) {
        if (resultCode != -1) {
            trackEvent(MixpanelTracking.EVENT_3DS_VERIFICATION_DISMISSED, new String[0]);
        } else if (this.mCommitTransfer == null || TextUtils.isEmpty(this.mCommitTransfer.getId()) || this.mAccount == null) {
            this.mBuyRouter.routeToError(null, getString(R.string.error_occurred_try_again));
        } else {
            trackEvent(MixpanelTracking.EVENT_3DS_VERIFICATION_COMPLETED, new String[0]);
            String redirect = data.getStringExtra(CardBuyVerifyActivity.REDIRECT);
            HashMap<String, Object> params = new HashMap();
            HashMap<String, Object> pares = new HashMap();
            pares.put(ApiConstants.PARES, redirect);
            params.put(ApiConstants.REDIRECT_PARAMS, pares);
            this.mBuyConfirmationScreen.showProgressDialog(getString(R.string.buying_progress));
            Observable<Pair<Response<Transfer>, Retrofit>> completeBuyObservable = this.mLoginManager.getClient().completeBuyRx(this.mAccount.getId(), this.mCommitTransfer.getId(), params);
            this.mBuyConfirmationScreen.showBuyButtonProgress();
            this.mSubscription.add(completeBuyObservable.observeOn(this.mMainScheduler).subscribe(BuyConfirmationPresenter$$Lambda$10.lambdaFactory$(this), BuyConfirmationPresenter$$Lambda$11.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$completeBuyAfterSecure3D$11(BuyConfirmationPresenter this_, Pair pair) {
        this_.mBuyConfirmationScreen.hideProgressDialog();
        if (!pair.first.isSuccessful()) {
            this_.mBuyRouter.routeToError(this_.getString(R.string.buy_complete_failed_error_title), this_.getString(R.string.buy_completed_failed_error_message));
            this_.mBuyConfirmationScreen.hideBuyButtonProgress();
        } else if (this_.mFeatureFlags.hasFeature("tEsTdIsAbLeD")) {
            String accountId = this_.mAccount.getId();
            if (TextUtils.isEmpty(accountId)) {
                this_.mBuyRouter.routeToError(null, this_.mContext.getString(this_.mGenericErrorRes));
                this_.mBuyConfirmationScreen.hideBuyButtonProgress();
                return;
            }
            this_.mPollingStartTime = System.currentTimeMillis();
            this_.pollForWorldpayStatus(accountId, this_.mCommitTransfer.getId());
        } else {
            this_.trackEvent(MixpanelTracking.EVENT_3DS_BUY_COMPLETED, new String[0]);
            this_.routeToSuccess(this_.mCommitTransfer);
        }
    }

    static /* synthetic */ void lambda$completeBuyAfterSecure3D$12(BuyConfirmationPresenter this_, Throwable t) {
        this_.mBuyConfirmationScreen.hideProgressDialog();
        this_.mBuyRouter.routeToError(this_.getString(R.string.buy_complete_failed_error_title), this_.getString(R.string.buy_completed_failed_error_message));
        this_.mBuyConfirmationScreen.hideBuyButtonProgress();
    }

    private void performQuote(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        this.mBuyConfirmationScreen.showProgressBar();
        Observable<Pair<Response<Transfer>, Retrofit>> buyBitcoinObservable = this.mLoginManager.getClient().buyBitcoinRx(this.mAccount.getId(), getBuyParams(paymentMethod));
        this.mBuySubscription.clear();
        clearCommitTransferCache();
        this.mBuySubscription.add(buyBitcoinObservable.observeOn(this.mMainScheduler).subscribe(BuyConfirmationPresenter$$Lambda$12.lambdaFactory$(this, paymentMethod), BuyConfirmationPresenter$$Lambda$13.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$performQuote$13(BuyConfirmationPresenter this_, com.coinbase.v2.models.paymentMethods.Data paymentMethod, Pair pair) {
        Response<Transfer> response = pair.first;
        if (response.isSuccessful()) {
            this_.setSelectedPaymentMethod(paymentMethod);
            this_.updateTransfer((Transfer) response.body());
            this_.updateWorldPayInfo();
            this_.mBuyConfirmationScreen.hideProgressBar();
            return;
        }
        this_.mBuyConfirmationScreen.hideProgressBar();
        this_.mBuyRouter.routeToError(null, this_.getError(response.errorBody()));
    }

    static /* synthetic */ void lambda$performQuote$14(BuyConfirmationPresenter this_, Throwable t) {
        this_.mBuyConfirmationScreen.hideProgressBar();
        this_.mBuyRouter.routeToError(null, Utils.getMessage(this_.mContext, t));
    }

    private HashMap<String, Object> getBuyParams(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        HashMap<String, Object> params = new HashMap();
        params.put("commit", Boolean.valueOf(false));
        params.put("payment_method", paymentMethod.getId());
        params.put(com.coinbase.ApiConstants.TOTAL, this.mTransferData.getTotal().getAmount());
        params.put("currency", this.mTransferData.getTotal().getCurrency());
        return params;
    }

    private void routeToSuccess(Data transferData) {
        this.mBuyRouter.routeToBuySuccess(transferData, this.mAccount);
        this.mBuySellMadeConnector.get().onNext(this.mAccount);
    }

    private void updateWorldPayInfo() {
        if (PaymentMethodUtils.shouldShowWorldPayInfo(this.mPaymentMethod)) {
            this.mBuyConfirmationScreen.showWorldPayInfo();
        } else {
            this.mBuyConfirmationScreen.hideWorldPayInfo();
        }
    }

    private void cacheCommitTransfer(Data commitTransfer) {
        this.mCommitTransfer = commitTransfer;
        this.mBundle.putString(COMMIT_TRANSFER, new Gson().toJson(this.mCommitTransfer));
    }

    private void loadCommitTransferFromBundle() {
        if (this.mBundle.containsKey(COMMIT_TRANSFER)) {
            this.mCommitTransfer = (Data) new Gson().fromJson(this.mBundle.getString(COMMIT_TRANSFER), Data.class);
        }
    }

    private void clearCommitTransferCache() {
        this.mCommitTransfer = null;
        this.mBundle.remove(COMMIT_TRANSFER);
    }
}
