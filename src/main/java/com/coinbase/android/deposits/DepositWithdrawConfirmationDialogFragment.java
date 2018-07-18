package com.coinbase.android.deposits;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.LinearLayout;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogConfirmBuysellBinding;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.transfers.Amount;
import com.coinbase.v2.models.transfers.Transfer;
import com.google.gson.Gson;
import javax.inject.Inject;

public abstract class DepositWithdrawConfirmationDialogFragment extends DialogFragment {
    public static final String ACCOUNT = "BUY_SELL_CONFIRMATION_DIALOG_ACCOUNT";
    public static final String PAYMENT_METHOD = "BUY_SELL_CONFIRMATION_DIALOG_PAYMENT_METHOD";
    public static final String TRANSFER = "BUY_SELL_CONFIRMATION_DIALOG_TRANSFER";
    private DialogConfirmBuysellBinding binding;
    protected Data mAccount;
    @Inject
    FiatTransactionsConnector mFiatTransactionsConnector;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    protected com.coinbase.v2.models.paymentMethods.Data mPaymentMethod;
    protected Transfer mTransfer;

    public abstract String getAmountMessage();

    public abstract String getDialogTitle();

    public abstract int getFirstImage();

    public abstract String getPaymentMessage();

    public abstract String getPayoutMessage();

    public abstract int getSecondImage();

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int i;
        int i2 = 0;
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        Gson gson = new Gson();
        this.mTransfer = (Transfer) gson.fromJson(getArguments().getString(TRANSFER), Transfer.class);
        this.mAccount = (Data) gson.fromJson(getArguments().getString(ACCOUNT), Data.class);
        this.mPaymentMethod = (com.coinbase.v2.models.paymentMethods.Data) gson.fromJson(getArguments().getString(PAYMENT_METHOD), com.coinbase.v2.models.paymentMethods.Data.class);
        this.binding = (DialogConfirmBuysellBinding) DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.dialog_confirm_buysell, null, false);
        this.binding.tvTitle.setText(getDialogTitle());
        this.binding.ivFirstIcon.setImageResource(getFirstImage());
        this.binding.ivSecondIcon.setImageResource(getSecondImage());
        this.binding.tvAmount.setText(getAmountMessage());
        this.binding.tvPaymentMethod.setText(getPaymentMessage());
        this.binding.tvPayoutDate.setText(getPayoutMessage());
        View view = this.binding.vDivider;
        if (showPayoutDate()) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        LinearLayout linearLayout = this.binding.llPayoutContainer;
        if (!showPayoutDate()) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
        Builder builder = new Builder(getActivity());
        builder.setView(this.binding.getRoot()).setPositiveButton(R.string.confirm, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DepositWithdrawConfirmationDialogFragment.this.onUserConfirm();
                DepositWithdrawConfirmationDialogFragment.this.mFiatTransactionsConnector.get().onNext(DepositWithdrawConfirmationDialogFragment.this.mTransfer);
            }
        }).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(-1).setTextColor(getResources().getColor(R.color.primary));
        ((AlertDialog) getDialog()).getButton(-2).setTextColor(getResources().getColor(R.color.grey_button_text));
    }

    protected String getFormattedTotal(Transfer transfer) {
        Amount amount = transfer.getData().getAmount();
        return this.mMoneyFormatterUtil.formatMoney(this.mMoneyFormatterUtil.moneyFromValue(amount.getCurrency(), amount.getAmount()));
    }

    public void onUserConfirm() {
    }

    public boolean showPayoutDate() {
        return true;
    }
}
