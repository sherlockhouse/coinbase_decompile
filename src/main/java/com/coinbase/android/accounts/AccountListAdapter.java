package com.coinbase.android.accounts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemAccountBinding;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import java.util.EnumSet;
import java.util.List;
import org.joda.money.Money;

public class AccountListAdapter extends Adapter<ViewHolder> {
    private List<Data> mAccountList = this.mPresenter.getSortedAccountList();
    private Context mContext;
    private MoneyFormatterUtil mMoneyFormatterUtil;
    private AccountListPresenter mPresenter;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemAccountBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemAccountBinding) DataBindingUtil.bind(itemView);
        }

        ListItemAccountBinding getBinding() {
            return this.mBinding;
        }
    }

    public AccountListAdapter(Context context, AccountListPresenter accountListPresenter, MoneyFormatterUtil moneyFormatterUtil) {
        this.mContext = context;
        this.mPresenter = accountListPresenter;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_account, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Money accountBalanceMoney;
        ListItemAccountBinding binding = holder.getBinding();
        Data account = (Data) this.mAccountList.get(position);
        binding.tvAccountListItemName.setText(account.getName());
        if (account.getType() == Type.FIAT) {
            binding.tvAccountListItemBalance.setVisibility(8);
            String fiatSymbol = AccountUtils.getFiatCurrencySymbol(account);
            if (fiatSymbol != null) {
                binding.tvFiatSymbol.setText(fiatSymbol);
            }
            accountBalanceMoney = AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil);
        } else {
            binding.tvFiatSymbol.setText("");
            binding.tvAccountListItemBalance.setVisibility(0);
            binding.tvAccountListItemBalance.setText(this.mMoneyFormatterUtil.formatMoney(AccountUtils.getAccountBalanceMoney(account, this.mMoneyFormatterUtil), EnumSet.of(Options.ROUND_8_DIGITS)));
            accountBalanceMoney = AccountUtils.getAccountNativeBalanceMoney(account, this.mMoneyFormatterUtil);
        }
        if (accountBalanceMoney != null) {
            binding.tvAccountListNativeBalance.setText(this.mMoneyFormatterUtil.formatMoney(accountBalanceMoney, EnumSet.of(Options.ROUND_2_DIGITS)));
            formatAccountBalance(binding.tvAccountListNativeBalance, accountBalanceMoney);
        } else {
            binding.tvAccountListNativeBalance.setText("");
        }
        AccountUtils.setAccountImage(this.mContext, binding.ivAccountListItemIcon, account);
        binding.rlAccount.setOnClickListener(AccountListAdapter$$Lambda$1.lambdaFactory$(this, account));
    }

    private void formatAccountBalance(TextView textView, Money accountBalance) {
        int textColor = R.color.primary_mystique_text_color;
        if (accountBalance != null && accountBalance.isZero()) {
            textColor = R.color.primary_mystique_extra_light_text_color;
        }
        textView.setTextColor(ContextCompat.getColor(this.mContext, textColor));
    }

    public int getItemCount() {
        return this.mAccountList.size();
    }
}
