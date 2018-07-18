package com.coinbase.android;

import com.coinbase.android.ui.SignOutConnector;
import java.util.Set;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

public class SignOutOnCreateListener implements ApplicationOnCreateListener {
    private final Set<ApplicationSignOutListener> mApplicationSignOutListenerSet;
    private final Scheduler mScheduler;
    private final SignOutConnector mSignOutConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    public SignOutOnCreateListener(SignOutConnector signOutConnector, Set<ApplicationSignOutListener> applicationSignOutListenerSet, Scheduler scheduler) {
        this.mSignOutConnector = signOutConnector;
        this.mApplicationSignOutListenerSet = applicationSignOutListenerSet;
        this.mScheduler = scheduler;
    }

    public void onCreate() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mSignOutConnector.get().observeOn(this.mScheduler).subscribe(SignOutOnCreateListener$$Lambda$1.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onCreate$0(SignOutOnCreateListener this_, Void v) {
        for (ApplicationSignOutListener listener : this_.mApplicationSignOutListenerSet) {
            listener.onApplicationSignOut();
        }
    }
}
