package com.coinbase.android.transfers;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import com.coinbase.android.ui.NumericKeypadConnector.NumericKeypadButton;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.AvailableBalance;
import com.coinbase.android.wbl.AvailableBalanceAppBarPresenter;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.AvailableBalanceCalculator;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class SendPresenter {
    private static final String CACHED_ACCOUNT = "cached_account";
    private static final String CACHED_ENTERED_SECONDARY_VALUE = "cached_entered_secondary_value";
    private static final String CACHED_ENTERED_VALUE = "cached_entered_value";
    private static final String CACHED_PRIMARY_AMOUNT_CURRENCY_UNIT = "cached_entered_primary_amount_currency_unit";
    private static final String CACHED_SECONDARY_AMOUNT_CURRENCY_UNIT = "cached_entered_secondary_amount_currency_unit";
    public static final String SELECTED_ACCOUNT = "selected_account";
    private AvailableBalance mAvailableBalance;
    private final AvailableBalanceAppBarPresenter mAvailableBalanceAppBarPresenter;
    private final AvailableBalanceAppBarScreen mAvailableBalanceAppBarScreen;
    private final AvailableBalanceCalculator mAvailableBalanceCalculator;
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private String mEnteredAmountValue = "";
    private boolean mIsSendMax = false;
    private final KeypadAmountFormatter mKeypadAmountFormatter;
    private final KeypadAmountValidator mKeypadAmountValidator;
    private final Logger mLogger = LoggerFactory.getLogger(SendPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final NumericKeypadConnector mNumericKeypadConnector;
    private Data mPrimaryAccount;
    private BigMoney mPrimaryAmount;
    private CurrencyUnit mPrimaryCurrencyUnit;
    private final SendScreen mScreen;
    private BigMoney mSecondaryAmount;
    private CurrencyUnit mSecondaryCurrencyUnit;
    private final SendRouter mSendRouter;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final WithdrawalBasedLimitsErrorControllerRouter mWithdrawalBasedLimitsErrorControllerRouter;

    @Inject
    SendPresenter(Application application, LoginManager loginManager, SendScreen screen, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, SendRouter sendRouter, WithdrawalBasedLimitsErrorControllerRouter withdrawalBasedLimitsErrorControllerRouter, NumericKeypadConnector numericKeypadConnector, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, SnackBarWrapper snackBarWrapper, CurrenciesUpdatedConnector currenciesUpdatedConnector, AvailableBalanceCalculator availableBalanceCalculator, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mAvailableBalanceAppBarScreen = availableBalanceAppBarScreen;
        this.mAvailableBalanceAppBarPresenter = availableBalanceAppBarPresenter;
        this.mSendRouter = sendRouter;
        this.mWithdrawalBasedLimitsErrorControllerRouter = withdrawalBasedLimitsErrorControllerRouter;
        this.mNumericKeypadConnector = numericKeypadConnector;
        this.mKeypadAmountFormatter = keypadAmountFormatter;
        this.mKeypadAmountValidator = keypadAmountValidator;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mAvailableBalanceCalculator = availableBalanceCalculator;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
    }

    void onViewCreated(Bundle args) {
        if (args != null) {
            String account = args.getString("selected_account");
            if (TextUtils.isEmpty(account)) {
                this.mLogger.error("Missing SELECTED_ACCOUNT, can't perform send");
                return;
            }
            this.mPrimaryAccount = (Data) new Gson().fromJson(account, Data.class);
            if (this.mPrimaryAccount == null) {
                this.mLogger.error("Missing SELECTED_ACCOUNT, can't perform send");
                return;
            }
            toggleSendMaxVisibility();
            this.mSubscription.add(this.mAvailableBalanceAppBarPresenter.onViewCreated(args, this.mPrimaryAccount).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SendPresenter$$Lambda$1.lambdaFactory$(this), SendPresenter$$Lambda$2.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$onViewCreated$0(SendPresenter this_, AvailableBalance availableBalance) {
        this_.mAvailableBalance = availableBalance;
        this_.showAvailableBalance();
    }

    static /* synthetic */ void lambda$onViewCreated$1(SendPresenter this_, Throwable t) {
        this_.mLogger.error("Couldn't get available balance", t);
        this_.mSnackBarWrapper.showFailure(t);
    }

    void onShow() {
        showDefaultTitle();
        if (this.mScreen.getArgs() == null || !loadUserEntriesFromBundle()) {
            initAmountAndCurrency();
        }
        this.mSubscription.add(this.mNumericKeypadConnector.getNumericSubject().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(SendPresenter$$Lambda$3.lambdaFactory$(this), SendPresenter$$Lambda$4.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonClickedSubject().onBackpressureLatest().filter(SendPresenter$$Lambda$5.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SendPresenter$$Lambda$6.lambdaFactory$(this), SendPresenter$$Lambda$7.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonClickedSubject().onBackpressureLatest().filter(SendPresenter$$Lambda$8.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SendPresenter$$Lambda$9.lambdaFactory$(this), SendPresenter$$Lambda$10.lambdaFactory$(this)));
        this.mSubscription.add(this.mNumericKeypadConnector.getButtonLongClickedSubject().onBackpressureLatest().filter(SendPresenter$$Lambda$11.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(SendPresenter$$Lambda$12.lambdaFactory$(this), SendPresenter$$Lambda$13.lambdaFactory$(this)));
        showAvailableBalance();
    }

    static /* synthetic */ Boolean lambda$onShow$4(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.LEFT;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$7(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.RIGHT;
        return Boolean.valueOf(z);
    }

    static /* synthetic */ Boolean lambda$onShow$10(Pair pair) {
        boolean z = pair != null && pair.first == NumericKeypadButton.RIGHT;
        return Boolean.valueOf(z);
    }

    void onHide() {
        this.mSubscription.clear();
        cacheUserEntriesInBundle();
    }

    void onSwapAmountClicked() {
        String str;
        swapAmounts();
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_SWITCHED_CURRENCY;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mPrimaryAccount == null) {
            str = "";
        } else {
            str = this.mPrimaryAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    private void swapAmounts() {
        CurrencyUnit tmpCurrency = this.mPrimaryCurrencyUnit;
        this.mPrimaryCurrencyUnit = this.mSecondaryCurrencyUnit;
        this.mSecondaryCurrencyUnit = tmpCurrency;
        BigMoney tmpAmount = this.mPrimaryAmount;
        this.mPrimaryAmount = this.mSecondaryAmount;
        this.mSecondaryAmount = tmpAmount;
        if (this.mPrimaryAmount == null) {
            this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, 0.0d);
        }
        if (this.mSecondaryAmount == null) {
            this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
        }
        if (this.mPrimaryAmount.isZero()) {
            this.mEnteredAmountValue = "";
        } else {
            this.mEnteredAmountValue = this.mPrimaryAmount.getAmount().toString();
        }
        setPrimaryAmountText();
        showAvailableBalance();
        cacheUserEntriesInBundle();
    }

    void onContinueButtonClicked() {
        String str;
        continueSend();
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_TAPPED_CONTINUE;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mPrimaryAccount == null) {
            str = "";
        } else {
            str = this.mPrimaryAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    void onSendAllClicked() {
        this.mIsSendMax = true;
        fillMaxBalance(BigDecimal.ONE);
    }

    void onSendHalfClicked() {
        fillMaxBalance(BigDecimal.valueOf(0.5d));
    }

    void onSendFourthClicked() {
        fillMaxBalance(BigDecimal.valueOf(0.25d));
    }

    private void initAmountAndCurrency() {
        this.mPrimaryCurrencyUnit = this.mLoginManager.getCurrencyUnit();
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, 0.0d);
        this.mSecondaryCurrencyUnit = CurrencyUnit.getInstance(this.mPrimaryAccount.getCurrency().getCode());
        this.mSecondaryAmount = BigMoney.of(this.mSecondaryCurrencyUnit, 0.0d);
        setPrimaryAmountText();
    }

    private void continueSend() {
        cacheUserEntriesInBundle();
        BigMoney enteredAmount = isNativePrimary() ? this.mSecondaryAmount : this.mPrimaryAmount;
        Pair<Money, Boolean> availableBalance = this.mAvailableBalanceAppBarPresenter.getAvailableCryptoBalance();
        if (availableBalance == null || availableBalance.first == null) {
            if (Utils.isConnectedOrConnecting(this.mContext)) {
                this.mSnackBarWrapper.show((int) R.string.error_occurred_try_again);
            } else {
                this.mSnackBarWrapper.show((int) R.string.no_internet);
            }
        } else if (((Money) availableBalance.first).isLessThan(enteredAmount)) {
            if (!((Boolean) availableBalance.second).booleanValue() || ((Money) this.mAvailableBalance.getAccountCryptoBalances().get(this.mPrimaryAccount.getId())).isLessThan(enteredAmount)) {
                String str;
                this.mSendRouter.routeToError(this.mContext.getString(R.string.transfer_sending_more_than_user_account_balance));
                MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
                String str2 = MixpanelTracking.EVENT_SEND_VIEWED_AMOUNT_ERROR;
                String[] strArr = new String[2];
                strArr[0] = "currency";
                if (this.mPrimaryAccount == null) {
                    str = "";
                } else {
                    str = this.mPrimaryAccount.getCurrency().getCode();
                }
                strArr[1] = str;
                mixpanelTracking.trackEvent(str2, strArr);
                return;
            }
            routeWithdrawalBasedLimitsClientError(this.mAvailableBalanceAppBarPresenter.getBalanceToShow(isNativePrimary()));
        } else if (enteredAmount.isPositive()) {
            this.mSendRouter.routeShowTransferSend(isNativePrimary() ? this.mSecondaryAmount : this.mPrimaryAmount, this.mPrimaryAccount, this.mIsSendMax);
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_AMOUNT_ENTERED, new String[0]);
        } else {
            this.mSendRouter.routeToError(this.mContext.getString(R.string.transfer_amt_empty));
        }
    }

    private void onKeystrokeEntered(KeypadType type, char digit) {
        if (this.mKeypadAmountValidator.validateAmount(type, digit, this.mEnteredAmountValue, this.mPrimaryCurrencyUnit.getDefaultFractionDigits())) {
            String newValue = this.mKeypadAmountFormatter.appendKeystroke(type, digit, this.mEnteredAmountValue);
            if (newValue == null) {
                this.mSnackBarWrapper.showGenericErrorTryAgain();
                return;
            } else {
                updatePrimaryAmount(newValue);
                return;
            }
        }
        this.mScreen.showInvalidKeystroke();
    }

    private void updatePrimaryAmount(String newValue) {
        this.mEnteredAmountValue = newValue;
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(parseEnteredText(this.mEnteredAmountValue)));
        updateSecondaryAmountFromLocalExchange(this.mPrimaryAmount, this.mSecondaryCurrencyUnit);
        setPrimaryAmountText();
    }

    private void setPrimaryAmountText() {
        this.mScreen.updatePrimaryAmountText(this.mKeypadAmountFormatter.getFormattedPrimaryAmount(this.mPrimaryCurrencyUnit, this.mPrimaryAmount, this.mEnteredAmountValue, R.color.cerulean_disabled));
    }

    private double parseEnteredText(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0d;
        }
    }

    private void cacheUserEntriesInBundle() {
        Bundle args = this.mScreen.getArgs();
        if (args != null) {
            Gson gson = new Gson();
            args.putString(CACHED_ENTERED_VALUE, this.mEnteredAmountValue);
            args.putString(CACHED_PRIMARY_AMOUNT_CURRENCY_UNIT, gson.toJson(this.mPrimaryCurrencyUnit));
            args.putString(CACHED_SECONDARY_AMOUNT_CURRENCY_UNIT, gson.toJson(this.mSecondaryCurrencyUnit));
            args.putSerializable(CACHED_ENTERED_SECONDARY_VALUE, this.mSecondaryAmount);
            args.putString(CACHED_ACCOUNT, gson.toJson(this.mPrimaryAccount));
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
        this.mSecondaryAmount = (BigMoney) args.getSerializable(CACHED_ENTERED_SECONDARY_VALUE);
        if (cachedAmountValue == null || cachedPrimaryCurrency == null || cachedSecondaryCurrency == null || this.mSecondaryAmount == null) {
            return false;
        }
        this.mEnteredAmountValue = cachedAmountValue;
        this.mPrimaryCurrencyUnit = cachedPrimaryCurrency;
        this.mSecondaryCurrencyUnit = cachedSecondaryCurrency;
        this.mPrimaryAmount = BigMoney.of(this.mPrimaryCurrencyUnit, BigDecimal.valueOf(parseEnteredText(this.mEnteredAmountValue)));
        this.mAvailableBalance = this.mAvailableBalanceCalculator.getCached(args);
        setPrimaryAmountText();
        return true;
    }

    private boolean isNativePrimary() {
        if (this.mPrimaryAmount == null || this.mLoginManager.getCurrencyUnit() == null) {
            return false;
        }
        return TextUtils.equals(this.mPrimaryCurrencyUnit.getCode(), this.mLoginManager.getCurrencyUnit().getCode());
    }

    private void fillMaxBalance(BigDecimal ratio) {
        if (isNativePrimary()) {
            swapAmounts();
        }
        Pair<BigMoney, BigMoney> balanceToShow = this.mAvailableBalanceAppBarPresenter.getNativeAndCryptoBalance(ratio);
        if (balanceToShow != null && balanceToShow.first != null) {
            this.mPrimaryAmount = (BigMoney) balanceToShow.first;
            this.mSecondaryAmount = (BigMoney) balanceToShow.second;
            this.mEnteredAmountValue = this.mPrimaryAmount.getAmount().toString();
            setPrimaryAmountText();
            trackSendMax(ratio);
        }
    }

    private void trackSendMax(BigDecimal ratio) {
        String str;
        String event = "";
        if (ratio.equals(BigDecimal.ONE)) {
            event = MixpanelTracking.EVENT_SEND_TAPPED_SEND_MAX;
        } else if (ratio.equals(BigDecimal.valueOf(0.5d))) {
            event = MixpanelTracking.EVENT_SEND_TAPPED_SEND_HALF;
        } else if (ratio.equals(BigDecimal.valueOf(0.25d))) {
            event = MixpanelTracking.EVENT_SEND_TAPPED_SEND_QUARTER;
        }
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String[] strArr = new String[2];
        strArr[0] = "currency";
        if (this.mPrimaryAccount == null) {
            str = "";
        } else {
            str = this.mPrimaryAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        mixpanelTracking.trackEvent(event, strArr);
    }

    private void updateSecondaryAmountFromLocalExchange(BigMoney primaryAmount, CurrencyUnit toUnit) {
        if (isNativePrimary()) {
            this.mSecondaryAmount = this.mAvailableBalanceAppBarPresenter.getConversionFromNative(primaryAmount, toUnit);
        } else {
            this.mSecondaryAmount = this.mAvailableBalanceAppBarPresenter.getConversionFromCrypto(primaryAmount, toUnit);
        }
    }

    private void showDefaultTitle() {
        String title = this.mContext.getString(R.string.send);
        if (!(this.mPrimaryAccount == null || this.mPrimaryAccount.getCurrency() == null || this.mCurrenciesUpdatedConnector.getCurrencyByCode(this.mPrimaryAccount.getCurrency().getCode()) == null)) {
            title = String.format(this.mContext.getString(R.string.send_currency_name_title), new Object[]{this.mCurrenciesUpdatedConnector.getCurrencyByCode(this.mPrimaryAccount.getCurrency().getCode()).getName()});
        }
        this.mScreen.updateTitle(title);
    }

    private void showAvailableBalance() {
        if (!this.mAvailableBalanceAppBarPresenter.showAvailableBalance(isNativePrimary())) {
            showDefaultTitle();
        }
    }

    private void routeWithdrawalBasedLimitsClientError(Pair<String, Boolean> balanceToShow) {
        if (balanceToShow == null || TextUtils.isEmpty((CharSequence) balanceToShow.first)) {
            this.mLogger.error("Balance to show is empty in routing wbl error, should never happen.");
            this.mSnackBarWrapper.showGenericErrorTryAgain();
            return;
        }
        String str;
        String title = this.mContext.getString(R.string.wbl_client_error_title);
        this.mWithdrawalBasedLimitsErrorControllerRouter.routeWithdrawalBasedLimitsError(this.mContext.getString(R.string.wbl_client_error_message, new Object[]{balanceToShow.first}), title, this.mContext.getString(R.string.wbl_client_error_learn_more_button), this.mContext.getString(R.string.wbl_client_error_dismiss_button));
        MixpanelTracking mixpanelTracking = this.mMixpanelTracking;
        String str2 = MixpanelTracking.EVENT_SEND_VIEWED_LIMIT_EXCEEDED_ERROR;
        String[] strArr = new String[4];
        strArr[0] = "currency";
        if (this.mPrimaryAccount == null) {
            str = "";
        } else {
            str = this.mPrimaryAccount.getCurrency().getCode();
        }
        strArr[1] = str;
        strArr[2] = "error_type";
        strArr[3] = MixpanelTracking.PROPERTY_VALUE_SEND_ERROR_TYPE_CLIENT;
        mixpanelTracking.trackEvent(str2, strArr);
    }

    private void toggleSendMaxVisibility() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        if (displayMetrics == null || displayMetrics.heightPixels < Constants.DISPLAY_HEIGHT_PX_MIN) {
            this.mScreen.hideSendMaxButtons();
        } else {
            this.mScreen.showSendMaxButtons();
        }
    }
}
