package jp.wasabeef.blurry.internal;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.AlphaAnimation;

public final class Helper {
    public static void setBackground(View v, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }
    }

    public static boolean hasZero(int... args) {
        for (int num : args) {
            if (num == 0) {
                return true;
            }
        }
        return false;
    }

    public static void animate(View v, int duration) {
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration((long) duration);
        v.startAnimation(alpha);
    }
}
