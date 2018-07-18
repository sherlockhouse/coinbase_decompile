package com.tenjin.android;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.coinbase.api.internal.ApiConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.tenjin.android.ReferrerUpdater.OnReferrerListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class TenjinSDK {
    private static long CONNECT_THRESHOLD = 30000;
    private static final Set<String> CUR_SET = new HashSet(Arrays.asList(CUR_VALUES));
    private static final String[] CUR_VALUES = new String[]{"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SPL", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWD"};
    private static long EVENT_THRESHOLD = 1000;
    private static final AtomicBoolean advertiserIdReceived = new AtomicBoolean(false);
    private static final AtomicBoolean broadCastReferrerReceived = new AtomicBoolean(false);
    private static final AtomicBoolean connectReady = new AtomicBoolean(false);
    private static final AtomicBoolean connectSent = new AtomicBoolean(false);
    private static final AtomicBoolean deepLinkSent = new AtomicBoolean(false);
    private static final AtomicBoolean googleInstallReferrerError = new AtomicBoolean(false);
    private static final AtomicBoolean googleInstallReferrerReceived = new AtomicBoolean(false);
    private static final AtomicBoolean googleInstallReferrerRequested = new AtomicBoolean(false);
    private static final AtomicBoolean googleInstallReferrerSuccess = new AtomicBoolean(false);
    private static TenjinSDK tenjinSDK;
    private String advertiserIdParam;
    private AdvertiserInfo advertiserInfo;
    private String apiKey;
    private Callback callback;
    private boolean clickedTenjinLink = false;
    private final Object connectReadyObject;
    private AsyncTask connectTask = null;
    private Context context;
    private String deferredDeeplink = null;
    private Map<String, Long> eventProcessingQueue = Collections.synchronizedMap(new LinkedHashMap());
    private Map<String, Object> eventQueue = Collections.synchronizedMap(new LinkedHashMap());
    private long googleInstallReferrerCallbackTime = 0;
    private String googleInstallReferrerClickTime;
    private InstallReferrerClient googleInstallReferrerClient;
    private String googleInstallReferrerInstallTime;
    private String googleInstallReferrerParam;
    private InstallReferrerStateListener googleInstallReferrerStateListener = new 3(this);
    private String limitAdTrackingParam;
    private OnReferrerListener onReferrerListener;
    private long referrerCallbackTime = 0;
    private int referrerRetryCount = 0;
    private Map<String, String> result = new HashMap();
    private long startTime = 0;

    public static TenjinSDK getInstance(Context context, String apiKey) {
        if (context == null || apiKey == null) {
            return null;
        }
        if (tenjinSDK == null) {
            tenjinSDK = new TenjinSDK(context, apiKey);
        }
        return tenjinSDK;
    }

    private TenjinSDK(Context context, String apiKey) {
        this.context = context.getApplicationContext();
        this.apiKey = apiKey;
        this.startTime = new Date().getTime();
        this.connectReadyObject = new Object();
        this.onReferrerListener = new 1(this);
    }

    public void connect() {
        connect(null);
    }

    public void connect(String deferredAppUri) {
        if (deferredAppUri != null) {
            this.deferredDeeplink = deferredAppUri;
        }
        if (!eventIsProcessing("connect") && this.connectTask == null) {
            if (this.advertiserIdParam == null) {
                new AsyncGetAdvertisingInfo(this, null).execute(new Void[0]);
            }
            if (!(hasAnyReferrer() || googleInstallReferrerReceived.get() || this.googleInstallReferrerClient != null)) {
                fetchGoogleInstallReferrer();
            }
            this.connectTask = null;
            this.connectTask = new AsyncConnect(this, null).execute(new Void[0]);
        }
    }

    private void notifyConnect(String source) {
        if (advertiserIdReceived.get() && broadCastReferrerReceived.get() && !googleInstallReferrerRequested.get()) {
            synchronized (this.connectReadyObject) {
                connectReady.set(true);
                this.connectReadyObject.notifyAll();
            }
        } else if (advertiserIdReceived.get() && googleInstallReferrerRequested.get() && googleInstallReferrerReceived.get()) {
            synchronized (this.connectReadyObject) {
                connectReady.set(true);
                this.connectReadyObject.notifyAll();
            }
        } else if (advertiserIdReceived.get() && googleInstallReferrerRequested.get() && googleInstallReferrerError.get() && broadCastReferrerReceived.get()) {
            synchronized (this.connectReadyObject) {
                connectReady.set(true);
                this.connectReadyObject.notifyAll();
            }
        }
    }

    private boolean hasBroadCastReferrer() {
        return this.context.getSharedPreferences("tenjinInstallPreferences", 0).getBoolean("containsReferrerKey", false);
    }

    private boolean hasGoogleReferrer() {
        return this.context.getSharedPreferences("tenjinInstallPreferences", 0).getBoolean("tenjinGoogleInstallContainsReferrerKey", false);
    }

    private boolean hasAnyReferrer() {
        return hasBroadCastReferrer() || hasGoogleReferrer();
    }

    private boolean isReadyToConnect() {
        return advertiserIdReceived.get() && hasAnyReferrer();
    }

    private Map<String, String> getParams() {
        return getParams("");
    }

    private Map<String, String> getParams(String eventName) {
        Map<String, String> params = new HashMap();
        try {
            SharedPreferences settings = this.context.getSharedPreferences("tenjinInstallPreferences", 0);
            String referrerString = settings.getString("tenjinInstallReferrer", null);
            String limitAdTracking = getLimitAdTracking();
            String advertisingID = getAdvertisingId();
            String platform = getPlatform();
            String bundleId = this.context.getPackageName();
            String manufacturerAndModel = getManufacturerAndModel();
            String timeZone = TimeZone.getDefault().getID();
            String carrierName = getCarrierName();
            String connectionType = getConnectivity();
            if (!(advertisingID == null || advertisingID.equals(""))) {
                params.put("bundle_id", bundleId);
                params.put("advertising_id", advertisingID);
                params.put(ApiConstants.PLATFORM, platform);
                params.put("limit_ad_tracking", limitAdTracking);
                params.put("os_version", String.valueOf(VERSION.SDK_INT));
                params.put("app_version", this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName + "." + this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode);
                params.put("sdk_version", "1.8.3");
                params.put(ApiConstants.DEVICE, manufacturerAndModel);
                params.put("timezone", timeZone);
                if (!(this.googleInstallReferrerParam == null || Boolean.valueOf(settings.getBoolean("tenjinGoogleInstallReferrerSent", false)).booleanValue())) {
                    params.put("referrer", this.googleInstallReferrerParam);
                    if (this.googleInstallReferrerClickTime != null) {
                        params.put("referrer_click_ts", this.googleInstallReferrerClickTime);
                    }
                    if (this.googleInstallReferrerInstallTime != null) {
                        params.put("referrer_install_ts", this.googleInstallReferrerInstallTime);
                    }
                    if (!(this.startTime == 0 || this.googleInstallReferrerCallbackTime == 0)) {
                        params.put("referrer_load_time", new DecimalFormat("0.00").format(((double) (this.googleInstallReferrerCallbackTime - this.startTime)) / 1000.0d));
                    }
                }
                if (!(referrerString == null || Boolean.valueOf(settings.getBoolean("tenjinInstallReferrerSent", false)).booleanValue())) {
                    if (this.googleInstallReferrerParam != null) {
                        params.put("broadcast_referrer", referrerString);
                    } else {
                        params.put("referrer", referrerString);
                    }
                    if (!(this.startTime == 0 || this.referrerCallbackTime == 0)) {
                        params.put("broadcast_referrer_load_time", new DecimalFormat("0.00").format(((double) (this.referrerCallbackTime - this.startTime)) / 1000.0d));
                    }
                }
                if (this.deferredDeeplink != null) {
                    String decoded_url = URLDecoder.decode(this.deferredDeeplink, "UTF-8");
                    String decoded_url_check = URLDecoder.decode(decoded_url, "UTF-8");
                    String decoded_url_double_check = URLDecoder.decode(decoded_url_check, "UTF-8");
                    String encoded_url = URLEncoder.encode(this.deferredDeeplink, "UTF-8");
                    try {
                        if (this.deferredDeeplink == decoded_url) {
                            encoded_url = URLEncoder.encode(decoded_url, "UTF-8");
                        } else if (decoded_url == decoded_url_check) {
                            encoded_url = URLEncoder.encode(decoded_url_check, "UTF-8");
                        } else if (decoded_url_check == decoded_url_double_check) {
                            encoded_url = URLEncoder.encode(decoded_url_double_check, "UTF-8");
                        } else {
                            encoded_url = URLEncoder.encode(this.deferredDeeplink, "UTF-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                    }
                    params.put("deeplink_url", encoded_url);
                }
                params.put("carrier", carrierName);
                params.put("connection_type", connectionType);
                params.put("screen_width", String.valueOf(this.context.getResources().getDisplayMetrics().widthPixels));
                params.put("screen_height", String.valueOf(this.context.getResources().getDisplayMetrics().heightPixels));
                params.put("language", this.context.getResources().getConfiguration().locale.getLanguage().toString());
                params.put("country", this.context.getResources().getConfiguration().locale.getCountry().toString());
                params.put("os_version_release", String.valueOf(VERSION.RELEASE));
                params.put("build_id", String.valueOf(Build.ID));
                params.put("device_model", String.valueOf(Build.MODEL));
                params.put("locale", Locale.getDefault().toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return params;
    }

    private String getCarrierName() {
        String carrierName = "";
        try {
            carrierName = ((TelephonyManager) this.context.getSystemService("phone")).getSimOperatorName();
        } catch (Exception e) {
        }
        return carrierName;
    }

    private String getConnectivity() {
        String connectionType = "";
        try {
            ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService("connectivity");
            if (cm == null) {
                return connectionType;
            }
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork == null) {
                return connectionType;
            }
            if (activeNetwork.getType() == 1) {
                return "wifi";
            }
            if (activeNetwork.getType() == 0) {
                return "mobile";
            }
            return connectionType;
        } catch (Exception e) {
            return connectionType;
        }
    }

    private String getPlatform() {
        if ("Amazon".equals(Build.MANUFACTURER)) {
            return "amazon";
        }
        return "android";
    }

    private String getManufacturerAndModel() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    private AdvertiserInfo fetchAdvertisingInfo() {
        boolean z = true;
        String advertisingId = "";
        Boolean limitAdTracking = Boolean.valueOf(false);
        try {
            if (getPlatform().equals("amazon")) {
                ContentResolver cr = this.context.getContentResolver();
                int lat = Secure.getInt(cr, "limit_ad_tracking", 2);
                if (lat == 0) {
                    advertisingId = Secure.getString(cr, "advertising_id");
                    limitAdTracking = Boolean.valueOf(false);
                } else {
                    limitAdTracking = lat == 2 ? Boolean.valueOf(true) : Boolean.valueOf(true);
                }
                this.advertiserIdParam = advertisingId;
                this.limitAdTrackingParam = String.valueOf(limitAdTracking);
                this.advertiserInfo = new AdvertiserInfo(this, advertisingId, limitAdTracking);
                return this.advertiserInfo;
            }
            try {
                Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.context);
                if (adInfo != null) {
                    advertisingId = adInfo.getId();
                    if (!adInfo.isLimitAdTrackingEnabled()) {
                        z = false;
                    }
                    limitAdTracking = Boolean.valueOf(z);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e2) {
                e2.printStackTrace();
            } catch (GooglePlayServicesRepairableException e3) {
                e3.printStackTrace();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            this.advertiserIdParam = advertisingId;
            this.limitAdTrackingParam = String.valueOf(limitAdTracking);
            this.advertiserInfo = new AdvertiserInfo(this, advertisingId, limitAdTracking);
            return this.advertiserInfo;
        } catch (Exception e42) {
            e42.printStackTrace();
        }
    }

    private String getAdvertisingId() {
        if (this.advertiserIdParam != null && !TextUtils.isEmpty(this.advertiserIdParam)) {
            return this.advertiserIdParam;
        }
        if (this.advertiserInfo != null) {
            this.advertiserIdParam = AdvertiserInfo.access$1800(this.advertiserInfo);
            this.limitAdTrackingParam = String.valueOf(AdvertiserInfo.access$2000(this.advertiserInfo));
            return this.advertiserIdParam;
        }
        AdvertiserInfo advertisingInfo = fetchAdvertisingInfo();
        if (advertisingInfo != null) {
            this.advertiserInfo = advertisingInfo;
            this.advertiserIdParam = AdvertiserInfo.access$1800(advertisingInfo);
            this.limitAdTrackingParam = String.valueOf(AdvertiserInfo.access$2000(advertisingInfo));
        }
        return this.advertiserIdParam;
    }

    private String getLimitAdTracking() {
        if (this.limitAdTrackingParam != null && !TextUtils.isEmpty(this.limitAdTrackingParam)) {
            return this.limitAdTrackingParam;
        }
        if (this.advertiserInfo != null) {
            this.advertiserIdParam = AdvertiserInfo.access$1800(this.advertiserInfo);
            this.limitAdTrackingParam = String.valueOf(AdvertiserInfo.access$2000(this.advertiserInfo));
            return this.limitAdTrackingParam;
        }
        AdvertiserInfo advertisingInfo = fetchAdvertisingInfo();
        if (advertisingInfo != null) {
            this.advertiserInfo = advertisingInfo;
            this.advertiserIdParam = AdvertiserInfo.access$1800(advertisingInfo);
            this.limitAdTrackingParam = String.valueOf(AdvertiserInfo.access$2000(advertisingInfo));
        }
        return this.limitAdTrackingParam;
    }

    private void processQueue() {
        synchronized (this.eventQueue) {
            for (Entry<String, Object> entry : this.eventQueue.entrySet()) {
                EventName obj = (EventName) entry.getValue();
                String eventType = EventName.access$2100(obj);
                if (eventType.equals("eventName")) {
                    removeProcessingEvent(getEventKey(EventName.access$2200(obj)));
                    eventWithName(EventName.access$2200(obj));
                } else if (eventType.equals("eventNameValue")) {
                    removeProcessingEvent(getEventKey(EventName.access$2200(obj), EventName.access$2300(obj)));
                    eventWithNameAndValue(EventName.access$2200(obj), EventName.access$2300(obj));
                } else if (eventType.equals("eventNameIntValue")) {
                    removeProcessingEvent(getEventKey(EventName.access$2200(obj), EventName.access$2400(obj)));
                    eventWithNameAndValue(EventName.access$2200(obj), EventName.access$2400(obj));
                } else if (eventType.equals("eventNameTransaction")) {
                    removeProcessingEvent(getEventKey(EventName.access$2500(obj), EventName.access$2600(obj), EventName.access$2700(obj), EventName.access$2800(obj)));
                    transaction(EventName.access$2500(obj), EventName.access$2600(obj), EventName.access$2700(obj), EventName.access$2800(obj));
                } else if (eventType.equals("eventNameTransactionData")) {
                    removeProcessingEvent(getEventKey(EventName.access$2500(obj), EventName.access$2600(obj), EventName.access$2700(obj), EventName.access$2800(obj), EventName.access$2900(obj), EventName.access$3000(obj)));
                    transaction(EventName.access$2500(obj), EventName.access$2600(obj), EventName.access$2700(obj), EventName.access$2800(obj), EventName.access$2900(obj), EventName.access$3000(obj));
                } else if (eventType.equals("eventGetDeeplink")) {
                    removeProcessingEvent("eventGetDeeplink");
                    getDeeplink(EventName.access$3100(obj));
                }
            }
            this.eventQueue.clear();
        }
    }

    public void eventWithName(String name) {
        String eventName = name;
        if (TextUtils.isEmpty(eventName)) {
            Log.d("TenjinSDK", "Tenjin WARNING: Your event was not sent because the eventName was not valid.");
            return;
        }
        String eventKey = getEventKey(eventName);
        if (eventIsProcessing(eventKey) && !inEventQueue(eventKey)) {
            return;
        }
        if (connectSent.get()) {
            new AsyncEvent(this, eventName, null).execute(new Void[0]);
        } else if (this.connectTask != null) {
            addEventQueue(eventName);
        } else {
            addEventQueue(eventName);
            connect();
        }
    }

    public void eventWithNameAndValue(String name, String value) {
        String eventName = name;
        String eventValue = value;
        if (TextUtils.isEmpty(eventName) || TextUtils.isEmpty(eventValue)) {
            Log.d("TenjinSDK", "Tenjin WARNING: Your event was not sent because the eventName or eventValue was not valid.");
            return;
        }
        String eventKey = getEventKey(eventName, eventValue);
        if (eventIsProcessing(eventKey) && !inEventQueue(eventKey)) {
            return;
        }
        if (connectSent.get()) {
            new AsyncEvent(this, eventName, eventValue, null).execute(new Void[0]);
        } else if (this.connectTask != null) {
            addEventQueue(eventName, eventValue);
        } else {
            addEventQueue(eventName, eventValue);
            connect();
        }
    }

    public void eventWithNameAndValue(String name, int value) {
        String eventName = name;
        int eventValue = value;
        if (TextUtils.isEmpty(eventName)) {
            Log.d("TenjinSDK", "Tenjin WARNING: Your event was not sent because the eventName or eventValue is not valid.");
            return;
        }
        String eventKey = getEventKey(eventName, eventValue);
        if (eventIsProcessing(eventKey) && !inEventQueue(eventKey)) {
            return;
        }
        if (connectSent.get()) {
            new AsyncEvent(this, eventName, eventValue, null).execute(new Void[0]);
        } else if (this.connectTask != null) {
            addEventQueue(eventName, eventValue);
        } else {
            addEventQueue(eventName, eventValue);
            connect();
        }
    }

    public void transaction(String productId, String currencyCode, int quantity, double unitPrice) {
        String productIdentifier = productId;
        String cLocale = currencyCode;
        int q = quantity;
        double price = unitPrice;
        if (TextUtils.isEmpty(productIdentifier) || TextUtils.isEmpty(cLocale) || !CUR_SET.contains(cLocale) || q <= 0) {
            Log.d("TenjinSDK", "Tenjin WARNING: Your purchase was not sent because you didn't have valid parameters.");
        } else if (!eventIsProcessing(getEventKey(productIdentifier, cLocale, q, price))) {
            if (connectSent.get()) {
                new AsyncEvent(this, productIdentifier, cLocale, q, price, null).execute(new Void[0]);
            } else if (this.connectTask != null) {
                addEventQueue(productIdentifier, cLocale, q, price);
            } else {
                addEventQueue(productIdentifier, cLocale, q, price);
                connect();
            }
        }
    }

    public void transaction(String productId, String currencyCode, int quantity, double unitPrice, String purchaseData, String dataSignature) {
        String productIdentifier = productId;
        String cLocale = currencyCode;
        int q = quantity;
        double price = unitPrice;
        try {
            String receipt = URLEncoder.encode(purchaseData, "UTF-8");
            String signature = URLEncoder.encode(dataSignature, "UTF-8");
            if (TextUtils.isEmpty(productIdentifier) || TextUtils.isEmpty(cLocale) || !CUR_SET.contains(cLocale) || q <= 0) {
                Log.d("TenjinSDK", "Tenjin WARNING: Your purchase was not sent because you didn't have valid parameters.");
            } else if (!eventIsProcessing(getEventKey(productIdentifier, cLocale, q, price, receipt, signature))) {
                if (connectSent.get()) {
                    new AsyncEvent(this, productIdentifier, cLocale, q, price, receipt, signature, null).execute(new Void[0]);
                } else if (this.connectTask != null) {
                    addEventQueue(productIdentifier, cLocale, q, price, receipt, signature);
                } else {
                    addEventQueue(productIdentifier, cLocale, q, price, receipt, signature);
                    connect();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            transaction(productId, currencyCode, quantity, unitPrice);
        }
    }

    public void getDeeplink(Callback callback) {
        if (!eventIsProcessing("eventGetDeeplink")) {
            if (connectSent.get()) {
                new AsyncGetDeeplink(this, callback).execute(new Callback[0]);
            } else if (this.connectTask != null) {
                addEventQueue("eventGetDeeplink", callback);
            } else {
                addEventQueue("eventGetDeeplink", callback);
                connect();
            }
        }
    }

    private void sendResultToCallback(Callback callback, String data, boolean firstLaunch) {
        if (!deepLinkSent.get()) {
            deepLinkSent.set(true);
            Map<String, String> result = new HashMap();
            if (data != null) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.length() > 0) {
                        JSONArray jsonArray = jsonObject.names();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            result.put(jsonArray.getString(i), jsonObject.getString(jsonArray.getString(i)));
                        }
                        this.result = result;
                    }
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
                this.clickedTenjinLink = this.context.getSharedPreferences("tenjinInstallPreferences", 0).getBoolean("tenjinInstallReferrerSent", false);
                if (result.get("ad_network") != null) {
                    if (((String) result.get("ad_network")).equals("organic")) {
                        this.clickedTenjinLink = false;
                    } else {
                        this.clickedTenjinLink = true;
                    }
                }
            }
            new Handler(Looper.getMainLooper()).post(new 2(this, callback, firstLaunch));
        }
    }

    private String getEventKey(String eventName) {
        return eventName;
    }

    private String getEventKey(String eventName, String eventValue) {
        return eventName + "." + eventValue;
    }

    private String getEventKey(String eventName, int eventValue) {
        return eventName + "." + Integer.toString(eventValue);
    }

    private String getEventKey(String productId, String currencyCode, int quantity, double unitPrice) {
        return productId + "." + currencyCode + "." + Integer.toString(quantity) + "." + Double.toString(unitPrice);
    }

    private String getEventKey(String productId, String currencyCode, int quantity, double unitPrice, String purchaseData, String dataSignature) {
        return productId + "." + currencyCode + "." + Integer.toString(quantity) + "." + Double.toString(unitPrice);
    }

    private boolean addEventQueue(String eventName) {
        boolean z;
        synchronized (this.eventQueue) {
            String eventID = getEventKey(eventName);
            if (this.eventQueue.containsKey(eventID)) {
                z = false;
            } else {
                this.eventQueue.put(eventID, new EventName(this, eventName));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(String eventName, String eventValue) {
        boolean z;
        synchronized (this.eventQueue) {
            String eventID = getEventKey(eventName, eventValue);
            if (this.eventQueue.containsKey(eventID)) {
                z = false;
            } else {
                this.eventQueue.put(eventID, new EventName(this, eventName, eventValue));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(String eventName, int eventValue) {
        boolean z;
        synchronized (this.eventQueue) {
            String eventID = getEventKey(eventName, eventValue);
            if (this.eventQueue.containsKey(eventID)) {
                z = false;
            } else {
                this.eventQueue.put(eventID, new EventName(this, eventName, eventValue));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(String productId, String currencyCode, int quantity, double unitPrice) {
        boolean z;
        synchronized (this.eventQueue) {
            String eventID = getEventKey(productId, currencyCode, quantity, unitPrice);
            if (this.eventQueue.containsKey(eventID)) {
                z = false;
            } else {
                this.eventQueue.put(eventID, new EventName(this, productId, currencyCode, quantity, unitPrice));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(String productId, String currencyCode, int quantity, double unitPrice, String purchaseData, String dataSignature) {
        boolean z;
        synchronized (this.eventQueue) {
            String eventID = getEventKey(productId, currencyCode, quantity, unitPrice, purchaseData, dataSignature);
            if (this.eventQueue.containsKey(eventID)) {
                z = false;
            } else {
                this.eventQueue.put(eventID, new EventName(this, productId, currencyCode, quantity, unitPrice, purchaseData, dataSignature));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(String eventId, Callback callback) {
        boolean z;
        synchronized (this.eventQueue) {
            if (this.eventQueue.containsKey(eventId)) {
                z = false;
            } else {
                this.eventQueue.put(eventId, new EventName(this, eventId, callback));
                z = true;
            }
        }
        return z;
    }

    private boolean addEventQueue(AsyncEvent e) {
        String eventType = AsyncEvent.access$5100(e);
        if (eventType.equals("eventName")) {
            return addEventQueue(AsyncEvent.access$5200(e));
        }
        if (eventType.equals("eventNameValue")) {
            return addEventQueue(AsyncEvent.access$5200(e), AsyncEvent.access$5300(e));
        }
        if (eventType.equals("eventNameIntValue")) {
            return addEventQueue(AsyncEvent.access$5200(e), AsyncEvent.access$5400(e));
        }
        if (eventType.equals("eventNameTransaction")) {
            return addEventQueue(AsyncEvent.access$5500(e), AsyncEvent.access$5600(e), AsyncEvent.access$5700(e), AsyncEvent.access$5800(e));
        }
        if (eventType.equals("eventNameTransactionData")) {
            return addEventQueue(AsyncEvent.access$5500(e), AsyncEvent.access$5600(e), AsyncEvent.access$5700(e), AsyncEvent.access$5800(e), AsyncEvent.access$5900(e), AsyncEvent.access$6000(e));
        }
        return false;
    }

    private boolean removeEventQueue(String eventId) {
        if (!this.eventQueue.containsKey(eventId)) {
            return false;
        }
        this.eventQueue.remove(eventId);
        return true;
    }

    private boolean inEventQueue(String eventId) {
        if (this.eventQueue.containsKey(eventId)) {
            return true;
        }
        return false;
    }

    private boolean eventIsProcessing(String eventId) {
        if (this.eventProcessingQueue.containsKey(eventId)) {
            if (new Date().getTime() - ((Long) this.eventProcessingQueue.get(eventId)).longValue() < (eventId.equals("connect") ? CONNECT_THRESHOLD : EVENT_THRESHOLD)) {
                return true;
            }
            removeProcessingEvent(eventId);
            return false;
        }
        queueProcessingEvent(eventId);
        return false;
    }

    private boolean queueProcessingEvent(String eventId) {
        this.eventProcessingQueue.put(eventId, Long.valueOf(new Date().getTime()));
        return true;
    }

    private boolean removeProcessingEvent(String eventId) {
        if (!this.eventProcessingQueue.containsKey(eventId)) {
            return false;
        }
        this.eventProcessingQueue.remove(eventId);
        return true;
    }

    private void fetchGoogleInstallReferrer() {
        googleInstallReferrerRequested.set(true);
        new Thread(new 4(this)).start();
    }

    private void startGoogleInstallReferrerClientConnection(Context context) {
        this.googleInstallReferrerClient = InstallReferrerClient.newBuilder(context).build();
        try {
            this.googleInstallReferrerClient.startConnection(this.googleInstallReferrerStateListener);
        } catch (Exception e) {
            onGoogleInstallReferrerResponseError(4);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new 5(this), 10000);
    }

    private void onGoogleInstallReferrerResponseError(int responseCode) {
        googleInstallReferrerError.set(true);
        googleInstallReferrerResponseComplete(false);
    }

    private void onGoogleInstallReferrerResponse(InstallReferrerClient client) {
        try {
            ReferrerDetails details = client.getInstallReferrer();
            if (details != null) {
                SharedPreferences preferences = this.context.getSharedPreferences("tenjinInstallPreferences", 0);
                preferences.edit().putBoolean("tenjinGoogleInstallContainsReferrerKey", true).commit();
                this.googleInstallReferrerParam = details.getInstallReferrer();
                if (this.googleInstallReferrerParam != null && TextUtils.isEmpty(this.googleInstallReferrerParam)) {
                    preferences.edit().putString("tenjinGoogleInstallReferrer", this.googleInstallReferrerParam).commit();
                }
                long referrerInstallTimestamp = details.getInstallBeginTimestampSeconds();
                long referrerClickTimestamp = details.getReferrerClickTimestampSeconds();
                if (Long.valueOf(referrerClickTimestamp) != null) {
                    this.googleInstallReferrerClickTime = Long.toString(referrerClickTimestamp);
                    preferences.edit().putString("tenjinGoogleInstallReferrerClickTs", this.googleInstallReferrerClickTime).commit();
                }
                if (Long.valueOf(referrerInstallTimestamp) != null) {
                    this.googleInstallReferrerInstallTime = Long.toString(referrerInstallTimestamp);
                    preferences.edit().putString("tenjinGoogleInstallReferrerInstallTs", this.googleInstallReferrerInstallTime).commit();
                }
                googleInstallReferrerResponseComplete(true);
                return;
            }
            googleInstallReferrerResponseComplete(false);
        } catch (Exception e) {
            googleInstallReferrerError.set(true);
            onGoogleInstallReferrerResponseError(-2);
        }
    }

    private void googleInstallReferrerResponseComplete(boolean success) {
        googleInstallReferrerReceived.set(true);
        if (success) {
            googleInstallReferrerSuccess.set(true);
        }
        notifyConnect("install_referrer");
    }
}
