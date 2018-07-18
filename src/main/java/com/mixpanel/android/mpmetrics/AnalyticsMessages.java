package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mixpanel.android.mpmetrics.MPDbAdapter.Table;
import com.mixpanel.android.util.Base64Coder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

class AnalyticsMessages {
    private static int ENQUEUE_EVENTS = 1;
    private static int ENQUEUE_PEOPLE = 0;
    private static int FLUSH_QUEUE = 2;
    private static int INSTALL_DECIDE_CHECK = 12;
    private static int KILL_WORKER = 5;
    private static int REGISTER_FOR_GCM = 13;
    private static int SET_DISABLE_FALLBACK = 10;
    private static int SET_FLUSH_INTERVAL = 4;
    private static final Map<Context, AnalyticsMessages> sInstances = new HashMap();
    private final MPConfig mConfig;
    private final Context mContext;
    private final Worker mWorker = new Worker();

    static class EventDescription {
        private final String eventName;
        private final JSONObject properties;
        private final String token;

        public EventDescription(String eventName, JSONObject properties, String token) {
            this.eventName = eventName;
            this.properties = properties;
            this.token = token;
        }

        public String getEventName() {
            return this.eventName;
        }

        public JSONObject getProperties() {
            return this.properties;
        }

        public String getToken() {
            return this.token;
        }
    }

    private class Worker {
        private long mAveFlushFrequency = 0;
        private long mFlushCount = 0;
        private Handler mHandler = restartWorkerThread();
        private final Object mHandlerLock = new Object();
        private long mLastFlushTime = -1;
        private SystemInformation mSystemInformation;

        private class AnalyticsMessageHandler extends Handler {
            private MPDbAdapter mDbAdapter = null;
            private final DecideChecker mDecideChecker;
            private boolean mDisableFallback;
            private long mFlushInterval;

            public AnalyticsMessageHandler(Looper looper) {
                super(looper);
                this.mDecideChecker = new DecideChecker(AnalyticsMessages.this.mContext, AnalyticsMessages.this.mConfig);
                this.mDisableFallback = AnalyticsMessages.this.mConfig.getDisableFallback();
                this.mFlushInterval = (long) AnalyticsMessages.this.mConfig.getFlushInterval();
                Worker.this.mSystemInformation = new SystemInformation(AnalyticsMessages.this.mContext);
            }

            public void handleMessage(Message msg) {
                if (this.mDbAdapter == null) {
                    this.mDbAdapter = AnalyticsMessages.this.makeDbAdapter(AnalyticsMessages.this.mContext);
                    this.mDbAdapter.cleanupEvents(System.currentTimeMillis() - ((long) AnalyticsMessages.this.mConfig.getDataExpiration()), Table.EVENTS);
                    this.mDbAdapter.cleanupEvents(System.currentTimeMillis() - ((long) AnalyticsMessages.this.mConfig.getDataExpiration()), Table.PEOPLE);
                }
                int queueDepth = -1;
                try {
                    if (msg.what == AnalyticsMessages.SET_FLUSH_INTERVAL) {
                        Long newIntervalObj = msg.obj;
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Changing flush interval to " + newIntervalObj);
                        this.mFlushInterval = newIntervalObj.longValue();
                        removeMessages(AnalyticsMessages.FLUSH_QUEUE);
                    } else if (msg.what == AnalyticsMessages.SET_DISABLE_FALLBACK) {
                        Boolean disableState = msg.obj;
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Setting fallback to " + disableState);
                        this.mDisableFallback = disableState.booleanValue();
                    } else if (msg.what == AnalyticsMessages.ENQUEUE_PEOPLE) {
                        message = msg.obj;
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Queuing people record for sending later");
                        AnalyticsMessages.this.logAboutMessageToMixpanel("    " + message.toString());
                        queueDepth = this.mDbAdapter.addJSON(message, Table.PEOPLE);
                    } else if (msg.what == AnalyticsMessages.ENQUEUE_EVENTS) {
                        EventDescription eventDescription = msg.obj;
                        try {
                            message = prepareEventObject(eventDescription);
                            AnalyticsMessages.this.logAboutMessageToMixpanel("Queuing event for sending later");
                            AnalyticsMessages.this.logAboutMessageToMixpanel("    " + message.toString());
                            queueDepth = this.mDbAdapter.addJSON(message, Table.EVENTS);
                        } catch (RuntimeException e) {
                            Log.e("MixpanelAPI", "Exception tracking event " + eventDescription.getEventName(), e);
                        }
                    } else if (msg.what == AnalyticsMessages.FLUSH_QUEUE) {
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Flushing queue due to scheduled or forced flush");
                        Worker.this.updateFlushFrequency();
                        this.mDecideChecker.runDecideChecks(AnalyticsMessages.this.getPoster());
                        sendAllData(this.mDbAdapter);
                    } else if (msg.what == AnalyticsMessages.INSTALL_DECIDE_CHECK) {
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Installing a check for surveys and in app notifications");
                        this.mDecideChecker.addDecideCheck(msg.obj);
                        this.mDecideChecker.runDecideChecks(AnalyticsMessages.this.getPoster());
                    } else if (msg.what == AnalyticsMessages.REGISTER_FOR_GCM) {
                        runGCMRegistration(msg.obj);
                    } else if (msg.what == AnalyticsMessages.KILL_WORKER) {
                        Log.w("MixpanelAPI", "Worker received a hard kill. Dumping all events and force-killing. Thread id " + Thread.currentThread().getId());
                        synchronized (Worker.this.mHandlerLock) {
                            this.mDbAdapter.deleteDB();
                            Worker.this.mHandler = null;
                            Looper.myLooper().quit();
                        }
                    } else {
                        Log.e("MixpanelAPI", "Unexpected message received by Mixpanel worker: " + msg);
                    }
                    if (queueDepth >= AnalyticsMessages.this.mConfig.getBulkUploadLimit()) {
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Flushing queue due to bulk upload limit");
                        Worker.this.updateFlushFrequency();
                        sendAllData(this.mDbAdapter);
                    } else if (queueDepth > 0 && !hasMessages(AnalyticsMessages.FLUSH_QUEUE)) {
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Queue depth " + queueDepth + " - Adding flush in " + this.mFlushInterval);
                        if (this.mFlushInterval >= 0) {
                            sendEmptyMessageDelayed(AnalyticsMessages.FLUSH_QUEUE, this.mFlushInterval);
                        }
                    }
                } catch (RuntimeException e2) {
                    Log.e("MixpanelAPI", "Worker threw an unhandled exception", e2);
                    synchronized (Worker.this.mHandlerLock) {
                        Worker.this.mHandler = null;
                        try {
                            Looper.myLooper().quit();
                            Log.e("MixpanelAPI", "Mixpanel will not process any more analytics messages", e2);
                        } catch (Exception tooLate) {
                            Log.e("MixpanelAPI", "Could not halt looper", tooLate);
                        }
                    }
                }
            }

            private void runGCMRegistration(String senderID) {
                try {
                    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(AnalyticsMessages.this.mContext) != 0) {
                        Log.i("MixpanelAPI", "Can't register for push notifications, Google Play Services are not installed.");
                        return;
                    }
                    final String registrationId = GoogleCloudMessaging.getInstance(AnalyticsMessages.this.mContext).register(new String[]{senderID});
                    MixpanelAPI.allInstances(new InstanceProcessor() {
                        public void process(MixpanelAPI api) {
                            if (MPConfig.DEBUG) {
                                Log.d("MixpanelAPI", "Using existing pushId " + registrationId);
                            }
                            api.getPeople().setPushRegistrationId(registrationId);
                        }
                    });
                } catch (IOException e) {
                    Log.i("MixpanelAPI", "Exception when trying to register for GCM", e);
                } catch (NoClassDefFoundError e2) {
                    Log.w("MixpanelAPI", "Google play services were not part of this build, push notifications cannot be registered or delivered");
                }
            }

            private void sendAllData(MPDbAdapter dbAdapter) {
                if (AnalyticsMessages.this.getPoster().isOnline(AnalyticsMessages.this.mContext)) {
                    AnalyticsMessages.this.logAboutMessageToMixpanel("Sending records to Mixpanel");
                    if (this.mDisableFallback) {
                        sendData(dbAdapter, Table.EVENTS, new String[]{AnalyticsMessages.this.mConfig.getEventsEndpoint()});
                        sendData(dbAdapter, Table.PEOPLE, new String[]{AnalyticsMessages.this.mConfig.getPeopleEndpoint()});
                        return;
                    }
                    sendData(dbAdapter, Table.EVENTS, new String[]{AnalyticsMessages.this.mConfig.getEventsEndpoint(), AnalyticsMessages.this.mConfig.getEventsFallbackEndpoint()});
                    sendData(dbAdapter, Table.PEOPLE, new String[]{AnalyticsMessages.this.mConfig.getPeopleEndpoint(), AnalyticsMessages.this.mConfig.getPeopleFallbackEndpoint()});
                    return;
                }
                AnalyticsMessages.this.logAboutMessageToMixpanel("Not flushing data to Mixpanel because the device is not connected to the internet.");
            }

            private void sendData(MPDbAdapter dbAdapter, Table table, String[] urls) {
                ServerMessage poster = AnalyticsMessages.this.getPoster();
                String[] eventsData = dbAdapter.generateDataString(table);
                if (eventsData != null) {
                    String lastId = eventsData[0];
                    String rawMessage = eventsData[1];
                    String encodedData = Base64Coder.encodeString(rawMessage);
                    List<NameValuePair> params = new ArrayList(1);
                    params.add(new BasicNameValuePair("data", encodedData));
                    if (MPConfig.DEBUG) {
                        params.add(new BasicNameValuePair("verbose", "1"));
                    }
                    boolean deleteEvents = true;
                    String[] arr$ = urls;
                    int len$ = arr$.length;
                    int i$ = 0;
                    while (i$ < len$) {
                        String url = arr$[i$];
                        try {
                            byte[] response = poster.performRequest(url, params);
                            deleteEvents = true;
                            if (response != null) {
                                String parsedResponse = new String(response, "UTF-8");
                                AnalyticsMessages.this.logAboutMessageToMixpanel("Successfully posted to " + url + ": \n" + rawMessage);
                                AnalyticsMessages.this.logAboutMessageToMixpanel("Response was " + parsedResponse);
                                break;
                            } else if (MPConfig.DEBUG) {
                                Log.d("MixpanelAPI", "Response was null, unexpected failure posting to " + url + ".");
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException("UTF not supported on this platform?", e);
                        } catch (OutOfMemoryError e2) {
                            Log.e("MixpanelAPI", "Out of memory when posting to " + url + ".", e2);
                        } catch (MalformedURLException e3) {
                            Log.e("MixpanelAPI", "Cannot interpret " + url + " as a URL.", e3);
                        } catch (IOException e4) {
                            if (MPConfig.DEBUG) {
                                Log.d("MixpanelAPI", "Cannot post message to " + url + ".", e4);
                            }
                            deleteEvents = false;
                            i$++;
                        }
                    }
                    if (deleteEvents) {
                        AnalyticsMessages.this.logAboutMessageToMixpanel("Not retrying this batch of events, deleting them from DB.");
                        dbAdapter.cleanupEvents(lastId, table);
                        return;
                    }
                    AnalyticsMessages.this.logAboutMessageToMixpanel("Retrying this batch of events.");
                    if (!hasMessages(AnalyticsMessages.FLUSH_QUEUE)) {
                        sendEmptyMessageDelayed(AnalyticsMessages.FLUSH_QUEUE, this.mFlushInterval);
                    }
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private JSONObject getDefaultEventProperties() throws JSONException {
                JSONObject ret = new JSONObject();
                ret.put("mp_lib", "android");
                ret.put("$lib_version", "4.4.1");
                ret.put("$os", "Android");
                ret.put("$os_version", VERSION.RELEASE == null ? "UNKNOWN" : VERSION.RELEASE);
                ret.put("$manufacturer", Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
                ret.put("$brand", Build.BRAND == null ? "UNKNOWN" : Build.BRAND);
                ret.put("$model", Build.MODEL == null ? "UNKNOWN" : Build.MODEL);
                try {
                    switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(AnalyticsMessages.this.mContext)) {
                        case 0:
                            ret.put("$google_play_services", "available");
                            break;
                        case 1:
                            ret.put("$google_play_services", "missing");
                            break;
                        case 2:
                            ret.put("$google_play_services", "out of date");
                            break;
                        case 3:
                            ret.put("$google_play_services", "disabled");
                            break;
                        case 9:
                            ret.put("$google_play_services", "invalid");
                            break;
                    }
                } catch (NoClassDefFoundError e) {
                    ret.put("$google_play_services", "not included");
                }
                DisplayMetrics displayMetrics = Worker.this.mSystemInformation.getDisplayMetrics();
                ret.put("$screen_dpi", displayMetrics.densityDpi);
                ret.put("$screen_height", displayMetrics.heightPixels);
                ret.put("$screen_width", displayMetrics.widthPixels);
                String applicationVersionName = Worker.this.mSystemInformation.getAppVersionName();
                if (applicationVersionName != null) {
                    ret.put("$app_version", applicationVersionName);
                }
                Boolean hasNFC = Boolean.valueOf(Worker.this.mSystemInformation.hasNFC());
                if (hasNFC != null) {
                    ret.put("$has_nfc", hasNFC.booleanValue());
                }
                Boolean hasTelephony = Boolean.valueOf(Worker.this.mSystemInformation.hasTelephony());
                if (hasTelephony != null) {
                    ret.put("$has_telephone", hasTelephony.booleanValue());
                }
                String carrier = Worker.this.mSystemInformation.getCurrentNetworkOperator();
                if (carrier != null) {
                    ret.put("$carrier", carrier);
                }
                Boolean isWifi = Worker.this.mSystemInformation.isWifiConnected();
                if (isWifi != null) {
                    ret.put("$wifi", isWifi.booleanValue());
                }
                Boolean isBluetoothEnabled = Worker.this.mSystemInformation.isBluetoothEnabled();
                if (isBluetoothEnabled != null) {
                    ret.put("$bluetooth_enabled", isBluetoothEnabled);
                }
                String bluetoothVersion = Worker.this.mSystemInformation.getBluetoothVersion();
                if (bluetoothVersion != null) {
                    ret.put("$bluetooth_version", bluetoothVersion);
                }
                return ret;
            }

            private JSONObject prepareEventObject(EventDescription eventDescription) throws JSONException {
                JSONObject eventObj = new JSONObject();
                JSONObject eventProperties = eventDescription.getProperties();
                JSONObject sendProperties = getDefaultEventProperties();
                sendProperties.put("token", eventDescription.getToken());
                if (eventProperties != null) {
                    Iterator<?> iter = eventProperties.keys();
                    while (iter.hasNext()) {
                        String key = (String) iter.next();
                        sendProperties.put(key, eventProperties.get(key));
                    }
                }
                eventObj.put("event", eventDescription.getEventName());
                eventObj.put("properties", sendProperties);
                return eventObj;
            }
        }

        public void runMessage(Message msg) {
            synchronized (this.mHandlerLock) {
                if (this.mHandler == null) {
                    AnalyticsMessages.this.logAboutMessageToMixpanel("Dead mixpanel worker dropping a message: " + msg.what);
                } else {
                    this.mHandler.sendMessage(msg);
                }
            }
        }

        private Handler restartWorkerThread() {
            HandlerThread thread = new HandlerThread("com.mixpanel.android.AnalyticsWorker", 1);
            thread.start();
            return new AnalyticsMessageHandler(thread.getLooper());
        }

        private void updateFlushFrequency() {
            long now = System.currentTimeMillis();
            long newFlushCount = this.mFlushCount + 1;
            if (this.mLastFlushTime > 0) {
                this.mAveFlushFrequency = ((now - this.mLastFlushTime) + (this.mAveFlushFrequency * this.mFlushCount)) / newFlushCount;
                AnalyticsMessages.this.logAboutMessageToMixpanel("Average send frequency approximately " + (this.mAveFlushFrequency / 1000) + " seconds.");
            }
            this.mLastFlushTime = now;
            this.mFlushCount = newFlushCount;
        }
    }

    AnalyticsMessages(Context context) {
        this.mContext = context;
        this.mConfig = getConfig(context);
    }

    public static AnalyticsMessages getInstance(Context messageContext) {
        AnalyticsMessages ret;
        synchronized (sInstances) {
            Context appContext = messageContext.getApplicationContext();
            if (sInstances.containsKey(appContext)) {
                ret = (AnalyticsMessages) sInstances.get(appContext);
            } else {
                ret = new AnalyticsMessages(appContext);
                sInstances.put(appContext, ret);
            }
        }
        return ret;
    }

    public void eventsMessage(EventDescription eventDescription) {
        Message m = Message.obtain();
        m.what = ENQUEUE_EVENTS;
        m.obj = eventDescription;
        this.mWorker.runMessage(m);
    }

    public void peopleMessage(JSONObject peopleJson) {
        Message m = Message.obtain();
        m.what = ENQUEUE_PEOPLE;
        m.obj = peopleJson;
        this.mWorker.runMessage(m);
    }

    public void postToServer() {
        Message m = Message.obtain();
        m.what = FLUSH_QUEUE;
        this.mWorker.runMessage(m);
    }

    public void installDecideCheck(DecideUpdates check) {
        Message m = Message.obtain();
        m.what = INSTALL_DECIDE_CHECK;
        m.obj = check;
        this.mWorker.runMessage(m);
    }

    protected MPDbAdapter makeDbAdapter(Context context) {
        return new MPDbAdapter(context);
    }

    protected MPConfig getConfig(Context context) {
        return MPConfig.getInstance(context);
    }

    protected ServerMessage getPoster() {
        return new ServerMessage();
    }

    private void logAboutMessageToMixpanel(String message) {
        if (MPConfig.DEBUG) {
            Log.d("MixpanelAPI", message + " (Thread " + Thread.currentThread().getId() + ")");
        }
    }
}
