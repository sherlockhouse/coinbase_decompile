package com.coinbase.android.notifications.priceAlerts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.accounts.AccountTransactionsPresenter;
import com.coinbase.android.notifications.priceAlerts.create.CreatePriceAlertActivity;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;

@ControllerScope
public class PriceAlertsRouter {
    public static String CURRENCY_DATA = AccountTransactionsPresenter.CURRENCY_DATA;
    private final AppCompatActivity mActivity;

    @Inject
    PriceAlertsRouter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    void routeCreatePriceAlert(Data currency) {
        String currencyData = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) currency);
        Intent intent = new Intent(this.mActivity, CreatePriceAlertActivity.class);
        intent.putExtra(CURRENCY_DATA, currencyData);
        this.mActivity.startActivity(intent);
    }
}
