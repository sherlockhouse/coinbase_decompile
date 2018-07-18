package com.coinbase.android.accounts;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.buysell.BuyRouter.BuySource;
import com.coinbase.android.buysell.SellRouter;
import com.coinbase.android.buysell.SellRouter.SellSource;
import com.coinbase.android.deposits.FiatTransactionsController;
import com.coinbase.android.event.TransferMadeEvent;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.quickstart.QuickstartManager.Type;
import com.coinbase.android.splittesting.FlavorSplitTestConstants;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.FetchAccountTask;
import com.coinbase.android.transfers.TransferMadeConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.policyRestrictions.PolicyRestrictions;
import com.coinbase.api.internal.models.policyRestrictions.Restrictions;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import javax.inject.Inject;
import org.joda.money.Money;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.functions.Func4;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AccountTransactionsPresenter {
    public static final String ACCOUNT_DATA = "account_data";
    public static final String CURRENCY_DATA = "currency_data";
    static final String NEW_WITHDRAW_DEPOSIT_SCREENS = "2018.march.mobile.android.new_withdraw_deposit";
    private final AccountCryptoAddressButtonConnector mAccountCryptoAddressButtonConnector;
    private final AccountTransactionsRouter mAccountTransactionsRouter;
    private final AccountUpdatedConnector mAccountUpdatedConnector;
    private final BuyRouter mBuyRouter;
    private final Context mContext;
    private final CompositeSubscription mEnableSendReceiveSubscription = new CompositeSubscription();
    private HashMap<String, Object> mFailureTrackingProperties = null;
    private final CompositeSubscription mFeatureFlagSubscription = new CompositeSubscription();
    private final FeatureFlags mFeatureFlags;
    private final FetchAccountTask mFetchAccountTask;
    private boolean mIsSend;
    private final Logger mLogger = LoggerFactory.getLogger(AccountTransactionsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final AccountTransactionsScreen mScreen;
    private Data mSelectedAccount;
    private com.coinbase.api.internal.models.currency.Data mSelectedCurrency;
    private final SellRouter mSellRouter;
    private boolean mShouldUseNewWithdrawDeposit = false;
    private final SnackBarWrapper mSnackBarWrapper;
    private final SplitTesting mSplitTesting;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final TransferMadeConnector mTransferMadeConnector;

    @Inject
    public AccountTransactionsPresenter(AccountTransactionsScreen screen, Application app, FetchAccountTask fetchAccountTask, SnackBarWrapper snackBarWrapper, AccountUpdatedConnector accountUpdatedConnector, AccountCryptoAddressButtonConnector accountCryptoAddressButtonConnector, TransferMadeConnector transferMadeConnector, BuyRouter buyRouter, SellRouter sellRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, AccountTransactionsRouter router, SplitTesting splitTesting, FeatureFlags featureFlags, LoginManager loginManager, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mContext = app;
        this.mFetchAccountTask = fetchAccountTask;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mAccountUpdatedConnector = accountUpdatedConnector;
        this.mAccountCryptoAddressButtonConnector = accountCryptoAddressButtonConnector;
        this.mTransferMadeConnector = transferMadeConnector;
        this.mBuyRouter = buyRouter;
        this.mSellRouter = sellRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mAccountTransactionsRouter = router;
        this.mSplitTesting = splitTesting;
        this.mFeatureFlags = featureFlags;
        this.mLoginManager = loginManager;
        this.mMainScheduler = mainScheduler;
    }

    void onViewCreated(Bundle args) {
        this.mSubscription.add(this.mAccountCryptoAddressButtonConnector.get().onBackpressureLatest().filter(AccountTransactionsPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(AccountTransactionsPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mAccountUpdatedConnector.get().observeOn(this.mMainScheduler).filter(AccountTransactionsPresenter$$Lambda$3.lambdaFactory$(this)).subscribe(AccountTransactionsPresenter$$Lambda$4.lambdaFactory$(this)));
        this.mSubscription.add(this.mTransferMadeConnector.get().filter(AccountTransactionsPresenter$$Lambda$5.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(AccountTransactionsPresenter$$Lambda$6.lambdaFactory$(this)));
        if (args != null) {
            Data account = (Data) new Gson().fromJson(args.getString(ACCOUNT_DATA), Data.class);
            if (account != null) {
                this.mSelectedAccount = account;
                updateAccountLabels(account);
                updateScreenForAccount(account);
            }
            String currencyJson = args.getString(CURRENCY_DATA);
            if (currencyJson != null) {
                this.mSelectedCurrency = (com.coinbase.api.internal.models.currency.Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(currencyJson, com.coinbase.api.internal.models.currency.Data.class);
            }
        }
    }

    static /* synthetic */ Boolean lambda$onViewCreated$0(AccountCryptoAddressButtonEvent button) {
        return Boolean.valueOf(button == AccountCryptoAddressButtonEvent.CLOSE);
    }

    static /* synthetic */ Boolean lambda$onViewCreated$4(TransferMadeEvent event) {
        return Boolean.valueOf(!event.isConsumed);
    }

    static /* synthetic */ void lambda$onViewCreated$5(AccountTransactionsPresenter this_, TransferMadeEvent event) {
        Utils.showRateAppDialog(this_.mScreen.getActivity(), MixpanelTracking.PROPERTY_VALUE_TRANSFER_MADE_VIEW);
        event.isConsumed = true;
    }

    void onShow() {
        this.mFeatureFlagSubscription.add(this.mFeatureFlags.get(NEW_WITHDRAW_DEPOSIT_SCREENS).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountTransactionsPresenter$$Lambda$7.lambdaFactory$(this), AccountTransactionsPresenter$$Lambda$8.lambdaFactory$(this)));
        if (this.mSelectedAccount != null) {
            fetchAndRefreshAccount();
        }
    }

    static /* synthetic */ void lambda$onShow$7(AccountTransactionsPresenter this_, Throwable throwable) {
        this_.mLogger.error("Error subscribing to mFeatureFlags for NEW_WITHDRAW_DEPOSIT_SCREENS");
        this_.mShouldUseNewWithdrawDeposit = false;
    }

    void onHide() {
        this.mFeatureFlagSubscription.clear();
    }

    void onShowOptionsMenu(Menu menu) {
        boolean isAddressMenuVisible = false;
        boolean isSendMenuVisible = false;
        if (this.mSelectedAccount != null) {
            switch (this.mSelectedAccount.getType()) {
                case WALLET:
                    isAddressMenuVisible = true;
                    isSendMenuVisible = true;
                    break;
                case VAULT:
                case MULTISIG:
                case MULTISIG_VAULT:
                case FORK_MULTISIG_VAULT:
                    isAddressMenuVisible = true;
                    isSendMenuVisible = false;
                    break;
            }
        }
        this.mScreen.showAddressMenu(menu, isAddressMenuVisible);
        this.mScreen.showSendMenu(menu, isSendMenuVisible);
    }

    void onDestroy() {
        this.mSubscription.clear();
        this.mEnableSendReceiveSubscription.clear();
    }

    boolean onBackPressed() {
        if (this.mScreen.isAccountAddressLayoutVisible()) {
            this.mScreen.hideAccountAddressLayout();
            return true;
        } else if (this.mScreen.isDetailLayoutVisible()) {
            this.mScreen.hideDetailLayout();
            return true;
        } else if (!this.mScreen.isShowingEnableSendReceive()) {
            return false;
        } else {
            this.mScreen.hideEnableSendReceive(false);
            return true;
        }
    }

    Data getSelectedAccount() {
        return this.mSelectedAccount;
    }

    com.coinbase.api.internal.models.currency.Data getSelectedCurrency() {
        return this.mSelectedCurrency;
    }

    void fetchAndRefreshAccount() {
        if (this.mSelectedAccount == null) {
            handleFetchAccountError();
        } else {
            this.mFetchAccountTask.execute(this.mSelectedAccount);
        }
    }

    void onBuyButtonClicked() {
        if (!(this.mSelectedAccount == null || this.mSelectedAccount.getCurrency() == null || this.mSelectedAccount.getCurrency().getCode() == null)) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNT_TAPPED_BUY_BUTTON, "currency", this.mSelectedAccount.getCurrency().getCode());
        }
        this.mBuyRouter.routeToBuyModal(this.mSelectedAccount, BuySource.ACCOUNT);
    }

    void onSellButtonClicked() {
        if (!(this.mSelectedAccount == null || this.mSelectedAccount.getCurrency() == null || this.mSelectedAccount.getCurrency().getCode() == null)) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNT_TAPPED_SELL_BUTTON, "currency", this.mSelectedAccount.getCurrency().getCode());
        }
        this.mSellRouter.routeToSellModal(this.mSelectedAccount, SellSource.ACCOUNT);
    }

    void onFiatWithdrawButtonClicked() {
        if (this.mSelectedAccount == null) {
            this.mLogger.error("Account was null when Withdraw was clicked");
            this.mSnackBarWrapper.show((int) R.string.account_unavailable_error_occurred);
            return;
        }
        onWithdrawFiatClicked(this.mSelectedAccount);
    }

    void onFiatDepositButtonClicked() {
        if (this.mSelectedAccount == null) {
            this.mLogger.error("Account was null when Deposit was clicked");
            this.mSnackBarWrapper.show((int) R.string.account_unavailable_error_occurred);
            return;
        }
        onDepositFiatClicked(this.mSelectedAccount);
    }

    void onAccountUpdated(Data account) {
        if (account == null) {
            handleFetchAccountError();
            return;
        }
        this.mSelectedAccount = account;
        updateAccountLabels(account);
        switch (account.getType()) {
            case WALLET:
                this.mScreen.updateTransactionWalletList(account);
                break;
            case FIAT:
                if (!shouldShowDepositFiat(account)) {
                    this.mScreen.updateTransactionDefaultList(account);
                    break;
                } else {
                    this.mScreen.updateTransactionFiatList(account);
                    break;
                }
            default:
                this.mScreen.updateTransactionDefaultList(account);
                break;
        }
        updateScreenForAccount(account);
    }

    void onShowSendClicked() {
        if (this.mSplitTesting.isInGroup(SplitTestConstants.WBL_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED) || this.mSplitTesting.isInGroup(SplitTestConstants.WBL_EXISTING_USER_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED)) {
            checkPolicyRestrictions(Type.SENDS.toString(), AccountTransactionsPresenter$$Lambda$9.lambdaFactory$(this), true);
        } else {
            this.mAccountTransactionsRouter.routeShowSend(this.mSelectedAccount);
        }
        if (this.mSelectedAccount != null && this.mSelectedAccount.getCurrency() != null && this.mSelectedAccount.getCurrency().getCode() != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNT_TAPPED_SEND_BUTTON, this.mSelectedAccount.getCurrency().getCode());
        }
    }

    static /* synthetic */ Void lambda$onShowSendClicked$8(AccountTransactionsPresenter this_, Boolean enabled, String title, String message, String button) {
        if (enabled.booleanValue()) {
            this_.mScreen.hideEnableSendReceive(true);
            this_.mAccountTransactionsRouter.routeShowSend(this_.mSelectedAccount);
        } else {
            AccountTransactionsScreen accountTransactionsScreen = this_.mScreen;
            if (TextUtils.isEmpty(title)) {
                title = this_.mContext.getString(R.string.account_transactions_enable_send);
            }
            if (TextUtils.isEmpty(message)) {
                message = this_.mContext.getString(R.string.account_transactions_enable_send_receive_explanation);
            }
            if (TextUtils.isEmpty(button)) {
                button = this_.mContext.getString(R.string.account_transactions_verify_your_account);
            }
            accountTransactionsScreen.showEnableSendReceive(title, message, button, R.drawable.account_transactions_enable_send);
        }
        return null;
    }

    void onShowCryptoAddressClicked() {
        if (!(this.mSelectedAccount == null || this.mSelectedAccount.getCurrency() == null || this.mSelectedAccount.getCurrency().getCode() == null)) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCOUNT_TAPPED_RECEIVE_BUTTON, this.mSelectedAccount.getCurrency().getCode());
        }
        if (this.mSplitTesting.isInGroup(SplitTestConstants.WBL_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED) || this.mSplitTesting.isInGroup(SplitTestConstants.WBL_EXISTING_USER_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED)) {
            checkPolicyRestrictions(Type.RECEIVES.toString(), AccountTransactionsPresenter$$Lambda$10.lambdaFactory$(this), false);
        } else {
            this.mScreen.showAccountAddressLayout();
        }
    }

    static /* synthetic */ Void lambda$onShowCryptoAddressClicked$9(AccountTransactionsPresenter this_, Boolean enabled, String title, String message, String button) {
        if (enabled.booleanValue()) {
            this_.mScreen.showAccountAddressLayout();
        } else {
            AccountTransactionsScreen accountTransactionsScreen = this_.mScreen;
            if (TextUtils.isEmpty(title)) {
                title = this_.mContext.getString(R.string.account_transactions_enable_send);
            }
            if (TextUtils.isEmpty(message)) {
                message = this_.mContext.getString(R.string.account_transactions_enable_send_receive_explanation);
            }
            if (TextUtils.isEmpty(button)) {
                button = this_.mContext.getString(R.string.account_transactions_verify_your_account);
            }
            accountTransactionsScreen.showEnableSendReceive(title, message, button, R.drawable.account_transactions_enable_receive);
        }
        return null;
    }

    private void handleFetchAccountError() {
        this.mSnackBarWrapper.showGenericError();
    }

    private void onWithdrawFiatClicked(Data account) {
        if (this.mShouldUseNewWithdrawDeposit) {
            this.mAccountTransactionsRouter.routeToWithdrawModal(account);
        } else {
            this.mScreen.showFiatDepositWithdraw(account, FiatTransactionsController.Type.WITHDRAW);
        }
    }

    private void onDepositFiatClicked(Data account) {
        if (!shouldShowDepositFiat(account)) {
            this.mLogger.error("Deposit button for account[" + account + "] was clicked even though button should not have been shown");
        } else if (AccountUtils.isEuroAccount(account)) {
            this.mScreen.showSepaDeposits();
        } else if (this.mShouldUseNewWithdrawDeposit) {
            this.mAccountTransactionsRouter.routeToDepositModal(account);
        } else {
            this.mScreen.showFiatDepositWithdraw(account, FiatTransactionsController.Type.DEPOSIT);
        }
    }

    private boolean shouldShowDepositFiat(Data account) {
        return !AccountUtils.isGBPAccount(account);
    }

    private void updateScreenForAccount(Data account) {
        switch (account.getType()) {
            case WALLET:
                this.mScreen.showBuyOption();
                this.mScreen.showSellOption();
                return;
            case VAULT:
            case MULTISIG:
            case MULTISIG_VAULT:
            case FORK_MULTISIG_VAULT:
                this.mScreen.hideButtonLayout();
                return;
            default:
                this.mScreen.showDepositOption(shouldShowDepositFiat(account));
                this.mScreen.showWithdrawOption(true);
                return;
        }
    }

    void updateAccountLabels(Data account) {
        String accountNativeBalance = "";
        String accountBalance = accountNativeBalance;
        String accountTitle = accountNativeBalance;
        if (account != null) {
            accountTitle = AccountUtils.getDisplayableAccountName(account);
            Money accountBalanceMoney = AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil);
            if (accountBalanceMoney != null) {
                Set<Options> formatOptions = EnumSet.of(Options.ROUND_8_DIGITS);
                if (account.getType() != Data.Type.FIAT && accountBalanceMoney.isZero()) {
                    formatOptions = EnumSet.of(Options.ROUND_0_DIGITS);
                }
                accountBalance = this.mMoneyFormatterUtil.formatMoney(accountBalanceMoney, formatOptions);
            }
            Money nativeBalanceMoney = AccountUtils.getAccountNativeBalanceMoney(account, this.mMoneyFormatterUtil);
            if (nativeBalanceMoney != null) {
                accountNativeBalance = this.mMoneyFormatterUtil.formatMoney(nativeBalanceMoney, EnumSet.of(Options.ROUND_2_DIGITS));
            }
        }
        this.mScreen.showAccountLabels(accountTitle, accountBalance, accountNativeBalance);
    }

    void onEnableSendReceivedClicked() {
        this.mScreen.hideEnableSendReceive(true);
        this.mAccountTransactionsRouter.routeToInvestmentTiers();
        trackEvent(true);
    }

    void onCloseEnableSendReceiveClicked() {
        this.mScreen.hideEnableSendReceive(false);
        trackEvent(false);
    }

    void initializeTracking(boolean send) {
        this.mIsSend = send;
    }

    private boolean accountCurrencyEquals(Data account1, Data account2) {
        return (account1 == null || account2 == null || account1.getCurrency() == null || account2.getCurrency() == null || !TextUtils.equals(account1.getCurrency().getCode(), account2.getCurrency().getCode())) ? false : true;
    }

    private void checkPolicyRestrictions(String action, Func4<Boolean, String, String, String, Void> policyRestrictionsCallback, boolean send) {
        this.mScreen.showEnableSendReceiveProgress(send);
        this.mEnableSendReceiveSubscription.clear();
        this.mEnableSendReceiveSubscription.add(this.mLoginManager.getClient().getPolicyRestrictionsRX(action).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountTransactionsPresenter$$Lambda$11.lambdaFactory$(this, policyRestrictionsCallback, send), AccountTransactionsPresenter$$Lambda$12.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$checkPolicyRestrictions$10(AccountTransactionsPresenter this_, Func4 policyRestrictionsCallback, boolean send, Pair pair) {
        boolean z = false;
        Response<Restrictions> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            boolean enabled;
            com.coinbase.api.internal.models.policyRestrictions.Data data = ((Restrictions) response.body()).getData();
            if (data == null || data.getPolicyRestrictions() == null || data.getPolicyRestrictions().isEmpty()) {
                enabled = true;
            } else {
                enabled = false;
            }
            String title = "";
            String message = "";
            String button = "";
            if (!(enabled || data.getPolicyRestrictions().get(0) == null)) {
                PolicyRestrictions policyRestrictions = (PolicyRestrictions) data.getPolicyRestrictions().get(0);
                title = policyRestrictions.getMessage();
                message = policyRestrictions.getDescription();
                button = policyRestrictions.getUrl() == null ? "" : policyRestrictions.getUrl().getText();
            }
            if (data == null || data.getPolicyRestrictions() == null || data.getPolicyRestrictions().isEmpty()) {
                z = true;
            }
            policyRestrictionsCallback.call(Boolean.valueOf(z), title, message, button);
            this_.trackSetupRequiredViewed(data, send);
            return;
        }
        this_.mScreen.hideEnableSendReceive(false);
        this_.mSnackBarWrapper.showError(response, retrofit);
    }

    static /* synthetic */ void lambda$checkPolicyRestrictions$11(AccountTransactionsPresenter this_, Throwable t) {
        this_.mScreen.hideEnableSendReceive(false);
        this_.mSnackBarWrapper.showFailure(t);
        this_.mLogger.error("Couldn't check policy restrictions", t);
    }

    private void trackSetupRequiredViewed(com.coinbase.api.internal.models.policyRestrictions.Data data, boolean send) {
        if (data != null && data.getPolicyRestrictions() != null && !data.getPolicyRestrictions().isEmpty()) {
            Object obj;
            JSONArray array = new JSONArray();
            for (int i = 0; i < data.getPolicyRestrictions().size(); i++) {
                PolicyRestrictions restriction = (PolicyRestrictions) data.getPolicyRestrictions().get(i);
                if (!(restriction == null || TextUtils.isEmpty(restriction.getId()))) {
                    array.put(restriction.getId());
                }
            }
            this.mFailureTrackingProperties = new HashMap();
            HashMap hashMap = this.mFailureTrackingProperties;
            String str = "currency";
            if (this.mSelectedAccount == null) {
                obj = "";
            } else {
                obj = this.mSelectedAccount.getCurrency().getCode();
            }
            hashMap.put(str, obj);
            this.mFailureTrackingProperties.put(MixpanelTracking.PROPERTY_REASON, array);
            this.mMixpanelTracking.trackEvent(send ? MixpanelTracking.EVENT_SEND_ACCOUNT_SETUP_REQUIRED_VIEWED : MixpanelTracking.EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_VIEWED, this.mFailureTrackingProperties);
        }
    }

    private void trackEvent(boolean confirm) {
        if (this.mFailureTrackingProperties != null) {
            String event = this.mIsSend ? confirm ? MixpanelTracking.EVENT_SEND_ACCOUNT_SETUP_REQUIRED_TAPPED_CONFIRMED : MixpanelTracking.EVENT_SEND_ACCOUNT_SETUP_REQUIRED_TAPPED_CLOSE : confirm ? MixpanelTracking.EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_TAPPED_CONFIRMED : MixpanelTracking.EVENT_RECEIVE_ACCOUNT_SETUP_REQUIRED_TAPPED_CLOSE;
            this.mMixpanelTracking.trackEvent(event, this.mFailureTrackingProperties);
        }
    }
}
