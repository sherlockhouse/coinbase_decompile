package com.coinbase.android.signin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerTwoFactorBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import com.coinbase.android.ui.OptionalViewHider;
import com.coinbase.android.utils.Utils;
import java.util.Arrays;
import javax.inject.Inject;

@ControllerScope
public class TwoFactorController extends ActionBarController implements TwoFactorScreen {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;
    private ControllerTwoFactorBinding mBinding;
    @Inject
    OptionalViewHider mOptionalViewHider;
    @Inject
    TwoFactorPresenter mPresenter;

    public TwoFactorController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerTwoFactorBinding) DataBindingUtil.inflate(inflater, R.layout.controller_two_factor, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addTwoFactorControllerSubcomponent(new TwoFactorPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        this.mAnimationUtilsWrapper.startAnimation(this.mBinding.ivEmailSpinnerBackground, R.anim.rotate_indefinitely);
        this.mBinding.etLogin2faField.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && event.getKeyCode() == 66) || actionId == 6) {
                    TwoFactorController.this.mPresenter.onSubmit();
                }
                return false;
            }
        });
        this.mBinding.btnLogin2faVerify.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TwoFactorController.this.mPresenter.onSubmit();
            }
        });
        this.mPresenter.onCreate(getArgs());
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.etLogin2faField.requestFocus();
        Utils.postShowKeyboardFrom(getActivity(), this.mBinding.etLogin2faField);
        this.mOptionalViewHider.registerViews(Arrays.asList(new View[]{this.mBinding.flPhoneLogo}), Arrays.asList(new View[]{this.mBinding.btnLogin2faVerify}));
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
        this.mOptionalViewHider.clearOptionalViews();
        Utils.hideKeyboardFrom(getActivity(), this.mBinding.etLogin2faField);
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void setActionText(String text) {
        this.mBinding.tvLogin2faType.setText(text);
    }

    public void setActionOnClickListener(OnClickListener listener) {
        this.mBinding.tvLogin2faType.setOnClickListener(listener);
    }

    public void setActionClickable(boolean clickable) {
        this.mBinding.tvLogin2faType.setClickable(clickable);
    }

    public String getEnteredText() {
        return this.mBinding.etLogin2faField.getText().toString();
    }

    public void showProgress() {
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showTwoFactorView() {
        this.mBinding.llTwoFactorContainer.setVisibility(0);
    }

    public void hideTwoFactorView() {
        this.mBinding.llTwoFactorContainer.setVisibility(8);
    }
}
