package com.coinbase.android.paymentmethods;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.deposits.SepaDepositActivity;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodListener;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask.DeletePaymentMethodsTaskWrapper;
import com.coinbase.android.paymentmethods.card.CardFormPresenter;
import com.coinbase.android.settings.AccountSettingsController;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethods;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Qualifier;
import retrofit2.Response;
import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PaymentMethodsPresenter {
    static final int INTENT_BANK_ACCOUNT = 10003;
    private static final int INTENT_DEBIT_CARD = 10002;
    private static final int INTENT_SEPA_INFO = 10004;
    public static final int INTENT_VERIFY = 10001;
    public static final String SHOWING_AVAILABLE_PAYMENT_METHODS = "showing_available_payment_methods";
    public static final long SUCCESS_DELAY = 100;
    private Bundle mArgs;
    final List<Object> mAvailablePaymentMethods;
    private final AdapterDelegatesManager<List<Object>> mAvailablePaymentMethodsAdapterDelegate;
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private List<String> mCurrentPaymentMethodIds;
    private final DeletePaymentMethodsTaskWrapper mDeleteTaskWrapper;
    private final FeatureFlags mFeatureFlags;
    private final GetPaymentMethodsTaskRx mGetPaymentMethodsTask;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final int mPaymentMethodDeletedResId;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private final AdapterDelegatesManager<List<Object>> mPaymentMethodsAdapterDelegate;
    final List<Object> mPaymentMethodsAndHeaders;
    private final PaymentMethodsUpdatedConnector mPaymentMethodsUpdatedConnector;
    private final PaymentMethodsRouter mRouter;
    private final PaymentMethodsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final SplitTesting mSplitTesting;
    private CompositeSubscription mSubscription;
    private final SuccessRouter mSuccessRouter;

    @Qualifier
    @Documented
    public @interface PaymentMethodDeleted {
    }

    public enum PaymentMethodType {
        ACH_BANK_ACCOUNT(ApiConstants.ACH_BANK_ACCOUNT),
        BANK_WIRE("bank_wire"),
        CREDIT_CARD("credit_card"),
        DEBIT_CARD(ApiConstants.DEBIT_CARD),
        SECURE3D_CARD(ApiConstants.SECURE3D_CARD),
        WORLDPAY_CARD(ApiConstants.WORLDPAY_CARD),
        SEPA_BANK_ACCOUNT("sepa_bank_account"),
        FIAT_ACCOUNT("fiat_account"),
        IDEAL_BANK_ACCOUNT("ideal_bank_account"),
        EFT_BANK_ACCOUNT("eft_bank_account"),
        INTERAC("interac"),
        XFERS_ACCOUNT("xfers_account");
        
        private String _value;

        private PaymentMethodType(String value) {
            this._value = value;
        }

        public static PaymentMethodType fromString(String text) {
            if (text != null) {
                for (PaymentMethodType str : values()) {
                    if (text.equalsIgnoreCase(str.toString())) {
                        return str;
                    }
                }
            }
            return FIAT_ACCOUNT;
        }

        public String toString() {
            return this._value;
        }
    }

    enum RequirementType {
        JUMIO("jumio");
        
        private String _value;

        private RequirementType(String value) {
            this._value = value;
        }

        public String toString() {
            return this._value;
        }
    }

    public enum VerifyRequirementType {
        CARDS_CDV(ApiConstants.CDV),
        FULL_ADDRESS("full_address");
        
        private String _value;

        private VerifyRequirementType(String value) {
            this._value = value;
        }

        public String toString() {
            return this._value;
        }
    }

    @Inject
    PaymentMethodsPresenter(LoginManager loginManager, PaymentMethodsScreen screen, SnackBarWrapper snackBarWrapper, @PaymentMethodDeleted int paymentMethodDeletedResId, GetPaymentMethodsTaskRx getPaymentMethodsTask, @MainScheduler Scheduler mainScheduler, PaymentMethodsRouter router, SuccessRouter successRouter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, SplitTesting splitTesting, FeatureFlags featureFlags) {
        this(loginManager, screen, snackBarWrapper, paymentMethodDeletedResId, getPaymentMethodsTask, mainScheduler, new DeletePaymentMethodsTaskWrapper(), router, successRouter, bankAccountsUpdatedConnector, paymentMethodsUpdatedConnector, paymentMethodUtils, splitTesting, featureFlags, new AdapterDelegatesManager(), new AdapterDelegatesManager());
    }

    PaymentMethodsPresenter(LoginManager loginManager, PaymentMethodsScreen screen, SnackBarWrapper snackBarWrapper, int paymentMethodDeletedResId, GetPaymentMethodsTaskRx getPaymentMethodsTask, Scheduler mainScheduler, DeletePaymentMethodsTaskWrapper deleteTaskWrapper, PaymentMethodsRouter router, SuccessRouter successRouter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, SplitTesting splitTesting, FeatureFlags featureFlags, AdapterDelegatesManager<List<Object>> delegate1, AdapterDelegatesManager<List<Object>> delegate2) {
        this.mPaymentMethodsAndHeaders = new LinkedList();
        this.mCurrentPaymentMethodIds = new LinkedList();
        this.mAvailablePaymentMethods = new LinkedList();
        this.mSubscription = new CompositeSubscription();
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mPaymentMethodDeletedResId = paymentMethodDeletedResId;
        this.mGetPaymentMethodsTask = getPaymentMethodsTask;
        this.mMainScheduler = mainScheduler;
        this.mDeleteTaskWrapper = deleteTaskWrapper;
        this.mRouter = router;
        this.mSuccessRouter = successRouter;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
        this.mPaymentMethodsUpdatedConnector = paymentMethodsUpdatedConnector;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mSplitTesting = splitTesting;
        this.mFeatureFlags = featureFlags;
        this.mPaymentMethodsAdapterDelegate = delegate1;
        this.mPaymentMethodsAdapterDelegate.addDelegate(new PaymentMethodAdapterDelegate(this.mScreen.getActivity(), this)).addDelegate(new PaymentMethodHeaderAdapterDelegate(this.mScreen.getActivity()));
        this.mAvailablePaymentMethodsAdapterDelegate = delegate2;
        this.mAvailablePaymentMethodsAdapterDelegate.addDelegate(new AvailablePaymentMethodAdapterDelegate(this.mScreen.getActivity(), this));
    }

    private boolean isBankAccountChangesSplitTestEnabled() {
        return this.mSplitTesting.isInGroup(SplitTestConstants.LINK_BANK_ACCOUNT_CHANGES_SPLIT_TEST, SplitTestConstants.LINK_BANK_ACCOUNT_CHANGES_ENABLED);
    }

    private boolean isPaymentMethodsSplitTestEnabled() {
        return this.mSplitTesting.isInGroup(SplitTestConstants.SETTINGS_PAYMENT_METHODS_SPLIT_TEST, SplitTestConstants.SETTINGS_PAYMENT_METHODS_ENABLED);
    }

    void onClickAddPaymentMethod(AvailablePaymentMethod availablePaymentMethod) {
        PaymentMethodType type = PaymentMethodType.fromString(availablePaymentMethod.getType());
        switch (type) {
            case DEBIT_CARD:
            case CREDIT_CARD:
                onClickAddCard(getCardBundle(availablePaymentMethod));
                return;
            case SECURE3D_CARD:
                onClickAdd3dsCard(getCardBundle(availablePaymentMethod));
                return;
            case WORLDPAY_CARD:
                onClickAddWorldPayCard(getCardBundle(availablePaymentMethod));
                return;
            case ACH_BANK_ACCOUNT:
                if (isBankAccountChangesSplitTestEnabled()) {
                    connectBankAccount();
                    return;
                } else {
                    addBankAccount();
                    return;
                }
            case SEPA_BANK_ACCOUNT:
                showSepaInfo();
                return;
            default:
                throw new IllegalStateException("Unhandled payment method type [" + type + "]");
        }
    }

    private void onClickAddCard(Bundle args) {
        args.putString(CardFormScreen.CARD_TYPE, ApiConstants.DEBIT_CARD);
        this.mRouter.routeCardForm(args);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_LINKED_ACCOUNTS_TAPPED_CARD, new String[0]);
    }

    private void onClickAdd3dsCard(Bundle args) {
        args.putString(CardFormScreen.CARD_TYPE, ApiConstants.SECURE3D_CARD);
        this.mRouter.routeCardForm(args);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_LINKED_ACCOUNTS_TAPPED_CARD, new String[0]);
    }

    private void onClickAddWorldPayCard(Bundle args) {
        args.putString(CardFormScreen.CARD_TYPE, ApiConstants.WORLDPAY_CARD);
        this.mRouter.routeCardForm(args);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_LINKED_ACCOUNTS_TAPPED_CARD, new String[0]);
    }

    boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_VERIFY && resultCode == -1) {
            this.mScreen.popToRoot();
            return true;
        } else if (resultCode != Constants.ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER) {
            return false;
        } else {
            if (!this.mSuccessRouter.shouldRouteSuccess()) {
                return true;
            }
            new Handler(Looper.getMainLooper()).postDelayed(PaymentMethodsPresenter$$Lambda$1.lambdaFactory$(this), 100);
            return true;
        }
    }

    static /* synthetic */ void lambda$onActivityResult$0(PaymentMethodsPresenter this_) {
        if (this_.mScreen.isShown() && this_.mSuccessRouter.shouldRouteSuccess()) {
            this_.mSuccessRouter.routeSuccess();
        }
    }

    boolean onBackPressed() {
        if (this.mSuccessRouter.hasRootTag(AccountSettingsController.class) || !showingAddPaymentMethods()) {
            return false;
        }
        transitionToPaymentMethods();
        return true;
    }

    private void transitionToPaymentMethods() {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_LINKED_ACCOUNTS_VIEWED, new String[0]);
        this.mScreen.showPaymentMethodsAdapter();
        setShowingAddPaymentMethods(false);
    }

    private void addBankAccount() {
        if (PaymentMethodUtils.isUSUser(this.mLoginManager) && this.mScreen.getActivity() != null) {
            this.mRouter.routeAddPlaidAccount();
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_BANK_ACCOUNT_FROM_SETTINGS, new String[0]);
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_LINKED_ACCOUNTS_TAPPED_BANK, new String[0]);
        }
    }

    private void connectBankAccount() {
        if (PaymentMethodUtils.isUSUser(this.mLoginManager) && this.mScreen.getActivity() != null) {
            this.mSubscription.add(this.mFeatureFlags.get(FeatureFlags.FF_SHORTCUT_TO_PLAID_SCREEN).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(new Action1<Boolean>() {
                public void call(Boolean isEnabled) {
                    PaymentMethodsPresenter.this.mRouter.routeConnectBankAccount(isEnabled.booleanValue());
                }
            }, new Action1<Throwable>() {
                public void call(Throwable throwable) {
                    PaymentMethodsPresenter.this.mRouter.routeConnectBankAccount(false);
                }
            }));
        }
    }

    void showSepaInfo() {
        Intent intent = new Intent(this.mScreen.getActivity(), SepaDepositActivity.class);
        intent.putExtra(SepaDepositActivity.FROM_DEPOSIT, false);
        this.mScreen.startActivityForResult(intent, INTENT_SEPA_INFO);
    }

    private void refreshPaymentMethods() {
        this.mSubscription.add(this.mGetPaymentMethodsTask.getPaymentMethodsExcludeLimits().first().observeOn(this.mMainScheduler).subscribe(PaymentMethodsPresenter$$Lambda$2.lambdaFactory$(this), PaymentMethodsPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$refreshPaymentMethods$1(PaymentMethodsPresenter this_, Pair pair) {
        if (pair.first.isSuccessful()) {
            List<Data> paymentMethods = ((PaymentMethods) ((Response) pair.first).body()).getData();
            List<Data> pms = new ArrayList();
            for (Data method : paymentMethods) {
                Type type = method.getType();
                if ((type != Type.PAYPAL_ACCOUNT || method.getAllowSell().booleanValue()) && !((type == Type.CREDIT_CARD && !method.getAllowBuy().booleanValue()) || type == Type.SEPA_BANK_ACCOUNT || type == Type.FIAT_ACCOUNT)) {
                    pms.add(method);
                }
            }
            List<Data> pmDataSource = this_.mPaymentMethodUtils.filterPaymentMethods(pms);
            this_.mPaymentMethodsAndHeaders.clear();
            this_.mPaymentMethodsAndHeaders.addAll(this_.mPaymentMethodUtils.insertHeadersIntoPaymentMethodsList(pmDataSource));
            this_.getAvailablePaymentMethods(pmDataSource);
            if (!pmDataSource.isEmpty() && this_.paymentMethodsUpdated(pmDataSource)) {
                this_.mScreen.hideProgress();
                this_.mScreen.notifyPaymentMethodsDataSetChanged();
                if (!this_.showingAddPaymentMethods()) {
                    this_.transitionToPaymentMethods();
                }
                this_.updateCurrentPaymentMethods(pmDataSource);
                return;
            }
            return;
        }
        this_.mSnackBarWrapper.showGenericErrorTryAgain();
    }

    static /* synthetic */ void lambda$refreshPaymentMethods$2(PaymentMethodsPresenter this_, Throwable t) {
        this_.mScreen.hideProgress();
        this_.mSnackBarWrapper.showFailure(t);
    }

    void onResume(Bundle args) {
        this.mArgs = args;
        if (showingAddPaymentMethods()) {
            onAddPaymentMethodsButtonClicked();
        } else {
            transitionToPaymentMethods();
        }
        this.mSubscription.add(this.mBankAccountsUpdatedConnector.get().filter(PaymentMethodsPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(PaymentMethodsPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mPaymentMethodsUpdatedConnector.get().filter(PaymentMethodsPresenter$$Lambda$6.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(PaymentMethodsPresenter$$Lambda$7.lambdaFactory$(this)));
        refreshPaymentMethods();
    }

    static /* synthetic */ Boolean lambda$onResume$3(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(PaymentMethodsPresenter.class));
    }

    static /* synthetic */ Boolean lambda$onResume$5(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(PaymentMethodsPresenter.class));
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void onAddPaymentMethodsButtonClicked() {
        showAvailablePaymentMethods();
        setShowingAddPaymentMethods(true);
    }

    private void showAvailablePaymentMethods() {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_ADD_LINKED_ACCOUNTS_VIEWED, new String[0]);
        this.mScreen.showAvailablePaymentMethodsAdapter();
    }

    private boolean showingAddPaymentMethods() {
        if (this.mArgs == null) {
            return false;
        }
        return this.mArgs.getBoolean(SHOWING_AVAILABLE_PAYMENT_METHODS);
    }

    private void setShowingAddPaymentMethods(boolean showingAddPaymentMethods) {
        if (this.mArgs != null) {
            this.mArgs.putBoolean(SHOWING_AVAILABLE_PAYMENT_METHODS, showingAddPaymentMethods);
        }
    }

    private void getAvailablePaymentMethods(List<Data> currentPaymentMethods) {
        this.mSubscription.add(this.mLoginManager.getClient().getAvailablePaymentMethodsRx("").first().observeOn(this.mMainScheduler).subscribe(PaymentMethodsPresenter$$Lambda$8.lambdaFactory$(this, currentPaymentMethods), PaymentMethodsPresenter$$Lambda$9.lambdaFactory$()));
    }

    static /* synthetic */ void lambda$getAvailablePaymentMethods$7(PaymentMethodsPresenter this_, List currentPaymentMethods, Pair pair) {
        Response<AvailablePaymentMethods> response = pair.first;
        if (response.isSuccessful() && ((AvailablePaymentMethods) response.body()).getData() != null) {
            this_.setUpAvailablePaymentMethods(((AvailablePaymentMethods) response.body()).getData().getAvailablePaymentMethods(), currentPaymentMethods);
        }
    }

    static /* synthetic */ void lambda$getAvailablePaymentMethods$8(Throwable t) {
    }

    private void setUpAvailablePaymentMethods(List<AvailablePaymentMethod> paymentMethods, List<Data> currentPaymentMethods) {
        List<Pair<PaymentMethodType, AvailablePaymentMethod>> filteredAvailablePaymentMethods = filterAvailablePaymentMethods(paymentMethods);
        this.mAvailablePaymentMethods.clear();
        this.mAvailablePaymentMethods.addAll(filteredAvailablePaymentMethods);
        if (filteredAvailablePaymentMethods.isEmpty()) {
            this.mScreen.showNoAvailablePaymentMethods();
            return;
        }
        this.mScreen.hideProgress();
        this.mScreen.notifyAvailablePaymentMethodsDataSetChanged();
        if (currentPaymentMethods == null || currentPaymentMethods.isEmpty() || (this.mArgs != null && this.mArgs.getBoolean(PaymentMethodsController.INTENDING_TO_ADD_PAYMENT_METHOD))) {
            showAvailablePaymentMethods();
        }
    }

    private List<Pair<PaymentMethodType, AvailablePaymentMethod>> filterAvailablePaymentMethods(List<AvailablePaymentMethod> paymentMethods) {
        List<AvailablePaymentMethod> sortedAvailablePaymentMethods = new LinkedList(paymentMethods);
        Collections.sort(sortedAvailablePaymentMethods, PaymentMethodsPresenter$$Lambda$10.lambdaFactory$(paymentMethods));
        List<Pair<PaymentMethodType, AvailablePaymentMethod>> filteredAvailablePaymentMethods = new LinkedList();
        for (AvailablePaymentMethod paymentMethod : sortedAvailablePaymentMethods) {
            PaymentMethodType paymentMethodType = PaymentMethodType.fromString(paymentMethod.getType());
            switch (paymentMethodType) {
                case DEBIT_CARD:
                case CREDIT_CARD:
                case SECURE3D_CARD:
                case WORLDPAY_CARD:
                case ACH_BANK_ACCOUNT:
                case SEPA_BANK_ACCOUNT:
                    if (this.mFeatureFlags.hasFeature("tEsTdIsAbLeD")) {
                        if (Arrays.asList(new PaymentMethodType[]{PaymentMethodType.DEBIT_CARD, PaymentMethodType.CREDIT_CARD, PaymentMethodType.SECURE3D_CARD}).contains(paymentMethodType)) {
                            break;
                        }
                    }
                    filteredAvailablePaymentMethods.add(new Pair(paymentMethodType, paymentMethod));
                    break;
                default:
                    break;
            }
        }
        return filteredAvailablePaymentMethods;
    }

    static /* synthetic */ int lambda$filterAvailablePaymentMethods$9(List paymentMethods, AvailablePaymentMethod p1, AvailablePaymentMethod p2) {
        boolean recommended1;
        if (p1.getRecommended() == null || !p1.getRecommended().booleanValue()) {
            recommended1 = false;
        } else {
            recommended1 = true;
        }
        boolean recommended2;
        if (p2.getRecommended() == null || !p2.getRecommended().booleanValue()) {
            recommended2 = false;
        } else {
            recommended2 = true;
        }
        if (recommended1 && !recommended2) {
            return -1;
        }
        if (recommended1 || !recommended2) {
            return paymentMethods.indexOf(p1) - paymentMethods.indexOf(p2);
        }
        return 1;
    }

    private Bundle getCardBundle(AvailablePaymentMethod paymentMethod) {
        boolean requiresCDV = false;
        boolean requiresBillingAddress = false;
        boolean requiresJumio = false;
        List<String> verifyRequirements = paymentMethod.getVerifyRequirements();
        if (verifyRequirements.contains(VerifyRequirementType.CARDS_CDV.toString())) {
            requiresCDV = true;
        }
        if (verifyRequirements.contains(VerifyRequirementType.FULL_ADDRESS.toString())) {
            requiresBillingAddress = true;
        }
        if (paymentMethod.getRequirements().contains(RequirementType.JUMIO.toString())) {
            requiresJumio = true;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(CardFormPresenter.REQUIRES_CDV, requiresCDV);
        bundle.putBoolean(CardFormPresenter.REQUIRES_BILLING_ADDRESS, requiresBillingAddress);
        bundle.putBoolean(CardFormPresenter.REQUIRES_JUMIO, requiresJumio);
        return bundle;
    }

    void onRemovePaymentMethodClicked(Data method) {
        this.mScreen.showRemovePaymentMethodConfirmed(method);
    }

    void onRemovePaymentMethodConfirmed(Data method) {
        this.mDeleteTaskWrapper.get(this.mScreen.getActivity(), method.getId(), createDeleteListener()).deletePaymentMethod();
        this.mScreen.hideRemovePaymentMethodView();
    }

    void onRemovePaymentMethodCancel() {
        this.mScreen.hideRemovePaymentMethodView();
    }

    void onVerifyPaymentMethodClicked(Data method) {
        this.mRouter.routeVerifyPaymentMethod(method);
    }

    int getPaymentMethodCount() {
        return this.mPaymentMethodsAndHeaders.size();
    }

    int getPaymentMethodItemViewType(int position) {
        return this.mPaymentMethodsAdapterDelegate.getItemViewType(this.mPaymentMethodsAndHeaders, position);
    }

    ViewHolder onCreatePaymentMethodViewHolder(ViewGroup parent, int viewType) {
        return this.mPaymentMethodsAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindPaymentMethodViewHolder(ViewHolder holder, int position) {
        this.mPaymentMethodsAdapterDelegate.onBindViewHolder(this.mPaymentMethodsAndHeaders, position, holder);
    }

    int getAvailablePaymentMethodCount() {
        return this.mAvailablePaymentMethods.size();
    }

    int getAvailablePaymentMethodItemViewType(int position) {
        return this.mAvailablePaymentMethodsAdapterDelegate.getItemViewType(this.mAvailablePaymentMethods, position);
    }

    ViewHolder onCreateAvailablePaymentMethodViewHolder(ViewGroup parent, int viewType) {
        return this.mAvailablePaymentMethodsAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindAvailablePaymentMethodViewHolder(ViewHolder holder, int position) {
        this.mAvailablePaymentMethodsAdapterDelegate.onBindViewHolder(this.mAvailablePaymentMethods, position, holder);
    }

    private boolean paymentMethodsUpdated(List<Data> paymentMethods) {
        if (paymentMethods.size() != this.mCurrentPaymentMethodIds.size()) {
            return true;
        }
        for (int i = 0; i < paymentMethods.size(); i++) {
            if (!TextUtils.equals(((Data) paymentMethods.get(i)).getId(), (CharSequence) this.mCurrentPaymentMethodIds.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void updateCurrentPaymentMethods(List<Data> paymentMethods) {
        this.mCurrentPaymentMethodIds.clear();
        for (Data data : paymentMethods) {
            this.mCurrentPaymentMethodIds.add(data.getId());
        }
    }

    private DeletePaymentMethodListener createDeleteListener() {
        return PaymentMethodsPresenter$$Lambda$11.lambdaFactory$(this);
    }

    private void onBanksUpdated() {
        if (isPaymentMethodsSplitTestEnabled()) {
            this.mScreen.popToRoot();
            return;
        }
        if (showingAddPaymentMethods()) {
            transitionToPaymentMethods();
        }
        refreshPaymentMethods();
    }

    private void onPaymentMethodsUpdated() {
        if (showingAddPaymentMethods()) {
            transitionToPaymentMethods();
        }
        refreshPaymentMethods();
    }
}
