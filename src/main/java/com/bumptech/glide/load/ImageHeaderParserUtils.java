package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ImageHeaderParserUtils {
    public static ImageType getType(List<ImageHeaderParser> parsers, InputStream is, ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return ImageType.UNKNOWN;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(5242880);
        for (ImageHeaderParser parser : parsers) {
            try {
                ImageType type = parser.getType(is);
                if (type != ImageType.UNKNOWN) {
                    is.reset();
                    return type;
                }
                is.reset();
            } catch (Throwable th) {
                is.reset();
            }
        }
        return ImageType.UNKNOWN;
    }

    public static ImageType getType(List<ImageHeaderParser> parsers, ByteBuffer buffer) throws IOException {
        if (buffer == null) {
            return ImageType.UNKNOWN;
        }
        for (ImageHeaderParser parser : parsers) {
            ImageType type = parser.getType(buffer);
            if (type != ImageType.UNKNOWN) {
                return type;
            }
        }
        return ImageType.UNKNOWN;
    }

    public static int getOrientation(List<ImageHeaderParser> parsers, InputStream is, ArrayPool byteArrayPool) throws IOException {
        if (is == null) {
            return -1;
        }
        if (!is.markSupported()) {
            is = new RecyclableBufferedInputStream(is, byteArrayPool);
        }
        is.mark(5242880);
        for (ImageHeaderParser parser : parsers) {
            try {
                int orientation = parser.getOrientation(is, byteArrayPool);
                if (orientation != -1) {
                    is.reset();
                    return orientation;
                }
                is.reset();
            } catch (Throwable th) {
                is.reset();
            }
        }
        return -1;
    }
}
