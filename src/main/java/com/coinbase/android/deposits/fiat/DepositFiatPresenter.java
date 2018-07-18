package com.coinbase.android.deposits.fiat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.quickstart.QuickstartManager.Type;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import com.coinbase.android.ui.NumericKeypadConnector.NumericKeypadButton;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.coinbase.v2.models.transfers.Transfer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class DepositFiatPresenter {
    private static final String CACHED_ENTERED_VALUE = "cached_entered_value";
    private static final String CACHED_PAYMENT_METHOD = "cached_payment_method";
    private static final String CACHED_PAYMENT_METHOD_LIST = "cached_payment_method_list";
    public static final String CURRENCY_UNIT = "DepositFiatCurrencyUnit";
    public static final String DEPOSIT_FIAT_ACCOUNT = "deposit_fiat_account";
    private Data mAccount;
    private final BankAccountsUpdatedConnector mBankAccountsUpdatedConnector;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private final Context mContext;
    private final DepositFiatRouter mDepositFiatRouter;
    private final DepositFiatScreen mDepositFiatScreen;
    private Subscription mDepositSubscription;
    private String mEnteredAmountValue = "";
    private final GetPaymentMethodsTaskRx mGetPaymentMethodsTask;
    private final GoToSettingsConnector mGoToSettingsConnector;
    private final KeypadAmountFormatter mKeypadAmountFormatter;
    private final KeypadAmountValidator mKeypadAmountValidator;
    private final LinkedAccountConnector mLinkedAccountConnector;
    private final Logger mLogger = LoggerFactory.getLogger(DepositFiatPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final NumericKeypadConnector mNumericKeypadConnector;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private final PaymentMethodsUpdatedConnector mPaymentMethodsUpdateConnector;
    private final PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private BigMoney mPrimaryAmount;
    private CurrencyUnit mPrimaryCurrencyUnit;
    private final CompositeSubscription mQuickStartSubscription = new CompositeSubscription();
    private final QuickstartManager mQuickstartManager;
    private com.coinbase.v2.models.paymentMethods.Data mSelectedPaymentMethod;
    private final SnackBarWrapper mSnackBarWrapper;
    private final List<com.coinbase.v2.models.paymentMethods.Data> mValidPaymentMethods = new ArrayList();

    @Inject
    DepositFiatPresenter(Application application, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, DepositFiatRouter depositFiatRouter, DepositFiatScreen depositFiatScreen, GetPaymentMethodsTaskRx getPaymentMethodsTask, GoToSettingsConnector goToSettingsConnector, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, LinkedAccountConnector linkedAccountConnector, LoginManager loginManager, MixpanelTracking mixpanelTracking, NumericKeypadConnector numericKeypadConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickstartManager quickstartManager, SnackBarWrapper snackBarWrapper, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mBankAccountsUpdatedConnector = bankAccountsUpdatedConnector;
        this.mDepositFiatRouter = depositFiatRouter;
        this.mDepositFiatScreen = depositFiatScreen;
        this.mGetPaymentMethodsTask = getPaymentMethodsTask;
        this.mGoToSettingsConnector = goToSettingsConnector;
        this.mKeypadAmountFormatter = keypadAmountFormatter;
        this.mKeypadAmountValidator = keypadAmountValidator;
        this.mLinkedAccountConnector = linkedAccountConnector;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mNumericKeypadConnector = numericKeypadConnector;
        this.mPaymentMethodsUpdateConnector = paymentMethodsUpdatedConnector;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        this.mQuickstartManager = quickstartManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        hookUpKeypad();
        hookUpLinkedAccounts();
        hookUpPhoneNumberListener();
        hookUpGoToSettingsDialog();
        if (checkAndGetUserEntriesFromBundle(this.mDepositFiatScreen.getArgs())) {
            this.mDepositFiatScreen.showDepositContent();
            return;
        }
        String accountString = this.mDepositFiatScreen.getArgs().getString(DEPOSIT_FIAT_ACCOUNT);
        if (TextUtils.isEmpty(accountString)) {
            this.mLogger.error("No account was passed in to Deposit. (Unsure how user got here)");
        } else {
            this.mAccount = (Data) new Gson().fromJson(accountString, Data.class);
        }
        initAmountAndCurrency();
        updateQuickstart();
    }

    private void updateQuickstart() {
        this.mDepositFiatScreen.showProgressDialog();
        this.mQuickStartSubscription.clear();
        this.mQuickStartSubscription.add(this.mQuickstartManager.updateQuickstartItems(Type.DEPOSITS.toString()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$1.lambdaFactory$(this), DepositFiatPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$updateQuickstart$0(DepositFiatPresenter this_, Void v) {
        this_.mDepositFiatScreen.hideProgressDialog();
        this_.checkAccountSetupStatus();
    }

    static /* synthetic */ void lambda$updateQuickstart$1(DepositFiatPresenter this_, Throwable t) {
        this_.mLogger.error("Error from updateQuickstartItems");
        this_.mDepositFiatScreen.hideProgressDialog();
        this_.checkAccountSetupStatus();
    }

    private void checkAccountSetupStatus() {
        List<QuickstartItem> quickstartItems = this.mQuickstartManager.getCachedQuickstartItems(true);
        if (quickstartItems.contains(QuickstartItem.REGION_UNSUPPORTED)) {
            this.mDepositFiatScreen.showRegionUnsupported(new Locale("", this.mLoginManager.getActiveUserCountryCode()).getDisplayCountry());
        } else if (quickstartItems.isEmpty()) {
            this.mDepositFiatScreen.showDepositContent();
            refreshPaymentMethods();
        } else {
            this.mDepositFiatScreen.showQuickstartContent(quickstartItems);
        }
    }

    private void hookUpKeypad() {
        this.mCompositeSubscription.add(this.mNumericKeypadConnector.getNumericSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$3.lambdaFactory$(this), DepositFiatPresenter$$Lambda$4.lambdaFactory$(this)));
        this.mCompositeSubscription.add(this.mNumericKeypadConnector.getButtonClickedSubject().onBackpressureLatest().map(DepositFiatPresenter$$Lambda$5.lambdaFactory$()).filter(DepositFiatPresenter$$Lambda$6.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$7.lambdaFactory$(this), DepositFiatPresenter$$Lambda$8.lambdaFactory$(this)));
        this.mCompositeSubscription.add(this.mNumericKeypadConnector.getButtonLongClickedSubject().onBackpressureLatest().filter(DepositFiatPresenter$$Lambda$9.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$10.lambdaFactory$(this), DepositFiatPresenter$$Lambda$11.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$hookUpKeypad$4(Pair numericKeypadButtonStringPair) {
        if (numericKeypadButtonStringPair == null) {
            return null;
        }
        switch ((NumericKeypadButton) numericKeypadButtonStringPair.first) {
            case LEFT:
                return new Pair(KeypadType.DOT, Character.valueOf(NumericKeypadConnector.DOT));
            case RIGHT:
                return new Pair(KeypadType.DELETE, Character.valueOf(' '));
            default:
                return null;
        }
    }

    static /* synthetic */ Boolean lambda$hookUpKeypad$5(Pair keypadTypeCharacterPair) {
        return Boolean.valueOf(keypadTypeCharacterPair != null);
    }

    static /* synthetic */ Boolean lambda$hookUpKeypad$8(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.RIGHT;
        return Boolean.valueOf(z);
    }

    private void hookUpLinkedAccounts() {
        this.mCompositeSubscription.add(this.mLinkedAccountConnector.getLinkedAccountClickedSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$12.lambdaFactory$(this), DepositFiatPresenter$$Lambda$13.lambdaFactory$(this)));
        this.mCompositeSubscription.add(this.mLinkedAccountConnector.getMissingAccountClickedSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$14.lambdaFactory$(this), DepositFiatPresenter$$Lambda$15.lambdaFactory$(this)));
        this.mCompositeSubscription.add(this.mLinkedAccountConnector.getPaymentMethodSelectedSubject().filter(DepositFiatPresenter$$Lambda$16.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$17.lambdaFactory$(this), DepositFiatPresenter$$Lambda$18.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$hookUpLinkedAccounts$11(DepositFiatPresenter this_, Void v) {
        this_.storeUserEntriesInBundle();
        this_.mDepositFiatRouter.routeToLinkedAccountsPicker(this_.mValidPaymentMethods);
    }

    static /* synthetic */ Boolean lambda$hookUpLinkedAccounts$15(Pair v) {
        boolean z = (v == null || ((ClassConsumableEvent) v.first).consumeIfNotConsumed(DepositFiatPresenter.class)) ? false : true;
        return Boolean.valueOf(z);
    }

    private void hookUpPhoneNumberListener() {
        this.mCompositeSubscription.add(this.mPhoneNumbersUpdatedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$19.lambdaFactory$(this)));
    }

    private void hookUpGoToSettingsDialog() {
        this.mCompositeSubscription.add(this.mGoToSettingsConnector.get().filter(DepositFiatPresenter$$Lambda$20.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$21.lambdaFactory$(this), DepositFiatPresenter$$Lambda$22.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$hookUpGoToSettingsDialog$19(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(DepositFiatPresenter.class));
    }

    private void onPhoneNumbersUpdated(Object event) {
        updateQuickstart();
        Utils.processPhoneNumbersUpdatedEvent(event, this.mContext);
    }

    void onHide() {
        storeUserEntriesInBundle();
        this.mCompositeSubscription.clear();
        this.mQuickStartSubscription.clear();
        if (this.mDepositSubscription != null && !this.mDepositSubscription.isUnsubscribed()) {
            this.mDepositSubscription.unsubscribe();
        }
    }

    void onPreviewButtonClicked() {
        trackEvent(MixpanelTracking.EVENT_DEPOSIT_TAPPED_PREVIEW, new String[0]);
        previewDeposit();
    }

    private void initAmountAndCurrency() {
        this.mPrimaryCurrencyUnit = CurrencyUnit.getInstance(this.mAccount.getCurrency().getCode());
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, 0.0d);
        formatAndSetAmountText(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, "");
    }

    private boolean checkAndGetUserEntriesFromBundle(Bundle args) {
        String cachedAccountString = args.getString(DEPOSIT_FIAT_ACCOUNT);
        String cachedEnteredAmountString = args.getString(CACHED_ENTERED_VALUE);
        String cachedPaymentMethodString = args.getString(CACHED_PAYMENT_METHOD);
        String cachedPaymentMethodListString = args.getString(CACHED_PAYMENT_METHOD_LIST);
        if (cachedAccountString == null || cachedEnteredAmountString == null || cachedPaymentMethodString == null || cachedPaymentMethodListString == null) {
            return false;
        }
        Gson gson = new Gson();
        Data account = (Data) gson.fromJson(cachedAccountString, Data.class);
        com.coinbase.v2.models.paymentMethods.Data tempSelectedPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) gson.fromJson(cachedPaymentMethodString, com.coinbase.v2.models.paymentMethods.Data.class);
        List<com.coinbase.v2.models.paymentMethods.Data> tempValidPaymentMethods = (List) gson.fromJson(cachedPaymentMethodListString, new TypeToken<ArrayList<com.coinbase.v2.models.paymentMethods.Data>>() {
        }.getType());
        if (account == null || tempSelectedPaymentMethod == null || tempValidPaymentMethods == null) {
            this.mLogger.error("arguments could not be deserialized properly.");
            return false;
        }
        this.mAccount = account;
        this.mPrimaryCurrencyUnit = CurrencyUnit.getInstance(account.getCurrency().getCode());
        this.mEnteredAmountValue = cachedEnteredAmountString;
        this.mSelectedPaymentMethod = tempSelectedPaymentMethod;
        this.mValidPaymentMethods.clear();
        this.mValidPaymentMethods.addAll(tempValidPaymentMethods);
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(this.mKeypadAmountFormatter.parseEnteredText(this.mEnteredAmountValue)));
        updatePaymentMethod(this.mSelectedPaymentMethod);
        return true;
    }

    private void storeUserEntriesInBundle() {
        Bundle args = this.mDepositFiatScreen.getArgs();
        Gson gson = new Gson();
        args.putString(DEPOSIT_FIAT_ACCOUNT, gson.toJson(this.mAccount));
        args.putString(CACHED_ENTERED_VALUE, this.mEnteredAmountValue);
        args.putString(CACHED_PAYMENT_METHOD, gson.toJson(this.mSelectedPaymentMethod));
        args.putString(CACHED_PAYMENT_METHOD_LIST, gson.toJson(this.mValidPaymentMethods));
    }

    private void previewDeposit() {
        storeUserEntriesInBundle();
        if (this.mSelectedPaymentMethod == null) {
            this.mDepositFiatRouter.routeToGoToSettingsDialog();
            return;
        }
        String error = getDepositError();
        if (error != null) {
            showErrorMessage(error);
            return;
        }
        this.mDepositFiatScreen.showProgressDialog();
        if (!(this.mDepositSubscription == null || this.mDepositSubscription.isUnsubscribed())) {
            this.mDepositSubscription.unsubscribe();
        }
        HashMap<String, Object> params = new HashMap();
        params.put("amount", this.mPrimaryAmount.getAmount().toPlainString());
        params.put("currency", this.mPrimaryAmount.getCurrencyUnit().toString());
        params.put("payment_method", this.mSelectedPaymentMethod.getId());
        params.put("commit", Boolean.valueOf(false));
        this.mDepositSubscription = this.mLoginManager.getClient().depositFundsRx(this.mAccount.getId(), params).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$23.lambdaFactory$(this), DepositFiatPresenter$$Lambda$24.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$previewDeposit$22(DepositFiatPresenter this_, Pair responseRetrofitPair) {
        this_.mDepositFiatScreen.hideProgressDialog();
        Response response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            this_.mDepositFiatRouter.routeToDepositConfirmation(((Transfer) response.body()).getData(), this_.mAccount, this_.mSelectedPaymentMethod, this_.mValidPaymentMethods);
            return;
        }
        this_.showErrorMessage(Utils.getErrorMessage(response));
    }

    static /* synthetic */ void lambda$previewDeposit$23(DepositFiatPresenter this_, Throwable throwable) {
        this_.mDepositFiatScreen.hideProgressDialog();
        this_.showErrorMessage(Utils.getMessage(this_.mContext, throwable));
    }

    private String getDepositError() {
        if (TextUtils.isEmpty(this.mEnteredAmountValue)) {
            return this.mContext.getString(R.string.entered_amt_empty);
        }
        if (this.mAccount == null) {
            return this.mContext.getString(R.string.account_unavailable_error_occurred);
        }
        return null;
    }

    private void showErrorMessage(String errorMessage) {
        trackEvent(MixpanelTracking.EVENT_DEPOSIT_VIEWED_ERROR, "error", errorMessage);
        this.mDepositFiatRouter.routeToError(null, errorMessage);
    }

    private void onKeystrokeEntered(KeypadType type, char digit) {
        if (this.mKeypadAmountValidator.validateAmount(type, digit, this.mEnteredAmountValue, this.mPrimaryCurrencyUnit.getDefaultFractionDigits())) {
            String newValue = this.mKeypadAmountFormatter.appendKeystroke(type, digit, this.mEnteredAmountValue);
            if (newValue == null) {
                this.mSnackBarWrapper.showGenericErrorTryAgain();
                return;
            }
            this.mEnteredAmountValue = newValue;
            this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(this.mKeypadAmountFormatter.parseEnteredText(this.mEnteredAmountValue)));
            formatAndSetAmountText(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, this.mEnteredAmountValue);
            return;
        }
        this.mDepositFiatScreen.showInvalidKeystroke();
    }

    private void formatAndSetAmountText(CurrencyUnit currencyUnit, BigMoney amount, String enteredValue) {
        this.mDepositFiatScreen.setAmountText(this.mKeypadAmountFormatter.getFormattedPrimaryAmount(currencyUnit, amount, enteredValue, R.color.cerulean_disabled));
    }

    private SpannableStringBuilder formatAmount(CurrencyUnit currencyUnit, String enteredAmount) {
        return this.mKeypadAmountFormatter.getFormattedPrimaryAmount(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, this.mEnteredAmountValue, R.color.cerulean_disabled);
    }

    private void refreshPaymentMethods() {
        this.mCompositeSubscription.add(this.mGetPaymentMethodsTask.getPaymentMethodsExcludeLimits().first().observeOn(this.mMainScheduler).subscribe(DepositFiatPresenter$$Lambda$25.lambdaFactory$(this), DepositFiatPresenter$$Lambda$26.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$refreshPaymentMethods$24(DepositFiatPresenter this_, Pair pair) {
        Response<PaymentMethods> response = pair.first;
        if (response.isSuccessful()) {
            List<com.coinbase.v2.models.paymentMethods.Data> paymentMethods = ((PaymentMethods) response.body()).getData();
            if (paymentMethods == null || paymentMethods.isEmpty()) {
                this_.showMissingPaymentMethod();
                return;
            } else {
                this_.getDefaultDepositPaymentMethod(paymentMethods);
                return;
            }
        }
        this_.showMissingPaymentMethod();
    }

    void performActionForQuickstartItem(QuickstartItem item, Activity activity, VerifyPhoneCaller verifyPhoneCaller) {
        MixpanelTracking.logTransactionEvent(item);
        this.mQuickstartManager.performActionForItem(item, activity, verifyPhoneCaller);
    }

    int getResourceForType(com.coinbase.v2.models.paymentMethods.Data.Type type) {
        return this.mPaymentMethodUtils.getResourceForType(type);
    }

    private void updatePaymentMethod(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        this.mSelectedPaymentMethod = paymentMethod;
        if (this.mSelectedPaymentMethod == null) {
            this.mDepositFiatScreen.showMissingPaymentMethod();
            return;
        }
        if (this.mSelectedPaymentMethod.getType() == com.coinbase.v2.models.paymentMethods.Data.Type.FIAT_ACCOUNT) {
            this.mLogger.error("Payment method [" + paymentMethod + "] was a fiat wallet. Should not have been selectable.");
        }
        this.mDepositFiatScreen.showPaymentMethod(paymentMethod, this.mPaymentMethodUtils.getLimit(paymentMethod), this.mPaymentMethodUtils.isLimitReached(paymentMethod));
        formatAndSetAmountText(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, this.mEnteredAmountValue);
    }

    Pair<String, String> getFormattedPaymentMethodNameAndNumberDisplay(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        return this.mPaymentMethodUtils.getFormattedNameAndNumberDisplay(paymentMethod);
    }

    private void showMissingPaymentMethod() {
        this.mSelectedPaymentMethod = null;
        this.mDepositFiatScreen.showMissingPaymentMethod();
    }

    private void getDefaultDepositPaymentMethod(List<com.coinbase.v2.models.paymentMethods.Data> paymentMethodList) {
        if (paymentMethodList == null) {
            showMissingPaymentMethod();
            return;
        }
        this.mValidPaymentMethods.clear();
        for (com.coinbase.v2.models.paymentMethods.Data paymentMethod : paymentMethodList) {
            if (PaymentMethodUtils.isValidDeposit(paymentMethod)) {
                this.mValidPaymentMethods.add(paymentMethod);
            }
        }
        updatePaymentMethod((com.coinbase.v2.models.paymentMethods.Data) this.mValidPaymentMethods.get(0));
    }

    void onSubscribeToNonSupportedRegionButtonClicked() {
        this.mDepositFiatRouter.routeToSupportBlog();
    }

    private void trackEvent(String event, String... params) {
        List<String> paramsList = new ArrayList();
        if (params != null) {
            paramsList.addAll(Arrays.asList(params));
        }
        paramsList.add("currency");
        paramsList.add(this.mAccount.getCurrency().getCode());
        this.mMixpanelTracking.trackEvent(event, (String[]) paramsList.toArray(new String[0]));
    }
}
