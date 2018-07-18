package com.coinbase.android.ui;

import android.graphics.Rect;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.coinbase.android.ActivityScope;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

@ActivityScope
public class KeyboardListener {
    private static final double MIN_KEYBOARD_RATIO = 0.15d;
    private volatile OnGlobalLayoutListener mGlobalLayoutListener;
    private final RootViewWrapper mRootViewWrapper;

    @Inject
    public KeyboardListener(RootViewWrapper rootViewWrapper) {
        this.mRootViewWrapper = rootViewWrapper;
    }

    public Observable<Boolean> isKeyboardVisible() {
        View contentView = this.mRootViewWrapper.getRoot();
        if (contentView == null) {
            return Observable.create(KeyboardListener$$Lambda$1.lambdaFactory$());
        }
        return Observable.create(KeyboardListener$$Lambda$2.lambdaFactory$(this, contentView));
    }

    static /* synthetic */ void lambda$isKeyboardVisible$0(Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(false));
        subscriber.onCompleted();
    }

    static /* synthetic */ void lambda$isKeyboardVisible$2(KeyboardListener this_, View contentView, Subscriber subscriber) {
        this_.mGlobalLayoutListener = KeyboardListener$$Lambda$3.lambdaFactory$(this_, contentView, subscriber);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(this_.mGlobalLayoutListener);
    }

    static /* synthetic */ void lambda$null$1(KeyboardListener this_, View contentView, Subscriber subscriber) {
        Pair<Integer, Integer> keypadAndScreenHeight = this_.getKeypadAndScreenHeight(contentView);
        subscriber.onNext(Boolean.valueOf(((double) ((Integer) keypadAndScreenHeight.first).intValue()) > ((double) ((Integer) keypadAndScreenHeight.second).intValue()) * MIN_KEYBOARD_RATIO));
    }

    public boolean isViewObscuredByKeyboard(View view) {
        View contentView = this.mRootViewWrapper.getRoot();
        if (contentView == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationInWindow(location);
        float viewBottom = (float) (location[1] + view.getHeight());
        Pair<Integer, Integer> keypadAndScreenHeight = getKeypadAndScreenHeight(contentView);
        return ((float) (((Integer) keypadAndScreenHeight.second).intValue() - ((Integer) keypadAndScreenHeight.first).intValue())) < viewBottom;
    }

    private Pair<Integer, Integer> getKeypadAndScreenHeight(View contentView) {
        Rect r = new Rect();
        contentView.getWindowVisibleDisplayFrame(r);
        int screenHeight = contentView.getRootView().getHeight();
        return new Pair(Integer.valueOf(screenHeight - r.bottom), Integer.valueOf(screenHeight));
    }
}
