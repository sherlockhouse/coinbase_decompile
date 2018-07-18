package com.coinbase.android.buysell;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.coinbase.ApiConstants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.transfers.Transfer;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class SellConfirmationPresenter extends AbstractBuySellConfirmationPresenter {
    private final BuySellMadeConnector mBuySellMadeConnector;
    private final Context mContext;
    private final LinkedAccountConnector mLinkedAccountConnector;
    private final Logger mLogger = LoggerFactory.getLogger(SellConfirmationPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final SellConfirmationScreen mSellConfirmationScreen;
    private final SellRouter mSellRouter;
    private CompositeSubscription mSellSubscription = new CompositeSubscription();
    private CompositeSubscription mSubscription = new CompositeSubscription();

    public /* bridge */ /* synthetic */ List getDetailList() {
        return super.getDetailList();
    }

    public /* bridge */ /* synthetic */ void onFeeHelpClicked() {
        super.onFeeHelpClicked();
    }

    @Inject
    SellConfirmationPresenter(Application app, LoginManager loginManager, SellConfirmationScreen screen, AbstractBuySellConfirmationScreen abstractBuySellConfirmationScreen, SellRouter sellRouter, BuySellMadeConnector buySellMadeConnector, LinkedAccountConnector linkedAccountConnector, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, SplitTesting splitTesting, @MainScheduler Scheduler mainScheduler) {
        super(app, abstractBuySellConfirmationScreen, paymentMethodUtils, transferUtils, mixpanelTracking, snackBarWrapper, splitTesting);
        this.mContext = app;
        this.mLoginManager = loginManager;
        this.mSellConfirmationScreen = screen;
        this.mSellRouter = sellRouter;
        this.mBuySellMadeConnector = buySellMadeConnector;
        this.mLinkedAccountConnector = linkedAccountConnector;
        this.mMainScheduler = mainScheduler;
    }

    public String getTitle() {
        return this.mContext.getString(R.string.confirm_cta, new Object[]{getString(R.string.sell_action)});
    }

    public void onConfirmClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_CONFIRMATION_CLICK, MixpanelTracking.PROPERTY_PAYMENT_METHOD_TYPE, this.mPaymentMethod.getType().toString());
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_CONFIRM, "currency", this.mTransferData.getAmount().getCurrency());
        commitSell();
    }

    protected String getPaymentMethodHeader() {
        return getString(R.string.sell_new_confirm_payment_method_header);
    }

    public void onPaymentMethodClicked() {
        if (this.mPaymentMethodList == null) {
            showGenericError();
        } else {
            this.mSellRouter.routeToLinkedAccountsPicker(this.mPaymentMethodList, this.mPaymentMethod);
        }
    }

    void onShow() {
        this.mSubscription.add(this.mLinkedAccountConnector.getPaymentMethodSelectedSubject().filter(SellConfirmationPresenter$$Lambda$1.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellConfirmationPresenter$$Lambda$2.lambdaFactory$(this), SellConfirmationPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Pair v) {
        boolean z = (v == null || ((ClassConsumableEvent) v.first).consumeIfNotConsumed(SellConfirmationPresenter.class) || v.second == null) ? false : true;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
        this.mSellSubscription.clear();
    }

    private void commitSell() {
        if (this.mAccount == null || this.mTransferData == null || this.mTransferData.getAmount() == null || this.mTransferData.getAmount().getAmount() == null) {
            this.mSellRouter.routeToError(null, getString(R.string.an_error_occurred));
            return;
        }
        this.mSellConfirmationScreen.showProgressDialog(getString(R.string.selling_progress));
        Observable<Pair<Response<Transfer>, Retrofit>> commitSellBitcoinRx = this.mLoginManager.getClient().commitSellBitcoinRx(this.mAccount.getId(), this.mTransferData.getId());
        this.mSellConfirmationScreen.showSellButtonProgress();
        this.mSellSubscription.clear();
        this.mSellSubscription.add(commitSellBitcoinRx.observeOn(this.mMainScheduler).subscribe(SellConfirmationPresenter$$Lambda$4.lambdaFactory$(this), SellConfirmationPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$commitSell$3(SellConfirmationPresenter this_, Pair pair) {
        this_.mSellConfirmationScreen.hideProgressDialog();
        Response response = pair.first;
        if (response.isSuccessful()) {
            this_.routeToSuccess(((Transfer) response.body()).getData());
            return;
        }
        this_.mSellRouter.routeToError(null, Utils.getErrorMessage(response));
        this_.mSellConfirmationScreen.hideSellButtonProgress();
    }

    static /* synthetic */ void lambda$commitSell$4(SellConfirmationPresenter this_, Throwable t) {
        this_.mSellConfirmationScreen.hideProgressDialog();
        this_.mSellRouter.routeToError(null, Utils.getMessage(this_.mContext, t));
        this_.mSellConfirmationScreen.hideSellButtonProgress();
    }

    private void performQuote(Data paymentMethod) {
        this.mSellConfirmationScreen.showProgressBar();
        Observable<Pair<Response<Transfer>, Retrofit>> sellBitcoinObservable = this.mLoginManager.getClient().sellBitcoinRx(this.mAccount.getId(), getSellParams(paymentMethod));
        this.mSellSubscription.clear();
        this.mSellSubscription.add(sellBitcoinObservable.observeOn(this.mMainScheduler).subscribe(SellConfirmationPresenter$$Lambda$6.lambdaFactory$(this, paymentMethod), SellConfirmationPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$performQuote$5(SellConfirmationPresenter this_, Data paymentMethod, Pair pair) {
        Response<Transfer> response = pair.first;
        if (response.isSuccessful()) {
            this_.setSelectedPaymentMethod(paymentMethod);
            this_.updateTransfer((Transfer) response.body());
            this_.mSellConfirmationScreen.hideProgressBar();
            return;
        }
        this_.mSellConfirmationScreen.hideProgressBar();
        this_.mSellRouter.routeToError(null, this_.getError(response.errorBody()));
    }

    static /* synthetic */ void lambda$performQuote$6(SellConfirmationPresenter this_, Throwable t) {
        this_.mSellConfirmationScreen.hideProgressBar();
        this_.mSellRouter.routeToError(null, Utils.getMessage(this_.mContext, t));
    }

    private HashMap<String, Object> getSellParams(Data paymentMethod) {
        HashMap<String, Object> params = new HashMap();
        params.put("commit", Boolean.valueOf(false));
        params.put("payment_method", paymentMethod.getId());
        params.put(ApiConstants.TOTAL, this.mTransferData.getTotal().getAmount());
        params.put("currency", this.mTransferData.getTotal().getCurrency());
        return params;
    }

    private void routeToSuccess(com.coinbase.v2.models.transfers.Data transferData) {
        this.mSellRouter.routeToSellSuccess(transferData, this.mAccount);
        this.mBuySellMadeConnector.get().onNext(this.mAccount);
    }
}
