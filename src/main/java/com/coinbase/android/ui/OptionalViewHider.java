package com.coinbase.android.ui;

import android.view.View;
import com.coinbase.android.ActivityScope;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class OptionalViewHider {
    private final List<View> mEssentialViews = new LinkedList();
    private final KeyboardListener mKeyboardListener;
    private final Scheduler mMainScheduler;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private final HashMap<View, Integer> mVisibilityMap = new HashMap();

    @Inject
    public OptionalViewHider(KeyboardListener keyboardListener, @MainScheduler Scheduler mainScheduler) {
        this.mKeyboardListener = keyboardListener;
        this.mMainScheduler = mainScheduler;
    }

    public void registerViews(List<View> optionalViews, List<View> essentialViews) {
        this.mVisibilityMap.clear();
        this.mEssentialViews.clear();
        for (View view : optionalViews) {
            this.mVisibilityMap.put(view, Integer.valueOf(view.getVisibility()));
        }
        this.mEssentialViews.addAll(essentialViews);
        this.mSubscription.add(this.mKeyboardListener.isKeyboardVisible().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(OptionalViewHider$$Lambda$1.lambdaFactory$(this)));
    }

    public void clearOptionalViews() {
        this.mVisibilityMap.clear();
        this.mEssentialViews.clear();
    }

    private void updateViewsVisibility(boolean isVisible) {
        if (!isVisible) {
            for (View view : this.mVisibilityMap.keySet()) {
                view.setVisibility(((Integer) this.mVisibilityMap.get(view)).intValue());
            }
        } else if (isEssentialViewObscured()) {
            for (View view2 : this.mVisibilityMap.keySet()) {
                view2.setVisibility(8);
            }
        }
    }

    private boolean isEssentialViewObscured() {
        for (View view : this.mEssentialViews) {
            if (this.mKeyboardListener.isViewObscuredByKeyboard(view)) {
                return true;
            }
        }
        return false;
    }
}
