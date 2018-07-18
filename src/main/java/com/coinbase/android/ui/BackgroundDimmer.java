package com.coinbase.android.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.RelativeLayout;
import com.coinbase.android.ActivityScope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Qualifier;
import jp.wasabeef.blurry.Blurry;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class BackgroundDimmer {
    private final int BLUR_RADIUS = 25;
    private final int BLUR_SAMPLING = 2;
    private boolean mAnimated;
    private final BackNavigationConnector mBackNavigatorConnector;
    private final RelativeLayout mBlockingOverlayView;
    private View mBottomView;
    private int mBottomViewHeight;
    private ViewGroup mBottomViewParent;
    private boolean mDimmed;
    private int mGravity = 80;
    private Action0 mRunOnUndim = null;
    private StatusBarDimmer mStatusBarDimmer;
    private final StatusBarUpdater mStatusBarUpdater;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private boolean mUndimming = false;
    private final Window mWindow;

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface BlockingOverlay {
    }

    private class StatusBarDimmer {
        private final int mOriginalStatusBarColor;

        StatusBarDimmer(int originalStatusBarColor, StatusBarUpdater statusBarUpdater) {
            this.mOriginalStatusBarColor = originalStatusBarColor;
        }

        void startStatusBarUndimAnimator(long duration) {
            if (VERSION.SDK_INT >= 21) {
                ValueAnimator statusBarUndimAnimator = new ValueAnimator();
                statusBarUndimAnimator.setDuration(duration);
                statusBarUndimAnimator.setFloatValues(new float[]{0.5f, 1.0f});
                statusBarUndimAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        StatusBarDimmer.this.undimStatusBar(((Float) animation.getAnimatedValue()).floatValue());
                    }
                });
                statusBarUndimAnimator.start();
            }
        }

        void startStatusBarDimAnimation(long duration) {
            if (VERSION.SDK_INT >= 21) {
                ValueAnimator statusBarDimAnimator = new ValueAnimator();
                statusBarDimAnimator.setDuration(duration);
                statusBarDimAnimator.setFloatValues(new float[]{1.0f, 0.5f});
                statusBarDimAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        StatusBarDimmer.this.dimStatusBar(((Float) animation.getAnimatedValue()).floatValue());
                    }
                });
                statusBarDimAnimator.start();
            }
        }

        void dimStatusBar(float factor) {
            BackgroundDimmer.this.mStatusBarUpdater.setStatusBarColor(BackgroundDimmer.this.manipulateColor(this.mOriginalStatusBarColor, factor));
        }

        void undimStatusBar(float factor) {
            BackgroundDimmer.this.mStatusBarUpdater.setStatusBarColor(BackgroundDimmer.this.manipulateColor(this.mOriginalStatusBarColor, factor));
        }
    }

    @Inject
    public BackgroundDimmer(@BlockingOverlay RelativeLayout blockingOverlayView, Window window, BackNavigationConnector backNavigationConnector, StatusBarUpdater statusBarUpdater) {
        this.mBlockingOverlayView = blockingOverlayView;
        this.mWindow = window;
        this.mBackNavigatorConnector = backNavigationConnector;
        this.mStatusBarUpdater = statusBarUpdater;
    }

    public void dim(final View bottomView, Runnable customAnimationRunnable, int gravity) {
        if (this.mUndimming) {
            this.mRunOnUndim = BackgroundDimmer$$Lambda$1.lambdaFactory$(this, bottomView, customAnimationRunnable, gravity);
            return;
        }
        this.mGravity = gravity;
        prepareForDimming(bottomView, true, gravity);
        this.mBlockingOverlayView.setVisibility(0);
        this.mBlockingOverlayView.setAlpha(0.0f);
        this.mBottomView.setVisibility(0);
        moveViewToForeground(this.mBottomView);
        this.mSubscription.add(this.mBackNavigatorConnector.get().first().subscribe(BackgroundDimmer$$Lambda$2.lambdaFactory$(this), BackgroundDimmer$$Lambda$3.lambdaFactory$()));
        customAnimationRunnable.run();
        final ViewPropertyAnimator viewAnimator = this.mBlockingOverlayView.animate().alpha(1.0f);
        viewAnimator.setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewAnimator.setListener(null);
                if (BackgroundDimmer.this.mBottomViewParent.getDrawingCache() != null) {
                    Blurry.with(bottomView.getContext()).radius(25).sampling(2).onto(BackgroundDimmer.this.mBottomViewParent);
                }
            }
        });
        this.mStatusBarDimmer.startStatusBarDimAnimation(viewAnimator.getDuration());
        viewAnimator.start();
        this.mBlockingOverlayView.setOnClickListener(BackgroundDimmer$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$dim$2(Throwable t) {
    }

    public void dim(final View bottomView, Runnable onUndim, boolean animated, int gravity) {
        if (this.mUndimming) {
            this.mRunOnUndim = BackgroundDimmer$$Lambda$5.lambdaFactory$(this, bottomView, onUndim, animated, gravity);
            return;
        }
        this.mGravity = gravity;
        prepareForDimming(bottomView, animated, gravity);
        this.mBlockingOverlayView.setVisibility(0);
        this.mBlockingOverlayView.setAlpha(0.0f);
        this.mBottomView.setVisibility(0);
        moveViewToForeground(this.mBottomView);
        this.mSubscription.add(this.mBackNavigatorConnector.get().first().subscribe(BackgroundDimmer$$Lambda$6.lambdaFactory$(this, onUndim), BackgroundDimmer$$Lambda$7.lambdaFactory$()));
        if (animated) {
            this.mBottomViewHeight = bottomView.getMeasuredHeightAndState();
            final ViewPropertyAnimator viewAnimator = this.mBlockingOverlayView.animate().alpha(1.0f);
            viewAnimator.setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    viewAnimator.setListener(null);
                    if (BackgroundDimmer.this.mBottomViewParent.getDrawingCache() != null) {
                        Blurry.with(bottomView.getContext()).radius(25).sampling(2).onto(BackgroundDimmer.this.mBottomViewParent);
                    }
                }
            });
            this.mStatusBarDimmer.startStatusBarDimAnimation(viewAnimator.getDuration());
            bottomView.setTranslationY((float) this.mBottomViewHeight);
            bottomView.animate().translationYBy((float) (-this.mBottomViewHeight));
        } else {
            this.mBlockingOverlayView.setAlpha(1.0f);
            if (this.mBottomViewParent.getDrawingCache() != null) {
                Blurry.with(bottomView.getContext()).radius(25).sampling(2).async().onto(this.mBottomViewParent);
            }
            this.mStatusBarDimmer.dimStatusBar(1.0f);
        }
        this.mBlockingOverlayView.setOnClickListener(BackgroundDimmer$$Lambda$8.lambdaFactory$(this, onUndim));
    }

    static /* synthetic */ void lambda$dim$6(Throwable t) {
    }

    public void transition(final View nextView) {
        final int transitionY = (nextView.getMeasuredHeightAndState() - this.mBottomViewHeight) / (this.mGravity == 17 ? 2 : 1);
        nextView.bringToFront();
        if (transitionY > 0) {
            if (this.mGravity != 17) {
                this.mBlockingOverlayView.animate().translationYBy((float) (-transitionY));
            }
            replaceView(this.mBottomView, nextView);
            moveViewToForeground(nextView);
            nextView.setTranslationY((float) transitionY);
            nextView.animate().translationYBy((float) (-transitionY));
            this.mBottomView = nextView;
            this.mBottomViewHeight += transitionY;
        } else if (transitionY < 0) {
            if (this.mGravity != 17) {
                this.mBlockingOverlayView.animate().translationYBy((float) transitionY);
            }
            final ViewPropertyAnimator viewAnimator = this.mBottomView.animate().translationYBy((float) transitionY);
            viewAnimator.setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    BackgroundDimmer.this.replaceView(BackgroundDimmer.this.mBottomView, nextView);
                    BackgroundDimmer.this.moveViewToForeground(nextView);
                    BackgroundDimmer.this.mBottomViewHeight = BackgroundDimmer.this.mBottomViewHeight + transitionY;
                    viewAnimator.setListener(null);
                    BackgroundDimmer.this.mBottomView = nextView;
                }
            });
        } else {
            replaceView(this.mBottomView, nextView);
            moveViewToForeground(nextView);
            this.mBottomView = nextView;
        }
    }

    private void prepareForDimming(View bottomView, boolean animated, int gravity) {
        this.mStatusBarDimmer = new StatusBarDimmer(getOriginalStatusBarColor(), this.mStatusBarUpdater);
        this.mBottomView = bottomView;
        this.mDimmed = true;
        this.mAnimated = animated;
        this.mBottomViewParent = (ViewGroup) bottomView.getParent();
        this.mBottomViewParent.removeView(bottomView);
        this.mBlockingOverlayView.addView(bottomView);
        this.mBlockingOverlayView.setGravity(gravity);
    }

    public void overrideAnimated(boolean animated) {
        this.mAnimated = animated;
    }

    private int getOriginalStatusBarColor() {
        if (VERSION.SDK_INT < 21) {
            return -1;
        }
        return this.mWindow.getStatusBarColor();
    }

    public void undim(Runnable onUndim) {
        undim(onUndim, false);
    }

    public void undim(final Runnable onUndim, boolean disableAnimation) {
        this.mSubscription.clear();
        if (this.mDimmed) {
            this.mUndimming = true;
            this.mDimmed = false;
            if (!this.mAnimated || disableAnimation) {
                this.mBlockingOverlayView.setAlpha(0.0f);
                this.mBottomView.setVisibility(8);
                this.mBlockingOverlayView.setAlpha(0.0f);
                this.mBlockingOverlayView.setVisibility(4);
                this.mStatusBarDimmer.undimStatusBar(1.0f);
                finishUndim(onUndim);
                return;
            }
            final ViewPropertyAnimator overlayAnimator = this.mBlockingOverlayView.animate().alpha(0.0f);
            final AtomicBoolean animationFinished = new AtomicBoolean(false);
            overlayAnimator.setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    BackgroundDimmer.this.mBlockingOverlayView.setVisibility(4);
                    overlayAnimator.setListener(null);
                    if (animationFinished.getAndSet(true)) {
                        BackgroundDimmer.this.finishUndim(onUndim);
                    }
                }
            });
            final ViewPropertyAnimator viewAnimator = this.mBottomView.animate().translationYBy((float) this.mBottomViewHeight);
            viewAnimator.setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    BackgroundDimmer.this.mBottomView.setVisibility(8);
                    viewAnimator.setListener(null);
                    if (animationFinished.getAndSet(true)) {
                        BackgroundDimmer.this.finishUndim(onUndim);
                    }
                }
            });
            this.mStatusBarDimmer.startStatusBarUndimAnimator(overlayAnimator.getDuration());
        }
    }

    private void finishUndim(Runnable onUndim) {
        if (onUndim != null) {
            onUndim.run();
        }
        this.mBlockingOverlayView.removeView(this.mBottomView);
        this.mBottomViewParent.addView(this.mBottomView);
        Blurry.delete(this.mBottomViewParent);
        this.mUndimming = false;
        if (this.mRunOnUndim != null) {
            this.mRunOnUndim.call();
            this.mRunOnUndim = null;
        }
    }

    private int manipulateColor(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.min(Math.round(((float) Color.red(color)) * factor), 255), Math.min(Math.round(((float) Color.green(color)) * factor), 255), Math.min(Math.round(((float) Color.blue(color)) * factor), 255));
    }

    private void replaceView(View previousView, View newView) {
        previousView.setVisibility(8);
        this.mBlockingOverlayView.removeView(previousView);
        this.mBottomViewParent.addView(previousView);
        this.mBottomViewParent.removeView(newView);
        this.mBlockingOverlayView.addView(newView);
        newView.setVisibility(0);
        this.mBottomViewHeight = newView.getMeasuredHeightAndState();
    }

    private void moveViewToForeground(View view) {
        if (VERSION.SDK_INT >= 21) {
            view.setTranslationZ(Float.MAX_VALUE);
        }
    }
}
