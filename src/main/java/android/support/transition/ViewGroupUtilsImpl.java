package android.support.transition;

import android.view.ViewGroup;

interface ViewGroupUtilsImpl {
    ViewGroupOverlayImpl getOverlay(ViewGroup viewGroup);

    void suppressLayout(ViewGroup viewGroup, boolean z);
}
