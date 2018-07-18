package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.coinbase.android.ui.NumericKeypadConnector;
import io.fabric.sdk.android.Fabric;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonUtils {
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new Comparator<File>() {
        public int compare(File file0, File file1) {
            return (int) (file0.lastModified() - file1.lastModified());
        }
    };
    private static final char[] HEX_VALUES = new char[]{NumericKeypadConnector.ZERO, '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Boolean clsTrace = null;
    private static long totalRamInBytes = -1;

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> matcher = null;

        static {
            matcher = new HashMap(4);
            matcher.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            String arch = Build.CPU_ABI;
            if (TextUtils.isEmpty(arch)) {
                Fabric.getLogger().d("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture value = (Architecture) matcher.get(arch.toLowerCase(Locale.US));
            if (value == null) {
                return UNKNOWN;
            }
            return value;
        }
    }

    public static java.lang.String getAppIconHashOrNull(android.content.Context r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r3 = 0;
        r1 = 0;
        r4 = r7.getResources();	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r5 = getAppIconResourceId(r7);	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r1 = r4.openRawResource(r5);	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r2 = sha1(r1);	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r4 = isNullOrEmpty(r2);	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        if (r4 == 0) goto L_0x0019;
    L_0x0018:
        r2 = r3;
    L_0x0019:
        r3 = "Failed to close icon input stream.";
        closeOrLog(r1, r3);
    L_0x001e:
        return r2;
    L_0x001f:
        r0 = move-exception;
        r4 = io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r5 = "Fabric";	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r6 = "Could not calculate hash for app icon.";	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r4.e(r5, r6, r0);	 Catch:{ Exception -> 0x001f, all -> 0x0032 }
        r4 = "Failed to close icon input stream.";
        closeOrLog(r1, r4);
        r2 = r3;
        goto L_0x001e;
    L_0x0032:
        r3 = move-exception;
        r4 = "Failed to close icon input stream.";
        closeOrLog(r1, r4);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.CommonUtils.getAppIconHashOrNull(android.content.Context):java.lang.String");
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String extractFieldFromSystemFile(File file, String fieldname) {
        Exception e;
        Throwable th;
        String toReturn = null;
        if (file.exists()) {
            BufferedReader br = null;
            try {
                BufferedReader br2 = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        String line = br2.readLine();
                        if (line == null) {
                            break;
                        }
                        String[] pieces = Pattern.compile("\\s*:\\s*").split(line, 2);
                        if (pieces.length > 1 && pieces[0].equals(fieldname)) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        br = br2;
                    } catch (Throwable th2) {
                        th = th2;
                        br = br2;
                    }
                }
                closeOrLog(br2, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                try {
                    Fabric.getLogger().e("Fabric", "Error parsing " + file, e);
                    closeOrLog(br, "Failed to close system file reader.");
                    return toReturn;
                } catch (Throwable th3) {
                    th = th3;
                    closeOrLog(br, "Failed to close system file reader.");
                    throw th;
                }
            }
        }
        return toReturn;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    public static synchronized long getTotalRamInBytes() {
        long j;
        synchronized (CommonUtils.class) {
            if (totalRamInBytes == -1) {
                long bytes = 0;
                String result = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(result)) {
                    result = result.toUpperCase(Locale.US);
                    try {
                        if (result.endsWith("KB")) {
                            bytes = convertMemInfoToBytes(result, "KB", 1024);
                        } else if (result.endsWith("MB")) {
                            bytes = convertMemInfoToBytes(result, "MB", 1048576);
                        } else if (result.endsWith("GB")) {
                            bytes = convertMemInfoToBytes(result, "GB", 1073741824);
                        } else {
                            Fabric.getLogger().d("Fabric", "Unexpected meminfo format while computing RAM: " + result);
                        }
                    } catch (NumberFormatException e) {
                        Fabric.getLogger().e("Fabric", "Unexpected meminfo format while computing RAM: " + result, e);
                    }
                }
                totalRamInBytes = bytes;
            }
            j = totalRamInBytes;
        }
        return j;
    }

    static long convertMemInfoToBytes(String memInfo, String notation, int notationMultiplier) {
        return Long.parseLong(memInfo.split(notation)[0].trim()) * ((long) notationMultiplier);
    }

    public static RunningAppProcessInfo getAppProcessInfo(String packageName, Context context) {
        List<RunningAppProcessInfo> processes = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (processes == null) {
            return null;
        }
        for (RunningAppProcessInfo info : processes) {
            if (info.processName.equals(packageName)) {
                return info;
            }
        }
        return null;
    }

    public static String streamToString(InputStream is) throws IOException {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String sha1(String source) {
        return hash(source, "SHA-1");
    }

    public static String sha256(String source) {
        return hash(source, "SHA-256");
    }

    public static String sha1(InputStream source) {
        return hash(source, "SHA-1");
    }

    private static String hash(String s, String algorithm) {
        return hash(s.getBytes(), algorithm);
    }

    private static String hash(InputStream source, String sha1Instance) {
        try {
            MessageDigest digest = MessageDigest.getInstance(sha1Instance);
            byte[] buffer = new byte[1024];
            while (true) {
                int length = source.read(buffer);
                if (length == -1) {
                    return hexify(digest.digest());
                }
                digest.update(buffer, 0, length);
            }
        } catch (Exception e) {
            Fabric.getLogger().e("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String hash(byte[] bytes, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(bytes);
            return hexify(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            Fabric.getLogger().e("Fabric", "Could not create hashing algorithm: " + algorithm + ", returning empty string.", e);
            return "";
        }
    }

    public static String createInstanceIdFrom(String... sliceIds) {
        if (sliceIds == null || sliceIds.length == 0) {
            return null;
        }
        List<String> sliceIdList = new ArrayList();
        for (String id : sliceIds) {
            if (id != null) {
                sliceIdList.add(id.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(sliceIdList);
        StringBuilder sb = new StringBuilder();
        for (String id2 : sliceIdList) {
            sb.append(id2);
        }
        String concatValue = sb.toString();
        if (concatValue.length() > 0) {
            return sha1(concatValue);
        }
        return null;
    }

    public static long calculateFreeRamInBytes(Context context) {
        MemoryInfo mi = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String path) {
        StatFs statFs = new StatFs(path);
        long blockSizeBytes = (long) statFs.getBlockSize();
        return (blockSizeBytes * ((long) statFs.getBlockCount())) - (blockSizeBytes * ((long) statFs.getAvailableBlocks()));
    }

    public static Float getBatteryLevel(Context context) {
        Intent battery = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (battery == null) {
            return null;
        }
        return Float.valueOf(((float) battery.getIntExtra("level", -1)) / ((float) battery.getIntExtra("scale", -1)));
    }

    public static boolean getProximitySensorEnabled(Context context) {
        if (isEmulator(context) || ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) == null) {
            return false;
        }
        return true;
    }

    public static void logControlled(Context context, String msg) {
        if (isClsTrace(context)) {
            Fabric.getLogger().d("Fabric", msg);
        }
    }

    public static void logControlledError(Context context, String msg, Throwable tr) {
        if (isClsTrace(context)) {
            Fabric.getLogger().e("Fabric", msg);
        }
    }

    public static void logControlled(Context context, int level, String tag, String msg) {
        if (isClsTrace(context)) {
            Fabric.getLogger().log(level, "Fabric", msg);
        }
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, "com.crashlytics.Trace", false));
        }
        return clsTrace.booleanValue();
    }

    public static boolean getBooleanResourceValue(Context context, String key, boolean defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return defaultValue;
        }
        int id = getResourcesIdentifier(context, key, "bool");
        if (id > 0) {
            return resources.getBoolean(id);
        }
        id = getResourcesIdentifier(context, key, "string");
        if (id > 0) {
            return Boolean.parseBoolean(context.getString(id));
        }
        return defaultValue;
    }

    public static int getResourcesIdentifier(Context context, String key, String resourceType) {
        return context.getResources().getIdentifier(key, resourceType, getResourcePackageName(context));
    }

    public static boolean isEmulator(Context context) {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean isRooted(Context context) {
        boolean isEmulator = isEmulator(context);
        String buildTags = Build.TAGS;
        if ((!isEmulator && buildTags != null && buildTags.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (isEmulator || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int getDeviceState(Context context) {
        int deviceState = 0;
        if (isEmulator(context)) {
            deviceState = 0 | 1;
        }
        if (isRooted(context)) {
            deviceState |= 2;
        }
        if (isDebuggerAttached()) {
            return deviceState | 4;
        }
        return deviceState;
    }

    public static int getBatteryVelocity(Context context, boolean powerConnected) {
        Float batteryLevel = getBatteryLevel(context);
        if (!powerConnected || batteryLevel == null) {
            return 1;
        }
        if (((double) batteryLevel.floatValue()) >= 99.0d) {
            return 3;
        }
        if (((double) batteryLevel.floatValue()) < 99.0d) {
            return 2;
        }
        return 0;
    }

    public static String hexify(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 255;
            hexChars[i * 2] = HEX_VALUES[v >>> 4];
            hexChars[(i * 2) + 1] = HEX_VALUES[v & 15];
        }
        return new String(hexChars);
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String getStringsFileValue(Context context, String key) {
        int id = getResourcesIdentifier(context, key, "string");
        if (id > 0) {
            return context.getString(id);
        }
        return "";
    }

    public static void closeOrLog(Closeable c, String message) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                Fabric.getLogger().e("Fabric", message, e);
            }
        }
    }

    public static void flushOrLog(Flushable f, String message) {
        if (f != null) {
            try {
                f.flush();
            } catch (IOException e) {
                Fabric.getLogger().e("Fabric", message, e);
            }
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static String padWithZerosToMaxIntWidth(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(value)}).replace(' ', NumericKeypadConnector.ZERO);
    }

    public static String getResourcePackageName(Context context) {
        int iconId = context.getApplicationContext().getApplicationInfo().icon;
        if (iconId > 0) {
            return context.getResources().getResourcePackageName(iconId);
        }
        return context.getPackageName();
    }

    public static void copyStream(InputStream is, OutputStream os, byte[] buffer) throws IOException {
        while (true) {
            int count = is.read(buffer);
            if (count != -1) {
                os.write(buffer, 0, count);
            } else {
                return;
            }
        }
    }

    public static int getAppIconResourceId(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String resolveBuildId(Context context) {
        int id = getResourcesIdentifier(context, "io.fabric.android.build_id", "string");
        if (id == 0) {
            id = getResourcesIdentifier(context, "com.crashlytics.android.build_id", "string");
        }
        if (id == 0) {
            return null;
        }
        String buildId = context.getResources().getString(id);
        Fabric.getLogger().d("Fabric", "Build ID is: " + buildId);
        return buildId;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean canTryConnection(Context context) {
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }
}
