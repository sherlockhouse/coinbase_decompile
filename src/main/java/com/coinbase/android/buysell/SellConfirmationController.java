package com.coinbase.android.buysell;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

@ControllerScope
public class SellConfirmationController extends AbstractBuySellConfirmationController implements SellConfirmationScreen {
    private ProgressDialog mDialog;
    @Inject
    SellConfirmationPresenter mPresenter;

    public /* bridge */ /* synthetic */ void displayFeeHelpUrl(String str) {
        super.displayFeeHelpUrl(str);
    }

    public /* bridge */ /* synthetic */ void hideWarningMessage() {
        super.hideWarningMessage();
    }

    public /* bridge */ /* synthetic */ void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public /* bridge */ /* synthetic */ void onConfirmCompleted() {
        super.onConfirmCompleted();
    }

    public /* bridge */ /* synthetic */ void setButtonText(String str) {
        super.setButtonText(str);
    }

    public /* bridge */ /* synthetic */ void setCryptoAmount(String str) {
        super.setCryptoAmount(str);
    }

    public /* bridge */ /* synthetic */ void showWarningMessage(String str) {
        super.showWarningMessage(str);
    }

    public SellConfirmationController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addSellConfirmationControllerSubcomponent(new SellConfirmationPresenterModule(this, this)).inject(this);
        return super.onCreateView(inflater, container);
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void showProgressDialog(String message) {
        this.mDialog = ProgressDialog.show(getActivity(), null, message);
    }

    public void hideProgressDialog() {
        Utils.dismissDialog(this.mDialog);
    }

    public void showSellButtonProgress() {
        disableConfirmButtonAndShowProgress();
    }

    public void hideSellButtonProgress() {
        enableConfirmButtonAndHideProgress();
    }

    public void showProgressBar() {
        showProgressBarOverlay();
    }

    public void hideProgressBar() {
        hideProgressBarOverlay();
    }
}
