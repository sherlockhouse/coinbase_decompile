package com.coinbase.android.modalAlerts;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.MainScheduler;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;

@ControllerScope
public class ModalRouterAggregator {
    public static final String INITIATING_AUTH_SCREEN = "initiating_auth_screen";
    private boolean mHandledRouting = false;
    private final Logger mLogger = LoggerFactory.getLogger(ModalRouterAggregator.class);
    private final Scheduler mMainScheduler;
    private final List<ModalRouter> mModalRouters = new LinkedList();

    @Inject
    public ModalRouterAggregator(List<ModalRouter> modalRouters, @MainScheduler Scheduler mainScheduler) {
        this.mModalRouters.addAll(modalRouters);
        this.mMainScheduler = mainScheduler;
    }

    public Subscription route() {
        this.mHandledRouting = false;
        Iterable modalRouterActions = new LinkedList();
        for (ModalRouter modalRouter : this.mModalRouters) {
            modalRouterActions.add(modalRouter.route());
        }
        return Observable.concat(modalRouterActions).observeOn(this.mMainScheduler).onBackpressureLatest().subscribe(ModalRouterAggregator$$Lambda$1.lambdaFactory$(this), ModalRouterAggregator$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$route$0(ModalRouterAggregator this_, Action0 action) {
        if (action != null && !this_.mHandledRouting) {
            this_.mHandledRouting = true;
            action.call();
        }
    }
}
