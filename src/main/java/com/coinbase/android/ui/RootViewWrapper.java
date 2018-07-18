package com.coinbase.android.ui;

import android.view.View;
import rx.functions.Func0;

public class RootViewWrapper<T extends View> {
    private final Func0<T>[] mRootViews;

    public RootViewWrapper(Func0<T>... rootViews) {
        this.mRootViews = rootViews;
    }

    public View getRoot() {
        View rootView = (View) this.mRootViews[0].call();
        for (int i = 1; i < this.mRootViews.length; i++) {
            View view = (View) this.mRootViews[i].call();
            if (view != null && view.getVisibility() == 0 && view.isShown()) {
                rootView = view;
            }
        }
        return rootView;
    }
}
