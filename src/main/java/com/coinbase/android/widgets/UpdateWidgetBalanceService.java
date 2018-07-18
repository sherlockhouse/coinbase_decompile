package com.coinbase.android.widgets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.widgets.UpdateWidgetBalanceTask.WidgetUpdater;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ActivityScope
public class UpdateWidgetBalanceService extends Service {
    public static String EXTRA_UPDATER_CLASS = "updater_class";
    public static String EXTRA_WIDGET_ID = "widget_id";
    @Inject
    LoginManager mLoginManager;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;

    public void onCreate() {
        super.onCreate();
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseServiceSubcomponent().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        int widgetId = intent.getIntExtra(EXTRA_WIDGET_ID, -1);
        Class<?> updaterClass = (Class) intent.getSerializableExtra(EXTRA_UPDATER_CLASS);
        if (updaterClass != null) {
            try {
                new UpdateWidgetBalanceTask(this.mLoginManager, this.mMoneyFormatterUtil, this.mMixpanelTracking).run(widgetId, (WidgetUpdater) updaterClass.newInstance(), this, UpdateWidgetBalanceService$$Lambda$1.lambdaFactory$(this, startId));
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e2) {
            }
        }
        return 3;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
