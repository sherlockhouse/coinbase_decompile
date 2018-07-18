package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class TransformationUtils {
    private static final Lock BITMAP_DRAWABLE_LOCK;
    private static final Paint CIRCLE_CROP_BITMAP_PAINT = new Paint(7);
    private static final Paint CIRCLE_CROP_SHAPE_PAINT = new Paint(7);
    private static final Paint DEFAULT_PAINT = new Paint(6);
    private static final List<String> MODELS_REQUIRING_BITMAP_LOCK = Arrays.asList(new String[]{"XT1097", "XT1085"});

    private static final class NoLock implements Lock {
        NoLock() {
        }

        public void lock() {
        }

        public void lockInterruptibly() throws InterruptedException {
        }

        public boolean tryLock() {
            return true;
        }

        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return true;
        }

        public void unlock() {
        }

        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }

    static {
        Lock reentrantLock = (MODELS_REQUIRING_BITMAP_LOCK.contains(Build.MODEL) && VERSION.SDK_INT == 22) ? new ReentrantLock() : new NoLock();
        BITMAP_DRAWABLE_LOCK = reentrantLock;
        CIRCLE_CROP_BITMAP_PAINT.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    public static Lock getBitmapDrawableLock() {
        return BITMAP_DRAWABLE_LOCK;
    }

    public static Bitmap centerCrop(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        if (inBitmap.getWidth() == width && inBitmap.getHeight() == height) {
            return inBitmap;
        }
        float scale;
        float dx = 0.0f;
        float dy = 0.0f;
        Matrix m = new Matrix();
        if (inBitmap.getWidth() * height > inBitmap.getHeight() * width) {
            scale = ((float) height) / ((float) inBitmap.getHeight());
            dx = (((float) width) - (((float) inBitmap.getWidth()) * scale)) * 0.5f;
        } else {
            scale = ((float) width) / ((float) inBitmap.getWidth());
            dy = (((float) height) - (((float) inBitmap.getHeight()) * scale)) * 0.5f;
        }
        m.setScale(scale, scale);
        m.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        Bitmap result = pool.get(width, height, getSafeConfig(inBitmap));
        setAlpha(inBitmap, result);
        applyMatrix(inBitmap, result, m);
        return result;
    }

    public static Bitmap fitCenter(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        if (inBitmap.getWidth() != width || inBitmap.getHeight() != height) {
            float minPercentage = Math.min(((float) width) / ((float) inBitmap.getWidth()), ((float) height) / ((float) inBitmap.getHeight()));
            int targetWidth = (int) (((float) inBitmap.getWidth()) * minPercentage);
            int targetHeight = (int) (((float) inBitmap.getHeight()) * minPercentage);
            if (inBitmap.getWidth() != targetWidth || inBitmap.getHeight() != targetHeight) {
                Bitmap toReuse = pool.get(targetWidth, targetHeight, getSafeConfig(inBitmap));
                setAlpha(inBitmap, toReuse);
                if (Log.isLoggable("TransformationUtils", 2)) {
                    Log.v("TransformationUtils", "request: " + width + "x" + height);
                    Log.v("TransformationUtils", "toFit:   " + inBitmap.getWidth() + "x" + inBitmap.getHeight());
                    Log.v("TransformationUtils", "toReuse: " + toReuse.getWidth() + "x" + toReuse.getHeight());
                    Log.v("TransformationUtils", "minPct:   " + minPercentage);
                }
                Matrix matrix = new Matrix();
                matrix.setScale(minPercentage, minPercentage);
                applyMatrix(inBitmap, toReuse, matrix);
                return toReuse;
            } else if (!Log.isLoggable("TransformationUtils", 2)) {
                return inBitmap;
            } else {
                Log.v("TransformationUtils", "adjusted target size matches input, returning input");
                return inBitmap;
            }
        } else if (!Log.isLoggable("TransformationUtils", 2)) {
            return inBitmap;
        } else {
            Log.v("TransformationUtils", "requested target size matches input, returning input");
            return inBitmap;
        }
    }

    public static Bitmap centerInside(BitmapPool pool, Bitmap inBitmap, int width, int height) {
        if (inBitmap.getWidth() > width || inBitmap.getHeight() > height) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return fitCenter(pool, inBitmap, width, height);
        } else if (!Log.isLoggable("TransformationUtils", 2)) {
            return inBitmap;
        } else {
            Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
            return inBitmap;
        }
    }

    public static void setAlpha(Bitmap inBitmap, Bitmap outBitmap) {
        outBitmap.setHasAlpha(inBitmap.hasAlpha());
    }

    public static int getExifOrientationDegrees(int exifOrientation) {
        switch (exifOrientation) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static Bitmap rotateImageExif(BitmapPool pool, Bitmap inBitmap, int exifOrientation) {
        Matrix matrix = new Matrix();
        initializeMatrixForRotation(exifOrientation, matrix);
        if (matrix.isIdentity()) {
            return inBitmap;
        }
        RectF newRect = new RectF(0.0f, 0.0f, (float) inBitmap.getWidth(), (float) inBitmap.getHeight());
        matrix.mapRect(newRect);
        Bitmap result = pool.get(Math.round(newRect.width()), Math.round(newRect.height()), getSafeConfig(inBitmap));
        matrix.postTranslate(-newRect.left, -newRect.top);
        applyMatrix(inBitmap, result, matrix);
        return result;
    }

    public static Bitmap circleCrop(BitmapPool pool, Bitmap inBitmap, int destWidth, int destHeight) {
        int destMinEdge = Math.min(destWidth, destHeight);
        float radius = ((float) destMinEdge) / 2.0f;
        int srcWidth = inBitmap.getWidth();
        int srcHeight = inBitmap.getHeight();
        float maxScale = Math.max(((float) destMinEdge) / ((float) srcWidth), ((float) destMinEdge) / ((float) srcHeight));
        float scaledWidth = maxScale * ((float) srcWidth);
        float scaledHeight = maxScale * ((float) srcHeight);
        float left = (((float) destMinEdge) - scaledWidth) / 2.0f;
        float top = (((float) destMinEdge) - scaledHeight) / 2.0f;
        RectF destRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
        Bitmap toTransform = getAlphaSafeBitmap(pool, inBitmap);
        Bitmap result = pool.get(destMinEdge, destMinEdge, Config.ARGB_8888);
        result.setHasAlpha(true);
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);
            canvas.drawCircle(radius, radius, radius, CIRCLE_CROP_SHAPE_PAINT);
            canvas.drawBitmap(toTransform, null, destRect, CIRCLE_CROP_BITMAP_PAINT);
            clear(canvas);
            if (!toTransform.equals(inBitmap)) {
                pool.put(toTransform);
            }
            return result;
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }
    }

    private static Bitmap getAlphaSafeBitmap(BitmapPool pool, Bitmap maybeAlphaSafe) {
        if (Config.ARGB_8888.equals(maybeAlphaSafe.getConfig())) {
            return maybeAlphaSafe;
        }
        Bitmap argbBitmap = pool.get(maybeAlphaSafe.getWidth(), maybeAlphaSafe.getHeight(), Config.ARGB_8888);
        new Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0.0f, 0.0f, null);
        return argbBitmap;
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private static Config getSafeConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
    }

    private static void applyMatrix(Bitmap inBitmap, Bitmap targetBitmap, Matrix matrix) {
        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(targetBitmap);
            canvas.drawBitmap(inBitmap, matrix, DEFAULT_PAINT);
            clear(canvas);
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }
    }

    static void initializeMatrixForRotation(int exifOrientation, Matrix matrix) {
        switch (exifOrientation) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix.setRotate(180.0f);
                return;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix.setRotate(90.0f);
                return;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}
