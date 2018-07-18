package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build.VERSION;

public final class AppOpsManagerCompat {
    public static String permissionToOp(String permission) {
        if (VERSION.SDK_INT >= 23) {
            return AppOpsManager.permissionToOp(permission);
        }
        return null;
    }

    public static int noteProxyOp(Context context, String op, String proxiedPackageName) {
        if (VERSION.SDK_INT >= 23) {
            return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(op, proxiedPackageName);
        }
        return 1;
    }
}
