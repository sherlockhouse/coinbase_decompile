package com.coinbase.android.buysell;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountTransactionsController;
import com.coinbase.android.accounts.AccountTransactionsPresenter;
import com.coinbase.android.databinding.ControllerBuysellSuccessBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ControllerLifeCycle;
import com.coinbase.android.ui.ModalControllerLifeCycle;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class BuySellSuccessController extends ActionBarController implements BuySellSuccessScreen {
    ControllerBuysellSuccessBinding mBinding;
    @Inject
    protected BottomNavigationConnector mBottomNavigationConnector;
    @Inject
    BuySellSuccessPresenter mPresenter;

    public BuySellSuccessController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerBuysellSuccessBinding) DataBindingUtil.inflate(inflater, R.layout.controller_buysell_success, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addBuySellSuccessControllerSubcomponent(new BuySellSuccessPresenterModule(this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceHideHomeDisplay(true);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.btGotoAccount.setOnClickListener(BuySellSuccessController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        this.mPresenter.onAccountClicked();
        return true;
    }

    public void gotoAccount(Data account) {
        closeModal();
        Bundle args = new Bundle();
        if (account != null) {
            args.putString(AccountTransactionsPresenter.ACCOUNT_DATA, new Gson().toJson((Object) account));
        }
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(((PageDestination) this.mBottomNavigationConnector.get().getValue()).getBottomNavigationItem()).setBottomNavigationItem(Type.ACCOUNTS).setPushPageController(new AccountTransactionsController(appendPageArgs(args))).build());
        Utils.showRateAppDialog(getActivity(), MixpanelTracking.PROPERTY_VALUE_BUY_SELL_SUCCESS_VIEW);
    }

    public void closeModal() {
        ControllerLifeCycle controllerLifeCycle = getControllerLifeCycle();
        if (controllerLifeCycle instanceof ModalControllerLifeCycle) {
            ((ModalControllerLifeCycle) controllerLifeCycle).closeModal();
        }
    }

    public void setSuccessMessage(String message) {
        this.mBinding.tvSuccessText.setText(message);
    }

    public void setCryptoAmount(String amount) {
        this.mBinding.tvAmount.setText(amount);
    }
}
