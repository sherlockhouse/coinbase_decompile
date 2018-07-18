package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
    private /* synthetic */ zzd zznfb;
    private /* synthetic */ zzf zznfc;

    zzg(zzf com_google_firebase_iid_zzf, zzd com_google_firebase_iid_zzd) {
        this.zznfc = com_google_firebase_iid_zzf;
        this.zznfb = com_google_firebase_iid_zzd;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zznfc.zznfa.handleIntent(this.zznfb.intent);
        this.zznfb.finish();
    }
}
