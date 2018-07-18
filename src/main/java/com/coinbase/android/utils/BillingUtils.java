package com.coinbase.android.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.coinbase.api.internal.models.billingAddress.Data;

public class BillingUtils {
    public static String getAddressLine(Data billingAddress) {
        String result = "";
        if (billingAddress.getLine1() != null) {
            result = billingAddress.getLine1();
        }
        if (billingAddress.getLine2() != null) {
            result = result + ", " + billingAddress.getLine2();
        }
        if (billingAddress.getLine3() != null) {
            return result + ", " + billingAddress.getLine3();
        }
        return result;
    }

    public static String getCityStateZip(Data billingAddress) {
        String result = "";
        if (billingAddress.getCity() != null) {
            result = billingAddress.getCity();
        }
        if (billingAddress.getState() != null) {
            result = result + ", " + billingAddress.getState();
        }
        if (billingAddress.getPostalCode() != null) {
            return result + " " + billingAddress.getPostalCode();
        }
        return result;
    }

    public static String getFullAddress(Data billingAddress) {
        return getAddressLine(billingAddress) + " " + getCityStateZip(billingAddress);
    }

    public static void showProgressBar(Context context, final boolean show, ProgressBar progressBar, final FrameLayout overlayLayout) {
        progressBar.setVisibility(show ? 0 : 8);
        overlayLayout.setVisibility(0);
        String str = "alpha";
        float[] fArr = new float[1];
        fArr[0] = show ? 1.0f : 0.0f;
        ObjectAnimator animator = ObjectAnimator.ofFloat(overlayLayout, str, fArr);
        animator.setDuration((long) context.getResources().getInteger(17694720));
        animator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                overlayLayout.setVisibility(show ? 0 : 8);
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }
}
