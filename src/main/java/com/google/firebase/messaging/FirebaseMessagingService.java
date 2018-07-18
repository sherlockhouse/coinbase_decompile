package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzq;
import java.util.Iterator;

public class FirebaseMessagingService extends zzb {
    static boolean zzag(Bundle bundle) {
        return bundle == null ? false : "1".equals(bundle.getString("google.c.a.e"));
    }

    static void zzq(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(Intent intent) {
        Object obj;
        Object obj2 = null;
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        switch (action.hashCode()) {
            case 75300319:
                if (action.equals("com.google.firebase.messaging.NOTIFICATION_DISMISS")) {
                    int i = 1;
                    break;
                }
            case 366519424:
                if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                    obj = null;
                    break;
                }
            default:
                obj = -1;
                break;
        }
        String str;
        String str2;
        switch (obj) {
            case null:
                action = intent.getStringExtra("message_type");
                if (action == null) {
                    action = "gcm";
                }
                switch (action.hashCode()) {
                    case -2062414158:
                        if (action.equals("deleted_messages")) {
                            int i2 = 1;
                            break;
                        }
                    case 102161:
                        if (action.equals("gcm")) {
                            break;
                        }
                    case 814694033:
                        if (action.equals("send_error")) {
                            obj2 = 3;
                            break;
                        }
                    case 814800675:
                        if (action.equals("send_event")) {
                            obj2 = 2;
                            break;
                        }
                    default:
                        obj2 = -1;
                        break;
                }
                switch (obj2) {
                    case null:
                        if (zzag(intent.getExtras())) {
                            zzd.zzg(this, intent);
                        }
                        Bundle extras = intent.getExtras();
                        if (extras == null) {
                            extras = new Bundle();
                        }
                        extras.remove("android.support.content.wakelockid");
                        if (zza.zzad(extras)) {
                            if (!zza.zzeq(this).zzs(extras)) {
                                if (zzag(extras)) {
                                    zzd.zzj(this, intent);
                                }
                            } else {
                                return;
                            }
                        }
                        onMessageReceived(new RemoteMessage(extras));
                        return;
                    case 1:
                        onDeletedMessages();
                        return;
                    case 2:
                        onMessageSent(intent.getStringExtra("google.message_id"));
                        return;
                    case 3:
                        action = intent.getStringExtra("google.message_id");
                        if (action == null) {
                            action = intent.getStringExtra("message_id");
                        }
                        onSendError(action, new SendException(intent.getStringExtra("error")));
                        return;
                    default:
                        str = "FirebaseMessaging";
                        str2 = "Received message with unknown type: ";
                        action = String.valueOf(action);
                        Log.w(str, action.length() != 0 ? str2.concat(action) : new String(str2));
                        return;
                }
            case 1:
                if (zzag(intent.getExtras())) {
                    zzd.zzi(this, intent);
                    return;
                }
                return;
            default:
                str = "FirebaseMessaging";
                str2 = "Unknown intent action: ";
                action = String.valueOf(intent.getAction());
                Log.d(str, action.length() != 0 ? str2.concat(action) : new String(str2));
                return;
        }
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, Exception exception) {
    }

    protected final Intent zzn(Intent intent) {
        return zzq.zzcge().zzcgf();
    }

    public final boolean zzo(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzag(intent.getExtras())) {
            zzd.zzh(this, intent);
        }
        return true;
    }
}
