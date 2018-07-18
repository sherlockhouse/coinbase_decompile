package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.CalendarDateTime;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;

public final class zze implements Creator<CalendarEvent> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        CalendarDateTime calendarDateTime = null;
        int zzd = zzbcl.zzd(parcel);
        CalendarDateTime calendarDateTime2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str5 = zzbcl.zzq(parcel, readInt);
                    break;
                case 3:
                    str4 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str3 = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 6:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 7:
                    calendarDateTime2 = (CalendarDateTime) zzbcl.zza(parcel, readInt, CalendarDateTime.CREATOR);
                    break;
                case 8:
                    calendarDateTime = (CalendarDateTime) zzbcl.zza(parcel, readInt, CalendarDateTime.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new CalendarEvent(str5, str4, str3, str2, str, calendarDateTime2, calendarDateTime);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CalendarEvent[i];
    }
}
