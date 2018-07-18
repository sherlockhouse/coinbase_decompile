package com.coinbase.android.widgets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.ServiceScope;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.widgets.UpdateWidgetPriceTask.WidgetUpdater;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ServiceScope
public class UpdateWidgetPriceService extends Service {
    public static String EXTRA_UPDATER_CLASS = "updater_class";
    public static String EXTRA_WIDGET_ID = "widget_id";
    @Inject
    LoginManager mLoginManager;
    @Inject
    MixpanelTracking mMixpanelTracking;

    public void onCreate() {
        super.onCreate();
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseServiceSubcomponent().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return 1;
        }
        try {
            if (new UpdateWidgetPriceTask(this.mLoginManager, this.mMixpanelTracking).run(intent.getIntExtra(EXTRA_WIDGET_ID, -1), (WidgetUpdater) ((Class) intent.getSerializableExtra(EXTRA_UPDATER_CLASS)).newInstance(), this, UpdateWidgetPriceService$$Lambda$1.lambdaFactory$(this, startId))) {
                return 3;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
