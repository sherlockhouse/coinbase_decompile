package com.coinbase.android.signin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerDeviceVerifyBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import javax.inject.Inject;

@ControllerScope
public class DeviceVerifyController extends ActionBarController implements DeviceVerifyScreen {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;
    private ControllerDeviceVerifyBinding mBinding;
    @Inject
    DeviceVerifyPresenter mPresenter;

    public DeviceVerifyController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerDeviceVerifyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_device_verify, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addDeviceVerifyControllerSubcomponent(new DeviceVerifyPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        this.mAnimationUtilsWrapper.startAnimation(this.mBinding.ivEmailSpinnerBackground, R.anim.rotate_indefinitely);
        this.mPresenter.onCreate(getArgs());
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void showProgress() {
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showDeviceVerifyView() {
        this.mBinding.llDeviceVerifyContainer.setVisibility(0);
    }

    public void hideDeviceVerifyView() {
        this.mBinding.llDeviceVerifyContainer.setVisibility(8);
    }
}
