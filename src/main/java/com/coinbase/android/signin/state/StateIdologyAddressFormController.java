package com.coinbase.android.signin.state;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateIdologyAddressFormBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyAddressFormController extends ActionBarController implements StateIdologyAddressFormScreen {
    ControllerStateIdologyAddressFormBinding mBinding;
    @Inject
    StateIdologyAddressFormPresenter mPresenter;

    public StateIdologyAddressFormController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateIdologyAddressFormBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_address_form, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologyAddressFormControllerSubcomponent(new StateIdologyAddressFormPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.idologyFormLayout.setController(this);
        this.mBinding.idologyFormLayout.setContinueButtonTextCallback(StateIdologyAddressFormController$$Lambda$1.lambdaFactory$(this));
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnIdologyContinue.setOnClickListener(StateIdologyAddressFormController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.idologyFormLayout.onShow(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
        this.mBinding.idologyFormLayout.onHide();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!this.mBinding.idologyFormLayout.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected boolean onBackPressed() {
        if (this.mBinding.idologyFormLayout.onBackPressed()) {
            return true;
        }
        return this.mPresenter.onBackPressed();
    }

    public void submitForm(Data buildingData) {
        this.mBinding.idologyFormLayout.onContinueClicked();
    }

    public void setContinueButtonEnabled(boolean isEnabled) {
        this.mBinding.btnIdologyContinue.setEnabled(isEnabled);
    }

    public void showContinueButton(boolean isShown) {
        int visibility = isShown ? 0 : 8;
        this.mBinding.btnIdologyContinue.setVisibility(visibility);
        this.mBinding.btnIdologyContinueDivider.setVisibility(visibility);
    }

    public void setIdologyData(Data idologyData) {
        this.mBinding.idologyFormLayout.setData(idologyData);
    }

    public void showProgress() {
        this.mBinding.nsvIdologyFormContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.nsvIdologyFormContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
