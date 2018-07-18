package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.zzo;

public abstract class zzp<T> {
    private final String zzgpf;
    private T zzgpg;

    protected zzp(String str) {
        this.zzgpf = str;
    }

    protected final T zzcu(Context context) throws zzq {
        if (this.zzgpg == null) {
            zzbp.zzu(context);
            Context remoteContext = zzo.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zzq("Could not get remote context.");
            }
            try {
                this.zzgpg = zze((IBinder) remoteContext.getClassLoader().loadClass(this.zzgpf).newInstance());
            } catch (Throwable e) {
                throw new zzq("Could not load creator class.", e);
            } catch (Throwable e2) {
                throw new zzq("Could not instantiate creator.", e2);
            } catch (Throwable e22) {
                throw new zzq("Could not access creator.", e22);
            }
        }
        return this.zzgpg;
    }

    protected abstract T zze(IBinder iBinder);
}
