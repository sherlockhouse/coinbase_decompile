package android.support.v4.view;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class ViewCompat {
    static final ViewCompatBaseImpl IMPL;

    static class ViewCompatBaseImpl {
        static boolean sAccessibilityDelegateCheckFailed = false;
        static Field sAccessibilityDelegateField;
        private static Method sChildrenDrawingOrderMethod;
        private static Field sMinHeightField;
        private static boolean sMinHeightFieldFetched;
        private static Field sMinWidthField;
        private static boolean sMinWidthFieldFetched;
        private static WeakHashMap<View, String> sTransitionNameMap;
        WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap = null;

        ViewCompatBaseImpl() {
        }

        public void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
            v.setAccessibilityDelegate(delegate == null ? null : delegate.getBridge());
        }

        public boolean hasAccessibilityDelegate(View v) {
            boolean z = true;
            if (sAccessibilityDelegateCheckFailed) {
                return false;
            }
            if (sAccessibilityDelegateField == null) {
                try {
                    sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                    sAccessibilityDelegateField.setAccessible(true);
                } catch (Throwable th) {
                    sAccessibilityDelegateCheckFailed = true;
                    return false;
                }
            }
            try {
                if (sAccessibilityDelegateField.get(v) == null) {
                    z = false;
                }
                return z;
            } catch (Throwable th2) {
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
        }

        public boolean hasTransientState(View view) {
            return false;
        }

        public void setHasTransientState(View view, boolean hasTransientState) {
        }

        public void postInvalidateOnAnimation(View view) {
            view.postInvalidate();
        }

        public void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
            view.postInvalidate(left, top, right, bottom);
        }

        public void postOnAnimation(View view, Runnable action) {
            view.postDelayed(action, getFrameTime());
        }

        public void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
            view.postDelayed(action, getFrameTime() + delayMillis);
        }

        long getFrameTime() {
            return ValueAnimator.getFrameDelay();
        }

        public int getImportantForAccessibility(View view) {
            return 0;
        }

        public void setImportantForAccessibility(View view, int mode) {
        }

        public void setLayerPaint(View view, Paint paint) {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }

        public int getLayoutDirection(View view) {
            return 0;
        }

        public ViewParent getParentForAccessibility(View view) {
            return view.getParent();
        }

        public void setAccessibilityLiveRegion(View view, int mode) {
        }

        public int getPaddingStart(View view) {
            return view.getPaddingLeft();
        }

        public int getPaddingEnd(View view) {
            return view.getPaddingRight();
        }

        public void setPaddingRelative(View view, int start, int top, int end, int bottom) {
            view.setPadding(start, top, end, bottom);
        }

        public boolean hasOverlappingRendering(View view) {
            return true;
        }

        public int getMinimumWidth(View view) {
            if (!sMinWidthFieldFetched) {
                try {
                    sMinWidthField = View.class.getDeclaredField("mMinWidth");
                    sMinWidthField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                sMinWidthFieldFetched = true;
            }
            if (sMinWidthField != null) {
                try {
                    return ((Integer) sMinWidthField.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public int getMinimumHeight(View view) {
            if (!sMinHeightFieldFetched) {
                try {
                    sMinHeightField = View.class.getDeclaredField("mMinHeight");
                    sMinHeightField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                sMinHeightFieldFetched = true;
            }
            if (sMinHeightField != null) {
                try {
                    return ((Integer) sMinHeightField.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public ViewPropertyAnimatorCompat animate(View view) {
            if (this.mViewPropertyAnimatorCompatMap == null) {
                this.mViewPropertyAnimatorCompatMap = new WeakHashMap();
            }
            ViewPropertyAnimatorCompat vpa = (ViewPropertyAnimatorCompat) this.mViewPropertyAnimatorCompatMap.get(view);
            if (vpa != null) {
                return vpa;
            }
            vpa = new ViewPropertyAnimatorCompat(view);
            this.mViewPropertyAnimatorCompatMap.put(view, vpa);
            return vpa;
        }

        public String getTransitionName(View view) {
            if (sTransitionNameMap == null) {
                return null;
            }
            return (String) sTransitionNameMap.get(view);
        }

        public int getWindowSystemUiVisibility(View view) {
            return 0;
        }

        public void requestApplyInsets(View view) {
        }

        public void setElevation(View view, float elevation) {
        }

        public float getElevation(View view) {
            return 0.0f;
        }

        public void setTranslationZ(View view, float translationZ) {
        }

        public float getTranslationZ(View view) {
            return 0.0f;
        }

        public void setClipBounds(View view, Rect clipBounds) {
        }

        public Rect getClipBounds(View view) {
            return null;
        }

        public void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean enabled) {
            if (sChildrenDrawingOrderMethod == null) {
                try {
                    sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e) {
                    Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", e);
                }
                sChildrenDrawingOrderMethod.setAccessible(true);
            }
            try {
                sChildrenDrawingOrderMethod.invoke(viewGroup, new Object[]{Boolean.valueOf(enabled)});
            } catch (IllegalAccessException e2) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e2);
            } catch (IllegalArgumentException e3) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e3);
            } catch (InvocationTargetException e4) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e4);
            }
        }

        public boolean getFitsSystemWindows(View view) {
            return false;
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener listener) {
        }

        public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
            return insets;
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View v, WindowInsetsCompat insets) {
            return insets;
        }

        public boolean isPaddingRelative(View view) {
            return false;
        }

        public boolean isNestedScrollingEnabled(View view) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).isNestedScrollingEnabled();
            }
            return false;
        }

        public void setBackground(View view, Drawable background) {
            view.setBackgroundDrawable(background);
        }

        public ColorStateList getBackgroundTintList(View view) {
            return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintList() : null;
        }

        public void setBackgroundTintList(View view, ColorStateList tintList) {
            if (view instanceof TintableBackgroundView) {
                ((TintableBackgroundView) view).setSupportBackgroundTintList(tintList);
            }
        }

        public void setBackgroundTintMode(View view, Mode mode) {
            if (view instanceof TintableBackgroundView) {
                ((TintableBackgroundView) view).setSupportBackgroundTintMode(mode);
            }
        }

        public Mode getBackgroundTintMode(View view) {
            return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintMode() : null;
        }

        public void stopNestedScroll(View view) {
            if (view instanceof NestedScrollingChild) {
                ((NestedScrollingChild) view).stopNestedScroll();
            }
        }

        public boolean isLaidOut(View view) {
            return view.getWidth() > 0 && view.getHeight() > 0;
        }

        public float getZ(View view) {
            return getTranslationZ(view) + getElevation(view);
        }

        public boolean isAttachedToWindow(View view) {
            return view.getWindowToken() != null;
        }

        public boolean hasOnClickListeners(View view) {
            return false;
        }

        public void setScrollIndicators(View view, int indicators, int mask) {
        }

        public void offsetLeftAndRight(View view, int offset) {
            view.offsetLeftAndRight(offset);
            if (view.getVisibility() == 0) {
                tickleInvalidationFlag(view);
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    tickleInvalidationFlag((View) parent);
                }
            }
        }

        public void offsetTopAndBottom(View view, int offset) {
            view.offsetTopAndBottom(offset);
            if (view.getVisibility() == 0) {
                tickleInvalidationFlag(view);
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    tickleInvalidationFlag((View) parent);
                }
            }
        }

        private static void tickleInvalidationFlag(View view) {
            float y = view.getTranslationY();
            view.setTranslationY(1.0f + y);
            view.setTranslationY(y);
        }

        public void setPointerIcon(View view, PointerIconCompat pointerIcon) {
        }

        public Display getDisplay(View view) {
            if (isAttachedToWindow(view)) {
                return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
            }
            return null;
        }
    }

    static class ViewCompatApi15Impl extends ViewCompatBaseImpl {
        ViewCompatApi15Impl() {
        }

        public boolean hasOnClickListeners(View view) {
            return view.hasOnClickListeners();
        }
    }

    static class ViewCompatApi16Impl extends ViewCompatApi15Impl {
        ViewCompatApi16Impl() {
        }

        public boolean hasTransientState(View view) {
            return view.hasTransientState();
        }

        public void setHasTransientState(View view, boolean hasTransientState) {
            view.setHasTransientState(hasTransientState);
        }

        public void postInvalidateOnAnimation(View view) {
            view.postInvalidateOnAnimation();
        }

        public void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
            view.postInvalidateOnAnimation(left, top, right, bottom);
        }

        public void postOnAnimation(View view, Runnable action) {
            view.postOnAnimation(action);
        }

        public void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
            view.postOnAnimationDelayed(action, delayMillis);
        }

        public int getImportantForAccessibility(View view) {
            return view.getImportantForAccessibility();
        }

        public void setImportantForAccessibility(View view, int mode) {
            if (mode == 4) {
                mode = 2;
            }
            view.setImportantForAccessibility(mode);
        }

        public ViewParent getParentForAccessibility(View view) {
            return view.getParentForAccessibility();
        }

        public int getMinimumWidth(View view) {
            return view.getMinimumWidth();
        }

        public int getMinimumHeight(View view) {
            return view.getMinimumHeight();
        }

        public void requestApplyInsets(View view) {
            view.requestFitSystemWindows();
        }

        public boolean getFitsSystemWindows(View view) {
            return view.getFitsSystemWindows();
        }

        public boolean hasOverlappingRendering(View view) {
            return view.hasOverlappingRendering();
        }

        public void setBackground(View view, Drawable background) {
            view.setBackground(background);
        }
    }

    static class ViewCompatApi17Impl extends ViewCompatApi16Impl {
        ViewCompatApi17Impl() {
        }

        public void setLayerPaint(View view, Paint paint) {
            view.setLayerPaint(paint);
        }

        public int getLayoutDirection(View view) {
            return view.getLayoutDirection();
        }

        public int getPaddingStart(View view) {
            return view.getPaddingStart();
        }

        public int getPaddingEnd(View view) {
            return view.getPaddingEnd();
        }

        public void setPaddingRelative(View view, int start, int top, int end, int bottom) {
            view.setPaddingRelative(start, top, end, bottom);
        }

        public int getWindowSystemUiVisibility(View view) {
            return view.getWindowSystemUiVisibility();
        }

        public boolean isPaddingRelative(View view) {
            return view.isPaddingRelative();
        }

        public Display getDisplay(View view) {
            return view.getDisplay();
        }
    }

    static class ViewCompatApi18Impl extends ViewCompatApi17Impl {
        ViewCompatApi18Impl() {
        }

        public void setClipBounds(View view, Rect clipBounds) {
            view.setClipBounds(clipBounds);
        }

        public Rect getClipBounds(View view) {
            return view.getClipBounds();
        }
    }

    static class ViewCompatApi19Impl extends ViewCompatApi18Impl {
        ViewCompatApi19Impl() {
        }

        public void setAccessibilityLiveRegion(View view, int mode) {
            view.setAccessibilityLiveRegion(mode);
        }

        public void setImportantForAccessibility(View view, int mode) {
            view.setImportantForAccessibility(mode);
        }

        public boolean isLaidOut(View view) {
            return view.isLaidOut();
        }

        public boolean isAttachedToWindow(View view) {
            return view.isAttachedToWindow();
        }
    }

    static class ViewCompatApi21Impl extends ViewCompatApi19Impl {
        private static ThreadLocal<Rect> sThreadLocalRect;

        ViewCompatApi21Impl() {
        }

        public String getTransitionName(View view) {
            return view.getTransitionName();
        }

        public void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }

        public void setElevation(View view, float elevation) {
            view.setElevation(elevation);
        }

        public float getElevation(View view) {
            return view.getElevation();
        }

        public void setTranslationZ(View view, float translationZ) {
            view.setTranslationZ(translationZ);
        }

        public float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        public void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener listener) {
            if (listener == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        return (WindowInsets) WindowInsetsCompat.unwrap(listener.onApplyWindowInsets(view, WindowInsetsCompat.wrap(insets)));
                    }
                });
            }
        }

        public boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        public void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }

        public ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        public void setBackgroundTintList(View view, ColorStateList tintList) {
            view.setBackgroundTintList(tintList);
            if (VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean hasTint = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        public void setBackgroundTintMode(View view, Mode mode) {
            view.setBackgroundTintMode(mode);
            if (VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean hasTint = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        public Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
            WindowInsets unwrapped = (WindowInsets) WindowInsetsCompat.unwrap(insets);
            WindowInsets result = v.onApplyWindowInsets(unwrapped);
            if (result != unwrapped) {
                unwrapped = new WindowInsets(result);
            }
            return WindowInsetsCompat.wrap(unwrapped);
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View v, WindowInsetsCompat insets) {
            WindowInsets unwrapped = (WindowInsets) WindowInsetsCompat.unwrap(insets);
            WindowInsets result = v.dispatchApplyWindowInsets(unwrapped);
            if (result != unwrapped) {
                unwrapped = new WindowInsets(result);
            }
            return WindowInsetsCompat.wrap(unwrapped);
        }

        public float getZ(View view) {
            return view.getZ();
        }

        public void offsetLeftAndRight(View view, int offset) {
            Rect parentRect = getEmptyTempRect();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.offsetLeftAndRight(view, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        }

        public void offsetTopAndBottom(View view, int offset) {
            Rect parentRect = getEmptyTempRect();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.offsetTopAndBottom(view, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        }

        private static Rect getEmptyTempRect() {
            if (sThreadLocalRect == null) {
                sThreadLocalRect = new ThreadLocal();
            }
            Rect rect = (Rect) sThreadLocalRect.get();
            if (rect == null) {
                rect = new Rect();
                sThreadLocalRect.set(rect);
            }
            rect.setEmpty();
            return rect;
        }
    }

    static class ViewCompatApi23Impl extends ViewCompatApi21Impl {
        ViewCompatApi23Impl() {
        }

        public void setScrollIndicators(View view, int indicators, int mask) {
            view.setScrollIndicators(indicators, mask);
        }

        public void offsetLeftAndRight(View view, int offset) {
            view.offsetLeftAndRight(offset);
        }

        public void offsetTopAndBottom(View view, int offset) {
            view.offsetTopAndBottom(offset);
        }
    }

    static class ViewCompatApi24Impl extends ViewCompatApi23Impl {
        ViewCompatApi24Impl() {
        }

        public void setPointerIcon(View view, PointerIconCompat pointerIconCompat) {
            view.setPointerIcon((PointerIcon) (pointerIconCompat != null ? pointerIconCompat.getPointerIcon() : null));
        }
    }

    static class ViewCompatApi26Impl extends ViewCompatApi24Impl {
        ViewCompatApi26Impl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            IMPL = new ViewCompatApi26Impl();
        } else if (VERSION.SDK_INT >= 24) {
            IMPL = new ViewCompatApi24Impl();
        } else if (VERSION.SDK_INT >= 23) {
            IMPL = new ViewCompatApi23Impl();
        } else if (VERSION.SDK_INT >= 21) {
            IMPL = new ViewCompatApi21Impl();
        } else if (VERSION.SDK_INT >= 19) {
            IMPL = new ViewCompatApi19Impl();
        } else if (VERSION.SDK_INT >= 18) {
            IMPL = new ViewCompatApi18Impl();
        } else if (VERSION.SDK_INT >= 17) {
            IMPL = new ViewCompatApi17Impl();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new ViewCompatApi16Impl();
        } else if (VERSION.SDK_INT >= 15) {
            IMPL = new ViewCompatApi15Impl();
        } else {
            IMPL = new ViewCompatBaseImpl();
        }
    }

    public static void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
        IMPL.setAccessibilityDelegate(v, delegate);
    }

    public static boolean hasAccessibilityDelegate(View v) {
        return IMPL.hasAccessibilityDelegate(v);
    }

    public static boolean hasTransientState(View view) {
        return IMPL.hasTransientState(view);
    }

    public static void setHasTransientState(View view, boolean hasTransientState) {
        IMPL.setHasTransientState(view, hasTransientState);
    }

    public static void postInvalidateOnAnimation(View view) {
        IMPL.postInvalidateOnAnimation(view);
    }

    public static void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
        IMPL.postInvalidateOnAnimation(view, left, top, right, bottom);
    }

    public static void postOnAnimation(View view, Runnable action) {
        IMPL.postOnAnimation(view, action);
    }

    public static void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
        IMPL.postOnAnimationDelayed(view, action, delayMillis);
    }

    public static int getImportantForAccessibility(View view) {
        return IMPL.getImportantForAccessibility(view);
    }

    public static void setImportantForAccessibility(View view, int mode) {
        IMPL.setImportantForAccessibility(view, mode);
    }

    public static void setLayerPaint(View view, Paint paint) {
        IMPL.setLayerPaint(view, paint);
    }

    public static int getLayoutDirection(View view) {
        return IMPL.getLayoutDirection(view);
    }

    public static ViewParent getParentForAccessibility(View view) {
        return IMPL.getParentForAccessibility(view);
    }

    public static void setAccessibilityLiveRegion(View view, int mode) {
        IMPL.setAccessibilityLiveRegion(view, mode);
    }

    public static int getPaddingStart(View view) {
        return IMPL.getPaddingStart(view);
    }

    public static int getPaddingEnd(View view) {
        return IMPL.getPaddingEnd(view);
    }

    public static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
        IMPL.setPaddingRelative(view, start, top, end, bottom);
    }

    public static int getMinimumWidth(View view) {
        return IMPL.getMinimumWidth(view);
    }

    public static int getMinimumHeight(View view) {
        return IMPL.getMinimumHeight(view);
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        return IMPL.animate(view);
    }

    public static void setElevation(View view, float elevation) {
        IMPL.setElevation(view, elevation);
    }

    public static float getElevation(View view) {
        return IMPL.getElevation(view);
    }

    public static void setTranslationZ(View view, float translationZ) {
        IMPL.setTranslationZ(view, translationZ);
    }

    public static float getTranslationZ(View view) {
        return IMPL.getTranslationZ(view);
    }

    public static String getTransitionName(View view) {
        return IMPL.getTransitionName(view);
    }

    public static int getWindowSystemUiVisibility(View view) {
        return IMPL.getWindowSystemUiVisibility(view);
    }

    public static void requestApplyInsets(View view) {
        IMPL.requestApplyInsets(view);
    }

    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean enabled) {
        IMPL.setChildrenDrawingOrderEnabled(viewGroup, enabled);
    }

    public static boolean getFitsSystemWindows(View v) {
        return IMPL.getFitsSystemWindows(v);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean fitSystemWindows) {
        view.setFitsSystemWindows(fitSystemWindows);
    }

    public static void setOnApplyWindowInsetsListener(View v, OnApplyWindowInsetsListener listener) {
        IMPL.setOnApplyWindowInsetsListener(v, listener);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
        return IMPL.onApplyWindowInsets(view, insets);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat insets) {
        return IMPL.dispatchApplyWindowInsets(view, insets);
    }

    public static boolean hasOverlappingRendering(View view) {
        return IMPL.hasOverlappingRendering(view);
    }

    public static boolean isPaddingRelative(View view) {
        return IMPL.isPaddingRelative(view);
    }

    public static void setBackground(View view, Drawable background) {
        IMPL.setBackground(view, background);
    }

    public static ColorStateList getBackgroundTintList(View view) {
        return IMPL.getBackgroundTintList(view);
    }

    public static void setBackgroundTintList(View view, ColorStateList tintList) {
        IMPL.setBackgroundTintList(view, tintList);
    }

    public static Mode getBackgroundTintMode(View view) {
        return IMPL.getBackgroundTintMode(view);
    }

    public static void setBackgroundTintMode(View view, Mode mode) {
        IMPL.setBackgroundTintMode(view, mode);
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return IMPL.isNestedScrollingEnabled(view);
    }

    public static void stopNestedScroll(View view) {
        IMPL.stopNestedScroll(view);
    }

    public static boolean isLaidOut(View view) {
        return IMPL.isLaidOut(view);
    }

    public static float getZ(View view) {
        return IMPL.getZ(view);
    }

    public static void offsetTopAndBottom(View view, int offset) {
        IMPL.offsetTopAndBottom(view, offset);
    }

    public static void offsetLeftAndRight(View view, int offset) {
        IMPL.offsetLeftAndRight(view, offset);
    }

    public static void setClipBounds(View view, Rect clipBounds) {
        IMPL.setClipBounds(view, clipBounds);
    }

    public static Rect getClipBounds(View view) {
        return IMPL.getClipBounds(view);
    }

    public static boolean isAttachedToWindow(View view) {
        return IMPL.isAttachedToWindow(view);
    }

    public static boolean hasOnClickListeners(View view) {
        return IMPL.hasOnClickListeners(view);
    }

    public static void setScrollIndicators(View view, int indicators, int mask) {
        IMPL.setScrollIndicators(view, indicators, mask);
    }

    public static void setPointerIcon(View view, PointerIconCompat pointerIcon) {
        IMPL.setPointerIcon(view, pointerIcon);
    }

    public static Display getDisplay(View view) {
        return IMPL.getDisplay(view);
    }
}
