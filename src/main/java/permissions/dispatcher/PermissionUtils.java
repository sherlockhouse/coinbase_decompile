package permissions.dispatcher;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.util.SimpleArrayMap;

public final class PermissionUtils {
    private static final SimpleArrayMap<String, Integer> MIN_SDK_PERMISSIONS = new SimpleArrayMap(6);
    private static volatile int targetSdkVersion = -1;

    static {
        MIN_SDK_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", Integer.valueOf(14));
        MIN_SDK_PERMISSIONS.put("android.permission.BODY_SENSORS", Integer.valueOf(20));
        MIN_SDK_PERMISSIONS.put("android.permission.READ_CALL_LOG", Integer.valueOf(16));
        MIN_SDK_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", Integer.valueOf(16));
        MIN_SDK_PERMISSIONS.put("android.permission.USE_SIP", Integer.valueOf(9));
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", Integer.valueOf(16));
    }

    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean permissionExists(String permission) {
        Integer minVersion = (Integer) MIN_SDK_PERMISSIONS.get(permission);
        return minVersion == null || VERSION.SDK_INT >= minVersion.intValue();
    }

    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (permissionExists(permission) && !hasSelfPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasSelfPermission(Context context, String permission) {
        try {
            return PermissionChecker.checkSelfPermission(context, permission) == 0;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(4)
    public static int getTargetSdkVersion(Context context) {
        if (targetSdkVersion != -1) {
            return targetSdkVersion;
        }
        try {
            targetSdkVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (NameNotFoundException e) {
        }
        return targetSdkVersion;
    }
}
