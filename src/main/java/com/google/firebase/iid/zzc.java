package com.google.firebase.iid;

import android.content.Intent;

final class zzc implements Runnable {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ Intent zzneu;
    private /* synthetic */ zzb zznev;

    zzc(zzb com_google_firebase_iid_zzb, Intent intent, Intent intent2) {
        this.zznev = com_google_firebase_iid_zzb;
        this.val$intent = intent;
        this.zzneu = intent2;
    }

    public final void run() {
        this.zznev.handleIntent(this.val$intent);
        this.zznev.zzm(this.zzneu);
    }
}
