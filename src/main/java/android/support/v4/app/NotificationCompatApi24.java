package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatBase.Action;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

class NotificationCompatApi24 {

    public static class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private android.app.Notification.Builder b;
        private int mGroupAlertBehavior;

        public Builder(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList<String> people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, CharSequence[] remoteInputHistory, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, int groupAlertBehavior) {
            this.b = new android.app.Notification.Builder(context).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setExtras(extras).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion).setRemoteInputHistory(remoteInputHistory);
            if (contentView != null) {
                this.b.setCustomContentView(contentView);
            }
            if (bigContentView != null) {
                this.b.setCustomBigContentView(bigContentView);
            }
            if (headsUpContentView != null) {
                this.b.setCustomHeadsUpContentView(headsUpContentView);
            }
            Iterator it = people.iterator();
            while (it.hasNext()) {
                this.b.addPerson((String) it.next());
            }
            this.mGroupAlertBehavior = groupAlertBehavior;
        }

        public void addAction(Action action) {
            NotificationCompatApi24.addAction(this.b, action);
        }

        public android.app.Notification.Builder getBuilder() {
            return this.b;
        }

        public Notification build() {
            Notification notification = this.b.build();
            if (this.mGroupAlertBehavior != 0) {
                if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || this.mGroupAlertBehavior != 2)) {
                    removeSoundAndVibration(notification);
                }
                if (notification.getGroup() != null && (notification.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(notification);
                }
            }
            return notification;
        }

        private void removeSoundAndVibration(Notification notification) {
            notification.sound = null;
            notification.vibrate = null;
            notification.defaults &= -2;
            notification.defaults &= -3;
        }
    }

    public static void addAction(android.app.Notification.Builder b, Action action) {
        Bundle actionExtras;
        android.app.Notification.Action.Builder actionBuilder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (RemoteInput remoteInput : RemoteInputCompatApi20.fromCompat(action.getRemoteInputs())) {
                actionBuilder.addRemoteInput(remoteInput);
            }
        }
        if (action.getExtras() != null) {
            actionExtras = new Bundle(action.getExtras());
        } else {
            actionExtras = new Bundle();
        }
        actionExtras.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        actionBuilder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        actionBuilder.addExtras(actionExtras);
        b.addAction(actionBuilder.build());
    }
}
