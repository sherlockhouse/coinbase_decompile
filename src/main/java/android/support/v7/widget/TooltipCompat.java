package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

public class TooltipCompat {
    private static final ViewCompatImpl IMPL;

    private interface ViewCompatImpl {
        void setTooltipText(View view, CharSequence charSequence);
    }

    @TargetApi(26)
    private static class Api26ViewCompatImpl implements ViewCompatImpl {
        private Api26ViewCompatImpl() {
        }

        public void setTooltipText(View view, CharSequence tooltipText) {
            view.setTooltipText(tooltipText);
        }
    }

    private static class BaseViewCompatImpl implements ViewCompatImpl {
        private BaseViewCompatImpl() {
        }

        public void setTooltipText(View view, CharSequence tooltipText) {
            TooltipCompatHandler.setTooltipText(view, tooltipText);
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            IMPL = new Api26ViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    public static void setTooltipText(View view, CharSequence tooltipText) {
        IMPL.setTooltipText(view, tooltipText);
    }
}
