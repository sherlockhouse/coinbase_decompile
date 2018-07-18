package com.google.android.gms.common.internal;

import android.content.Intent;
import android.support.v4.app.Fragment;

final class zzw extends zzu {
    private /* synthetic */ Fragment val$fragment;
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ int val$requestCode;

    zzw(Intent intent, Fragment fragment, int i) {
        this.val$intent = intent;
        this.val$fragment = fragment;
        this.val$requestCode = i;
    }

    public final void zzaka() {
        if (this.val$intent != null) {
            this.val$fragment.startActivityForResult(this.val$intent, this.val$requestCode);
        }
    }
}
