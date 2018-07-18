package com.coinbase.api.internal;

import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.featureflag.FeatureFlags;
import javax.inject.Inject;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ApplicationScope
public class CacheFeatureFlagInitializer implements ApplicationOnCreateListener, ApplicationSignOutListener {
    private static final String REDUCE_API_CALLS = "2017.december.mobile.android.reduce_api_calls";
    private final Scheduler mBackgroundScheduler;
    private final CoinbaseInternal mClient;
    private final FeatureFlags mFeatureFlags;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public CacheFeatureFlagInitializer(FeatureFlags featureFlags, CoinbaseInternal client, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mFeatureFlags = featureFlags;
        this.mClient = client;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mFeatureFlags.get(REDUCE_API_CALLS).observeOn(this.mBackgroundScheduler).subscribe(CacheFeatureFlagInitializer$$Lambda$1.lambdaFactory$(this)));
    }

    public void onApplicationSignOut() {
        this.mClient.setForcedCacheEnabled(false);
    }
}
