package com.coinbase.android.notifications.fcm;

import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlert;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts;
import com.coinbase.android.utils.PriceAlertUtils;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;

public class FcmMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String message = "";
        if (remoteMessage.getData() != null) {
            try {
                message = new JSONObject(remoteMessage.getData()).getString("body");
            } catch (Exception e) {
            }
        }
        if (remoteMessage.getNotification() != null) {
            message = remoteMessage.getNotification().getBody();
        }
        LocalPriceAlerts localPriceAlerts = PriceAlertUtils.getSavedPriceAlerts(this);
        if (localPriceAlerts != null && localPriceAlerts.getPriceAlerts() != null && !TextUtils.isEmpty(message)) {
            LocalPriceAlert localPriceAlert = null;
            for (LocalPriceAlert alert : localPriceAlerts.getPriceAlerts()) {
                if (message.toLowerCase().contains(alert.getNotificationMessage().toLowerCase())) {
                    localPriceAlert = LocalPriceAlert.builder().setAmount(alert.getAmount()).setCreatedOn(alert.getCreatedOn()).setCurrency(alert.getCurrency()).setCurrencyUnit(alert.getCurrencyUnit()).setId(alert.getId()).setIsAbove(alert.getIsAbove()).setDisplayText(alert.getDisplayText()).setNotificationTitle(alert.getNotificationTitle()).setNotificationMessage(alert.getNotificationMessage()).setTriggeredOn(System.currentTimeMillis()).setEnabled(false).build();
                    PriceAlertUtils.savePriceAlert(this, localPriceAlert);
                    break;
                }
            }
            if (localPriceAlert != null) {
                scheduleJob(localPriceAlert);
            }
        }
    }

    private void scheduleJob(LocalPriceAlert localPriceAlert) {
        Bundle bundle = new Bundle();
        bundle.putString("title", localPriceAlert.getNotificationTitle());
        bundle.putString("message", localPriceAlert.getNotificationMessage());
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        dispatcher.schedule(dispatcher.newJobBuilder().setService(MessageJobService.class).setRecurring(false).setTrigger(Trigger.executionWindow(0, 0)).setTag("messageJob").setExtras(bundle).build());
    }
}
