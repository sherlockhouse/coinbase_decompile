package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;

public class GCMReceiver extends BroadcastReceiver {
    String LOGTAG = "MPGCMReceiver";

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            handleRegistrationIntent(intent);
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            handleNotificationIntent(context, intent);
        }
    }

    private void handleRegistrationIntent(Intent intent) {
        final String registration = intent.getStringExtra("registration_id");
        if (intent.getStringExtra("error") != null) {
            Log.e(this.LOGTAG, "Error when registering for GCM: " + intent.getStringExtra("error"));
        } else if (registration != null) {
            if (MPConfig.DEBUG) {
                Log.d(this.LOGTAG, "Registering GCM ID: " + registration);
            }
            MixpanelAPI.allInstances(new InstanceProcessor() {
                public void process(MixpanelAPI api) {
                    api.getPeople().setPushRegistrationId(registration);
                }
            });
        } else if (intent.getStringExtra("unregistered") != null) {
            if (MPConfig.DEBUG) {
                Log.d(this.LOGTAG, "Unregistering from GCM");
            }
            MixpanelAPI.allInstances(new InstanceProcessor() {
                public void process(MixpanelAPI api) {
                    api.getPeople().clearPushRegistrationId();
                }
            });
        }
    }

    private void handleNotificationIntent(Context context, Intent intent) {
        String message = intent.getExtras().getString("mp_message");
        if (message != null) {
            if (MPConfig.DEBUG) {
                Log.d(this.LOGTAG, "MP GCM notification received: " + message);
            }
            PackageManager manager = context.getPackageManager();
            Intent appIntent = manager.getLaunchIntentForPackage(context.getPackageName());
            CharSequence notificationTitle = "";
            int notificationIcon = 17301651;
            try {
                ApplicationInfo appInfo = manager.getApplicationInfo(context.getPackageName(), 0);
                notificationTitle = manager.getApplicationLabel(appInfo);
                notificationIcon = appInfo.icon;
            } catch (NameNotFoundException e) {
            }
            PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, appIntent, 134217728);
            if (VERSION.SDK_INT < 11) {
                showNotificationSDKLessThan11(context, contentIntent, notificationIcon, notificationTitle, message);
            } else {
                showNotificationSDK11OrHigher(context, contentIntent, notificationIcon, notificationTitle, message);
            }
        }
    }

    @TargetApi(8)
    private void showNotificationSDKLessThan11(Context context, PendingIntent intent, int notificationIcon, CharSequence title, CharSequence message) {
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        Notification n = new Notification(notificationIcon, message, System.currentTimeMillis());
        n.flags |= 16;
        n.setLatestEventInfo(context, title, message, intent);
        nm.notify(0, n);
    }

    @TargetApi(11)
    private void showNotificationSDK11OrHigher(Context context, PendingIntent intent, int notificationIcon, CharSequence title, CharSequence message) {
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        Notification n = runBuilder(new Builder(context).setSmallIcon(notificationIcon).setTicker(message).setWhen(System.currentTimeMillis()).setContentTitle(title).setContentText(message).setContentIntent(intent));
        n.flags |= 16;
        nm.notify(0, n);
    }

    @SuppressLint({"NewApi"})
    private Notification runBuilder(Builder builder) {
        if (VERSION.SDK_INT < 16) {
            return builder.getNotification();
        }
        return builder.build();
    }
}
