package com.coinbase.android.dashboard;

import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.ApplicationSignOutListener;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.price.Price;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@ApplicationScope
public final class SpotPriceUpdatedConnector implements ApplicationSignOutListener {
    private final LoginManager mLoginManager;
    private final Map<String, BehaviorSubject<Pair<Response<Price>, Retrofit>>> mSubjectMap;

    @Inject
    public SpotPriceUpdatedConnector(LoginManager loginManager) {
        this(new HashMap(), loginManager);
    }

    public SpotPriceUpdatedConnector(Map<String, BehaviorSubject<Pair<Response<Price>, Retrofit>>> subjectMap, LoginManager loginManager) {
        this.mSubjectMap = subjectMap;
        this.mLoginManager = loginManager;
    }

    public BehaviorSubject<Pair<Response<Price>, Retrofit>> fetch(String currencyCode, String currencyUnitCode) {
        BehaviorSubject<Pair<Response<Price>, Retrofit>> subject;
        Observable<Pair<Response<Price>, Retrofit>> getSpotPriceObservable = this.mLoginManager.getClient().getSpotPriceRx(currencyCode, currencyUnitCode, new HashMap());
        synchronized (this) {
            subject = get(currencyCode, currencyUnitCode);
        }
        getSpotPriceObservable.first().subscribe(SpotPriceUpdatedConnector$$Lambda$1.lambdaFactory$(this, currencyCode, currencyUnitCode, subject), SpotPriceUpdatedConnector$$Lambda$2.lambdaFactory$(this, subject, currencyCode, currencyUnitCode));
        return subject;
    }

    static /* synthetic */ void lambda$fetch$0(SpotPriceUpdatedConnector this_, String currencyCode, String currencyUnitCode, BehaviorSubject subject, Pair pair) {
        if (pair.first.isSuccessful()) {
            subject.onNext(pair);
            return;
        }
        this_.clear(currencyCode, currencyUnitCode);
        subject.onNext(pair);
    }

    static /* synthetic */ void lambda$fetch$1(SpotPriceUpdatedConnector this_, BehaviorSubject subject, String currencyCode, String currencyUnitCode, Throwable t) {
        subject.onError(t);
        this_.clear(currencyCode, currencyUnitCode);
    }

    public String getKey(String currencyCode, String currencyUnitCode) {
        if (TextUtils.isEmpty(currencyCode) || TextUtils.isEmpty(currencyUnitCode)) {
            return null;
        }
        return currencyCode.toLowerCase() + "-" + currencyUnitCode.toLowerCase();
    }

    public BehaviorSubject<Pair<Response<Price>, Retrofit>> get(String currencyCode, String currencyUnitCode) {
        BehaviorSubject<Pair<Response<Price>, Retrofit>> create;
        synchronized (this) {
            String key = getKey(currencyCode, currencyUnitCode);
            if (TextUtils.isEmpty(key)) {
                create = BehaviorSubject.create();
            } else {
                if (!this.mSubjectMap.containsKey(key)) {
                    this.mSubjectMap.put(key, BehaviorSubject.create());
                }
                create = (BehaviorSubject) this.mSubjectMap.get(key);
            }
        }
        return create;
    }

    public void onApplicationSignOut() {
        synchronized (this) {
            this.mSubjectMap.clear();
        }
    }

    private void clear(String currencyCode, String currencyUnitCode) {
        synchronized (this) {
            String key = getKey(currencyCode, currencyUnitCode);
            if (TextUtils.isEmpty(key)) {
                this.mSubjectMap.clear();
            }
            this.mSubjectMap.remove(key);
        }
    }
}
