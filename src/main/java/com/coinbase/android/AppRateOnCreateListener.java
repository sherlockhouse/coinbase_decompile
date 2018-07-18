package com.coinbase.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import javax.inject.Inject;

@ApplicationScope
public class AppRateOnCreateListener implements ApplicationOnCreateListener, ApplicationSignOutListener {
    private static OnClickButtonListener mOnClickListener;
    private final Context mContext;
    private final MixpanelTracking mMixpanelTracking;
    private final SharedPreferences mSharedPrefs;

    enum AppRateButton {
        RATE(-1, "rate"),
        NEVER(-2, "never"),
        LATER(-3, "later"),
        UNKNOWN(0, "unknown");
        
        private final String mName;
        private final int mWhichButton;

        private AppRateButton(int whichButton, String name) {
            this.mWhichButton = whichButton;
            this.mName = name;
        }

        String getName() {
            return this.mName;
        }

        private int getWhichButton() {
            return this.mWhichButton;
        }

        static AppRateButton fromWhichButton(int whichButton) {
            for (AppRateButton appRateButton : values()) {
                if (appRateButton.getWhichButton() == whichButton) {
                    return appRateButton;
                }
            }
            return UNKNOWN;
        }
    }

    @Inject
    public AppRateOnCreateListener(Application context, MixpanelTracking mixpanelTracking, SharedPreferences sharedPrefs) {
        this.mContext = context;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSharedPrefs = sharedPrefs;
        mOnClickListener = AppRateOnCreateListener$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$new$0(AppRateOnCreateListener this_, int which) {
        String whichButtonStr = AppRateButton.fromWhichButton(which).getName();
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_APP_RATE_TAPPED, MixpanelTracking.PROPERTY_APP_RATE_BUTTON_TAP, whichButtonStr);
    }

    public void onCreate() {
        AppRate.with(this.mContext).setInstallDays(0).setLaunchTimes(0).setRemindInterval(0).setShowLaterButton(true).setDebug(false).setMessage(R.string.rate_notice_text).setOnClickButtonListener(mOnClickListener).monitor();
    }

    public void onApplicationSignOut() {
        this.mSharedPrefs.edit().remove(Constants.KEY_RATE_APP_THRESHOLD).apply();
        AppRate.with(this.mContext).clearSettingsParam();
    }
}
