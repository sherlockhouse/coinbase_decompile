package com.coinbase.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.ScreenProtector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class PageControllerLifeCycle extends ControllerLifeCycle {
    public static final String TYPE = "parent_page_controller_type";
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    private boolean mCalledOnPageHide = false;
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(PageControllerLifeCycle.class);
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    private final CompositeSubscription mMenuSubscription = new CompositeSubscription();
    @Inject
    @ModalNavItem
    Func0<ViewGroup> mModalView;
    private int mPageDepth;
    @Inject
    ScreenProtector mScreenProtector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    Type mType;
    @Inject
    Window mWindow;

    public static abstract class PageDestination {

        public static abstract class Builder {
            public abstract PageDestination build();

            public abstract Builder setBottomNavigationItem(Type type);

            public abstract Builder setPushPageController(ActionBarController actionBarController);

            public abstract Builder setShouldReplaceController(boolean z);

            public abstract Builder setSourceBottomNavigationItem(Type type);
        }

        public abstract Type getBottomNavigationItem();

        public abstract ActionBarController getPushPageController();

        public abstract boolean getShouldReplaceController();

        public abstract Type getSourceBottomNavigationItem();

        public static Builder builder() {
            return new Builder().setShouldReplaceController(false);
        }
    }

    protected PageControllerLifeCycle(ActionBarController controller) {
        Bundle args = controller.getArgs();
        if (args.containsKey(TYPE)) {
            this.mType = Type.fromValue(args.getInt(TYPE, Type.DASHBOARD.getValue()));
        }
        this.mController = controller;
    }

    protected void onPageShow() {
        this.mCalledOnPageHide = false;
        this.mController.onShow();
        this.mController.updateActionBar();
        this.mController.setHasOptionsMenu(true);
        this.mController.hideSoftKeyboard();
    }

    protected void onPageHide() {
        if (!this.mCalledOnPageHide) {
            this.mCalledOnPageHide = true;
            this.mController.onHide();
            this.mMenuSubscription.clear();
            this.mController.setAnimateStatusBar(false);
        }
    }

    protected void onPagePrepareOptionsMenu(Menu menu) {
        this.mCalledOnPageHide = false;
        menu.clear();
        this.mController.onShowOptionsMenu(menu);
    }

    public Type getType() {
        if (this.mType != null) {
            return this.mType;
        }
        if (this.mController.getRouter() == null) {
            return Type.fromValue(this.mController.getArgs().getInt(TYPE, Type.DASHBOARD.getValue()));
        }
        if (this.mController.getRouter().getBackstackSize() < 2) {
            return Type.fromValue(this.mController.getArgs().getInt(TYPE, Type.DASHBOARD.getValue()));
        }
        Controller controller = ((RouterTransaction) this.mController.getRouter().getBackstack().get(0)).controller();
        if (!((controller instanceof ActionBarController) && controller.getArgs().containsKey(TYPE))) {
            controller = this.mController;
        }
        return Type.fromValue(controller.getArgs().getInt(TYPE, Type.DASHBOARD.getValue()));
    }

    public void onAttach(View view) {
        this.mType = getType();
        this.mPageDepth = this.mController.getRouter().getBackstackSize();
        this.mController.setAnimateStatusBar(true);
        this.mSubscription.clear();
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().distinctUntilChanged().map(PageControllerLifeCycle$$Lambda$1.lambdaFactory$(this)).subscribeOn(Schedulers.immediate()).observeOn(this.mMainScheduler).filter(PageControllerLifeCycle$$Lambda$2.lambdaFactory$(this)).subscribe(PageControllerLifeCycle$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).distinctUntilChanged().filter(PageControllerLifeCycle$$Lambda$4.lambdaFactory$(this)).subscribe(PageControllerLifeCycle$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).distinctUntilChanged().filter(PageControllerLifeCycle$$Lambda$6.lambdaFactory$(this)).subscribe(PageControllerLifeCycle$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ PageDestination lambda$onAttach$0(PageControllerLifeCycle this_, PageDestination destination) {
        if (this_.preFilterThisPageShow(destination)) {
            this_.mScreenProtector.conditionallyProtect(this_.mController.getClass());
        }
        return destination;
    }

    static /* synthetic */ void lambda$onAttach$2(PageControllerLifeCycle this_, PageDestination destination) {
        this_.mScreenProtector.conditionallyProtect(this_.mController.getClass());
        this_.onPageShow();
    }

    public void onDetach(View view) {
        handleDetachOrPop();
    }

    void onChangeStartHide() {
        handleDetachOrPop();
    }

    void onChangeEndShow() {
    }

    private void handleDetachOrPop() {
        if (!this.mCalledOnPageHide) {
            this.mSubscription.clear();
            onPageHide();
        }
    }

    public void onCreate() {
        ((MainActivitySubcomponentProvider) this.mController.getActivity()).mainActivitySubcomponent().inject(this);
        this.mCalledOnPageHide = false;
    }

    public void onDestroy() {
        this.mCalledOnPageHide = false;
        this.mSubscription.clear();
        this.mMenuSubscription.clear();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (menu == null) {
            this.mLogger.error("Menu is null in actionbar controller onPrepareOptionsMenu, returning");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        this.mMenuSubscription.clear();
        this.mMenuSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).distinctUntilChanged().filter(PageControllerLifeCycle$$Lambda$8.lambdaFactory$(this)).subscribe(PageControllerLifeCycle$$Lambda$9.lambdaFactory$(this, menu)));
    }

    boolean onOptionsItemSelected(MenuItem item) {
        return filterThisPageShow((PageDestination) this.mBottomNavigationConnector.get().getValue()) && this.mController.onShownOptionsItemSelected(item);
    }

    void onActivityPaused(Activity activity) {
        if (filterThisPageHide((PageDestination) this.mBottomNavigationConnector.get().getValue()) && this.mController != null) {
            this.mController.disableBackSubscription();
        }
    }

    void onActivityResumed(Activity activity) {
        this.mCalledOnPageHide = false;
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).distinctUntilChanged().filter(PageControllerLifeCycle$$Lambda$10.lambdaFactory$(this)).subscribe(PageControllerLifeCycle$$Lambda$11.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onActivityResumed$9(PageControllerLifeCycle this_, PageDestination destination) {
        boolean z = this_.filterThisPageShow(destination) && this_.mController != null;
        return Boolean.valueOf(z);
    }

    public int getUpIndicator() {
        return -1;
    }

    void replayOnShow() {
        onPageShow();
        onPrepareOptionsMenu(this.mMenu);
    }

    void replayOnHide() {
        onPageHide();
    }

    private void pushPage(PageDestination destination) {
        ActionBarController pageController = destination.getPushPageController();
        Type sourceItem = destination.getSourceBottomNavigationItem();
        Type currentItem = destination.getBottomNavigationItem();
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(currentItem).setBottomNavigationItem(currentItem).build());
        if (destination.getShouldReplaceController()) {
            pageController.getArgs().putInt(TYPE, currentItem.getValue());
        }
        RouterTransaction transaction = RouterTransaction.with(pageController).tag(pageController.getTag());
        transaction.popChangeHandler(this.mController.mChildTransitionBehavior.popChangeHandler());
        if (sourceItem == this.mType) {
            transaction.pushChangeHandler(this.mController.mChildTransitionBehavior.pushChangeHandler());
        } else if (this.mController.getRouter().getBackstackSize() > 1) {
            this.mController.getRouter().popToRoot(new SimpleSwapChangeHandler());
        }
        if (destination.getShouldReplaceController()) {
            this.mController.getRouter().replaceTopController(transaction);
            return;
        }
        Router controllerRouter = this.mController.getRouter();
        if (controllerRouter.getBackstackSize() <= 0 || controllerRouter.getControllerWithTag(pageController.getTag()) == null) {
            controllerRouter.pushController(transaction);
            return;
        }
        controllerRouter.popToTag(pageController.getTag());
        controllerRouter.replaceTopController(transaction);
    }

    private boolean filterThisPageShow(PageDestination destination) {
        return preFilterThisPageShow(destination) && areWeTopOfStack();
    }

    private boolean preFilterThisPageShow(PageDestination destination) {
        if (destination.getBottomNavigationItem() != this.mType || destination.getPushPageController() != null) {
            return false;
        }
        if (this.mModalView == null || this.mModalView.call() == null || this.mController.getRouter().getBackstackSize() <= 0) {
            return true;
        }
        return ((RouterTransaction) this.mController.getRouter().getBackstack().get(this.mController.getRouter().getBackstackSize() - 1)).controller().getChildRouter((ViewGroup) this.mModalView.call()).getBackstack().isEmpty();
    }

    private boolean areWeTopOfStack() {
        return this.mPageDepth == this.mController.getRouter().getBackstackSize();
    }

    private boolean filterThisPagePush(PageDestination destination) {
        return destination.getBottomNavigationItem() == this.mType && destination.getPushPageController() != null && this.mPageDepth == this.mController.getRouter().getBackstackSize();
    }

    private boolean filterThisPageHide(PageDestination destination) {
        return destination.getBottomNavigationItem() != this.mType && this.mPageDepth == this.mController.getRouter().getBackstackSize();
    }
}
