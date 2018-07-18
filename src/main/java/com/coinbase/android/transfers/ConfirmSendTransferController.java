package com.coinbase.android.transfers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerDialogConfirmSendBinding;
import com.coinbase.android.ui.DialogController;
import com.coinbase.android.utils.RoundedTransformation;
import com.coinbase.android.utils.Utils;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

@ControllerScope
public class ConfirmSendTransferController extends DialogController implements ConfirmSendTransferScreen {
    public static final String TARGET_REQUEST_CODE = "target_request_code";
    public ProgressDialog dialog;
    ControllerDialogConfirmSendBinding mBinding;
    @Inject
    ConfirmSendTransferPresenter mPresenter;
    private int mTargetRequestCode;

    public ConfirmSendTransferController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addConfirmSendTransferControllerSubcomponent(new ConfirmSendTransferPresenterModule(this)).inject(this);
        this.mTargetRequestCode = getArgs().getInt(TARGET_REQUEST_CODE);
        this.mBinding = ControllerDialogConfirmSendBinding.inflate(inflater, container, true);
        this.mPresenter.onCreate(getArgs());
        this.mBinding.tvConfirm.setOnClickListener(ConfirmSendTransferController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.tvDismiss.setOnClickListener(ConfirmSendTransferController$$Lambda$2.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$inflateContent$0(ConfirmSendTransferController this_, View v) {
        this_.dismiss();
        this_.mPresenter.onUserConfirm();
    }

    public void setMessage(Spanned message) {
        this.mBinding.tvConfirmTransferText.setText(message);
    }

    public void showCounterParty(String email) {
        Picasso.with(getActivity()).load(Utils.getGravatarUrl(email)).error(R.drawable.circle_blue).placeholder(R.drawable.circle_blue).transform(new RoundedTransformation(60, 0)).into(this.mBinding.ivConfirmTransferProfile);
    }

    public void showTransferSendProgress() {
        this.dialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.transfer_send_progress));
    }

    public void dismissDialog() {
        Utils.dismissDialog(this.dialog);
    }

    public void handleRequestError(Intent result, int code) {
        if (getTargetController() != null) {
            getTargetController().onActivityResult(this.mTargetRequestCode, code, result);
        }
    }

    public void handleSuccess(Intent result) {
        if (getTargetController() != null) {
            getTargetController().onActivityResult(this.mTargetRequestCode, -1, result);
        }
    }
}
