package com.bluelinelabs.conductor.internal;

import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeCompletedListener;

public class NoOpControllerChangeHandler extends ControllerChangeHandler {
    public void performChange(ViewGroup container, View from, View to, boolean isPush, ControllerChangeCompletedListener changeListener) {
        changeListener.onChangeCompleted();
    }

    public ControllerChangeHandler copy() {
        return new NoOpControllerChangeHandler();
    }

    public boolean isReusable() {
        return true;
    }
}
