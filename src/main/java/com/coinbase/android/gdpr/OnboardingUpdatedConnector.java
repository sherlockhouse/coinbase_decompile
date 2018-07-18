package com.coinbase.android.gdpr;

import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.v2.models.user.OnboardingItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.subjects.BehaviorSubject;

public class OnboardingUpdatedConnector implements ApplicationSignOutListener {
    private BehaviorSubject<List<OnboardingItem>> mOnboardingItemsSubject;
    private BehaviorSubject<Map<String, Object>> mResultsSubject;

    public OnboardingUpdatedConnector() {
        this(BehaviorSubject.create(new ArrayList()), BehaviorSubject.create(new HashMap()));
    }

    OnboardingUpdatedConnector(BehaviorSubject<List<OnboardingItem>> onboardingItemsSubject, BehaviorSubject<Map<String, Object>> resultsSubject) {
        this.mOnboardingItemsSubject = onboardingItemsSubject;
        this.mResultsSubject = resultsSubject;
    }

    public BehaviorSubject<List<OnboardingItem>> getOnboardingItems() {
        return this.mOnboardingItemsSubject;
    }

    public BehaviorSubject<Map<String, Object>> getResults() {
        return this.mResultsSubject;
    }

    public void onApplicationSignOut() {
        this.mOnboardingItemsSubject = BehaviorSubject.create();
        this.mResultsSubject = BehaviorSubject.create(new HashMap());
    }
}
