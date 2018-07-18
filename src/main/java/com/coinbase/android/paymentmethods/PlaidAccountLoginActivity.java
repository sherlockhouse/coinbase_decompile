package com.coinbase.android.paymentmethods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.coinbase.android.CoinbaseActivityMystique;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.institutions.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlaidAccountLoginActivity extends CoinbaseActivityMystique {
    public static final String INSTITUTION = "PlaidAccountLoginActivity_Institution";
    public static final String MANUAL = "manual";
    private boolean mParentSuccessRouter = false;

    public void onCreate(Bundle savedInstanceState) {
        Fragment f;
        if (savedInstanceState != null) {
            savedInstanceState = null;
        }
        super.onCreate(savedInstanceState);
        this.mScreenProtector.protect();
        Data institution = null;
        boolean showManual = false;
        com.coinbase.v2.models.paymentMethods.Data method = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(INSTITUTION)) {
                institution = (Data) new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().fromJson(extras.getString(INSTITUTION), Data.class);
            }
            if (extras.containsKey("payment_method")) {
                method = (com.coinbase.v2.models.paymentMethods.Data) new Gson().fromJson(extras.getString("payment_method"), com.coinbase.v2.models.paymentMethods.Data.class);
            }
            showManual = extras.getBoolean(MANUAL, false);
            this.mParentSuccessRouter = extras.getBoolean(Constants.PARENT_SUCCESS_ROUTER, false);
        }
        if (!showManual) {
            f = PlaidAccountLoginFragment.newInstance(institution, this.mParentSuccessRouter);
        } else if (method != null) {
            f = IAVLoginFragment.newInstance(method, this.mParentSuccessRouter);
        } else {
            f = IAVLoginFragment.newInstance(this.mParentSuccessRouter);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, f).commit();
        supportLandscapeOnTablet();
    }

    public void showManualLogin() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, IAVLoginFragment.newInstance(this.mParentSuccessRouter)).addToBackStack(MANUAL).commit();
    }

    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flContainer);
        if (f instanceof PlaidAccountLoginFragment) {
            if (!((PlaidAccountLoginFragment) f).onBackPressed()) {
                super.onBackPressed();
            }
        } else if ((f instanceof IAVLoginFragment) && !((IAVLoginFragment) f).onBackPressed()) {
            super.onBackPressed();
        }
    }
}
