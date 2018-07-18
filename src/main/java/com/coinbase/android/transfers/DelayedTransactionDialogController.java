package com.coinbase.android.transfers;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.dialog.ConfirmationDialogController;
import com.coinbase.android.ui.SuccessRouter;
import javax.inject.Inject;

@ControllerScope
public class DelayedTransactionDialogController extends ConfirmationDialogController {
    @Inject
    DelayedTransactionDialogPresenter mPresenter;
    @Inject
    SuccessRouter mSuccessRouter;

    public DelayedTransactionDialogController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addDelayedTransactionDialogControllerSubcomponent(new DelayedTransactionsControllerModule(this)).inject(this);
        this.mPresenter.onCreate(getArgs());
        return super.onCreateView(inflater, container);
    }

    public String getMessage() {
        return getApplicationContext().getString(R.string.delayed_tx_dialog_message);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.delayed_tx_dialog_title));
    }

    protected boolean showTitle() {
        return true;
    }

    public void onUserCancel() {
        dismiss();
    }

    public void onUserConfirm() {
        this.mPresenter.onConfirm();
        dismiss();
        this.mSuccessRouter.routeSuccess();
    }

    protected int getPositiveButtonText() {
        return R.string.delayed_tx_dialog_ok;
    }
}
