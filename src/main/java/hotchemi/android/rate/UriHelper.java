package hotchemi.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.Uri;

final class UriHelper {
    static Uri getGooglePlay(String packageName) {
        return packageName == null ? null : Uri.parse("https://play.google.com/store/apps/details?id=" + packageName);
    }

    static Uri getAmazonAppstore(String packageName) {
        return packageName == null ? null : Uri.parse("amzn://apps/android?p=" + packageName);
    }

    static boolean isPackageExists(Context context, String targetPackage) {
        for (ApplicationInfo packageInfo : context.getPackageManager().getInstalledApplications(0)) {
            if (packageInfo.packageName.equals(targetPackage)) {
                return true;
            }
        }
        return false;
    }
}
