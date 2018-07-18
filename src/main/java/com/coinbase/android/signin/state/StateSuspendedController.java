package com.coinbase.android.signin.state;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerStateSuspendedBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class StateSuspendedController extends ActionBarController implements StateSuspendedScreen {
    ControllerStateSuspendedBinding mBinding;
    @Inject
    StateSuspendedPresenter mPresenter;

    public StateSuspendedController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerStateSuspendedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_suspended, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addStateSuspendedControllerSubcomponent(new StateSuspendedPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceDisplayHomeAsUp(true);
        setForceDisableToolbarThemeUpdate(true);
        this.mBinding.btnGoBack.setOnClickListener(StateSuspendedController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.account_setup));
    }

    protected boolean onBackPressed() {
        showCancelDialog();
        return true;
    }

    public void showCancelDialog() {
        Builder alertBuilder = new Builder(getActivity());
        alertBuilder.setMessage(getResources().getString(R.string.abandon_state_selection));
        alertBuilder.setPositiveButton(R.string.ok, StateSuspendedController$$Lambda$2.lambdaFactory$(this));
        alertBuilder.setNegativeButton(R.string.cancel, StateSuspendedController$$Lambda$3.lambdaFactory$());
        alertBuilder.show();
    }

    static /* synthetic */ void lambda$showCancelDialog$2(DialogInterface dialog, int which) {
    }
}
