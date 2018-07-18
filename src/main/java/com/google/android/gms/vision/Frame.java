package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public class Frame {
    private Bitmap mBitmap;
    private Metadata zzkhy;
    private ByteBuffer zzkhz;

    public static class Builder {
        private Frame zzkia = new Frame();

        public Frame build() {
            if (this.zzkia.zzkhz != null || this.zzkia.mBitmap != null) {
                return this.zzkia;
            }
            throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
        }

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.zzkia.mBitmap = bitmap;
            Metadata metadata = this.zzkia.getMetadata();
            metadata.zzakq = width;
            metadata.zzakr = height;
            return this;
        }

        public Builder setId(int i) {
            this.zzkia.getMetadata().mId = i;
            return this;
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            } else if (byteBuffer.capacity() < i * i2) {
                throw new IllegalArgumentException("Invalid image data size.");
            } else {
                switch (i3) {
                    case 16:
                    case 17:
                    case 842094169:
                        this.zzkia.zzkhz = byteBuffer;
                        Metadata metadata = this.zzkia.getMetadata();
                        metadata.zzakq = i;
                        metadata.zzakr = i2;
                        metadata.format = i3;
                        return this;
                    default:
                        throw new IllegalArgumentException("Unsupported image format: " + i3);
                }
            }
        }

        public Builder setRotation(int i) {
            this.zzkia.getMetadata().zzcew = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            this.zzkia.getMetadata().zzhln = j;
            return this;
        }
    }

    public static class Metadata {
        private int format = -1;
        private int mId;
        private int zzakq;
        private int zzakr;
        private int zzcew;
        private long zzhln;

        public Metadata(Metadata metadata) {
            this.zzakq = metadata.getWidth();
            this.zzakr = metadata.getHeight();
            this.mId = metadata.getId();
            this.zzhln = metadata.getTimestampMillis();
            this.zzcew = metadata.getRotation();
        }

        public int getHeight() {
            return this.zzakr;
        }

        public int getId() {
            return this.mId;
        }

        public int getRotation() {
            return this.zzcew;
        }

        public long getTimestampMillis() {
            return this.zzhln;
        }

        public int getWidth() {
            return this.zzakq;
        }

        public final void zzbil() {
            if (this.zzcew % 2 != 0) {
                int i = this.zzakq;
                this.zzakq = this.zzakr;
                this.zzakr = i;
            }
            this.zzcew = 0;
        }
    }

    private Frame() {
        this.zzkhy = new Metadata();
        this.zzkhz = null;
        this.mBitmap = null;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public ByteBuffer getGrayscaleImageData() {
        int i = 0;
        if (this.mBitmap == null) {
            return this.zzkhz;
        }
        int width = this.mBitmap.getWidth();
        int height = this.mBitmap.getHeight();
        int[] iArr = new int[(width * height)];
        this.mBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(width * height)];
        while (i < iArr.length) {
            bArr[i] = (byte) ((int) (((((float) Color.red(iArr[i])) * 0.299f) + (((float) Color.green(iArr[i])) * 0.587f)) + (((float) Color.blue(iArr[i])) * 0.114f)));
            i++;
        }
        return ByteBuffer.wrap(bArr);
    }

    public Metadata getMetadata() {
        return this.zzkhy;
    }
}
