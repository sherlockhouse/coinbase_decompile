package io.fabric.sdk.android.services.common;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public class ResponseParser {
    public static int parse(int statusCode) {
        if (statusCode >= Callback.DEFAULT_DRAG_ANIMATION_DURATION && statusCode <= 299) {
            return 0;
        }
        if (statusCode >= 300 && statusCode <= 399) {
            return 1;
        }
        if (statusCode >= 400 && statusCode <= 499) {
            return 0;
        }
        if (statusCode >= 500) {
            return 1;
        }
        return 1;
    }
}
