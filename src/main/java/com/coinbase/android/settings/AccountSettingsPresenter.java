package com.coinbase.android.settings;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.idology.IdologyUtils;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.paymentmethods.AddPaymentMethodConnector;
import com.coinbase.android.paymentmethods.ConnectedAccountsLayout;
import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenter;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.paymentmethods.VerifyPaymentMethodConnector;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.settings.UpdateUserTask.UpdateUserListener;
import com.coinbase.android.splittesting.FlavorSplitTestConstants;
import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.GetUserTask;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.task.SyncAccountsTask.SyncAccountsListener;
import com.coinbase.android.ui.CurrencySelectorConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.idology.Data.Status;
import com.coinbase.api.internal.models.idology.IdologyList;
import com.coinbase.v2.models.supportedCurrencies.SupportedCurrencies;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.Tiers;
import com.google.android.gms.appinvite.AppInviteInvitation.IntentBuilder;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AccountSettingsPresenter {
    private static final String IDOLOGY_VERIFICATION_METHOD = "idology";
    static final String LAST_USER_OBJECT = "last_user_object";
    private static final int REQUEST_CODE_APP_INVITE = 3;
    private EmailSettingsItem emailSettingsItem;
    private final AddPaymentMethodConnector mAddPaymentMethodConnector;
    private final List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> mAdditionalDebugItems;
    private final AppCompatActivity mAppCompatActivity;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final CurrencySelectorConnector mCurrencySelectorConnector;
    private final Func0<GetUserTask> mGetUserTaskFunc;
    private final IdologyUtils mIdologyUtils;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private boolean mIsPrefListInitialized = false;
    private final LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(AccountSettingsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final PINManager mPINManager;
    private final PaymentMethodsFetchedConnector mPaymentMethodsFetchedConnector;
    private final PaymentMethodsUpdatedConnector mPaymentMethodsUpdatedConnector;
    List<AccountSettingsPreferenceItem> mPreferenceList = new ArrayList();
    Map<String, AccountSettingsPreferenceItem> mPreferenceMap = new HashMap();
    final AccountSettingsScreen mScreen;
    private final SharedPreferences mSharedPrefs;
    private volatile boolean mShowingConnectedAccounts = false;
    private volatile boolean mShowingInvestmentTiers = false;
    private final SignOutConnector mSignOutConnector;
    private final SnackBarWrapper mSnackBarWrapper;
    private final SplitTesting mSplitTesting;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private Timer mTimer;
    private final UserUpdatedConnector mUserUpdatedConnector;
    private final VerifyPaymentMethodConnector mVerifyPaymentMethodConnector;
    private PhoneNumberItem phoneNumberItem;
    private PrivacyItem privacyItem;

    @Inject
    public AccountSettingsPresenter(LoginManager loginManager, PINManager pinManager, Application application, AppCompatActivity appCompatActivity, SnackBarWrapper snackBarWrapper, SharedPreferences sharedPrefs, AccountSettingsScreen screen, UserUpdatedConnector userUpdatedConnector, LocalUserDataUpdatedConnector localUserDataUpdatedConnector, CurrencySelectorConnector currencySelectorConnector, SignOutConnector signOutConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyUtils idologyUtils, AddPaymentMethodConnector addPaymentMethodConnector, VerifyPaymentMethodConnector verifyPaymentMethodConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> additionalDebugItems, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mPINManager = pinManager;
        this.mContext = application;
        this.mAppCompatActivity = appCompatActivity;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mSharedPrefs = sharedPrefs;
        this.mScreen = screen;
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mLocalUserDataUpdatedConnector = localUserDataUpdatedConnector;
        this.mCurrencySelectorConnector = currencySelectorConnector;
        this.mSignOutConnector = signOutConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyUtils = idologyUtils;
        this.mAddPaymentMethodConnector = addPaymentMethodConnector;
        this.mVerifyPaymentMethodConnector = verifyPaymentMethodConnector;
        this.mPaymentMethodsUpdatedConnector = paymentMethodsUpdatedConnector;
        this.mPaymentMethodsFetchedConnector = paymentMethodsFetchedConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSplitTesting = splitTesting;
        this.mGetUserTaskFunc = AccountSettingsPresenter$$Lambda$1.lambdaFactory$(this);
        this.mAdditionalDebugItems = additionalDebugItems;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    AccountSettingsPresenter(LoginManager loginManager, PINManager pinManager, Application application, AppCompatActivity appCompatActivity, SnackBarWrapper snackBarWrapper, SharedPreferences sharedPrefs, AccountSettingsScreen screen, UserUpdatedConnector userUpdatedConnector, LocalUserDataUpdatedConnector localUserDataUpdatedConnector, CurrencySelectorConnector currencySelectorConnector, SignOutConnector signOutConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyUtils idologyUtils, AddPaymentMethodConnector addPaymentMethodConnector, VerifyPaymentMethodConnector verifyPaymentMethodConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, Func0<GetUserTask> getUserTaskFunc, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mPINManager = pinManager;
        this.mContext = application;
        this.mAppCompatActivity = appCompatActivity;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mSharedPrefs = sharedPrefs;
        this.mScreen = screen;
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mLocalUserDataUpdatedConnector = localUserDataUpdatedConnector;
        this.mCurrencySelectorConnector = currencySelectorConnector;
        this.mSignOutConnector = signOutConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyUtils = idologyUtils;
        this.mAddPaymentMethodConnector = addPaymentMethodConnector;
        this.mVerifyPaymentMethodConnector = verifyPaymentMethodConnector;
        this.mPaymentMethodsUpdatedConnector = paymentMethodsUpdatedConnector;
        this.mPaymentMethodsFetchedConnector = paymentMethodsFetchedConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSplitTesting = splitTesting;
        this.mGetUserTaskFunc = getUserTaskFunc;
        this.mAdditionalDebugItems = null;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    private void initListItems() {
        this.mPreferenceList.clear();
        this.mPreferenceList.add(new AccountHeader(this));
        this.mPreferenceList.add(new NativeCurrencyItem(this));
        this.mPreferenceList.add(new PaymentMethodsItem(this));
        this.phoneNumberItem = new PhoneNumberItem(this);
        this.mPreferenceList.add(this.phoneNumberItem);
        if (this.mUserUpdatedConnector.get().getValue() != null) {
            updatePrivacyRightsListItems((Data) this.mUserUpdatedConnector.get().getValue());
        }
        if (!this.mShowingInvestmentTiers) {
            this.mPreferenceList.add(new VerificationHeader(this));
            this.mPreferenceList.add(new IdentityDocument(this));
            if (this.mIdologyUtils.showIdology()) {
                this.mPreferenceList.add(new PersonalInformation(this));
            }
        }
        this.mPreferenceList.add(new AppHeader(this));
        this.mPreferenceList.add(new PinItem(this));
        this.mPreferenceList.add(new SupportItem(this));
        this.mPreferenceList.add(new ShareItem(this));
        this.mPreferenceList.add(new InviteItem(this));
        this.mPreferenceList.add(new SignOutItem(this));
        this.mPreferenceList.add(new AboutItem(this));
        if (this.mAdditionalDebugItems != null) {
            for (Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem> item : this.mAdditionalDebugItems) {
                this.mPreferenceList.add(item.call(this));
            }
        }
        this.mIsPrefListInitialized = true;
        userDataUpdated();
    }

    List<SettingsPreferenceItem> getPreferenceList() {
        return this.mPreferenceList;
    }

    void onResume() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mLocalUserDataUpdatedConnector.get().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$2.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mCurrencySelectorConnector.getNative().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$4.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mSignOutConnector.get().first().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$6.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$7.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyVerificationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$8.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$9.lambdaFactory$(this)));
        this.mSubscription.add(this.mAddPaymentMethodConnector.get().onBackpressureLatest().filter(AccountSettingsPresenter$$Lambda$10.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$11.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$12.lambdaFactory$(this)));
        this.mSubscription.add(this.mVerifyPaymentMethodConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$13.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$14.lambdaFactory$(this)));
        this.mSubscription.add(this.mUserUpdatedConnector.get().distinctUntilChanged().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$15.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$16.lambdaFactory$(this)));
        if (this.mIdologyUtils.showIdology() && !this.mShowingInvestmentTiers) {
            getVerificationList();
        }
        if (this.mScreen.getArgs() != null && this.mScreen.getArgs().containsKey(LAST_USER_OBJECT)) {
            Data user = loadUserFromBundle();
            if (user != null) {
                handleGetUserResponse(user);
            }
        }
        if (!this.mIsPrefListInitialized) {
            initListItems();
        }
        refreshUserData();
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SETTINGS_VIEWED, new String[0]);
    }

    static /* synthetic */ Boolean lambda$onResume$9(ClassConsumableEvent v) {
        return Boolean.valueOf(!v.consumeIfNotConsumed(AccountSettingsPresenter.class));
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    boolean onBackPressed() {
        if (!this.mScreen.isRemovePaymentViewVisible()) {
            return false;
        }
        this.mScreen.hideRemovePaymentView();
        return true;
    }

    boolean isPinEnabled() {
        return this.mPINManager.isPinEnabled(this.mContext);
    }

    String getCachedValue(String preferenceKey, String defaultVal) {
        return this.mSharedPrefs.getString(preferenceKey, defaultVal);
    }

    String getString(int resourceId) {
        return this.mContext.getString(resourceId);
    }

    String getFormattedAppVersion() {
        String versionName = "";
        String buildName = "";
        try {
            versionName = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            buildName = String.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            Log.w("Coinbase", "Exception: " + e.getMessage());
        }
        return this.mContext.getString(R.string.coinbase_version) + versionName + " (" + buildName + ")";
    }

    void onNameItemClicked(String name) {
        this.mScreen.onNameItemClicked(name);
    }

    void onTiersItemClicked() {
        this.mScreen.onInvestmentTiersClicked();
    }

    void onIncreaseLimitsClicked(String buttonText) {
        if (!TextUtils.isEmpty(buttonText)) {
            if (buttonText.equals(this.mContext.getString(R.string.tiers_complete_your_account))) {
                this.mMixpanelTracking.trackEvent(MixpanelTracking.SETTINGS_TAPPED_COMPLETE_ACCOUNT, new String[0]);
            } else if (buttonText.equals(this.mContext.getString(R.string.increase_your_limits))) {
                this.mMixpanelTracking.trackEvent(MixpanelTracking.SETTINGS_TAPPED_INCREASE_LIMIT, new String[0]);
            }
        }
        this.mScreen.onInvestmentTiersClicked();
    }

    void onNativeCurrencyItemClicked(String currentCurrencyCode) {
        this.mSubscription.add(this.mLoginManager.getClient().getSupportedCurrenciesRx().map(AccountSettingsPresenter$$Lambda$17.lambdaFactory$(this, currentCurrencyCode)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$18.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$19.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$onNativeCurrencyItemClicked$16(AccountSettingsPresenter this_, String currentCurrencyCode, Pair pair) {
        Response<SupportedCurrencies> response = pair.first;
        if (!response.isSuccessful()) {
            return null;
        }
        List<com.coinbase.v2.models.supportedCurrencies.Data> supportedCurrenciesList = ((SupportedCurrencies) response.body()).getData();
        if (supportedCurrenciesList == null) {
            return null;
        }
        List<CurrencyUnit> currencyUnitList = new ArrayList();
        for (com.coinbase.v2.models.supportedCurrencies.Data currency : supportedCurrenciesList) {
            try {
                currencyUnitList.add(CurrencyUnit.getInstance(currency.getId()));
            } catch (IllegalCurrencyException e) {
            }
        }
        Collections.sort(currencyUnitList, new Comparator<CurrencyUnit>() {
            public int compare(CurrencyUnit lhs, CurrencyUnit rhs) {
                return lhs.getCurrencyCode().compareToIgnoreCase(rhs.getCurrencyCode());
            }
        });
        int currencyIndex = this_.getSelectedCurrencyIndex(currencyUnitList, currentCurrencyCode);
        if (currencyIndex == -1) {
            return null;
        }
        return new Pair(currencyUnitList, Integer.valueOf(currencyIndex));
    }

    static /* synthetic */ void lambda$onNativeCurrencyItemClicked$17(AccountSettingsPresenter this_, Pair pair) {
        if (pair == null) {
            this_.mSnackBarWrapper.show((int) R.string.account_list_error);
        } else {
            this_.mScreen.onNativeCurrencyItemClicked((List) pair.first, ((Integer) pair.second).intValue());
        }
    }

    void onPaymentMethodsItemClicked() {
        this.mScreen.onPaymentMethodsItemClicked();
    }

    void onPhoneNumberItemClicked() {
        this.mScreen.onPhoneNumberItemClicked();
    }

    void onPrivacyItemClicked() {
        this.mScreen.onPrivacyItemClicked();
    }

    void onEmailSettingsItemClicked() {
        this.mScreen.onEmailSettingsItemClicked();
    }

    void onIdentityDocumentItemClicked() {
        this.mScreen.onIdentityDocumentItemClicked();
    }

    public void onPersonalInformationItemClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.SETTINGS_TAPPED_PERSONAL_INFORMATION, new String[0]);
        this.mScreen.onPersonalInformationItemClicked();
    }

    void onPinItemClicked(boolean isSwitchOn) {
        this.mScreen.onPinItemClicked(isSwitchOn);
    }

    void onSupportItemClicked() {
        this.mScreen.onSupportItemClicked();
    }

    void onShareItemClicked() {
        this.mScreen.onShareItemClicked();
    }

    void onInviteItemClicked() {
        String message = getString(R.string.app_invite_message);
        if (message.length() >= 100) {
            message = message.substring(0, 96) + "...";
        }
        Intent intent = new IntentBuilder(getString(R.string.invite_friends_heading)).setMessage(message).setDeepLink(Uri.parse(getString(R.string.app_inivite_deeplink) + PreferenceManager.getDefaultSharedPreferences(this.mContext).getString(Constants.KEY_USER_ID, null))).build();
        intent.addFlags(1073741824);
        this.mScreen.startActivityForResult(intent, 3);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_APP_INVITE_INITIATED, new String[0]);
    }

    void onSignOutItemClicked() {
        this.mScreen.onSignOutItemClicked();
    }

    boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            if (resultCode == -1) {
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_APP_INVITE_COMPLETED, new String[0]);
            }
            if (resultCode != 0) {
                return true;
            }
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_APP_INVITE_FAILED, new String[0]);
            return true;
        } else if (requestCode == PaymentMethodsPresenter.INTENT_VERIFY && resultCode == -1) {
            this.mPaymentMethodsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            return true;
        } else if (resultCode != Constants.ACTIVITY_RESULT_PARENT_SUCCESS_ROUTER) {
            return false;
        } else {
            this.mPaymentMethodsUpdatedConnector.get().onNext(new ClassConsumableEvent());
            return true;
        }
    }

    private void refreshUserData() {
        ((GetUserTask) this.mGetUserTaskFunc.call()).getUser();
    }

    void handleGetUserResponse(Data user) {
        Editor e = PreferenceManager.getDefaultSharedPreferences(this.mContext).edit();
        e.putString(Constants.KEY_ACCOUNT_EMAIL, user.getEmail());
        e.putString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, user.getNativeCurrency());
        e.putString(Constants.KEY_ACCOUNT_FULL_NAME, user.getName());
        e.putString(Constants.KEY_ACCOUNT_TIME_ZONE, user.getTimeZone());
        e.putBoolean(Constants.KEY_ACCOUNT_NEEDS_USER_AGREEMENT, Utils.isUserAgreementRequired(user.getRestrictions()));
        e.apply();
        Utils.setAdminFlagsToPrefs(user, this.mContext);
        this.mLocalUserDataUpdatedConnector.get().onNext(null);
        boolean z = this.mScreen.isShown() && this.mSplitTesting.isInGroup(SplitTestConstants.SETTINGS_PAYMENT_METHODS_SPLIT_TEST, SplitTestConstants.SETTINGS_PAYMENT_METHODS_ENABLED);
        this.mShowingConnectedAccounts = z;
        if (this.mShowingConnectedAccounts) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SETTINGS_VIEWED_LINKED_ACCOUNTS, new String[0]);
            updateConnectedAccountsPreferenceListItem();
        }
        this.mSubscription.add(Observable.combineLatest(this.mSplitTesting.get(SplitTestConstants.WBL_SPLIT_TEST).onBackpressureLatest().observeOn(this.mMainScheduler), this.mPaymentMethodsFetchedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler), AccountSettingsPresenter$$Lambda$20.lambdaFactory$(this, user)).observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$21.lambdaFactory$(), AccountSettingsPresenter$$Lambda$22.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$handleGetUserResponse$20(Void v) {
    }

    static /* synthetic */ void lambda$handleGetUserResponse$21(AccountSettingsPresenter this_, Throwable t) {
        this_.mShowingInvestmentTiers = false;
        if (this_.mScreen.isShown()) {
            this_.mScreen.hideTiersHeader();
        }
        this_.mLogger.error("Couldn't subscribe to split test groups, shouldn't happen", t);
    }

    private Void combineWblAndPaymentMethodsSplitTest(List<com.coinbase.v2.models.paymentMethods.Data> paymentMethods, Data user) {
        Tiers tiers = user.getTiers();
        boolean z = this.mScreen.isShown() && ((this.mSplitTesting.isInGroup(SplitTestConstants.WBL_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED) || this.mSplitTesting.isInGroup(SplitTestConstants.WBL_EXISTING_USER_SPLIT_TEST, FlavorSplitTestConstants.WBL_ENABLED)) && shouldShowTiers(tiers));
        this.mShowingInvestmentTiers = z;
        if (this.mShowingInvestmentTiers) {
            updateTiersPreferenceListItem(tiers);
            if ((!this.mShowingConnectedAccounts || (this.mShowingConnectedAccounts && !paymentMethods.isEmpty())) && shouldShowInvestmentTiersHeader(tiers)) {
                this.mScreen.showTiersHeader();
                this.mScreen.setTiersHeader(tiers.getHeader());
                this.mScreen.setTiersBody(tiers.getBody());
                this.mScreen.setTiersActionButtonText(tiers.getUpgradeButtonText());
            } else {
                this.mScreen.hideTiersHeader();
            }
        } else if (this.mScreen.isShown()) {
            this.mScreen.hideTiersHeader();
        }
        return null;
    }

    private void handleErrorResponse(Throwable t) {
        this.mSnackBarWrapper.showFailure(t);
    }

    private void onConfirmSignOut() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mScreen.showProgressDialog();
        this.mTimer.schedule(new TimerTask() {
            public void run() {
                AccountSettingsPresenter.this.mScreen.hideProgressDialog();
                AccountSettingsPresenter.this.mScreen.signOut();
            }
        }, 100);
    }

    private void nativeCurrencySelected(String selectedCurrency) {
        this.mScreen.showProgressDialog();
        new UpdateUserTask(this.mContext, new UpdateUserListener() {
            public void onPreExecute() {
            }

            public void onException() {
                AccountSettingsPresenter.this.mScreen.showProgressDialog();
                AccountSettingsPresenter.this.mSnackBarWrapper.show((int) R.string.error_occurred_try_again);
            }

            public void onFinally() {
                new SyncAccountsTask(AccountSettingsPresenter.this.mContext, new SyncAccountsListener() {
                    public void onPreExecute() {
                    }

                    public void onException() {
                        AccountSettingsPresenter.this.mScreen.hideProgressDialog();
                    }

                    public void onFinally() {
                        AccountSettingsPresenter.this.mScreen.hideProgressDialog();
                    }
                }).syncAccounts();
            }
        }).updateUser(null, selectedCurrency, null);
    }

    private void userDataUpdated() {
        updatePreferenceMap();
        this.mScreen.updateUserData();
    }

    private void updatePrivacyRightsListItems(Data userData) {
        if (userData == null || !Boolean.TRUE.equals(userData.getAccessPrivacyRights())) {
            if (this.privacyItem != null) {
                this.mPreferenceList.remove(this.privacyItem);
            }
            if (this.emailSettingsItem != null) {
                this.mPreferenceList.remove(this.emailSettingsItem);
            }
        } else {
            int index = this.mPreferenceList.indexOf(this.phoneNumberItem);
            if (this.privacyItem == null) {
                this.privacyItem = new PrivacyItem(this);
                this.mPreferenceList.add(index + 1, this.privacyItem);
            }
            if (this.emailSettingsItem == null) {
                this.emailSettingsItem = new EmailSettingsItem(this);
                this.mPreferenceList.add(index + 2, this.emailSettingsItem);
            }
        }
        userDataUpdated();
    }

    private void updateTiersPreferenceListItem(Tiers tiers) {
        AccountSettingsPreferenceItem tiersItem = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(TiersItem.class.getName());
        if (tiersItem != null) {
            tiersItem.setDisplayValue(tiers.getCompletedDescription());
            return;
        }
        AccountSettingsPreferenceItem verificationItem = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(VerificationHeader.class.getName());
        if (verificationItem != null) {
            this.mPreferenceList.remove(verificationItem);
        }
        AccountSettingsPreferenceItem identityDocumentItem = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(IdentityDocument.class.getName());
        if (identityDocumentItem != null) {
            this.mPreferenceList.remove(identityDocumentItem);
        }
        AccountSettingsPreferenceItem idologyItem = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(PersonalInformation.class.getName());
        if (idologyItem != null) {
            this.mPreferenceList.remove(idologyItem);
        }
        int accountIndex = -1;
        for (int i = 0; i < this.mPreferenceList.size(); i++) {
            if (this.mPreferenceList.get(i) instanceof AccountHeader) {
                accountIndex = i;
            }
        }
        if (accountIndex >= 0) {
            this.mPreferenceList.add(accountIndex + 1, new TiersItem(this, tiers.getCompletedDescription()));
            userDataUpdated();
            return;
        }
        this.mLogger.error("No account header, don't konw where to insert tiers.");
    }

    private void updateConnectedAccountsPreferenceListItem() {
        AccountSettingsPreferenceItem connectedAccountsViewItem = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(ConnectedAccountsView.class.getName());
        if (connectedAccountsViewItem == null) {
            this.mPreferenceList.add(0, new ConnectedAccountsHeader(this));
            connectedAccountsViewItem = new ConnectedAccountsView(this, this.mAppCompatActivity);
            ConnectedAccountsLayout layout = ((ConnectedAccountsView) connectedAccountsViewItem).getView();
            this.mScreen.setConnectedAccountsLayout(layout);
            layout.onShow();
            this.mPreferenceList.add(1, connectedAccountsViewItem);
            AccountSettingsPreferenceItem linkedAccounts = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(PaymentMethodsItem.class.getName());
            if (linkedAccounts != null) {
                this.mPreferenceList.remove(linkedAccounts);
            }
            userDataUpdated();
        } else if (connectedAccountsViewItem instanceof ConnectedAccountsView) {
            ((ConnectedAccountsView) connectedAccountsViewItem).getView().onShow();
        }
    }

    private int getSelectedCurrencyIndex(List<CurrencyUnit> currencyList, String currentCurrencyCode) {
        int currencyIndex = -1;
        if (currencyList == null || currencyList.size() == 0 || StringUtils.isEmpty(currentCurrencyCode)) {
            return -1;
        }
        for (int i = 0; i < currencyList.size(); i++) {
            if (currentCurrencyCode.equals(((CurrencyUnit) currencyList.get(i)).getCurrencyCode())) {
                currencyIndex = i;
                break;
            }
        }
        return currencyIndex;
    }

    private boolean shouldShowTiers(Tiers tiers) {
        return shouldShowInvestmentTiersHeader(tiers) || !(tiers == null || TextUtils.isEmpty(tiers.getCompletedDescription()));
    }

    private boolean shouldShowInvestmentTiersHeader(Tiers tiers) {
        return (tiers == null || TextUtils.isEmpty(tiers.getUpgradeButtonText()) || TextUtils.isEmpty(tiers.getHeader()) || TextUtils.isEmpty(tiers.getBody())) ? false : true;
    }

    private void updateIdologyPrefList(com.coinbase.api.internal.models.idology.Data idology) {
        if (idology != null && idology.getMethod() != null && idology.getStatus() != null && idology.getStatus() != Status.UNKNOWN && idology.getMethod().toLowerCase().equals(IDOLOGY_VERIFICATION_METHOD) && idology.getStatus() == Status.SUCCESS) {
            AccountSettingsPreferenceItem item = (AccountSettingsPreferenceItem) this.mPreferenceMap.get(PersonalInformation.class.getName());
            if (item != null) {
                this.mPreferenceList.remove(item);
                userDataUpdated();
            }
        }
    }

    private void getVerificationList() {
        this.mSubscription.add(this.mLoginManager.getClient().getIdentificationVerificationsRx().first().observeOn(this.mMainScheduler).subscribe(AccountSettingsPresenter$$Lambda$23.lambdaFactory$(this), AccountSettingsPresenter$$Lambda$24.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getVerificationList$22(AccountSettingsPresenter this_, Pair pair) {
        Response<IdologyList> response = pair.first;
        if (response.isSuccessful() && response.body() != null) {
            List<com.coinbase.api.internal.models.idology.Data> verificationList = ((IdologyList) response.body()).getData();
            if (verificationList != null) {
                for (com.coinbase.api.internal.models.idology.Data idology : verificationList) {
                    if (idology.getMethod() != null && idology.getMethod().toLowerCase().equals(IDOLOGY_VERIFICATION_METHOD) && idology.getStatus() != null && idology.getStatus() == Status.SUCCESS) {
                        this_.mIdologyUtils.updateIdologyVerificationResult(true);
                        this_.mIdologyVerificationConnector.get().onNext(idology);
                        return;
                    }
                }
            }
        }
    }

    private void routeToAddAccounts() {
        this.mScreen.routeToAddAccounts();
    }

    private void routeToVerifyAccount(com.coinbase.v2.models.paymentMethods.Data paymentMethod) {
        this.mScreen.routeToVerifyAccount(paymentMethod);
    }

    private void cacheUserResponseInBundle(Data user) {
        if (this.mScreen.getArgs() != null) {
            String userJson = new Gson().toJson((Object) user, (Type) Data.class);
            if (!TextUtils.isEmpty(userJson)) {
                this.mScreen.getArgs().putString(LAST_USER_OBJECT, userJson);
            }
        }
    }

    private Data loadUserFromBundle() {
        Bundle args = this.mScreen.getArgs();
        if (args == null || !args.containsKey(LAST_USER_OBJECT)) {
            return null;
        }
        return (Data) new Gson().fromJson(args.getString(LAST_USER_OBJECT), Data.class);
    }

    private void updatePreferenceMap() {
        this.mPreferenceMap.clear();
        for (AccountSettingsPreferenceItem item : this.mPreferenceList) {
            this.mPreferenceMap.put(item.getClass().getName(), item);
        }
    }

    public LocalUserDataUpdatedConnector getUserDataUpdatedConnector() {
        return this.mLocalUserDataUpdatedConnector;
    }
}
