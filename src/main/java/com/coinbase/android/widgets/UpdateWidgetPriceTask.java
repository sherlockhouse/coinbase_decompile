package com.coinbase.android.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.preference.PreferenceManager;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.price.Data;
import com.coinbase.v2.models.price.Price;
import org.joda.money.CurrencyUnit;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateWidgetPriceTask {
    private final LoginManager mLoginManager;
    private final MixpanelTracking mMixpanelTracking;

    public interface WidgetUpdater {
        void updateWidget(Context context, AppWidgetManager appWidgetManager, int i, String str);
    }

    public UpdateWidgetPriceTask(LoginManager loginManager, MixpanelTracking mixpanelTracking) {
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
    }

    public boolean run(int widgetId, WidgetUpdater updater, Context context, Runnable serviceCallback) {
        this.mMixpanelTracking.trackEventOnce(MixpanelTracking.EVENT_PRICE_WIDGET_ENABLED);
        if (this.mLoginManager.isSignedIn()) {
            try {
                String currencyCode = PreferenceManager.getDefaultSharedPreferences(context).getString(String.format(Constants.KEY_WIDGET_CURRENCY, new Object[]{Integer.valueOf(widgetId)}), "USD");
                String baseCurrency = Utils.lastSelectedCurrencyType(context).toString();
                CurrencyUnit currency = CurrencyUnit.getInstance(currencyCode);
                final AppWidgetManager manager = AppWidgetManager.getInstance(context);
                updater.updateWidget(context, manager, widgetId, null);
                final WidgetUpdater widgetUpdater = updater;
                final Context context2 = context;
                final int i = widgetId;
                final Runnable runnable = serviceCallback;
                this.mLoginManager.getClient().getSpotPrice(baseCurrency, currency.getCurrencyCode(), null, new CallbackWithRetrofit<Price>() {
                    public void onResponse(Call<Price> call, Response<Price> response, Retrofit retrofit) {
                        if (response.isSuccessful()) {
                            Data spotPrice = ((Price) response.body()).getData();
                            if (spotPrice.getAmount() != null) {
                                widgetUpdater.updateWidget(context2, manager, i, spotPrice.getAmount());
                            }
                        }
                        runnable.run();
                    }

                    public void onFailure(Call<Price> call, Throwable t) {
                        runnable.run();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        Utils.showMessage(context, (int) R.string.please_sign_in_widget, 0);
        return false;
    }
}
