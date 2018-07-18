package com.amplitude.api;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Build.VERSION;
import android.util.Pair;
import com.amplitude.security.MD5;
import com.coinbase.android.Constants;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.ApiConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeClient {
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    protected String apiKey;
    private boolean backoffUpload;
    private int backoffUploadBatchSize;
    protected Context context;
    protected DatabaseHelper dbHelper;
    protected String deviceId;
    private DeviceInfo deviceInfo;
    private int eventMaxCount;
    private int eventUploadMaxBatchSize;
    private long eventUploadPeriodMillis;
    private int eventUploadThreshold;
    private boolean flushEventsOnClose;
    protected OkHttpClient httpClient;
    WorkerThread httpThread;
    private boolean inForeground;
    protected boolean initialized;
    protected String instanceName;
    Throwable lastError;
    long lastEventId;
    long lastEventTime;
    long lastIdentifyId;
    WorkerThread logThread;
    private long minTimeBetweenSessionsMillis;
    private boolean newDeviceIdPerInstall;
    private boolean offline;
    private boolean optOut;
    long previousSessionId;
    long sequenceNumber;
    long sessionId;
    private long sessionTimeoutMillis;
    private boolean trackingSessionEvents;
    private AtomicBoolean updateScheduled;
    AtomicBoolean uploadingCurrently;
    String url;
    private boolean useAdvertisingIdForDeviceId;
    protected String userId;
    private boolean usingForegroundTracking;

    public AmplitudeClient() {
        this(null);
    }

    public AmplitudeClient(String instance) {
        this.newDeviceIdPerInstall = false;
        this.useAdvertisingIdForDeviceId = false;
        this.initialized = false;
        this.optOut = false;
        this.offline = false;
        this.sessionId = -1;
        this.sequenceNumber = 0;
        this.lastEventId = -1;
        this.lastIdentifyId = -1;
        this.lastEventTime = -1;
        this.previousSessionId = -1;
        this.eventUploadThreshold = 30;
        this.eventUploadMaxBatchSize = 100;
        this.eventMaxCount = 1000;
        this.eventUploadPeriodMillis = 30000;
        this.minTimeBetweenSessionsMillis = 300000;
        this.sessionTimeoutMillis = 1800000;
        this.backoffUpload = false;
        this.backoffUploadBatchSize = this.eventUploadMaxBatchSize;
        this.usingForegroundTracking = false;
        this.trackingSessionEvents = false;
        this.inForeground = false;
        this.flushEventsOnClose = true;
        this.updateScheduled = new AtomicBoolean(false);
        this.uploadingCurrently = new AtomicBoolean(false);
        this.url = "https://api.amplitude.com/";
        this.logThread = new WorkerThread("logThread");
        this.httpThread = new WorkerThread("httpThread");
        this.instanceName = Utils.normalizeInstanceName(instance);
        this.logThread.start();
        this.httpThread.start();
    }

    public AmplitudeClient initialize(Context context, String apiKey) {
        return initialize(context, apiKey, null);
    }

    public synchronized AmplitudeClient initialize(final Context context, String apiKey, final String userId) {
        AmplitudeClient this;
        if (context == null) {
            logger.e("com.amplitude.api.AmplitudeClient", "Argument context cannot be null in initialize()");
            this = this;
        } else if (Utils.isEmptyString(apiKey)) {
            logger.e("com.amplitude.api.AmplitudeClient", "Argument apiKey cannot be null or blank in initialize()");
            this = this;
        } else {
            this.context = context.getApplicationContext();
            this.apiKey = apiKey;
            this.dbHelper = DatabaseHelper.getDatabaseHelper(this.context, this.instanceName);
            final AmplitudeClient client = this;
            runOnLogThread(new Runnable() {
                public void run() {
                    if (!AmplitudeClient.this.initialized) {
                        try {
                            boolean z;
                            if (AmplitudeClient.this.instanceName.equals("$default_instance")) {
                                AmplitudeClient.upgradePrefs(context);
                                AmplitudeClient.upgradeSharedPrefsToDB(context);
                            }
                            AmplitudeClient.this.httpClient = new OkHttpClient();
                            AmplitudeClient.this.initializeDeviceInfo();
                            if (userId != null) {
                                client.userId = userId;
                                AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(Constants.KEY_USER_ID, userId);
                            } else {
                                client.userId = AmplitudeClient.this.dbHelper.getValue(Constants.KEY_USER_ID);
                            }
                            Long optOutLong = AmplitudeClient.this.dbHelper.getLongValue("opt_out");
                            AmplitudeClient amplitudeClient = AmplitudeClient.this;
                            if (optOutLong == null || optOutLong.longValue() != 1) {
                                z = false;
                            } else {
                                z = true;
                            }
                            amplitudeClient.optOut = z;
                            AmplitudeClient.this.previousSessionId = AmplitudeClient.this.getLongvalue("previous_session_id", -1);
                            if (AmplitudeClient.this.previousSessionId >= 0) {
                                AmplitudeClient.this.sessionId = AmplitudeClient.this.previousSessionId;
                            }
                            AmplitudeClient.this.sequenceNumber = AmplitudeClient.this.getLongvalue("sequence_number", 0);
                            AmplitudeClient.this.lastEventId = AmplitudeClient.this.getLongvalue("last_event_id", -1);
                            AmplitudeClient.this.lastIdentifyId = AmplitudeClient.this.getLongvalue("last_identify_id", -1);
                            AmplitudeClient.this.lastEventTime = AmplitudeClient.this.getLongvalue("last_event_time", -1);
                            AmplitudeClient.this.initialized = true;
                        } catch (CursorWindowAllocationException e) {
                            AmplitudeClient.logger.e("com.amplitude.api.AmplitudeClient", String.format("Failed to initialize Amplitude SDK due to: %s", new Object[]{e.getMessage()}));
                            client.apiKey = null;
                        }
                    }
                }
            });
            this = this;
        }
        return this;
    }

    public AmplitudeClient enableForegroundTracking(Application app) {
        if (!this.usingForegroundTracking && contextAndApiKeySet("enableForegroundTracking()") && VERSION.SDK_INT >= 14) {
            app.registerActivityLifecycleCallbacks(new AmplitudeCallbacks(this));
        }
        return this;
    }

    private void initializeDeviceInfo() {
        this.deviceInfo = new DeviceInfo(this.context);
        this.deviceId = initializeDeviceId();
        this.deviceInfo.prefetch();
    }

    void useForegroundTracking() {
        this.usingForegroundTracking = true;
    }

    public void logEvent(String eventType, JSONObject eventProperties) {
        logEvent(eventType, eventProperties, false);
    }

    public void logEvent(String eventType, JSONObject eventProperties, boolean outOfSession) {
        logEvent(eventType, eventProperties, null, outOfSession);
    }

    public void logEvent(String eventType, JSONObject eventProperties, JSONObject groups, boolean outOfSession) {
        logEvent(eventType, eventProperties, groups, getCurrentTimeMillis(), outOfSession);
    }

    public void logEvent(String eventType, JSONObject eventProperties, JSONObject groups, long timestamp, boolean outOfSession) {
        if (validateLogEvent(eventType)) {
            logEventAsync(eventType, eventProperties, null, null, groups, timestamp, outOfSession);
        }
    }

    protected boolean validateLogEvent(String eventType) {
        if (!Utils.isEmptyString(eventType)) {
            return contextAndApiKeySet("logEvent()");
        }
        logger.e("com.amplitude.api.AmplitudeClient", "Argument eventType cannot be null or blank in logEvent()");
        return false;
    }

    protected void logEventAsync(String eventType, JSONObject eventProperties, JSONObject apiProperties, JSONObject userProperties, JSONObject groups, long timestamp, boolean outOfSession) {
        if (eventProperties != null) {
            eventProperties = Utils.cloneJSONObject(eventProperties);
        }
        if (userProperties != null) {
            userProperties = Utils.cloneJSONObject(userProperties);
        }
        if (groups != null) {
            groups = Utils.cloneJSONObject(groups);
        }
        final JSONObject copyEventProperties = eventProperties;
        final JSONObject copyUserProperties = userProperties;
        final JSONObject copyGroups = groups;
        final String str = eventType;
        final JSONObject jSONObject = apiProperties;
        final long j = timestamp;
        final boolean z = outOfSession;
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.logEvent(str, copyEventProperties, jSONObject, copyUserProperties, copyGroups, j, z);
                }
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected long logEvent(String eventType, JSONObject eventProperties, JSONObject apiProperties, JSONObject userProperties, JSONObject groups, long timestamp, boolean outOfSession) {
        logger.d("com.amplitude.api.AmplitudeClient", "Logged event to Amplitude: " + eventType);
        if (this.optOut) {
            return -1;
        }
        boolean loggingSessionEvent;
        JSONObject event;
        long j;
        Location location;
        Object jSONObject;
        if (this.trackingSessionEvents) {
            if (!eventType.equals("session_start")) {
            }
            loggingSessionEvent = true;
            if (!(loggingSessionEvent || outOfSession)) {
                if (this.inForeground) {
                    startNewSessionIfNeeded(timestamp);
                } else {
                    refreshSessionTime(timestamp);
                }
            }
            event = new JSONObject();
            event.put("event_type", replaceWithJSONNull(eventType));
            event.put("timestamp", timestamp);
            event.put(Constants.KEY_USER_ID, replaceWithJSONNull(this.userId));
            event.put(ApiConstants.DEVICE_ID, replaceWithJSONNull(this.deviceId));
            String str = "session_id";
            if (outOfSession) {
                j = this.sessionId;
            } else {
                j = -1;
            }
            event.put(str, j);
            event.put("version_name", replaceWithJSONNull(this.deviceInfo.getVersionName()));
            event.put("os_name", replaceWithJSONNull(this.deviceInfo.getOsName()));
            event.put("os_version", replaceWithJSONNull(this.deviceInfo.getOsVersion()));
            event.put("device_brand", replaceWithJSONNull(this.deviceInfo.getBrand()));
            event.put("device_manufacturer", replaceWithJSONNull(this.deviceInfo.getManufacturer()));
            event.put("device_model", replaceWithJSONNull(this.deviceInfo.getModel()));
            event.put("carrier", replaceWithJSONNull(this.deviceInfo.getCarrier()));
            event.put("country", replaceWithJSONNull(this.deviceInfo.getCountry()));
            event.put("language", replaceWithJSONNull(this.deviceInfo.getLanguage()));
            event.put(ApiConstants.PLATFORM, "Android");
            event.put("uuid", UUID.randomUUID().toString());
            event.put("sequence_number", getNextSequenceNumber());
            JSONObject library = new JSONObject();
            library.put("name", "amplitude-android");
            library.put("version", "2.14.1");
            event.put("library", library);
            if (apiProperties == null) {
                apiProperties = new JSONObject();
            }
            location = this.deviceInfo.getMostRecentLocation();
            if (location != null) {
                JSONObject locationJSON = new JSONObject();
                locationJSON.put("lat", location.getLatitude());
                locationJSON.put("lng", location.getLongitude());
                apiProperties.put("location", locationJSON);
            }
            if (this.deviceInfo.getAdvertisingId() != null) {
                apiProperties.put("androidADID", this.deviceInfo.getAdvertisingId());
            }
            apiProperties.put("limit_ad_tracking", this.deviceInfo.isLimitAdTrackingEnabled());
            apiProperties.put("gps_enabled", this.deviceInfo.isGooglePlayServicesEnabled());
            event.put("api_properties", apiProperties);
            String str2 = "event_properties";
            if (eventProperties != null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = truncate(eventProperties);
            }
            event.put(str2, jSONObject);
            str2 = "user_properties";
            if (userProperties != null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = truncate(userProperties);
            }
            event.put(str2, jSONObject);
            event.put("groups", groups != null ? new JSONObject() : truncate(groups));
            return saveEvent(eventType, event);
        }
        loggingSessionEvent = false;
        if (this.inForeground) {
            refreshSessionTime(timestamp);
        } else {
            startNewSessionIfNeeded(timestamp);
        }
        event = new JSONObject();
        try {
            event.put("event_type", replaceWithJSONNull(eventType));
            event.put("timestamp", timestamp);
            event.put(Constants.KEY_USER_ID, replaceWithJSONNull(this.userId));
            event.put(ApiConstants.DEVICE_ID, replaceWithJSONNull(this.deviceId));
            String str3 = "session_id";
            if (outOfSession) {
                j = this.sessionId;
            } else {
                j = -1;
            }
            event.put(str3, j);
            event.put("version_name", replaceWithJSONNull(this.deviceInfo.getVersionName()));
            event.put("os_name", replaceWithJSONNull(this.deviceInfo.getOsName()));
            event.put("os_version", replaceWithJSONNull(this.deviceInfo.getOsVersion()));
            event.put("device_brand", replaceWithJSONNull(this.deviceInfo.getBrand()));
            event.put("device_manufacturer", replaceWithJSONNull(this.deviceInfo.getManufacturer()));
            event.put("device_model", replaceWithJSONNull(this.deviceInfo.getModel()));
            event.put("carrier", replaceWithJSONNull(this.deviceInfo.getCarrier()));
            event.put("country", replaceWithJSONNull(this.deviceInfo.getCountry()));
            event.put("language", replaceWithJSONNull(this.deviceInfo.getLanguage()));
            event.put(ApiConstants.PLATFORM, "Android");
            event.put("uuid", UUID.randomUUID().toString());
            event.put("sequence_number", getNextSequenceNumber());
            JSONObject library2 = new JSONObject();
            library2.put("name", "amplitude-android");
            library2.put("version", "2.14.1");
            event.put("library", library2);
            if (apiProperties == null) {
                apiProperties = new JSONObject();
            }
            location = this.deviceInfo.getMostRecentLocation();
            if (location != null) {
                JSONObject locationJSON2 = new JSONObject();
                locationJSON2.put("lat", location.getLatitude());
                locationJSON2.put("lng", location.getLongitude());
                apiProperties.put("location", locationJSON2);
            }
            if (this.deviceInfo.getAdvertisingId() != null) {
                apiProperties.put("androidADID", this.deviceInfo.getAdvertisingId());
            }
            apiProperties.put("limit_ad_tracking", this.deviceInfo.isLimitAdTrackingEnabled());
            apiProperties.put("gps_enabled", this.deviceInfo.isGooglePlayServicesEnabled());
            event.put("api_properties", apiProperties);
            String str22 = "event_properties";
            if (eventProperties != null) {
                jSONObject = truncate(eventProperties);
            } else {
                jSONObject = new JSONObject();
            }
            event.put(str22, jSONObject);
            str22 = "user_properties";
            if (userProperties != null) {
                jSONObject = truncate(userProperties);
            } else {
                jSONObject = new JSONObject();
            }
            event.put(str22, jSONObject);
            if (groups != null) {
            }
            event.put("groups", groups != null ? new JSONObject() : truncate(groups));
            return saveEvent(eventType, event);
        } catch (JSONException e) {
            logger.e("com.amplitude.api.AmplitudeClient", String.format("JSON Serialization of event type %s failed, skipping: %s", new Object[]{eventType, e.toString()}));
            return -1;
        }
    }

    protected long saveEvent(String eventType, JSONObject event) {
        String eventString = event.toString();
        if (Utils.isEmptyString(eventString)) {
            logger.e("com.amplitude.api.AmplitudeClient", String.format("Detected empty event string for event type %s, skipping", new Object[]{eventType}));
            return -1;
        }
        if (eventType.equals("$identify")) {
            this.lastIdentifyId = this.dbHelper.addIdentify(eventString);
            setLastIdentifyId(this.lastIdentifyId);
        } else {
            this.lastEventId = this.dbHelper.addEvent(eventString);
            setLastEventId(this.lastEventId);
        }
        int numEventsToRemove = Math.min(Math.max(1, this.eventMaxCount / 10), 20);
        if (this.dbHelper.getEventCount() > ((long) this.eventMaxCount)) {
            this.dbHelper.removeEvents(this.dbHelper.getNthEventId((long) numEventsToRemove));
        }
        if (this.dbHelper.getIdentifyCount() > ((long) this.eventMaxCount)) {
            this.dbHelper.removeIdentifys(this.dbHelper.getNthIdentifyId((long) numEventsToRemove));
        }
        long totalEventCount = this.dbHelper.getTotalEventCount();
        if (totalEventCount % ((long) this.eventUploadThreshold) != 0 || totalEventCount < ((long) this.eventUploadThreshold)) {
            updateServerLater(this.eventUploadPeriodMillis);
        } else {
            updateServer();
        }
        if (eventType.equals("$identify")) {
            return this.lastIdentifyId;
        }
        return this.lastEventId;
    }

    private long getLongvalue(String key, long defaultValue) {
        Long value = this.dbHelper.getLongValue(key);
        return value == null ? defaultValue : value.longValue();
    }

    long getNextSequenceNumber() {
        this.sequenceNumber++;
        this.dbHelper.insertOrReplaceKeyLongValue("sequence_number", Long.valueOf(this.sequenceNumber));
        return this.sequenceNumber;
    }

    void setLastEventTime(long timestamp) {
        this.lastEventTime = timestamp;
        this.dbHelper.insertOrReplaceKeyLongValue("last_event_time", Long.valueOf(timestamp));
    }

    void setLastEventId(long eventId) {
        this.lastEventId = eventId;
        this.dbHelper.insertOrReplaceKeyLongValue("last_event_id", Long.valueOf(eventId));
    }

    void setLastIdentifyId(long identifyId) {
        this.lastIdentifyId = identifyId;
        this.dbHelper.insertOrReplaceKeyLongValue("last_identify_id", Long.valueOf(identifyId));
    }

    public long getSessionId() {
        return this.sessionId;
    }

    void setPreviousSessionId(long timestamp) {
        this.previousSessionId = timestamp;
        this.dbHelper.insertOrReplaceKeyLongValue("previous_session_id", Long.valueOf(timestamp));
    }

    boolean startNewSessionIfNeeded(long timestamp) {
        if (inSession()) {
            if (isWithinMinTimeBetweenSessions(timestamp)) {
                refreshSessionTime(timestamp);
                return false;
            }
            startNewSession(timestamp);
            return true;
        } else if (!isWithinMinTimeBetweenSessions(timestamp)) {
            startNewSession(timestamp);
            return true;
        } else if (this.previousSessionId == -1) {
            startNewSession(timestamp);
            return true;
        } else {
            setSessionId(this.previousSessionId);
            refreshSessionTime(timestamp);
            return false;
        }
    }

    private void startNewSession(long timestamp) {
        if (this.trackingSessionEvents) {
            sendSessionEvent("session_end");
        }
        setSessionId(timestamp);
        refreshSessionTime(timestamp);
        if (this.trackingSessionEvents) {
            sendSessionEvent("session_start");
        }
    }

    private boolean inSession() {
        return this.sessionId >= 0;
    }

    private boolean isWithinMinTimeBetweenSessions(long timestamp) {
        return timestamp - this.lastEventTime < (this.usingForegroundTracking ? this.minTimeBetweenSessionsMillis : this.sessionTimeoutMillis);
    }

    private void setSessionId(long timestamp) {
        this.sessionId = timestamp;
        setPreviousSessionId(timestamp);
    }

    void refreshSessionTime(long timestamp) {
        if (inSession()) {
            setLastEventTime(timestamp);
        }
    }

    private void sendSessionEvent(String sessionEvent) {
        if (contextAndApiKeySet(String.format("sendSessionEvent('%s')", new Object[]{sessionEvent})) && inSession()) {
            JSONObject apiProperties = new JSONObject();
            try {
                apiProperties.put("special", sessionEvent);
                logEvent(sessionEvent, null, apiProperties, null, null, this.lastEventTime, false);
            } catch (JSONException e) {
            }
        }
    }

    void onExitForeground(final long timestamp) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.refreshSessionTime(timestamp);
                    AmplitudeClient.this.inForeground = false;
                    if (AmplitudeClient.this.flushEventsOnClose) {
                        AmplitudeClient.this.updateServer();
                    }
                }
            }
        });
    }

    void onEnterForeground(final long timestamp) {
        runOnLogThread(new Runnable() {
            public void run() {
                if (!Utils.isEmptyString(AmplitudeClient.this.apiKey)) {
                    AmplitudeClient.this.startNewSessionIfNeeded(timestamp);
                    AmplitudeClient.this.inForeground = true;
                }
            }
        });
    }

    public void setUserProperties(JSONObject userProperties) {
        if (userProperties != null && userProperties.length() != 0 && contextAndApiKeySet("setUserProperties")) {
            JSONObject sanitized = truncate(userProperties);
            if (sanitized.length() != 0) {
                Identify identify = new Identify();
                Iterator<?> keys = sanitized.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    try {
                        identify.setUserProperty(key, sanitized.get(key));
                    } catch (JSONException e) {
                        logger.e("com.amplitude.api.AmplitudeClient", e.toString());
                    }
                }
                identify(identify);
            }
        }
    }

    public void identify(Identify identify) {
        identify(identify, false);
    }

    public void identify(Identify identify, boolean outOfSession) {
        if (identify != null && identify.userPropertiesOperations.length() != 0 && contextAndApiKeySet("identify()")) {
            logEventAsync("$identify", null, null, identify.userPropertiesOperations, null, getCurrentTimeMillis(), outOfSession);
        }
    }

    public JSONObject truncate(JSONObject object) {
        if (object == null) {
            return new JSONObject();
        }
        if (object.length() > 1000) {
            logger.w("com.amplitude.api.AmplitudeClient", "Warning: too many properties (more than 1000), ignoring");
            return new JSONObject();
        }
        Iterator<?> keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            try {
                Object value = object.get(key);
                if (key.equals("$receipt") || key.equals("$receiptSig")) {
                    object.put(key, value);
                } else if (value.getClass().equals(String.class)) {
                    object.put(key, truncate((String) value));
                } else if (value.getClass().equals(JSONObject.class)) {
                    object.put(key, truncate((JSONObject) value));
                } else if (value.getClass().equals(JSONArray.class)) {
                    object.put(key, truncate((JSONArray) value));
                }
            } catch (JSONException e) {
                logger.e("com.amplitude.api.AmplitudeClient", e.toString());
            }
        }
        return object;
    }

    public JSONArray truncate(JSONArray array) throws JSONException {
        if (array == null) {
            return new JSONArray();
        }
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value.getClass().equals(String.class)) {
                array.put(i, truncate((String) value));
            } else if (value.getClass().equals(JSONObject.class)) {
                array.put(i, truncate((JSONObject) value));
            } else if (value.getClass().equals(JSONArray.class)) {
                array.put(i, truncate((JSONArray) value));
            }
        }
        return array;
    }

    public String truncate(String value) {
        return value.length() <= 1024 ? value : value.substring(0, 1024);
    }

    public AmplitudeClient setUserId(final String userId) {
        if (contextAndApiKeySet("setUserId()")) {
            final AmplitudeClient client = this;
            runOnLogThread(new Runnable() {
                public void run() {
                    if (!Utils.isEmptyString(client.apiKey)) {
                        client.userId = userId;
                        AmplitudeClient.this.dbHelper.insertOrReplaceKeyValue(Constants.KEY_USER_ID, userId);
                    }
                }
            });
        }
        return this;
    }

    private void updateServerLater(long delayMillis) {
        if (!this.updateScheduled.getAndSet(true)) {
            this.logThread.postDelayed(new Runnable() {
                public void run() {
                    AmplitudeClient.this.updateScheduled.set(false);
                    AmplitudeClient.this.updateServer();
                }
            }, delayMillis);
        }
    }

    protected void updateServer() {
        updateServer(false);
    }

    protected void updateServer(boolean limit) {
        if (!this.optOut && !this.offline && !this.uploadingCurrently.getAndSet(true)) {
            long j;
            long totalEventCount = this.dbHelper.getTotalEventCount();
            if (limit) {
                j = (long) this.backoffUploadBatchSize;
            } else {
                j = (long) this.eventUploadMaxBatchSize;
            }
            long batchSize = Math.min(j, totalEventCount);
            if (batchSize <= 0) {
                this.uploadingCurrently.set(false);
                return;
            }
            try {
                Pair<Pair<Long, Long>, JSONArray> merged = mergeEventsAndIdentifys(this.dbHelper.getEvents(this.lastEventId, batchSize), this.dbHelper.getIdentifys(this.lastIdentifyId, batchSize), batchSize);
                if (merged.second.length() == 0) {
                    this.uploadingCurrently.set(false);
                    return;
                }
                final long maxEventId = ((Long) ((Pair) merged.first).first).longValue();
                final long maxIdentifyId = ((Long) ((Pair) merged.first).second).longValue();
                final String mergedEventsString = ((JSONArray) merged.second).toString();
                this.httpThread.post(new Runnable() {
                    public void run() {
                        AmplitudeClient.this.makeEventUploadPostRequest(AmplitudeClient.this.httpClient, mergedEventsString, maxEventId, maxIdentifyId);
                    }
                });
            } catch (JSONException e) {
                this.uploadingCurrently.set(false);
                logger.e("com.amplitude.api.AmplitudeClient", e.toString());
            } catch (CursorWindowAllocationException e2) {
                this.uploadingCurrently.set(false);
                logger.e("com.amplitude.api.AmplitudeClient", String.format("Caught Cursor window exception during event upload, deferring upload: %s", new Object[]{e2.getMessage()}));
            }
        }
    }

    protected Pair<Pair<Long, Long>, JSONArray> mergeEventsAndIdentifys(List<JSONObject> events, List<JSONObject> identifys, long numEvents) throws JSONException {
        JSONArray merged = new JSONArray();
        long maxEventId = -1;
        long maxIdentifyId = -1;
        while (((long) merged.length()) < numEvents) {
            boolean noEvents = events.isEmpty();
            boolean noIdentifys = identifys.isEmpty();
            if (noEvents && noIdentifys) {
                logger.w("com.amplitude.api.AmplitudeClient", String.format("mergeEventsAndIdentifys: number of events and identifys less than expected by %d", new Object[]{Long.valueOf(numEvents - ((long) merged.length()))}));
                break;
            } else if (noIdentifys) {
                event = (JSONObject) events.remove(0);
                maxEventId = event.getLong("event_id");
                merged.put(event);
            } else if (noEvents) {
                identify = (JSONObject) identifys.remove(0);
                maxIdentifyId = identify.getLong("event_id");
                merged.put(identify);
            } else if (!((JSONObject) events.get(0)).has("sequence_number") || ((JSONObject) events.get(0)).getLong("sequence_number") < ((JSONObject) identifys.get(0)).getLong("sequence_number")) {
                event = (JSONObject) events.remove(0);
                maxEventId = event.getLong("event_id");
                merged.put(event);
            } else {
                identify = (JSONObject) identifys.remove(0);
                maxIdentifyId = identify.getLong("event_id");
                merged.put(identify);
            }
        }
        return new Pair(new Pair(Long.valueOf(maxEventId), Long.valueOf(maxIdentifyId)), merged);
    }

    protected void makeEventUploadPostRequest(OkHttpClient client, String events, long maxEventId, long maxIdentifyId) {
        String apiVersionString = "2";
        String timestampString = "" + getCurrentTimeMillis();
        String checksumString = "";
        try {
            checksumString = bytesToHexString(new MD5().digest((apiVersionString + this.apiKey + events + timestampString).getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            logger.e("com.amplitude.api.AmplitudeClient", e.toString());
        }
        try {
            boolean uploadSuccess = false;
            try {
                Response response = client.newCall(new Builder().url(this.url).post(new FormBody.Builder().add("v", apiVersionString).add(MixpanelTracking.PROPERTY_VALUE_SEND_ERROR_TYPE_CLIENT, this.apiKey).add("e", events).add("upload_time", timestampString).add("checksum", checksumString).build()).build()).execute();
                String stringResponse = response.body().string();
                if (stringResponse.equals("success")) {
                    uploadSuccess = true;
                    final long j = maxEventId;
                    final long j2 = maxIdentifyId;
                    this.logThread.post(new Runnable() {
                        public void run() {
                            if (j >= 0) {
                                AmplitudeClient.this.dbHelper.removeEvents(j);
                            }
                            if (j2 >= 0) {
                                AmplitudeClient.this.dbHelper.removeIdentifys(j2);
                            }
                            AmplitudeClient.this.uploadingCurrently.set(false);
                            if (AmplitudeClient.this.dbHelper.getTotalEventCount() > ((long) AmplitudeClient.this.eventUploadThreshold)) {
                                AmplitudeClient.this.logThread.post(new Runnable() {
                                    public void run() {
                                        AmplitudeClient.this.updateServer(AmplitudeClient.this.backoffUpload);
                                    }
                                });
                                return;
                            }
                            AmplitudeClient.this.backoffUpload = false;
                            AmplitudeClient.this.backoffUploadBatchSize = AmplitudeClient.this.eventUploadMaxBatchSize;
                        }
                    });
                } else {
                    if (stringResponse.equals("invalid_api_key")) {
                        logger.e("com.amplitude.api.AmplitudeClient", "Invalid API key, make sure your API key is correct in initialize()");
                    } else {
                        if (stringResponse.equals("bad_checksum")) {
                            logger.w("com.amplitude.api.AmplitudeClient", "Bad checksum, post request was mangled in transit, will attempt to reupload later");
                        } else {
                            if (stringResponse.equals("request_db_write_failed")) {
                                logger.w("com.amplitude.api.AmplitudeClient", "Couldn't write to request database on server, will attempt to reupload later");
                            } else if (response.code() == 413) {
                                if (this.backoffUpload && this.backoffUploadBatchSize == 1) {
                                    if (maxEventId >= 0) {
                                        this.dbHelper.removeEvent(maxEventId);
                                    }
                                    if (maxIdentifyId >= 0) {
                                        this.dbHelper.removeIdentify(maxIdentifyId);
                                    }
                                }
                                this.backoffUpload = true;
                                this.backoffUploadBatchSize = (int) Math.ceil(((double) Math.min((int) this.dbHelper.getEventCount(), this.backoffUploadBatchSize)) / 2.0d);
                                logger.w("com.amplitude.api.AmplitudeClient", "Request too large, will decrease size and attempt to reupload");
                                this.logThread.post(new Runnable() {
                                    public void run() {
                                        AmplitudeClient.this.uploadingCurrently.set(false);
                                        AmplitudeClient.this.updateServer(true);
                                    }
                                });
                            } else {
                                logger.w("com.amplitude.api.AmplitudeClient", "Upload failed, " + stringResponse + ", will attempt to reupload later");
                            }
                        }
                    }
                }
            } catch (ConnectException e2) {
                this.lastError = e2;
            } catch (UnknownHostException e3) {
                this.lastError = e3;
            } catch (IOException e4) {
                logger.e("com.amplitude.api.AmplitudeClient", e4.toString());
                this.lastError = e4;
            } catch (AssertionError e5) {
                logger.e("com.amplitude.api.AmplitudeClient", "Exception:", e5);
                this.lastError = e5;
            } catch (Exception e6) {
                logger.e("com.amplitude.api.AmplitudeClient", "Exception:", e6);
                this.lastError = e6;
            }
            if (!uploadSuccess) {
                this.uploadingCurrently.set(false);
            }
        } catch (IllegalArgumentException e7) {
            logger.e("com.amplitude.api.AmplitudeClient", e7.toString());
            this.uploadingCurrently.set(false);
        }
    }

    private Set<String> getInvalidDeviceIds() {
        Set<String> invalidDeviceIds = new HashSet();
        invalidDeviceIds.add("");
        invalidDeviceIds.add("9774d56d682e549c");
        invalidDeviceIds.add("unknown");
        invalidDeviceIds.add("000000000000000");
        invalidDeviceIds.add("Android");
        invalidDeviceIds.add("DEFACE");
        invalidDeviceIds.add("00000000-0000-0000-0000-000000000000");
        return invalidDeviceIds;
    }

    private String initializeDeviceId() {
        Set<String> invalidIds = getInvalidDeviceIds();
        String deviceId = this.dbHelper.getValue(ApiConstants.DEVICE_ID);
        if (!Utils.isEmptyString(deviceId) && !invalidIds.contains(deviceId)) {
            return deviceId;
        }
        if (!this.newDeviceIdPerInstall && this.useAdvertisingIdForDeviceId) {
            String advertisingId = this.deviceInfo.getAdvertisingId();
            if (!(Utils.isEmptyString(advertisingId) || invalidIds.contains(advertisingId))) {
                this.dbHelper.insertOrReplaceKeyValue(ApiConstants.DEVICE_ID, advertisingId);
                return advertisingId;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        DeviceInfo deviceInfo = this.deviceInfo;
        String randomId = stringBuilder.append(DeviceInfo.generateUUID()).append("R").toString();
        this.dbHelper.insertOrReplaceKeyValue(ApiConstants.DEVICE_ID, randomId);
        return randomId;
    }

    protected void runOnLogThread(Runnable r) {
        if (Thread.currentThread() != this.logThread) {
            this.logThread.post(r);
        } else {
            r.run();
        }
    }

    protected Object replaceWithJSONNull(Object obj) {
        return obj == null ? JSONObject.NULL : obj;
    }

    protected synchronized boolean contextAndApiKeySet(String methodName) {
        boolean z = false;
        synchronized (this) {
            if (this.context == null) {
                logger.e("com.amplitude.api.AmplitudeClient", "context cannot be null, set context with initialize() before calling " + methodName);
            } else if (Utils.isEmptyString(this.apiKey)) {
                logger.e("com.amplitude.api.AmplitudeClient", "apiKey cannot be null or empty, set apiKey with initialize() before calling " + methodName);
            } else {
                z = true;
            }
        }
        return z;
    }

    protected String bytesToHexString(byte[] bytes) {
        char[] hexArray = new char[]{NumericKeypadConnector.ZERO, '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    static boolean upgradePrefs(Context context) {
        return upgradePrefs(context, null, null);
    }

    static boolean upgradePrefs(Context context, String sourcePkgName, String targetPkgName) {
        if (sourcePkgName == null) {
            try {
                sourcePkgName = "com.amplitude.api";
                try {
                    sourcePkgName = Constants.class.getPackage().getName();
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                logger.e("com.amplitude.api.AmplitudeClient", "Error upgrading shared preferences", e2);
                return false;
            }
        }
        if (targetPkgName == null) {
            targetPkgName = "com.amplitude.api";
        }
        if (targetPkgName.equals(sourcePkgName)) {
            return false;
        }
        String sourcePrefsName = sourcePkgName + "." + context.getPackageName();
        SharedPreferences source = context.getSharedPreferences(sourcePrefsName, 0);
        if (source.getAll().size() == 0) {
            return false;
        }
        String prefsName = targetPkgName + "." + context.getPackageName();
        Editor target = context.getSharedPreferences(prefsName, 0).edit();
        if (source.contains(sourcePkgName + ".previousSessionId")) {
            target.putLong("com.amplitude.api.previousSessionId", source.getLong(sourcePkgName + ".previousSessionId", -1));
        }
        if (source.contains(sourcePkgName + ".deviceId")) {
            target.putString("com.amplitude.api.deviceId", source.getString(sourcePkgName + ".deviceId", null));
        }
        if (source.contains(sourcePkgName + ".userId")) {
            target.putString("com.amplitude.api.userId", source.getString(sourcePkgName + ".userId", null));
        }
        if (source.contains(sourcePkgName + ".optOut")) {
            target.putBoolean("com.amplitude.api.optOut", source.getBoolean(sourcePkgName + ".optOut", false));
        }
        target.apply();
        source.edit().clear().apply();
        logger.i("com.amplitude.api.AmplitudeClient", "Upgraded shared preferences from " + sourcePrefsName + " to " + prefsName);
        return true;
    }

    static boolean upgradeSharedPrefsToDB(Context context) {
        return upgradeSharedPrefsToDB(context, null);
    }

    static boolean upgradeSharedPrefsToDB(Context context, String sourcePkgName) {
        if (sourcePkgName == null) {
            sourcePkgName = "com.amplitude.api";
        }
        DatabaseHelper dbHelper = DatabaseHelper.getDatabaseHelper(context);
        String deviceId = dbHelper.getValue(ApiConstants.DEVICE_ID);
        Long previousSessionId = dbHelper.getLongValue("previous_session_id");
        Long lastEventTime = dbHelper.getLongValue("last_event_time");
        if (Utils.isEmptyString(deviceId) || previousSessionId == null || lastEventTime == null) {
            SharedPreferences preferences = context.getSharedPreferences(sourcePkgName + "." + context.getPackageName(), 0);
            migrateStringValue(preferences, "com.amplitude.api.deviceId", null, dbHelper, ApiConstants.DEVICE_ID);
            migrateLongValue(preferences, "com.amplitude.api.lastEventTime", -1, dbHelper, "last_event_time");
            migrateLongValue(preferences, "com.amplitude.api.lastEventId", -1, dbHelper, "last_event_id");
            migrateLongValue(preferences, "com.amplitude.api.lastIdentifyId", -1, dbHelper, "last_identify_id");
            migrateLongValue(preferences, "com.amplitude.api.previousSessionId", -1, dbHelper, "previous_session_id");
            migrateStringValue(preferences, "com.amplitude.api.userId", null, dbHelper, Constants.KEY_USER_ID);
            migrateBooleanValue(preferences, "com.amplitude.api.optOut", false, dbHelper, "opt_out");
        }
        return true;
    }

    private static void migrateLongValue(SharedPreferences prefs, String prefKey, long defValue, DatabaseHelper dbHelper, String dbKey) {
        if (dbHelper.getLongValue(dbKey) == null) {
            dbHelper.insertOrReplaceKeyLongValue(dbKey, Long.valueOf(prefs.getLong(prefKey, defValue)));
            prefs.edit().remove(prefKey).apply();
        }
    }

    private static void migrateStringValue(SharedPreferences prefs, String prefKey, String defValue, DatabaseHelper dbHelper, String dbKey) {
        if (Utils.isEmptyString(dbHelper.getValue(dbKey))) {
            String oldValue = prefs.getString(prefKey, defValue);
            if (!Utils.isEmptyString(oldValue)) {
                dbHelper.insertOrReplaceKeyValue(dbKey, oldValue);
                prefs.edit().remove(prefKey).apply();
            }
        }
    }

    private static void migrateBooleanValue(SharedPreferences prefs, String prefKey, boolean defValue, DatabaseHelper dbHelper, String dbKey) {
        if (dbHelper.getLongValue(dbKey) == null) {
            dbHelper.insertOrReplaceKeyLongValue(dbKey, Long.valueOf(prefs.getBoolean(prefKey, defValue) ? 1 : 0));
            prefs.edit().remove(prefKey).apply();
        }
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
