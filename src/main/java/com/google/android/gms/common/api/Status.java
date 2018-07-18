package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class Status extends zzbck implements Result, ReflectedParcelable {
    public static final Creator<Status> CREATOR = new zzg();
    public static final Status zzfhv = new Status(0);
    public static final Status zzfhw = new Status(14);
    public static final Status zzfhx = new Status(8);
    public static final Status zzfhy = new Status(15);
    public static final Status zzfhz = new Status(16);
    private static Status zzfia = new Status(17);
    private static Status zzfib = new Status(18);
    private final PendingIntent mPendingIntent;
    private int zzdxs;
    private final int zzfac;
    private final String zzffg;

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.zzdxs = i;
        this.zzfac = i2;
        this.zzffg = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzdxs == status.zzdxs && this.zzfac == status.zzfac && zzbf.equal(this.zzffg, status.zzffg) && zzbf.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public final Status getStatus() {
        return this;
    }

    public final int getStatusCode() {
        return this.zzfac;
    }

    public final String getStatusMessage() {
        return this.zzffg;
    }

    public final boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzdxs), Integer.valueOf(this.zzfac), this.zzffg, this.mPendingIntent});
    }

    public final boolean isSuccess() {
        return this.zzfac <= 0;
    }

    public final void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("statusCode", zzafu()).zzg("resolution", this.mPendingIntent).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, getStatusCode());
        zzbcn.zza(parcel, 2, getStatusMessage(), false);
        zzbcn.zza(parcel, 3, this.mPendingIntent, i, false);
        zzbcn.zzc(parcel, 1000, this.zzdxs);
        zzbcn.zzai(parcel, zze);
    }

    public final String zzafu() {
        return this.zzffg != null ? this.zzffg : CommonStatusCodes.getStatusCodeString(this.zzfac);
    }
}
