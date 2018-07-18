package com.coinbase.android.ui;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.functions.Func0;

@ControllerScope
public abstract class ActionBarController extends Controller {
    private static final String LOCAL_ROOT = "local_root";
    public static final String OVERIDDEN_UP_INDICATOR = "overidden_up_indicator";
    ActionBar mActionBar;
    protected Boolean mAnimateStatusBar = Boolean.valueOf(false);
    @Inject
    AppCompatActivity mAppCompatActivity;
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    @Inject
    ControllerTransitionContainer mChildTransitionBehavior;
    private boolean mForceDisableToolbarThemeUpdate = false;
    private boolean mForceDisplayHomeAsUp = false;
    private boolean mForceHideHomeDisplay = false;
    private ControllerChangeType mLastControllerChangeStartType;
    private ControllerLifeCycle mLifeCycle;
    @Inject
    ControllerLifeCycleFactory mLifeCycleFactory;
    private final Logger mLogger = LoggerFactory.getLogger(ActionBarController.class);
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    private ModalToPageChangeListener mModalToPageChangeListener;
    @Inject
    @ModalNavItem
    Func0<ViewGroup> mModalView;
    private Integer mOverriddenUpIndicator;
    private boolean mShouldNotHideKeyboard = false;
    @Inject
    StatusBarUpdater mStatusBarUpdater;
    protected ThemeUpdater mThemeUpdater;
    Toolbar mToolbar;

    private static class ModalToPageChangeListener implements ControllerChangeListener {
        private final ControllerLifeCycle mLifeCycle;
        private final ViewGroup mModalViewGroup;

        ModalToPageChangeListener(ControllerLifeCycle lifeCycle, ViewGroup modalViewGroup) {
            this.mLifeCycle = lifeCycle;
            this.mModalViewGroup = modalViewGroup;
        }

        public void onChangeStarted(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler handler) {
            if (from == null && this.mLifeCycle != null) {
                this.mLifeCycle.replayOnHide();
            }
        }

        public void onChangeCompleted(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler handler) {
            if (to == null && this.mLifeCycle != null) {
                this.mLifeCycle.replayOnShow();
                this.mModalViewGroup.setClickable(false);
            }
        }
    }

    private ActionBarController() {
    }

    protected ActionBarController(Bundle args) {
        super(args);
        if (!args.containsKey(ControllerLifeCycle.LIFE_CYCLE_TYPE)) {
            throw new IllegalStateException("Controller must have a defined lifecycle type");
        } else if (args.containsKey(OVERIDDEN_UP_INDICATOR)) {
            this.mOverriddenUpIndicator = Integer.valueOf(args.getInt(OVERIDDEN_UP_INDICATOR));
        } else {
            this.mOverriddenUpIndicator = null;
        }
    }

    public void updateActionBar() {
        enableBackSubscription();
        if (this.mToolbar != null) {
            int upIndicator;
            this.mAppCompatActivity.setSupportActionBar(this.mToolbar);
            this.mActionBar = this.mAppCompatActivity.getSupportActionBar();
            this.mThemeUpdater = new ThemeUpdater(this.mStatusBarUpdater, this.mToolbar, this.mChildTransitionBehavior.getTransitionDuration());
            setTitle(getTitle());
            if (this.mOverriddenUpIndicator == null) {
                upIndicator = this.mLifeCycle.getUpIndicator();
            } else {
                upIndicator = this.mOverriddenUpIndicator.intValue();
            }
            if (upIndicator > 0) {
                this.mActionBar.setHomeAsUpIndicator(upIndicator);
            }
            if (getRouter().getBackstackSize() > 1 || this.mForceDisplayHomeAsUp || (this.mLifeCycle instanceof ModalControllerLifeCycle)) {
                setHasOptionsMenu(true);
                this.mActionBar.setDisplayHomeAsUpEnabled(true);
                int upIndicatorColor = getUpIndicatorColor();
                if (this.mToolbar.getNavigationIcon() != null && upIndicatorColor > 0) {
                    this.mToolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(getActivity(), upIndicatorColor), Mode.SRC_ATOP);
                }
            } else {
                this.mActionBar.setDisplayHomeAsUpEnabled(false);
            }
            if (getRouter().getBackstackSize() > 1 || (this.mLifeCycle instanceof ModalControllerLifeCycle)) {
                this.mToolbar.setNavigationOnClickListener(ActionBarController$$Lambda$1.lambdaFactory$(this));
            } else if (this.mForceDisplayHomeAsUp) {
                this.mToolbar.setNavigationOnClickListener(ActionBarController$$Lambda$2.lambdaFactory$(this));
            }
            int toolbarTextColor = getToolbarTextColor();
            if (toolbarTextColor > 0) {
                this.mToolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), toolbarTextColor));
            }
            if (this.mForceHideHomeDisplay) {
                this.mActionBar.setHomeButtonEnabled(false);
                this.mActionBar.setDisplayHomeAsUpEnabled(false);
                this.mActionBar.setDisplayShowHomeEnabled(false);
            }
            if (!this.mForceDisableToolbarThemeUpdate) {
                if (this.mAnimateStatusBar.booleanValue()) {
                    this.mThemeUpdater.setThemeColorAndAnimateStatusBar(getThemeColor(), getStatusBarColor());
                } else {
                    this.mThemeUpdater.setThemeColor(getThemeColor(), getStatusBarColor());
                }
            }
            if (getToolbarTextColor() > 0 && this.mToolbar != null) {
                this.mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(getToolbarTextColor()), Mode.SRC_ATOP);
            }
        } else if (!this.mForceDisableToolbarThemeUpdate) {
            if (this.mAnimateStatusBar.booleanValue()) {
                this.mStatusBarUpdater.startStatusBarAnimation(getStatusBarColor(), this.mChildTransitionBehavior.getTransitionDuration());
            } else {
                this.mStatusBarUpdater.setStatusBarColor(getStatusBarColor());
            }
        }
    }

    static /* synthetic */ void lambda$updateActionBar$0(ActionBarController this_, View v) {
        if (!this_.onBackPressed()) {
            if (this_.getRouter().getContainerId() == 0) {
                this_.mLogger.error("Container id is null, refusing to handle back from toolbar");
            } else if (this_.getRouter().hasRootController()) {
                this_.clearToolbarNavigationOnClickListener();
                if (this_.getParentController() instanceof MainController) {
                    this_.getRouter().popCurrentController();
                } else if (this_.getParentController() == null || this_.getRouter().getBackstackSize() != 1) {
                    this_.getRouter().handleBack();
                } else {
                    this_.gobackFromModalToPageController();
                }
            }
        }
    }

    protected boolean onBackPressed() {
        return false;
    }

    protected int getStatusBarColor() {
        return getThemeColor();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.coinbase_blue);
    }

    protected int getUpIndicatorColor() {
        return -1;
    }

    protected int getToolbarTextColor() {
        return -1;
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.mLifeCycle.onAttach(view);
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        this.mLifeCycle.onDetach(view);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mLifeCycle != null) {
            this.mLifeCycle.onDestroy();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.mLifeCycle != null && this.mLastControllerChangeStartType.isEnter) {
            this.mLifeCycle.onPrepareOptionsMenu(menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.mLifeCycle != null) {
            return this.mLifeCycle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onChangeStarted(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        super.onChangeStarted(changeHandler, changeType);
        this.mAnimateStatusBar = Boolean.valueOf(!(changeHandler instanceof SimpleSwapChangeHandler));
        this.mLastControllerChangeStartType = changeType;
        if (this.mLifeCycle != null && !changeType.isEnter) {
            this.mLifeCycle.onChangeStartHide();
        }
    }

    protected void onChangeEnded(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);
        if (this.mLifeCycle != null && changeType.isEnter && this.mLastControllerChangeStartType.isEnter) {
            this.mLifeCycle.onChangeEndShow();
        }
    }

    protected void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
        if (this.mLifeCycle != null) {
            this.mLifeCycle.onActivityPaused(activity);
        }
    }

    protected void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        if (this.mLifeCycle != null) {
            this.mLifeCycle.onActivityResumed(activity);
        }
    }

    protected void onShow() {
    }

    protected void onHide() {
    }

    protected void onShowOptionsMenu(Menu menu) {
    }

    protected boolean onShownOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    protected void disableBackSubscription() {
        this.mBackNavigationConnector.clearBackSubscription();
    }

    protected void enableBackSubscription() {
        this.mBackNavigationConnector.resubscribeBack(this.mBackNavigationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(ActionBarController$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$enableBackSubscription$2(ActionBarController this_, Void v) {
        if (!this_.onBackPressed()) {
            if (this_.getRouter().getContainerId() == 0) {
                this_.mLogger.error("Container id is null, refusing to handle back");
            } else if ((this_.getParentController() instanceof ActionBarController) && this_.getRouter().getBackstackSize() == 1) {
                this_.gobackFromModalToPageController();
            } else if (!this_.getRouter().handleBack()) {
                this_.mAppCompatActivity.finish();
            }
        }
    }

    protected SpannableStringBuilder getTitle() {
        return null;
    }

    protected String getTag() {
        return getClass().getName();
    }

    public void pushModalController(ActionBarController controller) {
        pushModalController(controller, false);
    }

    public void pushModalController(ActionBarController controller, boolean useModalAnimation) {
        pushModalController(controller, useModalAnimation, null, null);
    }

    public void pushModalController(ActionBarController controller, boolean useModalAnimation, ControllerChangeHandler pushChangeHandler, ControllerChangeHandler popChangeHandler) {
        RouterTransaction routerTransaction = RouterTransaction.with(controller).tag(controller.getTag());
        if (this.mLifeCycle instanceof PageControllerLifeCycle) {
            pushRootModalController(routerTransaction.pushChangeHandler(this.mChildTransitionBehavior.modalPushChangeHandler()).popChangeHandler(this.mChildTransitionBehavior.modalPopChangeHandler()));
            return;
        }
        if (pushChangeHandler == null) {
            if (useModalAnimation) {
                pushChangeHandler = this.mChildTransitionBehavior.modalPushChangeHandler();
            } else {
                pushChangeHandler = this.mChildTransitionBehavior.pushChangeHandler();
            }
        }
        if (popChangeHandler == null) {
            if (useModalAnimation) {
                popChangeHandler = this.mChildTransitionBehavior.modalPopChangeHandler();
            } else {
                popChangeHandler = this.mChildTransitionBehavior.popChangeHandler();
            }
        }
        routerTransaction.pushChangeHandler(pushChangeHandler).popChangeHandler(popChangeHandler);
        Router modalRouter = getRouter();
        if (modalRouter.getContainerId() == 0) {
            this.mLogger.error("Container id is null, refusing to push controller");
        } else if (modalRouter.getBackstackSize() <= 0 || modalRouter.getControllerWithTag(controller.getTag()) == null) {
            modalRouter.pushController(routerTransaction);
        } else {
            modalRouter.popToTag(controller.getTag());
            modalRouter.replaceTopController(routerTransaction);
        }
    }

    public void pushDialogModalController(DialogController controller) {
        ControllerChangeHandler pushHandler = this.mChildTransitionBehavior.dialogPushChangeHandler();
        RouterTransaction routerTransaction = RouterTransaction.with(controller).tag(controller.getTag()).pushChangeHandler(pushHandler).popChangeHandler(this.mChildTransitionBehavior.dialogPopChangeHandler());
        if (this.mLifeCycle instanceof PageControllerLifeCycle) {
            pushRootModalController(routerTransaction);
        } else {
            getRouter().pushController(routerTransaction);
        }
    }

    public void replaceModalController(ActionBarController controller) {
        if ((this.mLifeCycle instanceof PageControllerLifeCycle) || getRouter().getBackstackSize() <= 1) {
            replaceModalController(controller, this.mChildTransitionBehavior.modalPushChangeHandler(), this.mChildTransitionBehavior.modalPopChangeHandler());
        } else {
            replaceModalController(controller, this.mChildTransitionBehavior.pushChangeHandler(), this.mChildTransitionBehavior.popChangeHandler());
        }
    }

    public void replaceModalController(ActionBarController controller, ControllerChangeHandler pushChangeHandler, ControllerChangeHandler popChangeHandler) {
        getRouter().replaceTopController(RouterTransaction.with(controller).tag(controller.getTag()).pushChangeHandler(pushChangeHandler).popChangeHandler(popChangeHandler));
    }

    protected void injectComponentIfNeeded() {
        if (this.mModalView == null) {
            ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        }
    }

    protected void onAttachToolbar(Toolbar toolbar) {
        injectComponentIfNeeded();
        if (this.mLifeCycle == null) {
            this.mLifeCycle = this.mLifeCycleFactory.createFromController(this);
        }
        this.mLifeCycle.onCreate();
        this.mToolbar = toolbar;
    }

    protected void setForceDisplayHomeAsUp(boolean forceDisplayHomeAsUp) {
        this.mForceDisplayHomeAsUp = forceDisplayHomeAsUp;
    }

    protected void setForceHideHomeDisplay(boolean forceHideHomeDisplay) {
        this.mForceHideHomeDisplay = forceHideHomeDisplay;
    }

    protected void setForceDisableToolbarThemeUpdate(boolean forceDisableToolbarThemeUpdate) {
        this.mForceDisableToolbarThemeUpdate = forceDisableToolbarThemeUpdate;
    }

    protected void setAnimateStatusBar(boolean animateStatusBar) {
        this.mAnimateStatusBar = Boolean.valueOf(animateStatusBar);
    }

    protected void setShouldNotHideKeyboard(boolean shouldNotHideKeyboard) {
        this.mShouldNotHideKeyboard = shouldNotHideKeyboard;
    }

    protected Bundle appendArgs(Bundle args) {
        appendArgsInner(args);
        appendLocalRootIfNeeded(args);
        return args;
    }

    protected Bundle appendArgsWithRoot(Bundle args) {
        appendArgsInner(args);
        args.putString(LOCAL_ROOT, getTag());
        return args;
    }

    public Bundle appendPageArgs(Bundle args) {
        args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.PAGE.getValue());
        appendLocalRootIfNeeded(args);
        return args;
    }

    public Bundle appendModalArgs(Bundle args) {
        args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.MODAL.getValue());
        appendLocalRootIfNeeded(args);
        return args;
    }

    public Bundle appendModalArgsWithRoot(Bundle args) {
        args.putString(LOCAL_ROOT, getTag());
        args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.MODAL.getValue());
        return args;
    }

    public void appendLocalRootIfNeeded(Bundle args) {
        if (getArgs().containsKey(LOCAL_ROOT)) {
            args.putString(LOCAL_ROOT, getArgs().getString(LOCAL_ROOT));
        }
    }

    public String getLocalRoot() {
        return getArgs().getString(LOCAL_ROOT);
    }

    public ActionBarController getController() {
        return this;
    }

    protected void setTitle(SpannableStringBuilder title) {
        if (this.mToolbar != null) {
            if (title != null) {
                this.mToolbar.setTitle((CharSequence) title);
            } else {
                this.mToolbar.setTitle((CharSequence) "");
            }
        }
    }

    public ControllerLifeCycle getControllerLifeCycle() {
        return this.mLifeCycle;
    }

    public void hideSoftKeyboard() {
        if (!this.mShouldNotHideKeyboard) {
            InputMethodManager imm = (InputMethodManager) this.mAppCompatActivity.getSystemService("input_method");
            View view = this.mAppCompatActivity.getCurrentFocus();
            if (view == null) {
                view = new View(this.mAppCompatActivity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void pushRootModalController(RouterTransaction routerTransaction) {
        ViewGroup modalViewGroup = (ViewGroup) this.mModalView.call();
        if (modalViewGroup == null) {
            this.mLogger.error("Modal view group is null, should never happen");
            return;
        }
        modalViewGroup.setOnClickListener(ActionBarController$$Lambda$4.lambdaFactory$());
        Router modalRouter = getChildRouter(modalViewGroup);
        if (this.mModalToPageChangeListener == null) {
            this.mModalToPageChangeListener = new ModalToPageChangeListener(this.mLifeCycle, modalViewGroup);
        }
        modalRouter.addChangeListener(this.mModalToPageChangeListener);
        modalRouter.setRoot(routerTransaction);
        modalRouter.setPopsLastView(true);
    }

    static /* synthetic */ void lambda$pushRootModalController$3(View v) {
    }

    public boolean isShown() {
        return getActivity() != null && isAttached();
    }

    private void appendArgsInner(Bundle args) {
        if (!getArgs().containsKey(ControllerLifeCycle.LIFE_CYCLE_TYPE) && this.mLifeCycle == null) {
            return;
        }
        if (this.mLifeCycle != null) {
            args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.fromLifeCycle(this.mLifeCycle).getValue());
        } else {
            args.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, getArgs().getInt(ControllerLifeCycle.LIFE_CYCLE_TYPE));
        }
    }

    private void clearToolbarNavigationOnClickListener() {
        this.mToolbar.setNavigationOnClickListener(ActionBarController$$Lambda$5.lambdaFactory$());
    }

    static /* synthetic */ void lambda$clearToolbarNavigationOnClickListener$4(View v1) {
    }

    private void gobackFromModalToPageController() {
        if ((getParentController() instanceof ActionBarController) && getRouter().getBackstackSize() == 1) {
            ActionBarController parentController = (ActionBarController) getParentController();
            if (parentController.mModalToPageChangeListener == null) {
                ViewGroup modalViewGroup = (ViewGroup) this.mModalView.call();
                if (modalViewGroup == null) {
                    this.mLogger.error("Modal view group is null, should never happen");
                    return;
                } else {
                    parentController.mModalToPageChangeListener = new ModalToPageChangeListener(parentController.mLifeCycle, modalViewGroup);
                    getRouter().addChangeListener(parentController.mModalToPageChangeListener);
                }
            }
            getParentController().removeChildRouter(getRouter());
        }
    }
}
