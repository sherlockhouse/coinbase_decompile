package com.coinbase.android.settings.tiers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.RequirementItemBinding;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Requirement.Status;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class InvestmentTiersRequirementsAdapterDelegate extends AdapterDelegate<List<Requirement>> {
    private Context mContext;
    private InvestmentTiersRequirementsPresenter mPresenter;

    private class InvestmentTiersRequirementsViewHolder extends ViewHolder {
        private RequirementItemBinding mBinding;

        public InvestmentTiersRequirementsViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (RequirementItemBinding) DataBindingUtil.bind(itemView);
        }

        public RequirementItemBinding getBinding() {
            return this.mBinding;
        }
    }

    public InvestmentTiersRequirementsAdapterDelegate(Context context, InvestmentTiersRequirementsPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    protected boolean isForViewType(List<Requirement> items, int position) {
        return items.get(position) != null;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new InvestmentTiersRequirementsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.requirement_item, parent, false));
    }

    protected void onBindViewHolder(List<Requirement> items, int position, ViewHolder holder, List<Object> list) {
        Requirement requirement = (Requirement) items.get(position);
        RequirementItemBinding binding = ((InvestmentTiersRequirementsViewHolder) holder).getBinding();
        binding.tvName.setText(requirement.getDescription());
        Status status = Status.fromString(requirement.getStatus());
        binding.tvStatus.setText(getTextForStatus(status));
        if (status == Status.COMPLETE) {
            binding.rlContainer.setClickable(false);
            binding.ivChevron.setVisibility(4);
            return;
        }
        binding.rlContainer.setClickable(true);
        binding.ivChevron.setVisibility(0);
        binding.rlContainer.setOnClickListener(InvestmentTiersRequirementsAdapterDelegate$$Lambda$1.lambdaFactory$(this, requirement));
    }

    private SpannableString getTextForStatus(Status status) {
        switch (status) {
            case COMPLETE:
                return getSpannableStringWithColor(R.string.tier_requirement_complete, R.color.shamrock_green);
            case PENDING:
                return getSpannableStringWithColor(R.string.tier_requirement_pending, R.color.section_header_mystique);
            default:
                return new SpannableString("");
        }
    }

    private SpannableString getSpannableStringWithColor(int str, int color) {
        SpannableString span = new SpannableString(this.mContext.getString(str));
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, color)), 0, span.length(), 18);
        return span;
    }
}
