package com.coinbase.android.settings.idology;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerIdologyFailureBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class IdologyFailureController extends ActionBarController implements IdologyFailureScreen {
    ControllerIdologyFailureBinding mBinding;
    @Inject
    IdologyFailurePresenter mPresenter;

    public IdologyFailureController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdologyFailureBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_failure, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addIdologyFailureControllerSubcomponent(new IdologyFailurePresenterModule(this)).inject(this);
        this.mBinding.btTryAgain.setOnClickListener(IdologyFailureController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }
}
