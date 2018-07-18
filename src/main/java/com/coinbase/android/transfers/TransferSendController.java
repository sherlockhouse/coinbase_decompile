package com.coinbase.android.transfers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerTransferSendBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.v2.models.transactions.Data;
import com.google.gson.Gson;
import javax.inject.Inject;
import org.joda.money.Money;
import permissions.dispatcher.PermissionRequest;

@ControllerScope
public class TransferSendController extends ActionBarController {
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    ControllerTransferSendBinding mBinding;
    @Inject
    TransferSendPresenter mPresenter;
    @Inject
    SuccessRouter mSuccessRouter;

    public TransferSendController(Bundle args) {
        super(args);
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getApplicationContext(), R.color.white);
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerTransferSendBinding) DataBindingUtil.inflate(inflater, R.layout.controller_transfer_send, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addTransferSendControllerSubcomponent(new TransferSendPresenterModule(this.mBinding.vTransferSendLayout), new TransferSendControllerModule(this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.mBinding.vTransferSendLayout.onCreate(this.mPresenter, getArgs(), TransferSendController$$Lambda$1.lambdaFactory$(this), TransferSendController$$Lambda$2.lambdaFactory$(this), TransferSendController$$Lambda$3.lambdaFactory$(this), TransferSendController$$Lambda$4.lambdaFactory$(this), TransferSendController$$Lambda$5.lambdaFactory$(this), TransferSendController$$Lambda$6.lambdaFactory$(this), TransferSendController$$Lambda$7.lambdaFactory$(this), TransferSendController$$Lambda$8.lambdaFactory$(this));
        this.mBinding.vTransferSendLayout.onViewCreated();
        return this.mBinding.getRoot();
    }

    static /* synthetic */ Void lambda$onCreateView$2(TransferSendController this_, String recipient, String id, Money amount, String fee, Boolean isSendMax, String notes, String exchangeId, Integer onActivityResultTarget) {
        Bundle args = new Bundle();
        args.putString("ConfirmTransferFragment_Counteryparty", recipient);
        args.putSerializable("ConfirmTransferFragment_Amount", amount);
        args.putString("ConfirmSendTransferFragment_Fee", fee);
        args.putString("ConfirmTransferFragment_Notes", notes);
        args.putBoolean("ConfirmTransferFragment_Is_Send_Max", isSendMax.booleanValue());
        args.putString("ConfirmTransferFragment_Account", id);
        args.putString("ConfirmTransferFragment_Exchange_Id", exchangeId);
        args.putInt(ConfirmSendTransferController.TARGET_REQUEST_CODE, onActivityResultTarget.intValue());
        ConfirmSendTransferController controller = new ConfirmSendTransferController(this_.appendModalArgs(args));
        controller.setTargetController(this_);
        this_.pushDialogModalController(controller);
        return null;
    }

    static /* synthetic */ Void lambda$onCreateView$3(TransferSendController this_, Data transaction, String id) {
        Bundle args = new Bundle();
        args.putString(DelayedTransactionDialogPresenter.TRANSACTION, new Gson().toJson((Object) transaction));
        args.putString(DelayedTransactionDialogPresenter.ACCOUNT, id);
        this_.pushDialogModalController(new DelayedTransactionDialogController(this_.appendModalArgs(args)));
        return null;
    }

    protected void onShow() {
        super.onShow();
        if (this.mPresenter != null) {
            this.mPresenter.onShow();
        }
    }

    protected void onHide() {
        super.onHide();
        if (this.mPresenter != null) {
            this.mPresenter.onHide();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mPresenter == null) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (!this.mPresenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onShowOptionsMenu(Menu menu) {
        if (getActivity() != null && getResources() != null) {
            getActivity().getMenuInflater().inflate(R.menu.controller_transfer, menu);
            menu.getItem(0).getIcon().setColorFilter(getResources().getColor(getToolbarTextColor()), Mode.SRC_ATOP);
        }
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_transfer_send:
                this.mPresenter.onSendMenuClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    void showRationaleForCamera(PermissionRequest request) {
        this.mBinding.vTransferSendLayout.showRationaleForCamera(request);
    }

    void showDeniedForCamera() {
        this.mBinding.vTransferSendLayout.showDeniedForCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TransferSendControllerPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    void startQrScan() {
        this.mPresenter.onStartQrScanClicked();
    }

    public void onContactsPermissionGranted() {
        this.mBinding.vTransferSendLayout.onContactsPermissionGranted();
    }

    void showDeniedForReadContacts() {
        this.mBinding.vTransferSendLayout.showDeniedForReadContacts();
    }
}
