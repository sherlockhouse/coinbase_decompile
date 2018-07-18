package hotchemi.android.rate;

import android.app.Activity;
import android.content.Context;
import java.util.Date;

public final class AppRate {
    private static AppRate singleton;
    private final Context context;
    private int installDate = 10;
    private boolean isDebug = false;
    private int launchTimes = 10;
    private final DialogOptions options = new DialogOptions();
    private int remindInterval = 1;

    private AppRate(Context context) {
        this.context = context.getApplicationContext();
    }

    public static AppRate with(Context context) {
        if (singleton == null) {
            synchronized (AppRate.class) {
                if (singleton == null) {
                    singleton = new AppRate(context);
                }
            }
        }
        return singleton;
    }

    public static boolean showRateDialogIfMeetsConditions(Activity activity) {
        boolean isMeetsConditions = singleton.isDebug || singleton.shouldShowRateDialog();
        if (isMeetsConditions) {
            singleton.showRateDialog(activity);
        }
        return isMeetsConditions;
    }

    private static boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= ((long) ((((threshold * 24) * 60) * 60) * 1000));
    }

    public AppRate setLaunchTimes(int launchTimes) {
        this.launchTimes = launchTimes;
        return this;
    }

    public AppRate setInstallDays(int installDate) {
        this.installDate = installDate;
        return this;
    }

    public AppRate setRemindInterval(int remindInterval) {
        this.remindInterval = remindInterval;
        return this;
    }

    public AppRate setShowLaterButton(boolean isShowNeutralButton) {
        this.options.setShowNeutralButton(isShowNeutralButton);
        return this;
    }

    public AppRate clearSettingsParam() {
        PreferenceHelper.setAgreeShowDialog(this.context, true);
        PreferenceHelper.clearSharedPreferences(this.context);
        return this;
    }

    public AppRate setOnClickButtonListener(OnClickButtonListener listener) {
        this.options.setListener(listener);
        return this;
    }

    public AppRate setMessage(int resourceId) {
        this.options.setMessageResId(resourceId);
        return this;
    }

    public void monitor() {
        if (PreferenceHelper.isFirstLaunch(this.context)) {
            PreferenceHelper.setInstallDate(this.context);
        }
        PreferenceHelper.setLaunchTimes(this.context, PreferenceHelper.getLaunchTimes(this.context) + 1);
    }

    public void showRateDialog(Activity activity) {
        if (!activity.isFinishing()) {
            DialogManager.create(activity, this.options).show();
        }
    }

    public boolean shouldShowRateDialog() {
        return PreferenceHelper.getIsAgreeShowDialog(this.context) && isOverLaunchTimes() && isOverInstallDate() && isOverRemindDate();
    }

    private boolean isOverLaunchTimes() {
        return PreferenceHelper.getLaunchTimes(this.context) >= this.launchTimes;
    }

    private boolean isOverInstallDate() {
        return isOverDate(PreferenceHelper.getInstallDate(this.context), this.installDate);
    }

    private boolean isOverRemindDate() {
        return isOverDate(PreferenceHelper.getRemindInterval(this.context), this.remindInterval);
    }

    public AppRate setDebug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }
}
