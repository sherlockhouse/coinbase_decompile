package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;
import java.util.Set;

public class MultiProcessor<T> implements Processor<T> {
    private int zzkhu;
    private Factory<T> zzkig;
    private SparseArray<zza> zzkih;

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    public static class Builder<T> {
        private MultiProcessor<T> zzkii = new MultiProcessor();

        public Builder(Factory<T> factory) {
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            this.zzkii.zzkig = factory;
        }

        public MultiProcessor<T> build() {
            return this.zzkii;
        }
    }

    class zza {
        private Tracker<T> zzkht;
        private int zzkhx;

        private zza(MultiProcessor multiProcessor) {
            this.zzkhx = 0;
        }
    }

    private MultiProcessor() {
        this.zzkih = new SparseArray();
        this.zzkhu = 3;
    }

    private final void zza(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            zza com_google_android_gms_vision_MultiProcessor_zza = (zza) this.zzkih.get(keyAt);
            com_google_android_gms_vision_MultiProcessor_zza.zzkhx = 0;
            com_google_android_gms_vision_MultiProcessor_zza.zzkht.onUpdate(detections, valueAt);
        }
    }

    public void receiveDetections(Detections<T> detections) {
        int i = 0;
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i2 = 0; i2 < detectedItems.size(); i2++) {
            int keyAt = detectedItems.keyAt(i2);
            Object valueAt = detectedItems.valueAt(i2);
            if (this.zzkih.get(keyAt) == null) {
                zza com_google_android_gms_vision_MultiProcessor_zza = new zza();
                com_google_android_gms_vision_MultiProcessor_zza.zzkht = this.zzkig.create(valueAt);
                com_google_android_gms_vision_MultiProcessor_zza.zzkht.onNewItem(keyAt, valueAt);
                this.zzkih.append(keyAt, com_google_android_gms_vision_MultiProcessor_zza);
            }
        }
        detectedItems = detections.getDetectedItems();
        Set<Integer> hashSet = new HashSet();
        while (i < this.zzkih.size()) {
            int keyAt2 = this.zzkih.keyAt(i);
            if (detectedItems.get(keyAt2) == null) {
                zza com_google_android_gms_vision_MultiProcessor_zza2 = (zza) this.zzkih.valueAt(i);
                com_google_android_gms_vision_MultiProcessor_zza2.zzkhx = com_google_android_gms_vision_MultiProcessor_zza2.zzkhx + 1;
                if (com_google_android_gms_vision_MultiProcessor_zza2.zzkhx >= this.zzkhu) {
                    com_google_android_gms_vision_MultiProcessor_zza2.zzkht.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    com_google_android_gms_vision_MultiProcessor_zza2.zzkht.onMissing(detections);
                }
            }
            i++;
        }
        for (Integer intValue : hashSet) {
            this.zzkih.delete(intValue.intValue());
        }
        zza(detections);
    }

    public void release() {
        for (int i = 0; i < this.zzkih.size(); i++) {
            ((zza) this.zzkih.valueAt(i)).zzkht.onDone();
        }
        this.zzkih.clear();
    }
}
