package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzeyl {
    private static Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Object zzott = new Object();

    public static boolean equals(long[] jArr, long[] jArr2) {
        return (jArr == null || jArr.length == 0) ? jArr2 == null || jArr2.length == 0 : Arrays.equals(jArr, jArr2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        boolean length = objArr == null ? false : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (z >= length || objArr[z] != null) {
                int i2 = i;
                while (i2 < length2 && objArr2[i2] == null) {
                    i2++;
                }
                boolean z2 = z >= length;
                boolean z3 = i2 >= length2;
                if (z2 && z3) {
                    return true;
                }
                if (z2 != z3 || !objArr[z].equals(objArr2[i2])) {
                    return false;
                }
                i = i2 + 1;
                z++;
            } else {
                z++;
            }
        }
    }

    public static int hashCode(long[] jArr) {
        return (jArr == null || jArr.length == 0) ? 0 : Arrays.hashCode(jArr);
    }

    public static int hashCode(Object[] objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr[i2];
            if (obj != null) {
                i = (i * 31) + obj.hashCode();
            }
        }
        return i;
    }

    public static void zza(zzeyh com_google_android_gms_internal_zzeyh, zzeyh com_google_android_gms_internal_zzeyh2) {
        if (com_google_android_gms_internal_zzeyh.zzotl != null) {
            com_google_android_gms_internal_zzeyh2.zzotl = (zzeyj) com_google_android_gms_internal_zzeyh.zzotl.clone();
        }
    }
}
