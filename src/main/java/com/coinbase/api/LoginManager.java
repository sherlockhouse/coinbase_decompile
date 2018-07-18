package com.coinbase.api;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.BuildConfig;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.ClientCacheDatabase;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.task.GetUserTask;
import com.coinbase.android.task.GetUserTask.GetUserListener;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.CoinbaseInternal;
import com.coinbase.auth.AccessToken;
import com.coinbase.v1.entity.OAuthTokensResponse;
import com.coinbase.v1.exception.CoinbaseException;
import com.coinbase.v2.models.account.Accounts;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.user.Oauth;
import com.coinbase.v2.models.user.User;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class LoginManager {
    private final DatabaseManager dbManager;
    private CoinbaseInternal mClient;
    private final Application mContext;
    private final Logger mLogger;
    private final Scheduler mMainScheduler;
    private final SharedPreferences mSharedPrefs;
    private final SignOutConnector mSignOutConnector;
    private final UserUpdatedConnector mUserUpdatedConnector;
    private int retryCount;

    public interface TokenResponse {
        void onResponseNeededRefresh(boolean z);
    }

    public interface TokenAndUserResponse extends TokenResponse {
        void onResponse(Response<User> response);
    }

    public LoginManager(Application context, DatabaseManager dbManager, Scheduler mainScheduler, UserUpdatedConnector userUpdatedConnector, SharedPreferences sharedPreferences, SignOutConnector signOutConnector) {
        this(context, dbManager, CoinbaseInternal.getInstance(), mainScheduler, userUpdatedConnector, signOutConnector, sharedPreferences);
    }

    public LoginManager(Application context, DatabaseManager dbManager, CoinbaseInternal client, Scheduler mainScheduler, UserUpdatedConnector userUpdatedConnector, SignOutConnector signOutConnector, SharedPreferences sharedPreferences) {
        this.retryCount = 0;
        this.mLogger = LoggerFactory.getLogger(LoginManager.class);
        this.mContext = context;
        this.dbManager = dbManager;
        this.mClient = client;
        this.mMainScheduler = mainScheduler;
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mSignOutConnector = signOutConnector;
        this.mSharedPrefs = sharedPreferences;
    }

    public String getClientId() {
        return Constants.CLIENT_ID;
    }

    protected String getClientSecret() {
        return Constants.CLIENT_SECRET;
    }

    public boolean isSignedIn() {
        return getAccessToken() != null;
    }

    public synchronized List<Data> getAccounts() {
        List<Data> accounts;
        accounts = AccountORM.list(this.dbManager.openDatabase());
        this.dbManager.closeDatabase();
        String keyAccountId = this.mSharedPrefs.getString(Constants.KEY_ACTIVE_ACCOUNT_ID, null);
        for (Data account : accounts) {
            if (account.getId().equals(keyAccountId)) {
                accounts.remove(account);
                accounts.add(0, account);
                break;
            }
        }
        AccountUtils.setIfEthAccountsExists(accounts, this.mSharedPrefs);
        return accounts;
    }

    public void checkTokenValidityAndRefreshUser(final TokenAndUserResponse tokenResponse) {
        if (isSignedIn()) {
            new GetUserTask(this.mContext, new GetUserListener() {
                public void onPreExecute() {
                }

                public void onException(boolean unauthorized) {
                    if (unauthorized) {
                        LoginManager.this.logRefresh("refresh_access_token_checkTokenValidityAndRefreshUser");
                        LoginManager.this.refreshAccessToken(tokenResponse);
                    }
                    LoginManager.this.onRefreshException();
                }

                public void onFinally(Response<User> user) {
                    tokenResponse.onResponse(user);
                    LoginManager.this.onRefreshSuccess(((User) user.body()).getData());
                }
            }).getUser();
        } else {
            checkTokenValidity(tokenResponse);
        }
    }

    public void checkTokenValidity(TokenAndUserResponse tokenResponse) {
        getClient().getUserRx().observeOn(this.mMainScheduler).first().subscribe(LoginManager$$Lambda$1.lambdaFactory$(this, tokenResponse), LoginManager$$Lambda$2.lambdaFactory$(this, tokenResponse));
    }

    static /* synthetic */ void lambda$checkTokenValidity$0(LoginManager this_, TokenAndUserResponse tokenResponse, Pair pair) {
        Response<User> response = pair.first;
        if (response.code() == 401) {
            this_.logRefresh("refresh_access_token_checkTokenValidity");
            this_.refreshAccessToken((TokenResponse) tokenResponse);
            return;
        }
        if (response.isSuccessful()) {
            this_.mUserUpdatedConnector.get().onNext(((User) response.body()).getData());
        }
        tokenResponse.onResponse(response);
    }

    private void refreshAccessToken(final TokenResponse tokenResponse) {
        refreshAccessToken(new Callback<AccessToken>() {
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                tokenResponse.onResponseNeededRefresh(response.code() == 401);
            }

            public void onFailure(Call<AccessToken> call, Throwable t) {
                tokenResponse.onResponseNeededRefresh(false);
            }
        });
    }

    private void refreshAccessToken(final Callback<AccessToken> callback) {
        getClient().refreshTokens(getClientId(), getClientSecret(), this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_REFRESH_TOKEN, null), new CallbackWithRetrofit<AccessToken>() {
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response, Retrofit retrofit) {
                if (callback != null) {
                    callback.onResponse(call, response);
                }
                if (response.code() == 401) {
                    LoginManager.this.signout();
                } else if (response.isSuccessful()) {
                    AccessToken accessToken = (AccessToken) response.body();
                    Editor e = LoginManager.this.mSharedPrefs.edit();
                    LoginManager.this.logRefresh("token_refreshed_refreshAccessToken");
                    e.putString(Constants.KEY_ACCOUNT_ACCESS_TOKEN, accessToken.getAccessToken());
                    e.putString(Constants.KEY_ACCOUNT_REFRESH_TOKEN, accessToken.getRefreshToken());
                    e.putLong(Constants.KEY_ACCOUNT_TOKEN_EXPIRES_AT, System.currentTimeMillis() + Constants.TOKEN_EXPIRY_PERIOD);
                    e.apply();
                    CoinbaseInternal.getInstance().init(LoginManager.this.mContext, accessToken.getAccessToken());
                    Log.i(getClass().getSimpleName(), "Access token refreshed: " + accessToken.getAccessToken());
                }
            }

            public void onFailure(Call<AccessToken> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public synchronized void signin(Oauth tokens) {
        Editor e = this.mSharedPrefs.edit();
        CoinbaseInternal.getInstance().init(this.mContext, tokens.getAccessToken());
        e.putString(Constants.KEY_ACCOUNT_ACCESS_TOKEN, tokens.getAccessToken());
        e.putString(Constants.KEY_ACCOUNT_REFRESH_TOKEN, tokens.getRefreshToken());
        e.putLong(Constants.KEY_ACCOUNT_TOKEN_EXPIRES_AT, System.currentTimeMillis() + Constants.TOKEN_EXPIRY_PERIOD);
        e.putBoolean(Constants.KEY_ACCOUNT_VALID, true);
        e.apply();
        updateAccounts();
        refreshUser();
    }

    public synchronized void signin(OAuthTokensResponse tokens) {
        Editor e = this.mSharedPrefs.edit();
        CoinbaseInternal.getInstance().init(this.mContext, tokens.getAccessToken());
        e.putString(Constants.KEY_ACCOUNT_ACCESS_TOKEN, tokens.getAccessToken());
        e.putString(Constants.KEY_ACCOUNT_REFRESH_TOKEN, tokens.getRefreshToken());
        e.putLong(Constants.KEY_ACCOUNT_TOKEN_EXPIRES_AT, System.currentTimeMillis() + Constants.TOKEN_EXPIRY_PERIOD);
        e.putBoolean(Constants.KEY_ACCOUNT_VALID, true);
        e.apply();
        updateAccounts();
        refreshUser();
    }

    public void refreshUser() {
        new GetUserTask(this.mContext, new GetUserListener() {
            public void onPreExecute() {
            }

            public void onException(boolean unauthorized) {
                LoginManager.this.onRefreshException();
            }

            public void onFinally(Response<User> response) {
                LoginManager.this.onRefreshSuccess(((User) response.body()).getData());
            }
        }).getUser();
    }

    private void onRefreshException() {
        if (!isSignedIn()) {
            PreferenceManager.getDefaultSharedPreferences(this.mContext).edit().clear().apply();
        }
    }

    private void onRefreshSuccess(com.coinbase.v2.models.user.Data user) {
        saveUser(user);
        logUser(user);
    }

    private void logUser(com.coinbase.v2.models.user.Data user) {
        Crashlytics.setUserIdentifier(user.getId());
        Crashlytics.setString("country code", user.getCountry().getCode());
        Crashlytics.setString("currency code", user.getNativeCurrency());
        MixpanelTracking.getInstance().setId(user.getId());
    }

    public void saveUser(com.coinbase.v2.models.user.Data user) {
        Editor e = this.mSharedPrefs.edit();
        putStringAndLogIfEmpty(e, Constants.KEY_USER_ID, user.getId());
        putStringAndLogIfEmpty(e, Constants.KEY_ACCOUNT_EMAIL, user.getEmail());
        e.putString(Constants.KEY_FEATURE_FLAGS, Utils.curry(user.getFeatureFlags()));
        putStringAndLogIfEmpty(e, Constants.KEY_USER_COUNTRY_CODE, user.getCountry().getCode());
        e.putBoolean(Constants.KEY_USER_IN_EU, user.getCountry().getIsInEurope() == null ? false : user.getCountry().getIsInEurope().booleanValue());
        putStringAndLogIfEmpty(e, Constants.KEY_ACCOUNT_NATIVE_CURRENCY, user.getNativeCurrency());
        putStringAndLogIfEmpty(e, Constants.KEY_ACCOUNT_FULL_NAME, user.getName());
        putStringAndLogIfEmpty(e, Constants.KEY_ACCOUNT_TIME_ZONE, user.getTimeZone());
        e.putBoolean(Constants.KEY_ACCOUNT_NEEDS_USER_AGREEMENT, Utils.isUserAgreementRequired(user.getRestrictions()));
        e.apply();
    }

    private void updateAccounts() {
        Editor e = this.mSharedPrefs.edit();
        HashMap<String, Object> options = new HashMap();
        options.put(ApiConstants.LIMIT, Integer.valueOf(100));
        getClient().getAccountsRx(options).observeOn(AndroidSchedulers.mainThread()).subscribe(LoginManager$$Lambda$3.lambdaFactory$(this, e), LoginManager$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ void lambda$updateAccounts$3(LoginManager this_, Editor e, Pair pair) {
        Response<Accounts> response = pair.first;
        if (response.isSuccessful()) {
            this_.retryCount = 0;
            List<Data> accounts = ((Accounts) response.body()).getData();
            synchronized (this_) {
                SQLiteDatabase db = this_.dbManager.openDatabase();
                boolean foundPrimaryAccount = false;
                try {
                    for (Data account : accounts) {
                        if (account.getActive().booleanValue()) {
                            AccountORM.insert(db, account);
                            if (account.getPrimary().booleanValue()) {
                                e.putString(Constants.KEY_ACTIVE_ACCOUNT_ID, account.getId());
                                e.putString(Constants.KEY_ACTIVE_ACCOUNT_NAME, account.getName());
                                e.apply();
                                foundPrimaryAccount = true;
                            }
                        }
                    }
                    if (!foundPrimaryAccount) {
                        Utils.showMessage(this_.mContext, (int) R.string.primary_account_error, 1);
                    }
                } finally {
                    this_.dbManager.closeDatabase();
                }
            }
            return;
        }
        new Handler().postDelayed(LoginManager$$Lambda$5.lambdaFactory$(this_), 500);
    }

    static /* synthetic */ void lambda$null$2(LoginManager this_) {
        if (this_.retryCount < 3) {
            this_.retryCount++;
            this_.updateAccounts();
        }
    }

    static /* synthetic */ void lambda$updateAccounts$4(Throwable t) {
    }

    public void setAccountValid(boolean status, String desc) {
        Editor e = this.mSharedPrefs.edit();
        e.putBoolean(Constants.KEY_ACCOUNT_VALID, status);
        e.putString(Constants.KEY_ACCOUNT_VALID_DESC, desc);
        e.apply();
    }

    public String getAccountValid() {
        if (this.mSharedPrefs.getBoolean(Constants.KEY_ACCOUNT_VALID, false)) {
            return null;
        }
        return this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_VALID_DESC, "No msg");
    }

    public String getActiveUserCountryCode() {
        String activeUserCountryCode = this.mSharedPrefs.getString(Constants.KEY_USER_COUNTRY_CODE, null);
        logKeyIfEmpty("get_active_user_country_code", Constants.KEY_USER_COUNTRY_CODE, activeUserCountryCode);
        if (!TextUtils.isEmpty(activeUserCountryCode)) {
            return activeUserCountryCode;
        }
        activeUserCountryCode = this.mContext.getResources().getConfiguration().locale.getCountry();
        logKeyIfEmpty("get_active_user_country_code_resources", Constants.KEY_USER_COUNTRY_CODE, activeUserCountryCode);
        if (TextUtils.isEmpty(activeUserCountryCode)) {
            return Locale.US.getCountry();
        }
        return activeUserCountryCode;
    }

    public boolean getUserIsInEU() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(Constants.KEY_USER_IN_EU, false);
    }

    public List<String> getFeatureFlags() {
        return Utils.uncurry(this.mSharedPrefs.getString(Constants.KEY_FEATURE_FLAGS, null));
    }

    public String getActiveUserId() {
        return this.mSharedPrefs.getString(Constants.KEY_USER_ID, null);
    }

    public synchronized String getReceiveAddress(String accountId) {
        String cachedReceiveAddress;
        try {
            cachedReceiveAddress = AccountORM.getCachedReceiveAddress(this.dbManager.openDatabase(), accountId);
            this.dbManager.closeDatabase();
        } catch (Throwable th) {
            this.dbManager.closeDatabase();
        }
        return cachedReceiveAddress;
    }

    public String getActiveAccountId() {
        return this.mSharedPrefs.getString(Constants.KEY_ACTIVE_ACCOUNT_ID, null);
    }

    public String getActiveUserEmail() {
        return this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_EMAIL, null);
    }

    public CurrencyUnit getCurrencyUnit() {
        CurrencyUnit currencyUnit = CurrencyUnit.USD;
        if (isSignedIn() && this.mContext != null) {
            String currency = this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, null);
            if (currency != null) {
                return Utils.getCurrencyUnitByCode(currency);
            }
            return currencyUnit;
        } else if (this.mContext != null) {
            return Utils.getDeviceCurrency(this.mContext.getResources().getConfiguration().locale);
        } else {
            return currencyUnit;
        }
    }

    public synchronized String getAccessToken() {
        return this.mSharedPrefs.getString(Constants.KEY_ACCOUNT_ACCESS_TOKEN, null);
    }

    public OAuthTokensResponse getTokens(String authCode) throws CoinbaseException, IOException {
        return getClient().getTokens(getClientId(), getClientSecret(), authCode, getRedirectUri());
    }

    public synchronized boolean signout() {
        getClient().revokeToken(null);
        this.mContext.deleteDatabase(ClientCacheDatabase.DATABASE_NAME);
        SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        defaultPreferences.edit().clear().putInt(Constants.LAUNCH_MESSAGE_HASHCODE, defaultPreferences.getInt(Constants.LAUNCH_MESSAGE_HASHCODE, 0)).apply();
        this.mSignOutConnector.get().onNext(null);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_SIGN_OUT, new String[0]);
        setAccountValid(false, "logged out");
        return true;
    }

    public void sendSMS(String email, String password) throws CoinbaseException, IOException {
        getClient().sendSMS(getClientId(), getClientSecret(), email, password);
    }

    public CoinbaseInternal getClient() {
        return this.mClient;
    }

    public String getRedirectUri() {
        return "coinbase://login?csrf=" + getLoginCSRFToken();
    }

    private String getLoginCSRFToken() {
        int result = this.mSharedPrefs.getInt(Constants.KEY_LOGIN_CSRF_TOKEN, 0);
        if (result == 0) {
            result = new SecureRandom().nextInt();
            Editor e = this.mSharedPrefs.edit();
            e.putInt(Constants.KEY_LOGIN_CSRF_TOKEN, result);
            e.commit();
        }
        return Integer.toString(result);
    }

    private void logRefresh(String message) {
        long diff = this.mSharedPrefs.getLong(Constants.KEY_ACCOUNT_TOKEN_EXPIRES_AT, 0) - System.currentTimeMillis();
        Answers.getInstance().logCustom((CustomEvent) ((CustomEvent) ((CustomEvent) new CustomEvent(message).putCustomAttribute("version", Integer.valueOf(BuildConfig.VERSION_CODE))).putCustomAttribute("expiry_period", Long.valueOf(diff))).putCustomAttribute("status", diff > 0 ? "too_early" : "ok"));
        if (diff > 0) {
            this.mLogger.error("Refresh occurring too soon [" + message + "] [" + diff + "]");
        }
    }

    private void putStringAndLogIfEmpty(Editor e, String key, String value) {
        logKeyIfEmpty("put_string_user_value_empty", key, value);
        e.putString(key, value);
    }

    private void logKeyIfEmpty(String message, String key, String value) {
        if (TextUtils.isEmpty(value)) {
            Answers.getInstance().logCustom((CustomEvent) ((CustomEvent) new CustomEvent(message).putCustomAttribute("version", Integer.valueOf(BuildConfig.VERSION_CODE))).putCustomAttribute("key", key));
        }
    }
}
