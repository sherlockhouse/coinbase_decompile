package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.CalendarEvent;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.DriverLicense;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.GeoPoint;
import com.google.android.gms.vision.barcode.Barcode.Phone;
import com.google.android.gms.vision.barcode.Barcode.Sms;
import com.google.android.gms.vision.barcode.Barcode.UrlBookmark;
import com.google.android.gms.vision.barcode.Barcode.WiFi;

public final class zzb implements Creator<Barcode> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbcl.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        int i2 = 0;
        Point[] pointArr = null;
        Email email = null;
        Phone phone = null;
        Sms sms = null;
        WiFi wiFi = null;
        UrlBookmark urlBookmark = null;
        GeoPoint geoPoint = null;
        CalendarEvent calendarEvent = null;
        ContactInfo contactInfo = null;
        DriverLicense driverLicense = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = zzbcl.zzg(parcel, readInt);
                    break;
                case 3:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    i2 = zzbcl.zzg(parcel, readInt);
                    break;
                case 6:
                    pointArr = (Point[]) zzbcl.zzb(parcel, readInt, Point.CREATOR);
                    break;
                case 7:
                    email = (Email) zzbcl.zza(parcel, readInt, Email.CREATOR);
                    break;
                case 8:
                    phone = (Phone) zzbcl.zza(parcel, readInt, Phone.CREATOR);
                    break;
                case 9:
                    sms = (Sms) zzbcl.zza(parcel, readInt, Sms.CREATOR);
                    break;
                case 10:
                    wiFi = (WiFi) zzbcl.zza(parcel, readInt, WiFi.CREATOR);
                    break;
                case 11:
                    urlBookmark = (UrlBookmark) zzbcl.zza(parcel, readInt, UrlBookmark.CREATOR);
                    break;
                case 12:
                    geoPoint = (GeoPoint) zzbcl.zza(parcel, readInt, GeoPoint.CREATOR);
                    break;
                case 13:
                    calendarEvent = (CalendarEvent) zzbcl.zza(parcel, readInt, CalendarEvent.CREATOR);
                    break;
                case 14:
                    contactInfo = (ContactInfo) zzbcl.zza(parcel, readInt, ContactInfo.CREATOR);
                    break;
                case 15:
                    driverLicense = (DriverLicense) zzbcl.zza(parcel, readInt, DriverLicense.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new Barcode(i, str, str2, i2, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode[i];
    }
}
