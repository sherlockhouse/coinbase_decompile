package com.coinbase.android.ui;

import com.bluelinelabs.conductor.ControllerChangeHandler;

public interface ControllerTransitionContainer {
    ControllerChangeHandler dialogPopChangeHandler();

    ControllerChangeHandler dialogPushChangeHandler();

    int getTransitionDuration();

    ControllerChangeHandler modalPopChangeHandler();

    ControllerChangeHandler modalPushChangeHandler();

    ControllerChangeHandler popChangeHandler();

    ControllerChangeHandler pushChangeHandler();
}
