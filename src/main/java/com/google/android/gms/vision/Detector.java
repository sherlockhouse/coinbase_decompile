package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame.Metadata;

public abstract class Detector<T> {
    private final Object zzkho = new Object();
    private Processor<T> zzkhp;

    public static class Detections<T> {
        private SparseArray<T> zzkhq;
        private Metadata zzkhr;
        private boolean zzkhs;

        public Detections(SparseArray<T> sparseArray, Metadata metadata, boolean z) {
            this.zzkhq = sparseArray;
            this.zzkhr = metadata;
            this.zzkhs = z;
        }

        public SparseArray<T> getDetectedItems() {
            return this.zzkhq;
        }
    }

    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public void receiveFrame(Frame frame) {
        synchronized (this.zzkho) {
            if (this.zzkhp == null) {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
            Metadata metadata = new Metadata(frame.getMetadata());
            metadata.zzbil();
            this.zzkhp.receiveDetections(new Detections(detect(frame), metadata, isOperational()));
        }
    }

    public void release() {
        synchronized (this.zzkho) {
            if (this.zzkhp != null) {
                this.zzkhp.release();
                this.zzkhp = null;
            }
        }
    }

    public void setProcessor(Processor<T> processor) {
        this.zzkhp = processor;
    }
}
