package com.coinbase.android.confirmation;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemConfirmationDetailBinding;
import com.coinbase.android.databinding.ListItemConfirmationFeeBinding;
import com.coinbase.android.databinding.ListItemConfirmationPaymentMethodBinding;
import com.coinbase.android.databinding.ListItemConfirmationTotalBinding;
import java.util.List;

public class ConfirmationDetailListAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private List<ConfirmationDetail> mDetails;
    private ConfirmationPresenter mPresenter;

    public static class ConfirmationDetail {
        private String mHeader;
        private String mImageUrl;
        private ItemType mType;
        private String mValue;

        public ConfirmationDetail(String header, String value, ItemType type) {
            this(header, value, null, type);
        }

        public ConfirmationDetail(String header, String value, String imageUrl, ItemType type) {
            this.mHeader = header;
            this.mValue = value;
            this.mImageUrl = imageUrl;
            this.mType = type;
        }

        public String getHeader() {
            return this.mHeader;
        }

        public String getValue() {
            return this.mValue;
        }

        public String getImageUrl() {
            return this.mImageUrl;
        }

        public int getTypeValue() {
            return this.mType.getValue();
        }
    }

    static class DetailViewHolder extends ViewHolder {
        private ListItemConfirmationDetailBinding mBinding;

        public DetailViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConfirmationDetailBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConfirmationDetailBinding getBinding() {
            return this.mBinding;
        }
    }

    static class FeeViewHolder extends ViewHolder {
        private ListItemConfirmationFeeBinding mBinding;

        public FeeViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConfirmationFeeBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConfirmationFeeBinding getBinding() {
            return this.mBinding;
        }
    }

    static class PaymentMethodViewHolder extends ViewHolder {
        private ListItemConfirmationPaymentMethodBinding mBinding;

        public PaymentMethodViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConfirmationPaymentMethodBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConfirmationPaymentMethodBinding getBinding() {
            return this.mBinding;
        }
    }

    static class TotalViewHolder extends ViewHolder {
        private ListItemConfirmationTotalBinding mBinding;

        public TotalViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConfirmationTotalBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConfirmationTotalBinding getBinding() {
            return this.mBinding;
        }
    }

    public ConfirmationDetailListAdapter(Context context, ConfirmationPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mDetails = presenter.getDetailList();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (ItemType.values()[viewType]) {
            case DETAIL:
                return new DetailViewHolder(layoutInflater.inflate(R.layout.list_item_confirmation_detail, parent, false));
            case FEE:
                return new FeeViewHolder(layoutInflater.inflate(R.layout.list_item_confirmation_fee, parent, false));
            case TOTAL:
                return new TotalViewHolder(layoutInflater.inflate(R.layout.list_item_confirmation_total, parent, false));
            case PAYMENT_METHOD:
                return new PaymentMethodViewHolder(layoutInflater.inflate(R.layout.list_item_confirmation_payment_method, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ConfirmationDetail detail = (ConfirmationDetail) this.mDetails.get(position);
        switch (ItemType.values()[getItemViewType(position)]) {
            case DETAIL:
                setDetailBinding((DetailViewHolder) holder, detail);
                return;
            case FEE:
                setFeeBinding((FeeViewHolder) holder, detail);
                return;
            case TOTAL:
                setTotalBinding((TotalViewHolder) holder, detail);
                return;
            case PAYMENT_METHOD:
                setPaymentMethodBinding((PaymentMethodViewHolder) holder, detail);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.mDetails.size();
    }

    public int getItemViewType(int position) {
        return ((ConfirmationDetail) this.mDetails.get(position)).getTypeValue();
    }

    private void setDetailBinding(DetailViewHolder holder, ConfirmationDetail itemDetail) {
        ListItemConfirmationDetailBinding binding = holder.getBinding();
        binding.tvHeader.setText(itemDetail.getHeader());
        binding.tvValue.setText(itemDetail.getValue());
    }

    private void setFeeBinding(FeeViewHolder holder, ConfirmationDetail itemDetail) {
        ListItemConfirmationFeeBinding binding = holder.getBinding();
        binding.tvFeeHeader.setText(itemDetail.getHeader());
        binding.tvFeeValue.setText(itemDetail.getValue());
        binding.ivFeeHelp.setOnClickListener(ConfirmationDetailListAdapter$$Lambda$1.lambdaFactory$(this));
    }

    private void setTotalBinding(TotalViewHolder holder, ConfirmationDetail itemDetail) {
        holder.getBinding().tvTotalValue.setText(itemDetail.getValue());
    }

    private void setPaymentMethodBinding(PaymentMethodViewHolder holder, ConfirmationDetail itemDetail) {
        ListItemConfirmationPaymentMethodBinding binding = holder.getBinding();
        binding.tvHeader.setText(itemDetail.getHeader());
        binding.tvValue.setText(itemDetail.getValue());
        if (TextUtils.isEmpty(itemDetail.getImageUrl())) {
            binding.ivPaymentMethodIcon.setVisibility(8);
        } else {
            GlideApp.with(this.mContext).load(itemDetail.getImageUrl()).into(binding.ivPaymentMethodIcon);
            binding.ivPaymentMethodIcon.setVisibility(0);
        }
        binding.rlPaymentMethod.setOnClickListener(ConfirmationDetailListAdapter$$Lambda$2.lambdaFactory$(this));
    }
}
