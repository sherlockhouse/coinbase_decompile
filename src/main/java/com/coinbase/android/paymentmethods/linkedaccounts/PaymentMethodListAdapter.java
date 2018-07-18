package com.coinbase.android.paymentmethods.linkedaccounts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemBuysellFiatBinding;
import com.coinbase.android.databinding.ListItemBuysellPaymentMethodBinding;
import com.coinbase.android.databinding.ListItemBuysellPaymentMethodHeaderBinding;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import java.util.List;

public class PaymentMethodListAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_ACCOUNT = 2;
    private static final int TYPE_FIAT = 1;
    private static final int TYPE_HEADER = 0;
    private Context mContext;
    private List<Object> mPaymentMethodAndHeaderList = this.mPresenter.getPaymentMethodAndHeaderList();
    private LinkedAccountsPickerPresenter mPresenter;
    private Data mSelectedPaymentMethod = this.mPresenter.getSelectedPaymentMethod();

    static class AccountItemViewHolder extends ViewHolder {
        private ListItemBuysellPaymentMethodBinding mBinding;

        public AccountItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemBuysellPaymentMethodBinding) DataBindingUtil.bind(itemView);
        }

        ListItemBuysellPaymentMethodBinding getBinding() {
            return this.mBinding;
        }
    }

    static class FiatItemViewHolder extends ViewHolder {
        private ListItemBuysellFiatBinding mBinding;

        public FiatItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemBuysellFiatBinding) DataBindingUtil.bind(itemView);
        }

        ListItemBuysellFiatBinding getBinding() {
            return this.mBinding;
        }
    }

    static class HeaderItemViewHolder extends ViewHolder {
        private ListItemBuysellPaymentMethodHeaderBinding mBinding;

        public HeaderItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemBuysellPaymentMethodHeaderBinding) DataBindingUtil.bind(itemView);
        }

        ListItemBuysellPaymentMethodHeaderBinding getBinding() {
            return this.mBinding;
        }
    }

    PaymentMethodListAdapter(Context context, LinkedAccountsPickerPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                return new HeaderItemViewHolder(layoutInflater.inflate(R.layout.list_item_buysell_payment_method_header, parent, false));
            case 1:
                return new FiatItemViewHolder(layoutInflater.inflate(R.layout.list_item_buysell_fiat, parent, false));
            case 2:
                return new AccountItemViewHolder(layoutInflater.inflate(R.layout.list_item_buysell_payment_method, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                setHeaderBinding(holder, position);
                return;
            case 1:
                setFiatBinding(holder, position);
                return;
            case 2:
                setAccountBinding(holder, position);
                return;
            default:
                return;
        }
    }

    private void setFiatBinding(ViewHolder holder, int position) {
        ListItemBuysellFiatBinding binding = ((FiatItemViewHolder) holder).getBinding();
        Data paymentMethod = (Data) this.mPaymentMethodAndHeaderList.get(position);
        binding.tvAccountName.setText(paymentMethod.getName());
        binding.tvAccountBalance.setText(this.mPresenter.getFiatBalance(paymentMethod));
        binding.tvFiatSymbol.setText(this.mPresenter.getFiatAccountCurrencySymbol(paymentMethod));
        this.mPresenter.setFiatImageBackground(binding.ivFiatBackground, paymentMethod);
        binding.selectedView.setVisibility(isSelectedPaymentMethod(paymentMethod) ? 0 : 4);
        binding.rlPaymentMethod.setOnClickListener(PaymentMethodListAdapter$$Lambda$1.lambdaFactory$(this, paymentMethod));
    }

    private void setAccountBinding(ViewHolder holder, int position) {
        ListItemBuysellPaymentMethodBinding binding = ((AccountItemViewHolder) holder).getBinding();
        Data paymentMethod = (Data) this.mPaymentMethodAndHeaderList.get(position);
        Pair<String, String> nameAndNumber = this.mPresenter.getFormattedNameAndNumberDisplay(paymentMethod);
        binding.tvPaymentMethodName.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.first);
        binding.tvPaymentMethodNumber.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.second);
        if (paymentMethod.getImage() != null) {
            GlideApp.with(this.mContext).load(paymentMethod.getImage()).into(binding.ivPaymentMethodIcon);
        } else {
            binding.ivPaymentMethodIcon.setImageResource(this.mPresenter.getResourceForType(paymentMethod.getType()));
        }
        binding.selectedView.setVisibility(isSelectedPaymentMethod(paymentMethod) ? 0 : 4);
        binding.rlPaymentMethod.setOnClickListener(PaymentMethodListAdapter$$Lambda$2.lambdaFactory$(this, paymentMethod));
    }

    private void setHeaderBinding(ViewHolder holder, int position) {
        ((HeaderItemViewHolder) holder).getBinding().tvHeader.setText(this.mPaymentMethodAndHeaderList.get(position).toString());
    }

    private boolean isSelectedPaymentMethod(Data paymentMethod) {
        return this.mSelectedPaymentMethod != null && this.mSelectedPaymentMethod.getId().equals(paymentMethod.getId());
    }

    public int getItemCount() {
        return this.mPaymentMethodAndHeaderList.size();
    }

    public int getItemViewType(int position) {
        Object item = this.mPaymentMethodAndHeaderList.get(position);
        if (!(item instanceof Data)) {
            return 0;
        }
        if (((Data) item).getType() == Type.FIAT_ACCOUNT) {
            return 1;
        }
        return 2;
    }
}
