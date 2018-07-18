package com.coinbase.api.internal;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.ApiInterface;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.Coinbase;
import com.coinbase.android.BuildConfig;
import com.coinbase.android.Log;
import com.coinbase.android.StethoWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.models.achSetupSession.AchSetupSession;
import com.coinbase.api.internal.models.address.Address;
import com.coinbase.api.internal.models.agreement.UserAgreement;
import com.coinbase.api.internal.models.alerts.Alerts;
import com.coinbase.api.internal.models.auth.Auth;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethods;
import com.coinbase.api.internal.models.billingAddress.BillingAddress;
import com.coinbase.api.internal.models.billingAddress.BillingAddresses;
import com.coinbase.api.internal.models.config.Config;
import com.coinbase.api.internal.models.contacts.Contacts;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.dashboard.Dashboard;
import com.coinbase.api.internal.models.emailPreferences.EmailPreferences;
import com.coinbase.api.internal.models.idology.Idology;
import com.coinbase.api.internal.models.idology.IdologyList;
import com.coinbase.api.internal.models.idology.UserAnswers;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote;
import com.coinbase.api.internal.models.institutions.Institution;
import com.coinbase.api.internal.models.jumio.JumioProfile;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocument;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocuments;
import com.coinbase.api.internal.models.paymentMethods.CardSetup;
import com.coinbase.api.internal.models.paymentMethods.verify.Verify;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumbers;
import com.coinbase.api.internal.models.policyRestrictions.Restrictions;
import com.coinbase.api.internal.models.priceCharts.PriceChart;
import com.coinbase.api.internal.models.privacy.UserPrivacyPolicy;
import com.coinbase.api.internal.models.sepaDepositInfo.SepaDepositInfo;
import com.coinbase.api.internal.models.status.Status;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.coinbase.api.internal.models.transaction.TransactionFee;
import com.coinbase.api.internal.models.wbl.PendingHolds;
import com.coinbase.v2.models.paymentMethods.PaymentMethod;
import com.coinbase.v2.models.transactions.Transactions;
import com.coinbase.v2.models.transfers.Transfer;
import com.coinbase.v2.models.user.User;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import okhttp3.Call.Factory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;

public class CoinbaseInternal extends Coinbase {
    private static final long FORCED_CACHE_TIMEOUT = 30000;
    private static final Set<String> FORCED_CACHE_URLS = new HashSet(Arrays.asList(new String[]{SLASH + ApiConstants.SERVER_VERSION + SLASH + ApiConstants.ALERTS, SLASH + ApiConstants.SERVER_VERSION + SLASH + "accounts", SLASH + ApiConstants.SERVER_VERSION + SLASH + ApiConstants.DASHBOARD, SLASH + ApiConstants.SERVER_VERSION + SLASH + "user"}));
    private static final String SLASH = "/";
    protected static CoinbaseInternal _instance = null;
    private volatile String mAdvertisingId;
    private Pair<ApiInterfaceRx, Retrofit> mAuthApiServicePairRx;
    private final HashMap<String, Pair<ApiInterface, Retrofit>> mCustomApiServices = new HashMap();
    private final HashMap<String, Pair<ApiInterfaceRx, Retrofit>> mCustomApiServicesRx = new HashMap();
    private volatile Action1<Builder> mDebugRequestModifier = null;
    private volatile boolean mHttpLoggingEnabled = false;
    private final HashMap<String, Pair<ApiInterface, Retrofit>> mInitializedInternalServices = new HashMap();
    private final HashMap<String, Pair<ApiInterfaceRx, Retrofit>> mInitializedInternalServicesRx = new HashMap();
    private final Logger mLogger = LoggerFactory.getLogger(CoinbaseInternal.class);
    private StethoWrapper mStethoWrapper;

    protected CoinbaseInternal() {
    }

    public static CoinbaseInternal getInstance() {
        if (_instance == null) {
            _instance = new CoinbaseInternal();
        }
        return _instance;
    }

    public void init(Context context, String apiKey, String apiSecret) {
        super.init(context.getApplicationContext(), apiKey, apiSecret);
        this._cache.setForcedCache(FORCED_CACHE_URLS, FORCED_CACHE_TIMEOUT);
        this.mStethoWrapper = new StethoWrapper(context);
    }

    public void init(Context context, String accessToken) {
        Context appContext = context.getApplicationContext();
        String oldAccessToken = this._accessToken;
        super.init(appContext, accessToken);
        this.mStethoWrapper = new StethoWrapper(context);
        if (!TextUtils.equals(oldAccessToken, accessToken)) {
            this.mInitializedInternalServices.clear();
            this.mInitializedInternalServicesRx.clear();
            this._cache.setForcedCache(FORCED_CACHE_URLS, FORCED_CACHE_TIMEOUT);
        }
        if (accessToken != null) {
            Log.d("ACCESS TOKEN", accessToken);
        }
    }

    public void startNetworkRecording() {
        this.mHttpLoggingEnabled = true;
    }

    public void stopNetworkRecording() {
        this.mHttpLoggingEnabled = false;
    }

    public void setDebugRequestModifier(Action1<Builder> requestModifier) {
        this.mDebugRequestModifier = requestModifier;
    }

    public void setForcedCacheEnabled(boolean enabled) {
        this._cache.setForcedCacheEnabled(enabled);
    }

    public void setAdvertisingId(String advertisingId) {
        this.mAdvertisingId = advertisingId;
    }

    public Set<OkHttpClient> getCurrentOkHttpClients() {
        Set<OkHttpClient> okHttpClients = new HashSet();
        for (Pair<ApiInterface, Retrofit> pair : this.mInitializedServices.values()) {
            Factory factory = ((Retrofit) pair.second).callFactory();
            if (factory instanceof OkHttpClient) {
                okHttpClients.add((OkHttpClient) factory);
            }
        }
        for (Pair<ApiInterface, Retrofit> pair2 : this.mInitializedInternalServices.values()) {
            factory = ((Retrofit) pair2.second).callFactory();
            if (factory instanceof OkHttpClient) {
                okHttpClients.add((OkHttpClient) factory);
            }
        }
        for (Pair<ApiInterfaceRx, Retrofit> pair3 : this.mInitializedInternalServicesRx.values()) {
            factory = ((Retrofit) pair3.second).callFactory();
            if (factory instanceof OkHttpClient) {
                okHttpClients.add((OkHttpClient) factory);
            }
        }
        return new HashSet(okHttpClients);
    }

    public boolean verifyCallback(String body, String signature) {
        return this._callbackVerifier.verifyCallback(body, signature);
    }

    protected Interceptor buildOAuthInterceptor() {
        return new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                if (TextUtils.isEmpty(CoinbaseInternal.this._accessToken)) {
                    CoinbaseInternal.this.mLogger.error("access_token_empty path [" + chain.request().url().encodedPath() + "]");
                }
                return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + CoinbaseInternal.this._accessToken).build());
            }
        };
    }

    protected Interceptor build2FAInterceptor(final String token) {
        return new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("CB-2FA-TOKEN", token).build());
            }
        };
    }

    protected Interceptor deviceInfoInterceptor() {
        final String androidId = Secure.getString(this._context.getContentResolver(), "android_id");
        return new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                String advertisingId = CoinbaseInternal.this.mAdvertisingId;
                Builder addHeader = chain.request().newBuilder().addHeader("X-Device-Model", Build.MODEL).addHeader("X-Device-Product", Build.PRODUCT).addHeader("X-Device-Brand", Build.BRAND).addHeader("X-Device-Manufacturer", Build.MANUFACTURER).addHeader("X-System-Name", Build.DEVICE).addHeader("X-System-Version", String.valueOf(VERSION.SDK_INT)).addHeader("X-System-Board", Build.BOARD).addHeader("X-System-BOOTLOADER", Build.BOOTLOADER).addHeader("X-Os-Version", VERSION.RELEASE).addHeader("X-Os-Name", "Android").addHeader("X-Android-Id", androidId == null ? "" : androidId);
                String str = "X-Advertising-Id";
                if (advertisingId == null) {
                    advertisingId = "";
                }
                Builder newRequestBuilder = addHeader.addHeader(str, advertisingId).addHeader("Accept", "application/json");
                if (CoinbaseInternal.this.mDebugRequestModifier != null) {
                    CoinbaseInternal.this.mDebugRequestModifier.call(newRequestBuilder);
                }
                return chain.proceed(newRequestBuilder.build());
            }
        };
    }

    protected Interceptor networkSniffingInterceptor() {
        return this.mStethoWrapper.getNetworkSnifferInterceptor();
    }

    static /* synthetic */ void lambda$loggingInterceptor$0(CoinbaseInternal this_, String message) {
        if (this_.mHttpLoggingEnabled) {
            int length = message.length();
            for (int i = 0; i < length; i += 1024) {
                if (i + 1024 < length) {
                    Log.i("Coinbase", message.substring(i, i + 1024));
                } else {
                    Log.i("Coinbase", message.substring(i, length));
                }
            }
        }
    }

    protected Interceptor loggingInterceptor() {
        return CoinbaseInternal$$Lambda$1.lambdaFactory$();
    }

    protected String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    protected String getVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    private synchronized Pair<ApiInterface, Retrofit> getService(String url) {
        Pair<ApiInterface, Retrofit> pair;
        if (this.mInitializedInternalServices.containsKey(url)) {
            pair = (Pair) this.mInitializedInternalServices.get(url);
        } else {
            OkHttpClient.Builder clientBuilder = generateClientBuilder(this._sslContext);
            if (this._accessToken != null) {
                clientBuilder.addInterceptor(buildOAuthInterceptor());
            }
            clientBuilder.addInterceptor(buildVersionInterceptor());
            clientBuilder.addInterceptor(languageInterceptor());
            clientBuilder.addInterceptor(this._cache.createInterceptor());
            clientBuilder.addInterceptor(deviceInfoInterceptor());
            clientBuilder.addInterceptor(loggingInterceptor());
            clientBuilder.addNetworkInterceptor(networkSniffingInterceptor());
            Retrofit retrofit = new Retrofit.Builder().baseUrl(fixBaseUrl(url)).client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
            Pair<ApiInterface, Retrofit> servicePair = new Pair((ApiInterface) retrofit.create(ApiInterface.class), retrofit);
            this.mInitializedInternalServices.put(url, servicePair);
            pair = servicePair;
        }
        return pair;
    }

    private Pair<ApiInterface, Retrofit> getInternalApiService() {
        return getService(this._baseV2ApiUrl.toString());
    }

    private synchronized Pair<ApiInterface, Retrofit> get2FAApiService(String token) {
        Retrofit retrofit;
        OkHttpClient.Builder clientBuilder = generateClientBuilder(this._sslContext);
        if (this._accessToken != null) {
            clientBuilder.addInterceptor(buildOAuthInterceptor());
        }
        clientBuilder.addInterceptor(buildVersionInterceptor());
        if (token == null) {
            token = "l";
        }
        clientBuilder.addInterceptor(build2FAInterceptor(token));
        retrofit = new Retrofit.Builder().baseUrl(fixBaseUrl(this._baseV2ApiUrl.toString())).client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
        return new Pair((ApiInterface) retrofit.create(ApiInterface.class), retrofit);
    }

    private synchronized Pair<ApiInterface, Retrofit> getCustomApiService(String url) {
        Pair<ApiInterface, Retrofit> pair;
        if (this.mCustomApiServices.containsKey(url)) {
            pair = (Pair) this.mCustomApiServices.get(url);
        } else {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(fixBaseUrl(url)).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
            Pair<ApiInterface, Retrofit> customApiServicePair = new Pair((ApiInterface) retrofit.create(ApiInterface.class), retrofit);
            this.mCustomApiServices.put(url, customApiServicePair);
            pair = customApiServicePair;
        }
        return pair;
    }

    private synchronized Pair<ApiInterfaceRx, Retrofit> getServiceRx(String url) {
        Pair<ApiInterfaceRx, Retrofit> pair;
        if (this.mInitializedInternalServicesRx.containsKey(url)) {
            pair = (Pair) this.mInitializedInternalServicesRx.get(url);
        } else {
            CallAdapter.Factory create;
            OkHttpClient.Builder clientBuilder = generateClientBuilder(this._sslContext);
            if (this._accessToken != null) {
                clientBuilder.addInterceptor(buildOAuthInterceptor());
            }
            clientBuilder.addInterceptor(buildVersionInterceptor());
            clientBuilder.addInterceptor(languageInterceptor());
            clientBuilder.addInterceptor(this._cache.createInterceptor());
            clientBuilder.addInterceptor(deviceInfoInterceptor());
            clientBuilder.addInterceptor(loggingInterceptor());
            clientBuilder.addNetworkInterceptor(networkSniffingInterceptor());
            Retrofit.Builder client = new Retrofit.Builder().baseUrl(fixBaseUrl(url)).client(clientBuilder.build());
            if (this._backgroundScheduler == null) {
                create = RxJavaCallAdapterFactory.create();
            } else {
                create = RxJavaCallAdapterFactory.createWithScheduler(this._backgroundScheduler);
            }
            Retrofit retrofit = client.addCallAdapterFactory(create).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
            Pair<ApiInterfaceRx, Retrofit> servicePairRx = new Pair((ApiInterfaceRx) retrofit.create(ApiInterfaceRx.class), retrofit);
            this.mInitializedInternalServicesRx.put(url, servicePairRx);
            pair = servicePairRx;
        }
        return pair;
    }

    private Pair<ApiInterfaceRx, Retrofit> getInternalApiServiceRx() {
        return getServiceRx(this._baseV2ApiUrl.toString());
    }

    private synchronized Pair<ApiInterfaceRx, Retrofit> getAuthApiServiceRx() {
        Pair<ApiInterfaceRx, Retrofit> pair;
        if (this.mAuthApiServicePairRx != null) {
            pair = this.mAuthApiServicePairRx;
        } else {
            CallAdapter.Factory create;
            OkHttpClient.Builder clientBuilder = generateClientBuilder(this._sslContext);
            Retrofit.Builder client = new Retrofit.Builder().baseUrl(fixBaseUrl(this._baseApiUrl.toString())).client(clientBuilder.build());
            if (this._backgroundScheduler == null) {
                create = RxJavaCallAdapterFactory.create();
            } else {
                create = RxJavaCallAdapterFactory.createWithScheduler(this._backgroundScheduler);
            }
            Retrofit retrofit = client.addCallAdapterFactory(create).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
            this.mAuthApiServicePairRx = new Pair((ApiInterfaceRx) retrofit.create(ApiInterfaceRx.class), retrofit);
            pair = this.mAuthApiServicePairRx;
        }
        return pair;
    }

    private synchronized Pair<ApiInterfaceRx, Retrofit> get2FAApiServiceRx(String token) {
        Retrofit retrofit;
        CallAdapter.Factory create;
        OkHttpClient.Builder clientBuilder = generateClientBuilder(this._sslContext);
        if (this._accessToken != null) {
            clientBuilder.addInterceptor(buildOAuthInterceptor());
        }
        clientBuilder.addInterceptor(buildVersionInterceptor());
        if (token == null) {
            token = "l";
        }
        clientBuilder.addInterceptor(build2FAInterceptor(token));
        Retrofit.Builder client = new Retrofit.Builder().baseUrl(fixBaseUrl(this._baseV2ApiUrl.toString())).client(clientBuilder.build());
        if (this._backgroundScheduler == null) {
            create = RxJavaCallAdapterFactory.create();
        } else {
            create = RxJavaCallAdapterFactory.createWithScheduler(this._backgroundScheduler);
        }
        retrofit = client.addCallAdapterFactory(create).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
        return new Pair((ApiInterfaceRx) retrofit.create(ApiInterfaceRx.class), retrofit);
    }

    private synchronized Pair<ApiInterfaceRx, Retrofit> getCustomApiServiceRx(String url) {
        Pair<ApiInterfaceRx, Retrofit> pair;
        if (this.mCustomApiServicesRx.containsKey(url)) {
            pair = (Pair) this.mCustomApiServicesRx.get(url);
        } else {
            CallAdapter.Factory create;
            Retrofit.Builder baseUrl = new Retrofit.Builder().baseUrl(fixBaseUrl(url));
            if (this._backgroundScheduler == null) {
                create = RxJavaCallAdapterFactory.create();
            } else {
                create = RxJavaCallAdapterFactory.createWithScheduler(this._backgroundScheduler);
            }
            Retrofit retrofit = baseUrl.addCallAdapterFactory(create).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create())).build();
            Pair<ApiInterfaceRx, Retrofit> customApiServicePairRx = new Pair((ApiInterfaceRx) retrofit.create(ApiInterfaceRx.class), retrofit);
            this.mCustomApiServicesRx.put(url, customApiServicePairRx);
            pair = customApiServicePairRx;
        }
        return pair;
    }

    public Observable<Pair<retrofit2.Response<Auth>, Retrofit>> getAuthCodeRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getAuthApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAuthCode(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$2.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Contacts>, Retrofit>> getContactsListRx(String query) {
        HashMap<String, Object> params = createContactsListParams(query);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getContacts(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$3.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Address>, Retrofit>> createAddressRx(String address1, String address2, String city, String state, String zip) {
        HashMap<String, Object> params = createAddressParams(address1, address2, city, state, zip);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createAddress(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$4.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> acceptUserAgreementRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).acceptUserAgreement(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$5.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<UserAgreement>, Retrofit>> getUserAgreementRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getUserAgreement(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$6.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<UserPrivacyPolicy>, Retrofit>> getUserPrivacyPolicy() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getUserPrivacyPolicy(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$7.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> createDeviceTokenRx(String deviceId, String gcmToken) {
        HashMap<String, Object> params = createDeviceTokenParams(deviceId, gcmToken);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createDeviceToken(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$8.lambdaFactory$()).first();
    }

    public void createPriceAlert(HashMap<String, Object> params, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        ((ApiInterface) apiRetrofitPair.first).createPriceAlert(params).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PRICE_ALERT_CREATED, new String[0]);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> createPriceAlertRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createPriceAlert(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$9.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> deletePriceAlertRx(String deviceId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deletePriceAlert(deviceId, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$10.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<CardSetup>, Retrofit>> paymentCardSetupRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).paymentCardSetup(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$11.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> processPaymentCardRx(String url, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getCustomApiServiceRx(url);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).processPaymentCard("", params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$12.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Verify>, Retrofit>> verifyPaymentCardRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).verifyPaymentCard(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$13.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> getPaymentMethodVerifiedRx(String paymentMethodId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPaymentMethodVerified(paymentMethodId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$14.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> completeCardCDVRx(String paymentMethodId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).verifyCardCDV(paymentMethodId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$15.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> createPlaidPaymentMethodRx(String publicToken, String accountId) {
        HashMap<String, Object> params = new HashMap();
        params.put("type", ApiConstants.ACH_BANK_ACCOUNT);
        params.put(ApiConstants.PLAID_PUBLIC_TOKEN, publicToken);
        params.put(ApiConstants.PLAID_ACCOUNT_ID, accountId);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createPlaidPaymentMethod(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$16.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PhoneNumbers>, Retrofit>> getPhoneNumbersRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPhoneNumbers(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$17.lambdaFactory$()).first();
    }

    public void createPhoneNumber(String token, HashMap<String, Object> params, final CallbackWithRetrofit<PhoneNumber> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = get2FAApiService(token);
        ((ApiInterface) apiRetrofitPair.first).createPhoneNumber(params).enqueue(new Callback<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, retrofit2.Response<PhoneNumber> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<PhoneNumber>, Retrofit>> createPhoneNumberRx(String token, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = get2FAApiServiceRx(token);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createPhoneNumber(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$18.lambdaFactory$()).first();
    }

    public void verifyPhoneNumber(HashMap<String, Object> params, String phoneNumberId, final CallbackWithRetrofit<PhoneNumber> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        ((ApiInterface) apiRetrofitPair.first).verifyPhoneNumber(phoneNumberId, params).enqueue(new Callback<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, retrofit2.Response<PhoneNumber> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<PhoneNumber>, Retrofit>> verifyPhoneNumberRx(HashMap<String, Object> params, String phoneNumberId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).verifyPhoneNumber(phoneNumberId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$19.lambdaFactory$()).first();
    }

    public void deletePhoneNumber(String token, String phoneNumberId, final CallbackWithRetrofit<PhoneNumber> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = get2FAApiService(token);
        ((ApiInterface) apiRetrofitPair.first).deletePhoneNumber(phoneNumberId).enqueue(new Callback<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, retrofit2.Response<PhoneNumber> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> resendTwoFactorTokenRx(String phoneNumberId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).resendTwoFactorCode(phoneNumberId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$20.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PhoneNumber>, Retrofit>> deletePhoneNumberRx(String token, String phoneNumberId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = get2FAApiServiceRx(token);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deletePhoneNumber(phoneNumberId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$21.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<BillingAddresses>, Retrofit>> getBillingAddresses() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getBillingAddresses(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$22.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<BillingAddress>, Retrofit>> getBillingAddressRx(String billingAddressId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getBillingAddress(billingAddressId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$23.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<BillingAddress>, Retrofit>> createBillingAddressRx(String address1, String address2, String city, String state, String zip, String country) {
        HashMap<String, Object> params = createBillingAddressParams(address1, address2, city, state, zip, country);
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createBillingAddress(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$24.lambdaFactory$()).first();
    }

    public void deleteBillingAddresses(String billingAddressId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        ((ApiInterface) apiRetrofitPair.first).deleteBillingAddress(billingAddressId).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> deleteBillingAddressesRx(String billingAddressId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deleteBillingAddress(billingAddressId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$25.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<AvailablePaymentMethods>, Retrofit>> getAvailablePaymentMethodsRx(String type) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAvailablePaymentMethods(type), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$26.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Restrictions>, Retrofit>> getPolicyRestrictionsRX(String type) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPolicyRestrictions(type), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$27.lambdaFactory$()).first();
    }

    public void getSepaDepositInformation(final CallbackWithRetrofit<SepaDepositInfo> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        ((ApiInterface) apiRetrofitPair.first).getSepaInformation().enqueue(new Callback<SepaDepositInfo>() {
            public void onResponse(Call<SepaDepositInfo> call, retrofit2.Response<SepaDepositInfo> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<SepaDepositInfo> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public Observable<Pair<retrofit2.Response<SepaDepositInfo>, Retrofit>> getSepaDepositInformationRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSepaInformation(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$28.lambdaFactory$()).first();
    }

    public Call deletePaymentmethod(String paymentMethodId, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).deletePaymentMethod(paymentMethodId);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> deletePaymentmethodRx(String paymentMethodId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deletePaymentMethod(paymentMethodId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$29.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<AchSetupSession>, Retrofit>> createAchSetupSessionRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createAchSetupSession(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$30.lambdaFactory$()).first();
    }

    public Call deleteAchSetupSession(String id, final CallbackWithRetrofit<Void> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).deleteAchSetupSession(id);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> deleteAchSetupSessionRx(String id) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).deleteAchSetupSession(id), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$31.lambdaFactory$()).first();
    }

    public Call sendMFAToken(HashMap<String, Object> params, String sessionId, final CallbackWithRetrofit<AchSetupSession> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).sendMFA(sessionId, params);
        call.enqueue(new Callback<AchSetupSession>() {
            public void onResponse(Call<AchSetupSession> call, retrofit2.Response<AchSetupSession> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<AchSetupSession> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<AchSetupSession>, Retrofit>> sendMFATokenRx(HashMap<String, Object> params, String sessionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).sendMFA(sessionId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$32.lambdaFactory$()).first();
    }

    public Call submitMFAToken(HashMap<String, Object> params, String sessionId, final CallbackWithRetrofit<AchSetupSession> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).submitMFA(sessionId, params);
        call.enqueue(new Callback<AchSetupSession>() {
            public void onResponse(Call<AchSetupSession> call, retrofit2.Response<AchSetupSession> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<AchSetupSession> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<AchSetupSession>, Retrofit>> submitMFATokenRx(HashMap<String, Object> params, String sessionId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).submitMFA(sessionId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$33.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> createBankWithAchSetupSessionRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createBankWithAchSetupSession(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$34.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> createBankManuallyRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createBankManually(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$35.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> startAchVerificationRx(String paymentMethodId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).startAchVerification(paymentMethodId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$36.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PaymentMethod>, Retrofit>> completeCDVVerificationRx(String paymentMethodId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).completeCDVVerification(paymentMethodId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$37.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<PriceChart>, Retrofit>> getPriceChartRx(HashMap<String, Object> params, String baseCurrency, String currency) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getPriceChart(baseCurrency, currency, cleanQueryMap(params)), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$38.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Transfer>, Retrofit>> completeBuyRx(String accountId, String buyId, HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).completeBuy(accountId, buyId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$39.lambdaFactory$()).first();
    }

    public Call updateUser(HashMap<String, Object> params, final CallbackWithRetrofit<User> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).updateUser(params);
        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<User> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<User>, Retrofit>> updateUserRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).updateUser(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$40.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<User>, Retrofit>> createUserRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createUser(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$41.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Config>, Retrofit>> getConfigRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getConfig(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$42.lambdaFactory$()).first();
    }

    public Call getInstantExchangeQuote(HashMap<String, Object> params, String accountId, final CallbackWithRetrofit<InstantExchangeQuote> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getInstantExchangeQuote(accountId, params);
        call.enqueue(new Callback<InstantExchangeQuote>() {
            public void onResponse(Call<InstantExchangeQuote> call, retrofit2.Response<InstantExchangeQuote> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<InstantExchangeQuote> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<InstantExchangeQuote>, Retrofit>> getInstantExchangeQuoteRx(HashMap<String, Object> params, String accountId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getInstantExchangeQuote(accountId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$43.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Institution>, Retrofit>> getAchInstitutionsRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAchInstitutions(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$44.lambdaFactory$()).first();
    }

    public Call getJumioProfiles(final CallbackWithRetrofit<JumioProfiles> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getJumioProfiles();
        call.enqueue(new Callback<JumioProfiles>() {
            public void onResponse(Call<JumioProfiles> call, retrofit2.Response<JumioProfiles> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<JumioProfiles> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<JumioProfiles>, Retrofit>> getJumioProfilesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getJumioProfiles(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$45.lambdaFactory$()).first();
    }

    public Call createJumioProfile(String countryCode, String idType, byte[] frontImage, byte[] backImage, byte[] faceImage, final CallbackWithRetrofit<JumioProfile> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        RequestBody[] bodies = createJumioProfileRequestBodies(countryCode, idType, frontImage, backImage, faceImage);
        Call call = ((ApiInterface) apiRetrofitPair.first).createJumioProfile(bodies[0], bodies[1], bodies[2], bodies[3], bodies[4]);
        call.enqueue(new Callback<JumioProfile>() {
            public void onResponse(Call<JumioProfile> call, retrofit2.Response<JumioProfile> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<JumioProfile> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<JumioProfile>, Retrofit>> createJumioProfileRx(String countryCode, String idType, byte[] frontImage, byte[] backImage, byte[] faceImage) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        RequestBody[] bodies = createJumioProfileRequestBodies(countryCode, idType, frontImage, backImage, faceImage);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createJumioProfile(bodies[0], bodies[1], bodies[2], bodies[3], bodies[4]), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$46.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<SupportedDocuments>, Retrofit>> getJumioSupportedDocumentsRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getJumioSupportedDocuments(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$47.lambdaFactory$()).first();
    }

    public Call getJumioSupportedDocument(String countryCode, final CallbackWithRetrofit<SupportedDocument> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getInternalApiService();
        Call call = ((ApiInterface) apiRetrofitPair.first).getJumioSupportedDocument(countryCode);
        call.enqueue(new Callback<SupportedDocument>() {
            public void onResponse(Call<SupportedDocument> call, retrofit2.Response<SupportedDocument> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<SupportedDocument> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<SupportedDocument>, Retrofit>> getJumioSupportedDocumentRX(String countryCode) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getJumioSupportedDocument(countryCode), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$48.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<TransactionFee>, Retrofit>> getTransactionFee(String fromAccountId, String toAddress) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        HashMap<String, Object> params = new HashMap();
        params.put("to", toAddress);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getTransactionFee(fromAccountId, params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$49.lambdaFactory$()).first();
    }

    public Call getCoinbaseStatus(final CallbackWithRetrofit<Status> callback) {
        final Pair<ApiInterface, Retrofit> apiRetrofitPair = getCustomApiService(ApiConstants.STATUS_PAGE_URL);
        Call call = ((ApiInterface) apiRetrofitPair.first).getCoinbaseStatus();
        call.enqueue(new Callback<Status>() {
            public void onResponse(Call<Status> call, retrofit2.Response<Status> response) {
                if (callback != null) {
                    callback.onResponse(call, response, (Retrofit) apiRetrofitPair.second);
                }
            }

            public void onFailure(Call<Status> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
        return call;
    }

    public Observable<Pair<retrofit2.Response<Status>, Retrofit>> getCoinbaseStatus() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getCustomApiServiceRx(ApiConstants.STATUS_PAGE_URL);
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getCoinbaseStatus(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$50.lambdaFactory$()).first();
    }

    public Observable<Pair<retrofit2.Response<Idology>, Retrofit>> createIdentificationVerificationRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).createIdentityVerification(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$51.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Idology>, Retrofit>> submitAnswersRx(String idologyId, UserAnswers answers) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).submitAnswers(idologyId, answers), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$52.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<IdologyList>, Retrofit>> getIdentificationVerificationsRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getIdentityVerifications(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$53.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<IdologyOptions>, Retrofit>> getCoinbaseUsesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getCoinbaseUses(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$54.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<IdologyOptions>, Retrofit>> getSourceOfFundsRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getSourceOfFunds(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$55.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<IdologyOptions>, Retrofit>> getJobTitlesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getJobTitles(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$56.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Transactions>, Retrofit>> getNextTransactionsRx(String nextUri) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getServiceRx(this._baseApiUrl.toString());
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getNextTransactions(nextUri), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$57.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Currencies>, Retrofit>> getCryptoCurrenciesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getCryptoCurrencies(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$58.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Dashboard>, Retrofit>> getDashboardRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getDashboard(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$59.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Alerts>, Retrofit>> getAlertsRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getAlerts(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$60.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Tiers>, Retrofit>> getTiersRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getTiers(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$61.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<PendingHolds>, Retrofit>> getHoldBalancesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getHoldBalances(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$62.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<EmailPreferences>, Retrofit>> getEmailPreferencesRx() {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getEmailPreferences(), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$63.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> updateEmailPreferencesRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).updateEmailPreferences(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$64.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Void>, Retrofit>> sendGdprRequestsRx(HashMap<String, Object> params) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).sendGdprRequests(params), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$65.lambdaFactory$());
    }

    public Observable<Pair<retrofit2.Response<Transfer>, Retrofit>> getCommitBuyStatusRx(String accountId, String buyId) {
        Pair<ApiInterfaceRx, Retrofit> apiRetrofitPair = getInternalApiServiceRx();
        return Observable.combineLatest(((ApiInterfaceRx) apiRetrofitPair.first).getCommitBuyStatus(accountId, buyId), Observable.just(apiRetrofitPair.second), CoinbaseInternal$$Lambda$66.lambdaFactory$());
    }

    private String fixBaseUrl(String url) {
        return (TextUtils.isEmpty(url) || url.endsWith(SLASH)) ? url : url + SLASH;
    }

    private HashMap<String, Object> createContactsListParams(String query) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.PAGE, Integer.valueOf(1));
        if (query != null) {
            params.put(ApiConstants.QUERY, query);
        }
        return params;
    }

    private HashMap<String, Object> createAddressParams(String address1, String address2, String city, String state, String zip) {
        HashMap<String, Object> params = new HashMap();
        if (address1 != null) {
            params.put(ApiConstants.LINE1, address1);
        }
        if (address2 != null) {
            params.put(ApiConstants.LINE2, address2);
        }
        if (city != null) {
            params.put(ApiConstants.CITY, city);
        }
        if (state != null) {
            params.put("state", state);
        }
        if (zip != null) {
            params.put(ApiConstants.POSTAL_CODE, zip);
        }
        return params;
    }

    private HashMap<String, Object> createDeviceTokenParams(String deviceId, String gcmToken) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.DEVICE_ID, deviceId);
        params.put("token", gcmToken);
        params.put(ApiConstants.PLATFORM, ApiConstants.GCM);
        params.put(ApiConstants.APPLICATION_ID, ApiConstants.COINBASE_ANDROID);
        params.put("locale", Locale.getDefault().getLanguage());
        String str = "tz_offset";
        params.put(str, new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime()));
        return params;
    }

    private HashMap<String, Object> createBillingAddressParams(String address1, String address2, String city, String state, String zip, String country) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.LINE1, address1);
        params.put(ApiConstants.LINE2, address2);
        params.put(ApiConstants.CITY, city);
        params.put("state", state);
        params.put(ApiConstants.POSTAL_CODE, zip);
        params.put("country", country);
        return params;
    }

    private RequestBody[] createJumioProfileRequestBodies(String countryCode, String idType, byte[] frontImage, byte[] backImage, byte[] faceImage) {
        RequestBody[] requestBodies = new RequestBody[]{RequestBody.create(MediaType.parse("text/plain"), countryCode), RequestBody.create(MediaType.parse("text/plain"), idType), RequestBody.create(MediaType.parse("image/png"), frontImage), null, null};
        if (backImage != null) {
            requestBodies[3] = RequestBody.create(MediaType.parse("image/png"), backImage);
        }
        if (faceImage != null) {
            requestBodies[4] = RequestBody.create(MediaType.parse("image/png"), faceImage);
        }
        return requestBodies;
    }
}
