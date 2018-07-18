package com.coinbase.android.ui;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class MainControllerPresenter {
    private final BackNavigationConnector mBackNavigationConnector;
    private final BottomNavigationConnector mBottomNavigationConnector;
    private final KeyboardListener mKeyboardListener;
    private final Logger mLogger = LoggerFactory.getLogger(MainControllerPresenter.class);
    private final Scheduler mMainScheduler;
    private final MainScreen mMainScreen;
    private final CompositeSubscription mModalSubscription = new CompositeSubscription();
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public MainControllerPresenter(MainScreen mainScreen, BottomNavigationConnector bottomNavigationConnector, BackNavigationConnector backNavigationConnector, KeyboardListener keyboardListener, @MainScheduler Scheduler mainScheduler) {
        this.mMainScreen = mainScreen;
        this.mBottomNavigationConnector = bottomNavigationConnector;
        this.mBackNavigationConnector = backNavigationConnector;
        this.mKeyboardListener = keyboardListener;
        this.mMainScheduler = mainScheduler;
    }

    public void onCreateView() {
        this.mSubscription.add(this.mKeyboardListener.isKeyboardVisible().subscribeOn(this.mMainScheduler).subscribe(MainControllerPresenter$$Lambda$1.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onCreateView$0(MainControllerPresenter this_, Boolean isKeyboardVisible) {
        if (isKeyboardVisible.booleanValue()) {
            this_.mMainScreen.hideBottomNavigation();
        } else {
            this_.mMainScreen.showBottomNavigation();
        }
    }

    public void onAttach() {
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().distinctUntilChanged().observeOn(this.mMainScheduler).map(MainControllerPresenter$$Lambda$2.lambdaFactory$()).subscribe(MainControllerPresenter$$Lambda$3.lambdaFactory$(this)));
        subscribeModalController();
    }

    public void onDetach() {
        this.mSubscription.clear();
        this.mModalSubscription.clear();
    }

    public void onActivityPaused() {
        this.mBackNavigationConnector.clearBackSubscription();
        this.mModalSubscription.clear();
    }

    public void onActivityResumed() {
        subscribeModalController();
    }

    private void subscribeModalController() {
        this.mModalSubscription.clear();
        this.mModalSubscription.add(this.mBottomNavigationConnector.getModal().onBackpressureLatest().observeOn(this.mMainScheduler).filter(MainControllerPresenter$$Lambda$4.lambdaFactory$()).subscribe(MainControllerPresenter$$Lambda$5.lambdaFactory$(this), MainControllerPresenter$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$subscribeModalController$3(ModalDestination destination) {
        return Boolean.valueOf(!destination.getConsumed().booleanValue());
    }

    static /* synthetic */ void lambda$subscribeModalController$4(MainControllerPresenter this_, ModalDestination destination) {
        this_.switchToModal(destination);
        destination.getConsumed().setValue(true);
    }

    public void switchToModal(ModalDestination modalDestination) {
        PageDestination previousDestination = (PageDestination) this.mBottomNavigationConnector.get().getValue();
        if (previousDestination == null) {
            throw new IllegalStateException("Last type from behavior subject should never be null");
        }
        this.mMainScreen.switchToModal(previousDestination.getBottomNavigationItem(), modalDestination);
        if (modalDestination.getType() == Type.NEW_BUY) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_BUY_SIDEBAR_CLICK, new String[0]);
        }
    }
}
