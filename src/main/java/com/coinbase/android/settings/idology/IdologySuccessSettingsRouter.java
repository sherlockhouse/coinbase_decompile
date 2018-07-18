package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

@ControllerScope
public class IdologySuccessSettingsRouter implements IdologySuccessRouter {
    private final ActionBarController mController;
    private final IdologyArgsBuilder mIdologyArgsBuilder;
    private final Scheduler mMainScheduler;

    @Inject
    public IdologySuccessSettingsRouter(ActionBarController controller, @MainScheduler Scheduler scheduler, IdologyArgsBuilder idologyArgsBuilder) {
        this.mController = controller;
        this.mMainScheduler = scheduler;
        this.mIdologyArgsBuilder = idologyArgsBuilder;
    }

    public Subscription routeToSuccessPage() {
        return Observable.just(null).observeOn(this.mMainScheduler).first().onBackpressureLatest().subscribe(IdologySuccessSettingsRouter$$Lambda$1.lambdaFactory$(this));
    }
}
