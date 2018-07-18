package com.coinbase.android.identityverification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.coinbase.android.ActivityProvider;
import com.coinbase.android.BaseActivityModule;
import com.coinbase.android.BottomNavActivityModule;
import com.coinbase.android.CoinbaseActivity;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.MainActivitySubcomponent;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityMainContentBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.BaseViewProvider;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ControllerLifeCycleFactory.LifeCycleType;
import com.coinbase.android.ui.ModalViewProvider;
import javax.inject.Inject;

public class IdentityVerificationActivity extends CoinbaseActivity implements ActivityProvider, MainActivitySubcomponentProvider, BaseViewProvider, ModalViewProvider {
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    private ActivityMainContentBinding mContentBinding;
    private MainActivitySubcomponent mMainActivitySubcomponent;
    private Router mRouter;

    protected void onCreate(Bundle savedInstanceState) {
        supportLandscapeOnTablet();
        super.onCreate(savedInstanceState);
        this.mScreenProtector.protect();
        this.mContentBinding = (ActivityMainContentBinding) DataBindingUtil.setContentView(this, R.layout.activity_main_content);
        this.mMainActivitySubcomponent = ((ComponentProvider) getApplicationContext()).applicationComponent().mainActivitySubcomponent(new BaseActivityModule(this), new BaseViewModule(this), new BottomNavActivityModule(IdentityVerificationActivity$$Lambda$1.lambdaFactory$(), this));
        this.mMainActivitySubcomponent.inject(this);
        ActionBarController controller = new InAppIdentityAcceptableDocumentsController(createBundle(LifeCycleType.MODAL));
        this.mRouter = Conductor.attachRouter(this, this.mContentBinding.cvControllerContainer, savedInstanceState);
        if (!this.mRouter.hasRootController()) {
            this.mRouter.setRoot(RouterTransaction.with(controller));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

    private Bundle createBundle(LifeCycleType lifeCycleType) {
        Bundle bundle = new Bundle();
        bundle.putInt(ControllerLifeCycle.LIFE_CYCLE_TYPE, lifeCycleType.getValue());
        return bundle;
    }

    public void onBackPressed() {
        this.mBackNavigationConnector.get().onNext(null);
    }
}
