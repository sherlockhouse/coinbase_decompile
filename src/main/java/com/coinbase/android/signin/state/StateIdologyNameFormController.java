package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateIdologyNameFormBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyNameFormController extends ActionBarController implements StateIdologyFormScreen {
    ControllerStateIdologyNameFormBinding mBinding;
    @Inject
    StateIdologyNameFormPresenter mPresenter;

    public StateIdologyNameFormController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateIdologyNameFormBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_name_form, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologyNameFormControllerSubcomponent(new StateIdologyNameFormPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnIdologyContinue.setOnClickListener(StateIdologyNameFormController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
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
        if (this.mBinding.idologyFormLayout.hideVisibleLayout()) {
            return true;
        }
        return this.mPresenter.onBackPressed();
    }

    public void submitForm(Data idology) {
        this.mBinding.idologyFormLayout.onContinueClicked();
    }

    public void setContinueButtonEnabled(boolean isEnabled) {
        this.mBinding.btnIdologyContinue.setEnabled(isEnabled);
    }

    public void setIdologyData(Data idologyData) {
        this.mBinding.idologyFormLayout.setData(idologyData);
    }

    public void showProgress() {
        this.mBinding.nsvNameFormContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.nsvNameFormContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
