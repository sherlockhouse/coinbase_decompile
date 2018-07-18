package com.coinbase.android.billing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemBillingAddressBinding;
import com.coinbase.android.utils.BillingUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.billingAddress.Data;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class BillingAddressListAdapter extends ArrayAdapter<Data> {
    @Inject
    LoginManager loginManager;
    @Inject
    BillingAddressDeletedConnector mBillingAddressDeletedConnector;

    public BillingAddressListAdapter(Context context, List<Data> localPriceAlerts) {
        super(context, 0, localPriceAlerts);
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().adapterSubcomponent().inject(this);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBillingAddressBinding binding;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_billing_address, parent, false);
            binding = ListItemBillingAddressBinding.bind(convertView);
            convertView.setTag(binding);
        } else {
            binding = (ListItemBillingAddressBinding) convertView.getTag();
        }
        final Data billingAddress = (Data) getItem(position);
        binding.tvAddress.setText(BillingUtils.getAddressLine(billingAddress));
        binding.tvCityStateZip.setText(BillingUtils.getCityStateZip(billingAddress));
        binding.ibtnDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                BillingAddressListAdapter.this.deleteBillingAddress(billingAddress);
            }
        });
        return convertView;
    }

    private void deleteBillingAddress(final Data billingAddress) {
        this.loginManager.getClient().deleteBillingAddresses(billingAddress.getId(), new CallbackWithRetrofit<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    BillingAddressListAdapter.this.mBillingAddressDeletedConnector.get().onNext(billingAddress);
                } else {
                    Utils.showMessage(BillingAddressListAdapter.this.getContext(), Utils.getErrorMessage(response, retrofit), 0);
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Utils.showMessage(BillingAddressListAdapter.this.getContext(), t, 0);
            }
        });
    }
}
