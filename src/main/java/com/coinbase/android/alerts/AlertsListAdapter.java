package com.coinbase.android.alerts;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemAlertBinding;

public class AlertsListAdapter extends Adapter<ViewHolder> implements AlertsListItemScreen {
    ListItemAlertBinding mBinding;
    private AlertsListPresenter mPresenter;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemAlertBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemAlertBinding) DataBindingUtil.bind(itemView);
        }

        ListItemAlertBinding getBinding() {
            return this.mBinding;
        }
    }

    public AlertsListAdapter(AlertsListPresenter presenter) {
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alert, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        this.mBinding = holder.getBinding();
        this.mPresenter.onBindViewHolder(this, position);
    }

    public void setText(String description) {
        this.mBinding.tvDescription.setText(description);
        this.mBinding.getRoot().setVisibility(0);
    }

    public void setOnDismissClickListener(OnClickListener listener) {
        this.mBinding.btnDismiss.setOnClickListener(listener);
    }

    public void setOnLearnMoreClickListener(OnClickListener listener) {
        this.mBinding.btnLearnMore.setOnClickListener(listener);
    }

    public void showDismissButton() {
        this.mBinding.btnDismiss.setVisibility(0);
    }

    public void hideDismissButton() {
        this.mBinding.btnDismiss.setVisibility(8);
    }

    public void showLearnMoreButton() {
        this.mBinding.btnLearnMore.setVisibility(0);
    }

    public void hideLearnMoreButton() {
        this.mBinding.btnLearnMore.setVisibility(8);
    }

    public void hideSelf() {
        this.mBinding.getRoot().setVisibility(8);
    }

    public int getItemCount() {
        return this.mPresenter.getItemCount();
    }
}
