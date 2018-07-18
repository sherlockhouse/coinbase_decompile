package com.google.android.gms.internal;

import java.util.Iterator;

final class zzcbi implements Iterator<String> {
    private Iterator<String> zzinp = this.zzinq.zzino.keySet().iterator();
    private /* synthetic */ zzcbh zzinq;

    zzcbi(zzcbh com_google_android_gms_internal_zzcbh) {
        this.zzinq = com_google_android_gms_internal_zzcbh;
    }

    public final boolean hasNext() {
        return this.zzinp.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzinp.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
