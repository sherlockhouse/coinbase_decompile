package com.coinbase.android.featureflag;

import android.content.SharedPreferences;
import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.v2.models.user.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

@ApplicationScope
public class FeatureFlags implements ApplicationOnCreateListener, ApplicationSignOutListener {
    public static final String FF_SHORTCUT_TO_PLAID_SCREEN = "2018.april.mobile.android.shortcut_to_plaid_screen";
    public static final String FF_WORLDPAY = "tEsTdIsAbLeD";
    private static final Gson GSON = new Gson();
    static final String KEY_FEATURE_FLAGS = "feature_flags";
    private final Scheduler mBackgroundScheduler;
    private final Logger mLogger = LoggerFactory.getLogger(FeatureFlags.class);
    private final SharedPreferences mSharedPrefs;
    private final Map<String, BehaviorSubject<Boolean>> mSubjectMap = new HashMap();
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final UserUpdatedConnector mUserUpdatedConnector;

    @Inject
    public FeatureFlags(SharedPreferences sharedPrefs, UserUpdatedConnector userUpdatedConnector, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mSharedPrefs = sharedPrefs;
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mBackgroundScheduler = backgroundScheduler;
        loadFeatureFlags();
    }

    private void loadFeatureFlags() {
        try {
            if (this.mSharedPrefs.contains(KEY_FEATURE_FLAGS)) {
                for (String featureFlag : (Collection) GSON.fromJson(this.mSharedPrefs.getString(KEY_FEATURE_FLAGS, ""), new TypeToken<Collection<String>>() {
                }.getType())) {
                    initializeSubject(featureFlag, BehaviorSubject.create(Boolean.valueOf(true)));
                }
            }
        } catch (Exception e) {
            this.mLogger.error("Error loading feature flags", e);
        }
    }

    BehaviorSubject<Boolean> getSubject(String featureFlagName) {
        if (this.mSubjectMap.containsKey(featureFlagName)) {
            return (BehaviorSubject) this.mSubjectMap.get(featureFlagName);
        }
        return initializeSubject(featureFlagName, BehaviorSubject.create(Boolean.valueOf(false)));
    }

    public Observable<Boolean> get(String featureFlagName) {
        Observable subject;
        synchronized (this) {
            subject = getSubject(featureFlagName);
        }
        return subject;
    }

    public boolean hasFeature(String featureFlagName) {
        boolean booleanValue;
        synchronized (this) {
            booleanValue = ((Boolean) getSubject(featureFlagName).getValue()).booleanValue();
        }
        return booleanValue;
    }

    private BehaviorSubject<Boolean> initializeSubject(String featureFlagName, BehaviorSubject<Boolean> featureFlagBehaviorSubject) {
        this.mSubjectMap.put(featureFlagName, featureFlagBehaviorSubject);
        return featureFlagBehaviorSubject;
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mUserUpdatedConnector.get().filter(FeatureFlags$$Lambda$1.lambdaFactory$()).map(FeatureFlags$$Lambda$2.lambdaFactory$()).observeOn(this.mBackgroundScheduler).subscribe(FeatureFlags$$Lambda$3.lambdaFactory$(this), FeatureFlags$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onCreate$0(Data user) {
        boolean z = (user == null || user.getFeatureFlags() == null || user.getFeatureFlags().isEmpty()) ? false : true;
        return Boolean.valueOf(z);
    }

    private void update(List<String> featureFlagList) {
        synchronized (this) {
            this.mSharedPrefs.edit().putString(KEY_FEATURE_FLAGS, GSON.toJson((Object) featureFlagList)).apply();
            Set<String> originalSubjectKeySet = new HashSet();
            originalSubjectKeySet.addAll(this.mSubjectMap.keySet());
            Set<String> newSubjectKeySet = new HashSet();
            for (String featureFlagName : featureFlagList) {
                getSubject(featureFlagName).onNext(Boolean.valueOf(true));
                newSubjectKeySet.add(featureFlagName);
            }
            originalSubjectKeySet.removeAll(newSubjectKeySet);
            for (String key : originalSubjectKeySet) {
                BehaviorSubject<Boolean> removedFeatureFlag = (BehaviorSubject) this.mSubjectMap.get(key);
                if (removedFeatureFlag != null) {
                    removedFeatureFlag.onNext(Boolean.valueOf(false));
                }
            }
        }
    }

    public void onApplicationSignOut() {
        for (String key : this.mSubjectMap.keySet()) {
            ((BehaviorSubject) this.mSubjectMap.get(key)).onNext(Boolean.valueOf(false));
        }
        this.mSharedPrefs.edit().remove(KEY_FEATURE_FLAGS).apply();
    }
}
