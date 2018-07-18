package com.coinbase.android.billing;

import android.os.Bundle;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.CoinbaseActivityMystique;
import com.coinbase.android.R;

@ActivityScope
public class AddBillingAddressActivity extends CoinbaseActivityMystique {
    protected void onCreate(Bundle savedInstanceState) {
        supportLandscapeOnTablet();
        super.onCreate(savedInstanceState);
        this.mScreenProtector.protect();
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new AddBillingAddressFragment()).commit();
    }
}
