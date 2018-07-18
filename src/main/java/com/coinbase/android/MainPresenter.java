package com.coinbase.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.api.internal.models.status.Component;
import com.coinbase.api.internal.models.status.Status;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class MainPresenter {
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final CurrenciesUpdatedConnector mCurrenciesUpdatedConnector;
    private final Logger mLogger = LoggerFactory.getLogger(MainPresenter.class);
    private final LoginManager mLoginManager;
    private final MainRouter mRouter;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    MainPresenter(LoginManager loginManager, Application app, MainRouter router, CurrenciesUpdatedConnector currenciesUpdatedConnector, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mContext = app;
        this.mRouter = router;
        this.mCurrenciesUpdatedConnector = currenciesUpdatedConnector;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    public void onResume() {
        refreshCurrencyList();
        checkStatus();
    }

    public void onDestroy() {
        this.mSubscription.clear();
    }

    private void checkStatus() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        if (!Boolean.valueOf(prefs.getBoolean(Constants.SERVER_OUTAGE_MESSAGE_SHOWN, false)).booleanValue()) {
            this.mLoginManager.getClient().getCoinbaseStatus(new CallbackWithRetrofit<Status>() {
                public void onResponse(Call<Status> call, Response<Status> response, Retrofit retrofit) {
                    if (response.isSuccessful()) {
                        boolean showOutageMessage = false;
                        for (Component component : ((Status) response.body()).getComponents()) {
                            if (TextUtils.equals(component.getName(), Component.COINBASE_MOBILE) && TextUtils.equals(component.getStatus(), Component.MAJOR_OUTAGE)) {
                                showOutageMessage = true;
                                break;
                            }
                        }
                        if (showOutageMessage) {
                            MainPresenter.this.mRouter.routeServerOutageMessage();
                            prefs.edit().putBoolean(Constants.SERVER_OUTAGE_MESSAGE_SHOWN, true).apply();
                        }
                    }
                }

                public void onFailure(Call<Status> call, Throwable t) {
                }
            });
        }
    }

    private void refreshCurrencyList() {
        Observable<Pair<Response<Currencies>, Retrofit>> getCryptoCurrenciesObservable = this.mLoginManager.getClient().getCryptoCurrenciesRx();
        this.mSubscription.clear();
        this.mSubscription.add(getCryptoCurrenciesObservable.first().subscribeOn(this.mBackgroundScheduler).subscribe(MainPresenter$$Lambda$1.lambdaFactory$(this), MainPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$refreshCurrencyList$0(MainPresenter this_, Pair pair) {
        Response<Currencies> response = pair.first;
        if (response.isSuccessful()) {
            List<Data> currencies = ((Currencies) response.body()).getData();
            this_.mCurrenciesUpdatedConnector.registerCurrencies(currencies);
            this_.mCurrenciesUpdatedConnector.get().onNext(currencies);
        }
    }
}
