package com.coinbase.android.paymentmethods;

import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodListener;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodsTaskWrapper;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenter.PaymentMethodDeleted;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class ConnectedAccountsPresenter {
    private final AddPaymentMethodConnector mAddPaymentMethodConnector;
    private final AppCompatActivity mAppCompatActivity;
    private List<Data> mConnectedAccountsList = new ArrayList();
    private final DeletePaymentMethodsTaskWrapper mDeleteTaskWrapper;
    private final GetPaymentMethodsTaskRx mGetPaymentMethodsTaskRx;
    private final Logger mLogger = LoggerFactory.getLogger(ConnectedAccountsPresenter.class);
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final int mPaymentMethodDeletedResId;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private final PaymentMethodsFetchedConnector mPaymentMethodsFetchedConnector;
    private final PaymentMethodsUpdatedConnector mPaymentMethodsUpdatedConnector;
    private final ConnectedAccountsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final VerifyPaymentMethodConnector mVerifyPaymentMethodConnector;

    @Inject
    ConnectedAccountsPresenter(AppCompatActivity appCompatActivity, ConnectedAccountsScreen screen, GetPaymentMethodsTaskRx getPaymentMethodsTaskRx, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, AddPaymentMethodConnector addPaymentMethodConnector, VerifyPaymentMethodConnector verifyPaymentMethodConnector, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, PaymentMethodUtils paymentMethodUtils, MixpanelTracking mixpanelTracking, @PaymentMethodDeleted int paymentMethodDeletedResId, SnackBarWrapper snackBarWrapper, @MainScheduler Scheduler mainScheduler) {
        this.mAppCompatActivity = appCompatActivity;
        this.mScreen = screen;
        this.mGetPaymentMethodsTaskRx = getPaymentMethodsTaskRx;
        this.mDeleteTaskWrapper = new DeletePaymentMethodsTaskWrapper();
        this.mPaymentMethodsUpdatedConnector = paymentMethodsUpdatedConnector;
        this.mAddPaymentMethodConnector = addPaymentMethodConnector;
        this.mVerifyPaymentMethodConnector = verifyPaymentMethodConnector;
        this.mPaymentMethodsFetchedConnector = paymentMethodsFetchedConnector;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mMixpanelTracking = mixpanelTracking;
        this.mPaymentMethodDeletedResId = paymentMethodDeletedResId;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mPaymentMethodsUpdatedConnector.get().filter(ConnectedAccountsPresenter$$Lambda$1.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(ConnectedAccountsPresenter$$Lambda$2.lambdaFactory$(this), ConnectedAccountsPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mPaymentMethodsFetchedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(ConnectedAccountsPresenter$$Lambda$4.lambdaFactory$(this), ConnectedAccountsPresenter$$Lambda$5.lambdaFactory$(this)));
        fetchPaymentMethods();
    }

    static /* synthetic */ Boolean lambda$onShow$0(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(PaymentMethodsPresenter.class));
    }

    static /* synthetic */ void lambda$onShow$3(ConnectedAccountsPresenter this_, List paymentMethodsList) {
        this_.mConnectedAccountsList.clear();
        if (paymentMethodsList != null) {
            this_.mConnectedAccountsList.addAll(paymentMethodsList);
        }
        this_.mScreen.notifyAccountListAdapterChanged();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    private void fetchPaymentMethods() {
        this.mSubscription.add(this.mGetPaymentMethodsTaskRx.getPaymentMethodsExcludeLimits().first().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(ConnectedAccountsPresenter$$Lambda$6.lambdaFactory$(this), ConnectedAccountsPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$fetchPaymentMethods$5(ConnectedAccountsPresenter this_, Pair pair) {
        if (pair.first.isSuccessful()) {
            List<Data> paymentMethods = ((PaymentMethods) ((Response) pair.first).body()).getData();
            List<Data> pms = new ArrayList();
            for (Data method : paymentMethods) {
                Type type = method.getType();
                if (!(type == Type.BANK_WIRE || type == Type.FIAT_ACCOUNT)) {
                    pms.add(method);
                }
            }
            this_.mPaymentMethodsFetchedConnector.get().onNext(this_.mPaymentMethodUtils.filterPaymentMethods(pms));
            return;
        }
        this_.mSnackBarWrapper.show((int) R.string.load_linked_accounts_error);
    }

    List<Data> getConnectedAccountList() {
        return this.mConnectedAccountsList;
    }

    void onItemClicked(Data connectedAccount) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SETTINGS_TAPPED_LINKED_ACCOUNT, new String[0]);
        this.mScreen.showRemovePaymentFooterView(connectedAccount);
    }

    void onAddAccountClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SETTINGS_TAPPED_ADD_LINKED_ACCOUNT, MixpanelTracking.EVENT_LINKED_ACCOUNT_NUM_PROPERTY, String.valueOf(this.mConnectedAccountsList.size()));
        this.mAddPaymentMethodConnector.get().onNext(new ClassConsumableEvent());
    }

    void onRemovePaymentMethodClicked(Data connectedAccount) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNT_TAPPED_REMOVE, "type", connectedAccount.getType().toString());
        this.mScreen.showRemoveConfirmationDialog(connectedAccount);
    }

    void onVerifyPaymentMethodClicked(Data connectedAccount) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNT_TAPPED_VERIFY, "type", connectedAccount.getType().toString());
        this.mScreen.hideRemovePaymentFooterView();
        this.mVerifyPaymentMethodConnector.get().onNext(connectedAccount);
    }

    void onConfirmRemoveConfirmationCicked(Data connectedAccount) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNT_REMOVAL_CONFIRMATION_TAPPED_REMOVE, "type", connectedAccount.getType().toString());
        this.mDeleteTaskWrapper.get(this.mAppCompatActivity, connectedAccount.getId(), createDeleteListener()).deletePaymentMethod();
        this.mScreen.hideRemoveConfirmationDialog();
    }

    void onCancelRemoveConfirmationClicked(Data connectedAccount) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNT_REMOVAL_CONFIRMATION_TAPPED_CANCEL, "type", connectedAccount.getType().toString());
        this.mScreen.hideRemoveConfirmationDialog();
    }

    Pair<String, String> getFormattedPaymentMethodNameAndNumberDisplay(Data connectedAccount) {
        if (connectedAccount == null) {
            return null;
        }
        return this.mPaymentMethodUtils.getFormattedNameAndNumberDisplay(connectedAccount);
    }

    int getResourceForType(Type type) {
        return this.mPaymentMethodUtils.getResourceForType(type);
    }

    private DeletePaymentMethodListener createDeleteListener() {
        return ConnectedAccountsPresenter$$Lambda$8.lambdaFactory$(this);
    }

    int getTextNameForType(Type type) {
        switch (type) {
            case ACH_BANK_ACCOUNT:
            case SEPA_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
                return R.string.remove_bank_name;
            case CREDIT_CARD:
            case DEBIT_CARD:
            case SECURE_3DS:
            case WORLDPAY_CARD:
                return R.string.remove_card_name;
            default:
                return R.string.remove_account;
        }
    }
}
