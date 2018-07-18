package com.coinbase.android.deposits;

import android.os.Bundle;
import com.coinbase.android.CoinbaseActivityMystique;
import com.coinbase.android.R;

public class SepaDepositActivity extends CoinbaseActivityMystique {
    public static final String FROM_DEPOSIT = "SepaDepositActivity_From_Deposit";

    protected void onCreate(Bundle savedInstance) {
        supportLandscapeOnTablet();
        super.onCreate(savedInstance);
        this.mScreenProtector.protect();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, SepaDepositFragment.newInstance(Boolean.valueOf(bundle.getBoolean(FROM_DEPOSIT)).booleanValue())).commit();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still, R.anim.activity_slide_out_bottom);
    }
}
