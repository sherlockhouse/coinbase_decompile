package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;
import com.coinbase.android.ui.NumericKeypadConnector;

public final class NavUtils {
    public static boolean shouldUpRecreateTask(Activity sourceActivity, Intent targetIntent) {
        if (VERSION.SDK_INT >= 16) {
            return sourceActivity.shouldUpRecreateTask(targetIntent);
        }
        String action = sourceActivity.getIntent().getAction();
        return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
    }

    public static void navigateUpTo(Activity sourceActivity, Intent upIntent) {
        if (VERSION.SDK_INT >= 16) {
            sourceActivity.navigateUpTo(upIntent);
            return;
        }
        upIntent.addFlags(67108864);
        sourceActivity.startActivity(upIntent);
        sourceActivity.finish();
    }

    public static Intent getParentActivityIntent(Activity sourceActivity) {
        if (VERSION.SDK_INT >= 16) {
            Intent result = sourceActivity.getParentActivityIntent();
            if (result != null) {
                return result;
            }
        }
        String parentName = getParentActivityName(sourceActivity);
        if (parentName == null) {
            return null;
        }
        ComponentName target = new ComponentName(sourceActivity, parentName);
        try {
            Intent makeMainActivity;
            if (getParentActivityName(sourceActivity, target) == null) {
                makeMainActivity = Intent.makeMainActivity(target);
            } else {
                makeMainActivity = new Intent().setComponent(target);
            }
            return makeMainActivity;
        } catch (NameNotFoundException e) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + parentName + "' in manifest");
            return null;
        }
    }

    public static Intent getParentActivityIntent(Context context, ComponentName componentName) throws NameNotFoundException {
        String parentActivity = getParentActivityName(context, componentName);
        if (parentActivity == null) {
            return null;
        }
        Intent parentIntent;
        ComponentName target = new ComponentName(componentName.getPackageName(), parentActivity);
        if (getParentActivityName(context, target) == null) {
            parentIntent = Intent.makeMainActivity(target);
        } else {
            parentIntent = new Intent().setComponent(target);
        }
        return parentIntent;
    }

    public static String getParentActivityName(Activity sourceActivity) {
        try {
            return getParentActivityName(sourceActivity, sourceActivity.getComponentName());
        } catch (NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getParentActivityName(Context context, ComponentName componentName) throws NameNotFoundException {
        ActivityInfo info = context.getPackageManager().getActivityInfo(componentName, 128);
        if (VERSION.SDK_INT >= 16) {
            String result = info.parentActivityName;
            if (result != null) {
                return result;
            }
        }
        if (info.metaData == null) {
            return null;
        }
        String parentActivity = info.metaData.getString("android.support.PARENT_ACTIVITY");
        if (parentActivity == null) {
            return null;
        }
        if (parentActivity.charAt(0) == NumericKeypadConnector.DOT) {
            parentActivity = context.getPackageName() + parentActivity;
        }
        return parentActivity;
    }
}
