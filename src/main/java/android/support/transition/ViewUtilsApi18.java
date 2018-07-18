package android.support.transition;

import android.view.View;

class ViewUtilsApi18 extends ViewUtilsApi14 {
    ViewUtilsApi18() {
    }

    public ViewOverlayImpl getOverlay(View view) {
        return new ViewOverlayApi18(view);
    }

    public WindowIdImpl getWindowId(View view) {
        return new WindowIdApi18(view);
    }
}
