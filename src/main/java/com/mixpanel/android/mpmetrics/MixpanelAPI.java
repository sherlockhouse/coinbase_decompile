package com.mixpanel.android.mpmetrics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.mixpanel.android.R;
import com.mixpanel.android.mpmetrics.BackgroundCapture.OnBackgroundCapturedListener;
import com.mixpanel.android.mpmetrics.DecideUpdates.OnNewResultsListener;
import com.mixpanel.android.mpmetrics.InAppNotification.Type;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.InAppNotificationState;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.SurveyState;
import com.mixpanel.android.surveys.SurveyActivity;
import com.mixpanel.android.util.ActivityImageUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MixpanelAPI {
    private static final Map<String, Map<Context, MixpanelAPI>> sInstanceMap = new HashMap();
    private static final SharedPreferencesLoader sPrefsLoader = new SharedPreferencesLoader();
    private static Future<SharedPreferences> sReferrerPrefs;
    private final MPConfig mConfig = getConfig();
    private final Context mContext;
    private DecideUpdates mDecideUpdates;
    private final AnalyticsMessages mMessages = getAnalyticsMessages();
    private final PeopleImpl mPeople = new PeopleImpl();
    private final PersistentIdentity mPersistentIdentity;
    private final String mToken;
    private final UpdatesListener mUpdatesListener;

    interface InstanceProcessor {
        void process(MixpanelAPI mixpanelAPI);
    }

    public interface People {
        void append(String str, Object obj);

        void clearPushRegistrationId();

        void setPushRegistrationId(String str);

        void showNotificationIfAvailable(Activity activity);

        void showSurveyIfAvailable(Activity activity);

        People withIdentity(String str);
    }

    private class PeopleImpl implements People {
        private PeopleImpl() {
        }

        public void set(JSONObject properties) {
            try {
                JSONObject sendProperties = new JSONObject();
                sendProperties.put("$android_lib_version", "4.4.1");
                sendProperties.put("$android_os", "Android");
                sendProperties.put("$android_os_version", VERSION.RELEASE == null ? "UNKNOWN" : VERSION.RELEASE);
                try {
                    sendProperties.put("$android_app_version", MixpanelAPI.this.mContext.getPackageManager().getPackageInfo(MixpanelAPI.this.mContext.getPackageName(), 0).versionName);
                } catch (NameNotFoundException e) {
                    Log.e("MixpanelAPI", "Exception getting app version name", e);
                }
                sendProperties.put("$android_manufacturer", Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
                sendProperties.put("$android_brand", Build.BRAND == null ? "UNKNOWN" : Build.BRAND);
                sendProperties.put("$android_model", Build.MODEL == null ? "UNKNOWN" : Build.MODEL);
                Iterator<?> iter = properties.keys();
                while (iter.hasNext()) {
                    String key = (String) iter.next();
                    sendProperties.put(key, properties.get(key));
                }
                MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$set", sendProperties));
            } catch (JSONException e2) {
                Log.e("MixpanelAPI", "Exception setting people properties", e2);
            }
        }

        public void set(String property, Object value) {
            try {
                set(new JSONObject().put(property, value));
            } catch (JSONException e) {
                Log.e("MixpanelAPI", "set", e);
            }
        }

        public void append(String name, Object value) {
            try {
                JSONObject properties = new JSONObject();
                properties.put(name, value);
                MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$append", properties));
            } catch (JSONException e) {
                Log.e("MixpanelAPI", "Exception appending a property", e);
            }
        }

        public void union(String name, JSONArray value) {
            try {
                JSONObject properties = new JSONObject();
                properties.put(name, value);
                MixpanelAPI.this.recordPeopleMessage(stdPeopleMessage("$union", properties));
            } catch (JSONException e) {
                Log.e("MixpanelAPI", "Exception unioning a property");
            }
        }

        public InAppNotification getNotificationIfAvailable() {
            if (MixpanelAPI.this.canUpdate()) {
                return MixpanelAPI.this.mDecideUpdates.getNotification(MixpanelAPI.this.mConfig.getTestMode());
            }
            return null;
        }

        public Survey getSurveyIfAvailable() {
            if (MixpanelAPI.this.canUpdate()) {
                return MixpanelAPI.this.mDecideUpdates.getSurvey(MixpanelAPI.this.mConfig.getTestMode());
            }
            return null;
        }

        public void showSurveyIfAvailable(Activity parent) {
            if (VERSION.SDK_INT >= 14) {
                showGivenOrAvailableSurvey(null, parent);
            }
        }

        public void showNotificationIfAvailable(Activity parent) {
            if (VERSION.SDK_INT >= 14) {
                showGivenOrAvailableNotification(null, parent);
            }
        }

        public void setPushRegistrationId(String registrationId) {
            synchronized (MixpanelAPI.this.mPersistentIdentity) {
                if (MixpanelAPI.this.mPersistentIdentity.getPeopleDistinctId() == null) {
                    return;
                }
                MixpanelAPI.this.mPersistentIdentity.storePushId(registrationId);
                try {
                    union("$android_devices", new JSONArray("[" + registrationId + "]"));
                } catch (JSONException e) {
                    Log.e("MixpanelAPI", "set push registration id error", e);
                }
            }
        }

        public void clearPushRegistrationId() {
            MixpanelAPI.this.mPersistentIdentity.clearPushId();
            set("$android_devices", new JSONArray());
        }

        public String getDistinctId() {
            return MixpanelAPI.this.mPersistentIdentity.getPeopleDistinctId();
        }

        public People withIdentity(final String distinctId) {
            if (distinctId == null) {
                return null;
            }
            return new PeopleImpl() {
                public String getDistinctId() {
                    return distinctId;
                }
            };
        }

        public JSONObject stdPeopleMessage(String actionType, Object properties) throws JSONException {
            JSONObject dataObj = new JSONObject();
            String distinctId = getDistinctId();
            dataObj.put(actionType, properties);
            dataObj.put("$token", MixpanelAPI.this.mToken);
            dataObj.put("$time", System.currentTimeMillis());
            if (distinctId != null) {
                dataObj.put("$distinct_id", distinctId);
            }
            return dataObj;
        }

        private void showGivenOrAvailableSurvey(Survey surveyOrNull, final Activity parent) {
            if (VERSION.SDK_INT >= 14 && ConfigurationChecker.checkSurveyActivityAvailable(parent.getApplicationContext())) {
                ReentrantLock lock = UpdateDisplayState.getLockObject();
                lock.lock();
                try {
                    if (!UpdateDisplayState.hasCurrentProposal()) {
                        Survey toShow = surveyOrNull;
                        if (toShow == null) {
                            toShow = getSurveyIfAvailable();
                        }
                        if (toShow == null) {
                            lock.unlock();
                            return;
                        }
                        final SurveyState surveyDisplay = new SurveyState(toShow);
                        final int intentId = UpdateDisplayState.proposeDisplay(surveyDisplay, getDistinctId(), MixpanelAPI.this.mToken);
                        if (intentId <= 0) {
                            Log.e("MixpanelAPI", "DisplayState Lock is in an inconsistent state! Please report this issue to Mixpanel");
                            lock.unlock();
                            return;
                        }
                        OnBackgroundCapturedListener listener = new OnBackgroundCapturedListener() {
                            public void onBackgroundCaptured(Bitmap bitmapCaptured, int highlightColorCaptured) {
                                surveyDisplay.setBackground(bitmapCaptured);
                                surveyDisplay.setHighlightColor(highlightColorCaptured);
                                Intent surveyIntent = new Intent(parent.getApplicationContext(), SurveyActivity.class);
                                surveyIntent.addFlags(268435456);
                                surveyIntent.addFlags(131072);
                                surveyIntent.putExtra("com.mixpanel.android.surveys.SurveyActivity.INTENT_ID_KEY", intentId);
                                parent.startActivity(surveyIntent);
                            }
                        };
                        lock.unlock();
                        BackgroundCapture.captureBackground(parent, listener);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        private void showGivenOrAvailableNotification(final InAppNotification notifOrNull, final Activity parent) {
            if (VERSION.SDK_INT >= 14) {
                parent.runOnUiThread(new Runnable() {
                    @TargetApi(14)
                    public void run() {
                        ReentrantLock lock = UpdateDisplayState.getLockObject();
                        lock.lock();
                        try {
                            if (!UpdateDisplayState.hasCurrentProposal()) {
                                InAppNotification toShow = notifOrNull;
                                if (toShow == null) {
                                    toShow = PeopleImpl.this.getNotificationIfAvailable();
                                }
                                if (toShow == null) {
                                    lock.unlock();
                                    return;
                                }
                                Type inAppType = toShow.getType();
                                if (inAppType != Type.TAKEOVER || ConfigurationChecker.checkSurveyActivityAvailable(parent.getApplicationContext())) {
                                    int intentId = UpdateDisplayState.proposeDisplay(new InAppNotificationState(toShow, ActivityImageUtils.getHighlightColorFromBackground(parent)), PeopleImpl.this.getDistinctId(), MixpanelAPI.this.mToken);
                                    if (intentId <= 0) {
                                        Log.d("MixpanelAPI", "DisplayState Lock in inconsistent state! Please report this issue to Mixpanel");
                                        lock.unlock();
                                        return;
                                    }
                                    switch (inAppType) {
                                        case MINI:
                                            UpdateDisplayState claimed = UpdateDisplayState.claimDisplayState(intentId);
                                            InAppFragment inapp = new InAppFragment();
                                            inapp.setDisplayState(intentId, (InAppNotificationState) claimed.getDisplayState());
                                            inapp.setRetainInstance(true);
                                            FragmentTransaction transaction = parent.getFragmentManager().beginTransaction();
                                            transaction.setCustomAnimations(0, R.anim.com_mixpanel_android_slide_down);
                                            transaction.add(16908290, inapp);
                                            transaction.commit();
                                            break;
                                        case TAKEOVER:
                                            Intent intent = new Intent(parent.getApplicationContext(), SurveyActivity.class);
                                            intent.addFlags(268435456);
                                            intent.addFlags(131072);
                                            intent.putExtra("com.mixpanel.android.surveys.SurveyActivity.INTENT_ID_KEY", intentId);
                                            parent.startActivity(intent);
                                            break;
                                        default:
                                            Log.e("MixpanelAPI", "Unrecognized notification type " + inAppType + " can't be shown");
                                            break;
                                    }
                                    if (!MixpanelAPI.this.mConfig.getTestMode()) {
                                        trackNotificationSeen(toShow);
                                    }
                                    lock.unlock();
                                    return;
                                }
                                lock.unlock();
                            }
                        } finally {
                            lock.unlock();
                        }
                    }

                    private void trackNotificationSeen(InAppNotification notif) {
                        MixpanelAPI.this.track("$campaign_delivery", notif.getCampaignProperties());
                        People people = MixpanelAPI.this.getPeople().withIdentity(PeopleImpl.this.getDistinctId());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                        JSONObject notifProperties = notif.getCampaignProperties();
                        try {
                            notifProperties.put("$time", dateFormat.format(new Date()));
                        } catch (JSONException e) {
                            Log.e("MixpanelAPI", "Exception trying to track an in app notification seen", e);
                        }
                        people.append("$campaigns", Integer.valueOf(notif.getId()));
                        people.append("$notifications", notifProperties);
                    }
                });
            }
        }
    }

    private class UpdatesListener implements OnNewResultsListener, Runnable {
        private final Executor mExecutor;
        private final Set<OnMixpanelUpdatesReceivedListener> mListeners;

        private UpdatesListener() {
            this.mListeners = new HashSet();
            this.mExecutor = Executors.newSingleThreadExecutor();
        }

        public void onNewResults(String distinctId) {
            this.mExecutor.execute(this);
        }

        public synchronized void run() {
            for (OnMixpanelUpdatesReceivedListener listener : this.mListeners) {
                listener.onMixpanelUpdatesReceived();
            }
        }
    }

    MixpanelAPI(Context context, Future<SharedPreferences> referrerPreferences, String token) {
        this.mContext = context;
        this.mToken = token;
        this.mPersistentIdentity = getPersistentIdentity(context, referrerPreferences, token);
        this.mUpdatesListener = new UpdatesListener();
        this.mDecideUpdates = null;
        String peopleId = this.mPersistentIdentity.getPeopleDistinctId();
        if (peopleId != null) {
            this.mDecideUpdates = constructDecideUpdates(token, peopleId, this.mUpdatesListener);
        }
        registerMixpanelActivityLifecycleCallbacks();
        if (this.mDecideUpdates != null) {
            this.mMessages.installDecideCheck(this.mDecideUpdates);
        }
    }

    public static MixpanelAPI getInstance(Context context, String token) {
        MixpanelAPI mixpanelAPI = null;
        if (!(token == null || context == null)) {
            synchronized (sInstanceMap) {
                Context appContext = context.getApplicationContext();
                if (sReferrerPrefs == null) {
                    sReferrerPrefs = sPrefsLoader.loadPreferences(context, "com.mixpanel.android.mpmetrics.ReferralInfo", null);
                }
                Map<Context, MixpanelAPI> instances = (Map) sInstanceMap.get(token);
                if (instances == null) {
                    instances = new HashMap();
                    sInstanceMap.put(token, instances);
                }
                mixpanelAPI = (MixpanelAPI) instances.get(appContext);
                if (mixpanelAPI == null) {
                    mixpanelAPI = new MixpanelAPI(appContext, sReferrerPrefs, token);
                    registerAppLinksListeners(context, mixpanelAPI);
                    instances.put(appContext, mixpanelAPI);
                }
                checkIntentForInboundAppLink(context);
            }
        }
        return mixpanelAPI;
    }

    public void identify(String distinctId) {
        this.mPersistentIdentity.setEventsDistinctId(distinctId);
    }

    public void track(String eventName, JSONObject properties) {
        try {
            String key;
            JSONObject messageProps = new JSONObject();
            for (Entry<String, String> entry : this.mPersistentIdentity.getReferrerProperties().entrySet()) {
                messageProps.put((String) entry.getKey(), (String) entry.getValue());
            }
            JSONObject superProperties = this.mPersistentIdentity.getSuperProperties();
            Iterator<?> superIter = superProperties.keys();
            while (superIter.hasNext()) {
                key = (String) superIter.next();
                messageProps.put(key, superProperties.get(key));
            }
            messageProps.put("time", System.currentTimeMillis() / 1000);
            messageProps.put("distinct_id", getDistinctId());
            if (properties != null) {
                Iterator<?> propIter = properties.keys();
                while (propIter.hasNext()) {
                    key = (String) propIter.next();
                    messageProps.put(key, properties.get(key));
                }
            }
            this.mMessages.eventsMessage(new EventDescription(eventName, messageProps, this.mToken));
        } catch (JSONException e) {
            Log.e("MixpanelAPI", "Exception tracking event " + eventName, e);
        }
    }

    public void flush() {
        this.mMessages.postToServer();
    }

    public String getDistinctId() {
        return this.mPersistentIdentity.getEventsDistinctId();
    }

    public People getPeople() {
        return this.mPeople;
    }

    @TargetApi(14)
    void registerMixpanelActivityLifecycleCallbacks() {
        if (VERSION.SDK_INT >= 14 && this.mConfig.getAutoShowMixpanelUpdates()) {
            if (this.mContext.getApplicationContext() instanceof Application) {
                ((Application) this.mContext.getApplicationContext()).registerActivityLifecycleCallbacks(new MixpanelActivityLifecycleCallbacks(this));
            } else if (MPConfig.DEBUG) {
                Log.d("MixpanelAPI", "Context is NOT instanceof Application, AutoShowMixpanelUpdates will be disabled.");
            }
        }
    }

    static void allInstances(InstanceProcessor processor) {
        synchronized (sInstanceMap) {
            for (Map<Context, MixpanelAPI> contextInstances : sInstanceMap.values()) {
                for (MixpanelAPI instance : contextInstances.values()) {
                    processor.process(instance);
                }
            }
        }
    }

    AnalyticsMessages getAnalyticsMessages() {
        return AnalyticsMessages.getInstance(this.mContext);
    }

    MPConfig getConfig() {
        return MPConfig.getInstance(this.mContext);
    }

    PersistentIdentity getPersistentIdentity(Context context, Future<SharedPreferences> referrerPreferences, String token) {
        OnPrefsLoadedListener listener = new OnPrefsLoadedListener() {
            public void onPrefsLoaded(SharedPreferences preferences) {
                JSONArray records = PersistentIdentity.waitingPeopleRecordsForSending(preferences);
                if (records != null) {
                    MixpanelAPI.this.sendAllPeopleRecords(records);
                }
            }
        };
        return new PersistentIdentity(referrerPreferences, sPrefsLoader.loadPreferences(context, "com.mixpanel.android.mpmetrics.MixpanelAPI_" + token, listener));
    }

    DecideUpdates constructDecideUpdates(String token, String peopleId, OnNewResultsListener listener) {
        return new DecideUpdates(token, peopleId, listener);
    }

    boolean canUpdate() {
        return this.mDecideUpdates != null;
    }

    private void recordPeopleMessage(JSONObject message) {
        if (message.has("$distinct_id")) {
            this.mMessages.peopleMessage(message);
        } else {
            this.mPersistentIdentity.storeWaitingPeopleRecord(message);
        }
    }

    private void sendAllPeopleRecords(JSONArray records) {
        for (int i = 0; i < records.length(); i++) {
            try {
                this.mMessages.peopleMessage(records.getJSONObject(i));
            } catch (JSONException e) {
                Log.e("MixpanelAPI", "Malformed people record stored pending identity, will not send it.", e);
            }
        }
    }

    private static void registerAppLinksListeners(Context context, final MixpanelAPI mixpanel) {
        try {
            Class<?> clazz = Class.forName("android.support.v4.content.LocalBroadcastManager");
            Method methodGetInstance = clazz.getMethod("getInstance", new Class[]{Context.class});
            clazz.getMethod("registerReceiver", new Class[]{BroadcastReceiver.class, IntentFilter.class}).invoke(methodGetInstance.invoke(null, new Object[]{context}), new Object[]{new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    JSONObject properties = new JSONObject();
                    Bundle args = intent.getBundleExtra("event_args");
                    if (args != null) {
                        for (String key : args.keySet()) {
                            try {
                                properties.put(key, args.get(key));
                            } catch (JSONException e) {
                                Log.e("MixpanelAPI - App Links (OPTIONAL)", "failed to add key \"" + key + "\" to properties for tracking bolts event", e);
                            }
                        }
                    }
                    mixpanel.track("$" + intent.getStringExtra("event_name"), properties);
                }
            }, new IntentFilter("com.parse.bolts.measurement_event")});
        } catch (InvocationTargetException e) {
            Log.d("MixpanelAPI - App Links (OPTIONAL)", "Failed to invoke LocalBroadcastManager.registerReceiver() -- App Links tracking will not be enabled due to this exception", e);
        } catch (ClassNotFoundException e2) {
            Log.d("MixpanelAPI - App Links (OPTIONAL)", "To enable App Links tracking android.support.v4 must be installed: " + e2.getMessage());
        } catch (NoSuchMethodException e3) {
            Log.d("MixpanelAPI - App Links (OPTIONAL)", "To enable App Links tracking android.support.v4 must be installed: " + e3.getMessage());
        } catch (IllegalAccessException e4) {
            Log.d("MixpanelAPI - App Links (OPTIONAL)", "App Links tracking will not be enabled due to this exception: " + e4.getMessage());
        }
    }

    private static void checkIntentForInboundAppLink(Context context) {
        if (context instanceof Activity) {
            try {
                Class<?> clazz = Class.forName("bolts.AppLinks");
                Intent intent = ((Activity) context).getIntent();
                clazz.getMethod("getTargetUrlFromInboundIntent", new Class[]{Context.class, Intent.class}).invoke(null, new Object[]{context, intent});
                return;
            } catch (InvocationTargetException e) {
                Log.d("MixpanelAPI - App Links (OPTIONAL)", "Failed to invoke bolts.AppLinks.getTargetUrlFromInboundIntent() -- Unable to detect inbound App Links", e);
                return;
            } catch (ClassNotFoundException e2) {
                Log.d("MixpanelAPI - App Links (OPTIONAL)", "Please install the Bolts library >= 1.1.2 to track App Links: " + e2.getMessage());
                return;
            } catch (NoSuchMethodException e3) {
                Log.d("MixpanelAPI - App Links (OPTIONAL)", "Please install the Bolts library >= 1.1.2 to track App Links: " + e3.getMessage());
                return;
            } catch (IllegalAccessException e4) {
                Log.d("MixpanelAPI - App Links (OPTIONAL)", "Unable to detect inbound App Links: " + e4.getMessage());
                return;
            }
        }
        Log.d("MixpanelAPI - App Links (OPTIONAL)", "Context is not an instance of Activity. To detect inbound App Links, pass an instance of an Activity to getInstance.");
    }
}
