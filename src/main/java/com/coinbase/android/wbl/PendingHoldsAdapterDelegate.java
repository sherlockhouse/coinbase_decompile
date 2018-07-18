package com.coinbase.android.wbl;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.databinding.PendingHoldBinding;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.api.internal.models.wbl.Amount;
import com.coinbase.api.internal.models.wbl.PendingHold;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PendingHoldsAdapterDelegate extends AdapterDelegate<List<PendingHold>> {
    private static final SimpleDateFormat DATE_IN_FORM = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());
    private static final SimpleDateFormat DATE_OUT_FORM = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(PendingHoldsAdapterDelegate.class);
    private final MoneyFormatterUtil mMoneyFormatterUtil;

    private class PendingHoldViewHolder extends ViewHolder {
        private PendingHoldBinding mBinding;

        public PendingHoldViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (PendingHoldBinding) DataBindingUtil.bind(itemView);
        }

        public PendingHoldBinding getBinding() {
            return this.mBinding;
        }
    }

    public PendingHoldsAdapterDelegate(Context context, MoneyFormatterUtil moneyFormatterUtil) {
        this.mContext = context;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
    }

    protected boolean isForViewType(List<PendingHold> items, int position) {
        return items.get(position) instanceof PendingHold;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PendingHoldViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_hold, parent, false));
    }

    protected void onBindViewHolder(List<PendingHold> items, int position, ViewHolder holder, List<Object> list) {
        PendingHold item = (PendingHold) items.get(position);
        PendingHoldBinding binding = ((PendingHoldViewHolder) holder).getBinding();
        Amount amount = item.getAmount();
        if (amount == null || TextUtils.isEmpty(amount.getAmount()) || TextUtils.isEmpty(amount.getCurrency()) || TextUtils.isEmpty(item.getDate()) || TextUtils.isEmpty(item.getAvailableIn())) {
            binding.rlSetting.setVisibility(8);
            return;
        }
        binding.rlSetting.setVisibility(0);
        Set<Options> moneyFormatOption = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
        String formattedAmount = this.mMoneyFormatterUtil.formatMoney(this.mMoneyFormatterUtil.moneyFromValue(amount.getCurrency(), amount.getAmount()), moneyFormatOption);
        try {
            String formattedDate = DATE_OUT_FORM.format(DATE_IN_FORM.parse(item.getDate()));
            binding.tvHoldFromDate.setText(this.mContext.getString(R.string.available_balance_hold_from, new Object[]{formattedDate}));
        } catch (ParseException e) {
            this.mLogger.error("Parse exception parsing date", e);
            binding.tvHoldFromDate.setText("");
        }
        binding.tvAmount.setText(formattedAmount);
        binding.tvAvailableInText.setText(this.mContext.getString(R.string.available_balance_available_in, new Object[]{item.getAvailableIn()}));
    }
}
