package com.coinbase.android.currencies;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.coinbase.android.ApplicationOnCreateListener;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseInternal;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;
import retrofit2.Response;
import rx.Scheduler;

public class FetchCurrenciesOnCreateListener implements ApplicationOnCreateListener {
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private final LoginManager mLoginManager;

    public FetchCurrenciesOnCreateListener(Application application, LoginManager loginManager, CurrenciesUpdatedConnector currenciesUpdatedConnector, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public void onCreate() {
        CoinbaseInternal.getInstance().init(this.mContext, this.mLoginManager.getAccessToken());
        this.mLoginManager.getClient().getCryptoCurrenciesRx().first().subscribeOn(this.mBackgroundScheduler).subscribe(FetchCurrenciesOnCreateListener$$Lambda$1.lambdaFactory$(this), FetchCurrenciesOnCreateListener$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$onCreate$0(FetchCurrenciesOnCreateListener this_, Pair pair) {
        Response<Currencies> response = pair.first;
        if (response.isSuccessful()) {
            List<Data> currencies = ((Currencies) response.body()).getData();
            this_.mCurrenciesUpdatedConnector.registerCurrencies(currencies);
            this_.mCurrenciesUpdatedConnector.get().onNext(currencies);
        }
    }

    static /* synthetic */ void lambda$onCreate$1(Throwable t) {
    }
}
