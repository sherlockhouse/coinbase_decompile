package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.UpfrontKycVerifyDocumentPhotoHintItemBinding;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public class VerifyHintsAdapterDelegate extends AdapterDelegate<List<Integer>> {

    private class VerifyHintViewHolder extends ViewHolder {
        private UpfrontKycVerifyDocumentPhotoHintItemBinding mBinding;

        VerifyHintViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (UpfrontKycVerifyDocumentPhotoHintItemBinding) DataBindingUtil.bind(itemView);
        }

        public UpfrontKycVerifyDocumentPhotoHintItemBinding getBinding() {
            return this.mBinding;
        }
    }

    protected boolean isForViewType(List<Integer> list, int position) {
        return true;
    }

    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new VerifyHintViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.upfront_kyc_verify_document_photo_hint_item, parent, false));
    }

    protected void onBindViewHolder(List<Integer> items, int position, ViewHolder holder, List<Object> list) {
        ((VerifyHintViewHolder) holder).getBinding().tvHint.setText(((Integer) items.get(position)).intValue());
    }
}
