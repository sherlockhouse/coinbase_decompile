package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

public final class zzcf {
    private final Object zzfon;

    public final boolean isAndroid() {
        return this.zzfon instanceof Activity;
    }

    public final boolean zzaig() {
        return this.zzfon instanceof FragmentActivity;
    }

    public final Activity zzaih() {
        return (Activity) this.zzfon;
    }

    public final FragmentActivity zzaii() {
        return (FragmentActivity) this.zzfon;
    }
}
