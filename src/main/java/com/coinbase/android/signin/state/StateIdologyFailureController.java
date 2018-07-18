package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerIdologyStateFailureBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyFailureController extends ActionBarController implements StateIdologyFailureScreen {
    ControllerIdologyStateFailureBinding mBinding;
    @Inject
    StateIdologyFailurePresenter mPresenter;

    public StateIdologyFailureController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdologyStateFailureBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_state_failure, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateIdologyFailureControllerSubcomponent(new StateIdologyFailurePresenterModule(this)).inject(this);
        this.mBinding.btTryAgain.setOnClickListener(StateIdologyFailureController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }
}
