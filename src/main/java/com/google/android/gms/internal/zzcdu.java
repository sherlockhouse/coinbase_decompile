package com.google.android.gms.internal;

abstract class zzcdu extends zzcdt {
    private boolean zzdoe;

    zzcdu(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
        this.zziki.zzb(this);
    }

    public final void initialize() {
        if (this.zzdoe) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzuk();
        this.zziki.zzazj();
        this.zzdoe = true;
    }

    final boolean isInitialized() {
        return this.zzdoe;
    }

    protected abstract void zzuk();

    protected final void zzwk() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
