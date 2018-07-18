package com.coinbase.android.buysell;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.coinbase.ApiConstants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.phone.VerifyPhoneHandler.VerifyPhoneCaller;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import com.coinbase.android.ui.NumericKeypadConnector.NumericKeypadButton;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.AvailableBalance;
import com.coinbase.android.wbl.AvailableBalanceAppBarPresenter;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import com.coinbase.v2.models.transfers.PaymentMethod;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.transfers.TransferError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
class SellPresenter {
    private static final String CACHED_ACCOUNT = "cached_account";
    private static final String CACHED_ENTERED_VALUE = "cached_entered_value";
    private static final String CACHED_PAYMENT_METHOD = "cached_payment_method";
    private static final String CACHED_PAYMENT_METHOD_LIST = "cached_payment_method_list";
    private static final String CACHED_PRIMARY_AMOUNT_CURRENCY_UNIT = "cached_entered_primary_amount_currency_unit";
    private static final String CACHED_SECONDARY_AMOUNT_CURRENCY_UNIT = "cached_entered_secondary_amount_currency_unit";
    private static final int DISPLAY_HEIGHT_PX_MIN = 1794;
    private static final String INVALID_PAYMENT_METHOD_ERROR = "invalid_payment_method";
    private static final String NO_PAYMENT_METHOD_ERROR = "no_payment_method";
    private AvailableBalance mAvailableBalance;
    private final AvailableBalanceAppBarPresenter mAvailableBalanceAppBarPresenter;
    private final AvailableBalanceAppBarScreen mAvailableBalanceAppBarScreen;
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private String mCurrencyCode;
    private String mEnteredAmountValue = "";
    private String mErrorMessage;
    private final GetPaymentMethodsTaskRx mGetPaymentMethodsTask;
    private final GoToSettingsConnector mGoToSettingsConnector;
    private boolean mHidePaymentMethodView;
    private boolean mIsAmountTracked;
    private final KeypadAmountFormatter mKeypadAmountFormatter;
    private final KeypadAmountValidator mKeypadAmountValidator;
    private final LinkedAccountConnector mLinkedAccountConnector;
    private final Logger mLogger = LoggerFactory.getLogger(SellPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final NumericKeypadConnector mNumericKeypadConnector;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private final PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private Data mPrimaryAccount;
    private BigMoney mPrimaryAmount;
    private CurrencyUnit mPrimaryCurrencyUnit;
    private com.coinbase.v2.models.paymentMethods.Data mPrimaryPaymentMethod;
    private final CompositeSubscription mQuickStartSubscription = new CompositeSubscription();
    private final QuickstartManager mQuickstartManager;
    private final SellRouter mRouter;
    private final SellScreen mScreen;
    private BigMoney mSecondaryAmount;
    private CurrencyUnit mSecondaryCurrencyUnit;
    private final CompositeSubscription mSellSubscription = new CompositeSubscription();
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransferUtils mTransferUtils;
    private List<com.coinbase.v2.models.paymentMethods.Data> mValidPaymentMethods = new ArrayList();
    private final WithdrawalBasedLimitsApiErrorHandler mWithdrawalBasedLimitsApiErrorHandler;

    @Inject
    SellPresenter(Application application, LoginManager loginManager, SellScreen screen, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, NumericKeypadConnector numericKeypadConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, LinkedAccountConnector linkedAccountConnector, GoToSettingsConnector goToSettingsConnector, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickstartManager quickstartManager, GetPaymentMethodsTaskRx getPaymentMethodsTaskRx, SellRouter sellRouter, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MoneyFormatterUtil moneyFormatterUtil, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mAvailableBalanceAppBarScreen = availableBalanceAppBarScreen;
        this.mAvailableBalanceAppBarPresenter = availableBalanceAppBarPresenter;
        this.mNumericKeypadConnector = numericKeypadConnector;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mLinkedAccountConnector = linkedAccountConnector;
        this.mGoToSettingsConnector = goToSettingsConnector;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        this.mQuickstartManager = quickstartManager;
        this.mGetPaymentMethodsTask = getPaymentMethodsTaskRx;
        this.mRouter = sellRouter;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mTransferUtils = transferUtils;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mKeypadAmountFormatter = keypadAmountFormatter;
        this.mKeypadAmountValidator = keypadAmountValidator;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mWithdrawalBasedLimitsApiErrorHandler = withdrawalBasedLimitsApiErrorHandler;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        this.mSubscription.add(this.mNumericKeypadConnector.getNumericSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$1.lambdaFactory$(this), SellPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonClickedSubject().onBackpressureLatest().filter(SellPresenter$$Lambda$3.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$4.lambdaFactory$(this), SellPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonClickedSubject().onBackpressureLatest().filter(SellPresenter$$Lambda$6.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$7.lambdaFactory$(this), SellPresenter$$Lambda$8.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonLongClickedSubject().onBackpressureLatest().filter(SellPresenter$$Lambda$9.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$10.lambdaFactory$(this), SellPresenter$$Lambda$11.lambdaFactory$(this)));
        this.mSubscription.add(this.mLinkedAccountConnector.getLinkedAccountClickedSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$12.lambdaFactory$(this), SellPresenter$$Lambda$13.lambdaFactory$(this)));
        this.mSubscription.add(this.mLinkedAccountConnector.getMissingAccountClickedSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$14.lambdaFactory$(this), SellPresenter$$Lambda$15.lambdaFactory$(this)));
        this.mSubscription.add(this.mLinkedAccountConnector.getPaymentMethodSelectedSubject().filter(SellPresenter$$Lambda$16.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$17.lambdaFactory$(this), SellPresenter$$Lambda$18.lambdaFactory$(this)));
        this.mSubscription.add(this.mGoToSettingsConnector.get().filter(SellPresenter$$Lambda$19.lambdaFactory$()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$20.lambdaFactory$(this), SellPresenter$$Lambda$21.lambdaFactory$(this)));
        this.mSubscription.add(this.mPhoneNumbersUpdatedConnector.get().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$22.lambdaFactory$(this)));
        Bundle args = this.mScreen.getArgs();
        if (args != null) {
            this.mHidePaymentMethodView = args.getBoolean("HIDE_PAYMENT_METHOD", false);
            if (loadUserEntriesFromBundle()) {
                showSellContent();
                return;
            }
        }
        setCurrencyCode(args);
        setPrimaryAccount(args);
        initAmountAndCurrency();
        loadQuickstartItemsFromCache();
        getPolicyRestrictions();
    }

    static /* synthetic */ Boolean lambda$onShow$2(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.LEFT;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$5(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.RIGHT;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$8(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.RIGHT;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ void lambda$onShow$11(SellPresenter this_, Void v) {
        this_.trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_PAYMENT_METHOD, new String[0]);
        this_.cacheUserEntriesInBundle();
        this_.mRouter.routeToLinkedAccountsPicker(this_.mValidPaymentMethods, this_.mPrimaryPaymentMethod);
    }

    static /* synthetic */ Boolean lambda$onShow$15(Pair v) {
        boolean z = (v == null || ((ClassConsumableEvent) v.first).consumeIfNotConsumed(SellPresenter.class)) ? false : true;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ void lambda$onShow$16(SellPresenter this_, Pair pair) {
        this_.setUserSelectedPaymentMethod((com.coinbase.v2.models.paymentMethods.Data) pair.second);
        this_.updatePrimaryAmountAndFetchQuote();
    }

    static /* synthetic */ Boolean lambda$onShow$18(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(SellPresenter.class));
    }

    void onHide() {
        cacheUserEntriesInBundle();
        this.mSubscription.clear();
        this.mSellSubscription.clear();
        this.mQuickStartSubscription.clear();
    }

    void onSwapAmountClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_SWITCH, new String[0]);
        CurrencyUnit tmpCurrency = this.mPrimaryCurrencyUnit;
        this.mPrimaryCurrencyUnit = this.mSecondaryCurrencyUnit;
        this.mSecondaryCurrencyUnit = tmpCurrency;
        if (this.mPrimaryAmount == null) {
            this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, 0.0d);
        }
        if (this.mSecondaryAmount == null) {
            this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
        }
        BigMoney tmpAmount = this.mPrimaryAmount;
        this.mPrimaryAmount = this.mSecondaryAmount;
        this.mSecondaryAmount = tmpAmount;
        if (this.mPrimaryAmount.isZero()) {
            this.mEnteredAmountValue = "";
        } else {
            this.mEnteredAmountValue = this.mPrimaryAmount.getAmount().toString();
        }
        setPrimaryAmountText();
        setSecondaryCurrencyCode();
        showAvailableBalance();
    }

    void onPreviewButtonClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_PREVIEW, new String[0]);
        previewSell();
    }

    void onSellMaxClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_SELL_MAX, new String[0]);
        updateCustomAmount(BigDecimal.ONE);
    }

    void onSellHalfClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_SELL_HALF, new String[0]);
        updateCustomAmount(BigDecimal.valueOf(0.5d));
    }

    void onSellFourthClicked() {
        trackEvent(MixpanelTracking.EVENT_SELL_TAPPED_SELL_QUARTER, new String[0]);
        updateCustomAmount(BigDecimal.valueOf(0.25d));
    }

    void performActionForQuickstartItem(QuickstartItem item, Activity activity, VerifyPhoneCaller mVerifyPhoneCaller) {
        MixpanelTracking.logTransactionEvent(item);
        this.mQuickstartManager.performActionForItem(item, activity, mVerifyPhoneCaller);
    }

    void onSubscribeToNonSupportedRegionButtonClicked() {
        this.mRouter.routeToSupportBlog();
    }

    Pair<String, String> getFormattedPaymentMethodNameAndNumberDisplay(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return this.mPaymentMethodUtils.getFormattedNameAndNumberDisplay(paymentMethod);
    }

    int getResourceForType(Type type) {
        return this.mPaymentMethodUtils.getResourceForType(type);
    }

    private void initAmountAndCurrency() {
        if (this.mHidePaymentMethodView || this.mPrimaryPaymentMethod == null) {
            this.mPrimaryCurrencyUnit = this.mLoginManager.getCurrencyUnit();
        } else {
            this.mPrimaryCurrencyUnit = CurrencyUnit.getInstance(this.mPrimaryPaymentMethod.getCurrency());
        }
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, 0.0d);
        this.mSecondaryCurrencyUnit = CurrencyUnit.getInstance(this.mCurrencyCode);
        this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
        setPrimaryAmountText();
        setSecondaryCurrencyCode();
    }

    private void setCurrencyCode(Bundle args) {
        String currencyCode = null;
        if (args != null) {
            currencyCode = args.getString("selected_currency_code");
        }
        if (currencyCode == null) {
            currencyCode = "BTC";
            args.putString("selected_currency_code", currencyCode);
        }
        this.mCurrencyCode = currencyCode;
    }

    private void setPrimaryAccount(Bundle args) {
        Data primaryAccount;
        if (!(args == null || args.getString("selected_account") == null)) {
            primaryAccount = (Data) new Gson().fromJson(args.getString("selected_account"), Data.class);
            if (primaryAccount != null) {
                setPrimaryAccount(primaryAccount);
                return;
            }
        }
        List<Data> accountList = this.mLoginManager.getAccounts();
        if (accountList == null) {
            this.mLogger.error("Empty account list");
            return;
        }
        List<Data> matchingAccountList = new ArrayList();
        for (Data account : accountList) {
            if (account.getType() == Data.Type.WALLET && TextUtils.equals(account.getCurrency().getCode().toUpperCase(), this.mCurrencyCode.toUpperCase())) {
                matchingAccountList.add(account);
            }
        }
        if (matchingAccountList.isEmpty()) {
            this.mLogger.error("Account not found for " + this.mCurrencyCode);
            return;
        }
        primaryAccount = (Data) matchingAccountList.get(0);
        for (Data account2 : matchingAccountList) {
            if (account2.getPrimary() != null && account2.getPrimary().booleanValue()) {
                primaryAccount = account2;
                break;
            }
        }
        setPrimaryAccount(primaryAccount);
    }

    private void setPrimaryAccount(Data primaryAccount) {
        if (primaryAccount != null) {
            Bundle args = this.mScreen.getArgs();
            if (args == null) {
                args = new Bundle();
            }
            this.mPrimaryAccount = primaryAccount;
            args.putString("selected_account", new Gson().toJson(this.mPrimaryAccount));
            setPageTitle();
            this.mSubscription.add(this.mAvailableBalanceAppBarPresenter.onViewCreated(args, this.mPrimaryAccount).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$23.lambdaFactory$(this), SellPresenter$$Lambda$24.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$setPrimaryAccount$22(SellPresenter this_, AvailableBalance availableBalance) {
        this_.mAvailableBalance = availableBalance;
        this_.showAvailableBalance();
    }

    static /* synthetic */ void lambda$setPrimaryAccount$23(SellPresenter this_, Throwable t) {
        this_.mLogger.error("Couldn't get available balance", t);
        this_.mSnackBarWrapper.showFailure(t);
    }

    private void showAvailableBalance() {
        boolean z = true;
        AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter = this.mAvailableBalanceAppBarPresenter;
        boolean z2 = this.mPrimaryPaymentMethod == null ? false : this.mPrimaryPaymentMethod.getType() == Type.FIAT_ACCOUNT;
        if (isPrimaryCurrencyCrypto()) {
            z = false;
        }
        availableBalanceAppBarPresenter.showAvailableBalance(z2, z);
    }

    private void setPageTitle() {
        if (this.mPrimaryAccount != null && this.mPrimaryAccount.getCurrency() != null) {
            this.mScreen.updateTitle(this.mPrimaryAccount.getCurrency().getName());
        }
    }

    private void onPhoneNumbersUpdated(Object event) {
        getPolicyRestrictions();
        Utils.processPhoneNumbersUpdatedEvent(event, this.mContext);
    }

    private void getPolicyRestrictions() {
        this.mScreen.showProgressOverlay();
        this.mQuickStartSubscription.clear();
        this.mQuickStartSubscription.add(this.mQuickstartManager.updateQuickstartItems(QuickstartManager.Type.SELLS.toString()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$25.lambdaFactory$(this), SellPresenter$$Lambda$26.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getPolicyRestrictions$24(SellPresenter this_, Void v) {
        this_.mScreen.hideProgressOverlay();
        this_.checkAccountSetupStatus();
    }

    static /* synthetic */ void lambda$getPolicyRestrictions$25(SellPresenter this_, Throwable t) {
        this_.mLogger.error("Error from updateQuickstartItems");
        this_.mScreen.hideProgressOverlay();
        this_.checkAccountSetupStatus();
    }

    private void loadQuickstartItemsFromCache() {
        if (!this.mQuickstartManager.getCachedQuickstartItems(true).isEmpty()) {
            checkAccountSetupStatus();
        }
    }

    private void checkAccountSetupStatus() {
        List<QuickstartItem> quickstartItems = this.mQuickstartManager.getCachedQuickstartItems(true);
        if (quickstartItems.contains(QuickstartItem.REGION_UNSUPPORTED)) {
            this.mScreen.showRegionUnsupported(new Locale("", this.mLoginManager.getActiveUserCountryCode()).getDisplayCountry());
        } else if (quickstartItems.isEmpty()) {
            showSellContent();
            trackSellViewed();
            getPaymentMethods();
            setPrimaryAccount(this.mScreen.getArgs());
        } else {
            this.mScreen.showQuickstartItems(quickstartItems);
        }
    }

    private void showSellContent() {
        boolean showCustomSellButtons = false;
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        if (displayMetrics != null && displayMetrics.heightPixels >= 1794) {
            showCustomSellButtons = true;
        }
        this.mScreen.showSellContent(showCustomSellButtons, this.mHidePaymentMethodView);
    }

    private void getPaymentMethods() {
        this.mSubscription.add(this.mGetPaymentMethodsTask.getPaymentMethods().first().observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$27.lambdaFactory$(this), SellPresenter$$Lambda$28.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getPaymentMethods$26(SellPresenter this_, Pair pair) {
        Response<PaymentMethods> response = pair.first;
        if (response.isSuccessful()) {
            List<com.coinbase.v2.models.paymentMethods.Data> paymentMethods = ((PaymentMethods) response.body()).getData();
            if (paymentMethods == null || paymentMethods.isEmpty()) {
                this_.showMissingPaymentMethod();
                return;
            } else {
                this_.getDefaultSellPaymentMethod(paymentMethods);
                return;
            }
        }
        this_.showMissingPaymentMethod();
    }

    private void showMissingPaymentMethod() {
        this.mPrimaryPaymentMethod = null;
        this.mScreen.showMissingPaymentMethod();
    }

    private void getDefaultSellPaymentMethod(List<com.coinbase.v2.models.paymentMethods.Data> paymentMethodList) {
        if (paymentMethodList == null) {
            showMissingPaymentMethod();
            return;
        }
        this.mValidPaymentMethods = new ArrayList();
        List<com.coinbase.v2.models.paymentMethods.Data> fiatPaymentMethodList = new ArrayList();
        com.coinbase.v2.models.paymentMethods.Data primaryPaymentMethod = null;
        for (com.coinbase.v2.models.paymentMethods.Data paymentMethod : paymentMethodList) {
            if (paymentMethod.getAllowSell().booleanValue()) {
                if (paymentMethod.getType() == Type.FIAT_ACCOUNT) {
                    fiatPaymentMethodList.add(paymentMethod);
                }
                if (paymentMethod.getPrimarySell().booleanValue()) {
                    primaryPaymentMethod = paymentMethod;
                }
                if (paymentMethod.getVerified().booleanValue()) {
                    this.mValidPaymentMethods.add(paymentMethod);
                }
            }
        }
        if (!fiatPaymentMethodList.isEmpty()) {
            if (fiatPaymentMethodList.size() != 1) {
                CurrencyUnit nativeCurrencyUnit = this.mLoginManager.getCurrencyUnit();
                for (com.coinbase.v2.models.paymentMethods.Data fiat : fiatPaymentMethodList) {
                    if (fiat.getCurrency().toUpperCase().equals(nativeCurrencyUnit.getCurrencyCode().toUpperCase())) {
                        primaryPaymentMethod = fiat;
                        break;
                    }
                }
            }
            primaryPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) fiatPaymentMethodList.get(0);
        }
        if (primaryPaymentMethod == null && !this.mValidPaymentMethods.isEmpty()) {
            primaryPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) this.mValidPaymentMethods.get(0);
        }
        setUserSelectedPaymentMethod(primaryPaymentMethod);
    }

    private void updateCustomAmount(BigDecimal ratio) {
        if (ratio != null) {
            Pair<Money, Boolean> availableBalance = this.mAvailableBalanceAppBarPresenter.getAvailableCryptoBalance();
            if (availableBalance != null && availableBalance.first != null && !((Money) availableBalance.first).isZero()) {
                if (!isPrimaryCurrencyCrypto()) {
                    onSwapAmountClicked();
                }
                Pair<BigMoney, BigMoney> balanceToShow = this.mAvailableBalanceAppBarPresenter.getNativeAndCryptoBalance(ratio);
                if (balanceToShow != null && balanceToShow.first != null) {
                    this.mPrimaryAmount = (BigMoney) balanceToShow.first;
                    this.mSecondaryAmount = (BigMoney) balanceToShow.second;
                    this.mEnteredAmountValue = this.mPrimaryAmount.getAmount().toPlainString();
                    updatePrimaryAmountAndFetchQuote();
                }
            }
        }
    }

    private void performQuote() {
        this.mErrorMessage = null;
        this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
        Observable<Pair<Response<Transfer>, Retrofit>> sellBitcoinObservable = this.mLoginManager.getClient().sellBitcoinRx(this.mPrimaryAccount.getId(), getSellParams());
        this.mSellSubscription.clear();
        this.mSellSubscription.add(sellBitcoinObservable.observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$29.lambdaFactory$(this), SellPresenter$$Lambda$30.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$performQuote$28(SellPresenter this_, Pair pair) {
        Response<Transfer> response = pair.first;
        if (response.isSuccessful()) {
            com.coinbase.v2.models.transfers.Data transferData = ((Transfer) response.body()).getData();
            if (this_.mHidePaymentMethodView) {
                this_.setApiSelectedPaymentMethod(transferData.getPaymentMethod());
            }
            this_.mErrorMessage = null;
            this_.showTransferAmount(transferData);
            return;
        }
        this_.handleQuoteError(response.errorBody());
    }

    static /* synthetic */ void lambda$performQuote$29(SellPresenter this_, Throwable t) {
        this_.mErrorMessage = Utils.getMessage(this_.mContext, t);
        this_.showTransferAmount(null);
    }

    private void handleQuoteError(ResponseBody errorBody) {
        if (errorBody == null) {
            handleGenericError();
            return;
        }
        try {
            String errorBodyString = Utils.getErrorBody(errorBody);
            if (this.mWithdrawalBasedLimitsApiErrorHandler.handleApiError(errorBodyString, ApiConstants.SELLS)) {
                String str;
                MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
                String str2 = MixpanelTracking.EVENT_SELL_VIEWED_LIMIT_EXCEEDED_ERROR;
                String[] strArr = new String[2];
                strArr[0] = "currency";
                if (this.mPrimaryAccount == null) {
                    str = "";
                } else {
                    str = this.mPrimaryAccount.getCurrency().getCode();
                }
                strArr[1] = str;
                mixpanelTracking.trackEvent(str2, strArr);
                showTransferAmount(null);
                return;
            }
            TransferError transferError = (TransferError) new Gson().fromJson(errorBodyString, TransferError.class);
            ErrorBody error = this.mTransferUtils.getFirstError(transferError);
            if (error == null) {
                handleGenericError();
                return;
            }
            showTransferAmount(transferError.getData());
            if (!this.mHidePaymentMethodView || (!error.getId().equals(INVALID_PAYMENT_METHOD_ERROR) && !error.getId().equals(NO_PAYMENT_METHOD_ERROR))) {
                this.mErrorMessage = error.getMessage();
            }
        } catch (JsonSyntaxException e) {
            handleGenericError();
        }
    }

    private void handleGenericError() {
        this.mErrorMessage = this.mContext.getString(R.string.an_error_occurred);
        showTransferAmount(null);
    }

    private void previewSell() {
        cacheUserEntriesInBundle();
        if (this.mValidPaymentMethods.isEmpty()) {
            this.mRouter.routeToGoToSettingsDialog();
            return;
        }
        String error = getSellError();
        if (error != null) {
            showErrorMessage(error);
            return;
        }
        this.mScreen.showProgressOverlay();
        Observable<Pair<Response<Transfer>, Retrofit>> sellBitcoinObservable = this.mLoginManager.getClient().sellBitcoinRx(this.mPrimaryAccount.getId(), getSellParams());
        this.mSellSubscription.clear();
        this.mSellSubscription.add(sellBitcoinObservable.observeOn(this.mMainScheduler).subscribe(SellPresenter$$Lambda$31.lambdaFactory$(this), SellPresenter$$Lambda$32.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$previewSell$30(SellPresenter this_, Pair pair) {
        this_.mScreen.hideProgressOverlay();
        Response response = pair.first;
        if (response.isSuccessful()) {
            Transfer transfer = (Transfer) response.body();
            com.coinbase.v2.models.transfers.Data transferData = transfer.getData();
            if (this_.mHidePaymentMethodView) {
                this_.setApiSelectedPaymentMethod(transferData.getPaymentMethod());
            }
            this_.showTransferAmount(transfer.getData());
            this_.mRouter.routeToSellConfirmation(transfer, this_.mPrimaryAccount, this_.mPrimaryPaymentMethod, this_.mValidPaymentMethods);
            return;
        }
        this_.showErrorMessage(Utils.getErrorMessage(response));
    }

    static /* synthetic */ void lambda$previewSell$31(SellPresenter this_, Throwable t) {
        this_.mScreen.hideProgressOverlay();
        this_.showErrorMessage(Utils.getMessage(this_.mContext, t));
    }

    private String getSellError() {
        if (this.mErrorMessage != null) {
            return this.mErrorMessage;
        }
        if (TextUtils.isEmpty(this.mEnteredAmountValue)) {
            return this.mContext.getString(R.string.entered_amt_empty);
        }
        if (this.mPrimaryAccount == null) {
            return this.mContext.getString(R.string.account_unavailable_error_occurred);
        }
        return null;
    }

    private void showErrorMessage(String errorMessage) {
        trackEvent(MixpanelTracking.EVENT_SELL_VIEWED_ERROR, "error", errorMessage);
        this.mRouter.routeToError(null, errorMessage);
    }

    private void showTransferAmount(com.coinbase.v2.models.transfers.Data transferData) {
        if (transferData == null) {
            this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
            return;
        }
        String secondaryAmount = "";
        if (!isPrimaryCurrencyCrypto()) {
            secondaryAmount = this.mTransferUtils.getCryptoCurrencyAmount(transferData);
        } else if (transferData.getTotal() != null) {
            secondaryAmount = transferData.getTotal().getAmount();
        }
        this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, this.mKeypadAmountFormatter.parseEnteredText(secondaryAmount));
    }

    private HashMap<String, Object> getSellParams() {
        HashMap<String, Object> params = new HashMap();
        params.put("currency", this.mPrimaryCurrencyUnit.getCode());
        params.put("commit", Boolean.valueOf(false));
        if (!this.mHidePaymentMethodView) {
            if (this.mPrimaryPaymentMethod == null) {
                params.put(ApiConstants.QUOTE, Boolean.valueOf(true));
            } else {
                params.put("payment_method", this.mPrimaryPaymentMethod.getId());
            }
        }
        String amount = this.mPrimaryAmount.getAmount().stripTrailingZeros().toString();
        if (isPrimaryCurrencyCrypto()) {
            params.put("amount", amount);
        } else {
            params.put(ApiConstants.TOTAL, amount);
        }
        return params;
    }

    private boolean isPrimaryCurrencyCrypto() {
        if (this.mPrimaryCurrencyUnit == null) {
            return false;
        }
        return this.mCurrenciesUpdatedConnector.isValidCurrency(this.mPrimaryCurrencyUnit.getCode());
    }

    private void setUserSelectedPaymentMethod(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        if (!this.mHidePaymentMethodView) {
            this.mPrimaryPaymentMethod = paymentMethod;
            if (this.mPrimaryPaymentMethod == null) {
                this.mScreen.showMissingPaymentMethod();
                return;
            }
            switch (paymentMethod.getType()) {
                case FIAT_ACCOUNT:
                    this.mScreen.showFiatPaymentMethod(paymentMethod, paymentMethod.getName());
                    break;
                default:
                    this.mScreen.showPaymentMethod(paymentMethod);
                    break;
            }
            if (isPrimaryCurrencyCrypto()) {
                this.mSecondaryCurrencyUnit = CurrencyUnit.getInstance(this.mPrimaryPaymentMethod.getCurrency());
            } else {
                this.mPrimaryCurrencyUnit = CurrencyUnit.getInstance(this.mPrimaryPaymentMethod.getCurrency());
            }
            showAvailableBalance();
        }
    }

    private void setApiSelectedPaymentMethod(PaymentMethod transferPaymentMethod) {
        if (transferPaymentMethod != null && this.mValidPaymentMethods != null) {
            for (com.coinbase.v2.models.paymentMethods.Data paymentMethod : this.mValidPaymentMethods) {
                if (transferPaymentMethod.getId().equals(paymentMethod.getId())) {
                    this.mPrimaryPaymentMethod = paymentMethod;
                    return;
                }
            }
        }
    }

    private void onKeystrokeEntered(KeypadType type, char digit) {
        if (this.mKeypadAmountValidator.validateAmount(type, digit, this.mEnteredAmountValue, this.mPrimaryCurrencyUnit.getDefaultFractionDigits())) {
            String newValue = this.mKeypadAmountFormatter.appendKeystroke(type, digit, this.mEnteredAmountValue);
            if (newValue == null) {
                this.mSnackBarWrapper.showGenericErrorTryAgain();
                return;
            }
            if (!this.mIsAmountTracked) {
                this.mIsAmountTracked = true;
                String str = MixpanelTracking.EVENT_SELL_TAPPED_AMOUNT;
                String[] strArr = new String[2];
                strArr[0] = "type";
                strArr[1] = isPrimaryCurrencyCrypto() ? MixpanelTracking.VALUE_BUYSELL_TYPE_CRYPTO : MixpanelTracking.VALUE_BUYSELL_TYPE_NATIVE;
                trackEvent(str, strArr);
            }
            this.mEnteredAmountValue = newValue;
            updatePrimaryAmountAndFetchQuote();
            return;
        }
        this.mScreen.showInvalidKeystroke();
    }

    private void updatePrimaryAmountAndFetchQuote() {
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(this.mKeypadAmountFormatter.parseEnteredText(this.mEnteredAmountValue)));
        setPrimaryAmountText();
        performQuote();
    }

    private void setPrimaryAmountText() {
        this.mScreen.updatePrimaryAmountText(this.mKeypadAmountFormatter.getFormattedPrimaryAmount(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, this.mEnteredAmountValue, R.color.cerulean_disabled));
    }

    private void setSecondaryCurrencyCode() {
        this.mScreen.updateSecondaryCurrencyCode(this.mSecondaryCurrencyUnit.getCurrencyCode());
    }

    private void cacheUserEntriesInBundle() {
        Bundle args = this.mScreen.getArgs();
        if (args != null) {
            Gson gson = new Gson();
            args.putString(CACHED_ENTERED_VALUE, this.mEnteredAmountValue);
            args.putString(CACHED_PRIMARY_AMOUNT_CURRENCY_UNIT, gson.toJson(this.mPrimaryCurrencyUnit));
            args.putString(CACHED_SECONDARY_AMOUNT_CURRENCY_UNIT, gson.toJson(this.mSecondaryCurrencyUnit));
            args.putString(CACHED_ACCOUNT, gson.toJson(this.mPrimaryAccount));
            args.putString(CACHED_PAYMENT_METHOD, gson.toJson(this.mPrimaryPaymentMethod));
            args.putString(CACHED_PAYMENT_METHOD_LIST, gson.toJson(this.mValidPaymentMethods));
        }
    }

    private boolean loadUserEntriesFromBundle() {
        Bundle args = this.mScreen.getArgs();
        if (args == null) {
            return false;
        }
        Gson gson = new Gson();
        String cachedAmountValue = args.getString(CACHED_ENTERED_VALUE);
        CurrencyUnit cachedPrimaryCurrency = (CurrencyUnit) gson.fromJson(args.getString(CACHED_PRIMARY_AMOUNT_CURRENCY_UNIT), CurrencyUnit.class);
        CurrencyUnit cachedSecondaryCurrency = (CurrencyUnit) gson.fromJson(args.getString(CACHED_SECONDARY_AMOUNT_CURRENCY_UNIT), CurrencyUnit.class);
        Data cachedAccount = (Data) gson.fromJson(args.getString(CACHED_ACCOUNT), Data.class);
        com.coinbase.v2.models.paymentMethods.Data cachedPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) gson.fromJson(args.getString(CACHED_PAYMENT_METHOD), com.coinbase.v2.models.paymentMethods.Data.class);
        List<com.coinbase.v2.models.paymentMethods.Data> cachedPaymentMethodList = (List) gson.fromJson(args.getString(CACHED_PAYMENT_METHOD_LIST), new TypeToken<ArrayList<com.coinbase.v2.models.paymentMethods.Data>>() {
        }.getType());
        if (cachedAmountValue == null || cachedPrimaryCurrency == null || cachedSecondaryCurrency == null || cachedAccount == null || cachedPaymentMethod == null || cachedPaymentMethodList == null) {
            return false;
        }
        this.mCurrencyCode = args.getString("selected_currency_code");
        if (this.mCurrencyCode == null) {
            this.mLogger.error("Missing CURRENCY_CODE, can't perform sell");
            return false;
        }
        this.mEnteredAmountValue = cachedAmountValue;
        this.mPrimaryCurrencyUnit = cachedPrimaryCurrency;
        this.mSecondaryCurrencyUnit = cachedSecondaryCurrency;
        setSecondaryCurrencyCode();
        setPrimaryAccount(cachedAccount);
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(this.mKeypadAmountFormatter.parseEnteredText(this.mEnteredAmountValue)));
        this.mValidPaymentMethods = cachedPaymentMethodList;
        setUserSelectedPaymentMethod(cachedPaymentMethod);
        updatePrimaryAmountAndFetchQuote();
        return true;
    }

    private void trackSellViewed() {
        String pageSource = "";
        if (this.mScreen.getArgs() != null) {
            pageSource = this.mScreen.getArgs().getString("PAGE_SOURCE");
        }
        trackEvent(MixpanelTracking.EVENT_SELL_VIEWED, MixpanelTracking.PROPERTY_BUYSELL_PREVIOUS_SCREEN, pageSource);
    }

    private void trackEvent(String event, String... params) {
        List<String> paramsList = new ArrayList();
        if (params != null) {
            paramsList.addAll(Arrays.asList(params));
        }
        paramsList.add("currency");
        paramsList.add(this.mCurrencyCode);
        this.mMixpanelTracking.trackEvent(event, (String[]) paramsList.toArray(new String[paramsList.size()]));
    }
}
