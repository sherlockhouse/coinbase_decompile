package com.coinbase.android.transactions;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemTransactionBinding;
import com.coinbase.android.databinding.ListItemTransactionFooterBinding;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.android.utils.TransactionUtils.TransactionStatus;
import com.coinbase.v2.models.account.Data.Type;
import com.coinbase.v2.models.transactions.Data;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.joda.money.BigMoney;

public class TransactionListAdapter extends Adapter<ViewHolder> {
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_ITEM = 0;
    private Context mContext;
    private MoneyFormatterUtil mMoneyFormatterUtil;
    private TransactionListPresenter mPresenter;
    private List<Data> mTransactionList = this.mPresenter.getTransactionDataList();

    private class FooterViewHolder extends ViewHolder {
        private ListItemTransactionFooterBinding mBinding;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemTransactionFooterBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemTransactionFooterBinding getBinding() {
            return this.mBinding;
        }
    }

    private class ItemViewHolder extends ViewHolder {
        private ListItemTransactionBinding mBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemTransactionBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemTransactionBinding getBinding() {
            return this.mBinding;
        }
    }

    public TransactionListAdapter(Context context, TransactionListPresenter presenter, MoneyFormatterUtil moneyFormatterUtil) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false));
            case 1:
                return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction_footer, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ListItemTransactionBinding binding = ((ItemViewHolder) holder).getBinding();
                Data transaction = (Data) this.mTransactionList.get(position);
                com.coinbase.v2.models.account.Data selectedAccount = this.mPresenter.getSelectedAccount();
                configureImageView(binding, transaction);
                configureTitleView(binding, transaction);
                configureSummaryView(binding, transaction);
                configureAmountView(binding, selectedAccount.getType(), transaction);
                configureStatusView(binding, selectedAccount.getType(), transaction);
                binding.rlTransactionsItem.setOnClickListener(TransactionListAdapter$$Lambda$1.lambdaFactory$(this, transaction));
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        int count = this.mTransactionList.size();
        return isFooterVisible() ? count + 1 : count;
    }

    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return 1;
        }
        return 0;
    }

    private boolean isFooterVisible() {
        return this.mPresenter.canLoadMoreTransactions();
    }

    private boolean isPositionFooter(int position) {
        if (isFooterVisible() && position == this.mTransactionList.size()) {
            return true;
        }
        return false;
    }

    private void configureImageView(ListItemTransactionBinding binding, Data transaction) {
        binding.ivTransactionProfile.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.default_circle));
        TransactionUtils.setTransactionImage(this.mContext, binding.ivTransactionProfile, transaction, AccountUtils.getAccountColor(this.mPresenter.getSelectedAccount()));
    }

    private void configureTitleView(ListItemTransactionBinding binding, Data transaction) {
        binding.tvTransactionTitle.setText(transaction.getDetails() != null ? transaction.getDetails().getTitle() : "");
    }

    private void configureSummaryView(ListItemTransactionBinding binding, Data transaction) {
        binding.tvTransactionSummary.setText(transaction.getDetails() != null ? transaction.getDetails().getSubtitle() : "");
    }

    private void configureAmountView(ListItemTransactionBinding binding, Type type, Data transaction) {
        BigMoney amount = TransactionUtils.moneyFromAmount(transaction.getAmount());
        Set<Options> optionSet = EnumSet.of(Options.PREFIX_SIGNED);
        switch (type) {
            case FIAT:
                break;
            default:
                optionSet.add(Options.STRIP_TRAILING_ZEROS);
                break;
        }
        binding.tvTransactionAmount.setText(this.mMoneyFormatterUtil.formatMoney(amount, optionSet));
        binding.tvTransactionAmount.setTextColor(ContextCompat.getColor(this.mContext, amount.isPositive() ? R.color.transaction_positive : R.color.primary_mystique_extra_light_text_color));
    }

    private void configureStatusView(ListItemTransactionBinding binding, Type type, Data transaction) {
        TransactionStatus status = TransactionStatus.toStatus(transaction.getStatus());
        switch (status) {
            case PENDING:
                binding.transactionStatusView.setVisibility(0);
                Drawable background = binding.transactionStatusView.getBackground().mutate();
                if (background instanceof GradientDrawable) {
                    ((GradientDrawable) background).setColor(ContextCompat.getColor(this.mContext, R.color.transaction_orange));
                }
                String formattedStatus = status.getDisplayValue();
                if (!(transaction.getNetwork() == null || transaction.getNetwork().getConfirmations() == null || transaction.getNetwork().getConfirmations().intValue() <= 1)) {
                    formattedStatus = String.format(this.mContext.getResources().getString(R.string.transaction_num_confirmation), new Object[]{transaction.getNetwork().getConfirmations()});
                }
                binding.tvTransactionStatus.setText(formattedStatus);
                return;
            default:
                switch (type) {
                    case FIAT:
                        binding.tvTransactionStatus.setText("");
                        binding.transactionStatusView.setVisibility(8);
                        return;
                    default:
                        BigMoney amount = TransactionUtils.moneyFromNativeAmount(transaction.getNativeAmount());
                        if (amount != null) {
                            binding.tvTransactionStatus.setText(this.mMoneyFormatterUtil.formatMoney(amount, EnumSet.of(Options.PREFIX_SIGNED)));
                            binding.transactionStatusView.setVisibility(8);
                            return;
                        }
                        return;
                }
        }
    }
}
