package com.coinbase.android.settings;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemSettingsBinding;
import com.coinbase.android.databinding.ListItemSettingsConnectedAccountsBinding;
import com.coinbase.android.databinding.ListItemSettingsHeaderBinding;
import com.coinbase.android.databinding.ListItemSettingsInfoBinding;
import java.util.List;

public class SettingsPreferenceListAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private List<? extends SettingsPreferenceItem> mItemList;

    private class ConnectedAccountsViewHolder extends ViewHolder {
        private ListItemSettingsConnectedAccountsBinding mBinding;

        public ConnectedAccountsViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemSettingsConnectedAccountsBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemSettingsConnectedAccountsBinding getBinding() {
            return this.mBinding;
        }
    }

    private class HeaderViewHolder extends ViewHolder {
        private ListItemSettingsHeaderBinding mBinding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemSettingsHeaderBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemSettingsHeaderBinding getBinding() {
            return this.mBinding;
        }
    }

    private class InfoViewHolder extends ViewHolder {
        private ListItemSettingsInfoBinding mBinding;

        public InfoViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemSettingsInfoBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemSettingsInfoBinding getBinding() {
            return this.mBinding;
        }
    }

    private class ItemViewHolder extends ViewHolder {
        private ListItemSettingsBinding mBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemSettingsBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemSettingsBinding getBinding() {
            return this.mBinding;
        }
    }

    public SettingsPreferenceListAdapter(Context context, List<? extends SettingsPreferenceItem> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                return new ItemViewHolder(inflater.inflate(R.layout.list_item_settings, parent, false));
            case 1:
                return new HeaderViewHolder(inflater.inflate(R.layout.list_item_settings_header, parent, false));
            case 2:
                return new InfoViewHolder(inflater.inflate(R.layout.list_item_settings_info, parent, false));
            case 3:
                return new ConnectedAccountsViewHolder(inflater.inflate(R.layout.list_item_settings_connected_accounts, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingsPreferenceItem item = (SettingsPreferenceItem) this.mItemList.get(position);
        switch (getItemViewType(position)) {
            case 0:
                setItemBinding(holder, item);
                return;
            case 1:
                setHeaderBinding(holder, item);
                return;
            case 2:
                setInfoBinding(holder, item);
                return;
            case 3:
                setConnectedAccountsBinding(holder, item);
                return;
            default:
                return;
        }
    }

    private void setItemBinding(ViewHolder holder, SettingsPreferenceItem item) {
        int i = 0;
        ListItemSettingsBinding binding = ((ItemViewHolder) holder).getBinding();
        binding.tvDisplayName.setText(item.getDisplayName());
        binding.tvDisplayName.setTextColor(ContextCompat.getColor(this.mContext, item.getDisplayNameTextColor()));
        String value = item.getDisplayValue();
        binding.tvDisplayValue.setText(value);
        if (TextUtils.isEmpty(value)) {
            binding.tvDisplayValue.setVisibility(8);
        } else {
            binding.tvDisplayValue.setVisibility(0);
        }
        String description = item.getDisplayDescription();
        if (TextUtils.isEmpty(description)) {
            binding.tvDisplayDescription.setVisibility(8);
        } else {
            binding.tvDisplayDescription.setVisibility(0);
            binding.tvDisplayDescription.setText(description);
        }
        binding.cvSettingsSwitch.setVisibility(item.showSwitch() ? 0 : 4);
        binding.cvSettingsSwitch.setChecked(item.isSwitchOn());
        binding.rlSetting.setOnClickListener(SettingsPreferenceListAdapter$$Lambda$1.lambdaFactory$(item));
        ImageView imageView = binding.ivArrow;
        if (!item.isNavigationEnabled()) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    private void setHeaderBinding(ViewHolder holder, SettingsPreferenceItem item) {
        ListItemSettingsHeaderBinding binding = ((HeaderViewHolder) holder).getBinding();
        binding.tvHeader.setText(item.getDisplayName());
        binding.tvHeader.setOnClickListener(null);
    }

    private void setInfoBinding(ViewHolder holder, SettingsPreferenceItem item) {
        ListItemSettingsInfoBinding binding = ((InfoViewHolder) holder).getBinding();
        binding.tvInfo.setText(item.getDisplayValue());
        binding.tvInfo.setOnClickListener(null);
    }

    private void setConnectedAccountsBinding(ViewHolder holder, SettingsPreferenceItem item) {
        if (item instanceof ConnectedAccountsView) {
            ConnectedAccountsView connectedAccountsView = (ConnectedAccountsView) item;
            ListItemSettingsConnectedAccountsBinding binding = ((ConnectedAccountsViewHolder) holder).getBinding();
            binding.llCustomView.removeAllViews();
            binding.llCustomView.addView(connectedAccountsView.getView());
        }
    }

    public int getItemCount() {
        return this.mItemList.size();
    }

    public int getItemViewType(int position) {
        return ((SettingsPreferenceItem) this.mItemList.get(position)).getType().getValue();
    }
}
