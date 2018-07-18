package com.coinbase.android.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.modalAlerts.ModalRouter;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action0;

@ControllerScope
public class GdprModalRouter implements ModalRouter {
    private final OnboardingRouter mOnboardingRouter;
    private final OnboardingUpdatedConnector mOnboardingUpdatedConnector;

    @Inject
    public GdprModalRouter(OnboardingUpdatedConnector onboardingUpdatedConnector, OnboardingRouter onboardingRouter) {
        this.mOnboardingUpdatedConnector = onboardingUpdatedConnector;
        this.mOnboardingRouter = onboardingRouter;
    }

    public Observable<Action0> route() {
        return Observable.just(this.mOnboardingUpdatedConnector.getOnboardingItems().getValue()).map(GdprModalRouter$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ Action0 lambda$route$1(GdprModalRouter this_, List onboardingItems) {
        if (onboardingItems == null || onboardingItems.isEmpty() || onboardingItems.get(0) == null) {
            return null;
        }
        return GdprModalRouter$$Lambda$2.lambdaFactory$(this_, onboardingItems);
    }
}
