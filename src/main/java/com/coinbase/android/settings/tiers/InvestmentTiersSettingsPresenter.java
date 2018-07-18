package com.coinbase.android.settings.tiers;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.tiers.BuySellLimit;
import com.coinbase.api.internal.models.tiers.Currency;
import com.coinbase.api.internal.models.tiers.Data;
import com.coinbase.api.internal.models.tiers.Level;
import com.coinbase.api.internal.models.tiers.LevelFeature;
import com.coinbase.api.internal.models.tiers.LifetimeLimit;
import com.coinbase.api.internal.models.tiers.LimitsAndFeatures;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class InvestmentTiersSettingsPresenter {
    private static final String LAST_TARGET_TIER = "last_target_tier";
    private static final int MIN_PROGRESS = 1;
    private final AdapterDelegatesManager<List<Object>> mBuyDepositAdapterDelegate = new AdapterDelegatesManager();
    private List<Object> mBuyDepositLimitsAndWires = new LinkedList();
    private int mCurrentTierLevel = 0;
    private final Logger mLogger = LoggerFactory.getLogger(InvestmentTiersSettingsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private Set<Options> mMoneyFormatOption = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private List<Requirement> mNextRequirements = new LinkedList();
    private int mNextTier = -1;
    private final InvestmentTiersSettingsRouter mRouter;
    private final InvestmentTiersSettingsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private List<Level> mTiers = new LinkedList();
    private final AdapterDelegatesManager<List<Level>> mTiersAdapterDelegate = new AdapterDelegatesManager();

    @Inject
    public InvestmentTiersSettingsPresenter(InvestmentTiersSettingsScreen screen, InvestmentTiersSettingsRouter router, LoginManager loginManager, SnackBarWrapper snackBarWrapper, MoneyFormatterUtil moneyFormatterUtil, MixpanelTracking mixpanelTracking, Application application, AutoResizeTextViewAdjustor autoResizeTextViewAdjustor, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mLoginManager = loginManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
        this.mBuyDepositAdapterDelegate.addDelegate(new DepositWithdrawLimitsAdapterDelegate(application, moneyFormatterUtil, autoResizeTextViewAdjustor));
        this.mBuyDepositAdapterDelegate.addDelegate(new LimitsAndFeaturesAdapterDelegate(application, autoResizeTextViewAdjustor));
        this.mTiersAdapterDelegate.addDelegate(new TiersAdapterDelegate(application, this));
    }

    void onShow(Bundle args) {
        this.mSubscription.add(this.mLoginManager.getClient().getTiersRx().first().observeOn(this.mMainScheduler).subscribe(InvestmentTiersSettingsPresenter$$Lambda$1.lambdaFactory$(this, args), InvestmentTiersSettingsPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onShow$0(InvestmentTiersSettingsPresenter this_, Bundle args, Pair pair) {
        Response<Tiers> response = pair.first;
        Retrofit retrofit = pair.second;
        if (!this_.mScreen.isShown()) {
            return;
        }
        if (response.isSuccessful()) {
            Data data = ((Tiers) response.body()).getData();
            if (data == null || data.getVerificationLevels() == null) {
                this_.mSnackBarWrapper.showGenericError();
                return;
            }
            List<Level> tiers = data.getVerificationLevels().getLevels();
            if (tiers == null || tiers.isEmpty()) {
                this_.mSnackBarWrapper.showGenericError();
                return;
            }
            this_.mNextTier = this_.getNextTier(tiers);
            this_.mCurrentTierLevel = this_.mNextTier >= 0 ? this_.mNextTier : tiers.size();
            if (this_.mNextTier >= 0) {
                List<Requirement> requirements = ((Level) tiers.get(this_.mNextTier)).getRequirements();
                this_.mNextRequirements.clear();
                if (!(requirements == null || requirements.isEmpty())) {
                    this_.mNextRequirements.addAll(requirements);
                }
            }
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_INVESTMENT_TIERS_VIEWED, MixpanelTracking.EVENT_INVESTMENT_TIERS_CURRENT_TIER_LEVEL_PROPERTY, String.valueOf(this_.mCurrentTierLevel));
            if (this_.getCurrentTier(tiers) == null) {
                if (data.getAccountSetup() == null || TextUtils.isEmpty(data.getAccountSetup().getButtonText())) {
                    this_.mSnackBarWrapper.showGenericError();
                    this_.mLogger.error("Empty account setup returned, should never happen");
                    return;
                }
                this_.mScreen.showCompleteAccountSetupHeader(data.getAccountSetup().getButtonText());
                this_.mScreen.hideLimitsHeader();
                this_.mScreen.hideLimitsHeaderCallToActionButton();
            } else if (data.getAccountDetails() == null) {
                this_.mSnackBarWrapper.showGenericError();
                this_.mLogger.error("Empty account details returned, should never happen");
                return;
            } else {
                this_.mScreen.showLimitsHeader();
                this_.mScreen.hideCompleteAccountSetupHeader();
                if (this_.mNextTier < 0) {
                    this_.mScreen.hideLimitsHeaderCallToActionButton();
                } else if (TextUtils.isEmpty(data.getAccountDetails().getUpgradeButtonText())) {
                    this_.mSnackBarWrapper.showGenericError();
                    this_.mLogger.error("Empty account details button text returned, should never happen");
                    return;
                } else {
                    this_.mScreen.showLimitsHeaderCallToActionButton(data.getAccountDetails().getUpgradeButtonText());
                }
                if (TextUtils.isEmpty(data.getAccountDetails().getTitle())) {
                    this_.mSnackBarWrapper.showGenericError();
                    this_.mLogger.error("Empty account details title returned, should never happen");
                    return;
                }
                this_.mScreen.showLevelTitle(data.getAccountDetails().getTitle());
                LifetimeLimit lifetimeLimit = null;
                List<BuySellLimit> buyLimits = null;
                LevelFeature sends = null;
                LevelFeature receives = null;
                String headerTitle = null;
                if (!(data.getAccountDetails() == null || data.getAccountDetails().getLimitsAndFeatures() == null)) {
                    LimitsAndFeatures limitsAndFeatures = data.getAccountDetails().getLimitsAndFeatures();
                    lifetimeLimit = limitsAndFeatures.getLifetimeLimit();
                    buyLimits = limitsAndFeatures.getBuyDepositLimits();
                    sends = limitsAndFeatures.getSends();
                    receives = limitsAndFeatures.getReceives();
                    headerTitle = limitsAndFeatures.getTitle();
                }
                if (lifetimeLimit != null) {
                    if (this_.isValidLimit(lifetimeLimit.getRemaining(), lifetimeLimit.getAmount(), lifetimeLimit.getCurrency()) && !TextUtils.isEmpty(lifetimeLimit.getName())) {
                        String amountWithCurrency;
                        String remainingWithCurrency;
                        int lifetimeLimitProgress = this_.calculateLimitProgress(lifetimeLimit.getAmount(), lifetimeLimit.getRemaining());
                        Money lifetimeAmount = this_.mMoneyFormatterUtil.moneyFromValue(lifetimeLimit.getCurrency().getCode(), lifetimeLimit.getAmount());
                        if (lifetimeAmount == null) {
                            amountWithCurrency = "";
                        } else {
                            amountWithCurrency = this_.mMoneyFormatterUtil.formatMoney(lifetimeAmount, this_.mMoneyFormatOption);
                        }
                        Money remainingAmount = this_.mMoneyFormatterUtil.moneyFromValue(lifetimeLimit.getCurrency().getCode(), lifetimeLimit.getRemaining());
                        if (remainingAmount == null) {
                            remainingWithCurrency = "";
                        } else {
                            remainingWithCurrency = this_.mMoneyFormatterUtil.formatMoney(remainingAmount, this_.mMoneyFormatOption);
                        }
                        this_.mScreen.showLifetimeLimit(lifetimeLimit.getName(), amountWithCurrency, remainingWithCurrency, this_.getMinProgress(lifetimeLimitProgress));
                        String description = lifetimeLimit.getDescription();
                        if (!TextUtils.isEmpty(description)) {
                            this_.mScreen.showLifetimeLimitDescription(description);
                        }
                        this_.mBuyDepositLimitsAndWires.clear();
                        if (!(buyLimits == null || buyLimits.isEmpty())) {
                            this_.mBuyDepositLimitsAndWires.addAll(buyLimits);
                        }
                        if (sends != null) {
                            this_.mBuyDepositLimitsAndWires.add(new Pair(sends, Type.SEND));
                        }
                        if (receives != null) {
                            this_.mBuyDepositLimitsAndWires.add(new Pair(receives, Type.RECEIVE));
                        }
                        if (this_.mBuyDepositLimitsAndWires.isEmpty()) {
                            if (!TextUtils.isEmpty(headerTitle)) {
                                this_.mScreen.setBuyLimitsHeaderText(headerTitle);
                            }
                            this_.mScreen.notifyBuyLimitsAdapter();
                        } else {
                            this_.mScreen.hideBuyDepositLimits();
                        }
                    }
                }
                this_.mScreen.hideLifetimeLimit();
                this_.mBuyDepositLimitsAndWires.clear();
                this_.mBuyDepositLimitsAndWires.addAll(buyLimits);
                if (sends != null) {
                    this_.mBuyDepositLimitsAndWires.add(new Pair(sends, Type.SEND));
                }
                if (receives != null) {
                    this_.mBuyDepositLimitsAndWires.add(new Pair(receives, Type.RECEIVE));
                }
                if (this_.mBuyDepositLimitsAndWires.isEmpty()) {
                    if (TextUtils.isEmpty(headerTitle)) {
                        this_.mScreen.setBuyLimitsHeaderText(headerTitle);
                    }
                    this_.mScreen.notifyBuyLimitsAdapter();
                } else {
                    this_.mScreen.hideBuyDepositLimits();
                }
            }
            this_.mTiers.clear();
            this_.mTiers.addAll(this_.filterValidTiers(tiers));
            if (this_.mTiers == null || this_.mTiers.isEmpty()) {
                this_.mScreen.hideTiers();
            } else {
                if (!TextUtils.isEmpty(data.getTitle())) {
                    this_.mScreen.setTiersHeader(data.getTitle());
                }
                this_.mScreen.notifyTiersAdapter();
            }
            if (args.containsKey(LAST_TARGET_TIER) && ((args.getInt(LAST_TARGET_TIER) < this_.mNextTier || (this_.mNextTier < 0 && args.getInt(LAST_TARGET_TIER) != this_.mNextTier)) && !this_.mScreen.isTierSuccessShown())) {
                this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_INVESTMENT_TIERS_VIEWED_SUCCESS, MixpanelTracking.EVENT_INVESTMENT_TIERS_CURRENT_TIER_LEVEL_PROPERTY, String.valueOf(args.getInt(LAST_TARGET_TIER)));
                this_.mScreen.showTierSuccess();
            }
            args.putInt(LAST_TARGET_TIER, this_.mNextTier);
            return;
        }
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$onShow$1(InvestmentTiersSettingsPresenter this_, Throwable t) {
        if (this_.mScreen.isShown()) {
            this_.mSnackBarWrapper.showFailure(t);
        }
        this_.mLogger.error("Failure getting investment tiers", t);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    int getBuyDepositItemCount() {
        return this.mBuyDepositLimitsAndWires.size();
    }

    int getBuyDepositItemViewType(int position) {
        return this.mBuyDepositAdapterDelegate.getItemViewType(this.mBuyDepositLimitsAndWires, position);
    }

    ViewHolder onCreateBuyDepositViewHolder(ViewGroup parent, int viewType) {
        return this.mBuyDepositAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindBuyDepositViewHolder(ViewHolder holder, int position) {
        this.mBuyDepositAdapterDelegate.onBindViewHolder(this.mBuyDepositLimitsAndWires, position, holder);
    }

    int getTiersItemCount() {
        return this.mTiers.size();
    }

    int getTiersItemViewType(int position) {
        return this.mTiersAdapterDelegate.getItemViewType(this.mTiers, position);
    }

    ViewHolder onCreateTiersViewHolder(ViewGroup parent, int viewType) {
        return this.mTiersAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindTiersViewHolder(ViewHolder holder, int position) {
        this.mTiersAdapterDelegate.onBindViewHolder(this.mTiers, position, holder);
    }

    void onCompleteAccountSetupClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_INVESTMENT_TIERS_TAPPED_COMPLETE_ACCOUNT, MixpanelTracking.EVENT_INVESTMENT_TIERS_CURRENT_TIER_LEVEL_PROPERTY, String.valueOf(this.mCurrentTierLevel));
        routeRequirements();
    }

    void onIncreaseLimitsClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_INVESTMENT_TIERS_TAPPED_INCREASE_LIMITS, MixpanelTracking.EVENT_INVESTMENT_TIERS_CURRENT_TIER_LEVEL_PROPERTY, String.valueOf(this.mCurrentTierLevel));
        routeRequirements();
    }

    boolean onBackPressed() {
        if (!this.mScreen.isTierSuccessShown()) {
            return false;
        }
        this.mScreen.hideTierSuccess();
        return true;
    }

    void onTiersSuccessButtonClicked() {
        if (this.mScreen.isTierSuccessShown()) {
            this.mScreen.hideTierSuccess();
        }
    }

    private void routeRequirements() {
        if (this.mNextRequirements.isEmpty()) {
            this.mSnackBarWrapper.showGenericError();
        } else {
            this.mRouter.routeRequirements(this.mNextRequirements, this.mNextTier);
        }
    }

    private Level getCurrentTier(List<Level> tiers) {
        if (tiers.isEmpty()) {
            return null;
        }
        Level attainedLevel = null;
        int i = 0;
        for (Level tier : tiers) {
            if (tier.getStatus() == null || tier.getStatus().getComplete() == null) {
                return null;
            }
            if (!tier.getStatus().getComplete().booleanValue()) {
                return attainedLevel;
            }
            attainedLevel = tier;
            i++;
        }
        return attainedLevel;
    }

    private int getNextTier(List<Level> tiers) {
        if (tiers.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < tiers.size(); i++) {
            Level tier = (Level) tiers.get(i);
            if (tier.getStatus() == null || tier.getStatus().getComplete() == null) {
                return -1;
            }
            if (!tier.getStatus().getComplete().booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public int getCurrentTier() {
        return this.mCurrentTierLevel;
    }

    private int calculateLimitProgress(String amountStr, String remainingStr) {
        try {
            double amount = Double.valueOf(amountStr).doubleValue();
            return (int) (100.0d * ((amount - Double.valueOf(remainingStr).doubleValue()) / amount));
        } catch (Exception e) {
            this.mLogger.error("Exception parsing string amounts", e);
            return 0;
        }
    }

    private List<Level> filterValidTiers(List<Level> tiers) {
        List<Level> filteredTiers = new LinkedList();
        if (tiers != null) {
            for (Level tier : tiers) {
                if (isValidTier(tier)) {
                    filteredTiers.add(tier);
                }
            }
        }
        return filteredTiers;
    }

    private boolean isValidTier(Level tier) {
        return (TextUtils.isEmpty(tier.getName()) || (tier.getStatus() != null ? tier.getStatus().getComplete() : null) == null) ? false : true;
    }

    private boolean isValidLimit(String remaining, String amount, Currency currency) {
        return (TextUtils.isEmpty(remaining) || TextUtils.isEmpty(amount) || currency == null || TextUtils.isEmpty(currency.getCode())) ? false : true;
    }

    private int getMinProgress(int progress) {
        return progress > 1 ? progress : 1;
    }
}
