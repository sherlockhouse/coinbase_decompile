package com.coinbase.android.wbl;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.models.wbl.PendingHold;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AvailableBalancePresenter {
    private final AvailableBalanceCalculator mAvailableBalanceCalculator;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(AvailableBalancePresenter.class);
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private Set<Options> mMoneyFormatOption = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
    private Set<Options> mMoneyFormatOptionRound2Digits = EnumSet.of(Options.ROUND_2_DIGITS);
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private List<PendingHold> mPendingHolds = new LinkedList();
    private final AdapterDelegatesManager<List<PendingHold>> mPendingHoldsAdapterDelegate = new AdapterDelegatesManager();
    private final AvailableBalanceScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public AvailableBalancePresenter(AvailableBalanceScreen screen, SnackBarWrapper snackBarWrapper, MoneyFormatterUtil moneyFormatterUtil, MixpanelTracking mixpanelTracking, Application application, AvailableBalanceCalculator availableBalanceCalculator, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMixpanelTracking = mixpanelTracking;
        this.mContext = application;
        this.mAvailableBalanceCalculator = availableBalanceCalculator;
        this.mMainScheduler = mainScheduler;
        this.mPendingHoldsAdapterDelegate.addDelegate(new PendingHoldsAdapterDelegate(application, moneyFormatterUtil));
    }

    void onShow(Bundle args) {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_AVAILABLE_BALANCE_VIEWED, new String[0]);
        AvailableBalance savedData = this.mAvailableBalanceCalculator.getCached(args);
        if (savedData != null) {
            fillScreen(savedData);
            return;
        }
        this.mScreen.showProgress();
        this.mSubscription.add(this.mAvailableBalanceCalculator.get().observeOn(this.mMainScheduler).subscribe(AvailableBalancePresenter$$Lambda$1.lambdaFactory$(this, args), AvailableBalancePresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onShow$0(AvailableBalancePresenter this_, Bundle args, AvailableBalance availableBalance) {
        if (availableBalance == null) {
            this_.mSnackBarWrapper.showGenericError();
            return;
        }
        this_.mScreen.hideProgress();
        this_.mAvailableBalanceCalculator.cache(args, availableBalance);
        this_.fillScreen(availableBalance);
    }

    static /* synthetic */ void lambda$onShow$1(AvailableBalancePresenter this_, Throwable t) {
        this_.mLogger.error("Couldn't get available balance", t);
        this_.mSnackBarWrapper.showFailure(t);
        this_.mScreen.hideProgress();
    }

    void onHide() {
        this.mSubscription.clear();
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_AVAILABLE_BALANCE_TAPPED_CLOSE, new String[0]);
    }

    int getPendingHoldsItemCount() {
        return this.mPendingHolds.size();
    }

    int getPendingHoldsItemViewType(int position) {
        return this.mPendingHoldsAdapterDelegate.getItemViewType(this.mPendingHolds, position);
    }

    ViewHolder onCreatePendingHoldsViewHolder(ViewGroup parent, int viewType) {
        return this.mPendingHoldsAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindPendingHoldsViewHolder(ViewHolder holder, int position) {
        this.mPendingHoldsAdapterDelegate.onBindViewHolder(this.mPendingHolds, position, holder);
    }

    private void fillScreen(AvailableBalance data) {
        Money totalAvailableBalance = data.getTotalAvailableBalance();
        Money totalPortfolioBalance = data.getTotalPortfolioBalance();
        Money totalPendingFunds = data.getTotalHoldBalance();
        if (totalAvailableBalance == null || totalPortfolioBalance == null || totalPendingFunds == null) {
            this.mLogger.error("Total available balance empty in available balance response");
            this.mSnackBarWrapper.showGenericError();
            return;
        }
        Set<Options> selectedMoneyFormatterOptions = determineMoneyFormatterOptions(totalAvailableBalance, totalPortfolioBalance, totalPendingFunds);
        Pair<String, Boolean> formattedTotalAvailableBalance = getFormattedAmount(totalAvailableBalance, determineMoneyFormatterOptions(totalAvailableBalance));
        Pair<String, Boolean> formattedTotalAvailableBalanceSum = getFormattedAmount(totalAvailableBalance, selectedMoneyFormatterOptions);
        Pair<String, Boolean> formattedTotalPortfolioBalance = getFormattedAmount(totalPortfolioBalance, selectedMoneyFormatterOptions);
        Pair<String, Boolean> formattedTotalPendingFunds = getFormattedAmount(totalPendingFunds, selectedMoneyFormatterOptions);
        if (TextUtils.isEmpty((CharSequence) formattedTotalAvailableBalance.first) || TextUtils.isEmpty((CharSequence) formattedTotalAvailableBalanceSum.first) || TextUtils.isEmpty((CharSequence) formattedTotalPortfolioBalance.first) || TextUtils.isEmpty((CharSequence) formattedTotalPendingFunds.first)) {
            this.mLogger.error("Total available balance empty in available balance response");
            this.mSnackBarWrapper.showGenericError();
            return;
        }
        String string;
        this.mScreen.setTotalAvailableBalance((String) formattedTotalAvailableBalance.first);
        this.mScreen.setTotalAvailableBalanceSum((String) formattedTotalAvailableBalanceSum.first);
        this.mScreen.setTotalPortfolioBalance((String) formattedTotalPortfolioBalance.first);
        AvailableBalanceScreen availableBalanceScreen = this.mScreen;
        if (((Boolean) formattedTotalPendingFunds.second).booleanValue()) {
            string = this.mContext.getString(R.string.funds_on_hold_negative_amount, new Object[]{formattedTotalPendingFunds.first});
        } else {
            string = (String) formattedTotalPendingFunds.first;
        }
        availableBalanceScreen.setTotalPendingFunds(string);
        List<PendingHold> fundsOnHold = data.getPendingHolds();
        this.mPendingHolds.clear();
        if (fundsOnHold != null && !fundsOnHold.isEmpty()) {
            this.mPendingHolds.addAll(fundsOnHold);
            this.mScreen.notifyFundsOnHold();
        }
    }

    private Pair<String, Boolean> getFormattedAmount(Money amountBigMoney, Set<Options> selectedMoneyFormatterOptions) {
        return new Pair(this.mMoneyFormatterUtil.formatMoney(amountBigMoney, selectedMoneyFormatterOptions), Boolean.valueOf(!amountBigMoney.isZero()));
    }

    private Set<Options> determineMoneyFormatterOptions(Money... balances) {
        Set<Options> selectedMoneyFormatter = this.mMoneyFormatOption;
        for (Money balance : balances) {
            if (balance.isZero() || balance.getMinorPart() != 0) {
                return this.mMoneyFormatOptionRound2Digits;
            }
        }
        return selectedMoneyFormatter;
    }
}
