package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.internal.zzdcj;
import com.google.android.gms.internal.zzdck;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public final class zza extends zzdcj<zze> {
    private final zzc zzkjc;

    public zza(Context context, zzc com_google_android_gms_vision_face_internal_client_zzc) {
        super(context, "FaceNativeHandle");
        this.zzkjc = com_google_android_gms_vision_face_internal_client_zzc;
        zzbip();
    }

    private static Landmark[] zza(FaceParcel faceParcel) {
        int i = 0;
        LandmarkParcel[] landmarkParcelArr = faceParcel.zzkjf;
        if (landmarkParcelArr == null) {
            return new Landmark[0];
        }
        Landmark[] landmarkArr = new Landmark[landmarkParcelArr.length];
        while (i < landmarkParcelArr.length) {
            LandmarkParcel landmarkParcel = landmarkParcelArr[i];
            landmarkArr[i] = new Landmark(new PointF(landmarkParcel.x, landmarkParcel.y), landmarkParcel.type);
            i++;
        }
        return landmarkArr;
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, zzc {
        zzg com_google_android_gms_vision_face_internal_client_zzg;
        IBinder zzgv = dynamiteModule.zzgv("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator");
        if (zzgv == null) {
            com_google_android_gms_vision_face_internal_client_zzg = null;
        } else {
            IInterface queryLocalInterface = zzgv.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
            com_google_android_gms_vision_face_internal_client_zzg = queryLocalInterface instanceof zzg ? (zzg) queryLocalInterface : new zzh(zzgv);
        }
        return com_google_android_gms_vision_face_internal_client_zzg.zza(zzn.zzw(context), this.zzkjc);
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzdck com_google_android_gms_internal_zzdck) {
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zze) zzbip()).zzc(zzn.zzw(byteBuffer), com_google_android_gms_internal_zzdck);
            Face[] faceArr = new Face[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                FaceParcel faceParcel = zzc[i];
                faceArr[i] = new Face(faceParcel.id, new PointF(faceParcel.centerX, faceParcel.centerY), faceParcel.width, faceParcel.height, faceParcel.zzkjd, faceParcel.zzkje, zza(faceParcel), faceParcel.zzkjg, faceParcel.zzkjh, faceParcel.zzkji);
            }
            return faceArr;
        } catch (Throwable e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    protected final void zzbim() throws RemoteException {
        ((zze) zzbip()).zzbin();
    }
}
