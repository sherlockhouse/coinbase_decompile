package com.coinbase.android.wbl;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.exchangeRates.ExchangeRates;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

@ControllerScope
public class AvailableBalanceAppBarPresenter {
    private AvailableBalance mAvailableBalance;
    private final AvailableBalanceCalculator mAvailableBalanceCalculator;
    private final Context mContext;
    private final Map<String, String> mExchangeRatesFromCrypto = new HashMap();
    private final Map<String, String> mExchangeRatesFromNative = new HashMap();
    private boolean mIsWithdrawAccountWallet = false;
    private final Logger mLogger = LoggerFactory.getLogger(AvailableBalanceAppBarPresenter.class);
    private final LoginManager mLoginManager;
    private final Set<Options> mMoneyFormatOption = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
    private final Set<Options> mMoneyFormatOptionRound2Digits = EnumSet.of(Options.ROUND_2_DIGITS);
    private final Set<Options> mMoneyFormatOptionRound4Digits = EnumSet.of(Options.ROUND_4_DIGITS);
    private final Set<Options> mMoneyFormatOptionRound8Digits = EnumSet.of(Options.ROUND_8_DIGITS);
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private Data mPrimaryAccount;
    private CurrencyUnit mPrimaryCurrencyUnit;
    private final AvailableBalanceRouter mRouter;
    private final AvailableBalanceAppBarScreen mScreen;
    private BigMoney mZero;

    @Inject
    public AvailableBalanceAppBarPresenter(Application app, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceRouter router, AvailableBalanceCalculator availableBalanceCalculator, LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil) {
        this.mContext = app;
        this.mScreen = availableBalanceAppBarScreen;
        this.mRouter = router;
        this.mAvailableBalanceCalculator = availableBalanceCalculator;
        this.mLoginManager = loginManager;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public Observable<AvailableBalance> onViewCreated(Bundle args, Data account) {
        try {
            Money zero = Money.zero(CurrencyUnit.getInstance(this.mLoginManager.getCurrencyUnit().getCode()));
            if (zero != null) {
                this.mZero = zero.toBigMoney();
            }
            if (account == null || account.getCurrency() == null || TextUtils.isEmpty(account.getCurrency().getCode())) {
                return Observable.never();
            }
            this.mPrimaryAccount = account;
            this.mPrimaryCurrencyUnit = CurrencyUnit.getInstance(this.mPrimaryAccount.getCurrency().getCode());
            this.mScreen.registerOnClickListener(this);
            return Observable.combineLatest(this.mAvailableBalanceCalculator.get().onBackpressureLatest(), getExchangeRates(this.mLoginManager.getCurrencyUnit().getCode()), getExchangeRates(account.getCurrency().getCode()), AvailableBalanceAppBarPresenter$$Lambda$1.lambdaFactory$(this, args));
        } catch (IllegalCurrencyException e) {
            this.mLogger.error("Illegal currency exception", e);
            return Observable.never();
        }
    }

    private Pair<Money, Boolean> getAvailableNativeBalance(BigDecimal ratio) {
        if (this.mAvailableBalance == null) {
            return null;
        }
        Money availableAccountBalance = this.mAvailableBalanceCalculator.getNativeAvailableBalance(this.mAvailableBalance, this.mPrimaryAccount);
        Money availableBalance = this.mAvailableBalance.getTotalAvailableBalance();
        if (availableBalance == null || availableAccountBalance == null) {
            return null;
        }
        if (this.mIsWithdrawAccountWallet) {
            return new Pair(availableAccountBalance.multipliedBy(ratio, RoundingMode.HALF_DOWN), Boolean.valueOf(false));
        }
        Object multipliedBy;
        boolean availableBalanceLessThanAccountBalance = availableBalance.isLessThan(availableAccountBalance.multipliedBy(ratio, RoundingMode.HALF_DOWN));
        if (availableBalanceLessThanAccountBalance) {
            multipliedBy = availableBalance.multipliedBy(ratio, RoundingMode.HALF_DOWN);
        } else {
            multipliedBy = availableAccountBalance.multipliedBy(ratio, RoundingMode.HALF_DOWN);
        }
        return new Pair(multipliedBy, Boolean.valueOf(availableBalanceLessThanAccountBalance));
    }

    public Pair<Money, Boolean> getAvailableCryptoBalance() {
        if (this.mAvailableBalance == null) {
            return null;
        }
        Pair<Money, Boolean> availableFiatBalance = getAvailableNativeBalance(BigDecimal.ONE);
        Money cryptoAccountBalance = (Money) this.mAvailableBalance.getAccountCryptoBalances().get(this.mPrimaryAccount.getId());
        if (cryptoAccountBalance == null) {
            return null;
        }
        if (availableFiatBalance == null || !((Boolean) availableFiatBalance.second).booleanValue()) {
            return new Pair(cryptoAccountBalance, Boolean.valueOf(false));
        }
        return new Pair(getConversionFromNative(((Money) availableFiatBalance.first).toBigMoney(), this.mPrimaryCurrencyUnit).toMoney(), Boolean.valueOf(true));
    }

    public BigMoney getConversionFromNative(BigMoney amount, CurrencyUnit toUnit) {
        if (this.mExchangeRatesFromNative.isEmpty()) {
            return this.mZero;
        }
        return getConversion(amount, toUnit, this.mExchangeRatesFromNative);
    }

    public BigMoney getConversionFromCrypto(BigMoney amount, CurrencyUnit toUnit) {
        if (this.mExchangeRatesFromCrypto.isEmpty()) {
            return this.mZero;
        }
        return getConversion(amount, toUnit, this.mExchangeRatesFromCrypto);
    }

    public Pair<String, Boolean> getBalanceToShow(boolean isNativePrimary) {
        if (this.mAvailableBalance == null) {
            return null;
        }
        Pair<Money, Boolean> balanceToShow;
        CurrencyUnit currencyUnit;
        if (isNativePrimary) {
            balanceToShow = getAvailableNativeBalance(BigDecimal.ONE);
            currencyUnit = this.mLoginManager.getCurrencyUnit();
        } else {
            balanceToShow = getAvailableCryptoBalance();
            currencyUnit = this.mPrimaryCurrencyUnit;
        }
        if (balanceToShow != null) {
            return new Pair(this.mMoneyFormatterUtil.formatMoney((BigMoneyProvider) balanceToShow.first, determineMoneyFormatterOptions((Money) balanceToShow.first, currencyUnit)), balanceToShow.second);
        }
        return null;
    }

    public void showAvailableBalance(boolean withdrawToWallet, boolean isNativePrimary) {
        this.mIsWithdrawAccountWallet = withdrawToWallet;
        showAvailableBalance(isNativePrimary);
    }

    public boolean showAvailableBalance(boolean isNativePrimary) {
        if (this.mAvailableBalance == null || this.mPrimaryAccount == null || this.mExchangeRatesFromCrypto.isEmpty() || this.mExchangeRatesFromNative.isEmpty()) {
            return false;
        }
        Pair<String, Boolean> availableBalancePair = getBalanceToShow(isNativePrimary);
        if (availableBalancePair == null || TextUtils.isEmpty((CharSequence) availableBalancePair.first)) {
            this.mScreen.setAvailableBalance("");
            this.mScreen.hideAvailableBalanceClickable();
            return false;
        }
        this.mScreen.setAvailableBalance(this.mContext.getString(R.string.available_balance_header, new Object[]{availableBalancePair.first}));
        if (availableBalancePair.second == null || !((Boolean) availableBalancePair.second).booleanValue()) {
            this.mScreen.hideAvailableBalanceClickable();
        } else {
            this.mScreen.showAvailableBalanceClickable();
        }
        return true;
    }

    public Pair<BigMoney, BigMoney> getNativeAndCryptoBalance(BigDecimal ratio) {
        if (this.mAvailableBalance == null) {
            return null;
        }
        Pair<Money, Boolean> balanceToShow = getAvailableNativeBalance(ratio);
        if (balanceToShow == null) {
            return null;
        }
        BigMoney nativeAmount;
        BigMoney cryptoAmount;
        if (((Boolean) balanceToShow.second).booleanValue()) {
            nativeAmount = ((Money) balanceToShow.first).toBigMoney();
            cryptoAmount = getConversionFromNative(nativeAmount, this.mPrimaryCurrencyUnit);
        } else {
            cryptoAmount = ((Money) this.mAvailableBalance.getAccountCryptoBalances().get(this.mPrimaryAccount.getId())).multipliedBy(ratio, RoundingMode.HALF_DOWN).toBigMoney();
            nativeAmount = ((Money) this.mAvailableBalance.getAccountBalances().get(this.mPrimaryAccount.getId())).multipliedBy(ratio, RoundingMode.HALF_DOWN).toBigMoney();
        }
        return new Pair(cryptoAmount, nativeAmount);
    }

    void onAvailableBalanceClicked() {
        this.mRouter.routeShowAvailableBalance(this.mPrimaryCurrencyUnit == null ? "" : this.mPrimaryCurrencyUnit.getCurrencyCode());
    }

    private BigMoney getConversion(BigMoney amount, CurrencyUnit toUnit, Map<String, String> exchangeRates) {
        if (amount == null || toUnit == null || !exchangeRates.containsKey(toUnit.getCode())) {
            return null;
        }
        return this.mMoneyFormatterUtil.moneyFromValue(toUnit.getCode(), amount.convertedTo(toUnit, new BigDecimal((String) exchangeRates.get(toUnit.getCode()))).getAmount().toString()).toBigMoney();
    }

    private Observable<Pair<Response<ExchangeRates>, Retrofit>> getExchangeRates(String fromUnit) {
        HashMap<String, Object> param = new HashMap();
        param.put("currency", fromUnit);
        return this.mLoginManager.getClient().getExchangeRatesRx(param);
    }

    private AvailableBalance combineAvailableBalanceAndExchangeRates(AvailableBalance availableBalance, Pair<Response<ExchangeRates>, Retrofit> exchangeRatesFromNative, Pair<Response<ExchangeRates>, Retrofit> exchangeRatesFromCrypto, Bundle args) {
        this.mExchangeRatesFromNative.clear();
        if (!((Response) exchangeRatesFromNative.first).isSuccessful() || ((ExchangeRates) ((Response) exchangeRatesFromNative.first).body()).getData() == null || !((Response) exchangeRatesFromCrypto.first).isSuccessful() || ((ExchangeRates) ((Response) exchangeRatesFromCrypto.first).body()).getData() == null) {
            this.mLogger.error("Error fetching exchange rate");
        } else {
            this.mExchangeRatesFromNative.putAll(((ExchangeRates) ((Response) exchangeRatesFromNative.first).body()).getData().getRates());
            this.mExchangeRatesFromCrypto.putAll(((ExchangeRates) ((Response) exchangeRatesFromCrypto.first).body()).getData().getRates());
        }
        this.mAvailableBalanceCalculator.cache(args, availableBalance);
        this.mAvailableBalance = availableBalance;
        return availableBalance;
    }

    private Set<Options> determineMoneyFormatterOptions(Money balance, CurrencyUnit currencyUnit) {
        Set<Options> selectedMoneyFormatter = this.mMoneyFormatOption;
        if (balance.isZero() || balance.getMinorPart() != 0) {
            return getClosestRoundingMode(currencyUnit.getDefaultFractionDigits());
        }
        return selectedMoneyFormatter;
    }

    private Set<Options> getClosestRoundingMode(int digits) {
        if (digits <= 2) {
            return this.mMoneyFormatOptionRound2Digits;
        }
        if (digits <= 4) {
            return this.mMoneyFormatOptionRound4Digits;
        }
        return this.mMoneyFormatOptionRound8Digits;
    }
}
