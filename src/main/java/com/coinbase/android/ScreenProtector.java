package com.coinbase.android;

import android.view.Window;
import com.coinbase.android.ui.ScreenshotAllowed;
import javax.inject.Inject;

@ActivityScope
public class ScreenProtector {
    private final Window mWindow;

    @Inject
    public ScreenProtector(Window window) {
        this.mWindow = window;
    }

    public void protect() {
        this.mWindow.setFlags(8192, 8192);
    }

    public void unprotect() {
        this.mWindow.clearFlags(8192);
    }

    public void conditionallyProtect(Class<?> clazz) {
        if (clazz.isAnnotationPresent(ScreenshotAllowed.class)) {
            unprotect();
        } else {
            protect();
        }
    }
}
