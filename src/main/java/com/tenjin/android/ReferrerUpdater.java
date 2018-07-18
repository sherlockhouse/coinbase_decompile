package com.tenjin.android;

import java.util.HashSet;
import java.util.Set;

class ReferrerUpdater {
    private static volatile ReferrerUpdater instance;
    private Set<OnReferrerListener> listeners = new HashSet();

    interface OnReferrerListener {
        void onReferrerUpdated();
    }

    static ReferrerUpdater getInstance() {
        ReferrerUpdater localInstance = instance;
        if (localInstance == null) {
            synchronized (ReferrerUpdater.class) {
                try {
                    localInstance = instance;
                    if (localInstance == null) {
                        ReferrerUpdater localInstance2 = new ReferrerUpdater();
                        try {
                            instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    private ReferrerUpdater() {
    }

    void notifyReferrerUpdated() {
        for (OnReferrerListener listener : this.listeners) {
            listener.onReferrerUpdated();
        }
    }
}
