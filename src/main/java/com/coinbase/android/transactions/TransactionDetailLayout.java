package com.coinbase.android.transactions;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutTransactionDetailBinding;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.v2.models.account.Data;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.apache.http.util.TextUtils;

@ActivityScope
public class TransactionDetailLayout extends LinearLayout {
    private LayoutTransactionDetailBinding mBinding;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    TransactionDetailPresenter mPresenter;

    public TransactionDetailLayout(Context context) {
        this(context, null);
    }

    public TransactionDetailLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransactionDetailLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutTransactionDetailBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().inject(this);
    }

    void updateView(Data account, com.coinbase.v2.models.transactions.Data transaction) {
        if (account != null && transaction != null) {
            this.mPresenter.setSelectedTransaction(account.getId(), transaction);
            configureHeaderView(transaction, account);
            configureButtons(transaction);
            configureDetailListView(transaction);
        }
    }

    private void configureHeaderView(com.coinbase.v2.models.transactions.Data transaction, Data account) {
        TransactionUtils.setTransactionImage(getContext(), this.mBinding.ivAccountIcon, transaction, AccountUtils.getAccountColor(account));
        this.mBinding.tvCryptoPrice.setText(this.mMoneyFormatterUtil.formatMoney(TransactionUtils.moneyFromAmount(transaction.getAmount()), EnumSet.of(Options.ROUND_8_DIGITS)));
        this.mBinding.tvNativePrice.setText(this.mMoneyFormatterUtil.formatMoney(TransactionUtils.moneyFromNativeAmount(transaction.getNativeAmount()), EnumSet.of(Options.ROUND_4_DIGITS, Options.PREFIX_SIGNED)));
    }

    private void configureButtons(com.coinbase.v2.models.transactions.Data transaction) {
        this.mBinding.btnClose.setOnClickListener(TransactionDetailLayout$$Lambda$1.lambdaFactory$(this));
        ActionType actionType = this.mPresenter.getActionButtonType(transaction);
        switch (actionType) {
            case CANCEL:
                String buttonText = getResources().getString(R.string.cancel_transaction);
                this.mBinding.btnAction.setVisibility(0);
                this.mBinding.btnAction.setText(buttonText);
                this.mBinding.btnAction.setOnClickListener(TransactionDetailLayout$$Lambda$2.lambdaFactory$(this, actionType));
                return;
            default:
                this.mBinding.btnAction.setVisibility(8);
                return;
        }
    }

    private void configureDetailListView(final com.coinbase.v2.models.transactions.Data transaction) {
        List<HashMap<String, String>> transactionDetailList = new ArrayList();
        final String price = this.mPresenter.getDisplayableCryptoPrice(transaction);
        if (!TextUtils.isEmpty(price)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        final String paymentMethod = this.mPresenter.getDisplayablePaymentMethodName(transaction);
        if (!TextUtils.isEmpty(paymentMethod)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        final String fee = this.mPresenter.getDisplayableFee(transaction);
        if (!TextUtils.isEmpty(fee)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        final String date = this.mPresenter.getDisplayableDate(transaction);
        if (!TextUtils.isEmpty(date)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        final String notes = this.mPresenter.getDisplayableNotes(transaction);
        if (!TextUtils.isEmpty(notes)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        final String status = this.mPresenter.getDisplayableStatus(transaction);
        if (!TextUtils.isEmpty(status)) {
            transactionDetailList.add(new HashMap<String, String>() {
            });
        }
        this.mBinding.rvDetails.setAdapter(new TransactionDetailListAdapter(this.mPresenter, transactionDetailList));
        this.mBinding.rvDetails.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mBinding.rvDetails.setHasFixedSize(true);
    }
}
