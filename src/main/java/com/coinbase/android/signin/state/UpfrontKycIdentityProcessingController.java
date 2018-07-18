package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerUpfrontKycIdvProcessingBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import javax.inject.Inject;

public class UpfrontKycIdentityProcessingController extends ActionBarController implements UpfrontKycIdentityProcessingScreen {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;
    private ControllerUpfrontKycIdvProcessingBinding mBinding;
    @Inject
    UpfrontKycIdentityProcessingPresenter mPresenter;

    public UpfrontKycIdentityProcessingController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerUpfrontKycIdvProcessingBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_idv_processing, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addUpfrontKycIdentityProcessingControllerSubcomponent(new UpfrontKycIdentityProcessingPresenterModule(this, this)).inject(this);
        this.mAnimationUtilsWrapper.startAnimation(this.mBinding.ivIdvSpinnerBackground, R.anim.rotate_indefinitely);
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void showProgress() {
        this.mBinding.llIdvContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.llIdvContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
