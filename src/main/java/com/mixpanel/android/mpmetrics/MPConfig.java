package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

public class MPConfig {
    public static boolean DEBUG = false;
    private static MPConfig sInstance;
    private static final Object sInstanceLock = new Object();
    private final boolean mAutoShowMixpanelUpdates;
    private final int mBulkUploadLimit;
    private final int mDataExpiration;
    private final String mDecideEndpoint;
    private final String mDecideFallbackEndpoint;
    private final boolean mDisableFallback;
    private final String mEventsEndpoint;
    private final String mEventsFallbackEndpoint;
    private final int mFlushInterval;
    private final String mPeopleEndpoint;
    private final String mPeopleFallbackEndpoint;
    private final boolean mTestMode;

    public static MPConfig getInstance(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = readConfig(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    MPConfig(Bundle metaData) {
        boolean z = true;
        DEBUG = metaData.getBoolean("com.mixpanel.android.MPConfig.EnableDebugLogging", false);
        if (metaData.containsKey("com.mixpanel.android.MPConfig.AutoCheckForSurveys")) {
            Log.w("MixpanelAPI.MPConfig", "com.mixpanel.android.MPConfig.AutoCheckForSurveys has been deprecated in favor of com.mixpanel.android.MPConfig.AutoShowMixpanelUpdates. Please update this key as soon as possible.");
        }
        this.mBulkUploadLimit = metaData.getInt("com.mixpanel.android.MPConfig.BulkUploadLimit", 40);
        this.mFlushInterval = metaData.getInt("com.mixpanel.android.MPConfig.FlushInterval", 60000);
        this.mDataExpiration = metaData.getInt("com.mixpanel.android.MPConfig.DataExpiration", 432000000);
        this.mDisableFallback = metaData.getBoolean("com.mixpanel.android.MPConfig.DisableFallback", true);
        boolean surveysAutoCheck = metaData.getBoolean("com.mixpanel.android.MPConfig.AutoCheckForSurveys", true);
        boolean mixpanelUpdatesAutoShow = metaData.getBoolean("com.mixpanel.android.MPConfig.AutoShowMixpanelUpdates", true);
        if (!(surveysAutoCheck && mixpanelUpdatesAutoShow)) {
            z = false;
        }
        this.mAutoShowMixpanelUpdates = z;
        this.mTestMode = metaData.getBoolean("com.mixpanel.android.MPConfig.TestMode", false);
        String eventsEndpoint = metaData.getString("com.mixpanel.android.MPConfig.EventsEndpoint");
        if (eventsEndpoint == null) {
            eventsEndpoint = "https://api.mixpanel.com/track?ip=1";
        }
        this.mEventsEndpoint = eventsEndpoint;
        String eventsFallbackEndpoint = metaData.getString("com.mixpanel.android.MPConfig.EventsFallbackEndpoint");
        if (eventsFallbackEndpoint == null) {
            eventsFallbackEndpoint = "http://api.mixpanel.com/track?ip=1";
        }
        this.mEventsFallbackEndpoint = eventsFallbackEndpoint;
        String peopleEndpoint = metaData.getString("com.mixpanel.android.MPConfig.PeopleEndpoint");
        if (peopleEndpoint == null) {
            peopleEndpoint = "https://api.mixpanel.com/engage";
        }
        this.mPeopleEndpoint = peopleEndpoint;
        String peopleFallbackEndpoint = metaData.getString("com.mixpanel.android.MPConfig.PeopleFallbackEndpoint");
        if (peopleFallbackEndpoint == null) {
            peopleFallbackEndpoint = "http://api.mixpanel.com/engage";
        }
        this.mPeopleFallbackEndpoint = peopleFallbackEndpoint;
        String decideEndpoint = metaData.getString("com.mixpanel.android.MPConfig.DecideEndpoint");
        if (decideEndpoint == null) {
            decideEndpoint = "https://decide.mixpanel.com/decide";
        }
        this.mDecideEndpoint = decideEndpoint;
        String decideFallbackEndpoint = metaData.getString("com.mixpanel.android.MPConfig.DecideFallbackEndpoint");
        if (decideFallbackEndpoint == null) {
            decideFallbackEndpoint = "http://decide.mixpanel.com/decide";
        }
        this.mDecideFallbackEndpoint = decideFallbackEndpoint;
        if (DEBUG) {
            Log.d("MixpanelAPI.MPConfig", "Mixpanel configured with:\n    AutoShowMixpanelUpdates " + getAutoShowMixpanelUpdates() + "\n" + "    BulkUploadLimit " + getBulkUploadLimit() + "\n" + "    FlushInterval " + getFlushInterval() + "\n" + "    DataExpiration " + getDataExpiration() + "\n" + "    DisableFallback " + getDisableFallback() + "\n" + "    EnableDebugLogging " + DEBUG + "\n" + "    TestMode " + getTestMode() + "\n" + "    EventsEndpoint " + getEventsEndpoint() + "\n" + "    PeopleEndpoint " + getPeopleEndpoint() + "\n" + "    DecideEndpoint " + getDecideEndpoint() + "\n" + "    EventsFallbackEndpoint " + getEventsFallbackEndpoint() + "\n" + "    PeopleFallbackEndpoint " + getPeopleFallbackEndpoint() + "\n" + "    DecideFallbackEndpoint " + getDecideFallbackEndpoint() + "\n");
        }
    }

    public int getBulkUploadLimit() {
        return this.mBulkUploadLimit;
    }

    public int getFlushInterval() {
        return this.mFlushInterval;
    }

    public int getDataExpiration() {
        return this.mDataExpiration;
    }

    public boolean getDisableFallback() {
        return this.mDisableFallback;
    }

    public boolean getTestMode() {
        return this.mTestMode;
    }

    public String getEventsEndpoint() {
        return this.mEventsEndpoint;
    }

    public String getPeopleEndpoint() {
        return this.mPeopleEndpoint;
    }

    public String getDecideEndpoint() {
        return this.mDecideEndpoint;
    }

    public String getEventsFallbackEndpoint() {
        return this.mEventsFallbackEndpoint;
    }

    public String getPeopleFallbackEndpoint() {
        return this.mPeopleFallbackEndpoint;
    }

    public String getDecideFallbackEndpoint() {
        return this.mDecideFallbackEndpoint;
    }

    public boolean getAutoShowMixpanelUpdates() {
        return this.mAutoShowMixpanelUpdates;
    }

    static MPConfig readConfig(Context appContext) {
        String packageName = appContext.getPackageName();
        try {
            Bundle configBundle = appContext.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            if (configBundle == null) {
                configBundle = new Bundle();
            }
            return new MPConfig(configBundle);
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Can't configure Mixpanel with package name " + packageName, e);
        }
    }
}
