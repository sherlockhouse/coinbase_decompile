package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbcl;
import com.google.android.gms.vision.barcode.Barcode.Address;
import com.google.android.gms.vision.barcode.Barcode.ContactInfo;
import com.google.android.gms.vision.barcode.Barcode.Email;
import com.google.android.gms.vision.barcode.Barcode.PersonName;
import com.google.android.gms.vision.barcode.Barcode.Phone;

public final class zzf implements Creator<ContactInfo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Address[] addressArr = null;
        int zzd = zzbcl.zzd(parcel);
        String[] strArr = null;
        Email[] emailArr = null;
        Phone[] phoneArr = null;
        String str = null;
        String str2 = null;
        PersonName personName = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    personName = (PersonName) zzbcl.zza(parcel, readInt, PersonName.CREATOR);
                    break;
                case 3:
                    str2 = zzbcl.zzq(parcel, readInt);
                    break;
                case 4:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    phoneArr = (Phone[]) zzbcl.zzb(parcel, readInt, Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Email[]) zzbcl.zzb(parcel, readInt, Email.CREATOR);
                    break;
                case 7:
                    strArr = zzbcl.zzaa(parcel, readInt);
                    break;
                case 8:
                    addressArr = (Address[]) zzbcl.zzb(parcel, readInt, Address.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new ContactInfo(personName, str2, str, phoneArr, emailArr, strArr, addressArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ContactInfo[i];
    }
}
