package com.coinbase.android.wbl;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.wbl.AvailableBalance.Builder;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.wbl.PendingHold;
import com.coinbase.api.internal.models.wbl.PendingHolds;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import com.coinbase.v2.models.price.Prices;
import com.google.gson.GsonBuilder;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;

@ControllerScope
public class AvailableBalanceCalculator {
    private static final String AVAILABLE_BALANCE = "available_balance";
    private final AccountsUpdatedConnector mAccountsUpdatedConnector;
    private final List<PendingHold> mAdditionalPendingHolds = new ArrayList();
    private final Scheduler mBackgroundScheduler;
    private final Logger mLogger = LoggerFactory.getLogger(AvailableBalanceCalculator.class);
    private final LoginManager mLoginManager;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final HashMap<String, Money> mSpotPriceMap = new HashMap();
    private final SyncAccountsTask mSyncAccountsTask;

    @Inject
    public AvailableBalanceCalculator(SyncAccountsTask syncAccountsTask, LoginManager loginManager, AccountsUpdatedConnector accountsUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil, Func0<List<PendingHold>> additionalPendingHolds, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mSyncAccountsTask = syncAccountsTask;
        this.mLoginManager = loginManager;
        this.mAccountsUpdatedConnector = accountsUpdatedConnector;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mAdditionalPendingHolds.addAll((Collection) additionalPendingHolds.call());
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public Observable<AvailableBalance> get() {
        return Observable.combineLatest(this.mLoginManager.getClient().getHoldBalancesRx().onBackpressureLatest(), this.mAccountsUpdatedConnector.get().onBackpressureLatest(), this.mLoginManager.getClient().getSpotPricesRx(this.mLoginManager.getCurrencyUnit().getCurrencyCode(), new HashMap()), AvailableBalanceCalculator$$Lambda$1.lambdaFactory$()).map(AvailableBalanceCalculator$$Lambda$2.lambdaFactory$(this)).onBackpressureLatest().subscribeOn(this.mBackgroundScheduler).doOnSubscribe(AvailableBalanceCalculator$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ AvailableBalance lambda$get$1(AvailableBalanceCalculator this_, Pair pair) {
        if (pair.first == null || ((Pair) pair.first).first == null || pair.second == null || ((Pair) pair.second).first == null || ((Response) ((Pair) pair.second).first).body() == null || ((Prices) ((Response) ((Pair) pair.second).first).body()).getData() == null || ((Prices) ((Response) ((Pair) pair.second).first).body()).getData().isEmpty()) {
            this_.mLogger.error("Empty response either from accounts or from available balance");
            return null;
        }
        Response<PendingHolds> pendingHoldsResponse = ((Pair) pair.first).first;
        List<Data> accountList = this_.mLoginManager.getAccounts();
        if (accountList == null || accountList.isEmpty()) {
            this_.mLogger.error("Account list is empty");
            return null;
        }
        try {
            Money zero = Money.zero(CurrencyUnit.getInstance(this_.mLoginManager.getCurrencyUnit().getCode()));
            this_.mSpotPriceMap.clear();
            this_.mSpotPriceMap.putAll(this_.getSpotPriceMap(((Prices) ((Response) ((Pair) pair.second).first).body()).getData(), zero));
            Money accountsBalanceTotal = zero;
            Map<String, Money> accountBalances = new HashMap();
            Map<String, Money> cryptoAccountBalances = new HashMap();
            for (Data account : accountList) {
                Money accountBalance = this_.getAccountNativeBalance(account, this_.mSpotPriceMap, zero);
                Money cryptoBalance = this_.getAccountCryptoBalance(account, zero);
                accountsBalanceTotal = accountsBalanceTotal.plus(accountBalance);
                accountBalances.put(account.getId(), accountBalance);
                cryptoAccountBalances.put(account.getId(), cryptoBalance);
            }
            Money holdAmountMoneyTotal = zero;
            List<PendingHold> pendingHolds = new ArrayList();
            if (!(pendingHoldsResponse == null || pendingHoldsResponse.body() == null || ((PendingHolds) pendingHoldsResponse.body()).getData() == null || ((PendingHolds) pendingHoldsResponse.body()).getData().getPendingHolds() == null || ((PendingHolds) pendingHoldsResponse.body()).getData().getPendingHolds().isEmpty())) {
                pendingHolds.addAll(((PendingHolds) pendingHoldsResponse.body()).getData().getPendingHolds());
            }
            pendingHolds.addAll(this_.mAdditionalPendingHolds);
            for (PendingHold pendingHold : pendingHolds) {
                Money holdAmount = this_.mMoneyFormatterUtil.moneyFromValue(pendingHold.getAmount().getCurrency(), pendingHold.getAmount().getAmount());
                if (holdAmount == null) {
                    holdAmount = zero;
                }
                holdAmountMoneyTotal = holdAmountMoneyTotal.plus(holdAmount);
            }
            Money availableMoneyTotal = accountsBalanceTotal.minus(holdAmountMoneyTotal);
            Builder pendingHolds2 = AvailableBalance.builder().setPendingHolds(pendingHolds);
            if (!availableMoneyTotal.isLessThan(zero)) {
                zero = availableMoneyTotal;
            }
            return pendingHolds2.setTotalAvailableBalance(zero).setTotalPortfolioBalance(accountsBalanceTotal).setTotalHoldBalance(holdAmountMoneyTotal).setAccountBalances(accountBalances).setAccountCryptoBalances(cryptoAccountBalances).build();
        } catch (IllegalCurrencyException e) {
            this_.mLogger.error("Illegal currency exception", e);
            return null;
        }
    }

    public AvailableBalance getCached(Bundle args) {
        String availableBalanceStr = args.getString(AVAILABLE_BALANCE);
        if (TextUtils.isEmpty(availableBalanceStr)) {
            return null;
        }
        return loadAvailableBalanceFromString(availableBalanceStr);
    }

    public void cache(Bundle args, AvailableBalance data) {
        args.putString(AVAILABLE_BALANCE, new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) data));
    }

    private Money getAccountNativeBalance(Data account, Map<String, Money> spotPriceMap, Money zero) {
        Money money;
        if (account.getType() == Type.FIAT) {
            money = AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil);
            if (!TextUtils.equals(account.getCurrency().getCode(), this.mLoginManager.getCurrencyUnit().getCode())) {
                if (spotPriceMap.containsKey(account.getCurrency().getCode())) {
                    money = money.convertedTo(this.mLoginManager.getCurrencyUnit(), ((Money) spotPriceMap.get(account.getCurrency().getCode())).getAmount(), RoundingMode.HALF_EVEN);
                } else {
                    this.mLogger.error("Couldn't get spot price for [" + account.getCurrency().getCode() + "]");
                    money = zero;
                }
            }
        } else {
            money = AccountUtils.getAccountNativeBalanceMoney(account, this.mMoneyFormatterUtil);
        }
        if (money == null) {
            return zero;
        }
        return money;
    }

    private Money getAccountCryptoBalance(Data account, Money zero) {
        if (account.getType() == Type.FIAT) {
            return zero;
        }
        Money money = AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil);
        if (money != null) {
            return money;
        }
        return zero;
    }

    private AvailableBalance loadAvailableBalanceFromString(String availableBalanceStr) {
        return (AvailableBalance) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(availableBalanceStr, AvailableBalance.class);
    }

    public Money getNativeAvailableBalance(AvailableBalance availableBalance, Data account) {
        String accountId = account.getId();
        Map<String, Money> accountBalances = availableBalance.getAccountBalances();
        if (accountBalances == null || accountBalances.isEmpty() || TextUtils.isEmpty(accountId)) {
            return null;
        }
        return (Money) accountBalances.get(accountId);
    }

    private HashMap<String, Money> getSpotPriceMap(List<com.coinbase.v2.models.price.Data> prices, Money zero) {
        HashMap<String, Money> spotPriceMap = new HashMap();
        for (com.coinbase.v2.models.price.Data price : prices) {
            Money spotPrice = this.mMoneyFormatterUtil.moneyFromValue(price.getCurrency(), price.getAmount());
            if (spotPrice == null) {
                spotPrice = zero;
            }
            spotPriceMap.put(price.getCurrency(), spotPrice);
        }
        return spotPriceMap;
    }
}
