package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class Downsampler {
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DownsampleStrategy.AT_LEAST);
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() {
        public void onObtainBounds() {
        }

        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap downsampled) throws IOException {
        }
    };
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", Boolean.valueOf(false));
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"image/vnd.wap.wbmp", "image/x-ico"})));
    private static final Queue<Options> OPTIONS_QUEUE = Util.createQueue(0);
    private static final Set<ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG));
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final List<ImageHeaderParser> parsers;

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    public Downsampler(List<ImageHeaderParser> parsers, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool byteArrayPool) {
        this.parsers = parsers;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    public boolean handles(InputStream is) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream is, int outWidth, int outHeight, com.bumptech.glide.load.Options options) throws IOException {
        return decode(is, outWidth, outHeight, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(InputStream is, int requestedWidth, int requestedHeight, com.bumptech.glide.load.Options options, DecodeCallbacks callbacks) throws IOException {
        Preconditions.checkArgument(is.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bytesForOptions = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        Options bitmapFactoryOptions = getDefaultOptions();
        bitmapFactoryOptions.inTempStorage = bytesForOptions;
        try {
            Resource<Bitmap> obtain = BitmapResource.obtain(decodeFromWrappedStreams(is, bitmapFactoryOptions, (DownsampleStrategy) options.get(DOWNSAMPLE_STRATEGY), (DecodeFormat) options.get(DECODE_FORMAT), requestedWidth, requestedHeight, ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue(), callbacks), this.bitmapPool);
            return obtain;
        } finally {
            releaseOptions(bitmapFactoryOptions);
            this.byteArrayPool.put(bytesForOptions, byte[].class);
        }
    }

    private Bitmap decodeFromWrappedStreams(InputStream is, Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, int requestedWidth, int requestedHeight, boolean fixBitmapToRequestedDimensions, DecodeCallbacks callbacks) throws IOException {
        int targetWidth;
        int targetHeight;
        int[] sourceDimensions = getDimensions(is, options, callbacks, this.bitmapPool);
        int sourceWidth = sourceDimensions[0];
        int sourceHeight = sourceDimensions[1];
        String sourceMimeType = options.outMimeType;
        int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, is, this.byteArrayPool);
        int degreesToRotate = TransformationUtils.getExifOrientationDegrees(orientation);
        options.inPreferredConfig = getConfig(is, decodeFormat);
        if (options.inPreferredConfig != Config.ARGB_8888) {
            options.inDither = true;
        }
        if (requestedWidth == Integer.MIN_VALUE) {
            targetWidth = sourceWidth;
        } else {
            targetWidth = requestedWidth;
        }
        if (requestedHeight == Integer.MIN_VALUE) {
            targetHeight = sourceHeight;
        } else {
            targetHeight = requestedHeight;
        }
        calculateScaling(downsampleStrategy, degreesToRotate, sourceWidth, sourceHeight, targetWidth, targetHeight, options);
        boolean isKitKatOrGreater = VERSION.SDK_INT >= 19;
        if ((options.inSampleSize == 1 || isKitKatOrGreater) && shouldUsePool(is)) {
            int expectedWidth;
            int expectedHeight;
            if (fixBitmapToRequestedDimensions && isKitKatOrGreater) {
                expectedWidth = targetWidth;
                expectedHeight = targetHeight;
            } else {
                float densityMultiplier = isScaling(options) ? ((float) options.inTargetDensity) / ((float) options.inDensity) : 1.0f;
                int sampleSize = options.inSampleSize;
                int downsampledHeight = (int) Math.ceil((double) (((float) sourceHeight) / ((float) sampleSize)));
                expectedWidth = Math.round(((float) ((int) Math.ceil((double) (((float) sourceWidth) / ((float) sampleSize))))) * densityMultiplier);
                expectedHeight = Math.round(((float) downsampledHeight) * densityMultiplier);
                if (Log.isLoggable("Downsampler", 2)) {
                    Log.v("Downsampler", "Calculated target [" + expectedWidth + "x" + expectedHeight + "] for source [" + sourceWidth + "x" + sourceHeight + "], sampleSize: " + sampleSize + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + densityMultiplier);
                }
            }
            if (expectedWidth > 0 && expectedHeight > 0) {
                setInBitmap(options, this.bitmapPool, expectedWidth, expectedHeight);
            }
        }
        Bitmap downsampled = decodeStream(is, options, callbacks, this.bitmapPool);
        callbacks.onDecodeComplete(this.bitmapPool, downsampled);
        if (Log.isLoggable("Downsampler", 2)) {
            logDecode(sourceWidth, sourceHeight, sourceMimeType, options, downsampled, requestedWidth, requestedHeight);
        }
        Bitmap rotated = null;
        if (downsampled != null) {
            downsampled.setDensity(this.displayMetrics.densityDpi);
            rotated = TransformationUtils.rotateImageExif(this.bitmapPool, downsampled, orientation);
            if (!downsampled.equals(rotated)) {
                this.bitmapPool.put(downsampled);
            }
        }
        return rotated;
    }

    static void calculateScaling(DownsampleStrategy downsampleStrategy, int degreesToRotate, int sourceWidth, int sourceHeight, int targetWidth, int targetHeight, Options options) {
        if (sourceWidth > 0 && sourceHeight > 0) {
            float exactScaleFactor;
            if (degreesToRotate == 90 || degreesToRotate == 270) {
                exactScaleFactor = downsampleStrategy.getScaleFactor(sourceHeight, sourceWidth, targetWidth, targetHeight);
            } else {
                exactScaleFactor = downsampleStrategy.getScaleFactor(sourceWidth, sourceHeight, targetWidth, targetHeight);
            }
            if (exactScaleFactor <= 0.0f) {
                throw new IllegalArgumentException("Cannot scale with factor: " + exactScaleFactor + " from: " + downsampleStrategy);
            }
            SampleSizeRounding rounding = downsampleStrategy.getSampleSizeRounding(sourceWidth, sourceHeight, targetWidth, targetHeight);
            if (rounding == null) {
                throw new IllegalArgumentException("Cannot round with null rounding");
            }
            int scaleFactor;
            int powerOfTwoSampleSize;
            int widthScaleFactor = sourceWidth / ((int) ((((float) sourceWidth) * exactScaleFactor) + 0.5f));
            int heightScaleFactor = sourceHeight / ((int) ((((float) sourceHeight) * exactScaleFactor) + 0.5f));
            if (rounding == SampleSizeRounding.MEMORY) {
                scaleFactor = Math.max(widthScaleFactor, heightScaleFactor);
            } else {
                scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);
            }
            if (VERSION.SDK_INT > 23 || !NO_DOWNSAMPLE_PRE_N_MIME_TYPES.contains(options.outMimeType)) {
                powerOfTwoSampleSize = Math.max(1, Integer.highestOneBit(scaleFactor));
                if (rounding == SampleSizeRounding.MEMORY && ((float) powerOfTwoSampleSize) < 1.0f / exactScaleFactor) {
                    powerOfTwoSampleSize <<= 1;
                }
            } else {
                powerOfTwoSampleSize = 1;
            }
            float adjustedScaleFactor = ((float) powerOfTwoSampleSize) * exactScaleFactor;
            options.inSampleSize = powerOfTwoSampleSize;
            if (VERSION.SDK_INT >= 19) {
                options.inTargetDensity = (int) ((1000.0f * adjustedScaleFactor) + 0.5f);
                options.inDensity = 1000;
            }
            if (isScaling(options)) {
                options.inScaled = true;
            } else {
                options.inTargetDensity = 0;
                options.inDensity = 0;
            }
            if (Log.isLoggable("Downsampler", 2)) {
                Log.v("Downsampler", "Calculate scaling, source: [" + sourceWidth + "x" + sourceHeight + "], target: [" + targetWidth + "x" + targetHeight + "], exact scale factor: " + exactScaleFactor + ", power of 2 sample size: " + powerOfTwoSampleSize + ", adjusted scale factor: " + adjustedScaleFactor + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
            }
        }
    }

    private boolean shouldUsePool(InputStream is) throws IOException {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        try {
            return TYPES_THAT_USE_POOL_PRE_KITKAT.contains(ImageHeaderParserUtils.getType(this.parsers, is, this.byteArrayPool));
        } catch (IOException e) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Cannot determine the image type from header", e);
            }
            return false;
        }
    }

    private Config getConfig(InputStream is, DecodeFormat format) throws IOException {
        if (format == DecodeFormat.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        boolean hasAlpha = false;
        try {
            hasAlpha = ImageHeaderParserUtils.getType(this.parsers, is, this.byteArrayPool).hasAlpha();
        } catch (IOException e) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Cannot determine whether the image has alpha or not from header, format " + format, e);
            }
        }
        if (hasAlpha) {
            return Config.ARGB_8888;
        }
        return Config.RGB_565;
    }

    private static int[] getDimensions(InputStream is, Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(is, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap decodeStream(InputStream is, Options options, DecodeCallbacks callbacks, BitmapPool bitmapPool) throws IOException {
        Bitmap result;
        IOException bitmapAssertionException;
        if (options.inJustDecodeBounds) {
            is.mark(5242880);
        } else {
            callbacks.onObtainBounds();
        }
        int sourceWidth = options.outWidth;
        int sourceHeight = options.outHeight;
        String outMimeType = options.outMimeType;
        TransformationUtils.getBitmapDrawableLock().lock();
        try {
            result = BitmapFactory.decodeStream(is, null, options);
            TransformationUtils.getBitmapDrawableLock().unlock();
            if (options.inJustDecodeBounds) {
                is.reset();
            }
        } catch (IOException e) {
            throw bitmapAssertionException;
        } catch (IllegalArgumentException e2) {
            bitmapAssertionException = newIoExceptionForInBitmapAssertion(e2, sourceWidth, sourceHeight, outMimeType, options);
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Failed to decode with inBitmap, trying again without Bitmap re-use", bitmapAssertionException);
            }
            if (options.inBitmap != null) {
                is.reset();
                bitmapPool.put(options.inBitmap);
                options.inBitmap = null;
                result = decodeStream(is, options, callbacks, bitmapPool);
                TransformationUtils.getBitmapDrawableLock().unlock();
            } else {
                throw bitmapAssertionException;
            }
        } catch (Throwable th) {
            TransformationUtils.getBitmapDrawableLock().unlock();
        }
        return result;
    }

    private static boolean isScaling(Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void logDecode(int sourceWidth, int sourceHeight, String outMimeType, Options options, Bitmap result, int requestedWidth, int requestedHeight) {
        Log.v("Downsampler", "Decoded " + getBitmapString(result) + " from [" + sourceWidth + "x" + sourceHeight + "] " + outMimeType + " with inBitmap " + getInBitmapString(options) + " for [" + requestedWidth + "x" + requestedHeight + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName());
    }

    private static String getInBitmapString(Options options) {
        return getBitmapString(options.inBitmap);
    }

    @TargetApi(19)
    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (VERSION.SDK_INT >= 19 ? " (" + bitmap.getAllocationByteCount() + ")" : "");
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException e, int outWidth, int outHeight, String outMimeType, Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + outWidth + ", outHeight: " + outHeight + ", outMimeType: " + outMimeType + ", inBitmap: " + getInBitmapString(options), e);
    }

    private static void setInBitmap(Options options, BitmapPool bitmapPool, int width, int height) {
        options.inBitmap = bitmapPool.getDirty(width, height, options.inPreferredConfig);
    }

    private static synchronized Options getDefaultOptions() {
        Options decodeBitmapOptions;
        synchronized (Downsampler.class) {
            synchronized (OPTIONS_QUEUE) {
                decodeBitmapOptions = (Options) OPTIONS_QUEUE.poll();
            }
            if (decodeBitmapOptions == null) {
                decodeBitmapOptions = new Options();
                resetOptions(decodeBitmapOptions);
            }
        }
        return decodeBitmapOptions;
    }

    private static void releaseOptions(Options decodeBitmapOptions) {
        resetOptions(decodeBitmapOptions);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(decodeBitmapOptions);
        }
    }

    private static void resetOptions(Options decodeBitmapOptions) {
        decodeBitmapOptions.inTempStorage = null;
        decodeBitmapOptions.inDither = false;
        decodeBitmapOptions.inScaled = false;
        decodeBitmapOptions.inSampleSize = 1;
        decodeBitmapOptions.inPreferredConfig = null;
        decodeBitmapOptions.inJustDecodeBounds = false;
        decodeBitmapOptions.inDensity = 0;
        decodeBitmapOptions.inTargetDensity = 0;
        decodeBitmapOptions.outWidth = 0;
        decodeBitmapOptions.outHeight = 0;
        decodeBitmapOptions.outMimeType = null;
        decodeBitmapOptions.inBitmap = null;
        decodeBitmapOptions.inMutable = true;
    }
}
