package com.coinbase.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.ScreenProtector;
import com.coinbase.android.ui.ModalBottomNavigationItem.Type;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class ModalControllerLifeCycle extends ControllerLifeCycle {
    private boolean mCalledOnHide;
    private boolean mCalledOnShow;
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(ModalControllerLifeCycle.class);
    private final CompositeSubscription mMenuSubscription = new CompositeSubscription();
    @Inject
    @ModalNavItem
    Func0<ViewGroup> mModalView;
    private int mPageDepth;
    @Inject
    ScreenProtector mScreenProtector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    public static abstract class ModalDestination {

        public static abstract class Builder {
            public abstract ModalDestination build();

            public abstract Builder setConsumed(MutableBoolean mutableBoolean);

            public abstract Builder setPushModalControllerFunc(Func1<Bundle, ActionBarController> func1);

            public abstract Builder setType(Type type);
        }

        public abstract MutableBoolean getConsumed();

        public abstract Func1<Bundle, ActionBarController> getPushModalControllerFunc();

        public abstract Type getType();

        public static Builder builder() {
            return new Builder().setConsumed(new MutableBoolean(false));
        }
    }

    protected ModalControllerLifeCycle(ActionBarController controller) {
        this.mController = controller;
    }

    protected SpannableStringBuilder getTitle() {
        return null;
    }

    public void onAttach(View view) {
        this.mCalledOnHide = false;
        this.mPageDepth = this.mController.getRouter().getBackstackSize();
        this.mScreenProtector.conditionallyProtect(this.mController.getClass());
        this.mController.updateActionBar();
        this.mController.setHasOptionsMenu(true);
        this.mController.hideSoftKeyboard();
        if (!this.mCalledOnShow) {
            this.mCalledOnShow = true;
            this.mController.onShow();
        }
    }

    public void onDetach(View v) {
        this.mCalledOnShow = false;
        if (!this.mCalledOnHide) {
            this.mCalledOnHide = true;
            this.mController.onHide();
        }
    }

    void onChangeStartHide() {
        this.mCalledOnShow = false;
        if (!this.mCalledOnHide) {
            this.mCalledOnHide = true;
            this.mController.onHide();
        }
    }

    void onChangeEndShow() {
        if (!this.mCalledOnShow) {
            this.mCalledOnShow = true;
            this.mController.onShow();
        }
    }

    public void onCreate() {
        ((MainActivitySubcomponentProvider) this.mController.getActivity()).mainActivitySubcomponent().inject(this);
        this.mCalledOnHide = false;
        this.mCalledOnShow = false;
    }

    public void onDestroy() {
        this.mSubscription.clear();
        this.mMenuSubscription.clear();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.mCalledOnHide = false;
        menu.clear();
        this.mController.onShowOptionsMenu(menu);
    }

    boolean onOptionsItemSelected(MenuItem item) {
        return this.mController.onShownOptionsItemSelected(item);
    }

    void onActivityPaused(Activity activity) {
        this.mCalledOnShow = false;
        if (!this.mCalledOnHide) {
            this.mCalledOnHide = true;
            this.mController.onHide();
        }
    }

    void onActivityResumed(Activity activity) {
        this.mCalledOnHide = false;
        if (!this.mCalledOnShow && this.mController.getRouter().getBackstackSize() == this.mPageDepth) {
            this.mCalledOnShow = true;
            this.mController.onShow();
        }
    }

    public int getUpIndicator() {
        if (this.mController.getRouter().getBackstackSize() <= 1) {
            return R.drawable.modal_close_white;
        }
        return -1;
    }

    void replayOnShow() {
        this.mCalledOnHide = false;
    }

    void replayOnHide() {
    }

    public void closeModal() {
        if (!(this.mController.getParentController() instanceof ActionBarController)) {
            if (this.mController.getRouter().hasRootController()) {
                this.mController.getRouter().popToRoot();
            }
            this.mController.handleBack();
        } else if (this.mModalView == null || this.mModalView.call() == null) {
            this.mLogger.error("Couldn't close modal, probably no modal view available");
        } else {
            this.mController.getParentController().removeChildRouter(this.mController.getParentController().getChildRouter((ViewGroup) this.mModalView.call()));
        }
    }
}
