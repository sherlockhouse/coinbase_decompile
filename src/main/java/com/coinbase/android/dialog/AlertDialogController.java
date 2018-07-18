package com.coinbase.android.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogAlertBinding;
import com.coinbase.android.ui.DialogController;

public abstract class AlertDialogController extends DialogController {
    DialogAlertBinding mBinding;

    public abstract String getDialogTitle();

    public abstract String getMessage();

    public abstract void onUserConfirm();

    public AlertDialogController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        this.mBinding = DialogAlertBinding.inflate(inflater, container, true);
        String title = getDialogTitle();
        if (TextUtils.isEmpty(title)) {
            this.mBinding.tvTitle.setVisibility(8);
        } else {
            this.mBinding.tvTitle.setVisibility(0);
            this.mBinding.tvTitle.setText(title);
        }
        this.mBinding.tvMessage.setText(getMessage());
        this.mBinding.tvOk.setOnClickListener(AlertDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.tvOk.setText(getPositiveButtonText());
        return this.mBinding.getRoot();
    }

    protected int getPositiveButtonText() {
        return R.string.ok;
    }
}
