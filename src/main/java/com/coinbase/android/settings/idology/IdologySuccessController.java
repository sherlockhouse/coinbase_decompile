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
import com.coinbase.android.databinding.ControllerIdologySuccessBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class IdologySuccessController extends ActionBarController implements IdologySuccessScreen {
    ControllerIdologySuccessBinding mBinding;
    @Inject
    IdologySuccessPresenter mPresenter;

    public IdologySuccessController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdologySuccessBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_success, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addIdologySuccessControllerSubcomponent(new IdologySuccessPresenterModule(this)).inject(this);
        this.mBinding.btGotoSettings.setOnClickListener(IdologySuccessController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.verify_identity));
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        this.mPresenter.gotoSettings();
        return true;
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    public void setSuccessButtonText(String text) {
        this.mBinding.btGotoSettings.setText(text);
    }
}
