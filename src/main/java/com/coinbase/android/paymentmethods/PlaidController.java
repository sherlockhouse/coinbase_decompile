package com.coinbase.android.paymentmethods;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerPlaidBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

@ControllerScope
public class PlaidController extends ActionBarController implements PlaidScreen {
    ControllerPlaidBinding mBinding;
    @Inject
    PlaidPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Inject
    public PlaidController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerPlaidBinding) DataBindingUtil.inflate(inflater, R.layout.controller_plaid, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addPlaidControllerSubcomponent(new PlaidPresenterModule(this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        return this.mBinding.getRoot();
    }

    protected int getThemeColor() {
        return this.mPresenter.getThemeColor();
    }

    public WebView getWebView() {
        return this.mBinding.wvPlaid;
    }

    public void closeModal() {
        ControllerLifeCycle controllerLifeCycle = getControllerLifeCycle();
        if (controllerLifeCycle instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) controllerLifeCycle).closeModal();
        }
    }

    public void showProgressDialog() {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getResources().getString(R.string.verifying));
    }

    public void hideProgressDialog() {
        if (this.mProgressDialog != null) {
            Utils.dismissDialog(this.mProgressDialog);
        }
    }

    public void closeForm() {
        getRouter().popCurrentController();
    }
}
