package com.coinbase.android.signin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerEmailVerifyBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import javax.inject.Inject;

@ControllerScope
public class EmailVerifyController extends ActionBarController implements EmailVerifyScreen {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;
    private ControllerEmailVerifyBinding mBinding;
    @Inject
    EmailVerifyPresenter mPresenter;

    public EmailVerifyController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerEmailVerifyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_email_verify, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addEmailVerifyControllerSubcomponent(new EmailVerifyPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.tvSignupIncorrectEmail.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EmailVerifyController.this.mPresenter.onCancelClicked();
            }
        });
        this.mAnimationUtilsWrapper.startAnimation(this.mBinding.ivEmailSpinnerBackground, R.anim.rotate_indefinitely);
        this.mPresenter.onCreate(getArgs());
        return this.mBinding.getRoot();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void setMessage(Spanned spanned) {
        this.mBinding.tvSignupConfirmEmail.setText(spanned);
    }

    public void showProgress() {
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showEmailView() {
        this.mBinding.llEmailContainer.setVisibility(0);
    }

    public void hideEmailView() {
        this.mBinding.llEmailContainer.setVisibility(8);
    }
}
