package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

public final class zzr {
    private static String zzfzf = null;
    private static final int zzfzg = Process.myPid();

    public static String zzalk() {
        if (zzfzf == null) {
            zzfzf = zzch(zzfzg);
        }
        return zzfzf;
    }

    private static String zzch(int i) {
        ThreadPolicy allowThreadDiskReads;
        Closeable bufferedReader;
        Throwable th;
        String str = null;
        if (i > 0) {
            try {
                allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
                try {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    str = bufferedReader.readLine().trim();
                    zzn.closeQuietly(bufferedReader);
                } catch (IOException e) {
                    zzn.closeQuietly(bufferedReader);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    zzn.closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReader = str;
                zzn.closeQuietly(bufferedReader);
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = str;
                th = th4;
                zzn.closeQuietly(bufferedReader);
                throw th;
            }
        }
        return str;
    }
}
