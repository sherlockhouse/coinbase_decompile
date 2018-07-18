package com.google.android.gms.vision;

import android.util.SparseArray;

public final class zzc {
    private static final Object zzaqd = new Object();
    private static int zzkib = 0;
    private SparseArray<Integer> zzkic = new SparseArray();
    private SparseArray<Integer> zzkid = new SparseArray();

    public final int zzes(int i) {
        int intValue;
        synchronized (zzaqd) {
            Integer num = (Integer) this.zzkic.get(i);
            if (num != null) {
                intValue = num.intValue();
            } else {
                intValue = zzkib;
                zzkib++;
                this.zzkic.append(i, Integer.valueOf(intValue));
                this.zzkid.append(intValue, Integer.valueOf(i));
            }
        }
        return intValue;
    }
}
