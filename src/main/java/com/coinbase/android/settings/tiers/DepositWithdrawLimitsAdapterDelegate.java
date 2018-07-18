package com.coinbase.android.settings.tiers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DepositWithdrawLimitBinding;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.api.internal.models.tiers.BuySellLimit;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.joda.money.Money;

public class DepositWithdrawLimitsAdapterDelegate extends AdapterDelegate<List<Object>> {
    private final AutoResizeTextViewAdjustor mAutoResizeTextViewAdjustor;
    private Context mContext;
    private MoneyFormatterUtil mMoneyFormatterUtil;

    private class DepositWithdrawLimitViewHolder extends ViewHolder {
        private DepositWithdrawLimitBinding mBinding;

        public DepositWithdrawLimitViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (DepositWithdrawLimitBinding) DataBindingUtil.bind(itemView);
        }

        public DepositWithdrawLimitBinding getBinding() {
            return this.mBinding;
        }
    }

    enum Type {
        ACH("ach", R.drawable.tiers_bank_limits),
        CARD("credit_debit_card", R.drawable.tiers_card_limits),
        WIRE("wire", R.drawable.wire_item);
        
        private final int mDrawableId;
        private final String mName;

        private Type(String name, int drawableId) {
            this.mName = name;
            this.mDrawableId = drawableId;
        }

        int getDrawable() {
            return this.mDrawableId;
        }

        static Type fromString(String typeStr) {
            for (Type val : values()) {
                if (TextUtils.equals(val.mName, typeStr)) {
                    return val;
                }
            }
            return ACH;
        }
    }

    public DepositWithdrawLimitsAdapterDelegate(Context context, MoneyFormatterUtil moneyFormatterUtil, AutoResizeTextViewAdjustor autoResizeTextViewAdjustor) {
        this.mContext = context;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mAutoResizeTextViewAdjustor = autoResizeTextViewAdjustor;
    }

    protected boolean isForViewType(List<Object> items, int position) {
        return items.get(position) instanceof BuySellLimit;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        DepositWithdrawLimitViewHolder holder = new DepositWithdrawLimitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.deposit_withdraw_limit, parent, false));
        this.mAutoResizeTextViewAdjustor.setReferenceView(holder.getBinding().tvAmount);
        this.mAutoResizeTextViewAdjustor.addViews(holder.getBinding().tvAmount);
        return holder;
    }

    protected void onBindViewHolder(List<Object> items, int position, ViewHolder holder, List<Object> list) {
        BuySellLimit item = (BuySellLimit) items.get(position);
        DepositWithdrawLimitBinding binding = ((DepositWithdrawLimitViewHolder) holder).getBinding();
        binding.tvDisplayName.setText(item.getName());
        binding.ivImage.setImageDrawable(ContextCompat.getDrawable(this.mContext, getImageFromType(item.getType())));
        String amount = item.getAmount();
        String currency = item.getCurrency() != null ? item.getCurrency().getCode() : null;
        if (amount != null && currency != null) {
            String formattedAmount;
            Set<Options> moneyFormatOption = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
            Money lifetimeAmount = this.mMoneyFormatterUtil.moneyFromValue(currency, amount);
            if (lifetimeAmount == null) {
                formattedAmount = "";
            } else {
                formattedAmount = this.mMoneyFormatterUtil.formatMoney(lifetimeAmount, moneyFormatOption);
            }
            boolean enabled = item.getEnabled() != null && item.getEnabled().booleanValue();
            if (enabled) {
                if (TextUtils.isEmpty(item.getPerUnit())) {
                    binding.tvAmount.setText(formattedAmount);
                    return;
                } else {
                    binding.tvAmount.setText(this.mContext.getString(R.string.amount_with_period, new Object[]{formattedAmount, item.getPerUnit()}));
                    return;
                }
            }
            String statusText = item.getStatusText();
            if (!TextUtils.isEmpty(statusText)) {
                binding.tvAmount.setText(statusText);
            }
        }
    }

    private int getImageFromType(String type) {
        return Type.fromString(type).getDrawable();
    }
}
