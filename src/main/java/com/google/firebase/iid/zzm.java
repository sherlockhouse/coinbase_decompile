package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzm extends Handler {
    private /* synthetic */ zzl zznfx;

    zzm(zzl com_google_firebase_iid_zzl, Looper looper) {
        this.zznfx = com_google_firebase_iid_zzl;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zznfx.zzc(message);
    }
}
