package rx_activity_result;

import android.app.Activity;
import android.app.Application;

public final class RxActivityResult {
    static ActivitiesLifecycleCallbacks activitiesLifecycle;

    public static void register(Application application) {
        activitiesLifecycle = new ActivitiesLifecycleCallbacks(application);
    }

    public static <T extends Activity> Builder<T> on(T activity) {
        return new Builder(activity);
    }
}
