package com.coinbase.android.notifications.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import com.coinbase.android.Constants;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;
import com.coinbase.android.utils.PriceAlertUtils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MessageJobService extends JobService {
    static final String MESSAGE = "message";
    public static final int MESSAGE_NOTIFICATION_ID = 435346;
    static final String TITLE = "title";

    public boolean onStartJob(JobParameters job) {
        Bundle bundle = job.getExtras();
        if (bundle != null) {
            generateNotification(bundle.getString(TITLE), bundle.getString("message"));
            PriceAlertUtils.setHadPriceAlert(this, true);
        }
        return false;
    }

    public boolean onStopJob(JobParameters job) {
        return false;
    }

    private void generateNotification(String title, String message) {
        PendingIntent chartPendingIntent = generatePendingIntent(this, Constants.NOTIFICATION_CHART_FRAGMENT);
        PendingIntent buyPendingIntent = generatePendingIntent(this, Constants.NOTIFICATION_BUY_FRAGMENT);
        Builder builder = new Builder(this).setContentIntent(chartPendingIntent).setSmallIcon(R.drawable.ic_price_alert).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification)).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(message).setChannelId(Constants.NOTIFICATION_PRICE_ALERTS_CHANNEL).addAction(R.drawable.menu_buy_selected, "Buy", buyPendingIntent).addAction(R.drawable.menu_sell_selected, "Sell", generatePendingIntent(this, Constants.NOTIFICATION_SELL_FRAGMENT));
        NotificationManager mNotificationManager = (NotificationManager) getSystemService("notification");
        if (VERSION.SDK_INT >= 26) {
            mNotificationManager.createNotificationChannel(new NotificationChannel(Constants.NOTIFICATION_PRICE_ALERTS_CHANNEL, getString(R.string.price_alerts), 3));
        }
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, builder.build());
    }

    private PendingIntent generatePendingIntent(Context context, String action) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(action);
        intent.setFlags(603979776);
        return PendingIntent.getActivity(context, 0, intent, 268435456);
    }
}
