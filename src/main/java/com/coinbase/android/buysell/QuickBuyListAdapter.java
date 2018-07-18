package com.coinbase.android.buysell;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemQuickBuyBinding;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class QuickBuyListAdapter extends Adapter<BuyQuickButtonViewHolder> {
    private static final Set<Options> FORMAT_OPTIONS = EnumSet.of(Options.STRIP_TRAILING_ZEROS);
    private String mCurrencyCode;
    private MoneyFormatterUtil mMoneyFormatterUtil;
    private QuickBuyConnector mQuickBuyConnector;
    private List<String> mQuickBuyValueList = QuickBuyConnector.QUICK_BUY_VALUES;

    static class BuyQuickButtonViewHolder extends ViewHolder {
        private ListItemQuickBuyBinding mBinding;

        public BuyQuickButtonViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemQuickBuyBinding) DataBindingUtil.bind(itemView);
        }

        ListItemQuickBuyBinding getBinding() {
            return this.mBinding;
        }
    }

    QuickBuyListAdapter(String currencyCode, MoneyFormatterUtil moneyFormatterUtil, QuickBuyConnector quickBuyConnector) {
        this.mCurrencyCode = currencyCode;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mQuickBuyConnector = quickBuyConnector;
    }

    public BuyQuickButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuyQuickButtonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_quick_buy, parent, false));
    }

    public void onBindViewHolder(BuyQuickButtonViewHolder holder, int position) {
        ListItemQuickBuyBinding binding = holder.getBinding();
        String amount = (String) this.mQuickBuyValueList.get(position);
        binding.tvQuickBuy.setText(this.mMoneyFormatterUtil.formatMoney(this.mMoneyFormatterUtil.moneyFromValue(this.mCurrencyCode, amount), FORMAT_OPTIONS));
        binding.tvQuickBuy.setOnClickListener(QuickBuyListAdapter$$Lambda$1.lambdaFactory$(this, amount));
    }

    public int getItemCount() {
        return this.mQuickBuyValueList.size();
    }
}
