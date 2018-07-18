package com.coinbase.android.settings.tiers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LimitAndFeatureItemBinding;
import com.coinbase.android.ui.AutoResizeTextViewAdjustor;
import com.coinbase.api.internal.models.tiers.LevelFeature;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class LimitsAndFeaturesAdapterDelegate extends AdapterDelegate<List<Object>> {
    private final AutoResizeTextViewAdjustor mAutoResizeTextViewAdjustor;
    private final Context mContext;

    private class LimitAndFeatureViewHolder extends ViewHolder {
        LimitAndFeatureItemBinding mBinding;

        public LimitAndFeatureViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (LimitAndFeatureItemBinding) DataBindingUtil.bind(itemView);
        }

        LimitAndFeatureItemBinding getBinding() {
            return this.mBinding;
        }
    }

    enum Type {
        SEND(R.drawable.send_item),
        RECEIVE(R.drawable.receive_item);
        
        private final int mDrawableId;

        private Type(int drawableId) {
            this.mDrawableId = drawableId;
        }

        int getDrawable() {
            return this.mDrawableId;
        }
    }

    public LimitsAndFeaturesAdapterDelegate(Context context, AutoResizeTextViewAdjustor autoResizeTextViewAdjustor) {
        this.mContext = context;
        this.mAutoResizeTextViewAdjustor = autoResizeTextViewAdjustor;
    }

    protected boolean isForViewType(List<Object> items, int position) {
        if (!(items.get(position) instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) items.get(position);
        boolean z = (pair.first instanceof LevelFeature) && (pair.second instanceof Type);
        return z;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        LimitAndFeatureViewHolder holder = new LimitAndFeatureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.limit_and_feature_item, parent, false));
        this.mAutoResizeTextViewAdjustor.addView(holder.getBinding().tvStatus);
        return holder;
    }

    protected void onBindViewHolder(List<Object> items, int position, ViewHolder holder, List<Object> list) {
        LevelFeature levelFeature = (LevelFeature) ((Pair) items.get(position)).first;
        Type type = (Type) ((Pair) items.get(position)).second;
        LimitAndFeatureItemBinding binding = ((LimitAndFeatureViewHolder) holder).getBinding();
        binding.tvWireName.setText(levelFeature.getDescription());
        if (TextUtils.isEmpty(levelFeature.getStatusText())) {
            binding.tvStatus.setVisibility(4);
        } else {
            binding.tvStatus.setText(levelFeature.getStatusText());
            binding.tvStatus.setTextColor(ContextCompat.getColor(this.mContext, getColorResForStatus(levelFeature.getEnabled())));
            binding.tvStatus.setVisibility(0);
        }
        binding.ivImage.setImageDrawable(ContextCompat.getDrawable(this.mContext, type.getDrawable()));
    }

    private int getColorResForStatus(Boolean enabled) {
        if (enabled == null || !enabled.booleanValue()) {
            return R.color.tiers_level_feature_disabled;
        }
        return R.color.tiers_level_feature_enabled;
    }
}
