package com.coinbase.android;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.widgets.UpdateWidgetBalanceService;
import com.coinbase.android.widgets.UpdateWidgetPriceService;
import com.coinbase.android.widgets.UpdateWidgetPriceTask;
import com.coinbase.android.widgets.UpdateWidgetPriceTask.WidgetUpdater;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ServiceScope
public class PriceAppWidgetProvider extends AppWidgetProvider {
    @Inject
    LoginManager mLoginManager;
    @Inject
    MixpanelTracking mMixpanelTracking;

    public static class PriceWidgetUpdater implements WidgetUpdater {
        public void updateWidget(Context context, AppWidgetManager manager, int appWidgetId, String price) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_price);
            String currency = PreferenceManager.getDefaultSharedPreferences(context).getString(String.format(Constants.KEY_WIDGET_CURRENCY, new Object[]{Integer.valueOf(appWidgetId)}), "USD");
            setKeyguardPadding(context, manager, appWidgetId, views);
            views.setTextViewText(R.id.widget_price, price);
            views.setTextViewText(R.id.widget_currency, currency);
            Intent mainMenu = new Intent(context, MainActivity.class);
            mainMenu.setAction(MainActivity.ACTION_TRANSACTIONS);
            views.setOnClickPendingIntent(R.id.widget_icon, PendingIntent.getActivity(context, 0, mainMenu, 0));
            Intent refresh = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
            refresh.putExtra("appWidgetIds", new int[]{appWidgetId});
            refresh.setPackage(context.getPackageName());
            views.setOnClickPendingIntent(R.id.widget_refresh, PendingIntent.getBroadcast(context, appWidgetId, refresh, 0));
            Log.i("Coinbase", "Updating price widget " + appWidgetId + " with price " + price);
            manager.updateAppWidget(appWidgetId, views);
        }

        private void setKeyguardPadding(Context context, AppWidgetManager appWidgetManager, int appWidgetId, RemoteViews views) {
            if (appWidgetManager.getAppWidgetOptions(appWidgetId).getInt("appWidgetCategory", -1) == 2) {
                int padding = (int) (8.0f * context.getResources().getDisplayMetrics().density);
                views.setViewPadding(R.id.widget_outer, padding, padding, padding, padding);
            }
        }
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().coinbaseServiceSubcomponent().inject(this);
        for (int appWidgetId : appWidgetIds) {
            if (appWidgetManager.getAppWidgetInfo(appWidgetId) == null || appWidgetManager.getAppWidgetInfo(appWidgetId).provider.getClassName().equals(getClass().getName())) {
                new PriceWidgetUpdater().updateWidget(context, appWidgetManager, appWidgetId, null);
                if (VERSION.SDK_INT >= 26) {
                    new UpdateWidgetPriceTask(this.mLoginManager, this.mMixpanelTracking).run(appWidgetId, new PriceWidgetUpdater(), context, PriceAppWidgetProvider$$Lambda$1.lambdaFactory$());
                } else {
                    Intent service = new Intent(context, UpdateWidgetPriceService.class);
                    service.putExtra(UpdateWidgetBalanceService.EXTRA_UPDATER_CLASS, PriceWidgetUpdater.class);
                    service.putExtra(UpdateWidgetBalanceService.EXTRA_WIDGET_ID, appWidgetId);
                    context.startService(service);
                }
            } else {
                Log.w("Coinbase", "Received app widget broadcast for other provider " + appWidgetId);
            }
        }
    }

    static /* synthetic */ void lambda$onUpdate$0() {
    }
}
