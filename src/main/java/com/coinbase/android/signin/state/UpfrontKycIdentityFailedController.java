package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityFailedBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class UpfrontKycIdentityFailedController extends ActionBarController implements UpfrontKycIdentityFailedScreen {
    ControllerUpfrontKycIdentityFailedBinding mBinding;
    @Inject
    UpfrontKycIdentityFailedPresenter mPresenter;

    public UpfrontKycIdentityFailedController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerUpfrontKycIdentityFailedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_failed, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addUpfrontKycIdentityFailedControllerSubcomponent(new UpfrontKycIdentityFailedPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btnTryAgain.setOnClickListener(UpfrontKycIdentityFailedController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void setHeader(String header) {
        this.mBinding.tvHeader.setText(header);
    }

    public void setHint(String hint) {
        this.mBinding.tvHint.setText(hint);
    }
}
