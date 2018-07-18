package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateIdologyRetryFormBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyRetryFormController extends ActionBarController implements StateIdologyRetryFormScreen {
    ControllerStateIdologyRetryFormBinding mBinding;
    @Inject
    StateIdologyRetryFormPresenter mPresenter;

    public StateIdologyRetryFormController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateIdologyRetryFormBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_retry_form, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologyRetryFormControllerSubcomponent(new StateIdologyRetryFormPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnIdologyContinue.setOnClickListener(StateIdologyRetryFormController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.idologyFormLayout.getLastEditText().setOnEditorActionListener(StateIdologyRetryFormController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ boolean lambda$onCreateView$1(StateIdologyRetryFormController this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 6) {
            this_.mPresenter.onContinueButtonClicked();
        }
        return false;
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.idologyFormLayout.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
        this.mBinding.idologyFormLayout.onHide();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void submitForm() {
        this.mBinding.idologyFormLayout.submitForm();
    }

    public void setContinueButtonEnabled(boolean isEnabled) {
        this.mBinding.btnIdologyContinue.setEnabled(isEnabled);
    }

    public boolean isContinueButtonEnabled() {
        return this.mBinding.btnIdologyContinue.isEnabled();
    }

    public void setIdologyData(Data idologyData) {
        this.mBinding.idologyFormLayout.setData(idologyData);
    }

    public void showProgress() {
        this.mBinding.rlFormContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.rlFormContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
