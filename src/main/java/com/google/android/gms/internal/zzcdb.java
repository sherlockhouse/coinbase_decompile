package com.google.android.gms.internal;

import android.os.Binder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.zzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class zzcdb extends zzcbp {
    private final zzccw zziki;
    private Boolean zziuh;
    private String zziui;

    public zzcdb(zzccw com_google_android_gms_internal_zzccw) {
        this(com_google_android_gms_internal_zzccw, null);
    }

    private zzcdb(zzccw com_google_android_gms_internal_zzccw, String str) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        this.zziki = com_google_android_gms_internal_zzccw;
        this.zziui = null;
    }

    private final void zzb(zzcas com_google_android_gms_internal_zzcas, boolean z) {
        zzbp.zzu(com_google_android_gms_internal_zzcas);
        zzf(com_google_android_gms_internal_zzcas.packageName, false);
        this.zziki.zzauh().zzkb(com_google_android_gms_internal_zzcas.zzilu);
    }

    private final void zzf(String str, boolean z) {
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            this.zziki.zzaul().zzayd().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zziuh == null) {
                    if ("com.google.android.gms".equals(this.zziui) || zzw.zzf(this.zziki.getContext(), Binder.getCallingUid()) || zzp.zzbz(this.zziki.getContext()).zzbo(Binder.getCallingUid())) {
                        z2 = true;
                    }
                    this.zziuh = Boolean.valueOf(z2);
                }
                if (this.zziuh.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zziki.zzaul().zzayd().zzj("Measurement Service called with invalid calling package. appId", zzcbw.zzjf(str));
                throw e;
            }
        }
        if (this.zziui == null && zzo.zzb(this.zziki.getContext(), Binder.getCallingUid(), str)) {
            this.zziui = str;
        }
        if (!str.equals(this.zziui)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }

    public final List<zzcft> zza(zzcas com_google_android_gms_internal_zzcas, boolean z) {
        Object e;
        zzb(com_google_android_gms_internal_zzcas, false);
        try {
            List<zzcfv> list = (List) this.zziki.zzauk().zzd(new zzcdq(this, com_google_android_gms_internal_zzcas)).get();
            List<zzcft> arrayList = new ArrayList(list.size());
            for (zzcfv com_google_android_gms_internal_zzcfv : list) {
                if (z || !zzcfw.zzkd(com_google_android_gms_internal_zzcfv.mName)) {
                    arrayList.add(new zzcft(com_google_android_gms_internal_zzcfv));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e);
            return null;
        }
    }

    public final List<zzcav> zza(String str, String str2, zzcas com_google_android_gms_internal_zzcas) {
        Object e;
        zzb(com_google_android_gms_internal_zzcas, false);
        try {
            return (List) this.zziki.zzauk().zzd(new zzcdj(this, com_google_android_gms_internal_zzcas, str, str2)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.zziki.zzaul().zzayd().zzj("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }

    public final List<zzcft> zza(String str, String str2, String str3, boolean z) {
        Object e;
        zzf(str, true);
        try {
            List<zzcfv> list = (List) this.zziki.zzauk().zzd(new zzcdi(this, str, str2, str3)).get();
            List<zzcft> arrayList = new ArrayList(list.size());
            for (zzcfv com_google_android_gms_internal_zzcfv : list) {
                if (z || !zzcfw.zzkd(com_google_android_gms_internal_zzcfv.mName)) {
                    arrayList.add(new zzcft(com_google_android_gms_internal_zzcfv));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(str), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(str), e);
            return Collections.emptyList();
        }
    }

    public final List<zzcft> zza(String str, String str2, boolean z, zzcas com_google_android_gms_internal_zzcas) {
        Object e;
        zzb(com_google_android_gms_internal_zzcas, false);
        try {
            List<zzcfv> list = (List) this.zziki.zzauk().zzd(new zzcdh(this, com_google_android_gms_internal_zzcas, str, str2)).get();
            List<zzcft> arrayList = new ArrayList(list.size());
            for (zzcfv com_google_android_gms_internal_zzcfv : list) {
                if (z || !zzcfw.zzkd(com_google_android_gms_internal_zzcfv.mName)) {
                    arrayList.add(new zzcft(com_google_android_gms_internal_zzcfv));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.zziki.zzaul().zzayd().zze("Failed to get user attributes. appId", zzcbw.zzjf(com_google_android_gms_internal_zzcas.packageName), e);
            return Collections.emptyList();
        }
    }

    public final void zza(long j, String str, String str2, String str3) {
        this.zziki.zzauk().zzg(new zzcds(this, str2, str3, str, j));
    }

    public final void zza(zzcas com_google_android_gms_internal_zzcas) {
        zzb(com_google_android_gms_internal_zzcas, false);
        Runnable com_google_android_gms_internal_zzcdr = new zzcdr(this, com_google_android_gms_internal_zzcas);
        if (this.zziki.zzauk().zzays()) {
            com_google_android_gms_internal_zzcdr.run();
        } else {
            this.zziki.zzauk().zzg(com_google_android_gms_internal_zzcdr);
        }
    }

    public final void zza(zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzbp.zzu(com_google_android_gms_internal_zzcav.zzimh);
        zzb(com_google_android_gms_internal_zzcas, false);
        zzcav com_google_android_gms_internal_zzcav2 = new zzcav(com_google_android_gms_internal_zzcav);
        com_google_android_gms_internal_zzcav2.packageName = com_google_android_gms_internal_zzcas.packageName;
        if (com_google_android_gms_internal_zzcav.zzimh.getValue() == null) {
            this.zziki.zzauk().zzg(new zzcdd(this, com_google_android_gms_internal_zzcav2, com_google_android_gms_internal_zzcas));
        } else {
            this.zziki.zzauk().zzg(new zzcde(this, com_google_android_gms_internal_zzcav2, com_google_android_gms_internal_zzcas));
        }
    }

    public final void zza(zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzb(com_google_android_gms_internal_zzcas, false);
        this.zziki.zzauk().zzg(new zzcdl(this, com_google_android_gms_internal_zzcbk, com_google_android_gms_internal_zzcas));
    }

    public final void zza(zzcbk com_google_android_gms_internal_zzcbk, String str, String str2) {
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzbp.zzgg(str);
        zzf(str, true);
        this.zziki.zzauk().zzg(new zzcdm(this, com_google_android_gms_internal_zzcbk, str));
    }

    public final void zza(zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) {
        zzbp.zzu(com_google_android_gms_internal_zzcft);
        zzb(com_google_android_gms_internal_zzcas, false);
        if (com_google_android_gms_internal_zzcft.getValue() == null) {
            this.zziki.zzauk().zzg(new zzcdo(this, com_google_android_gms_internal_zzcft, com_google_android_gms_internal_zzcas));
        } else {
            this.zziki.zzauk().zzg(new zzcdp(this, com_google_android_gms_internal_zzcft, com_google_android_gms_internal_zzcas));
        }
    }

    public final byte[] zza(zzcbk com_google_android_gms_internal_zzcbk, String str) {
        Object e;
        zzbp.zzgg(str);
        zzbp.zzu(com_google_android_gms_internal_zzcbk);
        zzf(str, true);
        this.zziki.zzaul().zzayi().zzj("Log and bundle. event", this.zziki.zzaug().zzjc(com_google_android_gms_internal_zzcbk.name));
        long nanoTime = this.zziki.zzvx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zziki.zzauk().zze(new zzcdn(this, com_google_android_gms_internal_zzcbk, str)).get();
            if (bArr == null) {
                this.zziki.zzaul().zzayd().zzj("Log and bundle returned null. appId", zzcbw.zzjf(str));
                bArr = new byte[0];
            }
            this.zziki.zzaul().zzayi().zzd("Log and bundle processed. event, size, time_ms", this.zziki.zzaug().zzjc(com_google_android_gms_internal_zzcbk.name), Integer.valueOf(bArr.length), Long.valueOf((this.zziki.zzvx().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException e2) {
            e = e2;
            this.zziki.zzaul().zzayd().zzd("Failed to log and bundle. appId, event, error", zzcbw.zzjf(str), this.zziki.zzaug().zzjc(com_google_android_gms_internal_zzcbk.name), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zziki.zzaul().zzayd().zzd("Failed to log and bundle. appId, event, error", zzcbw.zzjf(str), this.zziki.zzaug().zzjc(com_google_android_gms_internal_zzcbk.name), e);
            return null;
        }
    }

    public final void zzb(zzcas com_google_android_gms_internal_zzcas) {
        zzb(com_google_android_gms_internal_zzcas, false);
        this.zziki.zzauk().zzg(new zzcdc(this, com_google_android_gms_internal_zzcas));
    }

    public final void zzb(zzcav com_google_android_gms_internal_zzcav) {
        zzbp.zzu(com_google_android_gms_internal_zzcav);
        zzbp.zzu(com_google_android_gms_internal_zzcav.zzimh);
        zzf(com_google_android_gms_internal_zzcav.packageName, true);
        zzcav com_google_android_gms_internal_zzcav2 = new zzcav(com_google_android_gms_internal_zzcav);
        if (com_google_android_gms_internal_zzcav.zzimh.getValue() == null) {
            this.zziki.zzauk().zzg(new zzcdf(this, com_google_android_gms_internal_zzcav2));
        } else {
            this.zziki.zzauk().zzg(new zzcdg(this, com_google_android_gms_internal_zzcav2));
        }
    }

    public final String zzc(zzcas com_google_android_gms_internal_zzcas) {
        zzb(com_google_android_gms_internal_zzcas, false);
        return this.zziki.zzjs(com_google_android_gms_internal_zzcas.packageName);
    }

    public final List<zzcav> zzj(String str, String str2, String str3) {
        Object e;
        zzf(str, true);
        try {
            return (List) this.zziki.zzauk().zzd(new zzcdk(this, str, str2, str3)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.zziki.zzaul().zzayd().zzj("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }
}
