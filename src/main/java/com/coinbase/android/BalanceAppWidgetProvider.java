package com.coinbase.android;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.widgets.UpdateWidgetBalanceService;
import com.coinbase.android.widgets.UpdateWidgetBalanceTask;
import com.coinbase.android.widgets.UpdateWidgetBalanceTask.WidgetUpdater;
import com.coinbase.android.widgets.WidgetCommon;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data.Type;
import javax.inject.Inject;

@ApplicationScope
public class BalanceAppWidgetProvider extends AppWidgetProvider {
    @Inject
    LoginManager mLoginManager;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;

    public static class BalanceWidgetUpdater implements WidgetUpdater {
        public void updateWidget(Context context, AppWidgetManager manager, int appWidgetId, String balance) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_balance);
            String accountType = PreferenceManager.getDefaultSharedPreferences(context).getString(String.format(Constants.KEY_WIDGET_ACCOUNT_TYPE, new Object[]{Integer.valueOf(appWidgetId)}), null);
            setKeyguardPadding(context, manager, appWidgetId, views);
            if (Type.toType(accountType) != Type.WALLET) {
                views.setViewVisibility(R.id.widget_scan, 8);
            }
            views.setTextViewText(R.id.widget_balance, balance);
            WidgetCommon.bindButtons(context, views, appWidgetId);
            Log.i("Coinbase", "Updating balance widget " + appWidgetId + " with balance " + balance);
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
                new BalanceWidgetUpdater().updateWidget(context, appWidgetManager, appWidgetId, null);
                if (VERSION.SDK_INT >= 26) {
                    new UpdateWidgetBalanceTask(this.mLoginManager, this.mMoneyFormatterUtil, this.mMixpanelTracking).run(appWidgetId, new BalanceWidgetUpdater(), context, BalanceAppWidgetProvider$$Lambda$1.lambdaFactory$());
                } else {
                    Intent service = new Intent(context, UpdateWidgetBalanceService.class);
                    service.putExtra(UpdateWidgetBalanceService.EXTRA_UPDATER_CLASS, BalanceWidgetUpdater.class);
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
