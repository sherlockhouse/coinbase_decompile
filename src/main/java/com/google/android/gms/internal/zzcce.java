package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

final class zzcce implements Runnable {
    private final String mPackageName;
    private final URL zzbvn;
    private final byte[] zzgaj;
    private final zzccc zziqi;
    private final Map<String, String> zziqj;
    private /* synthetic */ zzcca zziqk;

    public zzcce(zzcca com_google_android_gms_internal_zzcca, String str, URL url, byte[] bArr, Map<String, String> map, zzccc com_google_android_gms_internal_zzccc) {
        this.zziqk = com_google_android_gms_internal_zzcca;
        zzbp.zzgg(str);
        zzbp.zzu(url);
        zzbp.zzu(com_google_android_gms_internal_zzccc);
        this.zzbvn = url;
        this.zzgaj = bArr;
        this.zziqi = com_google_android_gms_internal_zzccc;
        this.mPackageName = str;
        this.zziqj = map;
    }

    public final void run() {
        OutputStream outputStream;
        Throwable e;
        Map map;
        int i;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream2;
        Throwable th;
        int i2 = 0;
        this.zziqk.zzatw();
        HttpURLConnection httpURLConnection2;
        Map map2;
        try {
            URLConnection openConnection = this.zzbvn.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                httpURLConnection2 = (HttpURLConnection) openConnection;
                httpURLConnection2.setDefaultUseCaches(false);
                zzcax.zzawf();
                httpURLConnection2.setConnectTimeout(60000);
                zzcax.zzawg();
                httpURLConnection2.setReadTimeout(61000);
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setDoInput(true);
                try {
                    if (this.zziqj != null) {
                        for (Entry entry : this.zziqj.entrySet()) {
                            httpURLConnection2.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    if (this.zzgaj != null) {
                        byte[] zzp = this.zziqk.zzauh().zzp(this.zzgaj);
                        this.zziqk.zzaul().zzayj().zzj("Uploading data. size", Integer.valueOf(zzp.length));
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.addRequestProperty("Content-Encoding", "gzip");
                        httpURLConnection2.setFixedLengthStreamingMode(zzp.length);
                        httpURLConnection2.connect();
                        outputStream = httpURLConnection2.getOutputStream();
                        try {
                            outputStream.write(zzp);
                            outputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            map = null;
                            i = 0;
                            OutputStream outputStream3 = outputStream;
                            httpURLConnection = httpURLConnection2;
                            outputStream2 = outputStream3;
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (IOException e3) {
                                    this.zziqk.zzaul().zzayd().zze("Error closing HTTP compressed POST connection output stream. appId", zzcbw.zzjf(this.mPackageName), e3);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i, e, null, map));
                        } catch (Throwable th2) {
                            th = th2;
                            map2 = null;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e4) {
                                    this.zziqk.zzaul().zzayd().zze("Error closing HTTP compressed POST connection output stream. appId", zzcbw.zzjf(this.mPackageName), e4);
                                }
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i2, null, null, map2));
                            throw th;
                        }
                    }
                    i2 = httpURLConnection2.getResponseCode();
                    map2 = httpURLConnection2.getHeaderFields();
                } catch (IOException e5) {
                    e = e5;
                    map = null;
                    i = i2;
                    httpURLConnection = httpURLConnection2;
                    outputStream2 = null;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i, e, null, map));
                } catch (Throwable th3) {
                    th = th3;
                    map2 = null;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i2, null, null, map2));
                    throw th;
                }
                try {
                    byte[] zza = zzcca.zzc(httpURLConnection2);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i2, null, zza, map2));
                    return;
                } catch (IOException e6) {
                    e = e6;
                    map = map2;
                    i = i2;
                    httpURLConnection = httpURLConnection2;
                    outputStream2 = null;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i, e, null, map));
                } catch (Throwable th32) {
                    th = th32;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i2, null, null, map2));
                    throw th;
                }
            }
            throw new IOException("Failed to obtain HTTP connection");
        } catch (IOException e7) {
            e = e7;
            map = null;
            i = 0;
            outputStream2 = null;
            httpURLConnection = null;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i, e, null, map));
        } catch (Throwable th4) {
            th = th4;
            map2 = null;
            outputStream = null;
            httpURLConnection2 = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            this.zziqk.zzauk().zzg(new zzccd(this.mPackageName, this.zziqi, i2, null, null, map2));
            throw th;
        }
    }
}
