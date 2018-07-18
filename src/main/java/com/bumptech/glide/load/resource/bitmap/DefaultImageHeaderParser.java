package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DefaultImageHeaderParser implements ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    private interface Reader {
        int getByte() throws IOException;

        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    private static final class ByteBufferReader implements Reader {
        private final ByteBuffer byteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public int getUInt16() throws IOException {
            return ((getByte() << 8) & 65280) | (getByte() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (getByte() & 255);
        }

        public long skip(long total) throws IOException {
            int toSkip = (int) Math.min((long) this.byteBuffer.remaining(), total);
            this.byteBuffer.position(this.byteBuffer.position() + toSkip);
            return (long) toSkip;
        }

        public int read(byte[] buffer, int byteCount) throws IOException {
            int toRead = Math.min(byteCount, this.byteBuffer.remaining());
            if (toRead == 0) {
                return -1;
            }
            this.byteBuffer.get(buffer, 0, toRead);
            return toRead;
        }

        public int getByte() throws IOException {
            if (this.byteBuffer.remaining() < 1) {
                return -1;
            }
            return this.byteBuffer.get();
        }
    }

    private static final class RandomAccessReader {
        private final ByteBuffer data;

        RandomAccessReader(byte[] data, int length) {
            this.data = (ByteBuffer) ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).limit(length);
        }

        void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }

        int length() {
            return this.data.remaining();
        }

        int getInt32(int offset) {
            return isAvailable(offset, 4) ? this.data.getInt(offset) : -1;
        }

        short getInt16(int offset) {
            return isAvailable(offset, 2) ? this.data.getShort(offset) : (short) -1;
        }

        private boolean isAvailable(int offset, int byteSize) {
            return this.data.remaining() - offset >= byteSize;
        }
    }

    private static final class StreamReader implements Reader {
        private final InputStream is;

        StreamReader(InputStream is) {
            this.is = is;
        }

        public int getUInt16() throws IOException {
            return ((this.is.read() << 8) & 65280) | (this.is.read() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (this.is.read() & 255);
        }

        public long skip(long total) throws IOException {
            if (total < 0) {
                return 0;
            }
            long toSkip = total;
            while (toSkip > 0) {
                long skipped = this.is.skip(toSkip);
                if (skipped > 0) {
                    toSkip -= skipped;
                } else if (this.is.read() == -1) {
                    break;
                } else {
                    toSkip--;
                }
            }
            return total - toSkip;
        }

        public int read(byte[] buffer, int byteCount) throws IOException {
            int toRead = byteCount;
            while (toRead > 0) {
                int read = this.is.read(buffer, byteCount - toRead, toRead);
                if (read == -1) {
                    break;
                }
                toRead -= read;
            }
            return byteCount - toRead;
        }

        public int getByte() throws IOException {
            return this.is.read();
        }
    }

    public ImageType getType(InputStream is) throws IOException {
        return getType(new StreamReader((InputStream) Preconditions.checkNotNull(is)));
    }

    public ImageType getType(ByteBuffer byteBuffer) throws IOException {
        return getType(new ByteBufferReader((ByteBuffer) Preconditions.checkNotNull(byteBuffer)));
    }

    public int getOrientation(InputStream is, ArrayPool byteArrayPool) throws IOException {
        return getOrientation(new StreamReader((InputStream) Preconditions.checkNotNull(is)), (ArrayPool) Preconditions.checkNotNull(byteArrayPool));
    }

    private ImageType getType(Reader reader) throws IOException {
        int firstTwoBytes = reader.getUInt16();
        if (firstTwoBytes == 65496) {
            return ImageType.JPEG;
        }
        int firstFourBytes = ((firstTwoBytes << 16) & -65536) | (reader.getUInt16() & 65535);
        if (firstFourBytes == -1991225785) {
            reader.skip(21);
            return reader.getByte() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((firstFourBytes >> 8) == 4671814) {
            return ImageType.GIF;
        } else {
            if (firstFourBytes != 1380533830) {
                return ImageType.UNKNOWN;
            }
            reader.skip(4);
            if ((((reader.getUInt16() << 16) & -65536) | (reader.getUInt16() & 65535)) != 1464156752) {
                return ImageType.UNKNOWN;
            }
            int fourthFourBytes = ((reader.getUInt16() << 16) & -65536) | (reader.getUInt16() & 65535);
            if ((fourthFourBytes & -256) != 1448097792) {
                return ImageType.UNKNOWN;
            }
            if ((fourthFourBytes & 255) == 88) {
                reader.skip(4);
                return (reader.getByte() & 16) != 0 ? ImageType.WEBP_A : ImageType.WEBP;
            } else if ((fourthFourBytes & 255) != 76) {
                return ImageType.WEBP;
            } else {
                reader.skip(4);
                return (reader.getByte() & 8) != 0 ? ImageType.WEBP_A : ImageType.WEBP;
            }
        }
    }

    private int getOrientation(Reader reader, ArrayPool byteArrayPool) throws IOException {
        int i = -1;
        int magicNumber = reader.getUInt16();
        if (handles(magicNumber)) {
            int exifSegmentLength = moveToExifSegmentAndGetLength(reader);
            if (exifSegmentLength != -1) {
                byte[] exifData = (byte[]) byteArrayPool.get(exifSegmentLength, byte[].class);
                try {
                    i = parseExifSegment(reader, exifData, exifSegmentLength);
                } finally {
                    byteArrayPool.put(exifData, byte[].class);
                }
            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + magicNumber);
        }
        return i;
    }

    private int parseExifSegment(Reader reader, byte[] tempArray, int exifSegmentLength) throws IOException {
        int read = reader.read(tempArray, exifSegmentLength);
        if (read != exifSegmentLength) {
            if (!Log.isLoggable("DfltImageHeaderParser", 3)) {
                return -1;
            }
            Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + exifSegmentLength + ", actually read: " + read);
            return -1;
        } else if (hasJpegExifPreamble(tempArray, exifSegmentLength)) {
            return parseExifSegment(new RandomAccessReader(tempArray, exifSegmentLength));
        } else {
            if (!Log.isLoggable("DfltImageHeaderParser", 3)) {
                return -1;
            }
            Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            return -1;
        }
    }

    private boolean hasJpegExifPreamble(byte[] exifData, int exifSegmentLength) {
        boolean result = exifData != null && exifSegmentLength > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (!result) {
            return result;
        }
        for (int i = 0; i < JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length; i++) {
            if (exifData[i] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[i]) {
                return false;
            }
        }
        return result;
    }

    private int moveToExifSegmentAndGetLength(Reader reader) throws IOException {
        long skipped;
        int segmentLength;
        do {
            short segmentId = reader.getUInt8();
            if (segmentId != (short) 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Unknown segmentId=" + segmentId);
                }
                return -1;
            }
            short segmentType = reader.getUInt8();
            if (segmentType == (short) 218) {
                return -1;
            }
            if (segmentType == (short) 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            segmentLength = reader.getUInt16() - 2;
            if (segmentType == (short) 225) {
                return segmentLength;
            }
            skipped = reader.skip((long) segmentLength);
        } while (skipped == ((long) segmentLength));
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to skip enough data, type: " + segmentType + ", wanted to skip: " + segmentLength + ", but actually skipped: " + skipped);
        }
        return -1;
    }

    private static int parseExifSegment(RandomAccessReader segmentData) {
        ByteOrder byteOrder;
        int headerOffsetSize = "Exif\u0000\u0000".length();
        short byteOrderIdentifier = segmentData.getInt16(headerOffsetSize);
        if (byteOrderIdentifier == (short) 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (byteOrderIdentifier == (short) 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Unknown endianness = " + byteOrderIdentifier);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        segmentData.order(byteOrder);
        int firstIfdOffset = segmentData.getInt32(headerOffsetSize + 4) + headerOffsetSize;
        int tagCount = segmentData.getInt16(firstIfdOffset);
        for (int i = 0; i < tagCount; i++) {
            int tagOffset = calcTagOffset(firstIfdOffset, i);
            int tagType = segmentData.getInt16(tagOffset);
            if (tagType == 274) {
                int formatCode = segmentData.getInt16(tagOffset + 2);
                if (formatCode >= 1 && formatCode <= 12) {
                    int componentCount = segmentData.getInt32(tagOffset + 4);
                    if (componentCount >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got tagIndex=" + i + " tagType=" + tagType + " formatCode=" + formatCode + " componentCount=" + componentCount);
                        }
                        int byteCount = componentCount + BYTES_PER_FORMAT[formatCode];
                        if (byteCount <= 4) {
                            int tagValueOffset = tagOffset + 8;
                            if (tagValueOffset < 0 || tagValueOffset > segmentData.length()) {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    Log.d("DfltImageHeaderParser", "Illegal tagValueOffset=" + tagValueOffset + " tagType=" + tagType);
                                }
                            } else if (byteCount >= 0 && tagValueOffset + byteCount <= segmentData.length()) {
                                return segmentData.getInt16(tagValueOffset);
                            } else {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    Log.d("DfltImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + tagType);
                                }
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + formatCode);
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        Log.d("DfltImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Got invalid format code = " + formatCode);
                }
            }
        }
        return -1;
    }

    private static int calcTagOffset(int ifdOffset, int tagIndex) {
        return (ifdOffset + 2) + (tagIndex * 12);
    }

    private static boolean handles(int imageMagicNumber) {
        return (imageMagicNumber & 65496) == 65496 || imageMagicNumber == 19789 || imageMagicNumber == 18761;
    }
}
