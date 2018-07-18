package com.coinbase.android.paymentmethods;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemAvailablePaymentMethodBinding;
import com.coinbase.android.paymentmethods.PaymentMethodsPresenter.PaymentMethodType;
import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod;
import com.coinbase.api.internal.models.availablePaymentMethods.BuyingPower;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvailablePaymentMethodAdapterDelegate extends AdapterDelegate<List<Object>> {
    private static final String BUYING_POWER_LARGE = "large";
    private final Activity mActivity;
    private final Logger mLogger = LoggerFactory.getLogger(AvailablePaymentMethodAdapterDelegate.class);
    private final PaymentMethodsPresenter mPresenter;

    class AvailablePaymentMethodViewHolder extends ViewHolder {
        ListItemAvailablePaymentMethodBinding mAvailablePaymentMethodBinding;

        AvailablePaymentMethodViewHolder(ListItemAvailablePaymentMethodBinding binding) {
            super(binding.getRoot());
            this.mAvailablePaymentMethodBinding = binding;
        }

        void bind(Pair<PaymentMethodType, AvailablePaymentMethod> pair) {
            boolean recommended;
            String name = ((AvailablePaymentMethod) pair.second).getName();
            String buyingPower = ((AvailablePaymentMethod) pair.second).getBuyingPower() != null ? ((AvailablePaymentMethod) pair.second).getBuyingPower().getText() : "";
            String description = ((AvailablePaymentMethod) pair.second).getDescription();
            if (((AvailablePaymentMethod) pair.second).getRecommended() != null) {
                recommended = ((AvailablePaymentMethod) pair.second).getRecommended().booleanValue();
            } else {
                recommended = false;
            }
            String additionalFees = ((AvailablePaymentMethod) pair.second).getAdditionalFees();
            this.mAvailablePaymentMethodBinding.ivPaymentMethodIcon.setImageDrawable(ContextCompat.getDrawable(AvailablePaymentMethodAdapterDelegate.this.mActivity, getResourceForType((PaymentMethodType) pair.first)));
            if (TextUtils.isEmpty(name)) {
                this.mAvailablePaymentMethodBinding.tvPaymentMethodName.setText("");
            } else {
                this.mAvailablePaymentMethodBinding.tvPaymentMethodName.setText(name);
            }
            setTextViewIfNotEmpty(buyingPower, this.mAvailablePaymentMethodBinding.tvBuyingPower, getBuyingPowerColor(((AvailablePaymentMethod) pair.second).getBuyingPower()));
            setTextViewIfNotEmpty(description, this.mAvailablePaymentMethodBinding.tvDescription);
            if (recommended) {
                this.mAvailablePaymentMethodBinding.tvRecommended.setVisibility(0);
            } else {
                this.mAvailablePaymentMethodBinding.tvRecommended.setVisibility(8);
            }
            String additionalFeesFormatted = getAdditionalFeesString(additionalFees);
            setTextViewIfNotEmpty(additionalFeesFormatted, this.mAvailablePaymentMethodBinding.tvAdditionalFees);
            if (TextUtils.isEmpty(additionalFeesFormatted)) {
                this.mAvailablePaymentMethodBinding.vAdditionalFeesDivider.setVisibility(8);
            } else {
                this.mAvailablePaymentMethodBinding.vAdditionalFeesDivider.setVisibility(0);
            }
            this.mAvailablePaymentMethodBinding.getRoot().setOnClickListener(AvailablePaymentMethodAdapterDelegate$AvailablePaymentMethodViewHolder$$Lambda$1.lambdaFactory$(this, pair));
        }

        private void setTextViewIfNotEmpty(String str, TextView textView, int color) {
            textView.setTextColor(ContextCompat.getColor(AvailablePaymentMethodAdapterDelegate.this.mActivity, color));
            setTextViewIfNotEmpty(str, textView);
        }

        private void setTextViewIfNotEmpty(String str, TextView textView) {
            if (TextUtils.isEmpty(str)) {
                textView.setVisibility(8);
                return;
            }
            textView.setText(str);
            textView.setVisibility(0);
        }

        private String getAdditionalFeesString(String additionalFees) {
            if (TextUtils.isEmpty(additionalFees)) {
                return "";
            }
            try {
                if (Double.valueOf(additionalFees).doubleValue() > 0.0d) {
                    return String.format(AvailablePaymentMethodAdapterDelegate.this.mActivity.getString(R.string.additional_fees), new Object[]{additionalFees});
                }
            } catch (Exception e) {
                AvailablePaymentMethodAdapterDelegate.this.mLogger.error("Error parsing fees [" + additionalFees + "]", e);
            }
            return "";
        }

        int getResourceForType(PaymentMethodType type) {
            switch (type) {
                case ACH_BANK_ACCOUNT:
                    return R.drawable.ic_bank;
                case SEPA_BANK_ACCOUNT:
                    return R.drawable.sepa_bank;
                default:
                    return R.drawable.usd_large;
            }
        }

        private int getBuyingPowerColor(BuyingPower power) {
            if (power != null && TextUtils.equals(AvailablePaymentMethodAdapterDelegate.BUYING_POWER_LARGE, power.getType())) {
                return R.color.linked_accounts_green;
            }
            return R.color.primary_mystique_text_color;
        }
    }

    public AvailablePaymentMethodAdapterDelegate(Activity activity, PaymentMethodsPresenter presenter) {
        this.mActivity = activity;
        this.mPresenter = presenter;
    }

    protected boolean isForViewType(List<Object> items, int position) {
        Pair o = items.get(position);
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair pair = o;
        if ((pair.first instanceof PaymentMethodType) && (pair.second instanceof AvailablePaymentMethod)) {
            return true;
        }
        return false;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new AvailablePaymentMethodViewHolder((ListItemAvailablePaymentMethodBinding) DataBindingUtil.bind(this.mActivity.getLayoutInflater().inflate(R.layout.list_item_available_payment_method, parent, false)));
    }

    protected void onBindViewHolder(List<Object> items, int position, ViewHolder holder, List<Object> list) {
        ((AvailablePaymentMethodViewHolder) holder).bind((Pair) items.get(position));
    }
}
