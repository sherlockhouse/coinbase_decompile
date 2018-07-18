package jp.wasabeef.blurry;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import jp.wasabeef.blurry.internal.Blur;
import jp.wasabeef.blurry.internal.BlurFactor;
import jp.wasabeef.blurry.internal.BlurTask;
import jp.wasabeef.blurry.internal.Helper;

public class Blurry {
    private static final String TAG = Blurry.class.getSimpleName();

    public static class Composer {
        private boolean animate;
        private boolean async;
        private View blurredView;
        private Context context;
        private int duration = 300;
        private BlurFactor factor;

        public Composer(Context context) {
            this.context = context;
            this.blurredView = new View(context);
            this.blurredView.setTag(Blurry.TAG);
            this.factor = new BlurFactor();
        }

        public Composer radius(int radius) {
            this.factor.radius = radius;
            return this;
        }

        public Composer sampling(int sampling) {
            this.factor.sampling = sampling;
            return this;
        }

        public Composer async() {
            this.async = true;
            return this;
        }

        public void onto(ViewGroup target) {
            this.factor.width = target.getMeasuredWidth();
            this.factor.height = target.getMeasuredHeight();
            if (this.async) {
                new BlurTask(target, this.factor, new 1(this, target)).execute();
            } else {
                addView(target, new BitmapDrawable(this.context.getResources(), Blur.of(target, this.factor)));
            }
        }

        private void addView(ViewGroup target, Drawable drawable) {
            Helper.setBackground(this.blurredView, drawable);
            target.addView(this.blurredView);
            if (this.animate) {
                Helper.animate(this.blurredView, this.duration);
            }
        }
    }

    public static Composer with(Context context) {
        return new Composer(context);
    }

    public static void delete(ViewGroup target) {
        View view = target.findViewWithTag(TAG);
        if (view != null) {
            target.removeView(view);
        }
    }
}
