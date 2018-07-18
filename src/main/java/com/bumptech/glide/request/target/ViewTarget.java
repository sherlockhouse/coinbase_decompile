package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static boolean isTagUsedAtLeastOnce = false;
    private static Integer tagId = null;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    private static class SizeDeterminer {
        private final List<SizeReadyCallback> cbs = new ArrayList();
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;

        private static class SizeDeterminerLayoutListener implements OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called listener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer != null) {
                    sizeDeterminer.checkCurrentDimens();
                }
                return true;
            }
        }

        SizeDeterminer(View view) {
            this.view = view;
        }

        private void notifyCbs(int width, int height) {
            for (SizeReadyCallback cb : this.cbs) {
                cb.onSizeReady(width, height);
            }
        }

        void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int currentWidth = getTargetWidth();
                int currentHeight = getTargetHeight();
                if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                    notifyCbs(currentWidth, currentHeight);
                    clearCallbacksAndListener();
                }
            }
        }

        void getSize(SizeReadyCallback cb) {
            int currentWidth = getTargetWidth();
            int currentHeight = getTargetHeight();
            if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                cb.onSizeReady(currentWidth, currentHeight);
                return;
            }
            if (!this.cbs.contains(cb)) {
                this.cbs.add(cb);
            }
            if (this.layoutListener == null) {
                ViewTreeObserver observer = this.view.getViewTreeObserver();
                this.layoutListener = new SizeDeterminerLayoutListener(this);
                observer.addOnPreDrawListener(this.layoutListener);
            }
        }

        void removeCallback(SizeReadyCallback cb) {
            this.cbs.remove(cb);
        }

        void clearCallbacksAndListener() {
            ViewTreeObserver observer = this.view.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.removeOnPreDrawListener(this.layoutListener);
            }
            this.layoutListener = null;
            this.cbs.clear();
        }

        private boolean isViewStateAndSizeValid(int width, int height) {
            return isViewStateValid() && isSizeValid(width) && isSizeValid(height);
        }

        private boolean isViewStateValid() {
            if ((this.view.getLayoutParams() == null || this.view.getLayoutParams().width <= 0 || this.view.getLayoutParams().height <= 0) && this.view.isLayoutRequested()) {
                return false;
            }
            return true;
        }

        private int getTargetHeight() {
            int verticalPadding = this.view.getPaddingTop() + this.view.getPaddingBottom();
            LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getHeight(), layoutParams != null ? layoutParams.height : 0, verticalPadding);
        }

        private int getTargetWidth() {
            int horizontalPadding = this.view.getPaddingLeft() + this.view.getPaddingRight();
            LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getWidth(), layoutParams != null ? layoutParams.width : 0, horizontalPadding);
        }

        private int getTargetDimen(int viewSize, int paramSize, int paddingSize) {
            int adjustedViewSize = viewSize - paddingSize;
            if (isSizeValid(adjustedViewSize)) {
                return adjustedViewSize;
            }
            if (paramSize == 0) {
                return 0;
            }
            if (paramSize == -2) {
                return Integer.MIN_VALUE;
            }
            return paramSize > 0 ? paramSize - paddingSize : 0;
        }

        private boolean isSizeValid(int size) {
            return size > 0 || size == Integer.MIN_VALUE;
        }
    }

    public ViewTarget(T view) {
        this.view = (View) Preconditions.checkNotNull(view);
        this.sizeDeterminer = new SizeDeterminer(view);
    }

    public void getSize(SizeReadyCallback cb) {
        this.sizeDeterminer.getSize(cb);
    }

    public void removeCallback(SizeReadyCallback cb) {
        this.sizeDeterminer.removeCallback(cb);
    }

    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        this.sizeDeterminer.clearCallbacksAndListener();
    }

    public void setRequest(Request request) {
        setTag(request);
    }

    public Request getRequest() {
        Request tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    private void setTag(Object tag) {
        if (tagId == null) {
            isTagUsedAtLeastOnce = true;
            this.view.setTag(tag);
            return;
        }
        this.view.setTag(tagId.intValue(), tag);
    }

    private Object getTag() {
        if (tagId == null) {
            return this.view.getTag();
        }
        return this.view.getTag(tagId.intValue());
    }
}
