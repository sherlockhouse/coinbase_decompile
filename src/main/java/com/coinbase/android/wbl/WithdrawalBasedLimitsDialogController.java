package com.coinbase.android.wbl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.DialogWithdrawalBasedLimitsErrorBinding;
import com.coinbase.android.ui.DialogController;
import javax.inject.Inject;

public class WithdrawalBasedLimitsDialogController extends DialogController implements WithdrawalBasedLimitsDialogScreen {
    DialogWithdrawalBasedLimitsErrorBinding mBinding;
    @Inject
    WithdrawalBasedLimitsDialogPresenter mPresenter;

    public WithdrawalBasedLimitsDialogController(Bundle bundle) {
        super(bundle);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addWithdrawalBasedLimitsDialogControllerSubcomponent(new WithdrawalBasedLimitsDialogPresenterModule(this)).inject(this);
        this.mBinding = DialogWithdrawalBasedLimitsErrorBinding.inflate(inflater, container, true);
        if (!this.mPresenter.onCreate(getArgs())) {
            return this.mBinding.getRoot();
        }
        this.mBinding.tvLearnMore.setOnClickListener(WithdrawalBasedLimitsDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.tvDismiss.setOnClickListener(WithdrawalBasedLimitsDialogController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    public void setTextFields(String title, String message, String dismissText, String learnMoreText) {
        this.mBinding.tvMessage.setText(message);
        this.mBinding.tvTitle.setText(title);
        this.mBinding.tvLearnMore.setText(learnMoreText);
        this.mBinding.tvDismiss.setText(dismissText);
    }

    public void routeToAvailableBalance() {
        dismiss(true);
        pushModalController(new AvailableBalanceController(appendModalArgs(new Bundle())));
    }
}
