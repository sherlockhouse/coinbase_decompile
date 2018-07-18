package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzbh {
    private final Object zzdef;
    private final List<String> zzfvq;

    private zzbh(Object obj) {
        this.zzdef = zzbp.zzu(obj);
        this.zzfvq = new ArrayList();
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(100).append(this.zzdef.getClass().getSimpleName()).append('{');
        int size = this.zzfvq.size();
        for (int i = 0; i < size; i++) {
            append.append((String) this.zzfvq.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }

    public final zzbh zzg(String str, Object obj) {
        List list = this.zzfvq;
        String str2 = (String) zzbp.zzu(str);
        String valueOf = String.valueOf(obj);
        list.add(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(valueOf).length()).append(str2).append("=").append(valueOf).toString());
        return this;
    }
}
