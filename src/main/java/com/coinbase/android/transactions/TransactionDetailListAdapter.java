package com.coinbase.android.transactions;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemTransactionDetailBinding;
import java.util.HashMap;
import java.util.List;

public class TransactionDetailListAdapter extends Adapter<ViewHolder> {
    private TransactionDetailPresenter mPresenter;
    private List<HashMap<String, String>> mTransactionDetailList;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemTransactionDetailBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemTransactionDetailBinding) DataBindingUtil.bind(itemView);
        }

        ListItemTransactionDetailBinding getBinding() {
            return this.mBinding;
        }
    }

    public TransactionDetailListAdapter(TransactionDetailPresenter presenter, List<HashMap<String, String>> transactionDetailList) {
        this.mPresenter = presenter;
        this.mTransactionDetailList = transactionDetailList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction_detail, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        int i;
        ListItemTransactionDetailBinding binding = holder.getBinding();
        HashMap<String, String> detailMap = (HashMap) this.mTransactionDetailList.get(position);
        String header = (String) detailMap.get(TransactionDetailPresenter.DISPLAYABLE_HEADER);
        binding.tvHeader.setText(header);
        binding.tvValue.setText((CharSequence) detailMap.get(TransactionDetailPresenter.DISPLAYABLE_VALUE));
        binding.statusView.setVisibility(8);
        if (header.equals(this.mPresenter.getHeaderStringValue(R.string.transaction_header_status))) {
            try {
                int statusViewColor = Integer.parseInt((String) detailMap.get(TransactionDetailPresenter.DISPLAYABLE_COLOR));
                binding.statusView.setVisibility(0);
                Drawable background = binding.statusView.getBackground().mutate();
                if (background instanceof GradientDrawable) {
                    ((GradientDrawable) background).setColor(statusViewColor);
                }
            } catch (NumberFormatException e) {
            }
        }
        View view = binding.vDottedLine;
        if (position == getItemCount() - 1) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
    }

    public int getItemCount() {
        return this.mTransactionDetailList.size();
    }
}
