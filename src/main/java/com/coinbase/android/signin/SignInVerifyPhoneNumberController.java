package com.coinbase.android.signin;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.SigninVerifyPhoneNumberControllerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.OptionalViewHider;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.Arrays;
import javax.inject.Inject;

@ControllerScope
public class SignInVerifyPhoneNumberController extends ActionBarController implements SignInVerifyPhoneNumberScreen {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    private SigninVerifyPhoneNumberControllerBinding mBinding;
    private TextWatcher mEditTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            SignInVerifyPhoneNumberController.this.mPresenter.onFormUpdated();
        }
    };
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    OptionalViewHider mOptionalViewHider;
    @Inject
    SignInVerifyPhoneNumberPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    public SignInVerifyPhoneNumberController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addSignInVerifyPhoneNumberControllerSubcomponent(new SignInVerifyPhoneNumberPresenterModule(this)).inject(this);
        onAttachToolbar(null);
        setShouldNotHideKeyboard(true);
        this.mBinding = (SigninVerifyPhoneNumberControllerBinding) DataBindingUtil.inflate(inflater, R.layout.signin_verify_phone_number_controller, null, false);
        this.mAnimationUtilsWrapper.startAnimation(this.mBinding.ivEmailSpinnerBackground, R.anim.rotate_indefinitely);
        this.mPresenter.onCreate(getArgs());
        this.mBinding.btnBack.setOnClickListener(SignInVerifyPhoneNumberController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etInput2fa.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.btnResendToken.setOnClickListener(SignInVerifyPhoneNumberController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mOptionalViewHider.registerViews(Arrays.asList(new View[]{this.mBinding.flPhoneLogoContainer}), Arrays.asList(new View[]{this.mBinding.btnResendToken}));
        this.mBinding.etInput2fa.requestFocus();
        this.mPresenter.onShow();
    }

    protected boolean onBackPressed() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_BACK, new String[0]);
        return super.onBackPressed();
    }

    protected void onHide() {
        super.onHide();
        this.mOptionalViewHider.clearOptionalViews();
        if (this.mPresenter != null) {
            this.mPresenter.onHide();
        }
    }

    public void showProgressDialog() {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.verifying));
    }

    public void setVerificationText(CharSequence text) {
        this.mBinding.tvVerifyText.setText(text);
    }

    public void hideProgressDialog() {
        if (this.mProgressDialog != null && getActivity() != null) {
            Utils.dismissDialog(this.mProgressDialog);
        }
    }

    public String getInput() {
        return this.mBinding.etInput2fa.getText().toString();
    }

    public void showProgress() {
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showVerifyPhoneNumberView() {
        this.mBinding.svVerifyPhoneNumberContainer.setVisibility(0);
    }

    public void hideVerifyPhoneNumberView() {
        this.mBinding.svVerifyPhoneNumberContainer.setVisibility(8);
    }
}
