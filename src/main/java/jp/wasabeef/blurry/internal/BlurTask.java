package jp.wasabeef.blurry.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlurTask {
    private static ExecutorService THREAD_POOL = Executors.newCachedThreadPool();
    private Bitmap bitmap;
    private Callback callback;
    private WeakReference<Context> contextWeakRef;
    private BlurFactor factor;
    private Resources res;

    public interface Callback {
        void done(BitmapDrawable bitmapDrawable);
    }

    public BlurTask(View target, BlurFactor factor, Callback callback) {
        this.res = target.getResources();
        this.factor = factor;
        this.callback = callback;
        this.contextWeakRef = new WeakReference(target.getContext());
        target.setDrawingCacheEnabled(true);
        target.destroyDrawingCache();
        target.setDrawingCacheQuality(524288);
        this.bitmap = target.getDrawingCache();
    }

    public void execute() {
        THREAD_POOL.execute(new Runnable() {
            public void run() {
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(BlurTask.this.res, Blur.of((Context) BlurTask.this.contextWeakRef.get(), BlurTask.this.bitmap, BlurTask.this.factor));
                if (BlurTask.this.callback != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            BlurTask.this.callback.done(bitmapDrawable);
                        }
                    });
                }
            }
        });
    }
}
