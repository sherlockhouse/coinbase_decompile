package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdck;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.internal.client.zza;
import com.google.android.gms.vision.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public final class FaceDetector extends Detector<Face> {
    private final Object mLock;
    private final zzc zzkit;
    private final zza zzkiu;
    private boolean zzkiv;

    public static class Builder {
        private final Context mContext;
        private int zzgdd = 0;
        private int zzkiw = 0;
        private boolean zzkix = false;
        private int zzkiy = 0;
        private boolean zzkiz = true;
        private float zzkja = -1.0f;

        public Builder(Context context) {
            this.mContext = context;
        }

        public FaceDetector build() {
            com.google.android.gms.vision.face.internal.client.zzc com_google_android_gms_vision_face_internal_client_zzc = new com.google.android.gms.vision.face.internal.client.zzc();
            com_google_android_gms_vision_face_internal_client_zzc.mode = this.zzgdd;
            com_google_android_gms_vision_face_internal_client_zzc.zzkjj = this.zzkiw;
            com_google_android_gms_vision_face_internal_client_zzc.zzkjk = this.zzkiy;
            com_google_android_gms_vision_face_internal_client_zzc.zzkjl = this.zzkix;
            com_google_android_gms_vision_face_internal_client_zzc.zzkjm = this.zzkiz;
            com_google_android_gms_vision_face_internal_client_zzc.zzkjn = this.zzkja;
            return new FaceDetector(new zza(this.mContext, com_google_android_gms_vision_face_internal_client_zzc));
        }

        public Builder setLandmarkType(int i) {
            if (i == 0 || i == 1) {
                this.zzkiw = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid landmark type: " + i);
        }

        public Builder setMode(int i) {
            switch (i) {
                case 0:
                case 1:
                    this.zzgdd = i;
                    return this;
                default:
                    throw new IllegalArgumentException("Invalid mode: " + i);
            }
        }

        public Builder setTrackingEnabled(boolean z) {
            this.zzkiz = z;
            return this;
        }
    }

    private FaceDetector() {
        this.zzkit = new zzc();
        this.mLock = new Object();
        this.zzkiv = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(zza com_google_android_gms_vision_face_internal_client_zza) {
        this.zzkit = new zzc();
        this.mLock = new Object();
        this.zzkiv = true;
        this.zzkiu = com_google_android_gms_vision_face_internal_client_zza;
    }

    public final SparseArray<Face> detect(Frame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        Face[] zzb;
        ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
        synchronized (this.mLock) {
            if (this.zzkiv) {
                zzb = this.zzkiu.zzb(grayscaleImageData, zzdck.zzc(frame));
            } else {
                throw new RuntimeException("Cannot use detector after release()");
            }
        }
        Set hashSet = new HashSet();
        SparseArray<Face> sparseArray = new SparseArray(zzb.length);
        int i = 0;
        for (Face face : zzb) {
            int id = face.getId();
            i = Math.max(i, id);
            if (hashSet.contains(Integer.valueOf(id))) {
                id = i + 1;
                i = id;
            }
            hashSet.add(Integer.valueOf(id));
            sparseArray.append(this.zzkit.zzes(id), face);
        }
        return sparseArray;
    }

    protected final void finalize() throws Throwable {
        try {
            synchronized (this.mLock) {
                if (this.zzkiv) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
        } finally {
            super.finalize();
        }
    }

    public final boolean isOperational() {
        return this.zzkiu.isOperational();
    }

    public final void release() {
        super.release();
        synchronized (this.mLock) {
            if (this.zzkiv) {
                this.zzkiu.zzbio();
                this.zzkiv = false;
                return;
            }
        }
    }
}
