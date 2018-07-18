package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build.VERSION;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"CommitPrefEdits"})
class PersistentIdentity {
    private static boolean sReferrerPrefsDirty = true;
    private static final Object sReferrerPrefsLock = new Object();
    private String mEventsDistinctId;
    private boolean mIdentitiesLoaded = false;
    private final Future<SharedPreferences> mLoadReferrerPreferences;
    private final Future<SharedPreferences> mLoadStoredPreferences;
    private String mPeopleDistinctId;
    private final OnSharedPreferenceChangeListener mReferrerChangeListener = new OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            synchronized (PersistentIdentity.sReferrerPrefsLock) {
                PersistentIdentity.this.readReferrerProperties();
                PersistentIdentity.sReferrerPrefsDirty = false;
            }
        }
    };
    private Map<String, String> mReferrerPropertiesCache = null;
    private JSONObject mSuperPropertiesCache = null;
    private JSONArray mWaitingPeopleRecords;

    private void readSuperProperties() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        r3 = r6.mLoadStoredPreferences;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r1 = r3.get();	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r1 = (android.content.SharedPreferences) r1;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = "super_properties";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = "{}";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r2 = r1.getString(r3, r4);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = com.mixpanel.android.mpmetrics.MPConfig.DEBUG;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        if (r3 == 0) goto L_0x002c;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
    L_0x0014:
        r3 = "MixpanelAPI PersistentIdentity";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4.<init>();	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r5 = "Loading Super Properties ";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = r4.append(r5);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = r4.append(r2);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = r4.toString();	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        android.util.Log.d(r3, r4);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
    L_0x002c:
        r3 = new org.json.JSONObject;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3.<init>(r2);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r6.mSuperPropertiesCache = r3;	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = r6.mSuperPropertiesCache;
        if (r3 != 0) goto L_0x003e;
    L_0x0037:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r6.mSuperPropertiesCache = r3;
    L_0x003e:
        return;
    L_0x003f:
        r0 = move-exception;
        r3 = "MixpanelAPI PersistentIdentity";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = "Cannot load superProperties from SharedPreferences.";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r5 = r0.getCause();	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        android.util.Log.e(r3, r4, r5);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = r6.mSuperPropertiesCache;
        if (r3 != 0) goto L_0x003e;
    L_0x004f:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r6.mSuperPropertiesCache = r3;
        goto L_0x003e;
    L_0x0057:
        r0 = move-exception;
        r3 = "MixpanelAPI PersistentIdentity";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = "Cannot load superProperties from SharedPreferences.";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        android.util.Log.e(r3, r4, r0);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = r6.mSuperPropertiesCache;
        if (r3 != 0) goto L_0x003e;
    L_0x0063:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r6.mSuperPropertiesCache = r3;
        goto L_0x003e;
    L_0x006b:
        r0 = move-exception;
        r3 = "MixpanelAPI PersistentIdentity";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r4 = "Cannot parse stored superProperties";	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        android.util.Log.e(r3, r4);	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r6.storeSuperProperties();	 Catch:{ ExecutionException -> 0x003f, InterruptedException -> 0x0057, JSONException -> 0x006b, all -> 0x0082 }
        r3 = r6.mSuperPropertiesCache;
        if (r3 != 0) goto L_0x003e;
    L_0x007a:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r6.mSuperPropertiesCache = r3;
        goto L_0x003e;
    L_0x0082:
        r3 = move-exception;
        r4 = r6.mSuperPropertiesCache;
        if (r4 != 0) goto L_0x008e;
    L_0x0087:
        r4 = new org.json.JSONObject;
        r4.<init>();
        r6.mSuperPropertiesCache = r4;
    L_0x008e:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mixpanel.android.mpmetrics.PersistentIdentity.readSuperProperties():void");
    }

    public static JSONArray waitingPeopleRecordsForSending(SharedPreferences storedPreferences) {
        JSONArray ret = null;
        String peopleDistinctId = storedPreferences.getString("people_distinct_id", null);
        String waitingPeopleRecords = storedPreferences.getString("waiting_array", null);
        if (!(waitingPeopleRecords == null || peopleDistinctId == null)) {
            try {
                JSONArray waitingObjects = new JSONArray(waitingPeopleRecords);
                ret = new JSONArray();
                for (int i = 0; i < waitingObjects.length(); i++) {
                    try {
                        JSONObject ob = waitingObjects.getJSONObject(i);
                        ob.put("$distinct_id", peopleDistinctId);
                        ret.put(ob);
                    } catch (JSONException e) {
                        Log.e("MixpanelAPI PersistentIdentity", "Unparsable object found in waiting people records", e);
                    }
                }
                Editor editor = storedPreferences.edit();
                editor.remove("waiting_array");
                writeEdits(editor);
            } catch (JSONException e2) {
                Log.e("MixpanelAPI PersistentIdentity", "Waiting people records were unreadable.");
                return null;
            }
        }
        return ret;
    }

    public static void writeReferrerPrefs(Context context, String preferencesName, Map<String, String> properties) {
        synchronized (sReferrerPrefsLock) {
            Editor editor = context.getSharedPreferences(preferencesName, 0).edit();
            editor.clear();
            for (Entry<String, String> entry : properties.entrySet()) {
                editor.putString((String) entry.getKey(), (String) entry.getValue());
            }
            writeEdits(editor);
            sReferrerPrefsDirty = true;
        }
    }

    public PersistentIdentity(Future<SharedPreferences> referrerPreferences, Future<SharedPreferences> storedPreferences) {
        this.mLoadReferrerPreferences = referrerPreferences;
        this.mLoadStoredPreferences = storedPreferences;
    }

    public synchronized JSONObject getSuperProperties() {
        if (this.mSuperPropertiesCache == null) {
            readSuperProperties();
        }
        return this.mSuperPropertiesCache;
    }

    public Map<String, String> getReferrerProperties() {
        synchronized (sReferrerPrefsLock) {
            if (sReferrerPrefsDirty || this.mReferrerPropertiesCache == null) {
                readReferrerProperties();
                sReferrerPrefsDirty = false;
            }
        }
        return this.mReferrerPropertiesCache;
    }

    public synchronized String getEventsDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mEventsDistinctId;
    }

    public synchronized void setEventsDistinctId(String eventsDistinctId) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        this.mEventsDistinctId = eventsDistinctId;
        writeIdentities();
    }

    public synchronized String getPeopleDistinctId() {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        return this.mPeopleDistinctId;
    }

    public synchronized void storeWaitingPeopleRecord(JSONObject record) {
        if (!this.mIdentitiesLoaded) {
            readIdentities();
        }
        if (this.mWaitingPeopleRecords == null) {
            this.mWaitingPeopleRecords = new JSONArray();
        }
        this.mWaitingPeopleRecords.put(record);
        writeIdentities();
    }

    public synchronized void storePushId(String registrationId) {
        try {
            Editor editor = ((SharedPreferences) this.mLoadStoredPreferences.get()).edit();
            editor.putString("push_id", registrationId);
            writeEdits(editor);
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write push id to shared preferences", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write push id to shared preferences", e2);
        }
    }

    public synchronized void clearPushId() {
        try {
            Editor editor = ((SharedPreferences) this.mLoadStoredPreferences.get()).edit();
            editor.remove("push_id");
            writeEdits(editor);
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write push id to shared preferences", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write push id to shared preferences", e2);
        }
    }

    private void readReferrerProperties() {
        this.mReferrerPropertiesCache = new HashMap();
        try {
            SharedPreferences referrerPrefs = (SharedPreferences) this.mLoadReferrerPreferences.get();
            referrerPrefs.unregisterOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            referrerPrefs.registerOnSharedPreferenceChangeListener(this.mReferrerChangeListener);
            for (Entry<String, ?> entry : referrerPrefs.getAll().entrySet()) {
                this.mReferrerPropertiesCache.put((String) entry.getKey(), entry.getValue().toString());
            }
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot load referrer properties from shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot load referrer properties from shared preferences.", e2);
        }
    }

    private void storeSuperProperties() {
        if (this.mSuperPropertiesCache == null) {
            Log.e("MixpanelAPI PersistentIdentity", "storeSuperProperties should not be called with uninitialized superPropertiesCache.");
            return;
        }
        String props = this.mSuperPropertiesCache.toString();
        if (MPConfig.DEBUG) {
            Log.d("MixpanelAPI PersistentIdentity", "Storing Super Properties " + props);
        }
        try {
            Editor editor = ((SharedPreferences) this.mLoadStoredPreferences.get()).edit();
            editor.putString("super_properties", props);
            writeEdits(editor);
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot store superProperties in shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot store superProperties in shared preferences.", e2);
        }
    }

    private void readIdentities() {
        SharedPreferences prefs = null;
        try {
            prefs = (SharedPreferences) this.mLoadStoredPreferences.get();
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot read distinct ids from sharedPreferences.", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Cannot read distinct ids from sharedPreferences.", e2);
        }
        if (prefs != null) {
            this.mEventsDistinctId = prefs.getString("events_distinct_id", null);
            this.mPeopleDistinctId = prefs.getString("people_distinct_id", null);
            this.mWaitingPeopleRecords = null;
            String storedWaitingRecord = prefs.getString("waiting_array", null);
            if (storedWaitingRecord != null) {
                try {
                    this.mWaitingPeopleRecords = new JSONArray(storedWaitingRecord);
                } catch (JSONException e3) {
                    Log.e("MixpanelAPI PersistentIdentity", "Could not interpret waiting people JSON record " + storedWaitingRecord);
                }
            }
            if (this.mEventsDistinctId == null) {
                this.mEventsDistinctId = UUID.randomUUID().toString();
                writeIdentities();
            }
            this.mIdentitiesLoaded = true;
        }
    }

    private void writeIdentities() {
        try {
            Editor prefsEditor = ((SharedPreferences) this.mLoadStoredPreferences.get()).edit();
            prefsEditor.putString("events_distinct_id", this.mEventsDistinctId);
            prefsEditor.putString("people_distinct_id", this.mPeopleDistinctId);
            if (this.mWaitingPeopleRecords == null) {
                prefsEditor.remove("waiting_array");
            } else {
                prefsEditor.putString("waiting_array", this.mWaitingPeopleRecords.toString());
            }
            writeEdits(prefsEditor);
        } catch (ExecutionException e) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write distinct ids to shared preferences.", e.getCause());
        } catch (InterruptedException e2) {
            Log.e("MixpanelAPI PersistentIdentity", "Can't write distinct ids to shared preferences.", e2);
        }
    }

    @TargetApi(9)
    private static void writeEdits(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
