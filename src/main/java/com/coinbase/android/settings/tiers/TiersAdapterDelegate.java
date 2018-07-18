package com.coinbase.android.settings.tiers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.coinbase.android.R;
import com.coinbase.android.databinding.TierRequirementItemBinding;
import com.coinbase.android.databinding.TiersItemBinding;
import com.coinbase.api.internal.models.tiers.Level;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Requirement.Status;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class TiersAdapterDelegate extends AdapterDelegate<List<Level>> {
    private static final String REQUIREMENT_STATUS_INCOMPLETE = "incomplete";
    private final Context mContext;
    private final InvestmentTiersSettingsPresenter mPresenter;

    static class RequirementAdapter extends Adapter<RequirementViewHolder> {
        private final Context mContext;
        private final Requirement[] mRequirements;

        static class RequirementViewHolder extends ViewHolder {
            private final TierRequirementItemBinding mBinding;

            public RequirementViewHolder(View requirementView) {
                super(requirementView);
                this.mBinding = (TierRequirementItemBinding) DataBindingUtil.bind(requirementView);
            }

            public TierRequirementItemBinding getBinding() {
                return this.mBinding;
            }
        }

        public RequirementAdapter(Context context, Requirement[] requirements) {
            this.mContext = context;
            this.mRequirements = requirements;
        }

        public int getItemCount() {
            return this.mRequirements.length;
        }

        public RequirementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RequirementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tier_requirement_item, parent, false));
        }

        public void onBindViewHolder(RequirementViewHolder holder, int position) {
            Requirement requirement = this.mRequirements[position];
            TierRequirementItemBinding binding = holder.getBinding();
            if (TextUtils.isEmpty(requirement.getDescription())) {
                binding.tvName.setVisibility(8);
                binding.ivStatus.setVisibility(8);
                return;
            }
            binding.tvName.setText(requirement.getDescription());
            boolean complete = Status.COMPLETE == Status.fromString(requirement.getStatus());
            int statusDrawable = R.drawable.tiers_requirement_incomplete;
            if (complete) {
                statusDrawable = R.drawable.tiers_requirement_completed;
            }
            binding.ivStatus.setImageDrawable(ContextCompat.getDrawable(this.mContext, statusDrawable));
            binding.tvName.setVisibility(0);
            binding.ivStatus.setVisibility(0);
        }
    }

    private class TiersItemViewHolder extends ViewHolder {
        private TiersItemBinding mBinding;

        public TiersItemViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (TiersItemBinding) DataBindingUtil.bind(itemView);
        }

        public TiersItemBinding getBinding() {
            return this.mBinding;
        }
    }

    public TiersAdapterDelegate(Context context, InvestmentTiersSettingsPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    protected boolean isForViewType(List<Level> items, int position) {
        return items.get(position) != null;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TiersItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tiers_item, parent, false));
    }

    protected void onBindViewHolder(List<Level> items, int position, ViewHolder holder, List<Object> list) {
        Level tier = (Level) items.get(position);
        if (tier != null) {
            TiersItemBinding binding = ((TiersItemViewHolder) holder).getBinding();
            binding.tvName.setText(tier.getName());
            boolean attained = (tier.getStatus() == null || tier.getStatus().getComplete() == null || !tier.getStatus().getComplete().booleanValue()) ? false : true;
            List<Requirement> requirements = tier.getRequirements();
            binding.ivTierImage.setImageDrawable(ContextCompat.getDrawable(this.mContext, getImageForTier(attained, requirements, position)));
            if (tier.getStatus() != null && !TextUtils.isEmpty(tier.getStatus().getDescription())) {
                binding.tvStatus.setText(tier.getStatus().getDescription());
            } else if (attained) {
                binding.tvStatus.setText(this.mContext.getString(R.string.verified));
            } else {
                binding.tvStatus.setText(this.mContext.getString(R.string.unverified));
            }
            binding.tvDescription.setText(tier.getDescription() == null ? "" : tier.getDescription());
            if (!(requirements == null || requirements.isEmpty())) {
                RequirementAdapter adapter = new RequirementAdapter(this.mContext, (Requirement[]) requirements.toArray(new Requirement[requirements.size()]));
                binding.rvRequirements.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
                binding.rvRequirements.setAdapter(adapter);
            }
            binding.vBottomDivider.setVisibility(0);
            if (position == 0) {
                binding.vConnectingVerticalLineTop.setVisibility(4);
                setConnectorColor(binding.ivDividerBottom, position, position + 1, items);
            } else if (position == items.size() - 1) {
                binding.ivDividerBottom.setVisibility(4);
                setConnectorColor(binding.vConnectingVerticalLineTop, position, position - 1, items);
                binding.vBottomDivider.setVisibility(8);
            } else {
                setConnectorColor(binding.ivDividerBottom, position, position + 1, items);
                setConnectorColor(binding.vConnectingVerticalLineTop, position, position - 1, items);
            }
        }
    }

    private int getImageForTier(boolean attained, List<Requirement> requirements, int position) {
        if (attained) {
            return R.drawable.tier_attained;
        }
        if (requirements == null || requirements.isEmpty() || position > this.mPresenter.getCurrentTier()) {
            return R.drawable.tier_unattained;
        }
        for (Requirement requirement : requirements) {
            if (requirement.getStatus() != null && !TextUtils.equals(requirement.getStatus(), REQUIREMENT_STATUS_INCOMPLETE)) {
                return R.drawable.tier_in_progress;
            }
        }
        return R.drawable.tier_unattained;
    }

    private void setConnectorColor(ImageView view, int position1, int position2, List<Level> items) {
        if (view != null && position1 < items.size() && position2 < items.size()) {
            Level tier1 = (Level) items.get(position1);
            Level tier2 = (Level) items.get(position2);
            if (tier1.getStatus() != null && tier2.getStatus() != null && tier1.getStatus().getComplete() != null && tier2.getStatus().getComplete() != null) {
                int lineDrawable;
                if (tier1.getStatus().getComplete().booleanValue() && tier2.getStatus().getComplete().booleanValue()) {
                    lineDrawable = R.drawable.vertical_green_line;
                } else {
                    lineDrawable = R.drawable.vertical_grey_line;
                }
                view.setVisibility(0);
                view.setImageDrawable(ContextCompat.getDrawable(this.mContext, lineDrawable));
            }
        }
    }
}
