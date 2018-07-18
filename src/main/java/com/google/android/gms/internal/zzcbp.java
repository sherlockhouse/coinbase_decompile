package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzcbp extends zzec implements zzcbo {
    public zzcbp() {
        attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        List zza;
        switch (i) {
            case 1:
                zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzcft) zzed.zza(parcel, zzcft.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                zza = zza((zzcas) zzed.zza(parcel, zzcas.CREATOR), zzed.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzcav) zzed.zza(parcel, zzcav.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzcav) zzed.zza(parcel, zzcav.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza = zza(parcel.readString(), parcel.readString(), zzed.zza(parcel), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 15:
                zza = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzed.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 16:
                zza = zza(parcel.readString(), parcel.readString(), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 17:
                zza = zzj(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            default:
                return false;
        }
        return true;
    }
}
