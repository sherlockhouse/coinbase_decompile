package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;

public class NotificationCompatBase {

    public static abstract class Action {

        public interface Factory {
        }

        public abstract PendingIntent getActionIntent();

        public abstract boolean getAllowGeneratedReplies();

        public abstract RemoteInput[] getDataOnlyRemoteInputs();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();
    }
}
