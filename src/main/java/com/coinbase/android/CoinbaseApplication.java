package com.coinbase.android;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.pin.PINManager.AccessType;
import com.coinbase.android.pin.PINPromptActivity;
import com.coinbase.android.settings.PreferenceUpgrade;
import com.coinbase.android.signin.IntroActivity;
import com.coinbase.android.signin.LaunchMessageActivity;
import com.coinbase.android.utils.PreferenceUtils;
import com.coinbase.android.utils.RefWatcherWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.CoinbaseInternal;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.google.firebase.FirebaseApp;
import com.tenjin.android.TenjinSDK;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import rx_activity_result.RxActivityResult;

@ApplicationScope
public class CoinbaseApplication extends MultiDexApplication implements ComponentProvider {
    private static int started;
    private static int stopped;
    @Inject
    protected Set<ApplicationOnCreateListener> mApplicationOnCreateListeners;
    protected CoinbaseApplicationComponent mComponent;
    private List<MainActivity> mMainActivities = new ArrayList();
    @Inject
    protected PINManager mPINManager;
    @Inject
    protected RefWatcherWrapper mRefWatcherWrapper;

    public class MyLifecycleHandler implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
        private final Application mApplication;

        MyLifecycleHandler(Application application) {
            this.mApplication = application;
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (CoinbaseApplication.this.mPINManager.isProtected(this.mApplication, AccessType.APP_OPEN) && !CoinbaseApplication.this.mPINManager.shouldGrantAccess(this.mApplication) && !(activity instanceof IntroActivity) && !(activity instanceof PINPromptActivity) && !(activity instanceof LaunchMessageActivity)) {
                Utils.showJumioFlow(activity, true);
                Intent intent = new Intent(activity, PINPromptActivity.class);
                intent.setAction(PINPromptActivity.ACTION_PROMPT);
                activity.startActivity(intent);
            }
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityStarted(Activity activity) {
            CoinbaseApplication.access$004();
        }

        public void onActivityStopped(Activity activity) {
            CoinbaseApplication.access$104();
            if (!CoinbaseApplication.isApplicationVisible()) {
                CoinbaseApplication.this.mPINManager.setPinEntered(this.mApplication, false);
                PreferenceManager.getDefaultSharedPreferences(activity).edit().putBoolean(Constants.SERVER_OUTAGE_MESSAGE_SHOWN, false).apply();
            }
        }

        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onLowMemory() {
        }

        public void onTrimMemory(int level) {
            if (level == 20) {
                CoinbaseApplication.this.mPINManager.setPinEntered(this.mApplication, false);
            }
        }
    }

    static /* synthetic */ int access$004() {
        int i = started + 1;
        started = i;
        return i;
    }

    static /* synthetic */ int access$104() {
        int i = stopped + 1;
        stopped = i;
        return i;
    }

    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public CoinbaseApplicationComponent applicationComponent() {
        return this.mComponent;
    }

    public void setComponent(CoinbaseApplicationComponent component) {
        this.mComponent = component;
    }

    public void onCreate() {
        startOnCreate();
        super.onCreate();
        initializeThirdPartyLibraries();
        initializeComponents();
        finishOnCreate();
    }

    protected void startOnCreate() {
        CoinbaseInternal.getInstance().init(this, Constants.CLIENT_ID, Constants.CLIENT_SECRET);
        MixpanelTracking.getInstance().initializeMixpanel(this);
    }

    protected void initializeThirdPartyLibraries() {
        Fabric.with(this, new Crashlytics(), new Answers());
        FirebaseApp.initializeApp(this);
        TenjinSDK.getInstance(this, Constants.TENJIN_API_KEY).connect();
    }

    protected void initializeComponents() {
        this.mComponent = DaggerMainCoinbaseApplicationComponent.builder().coinbaseApplicationModule(new CoinbaseApplicationModule(this)).build();
        this.mComponent.inject(this);
    }

    protected void finishOnCreate() {
        new BuglifeWrapper(this).onApplicationCreate();
        this.mRefWatcherWrapper.install();
        PreferenceUpgrade.perform(this);
        PreferenceUtils.incrementPrefsInt(this, Constants.KEY_ACCOUNT_APP_USAGE);
        this.mPINManager.setPinEntered(this, false);
        MyLifecycleHandler handler = new MyLifecycleHandler(this);
        registerActivityLifecycleCallbacks(handler);
        registerComponentCallbacks(handler);
        RxActivityResult.register(this);
        callApplicationOnCreateListeners();
    }

    protected void callApplicationOnCreateListeners() {
        for (ApplicationOnCreateListener listener : this.mApplicationOnCreateListeners) {
            listener.onCreate();
        }
    }

    public void removeMainActivity(MainActivity mainActivity) {
        this.mMainActivities.remove(mainActivity);
    }
}
