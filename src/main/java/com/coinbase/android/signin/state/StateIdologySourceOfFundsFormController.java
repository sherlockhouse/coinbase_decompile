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
import com.coinbase.android.databinding.ControllerStateIdologySourceOfFundsFormBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class StateIdologySourceOfFundsFormController extends ActionBarController implements StateIdologySourceOfFundsScreen {
    ControllerStateIdologySourceOfFundsFormBinding mBinding;
    @Inject
    StateIdologySourceOfFundsFormPresenter mPresenter;

    public StateIdologySourceOfFundsFormController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateIdologySourceOfFundsFormBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_source_of_funds_form, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologySourceOfFundsFormControllerSubcomponent(new StateIdologySourceOfFundsFormPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnIdologyContinue.setOnClickListener(StateIdologySourceOfFundsFormController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.idologyFormLayout.getLastEditText().setOnEditorActionListener(StateIdologySourceOfFundsFormController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ boolean lambda$onCreateView$1(StateIdologySourceOfFundsFormController this_, TextView textView, int actionId, KeyEvent keyEvent) {
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

    public void submitForm(Data buildingData) {
        this.mBinding.idologyFormLayout.submitForm(buildingData);
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
        this.mBinding.progress.setVisibility(0);
        this.mBinding.nsvContainer.setVisibility(8);
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
        this.mBinding.nsvContainer.setVisibility(0);
    }
}
