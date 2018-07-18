package com.google.firebase.iid;

import android.text.TextUtils;

public final class zzk {
    private static final Object zzaqd = new Object();
    private final zzr zznfv;

    zzk(zzr com_google_firebase_iid_zzr) {
        this.zznfv = com_google_firebase_iid_zzr;
    }

    final String zzcgc() {
        String str = null;
        synchronized (zzaqd) {
            String string = this.zznfv.zzhul.getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    final boolean zzra(String str) {
        boolean z;
        synchronized (zzaqd) {
            String string = this.zznfv.zzhul.getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.zznfv.zzhul.edit().putString("topic_operaion_queue", string.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
