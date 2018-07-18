package com.coinbase.android.idology;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.api.LoginManager;
import java.util.Locale;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ApplicationScope
public class IdologyUtils implements ApplicationOnCreateListener {
    private static final String ENABLE_IDOLOGY_FEATURE_FLAG = "2018.january.mobile.android.idology";
    private static final String KEY_IS_IDOLOGY_ENABLED = "is_idology_enabled";
    private static final String KEY_IS_IDOLOGY_VERIFIED = "is_idology_verified";
    private final Scheduler mBackgroundScheduler;
    private final FeatureFlags mFeatureFlags;
    private final Logger mLogger = LoggerFactory.getLogger(IdologyUtils.class);
    private final LoginManager mLoginManager;
    private final SharedPreferences mSharedPrefs;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologyUtils(LoginManager loginManager, SharedPreferences sharedPrefs, FeatureFlags featureFlags, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mSharedPrefs = sharedPrefs;
        this.mFeatureFlags = featureFlags;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mFeatureFlags.get(ENABLE_IDOLOGY_FEATURE_FLAG).observeOn(this.mBackgroundScheduler).subscribe(IdologyUtils$$Lambda$1.lambdaFactory$(this), IdologyUtils$$Lambda$2.lambdaFactory$(this)));
    }

    public boolean showIdology() {
        return isIdologyEnabled() && isUSUser() && !isIdologyVerified();
    }

    public void updateIdologyVerificationResult(boolean result) {
        updateSharedPrefs(KEY_IS_IDOLOGY_VERIFIED, result);
    }

    public void updateIdologyEnabledFlag(boolean isEnabled) {
        updateSharedPrefs(KEY_IS_IDOLOGY_ENABLED, isEnabled);
    }

    public void clear() {
        this.mSharedPrefs.edit().remove(KEY_IS_IDOLOGY_VERIFIED).apply();
        this.mSharedPrefs.edit().remove(KEY_IS_IDOLOGY_ENABLED).apply();
    }

    private void updateSharedPrefs(String key, boolean value) {
        Editor e = this.mSharedPrefs.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    private boolean isIdologyEnabled() {
        return this.mSharedPrefs.getBoolean(KEY_IS_IDOLOGY_ENABLED, false);
    }

    private boolean isIdologyVerified() {
        return this.mSharedPrefs.getBoolean(KEY_IS_IDOLOGY_VERIFIED, false);
    }

    private boolean isUSUser() {
        return TextUtils.equals(this.mLoginManager.getActiveUserCountryCode(), Locale.US.getCountry());
    }
}
