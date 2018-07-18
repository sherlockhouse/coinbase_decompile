package com.coinbase.android.gdpr;

import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.v2.models.user.Data;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ApplicationScope
public class GdprInitializer implements ApplicationOnCreateListener {
    private static final String SKIP_GDPR_FEATURE_FLAG = "2018.april.android.skip_gdpr";
    private final Scheduler mBackgroundScheduler;
    private final FeatureFlags mFeatureFlags;
    private final OnboardingUpdatedConnector mOnboardingUpdatedConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final UserUpdatedConnector mUserUpdatedConnector;

    @Inject
    public GdprInitializer(FeatureFlags featureFlags, UserUpdatedConnector userUpdatedConnector, OnboardingUpdatedConnector onboardingUpdatedConnector, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mFeatureFlags = featureFlags;
        this.mUserUpdatedConnector = userUpdatedConnector;
        this.mOnboardingUpdatedConnector = onboardingUpdatedConnector;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(Observable.combineLatest(this.mUserUpdatedConnector.get(), this.mFeatureFlags.get(SKIP_GDPR_FEATURE_FLAG), GdprInitializer$$Lambda$1.lambdaFactory$()).subscribeOn(this.mBackgroundScheduler).subscribe(GdprInitializer$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ Data lambda$onCreate$0(Data userData, Boolean shouldSkipGdprFlow) {
        if (shouldSkipGdprFlow.booleanValue()) {
            return null;
        }
        return userData;
    }

    static /* synthetic */ void lambda$onCreate$1(GdprInitializer this_, Data userData) {
        if (userData == null || userData.getOnboardingItems() == null || userData.getOnboardingItems().isEmpty()) {
            this_.mOnboardingUpdatedConnector.getOnboardingItems().onNext(null);
        } else {
            this_.mOnboardingUpdatedConnector.getOnboardingItems().onNext(userData.getOnboardingItems());
        }
    }
}
