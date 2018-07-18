package com.coinbase.android.signin.state;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
import com.coinbase.android.databinding.ControllerStateLockedBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class StateLockedController extends ActionBarController implements StateLockedScreen {
    ControllerStateLockedBinding mBinding;
    @Inject
    StateLockedPresenter mPresenter;

    public StateLockedController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateLockedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_locked, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateLockedControllerSubcomponent(new StateLockedPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceDisplayHomeAsUp(true);
        setForceDisableToolbarThemeUpdate(true);
        this.mBinding.btnGoBack.setOnClickListener(StateLockedController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnContactSupport.setOnClickListener(StateLockedController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.account_locked));
    }

    protected boolean onBackPressed() {
        showCancelDialog();
        return true;
    }

    public void showCancelDialog() {
        Builder alertBuilder = new Builder(getActivity());
        alertBuilder.setMessage(getResources().getString(R.string.abandon_state_selection));
        alertBuilder.setPositiveButton(R.string.ok, StateLockedController$$Lambda$3.lambdaFactory$(this));
        alertBuilder.setNegativeButton(R.string.cancel, StateLockedController$$Lambda$4.lambdaFactory$());
        alertBuilder.show();
    }

    static /* synthetic */ void lambda$showCancelDialog$3(DialogInterface dialog, int which) {
    }

    public void showSupportActivity(Intent contactSupportIntent) {
        if (contactSupportIntent != null && contactSupportIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(contactSupportIntent);
        }
    }
}
