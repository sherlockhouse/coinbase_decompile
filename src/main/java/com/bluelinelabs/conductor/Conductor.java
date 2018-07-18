package com.bluelinelabs.conductor;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.internal.LifecycleHandler;
import com.bluelinelabs.conductor.internal.ThreadUtils;

public final class Conductor {
    public static Router attachRouter(Activity activity, ViewGroup container, Bundle savedInstanceState) {
        ThreadUtils.ensureMainThread();
        Router router = LifecycleHandler.install(activity).getRouter(container, savedInstanceState);
        router.rebindIfNeeded();
        return router;
    }
}
