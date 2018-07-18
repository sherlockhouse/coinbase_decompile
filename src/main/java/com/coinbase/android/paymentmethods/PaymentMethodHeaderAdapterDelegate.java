package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemPaymentMethodHeaderBinding;
import com.coinbase.android.utils.PaymentMethodUtils.PaymentGroup;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class PaymentMethodHeaderAdapterDelegate extends AdapterDelegate<List<Object>> {
    private final Activity mActivity;

    class PaymentMethodHeaderViewHolder extends ViewHolder {
        private ListItemPaymentMethodHeaderBinding mPaymentMethodHeaderBinding;

        PaymentMethodHeaderViewHolder(ListItemPaymentMethodHeaderBinding binding) {
            super(binding.getRoot());
            this.mPaymentMethodHeaderBinding = binding;
        }

        public void bind(PaymentGroup type) {
            this.mPaymentMethodHeaderBinding.tvListItemPaymentMethodHeader.setText(getHeaderForType(type));
        }

        String getHeaderForType(PaymentGroup group) {
            switch (group) {
                case BANK:
                    return PaymentMethodHeaderAdapterDelegate.this.mActivity.getString(R.string.bank_payment_item_header);
                case CARD:
                    return PaymentMethodHeaderAdapterDelegate.this.mActivity.getString(R.string.card_payment_item_header);
                default:
                    return PaymentMethodHeaderAdapterDelegate.this.mActivity.getString(R.string.other_payment_item_header);
            }
        }
    }

    public PaymentMethodHeaderAdapterDelegate(Activity activity) {
        this.mActivity = activity;
    }

    protected boolean isForViewType(List<Object> items, int position) {
        return items.get(position) instanceof PaymentGroup;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PaymentMethodHeaderViewHolder((ListItemPaymentMethodHeaderBinding) DataBindingUtil.bind(this.mActivity.getLayoutInflater().inflate(R.layout.list_item_payment_method_header, parent, false)));
    }

    protected void onBindViewHolder(List<Object> items, int position, ViewHolder holder, List<Object> list) {
        ((PaymentMethodHeaderViewHolder) holder).bind((PaymentGroup) items.get(position));
    }
}
