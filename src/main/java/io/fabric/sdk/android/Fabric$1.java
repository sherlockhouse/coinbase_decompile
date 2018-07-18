package io.fabric.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;

class Fabric$1 extends Callbacks {
    final /* synthetic */ Fabric this$0;

    Fabric$1(Fabric this$0) {
        this.this$0 = this$0;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.this$0.setCurrentActivity(activity);
    }

    public void onActivityStarted(Activity activity) {
        this.this$0.setCurrentActivity(activity);
    }

    public void onActivityResumed(Activity activity) {
        this.this$0.setCurrentActivity(activity);
    }
}
