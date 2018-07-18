package com.coinbase.android.deposits.fiat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerWithdrawdepositSuccessBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import javax.inject.Inject;

@ControllerScope
public class WithdrawDepositSuccessController extends ActionBarController implements WithdrawDepositSuccessScreen {
    private ControllerWithdrawdepositSuccessBinding mBinding;
    @Inject
    protected BottomNavigationConnector mBottomNavigationConnector;
    @Inject
    WithdrawDepositSuccessPresenter mPresenter;

    public WithdrawDepositSuccessController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerWithdrawdepositSuccessBinding) DataBindingUtil.inflate(inflater, R.layout.controller_withdrawdeposit_success, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addWithdrawDepositSuccessControllerSubcomponent(new WithdrawDepositSuccessPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceHideHomeDisplay(true);
        setForceDisableToolbarThemeUpdate(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btGotoAccount.setOnClickListener(WithdrawDepositSuccessController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    public void setSuccessMessage(String message) {
        this.mBinding.tvSuccessText.setText(message);
    }

    public void setAmountTransferred(String amountTransferred) {
        this.mBinding.tvAmount.setText(amountTransferred);
    }

    public void closeModal() {
        ControllerLifeCycle controllerLifeCycle = getControllerLifeCycle();
        if (controllerLifeCycle instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) controllerLifeCycle).closeModal();
        }
    }
}
