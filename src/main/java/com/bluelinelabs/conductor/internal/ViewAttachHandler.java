package com.bluelinelabs.conductor.internal;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;

public class ViewAttachHandler implements OnAttachStateChangeListener {
    private boolean activityStopped = false;
    private ViewAttachListener attachListener;
    private OnAttachStateChangeListener childOnAttachStateChangeListener;
    private boolean childrenAttached = false;
    private ReportedState reportedState = ReportedState.VIEW_DETACHED;
    private boolean rootAttached = false;

    public interface ViewAttachListener {
        void onAttached();

        void onDetached(boolean z);

        void onViewDetachAfterStop();
    }

    private interface ChildAttachListener {
        void onAttached();
    }

    private enum ReportedState {
        VIEW_DETACHED,
        ACTIVITY_STOPPED,
        ATTACHED
    }

    public ViewAttachHandler(ViewAttachListener attachListener) {
        this.attachListener = attachListener;
    }

    public void onViewAttachedToWindow(View v) {
        if (!this.rootAttached) {
            this.rootAttached = true;
            listenForDeepestChildAttach(v, new ChildAttachListener() {
                public void onAttached() {
                    ViewAttachHandler.this.childrenAttached = true;
                    ViewAttachHandler.this.reportAttached();
                }
            });
        }
    }

    public void onViewDetachedFromWindow(View v) {
        this.rootAttached = false;
        if (this.childrenAttached) {
            this.childrenAttached = false;
            reportDetached(false);
        }
    }

    public void listenForAttach(View view) {
        view.addOnAttachStateChangeListener(this);
    }

    public void unregisterAttachListener(View view) {
        view.removeOnAttachStateChangeListener(this);
        if (this.childOnAttachStateChangeListener != null && (view instanceof ViewGroup)) {
            findDeepestChild((ViewGroup) view).removeOnAttachStateChangeListener(this.childOnAttachStateChangeListener);
        }
    }

    public void onActivityStarted() {
        this.activityStopped = false;
        reportAttached();
    }

    public void onActivityStopped() {
        this.activityStopped = true;
        reportDetached(true);
    }

    private void reportAttached() {
        if (this.rootAttached && this.childrenAttached && !this.activityStopped && this.reportedState != ReportedState.ATTACHED) {
            this.reportedState = ReportedState.ATTACHED;
            this.attachListener.onAttached();
        }
    }

    private void reportDetached(boolean detachedForActivity) {
        boolean wasDetachedForActivity = this.reportedState == ReportedState.ACTIVITY_STOPPED;
        if (detachedForActivity) {
            this.reportedState = ReportedState.ACTIVITY_STOPPED;
        } else {
            this.reportedState = ReportedState.VIEW_DETACHED;
        }
        if (!wasDetachedForActivity || detachedForActivity) {
            this.attachListener.onDetached(detachedForActivity);
        } else {
            this.attachListener.onViewDetachAfterStop();
        }
    }

    private void listenForDeepestChildAttach(View view, final ChildAttachListener attachListener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                attachListener.onAttached();
                return;
            }
            this.childOnAttachStateChangeListener = new OnAttachStateChangeListener() {
                boolean attached = false;

                public void onViewAttachedToWindow(View v) {
                    if (!this.attached) {
                        this.attached = true;
                        attachListener.onAttached();
                        v.removeOnAttachStateChangeListener(this);
                        ViewAttachHandler.this.childOnAttachStateChangeListener = null;
                    }
                }

                public void onViewDetachedFromWindow(View v) {
                }
            };
            findDeepestChild(viewGroup).addOnAttachStateChangeListener(this.childOnAttachStateChangeListener);
            return;
        }
        attachListener.onAttached();
    }

    private View findDeepestChild(ViewGroup viewGroup) {
        if (viewGroup.getChildCount() == 0) {
            return viewGroup;
        }
        View lastChild = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
        return lastChild instanceof ViewGroup ? findDeepestChild((ViewGroup) lastChild) : lastChild;
    }
}
