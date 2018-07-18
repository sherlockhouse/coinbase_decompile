package com.coinbase.android.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.coinbase.android.ActivityProvider;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BaseActivityModule;
import com.coinbase.android.BottomNavActivityModule;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.MainActivitySubcomponent;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityMainContentBinding;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.BaseViewProvider;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import com.coinbase.android.ui.ModalViewProvider;
import javax.inject.Inject;

@ActivityScope
public class RegistrationControllerActivity extends AppCompatActivity implements ActivityProvider, MainActivitySubcomponentProvider, BaseViewProvider, ModalViewProvider {
    public static final String NEXT_FLOW = "RegistrationControllerActivity_NextFlow";
    @Inject
    AuthManager mAuthManager;
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    private ActivityMainContentBinding mContentBinding;
    private MainActivitySubcomponent mMainActivitySubcomponent;
    private Router mRouter;
    @Inject
    SplitTesting mSplitTesting;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContentBinding = (ActivityMainContentBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_content);
        this.mMainActivitySubcomponent = ((ComponentProvider) getApplicationContext()).applicationComponent().mainActivitySubcomponent(new BaseActivityModule(this), new BaseViewModule(this), new BottomNavActivityModule(RegistrationControllerActivity$$Lambda$1.lambdaFactory$(), this));
        this.mMainActivitySubcomponent.inject(this);
        Intent intent = getIntent();
        if (intent == null || !routeByScheme(intent.getScheme(), savedInstanceState)) {
            Bundle bundle = new Bundle();
            if (!(intent == null || intent.getExtras() == null)) {
                bundle = intent.getExtras();
            }
            routeToRootController(getRootController(createRootControllerBundle(bundle, false)), savedInstanceState);
        }
    }

    private void routeToRootController(ActionBarController rootController, Bundle savedInstanceState) {
        this.mRouter = Conductor.attachRouter(this, this.mContentBinding.cvControllerContainer, savedInstanceState);
        if (!this.mRouter.hasRootController()) {
            this.mRouter.setRoot(RouterTransaction.with(rootController));
        }
    }

    private ActionBarController getRootController(Bundle bundle) {
        if (this.mAuthManager.isSignUp()) {
            return new SignUpController(bundle);
        }
        return new LoginController(bundle);
    }

    private Bundle createRootControllerBundle(Bundle inBundle, boolean routedByScheme) {
        Bundle bundle = new Bundle(inBundle);
        bundle.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, LifeCycleType.MODAL.getValue());
        bundle.putBoolean(AuthRouter.ROUTED_BY_SCHEME, routedByScheme);
        bundle.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, 0);
        return bundle;
    }

    private boolean routeByScheme(String scheme, Bundle savedInstanceState) {
        if (TextUtils.equals(EmailVerifyPresenter.EMAIL_SCHEME, scheme)) {
            Bundle newBundle = createRootControllerBundle(new Bundle(), true);
            newBundle.putInt(NEXT_FLOW, ViewType.EMAIL_VERIFICATION.getType());
            routeToRootController(getRootController(newBundle), savedInstanceState);
            return true;
        }
        if (TextUtils.equals("coinbase", scheme)) {
            this.mAuthManager.setAuthScreen(ViewType.LOGIN.ordinal());
            routeToRootController(getRootController(createRootControllerBundle(new Bundle(), true)), savedInstanceState);
        }
        return false;
    }

    public void onBackPressed() {
        this.mBackNavigationConnector.get().onNext(null);
    }

    public View getBaseView() {
        return this.mContentBinding.cvControllerContainer;
    }

    public RelativeLayout getBlockingOverlayView() {
        return this.mContentBinding.flOverlay;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public MainActivitySubcomponent mainActivitySubcomponent() {
        return this.mMainActivitySubcomponent;
    }

    public ViewGroup getModalView() {
        return this.mContentBinding.flModalContainer;
    }
}
