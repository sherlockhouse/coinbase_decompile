package com.coinbase.android.phone;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemPhoneNumberBinding;
import com.coinbase.api.internal.models.phoneNumber.Data;
import java.util.List;

public class PhoneNumberAdapter extends Adapter<ViewHolder> {
    private List<Data> mPhoneNumberList = this.mPresenter.getPhoneNumberList();
    private PhoneNumberPresenter mPresenter;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemPhoneNumberBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemPhoneNumberBinding) DataBindingUtil.bind(itemView);
        }

        public ListItemPhoneNumberBinding getBinding() {
            return this.mBinding;
        }
    }

    PhoneNumberAdapter(PhoneNumberPresenter presenter) {
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_phone_number, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemPhoneNumberBinding binding = holder.getBinding();
        Data phoneNumber = (Data) this.mPhoneNumberList.get(position);
        binding.tvPhoneNumber.setText(phoneNumber.getNumber());
        binding.btnVerify.setVisibility(phoneNumber.getVerified().booleanValue() ? 8 : 0);
        binding.btnVerify.setOnClickListener(PhoneNumberAdapter$$Lambda$1.lambdaFactory$(this, phoneNumber));
        binding.btnRemove.setOnClickListener(PhoneNumberAdapter$$Lambda$2.lambdaFactory$(this, phoneNumber));
    }

    public int getItemCount() {
        return this.mPhoneNumberList.size();
    }
}
