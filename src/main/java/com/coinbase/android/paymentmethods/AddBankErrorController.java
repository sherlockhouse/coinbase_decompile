package com.coinbase.android.paymentmethods;

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
import com.coinbase.android.databinding.ControllerAddBankErrorBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class AddBankErrorController extends ActionBarController implements AddBankErrorScreen {
    ControllerAddBankErrorBinding mBinding;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    AddBankErrorPresenter mPresenter;

    public AddBankErrorController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerAddBankErrorBinding) DataBindingUtil.inflate(inflater, R.layout.controller_add_bank_error, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addBankErrorControllerSubcomponent(new AddBankErrorPresenterModule(this)).inject(this);
        this.mBinding.btnGetStarted.setOnClickListener(AddBankErrorController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnNoThanks.setOnClickListener(AddBankErrorController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$0(AddBankErrorController this_, View view) {
        Intent intent = new Intent(this_.getActivity(), PlaidAccountLoginActivity.class);
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        this_.startActivity(intent);
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_PLAID_TAPPED_USE_CDV, new String[0]);
    }

    static /* synthetic */ void lambda$onCreateView$1(AddBankErrorController this_, View view) {
        this_.closeModal();
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.ADD_BANK_ACCOUNT_PLAID_TAPPED_START_OVER, new String[0]);
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void closeModal() {
        ControllerLifeCycle controllerLifeCycle = getControllerLifeCycle();
        if (controllerLifeCycle instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) controllerLifeCycle).closeModal();
        }
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.connect_bank));
    }
}
