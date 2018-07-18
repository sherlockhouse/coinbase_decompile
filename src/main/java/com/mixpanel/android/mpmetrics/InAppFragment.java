package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.mixpanel.android.R;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.InAppNotificationState;

@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(14)
public class InAppFragment extends Fragment {
    private boolean mCleanedUp;
    private GestureDetector mDetector;
    private Runnable mDisplayMini;
    private InAppNotificationState mDisplayState;
    private int mDisplayStateId;
    private Handler mHandler;
    private View mInAppView;
    private Activity mParent;
    private Runnable mRemover;

    private class SineBounceInterpolator implements Interpolator {
        public float getInterpolation(float t) {
            return ((float) (-(Math.pow(2.718281828459045d, (double) (-8.0f * t)) * Math.cos((double) (12.0f * t))))) + 1.0f;
        }
    }

    public void setDisplayState(int stateId, InAppNotificationState displayState) {
        this.mDisplayStateId = stateId;
        this.mDisplayState = displayState;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mParent = activity;
        this.mHandler = new Handler();
        this.mRemover = new Runnable() {
            public void run() {
                InAppFragment.this.remove();
            }
        };
        this.mDisplayMini = new Runnable() {
            public void run() {
                InAppFragment.this.mInAppView.setVisibility(0);
                InAppFragment.this.mInAppView.setBackgroundColor(InAppFragment.this.mDisplayState.getHighlightColor());
                InAppFragment.this.mInAppView.setOnTouchListener(new OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent event) {
                        return InAppFragment.this.mDetector.onTouchEvent(event);
                    }
                });
                ImageView notifImage = (ImageView) InAppFragment.this.mInAppView.findViewById(R.id.com_mixpanel_android_notification_image);
                float heightPx = TypedValue.applyDimension(1, 75.0f, InAppFragment.this.mParent.getResources().getDisplayMetrics());
                TranslateAnimation translate = new TranslateAnimation(0.0f, 0.0f, heightPx, 0.0f);
                translate.setInterpolator(new DecelerateInterpolator());
                translate.setDuration(200);
                InAppFragment.this.mInAppView.startAnimation(translate);
                ScaleAnimation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, heightPx / 2.0f, heightPx / 2.0f);
                scale.setInterpolator(new SineBounceInterpolator());
                scale.setDuration(400);
                scale.setStartOffset(200);
                notifImage.startAnimation(scale);
            }
        };
        this.mDetector = new GestureDetector(activity, new OnGestureListener() {
            public boolean onDown(MotionEvent e) {
                return true;
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (velocityY > 0.0f) {
                    InAppFragment.this.remove();
                }
                return true;
            }

            public void onLongPress(MotionEvent e) {
            }

            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            public void onShowPress(MotionEvent e) {
            }

            public boolean onSingleTapUp(MotionEvent event) {
                String uriString = InAppFragment.this.mDisplayState.getInAppNotification().getCallToActionUrl();
                if (uriString != null && uriString.length() > 0) {
                    try {
                        try {
                            InAppFragment.this.mParent.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uriString)));
                        } catch (ActivityNotFoundException e) {
                            Log.i("InAppFragment", "User doesn't have an activity for notification URI");
                        }
                    } catch (IllegalArgumentException e2) {
                        Log.i("InAppFragment", "Can't parse notification URI, will not take any action", e2);
                    }
                }
                InAppFragment.this.remove();
                return true;
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCleanedUp = false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (this.mDisplayState == null) {
            cleanUp();
        } else {
            this.mInAppView = inflater.inflate(R.layout.com_mixpanel_android_activity_notification_mini, container, false);
            TextView titleView = (TextView) this.mInAppView.findViewById(R.id.com_mixpanel_android_notification_title);
            ImageView notifImage = (ImageView) this.mInAppView.findViewById(R.id.com_mixpanel_android_notification_image);
            InAppNotification inApp = this.mDisplayState.getInAppNotification();
            titleView.setText(inApp.getTitle());
            notifImage.setImageBitmap(inApp.getImage());
            this.mHandler.postDelayed(this.mRemover, 6000);
        }
        return this.mInAppView;
    }

    public void onStart() {
        super.onStart();
        if (this.mCleanedUp) {
            this.mParent.getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    public void onResume() {
        super.onResume();
        this.mHandler.postDelayed(this.mDisplayMini, 500);
    }

    public void onSaveInstanceState(Bundle outState) {
        cleanUp();
        super.onSaveInstanceState(outState);
    }

    public void onPause() {
        super.onPause();
        cleanUp();
    }

    private void cleanUp() {
        if (!this.mCleanedUp) {
            this.mHandler.removeCallbacks(this.mRemover);
            this.mHandler.removeCallbacks(this.mDisplayMini);
            UpdateDisplayState.releaseDisplayState(this.mDisplayStateId);
            this.mParent.getFragmentManager().beginTransaction().remove(this).commit();
        }
        this.mCleanedUp = true;
    }

    private void remove() {
        if (this.mParent != null && !this.mCleanedUp) {
            this.mHandler.removeCallbacks(this.mRemover);
            this.mHandler.removeCallbacks(this.mDisplayMini);
            this.mParent.getFragmentManager().beginTransaction().setCustomAnimations(0, R.anim.com_mixpanel_android_slide_down).remove(this).commit();
            this.mCleanedUp = true;
        }
    }
}
