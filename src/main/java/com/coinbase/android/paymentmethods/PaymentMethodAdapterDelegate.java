package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemPaymentMethodBinding;
import com.coinbase.android.ui.ExpandTouchDelegate;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class PaymentMethodAdapterDelegate extends AdapterDelegate<List<Object>> {
    private final Activity mActivity;
    private final PaymentMethodsPresenter mPresenter;

    class PaymentMethodViewHolder extends ViewHolder {
        private ListItemPaymentMethodBinding mPaymentMethodBinding;

        PaymentMethodViewHolder(ListItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.mPaymentMethodBinding = binding;
        }

        public void bind(Data method) {
            int i;
            this.mPaymentMethodBinding.tvPaymentMethodRemove.setOnClickListener(PaymentMethodAdapterDelegate$PaymentMethodViewHolder$$Lambda$1.lambdaFactory$(this, method));
            this.mPaymentMethodBinding.ivPaymentIcon.setImageResource(PaymentMethodAdapterDelegate.this.getResourceForType(method.getType()));
            Pair<String, String> nameAndMaskedNumber = PaymentMethodUtils.applyPaymentMethodNameNewlineTransformation(method.getName());
            if (nameAndMaskedNumber == null) {
                this.mPaymentMethodBinding.tvPaymentMethodName.setText(method.getName());
                this.mPaymentMethodBinding.tvPaymentMethodNameSuffix.setVisibility(8);
            } else {
                this.mPaymentMethodBinding.tvPaymentMethodName.setText((CharSequence) nameAndMaskedNumber.first);
                this.mPaymentMethodBinding.tvPaymentMethodNameSuffix.setVisibility(0);
                this.mPaymentMethodBinding.tvPaymentMethodNameSuffix.setText((CharSequence) nameAndMaskedNumber.second);
            }
            TextView textView = this.mPaymentMethodBinding.tvPaymentMethodVerify;
            if (method.getVerified().booleanValue()) {
                i = 8;
            } else {
                i = 0;
            }
            textView.setVisibility(i);
            if (!method.getVerified().booleanValue()) {
                new ExpandTouchDelegate(this.mPaymentMethodBinding.tvPaymentMethodVerify).expand(ExpandTouchDelegate.ALL);
                this.mPaymentMethodBinding.tvPaymentMethodVerify.setOnClickListener(PaymentMethodAdapterDelegate$PaymentMethodViewHolder$$Lambda$2.lambdaFactory$(this, method));
            }
            this.mPaymentMethodBinding.executePendingBindings();
        }
    }

    public PaymentMethodAdapterDelegate(Activity activity, PaymentMethodsPresenter presenter) {
        this.mActivity = activity;
        this.mPresenter = presenter;
    }

    protected boolean isForViewType(List<Object> items, int position) {
        return items.get(position) instanceof Data;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PaymentMethodViewHolder((ListItemPaymentMethodBinding) DataBindingUtil.bind(this.mActivity.getLayoutInflater().inflate(R.layout.list_item_payment_method, parent, false)));
    }

    protected void onBindViewHolder(List<Object> items, int position, ViewHolder holder, List<Object> list) {
        ((PaymentMethodViewHolder) holder).bind((Data) items.get(position));
    }

    int getResourceForType(Type type) {
        switch (type) {
            case ACH_BANK_ACCOUNT:
            case SEPA_BANK_ACCOUNT:
            case BANK_WIRE:
            case FIAT_ACCOUNT:
            case XFERS:
                return R.drawable.ic_bank;
            default:
                return R.drawable.usd_large;
        }
    }
}
