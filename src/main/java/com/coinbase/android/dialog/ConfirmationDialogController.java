package com.coinbase.android.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogConfirmationBinding;
import com.coinbase.android.ui.DialogController;

public abstract class ConfirmationDialogController extends DialogController {
    DialogConfirmationBinding mBinding;

    public abstract String getMessage();

    public abstract void onUserCancel();

    public abstract void onUserConfirm();

    public ConfirmationDialogController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        this.mBinding = DialogConfirmationBinding.inflate(inflater, container, true);
        if (showTitle()) {
            this.mBinding.tvTitle.setVisibility(0);
            this.mBinding.tvTitle.setText(getTitle());
        }
        this.mBinding.tvText.setText(getMessage());
        this.mBinding.tvCancel.setText(getNegativeButtonText());
        this.mBinding.tvCancel.setOnClickListener(ConfirmationDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.tvOk.setOnClickListener(ConfirmationDialogController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.tvOk.setText(getPositiveButtonText());
        return this.mBinding.getRoot();
    }

    protected int getPositiveButtonText() {
        return R.string.confirm;
    }

    protected int getNegativeButtonText() {
        return R.string.cancel;
    }

    protected boolean showTitle() {
        return false;
    }
}
