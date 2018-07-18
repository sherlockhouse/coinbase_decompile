package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class ConnectionResult extends zzbck {
    public static final Creator<ConnectionResult> CREATOR = new zzb();
    public static final ConnectionResult zzfff = new ConnectionResult(0);
    private final PendingIntent mPendingIntent;
    private int zzdxs;
    private final int zzfac;
    private final String zzffg;

    public ConnectionResult(int i) {
        this(i, null, null);
    }

    ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.zzdxs = i;
        this.zzfac = i2;
        this.mPendingIntent = pendingIntent;
        this.zzffg = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }

    static String getStatusString(int i) {
        switch (i) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            case 13:
                return "CANCELED";
            case 14:
                return "TIMEOUT";
            case 15:
                return "INTERRUPTED";
            case 16:
                return "API_UNAVAILABLE";
            case 17:
                return "SIGN_IN_FAILED";
            case 18:
                return "SERVICE_UPDATING";
            case 19:
                return "SERVICE_MISSING_PERMISSION";
            case 20:
                return "RESTRICTED_PROFILE";
            case 21:
                return "API_VERSION_UPDATE_REQUIRED";
            case 99:
                return "UNFINISHED";
            case 1500:
                return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
            default:
                return "UNKNOWN_ERROR_CODE(" + i + ")";
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.zzfac == connectionResult.zzfac && zzbf.equal(this.mPendingIntent, connectionResult.mPendingIntent) && zzbf.equal(this.zzffg, connectionResult.zzffg);
    }

    public final int getErrorCode() {
        return this.zzfac;
    }

    public final String getErrorMessage() {
        return this.zzffg;
    }

    public final PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public final boolean hasResolution() {
        return (this.zzfac == 0 || this.mPendingIntent == null) ? false : true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzfac), this.mPendingIntent, this.zzffg});
    }

    public final boolean isSuccess() {
        return this.zzfac == 0;
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("statusCode", getStatusString(this.zzfac)).zzg("resolution", this.mPendingIntent).zzg("message", this.zzffg).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxs);
        zzbcn.zzc(parcel, 2, getErrorCode());
        zzbcn.zza(parcel, 3, getResolution(), i, false);
        zzbcn.zza(parcel, 4, getErrorMessage(), false);
        zzbcn.zzai(parcel, zze);
    }
}
