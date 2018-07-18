package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.os.TraceCompat;
import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final Option<CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", Integer.valueOf(90));

    public boolean encode(Resource<Bitmap> resource, File file, Options options) {
        IOException e;
        Throwable th;
        Bitmap bitmap = (Bitmap) resource.get();
        CompressFormat format = getFormat(bitmap, options);
        TraceCompat.beginSection("encode: [" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + format);
        try {
            long start = LogTime.getLogTime();
            int quality = ((Integer) options.get(COMPRESSION_QUALITY)).intValue();
            boolean success = false;
            OutputStream os = null;
            try {
                OutputStream os2 = new FileOutputStream(file);
                try {
                    bitmap.compress(format, quality, os2);
                    os2.close();
                    success = true;
                    if (os2 != null) {
                        try {
                            os2.close();
                            os = os2;
                        } catch (IOException e2) {
                            os = os2;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    os = os2;
                    try {
                        if (Log.isLoggable("BitmapEncoder", 3)) {
                            Log.d("BitmapEncoder", "Failed to encode Bitmap", e);
                        }
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (Log.isLoggable("BitmapEncoder", 2)) {
                            Log.v("BitmapEncoder", "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
                        }
                        TraceCompat.endSection();
                        return success;
                    } catch (Throwable th2) {
                        th = th2;
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    os = os2;
                    if (os != null) {
                        os.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                if (Log.isLoggable("BitmapEncoder", 3)) {
                    Log.d("BitmapEncoder", "Failed to encode Bitmap", e);
                }
                if (os != null) {
                    os.close();
                }
                if (Log.isLoggable("BitmapEncoder", 2)) {
                    Log.v("BitmapEncoder", "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
                }
                TraceCompat.endSection();
                return success;
            }
            if (Log.isLoggable("BitmapEncoder", 2)) {
                Log.v("BitmapEncoder", "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(start) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
            }
            TraceCompat.endSection();
            return success;
        } catch (Throwable th4) {
            TraceCompat.endSection();
        }
    }

    private CompressFormat getFormat(Bitmap bitmap, Options options) {
        CompressFormat format = (CompressFormat) options.get(COMPRESSION_FORMAT);
        if (format != null) {
            return format;
        }
        if (bitmap.hasAlpha()) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }

    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
