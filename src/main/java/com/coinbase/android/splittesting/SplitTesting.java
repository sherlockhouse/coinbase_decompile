package com.coinbase.android.splittesting;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.SplitTest;
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
public class SplitTesting extends FlavorSplitTesting implements ApplicationOnCreateListener, ApplicationSignOutListener {
    private static final Gson GSON = new Gson();
    static final String KEY_SPLIT_TESTS = "split_tests";
    private final Scheduler mBackgroundScheduler;
    private final Logger mLogger = LoggerFactory.getLogger(SplitTesting.class);
    private final MixpanelTracking mMixpanelTracking;
    private final BehaviorSubject<Void> mSplitTestsInitalized = BehaviorSubject.create();
    final Map<String, BehaviorSubject<SplitTest>> mSubjectMap = new HashMap();
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final Map<String, String> mTrackableTestsMap = new HashMap();
    private final UserUpdatedConnector mUserUpdatedConnector;

    @Inject
    public SplitTesting(SharedPreferences sharedPrefs, UserUpdatedConnector userUpdatedConnector, MixpanelTracking mixpanelTracking, @BackgroundScheduler Scheduler backgroundScheduler) {
        super(sharedPrefs);
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mBackgroundScheduler = backgroundScheduler;
        loadSplitTests();
    }

    public Observable<SplitTest> get(String splitTestName) {
        Observable subject;
        synchronized (this) {
            subject = getSubject(splitTestName, false);
        }
        return subject;
    }

    public boolean isInGroup(String splitTestName, String groupName) {
        return super.isInGroup(splitTestName, groupName);
    }

    public void update(Data user) {
        List splitTests = user.getSplitTestGroups();
        if (splitTests != null && !splitTests.isEmpty()) {
            update(splitTests);
        }
    }

    public Observable<Void> splitTestsInitialized() {
        return this.mSplitTestsInitalized;
    }

    private void update(List<SplitTest> splitTests) {
        synchronized (this) {
            this.mSharedPrefs.edit().putString(KEY_SPLIT_TESTS, GSON.toJson((Object) splitTests)).apply();
            Set<String> originalSubjectKeySet = new HashSet();
            originalSubjectKeySet.addAll(this.mSubjectMap.keySet());
            Set<String> newSubjectKeySet = new HashSet();
            for (SplitTest splitTest : splitTests) {
                BehaviorSubject<SplitTest> splitTestBehaviorSubject = getSubject(splitTest.getTest(), true);
                if (splitTestUpdated(splitTest, (SplitTest) splitTestBehaviorSubject.getValue())) {
                    addTestIfTracked(splitTest);
                    splitTestBehaviorSubject.onNext(splitTest);
                }
                newSubjectKeySet.add(splitTest.getTest());
            }
            originalSubjectKeySet.removeAll(newSubjectKeySet);
            for (String key : originalSubjectKeySet) {
                this.mTrackableTestsMap.remove(key);
                this.mSubjectMap.remove(key);
            }
            synchronized (this) {
                this.mMixpanelTracking.initializeTrackedTests(this.mTrackableTestsMap);
            }
        }
        this.mSplitTestsInitalized.onNext(null);
    }

    private boolean splitTestUpdated(SplitTest updatedTest, SplitTest originalSplitTest) {
        if (updatedTest == null || originalSplitTest == null) {
            this.mLogger.error("Updated test or original split test is null, should never happen.");
            return false;
        } else if (updatedTest.getIsTracked() == null) {
            return false;
        } else {
            if (TextUtils.equals(updatedTest.getGroup(), originalSplitTest.getGroup()) && TextUtils.equals(updatedTest.getTest(), originalSplitTest.getTest()) && originalSplitTest.getIsTracked() != null && updatedTest.getIsTracked() == originalSplitTest.getIsTracked()) {
                return false;
            }
            return true;
        }
    }

    private void loadSplitTests() {
        try {
            if (this.mSharedPrefs.contains(KEY_SPLIT_TESTS)) {
                for (SplitTest splitTest : (Collection) GSON.fromJson(this.mSharedPrefs.getString(KEY_SPLIT_TESTS, ""), new TypeToken<Collection<SplitTest>>() {
                }.getType())) {
                    addTestIfTracked(splitTest);
                    initializeSubject(splitTest.getTest(), BehaviorSubject.create(splitTest));
                }
                this.mMixpanelTracking.initializeTrackedTests(this.mTrackableTestsMap);
                this.mSplitTestsInitalized.onNext(null);
            }
        } catch (Exception e) {
            this.mLogger.error("Error loading split tests", e);
        }
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mUserUpdatedConnector.get().filter(SplitTesting$$Lambda$1.lambdaFactory$()).map(SplitTesting$$Lambda$2.lambdaFactory$()).observeOn(this.mBackgroundScheduler).subscribe(SplitTesting$$Lambda$3.lambdaFactory$(this), SplitTesting$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onCreate$0(Data user) {
        boolean z = (user == null || user.getSplitTestGroups() == null || user.getSplitTestGroups().isEmpty()) ? false : true;
        return Boolean.valueOf(z);
    }

    public void onApplicationSignOut() {
        for (String key : this.mSubjectMap.keySet()) {
            ((BehaviorSubject) this.mSubjectMap.get(key)).onNext(new SplitTest());
        }
        this.mTrackableTestsMap.clear();
        this.mSharedPrefs.edit().remove(KEY_SPLIT_TESTS).apply();
    }

    private void addTestIfTracked(SplitTest splitTest) {
        if (splitTest.getIsTracked() != null && splitTest.getIsTracked().booleanValue() && !TextUtils.isEmpty(splitTest.getTest()) && !TextUtils.isEmpty(splitTest.getGroup())) {
            this.mTrackableTestsMap.put(splitTest.getTest(), splitTest.getGroup());
        }
    }

    public SplitTest getValue(String splitTestName) {
        SplitTest splitTest;
        synchronized (this) {
            splitTest = (SplitTest) getSubject(splitTestName, false).getValue();
        }
        return splitTest;
    }

    BehaviorSubject<SplitTest> getSubject(String splitTestName, boolean update) {
        if (!this.mSubjectMap.containsKey(splitTestName)) {
            initializeSubject(splitTestName, BehaviorSubject.create(new SplitTest()));
        }
        BehaviorSubject<SplitTest> splitTestBehaviorSubject = (BehaviorSubject) this.mSubjectMap.get(splitTestName);
        if (!update) {
            this.mMixpanelTracking.trackExposureEventIfNeeded((SplitTest) splitTestBehaviorSubject.getValue());
        }
        return splitTestBehaviorSubject;
    }

    BehaviorSubject<SplitTest> initializeSubject(String splitTestName, BehaviorSubject<SplitTest> splitTestBehaviorSubject) {
        this.mSubjectMap.put(splitTestName, splitTestBehaviorSubject);
        return splitTestBehaviorSubject;
    }
}
