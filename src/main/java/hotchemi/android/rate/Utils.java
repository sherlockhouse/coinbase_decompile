package hotchemi.android.rate;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Build.VERSION;

final class Utils {
    static boolean underHoneyComb() {
        return VERSION.SDK_INT < 11;
    }

    static boolean isLollipop() {
        return VERSION.SDK_INT == 21 || VERSION.SDK_INT == 22;
    }

    static int getDialogTheme() {
        return isLollipop() ? R.style.CustomLollipopDialogStyle : 0;
    }

    @SuppressLint({"NewApi"})
    static Builder getDialogBuilder(Context context) {
        if (underHoneyComb()) {
            return new Builder(context);
        }
        return new Builder(context, getDialogTheme());
    }
}
