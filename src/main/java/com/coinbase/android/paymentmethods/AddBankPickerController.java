package com.coinbase.android.paymentmethods;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerBankPickerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import javax.inject.Inject;

@ControllerScope
public class AddBankPickerController extends ActionBarController implements AddBankPickerScreen {
    ControllerBankPickerBinding mBinding;
    @Inject
    AddBankPickerPresenter mPresenter;

    public AddBankPickerController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerBankPickerBinding) DataBindingUtil.inflate(inflater, R.layout.controller_bank_picker, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addBankPickerControllerSubcomponent(new AddBankPickerPresenterModule(this)).inject(this);
        this.mBinding.btnConnectInstantly.setOnClickListener(AddBankPickerController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnConnectManually.setOnClickListener(AddBankPickerController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.connect_bank));
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
}
