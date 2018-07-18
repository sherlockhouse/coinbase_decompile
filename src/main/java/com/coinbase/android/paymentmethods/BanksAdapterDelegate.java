package com.coinbase.android.paymentmethods;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinbase.android.GlideApp;
import com.coinbase.android.R;
import com.coinbase.android.databinding.GridItemBankBinding;
import com.coinbase.api.internal.models.institutions.Data;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

class BanksAdapterDelegate extends AdapterDelegate<List<Data>> {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final AddPlaidAccountPresenter mPresenter;

    private class BankViewHolder extends ViewHolder {
        private GridItemBankBinding mBinding;

        BankViewHolder(GridItemBankBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(final Data institution) {
            if (institution.getImage() == null || institution.getImage().getPng() == null) {
                BanksAdapterDelegate.this.showBankInformation(false, this.mBinding, institution);
            } else {
                BanksAdapterDelegate.this.showBankInformation(true, this.mBinding, institution);
                GlideApp.with(BanksAdapterDelegate.this.mContext).load(institution.getImage().getPng()).listener(new RequestListener<Drawable>() {
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        BanksAdapterDelegate.this.showBankInformation(false, BankViewHolder.this.mBinding, institution);
                        return false;
                    }

                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        BanksAdapterDelegate.this.showBankInformation(true, BankViewHolder.this.mBinding, institution);
                        return false;
                    }
                }).into(this.mBinding.ivBankLogo);
            }
            this.mBinding.cvBankContainer.setOnClickListener(BanksAdapterDelegate$BankViewHolder$$Lambda$1.lambdaFactory$(this, institution));
        }
    }

    BanksAdapterDelegate(AddPlaidAccountPresenter presenter, Context context, LayoutInflater inflater) {
        this.mPresenter = presenter;
        this.mContext = context;
        this.mInflater = inflater;
    }

    protected boolean isForViewType(List<Data> list, int position) {
        return true;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BankViewHolder((GridItemBankBinding) DataBindingUtil.bind(this.mInflater.inflate(R.layout.grid_item_bank, parent, false)));
    }

    protected void onBindViewHolder(List<Data> items, int position, ViewHolder holder, List<Object> list) {
        Data institution = (Data) items.get(position);
        if (institution != null) {
            ((BankViewHolder) holder).bind(institution);
        }
    }

    private void showBankInformation(boolean imageLoaded, GridItemBankBinding binding, Data institution) {
        int i = 8;
        binding.ivBankLogo.setVisibility(imageLoaded ? 0 : 8);
        TextView textView = binding.tvBankName;
        if (!imageLoaded) {
            i = 0;
        }
        textView.setVisibility(i);
        if (!imageLoaded) {
            binding.tvBankName.setText(institution.getName());
        }
    }
}
