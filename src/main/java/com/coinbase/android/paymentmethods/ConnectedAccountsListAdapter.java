package com.coinbase.android.paymentmethods;

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
import com.coinbase.android.databinding.ListItemConnectedAccountBinding;
import com.coinbase.android.databinding.ListItemConnectedAccountFooterBinding;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;

public class ConnectedAccountsListAdapter extends Adapter<ViewHolder> {
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_ITEM = 0;
    private List<Data> mConnectedAccountList = this.mPresenter.getConnectedAccountList();
    private Context mContext;
    private ConnectedAccountsPresenter mPresenter;

    static class FooterViewHolder extends ViewHolder {
        private ListItemConnectedAccountFooterBinding mBinding;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConnectedAccountFooterBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConnectedAccountFooterBinding getBinding() {
            return this.mBinding;
        }
    }

    static class ItemViewHolder extends ViewHolder {
        private ListItemConnectedAccountBinding mBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemConnectedAccountBinding) DataBindingUtil.bind(itemView);
        }

        ListItemConnectedAccountBinding getBinding() {
            return this.mBinding;
        }
    }

    public ConnectedAccountsListAdapter(Context context, ConnectedAccountsPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                return new ItemViewHolder(layoutInflater.inflate(R.layout.list_item_connected_account, parent, false));
            case 1:
                return new FooterViewHolder(layoutInflater.inflate(R.layout.list_item_connected_account_footer, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                setItemBinding(holder, position);
                return;
            case 1:
                setFooterBinding(holder);
                return;
            default:
                return;
        }
    }

    public int getItemViewType(int position) {
        if (this.mConnectedAccountList.size() == position) {
            return 1;
        }
        return 0;
    }

    public int getItemCount() {
        return this.mConnectedAccountList.size() + 1;
    }

    private void setItemBinding(ViewHolder holder, int position) {
        if (!this.mConnectedAccountList.isEmpty()) {
            ListItemConnectedAccountBinding binding = ((ItemViewHolder) holder).getBinding();
            Data connectedAccount = (Data) this.mConnectedAccountList.get(position);
            Pair<String, String> nameAndNumber = this.mPresenter.getFormattedPaymentMethodNameAndNumberDisplay(connectedAccount);
            binding.tvPaymentMethodName.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.first);
            binding.tvPaymentMethodNumber.setText(nameAndNumber == null ? "" : (CharSequence) nameAndNumber.second);
            if (connectedAccount.getImage() != null) {
                GlideApp.with(this.mContext).load(connectedAccount.getImage()).into(binding.ivPaymentMethodIcon);
            } else {
                binding.ivPaymentMethodIcon.setImageResource(this.mPresenter.getResourceForType(connectedAccount.getType()));
            }
            binding.ivWarning.setVisibility(connectedAccount.getVerified().booleanValue() ? 8 : 0);
            binding.rlConnectedAccount.setOnClickListener(ConnectedAccountsListAdapter$$Lambda$1.lambdaFactory$(this, connectedAccount));
        }
    }

    private void setFooterBinding(ViewHolder holder) {
        ListItemConnectedAccountFooterBinding binding = ((FooterViewHolder) holder).getBinding();
        if (this.mConnectedAccountList.isEmpty()) {
            binding.rlAddFooter.setVisibility(8);
            binding.rlAddEmptyFooter.setVisibility(0);
        } else {
            binding.rlAddEmptyFooter.setVisibility(8);
            binding.rlAddFooter.setVisibility(0);
        }
        binding.rlFooter.setOnClickListener(ConnectedAccountsListAdapter$$Lambda$2.lambdaFactory$(this));
    }
}
