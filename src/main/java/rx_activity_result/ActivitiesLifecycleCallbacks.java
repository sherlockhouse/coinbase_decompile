package rx_activity_result;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;

class ActivitiesLifecycleCallbacks {
    ActivityLifecycleCallbacks activityLifecycleCallbacks;
    final Application application;
    volatile boolean emitted = false;
    volatile Activity liveActivityOrNull;

    public ActivitiesLifecycleCallbacks(Application application) {
        this.application = application;
        registerActivityLifeCycle();
    }

    private void registerActivityLifeCycle() {
        if (this.activityLifecycleCallbacks != null) {
            this.application.unregisterActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        }
        this.activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivitiesLifecycleCallbacks.this.liveActivityOrNull = activity;
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
                ActivitiesLifecycleCallbacks.this.liveActivityOrNull = activity;
            }

            public void onActivityPaused(Activity activity) {
                ActivitiesLifecycleCallbacks.this.liveActivityOrNull = null;
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            public void onActivityDestroyed(Activity activity) {
            }
        };
        this.application.registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
    }

    Activity getLiveActivity() {
        return this.liveActivityOrNull;
    }

    Observable<Activity> getOLiveActivity() {
        this.emitted = false;
        return Observable.interval(50, 50, TimeUnit.MILLISECONDS).map(new Func1<Long, Activity>() {
            public Activity call(Long aLong) {
                return ActivitiesLifecycleCallbacks.this.liveActivityOrNull;
            }
        }).takeWhile(new Func1<Activity, Boolean>() {
            public Boolean call(Activity activity) {
                boolean continueEmitting = true;
                if (ActivitiesLifecycleCallbacks.this.emitted) {
                    continueEmitting = false;
                }
                if (activity != null) {
                    ActivitiesLifecycleCallbacks.this.emitted = true;
                }
                return Boolean.valueOf(continueEmitting);
            }
        }).filter(new Func1<Activity, Boolean>() {
            public Boolean call(Activity activity) {
                return Boolean.valueOf(activity != null);
            }
        });
    }
}
