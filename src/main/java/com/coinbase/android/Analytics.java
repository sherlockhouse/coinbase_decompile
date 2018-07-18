package com.coinbase.android;

import android.app.Application;
import android.content.Context;
import com.tenjin.android.TenjinSDK;
import javax.inject.Inject;

@ApplicationScope
public class Analytics {
    private final Context mContext;

    @Inject
    public Analytics(Application application) {
        this.mContext = application;
    }

    public void trackAttributionEvent(String event) {
        TenjinSDK.getInstance(this.mContext, Constants.TENJIN_API_KEY).eventWithName(event);
    }
}
