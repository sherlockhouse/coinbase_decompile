package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class ThumbnailStreamOpener {
    private static final FileService DEFAULT_SERVICE = new FileService();
    private final ArrayPool byteArrayPool;
    private final ContentResolver contentResolver;
    private final List<ImageHeaderParser> parsers;
    private final ThumbnailQuery query;
    private final FileService service;

    public java.io.InputStream open(android.net.Uri r11) throws java.io.FileNotFoundException {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        r6 = 0;
        r5 = 0;
        r3 = 0;
        r7 = r10.query;
        r0 = r7.query(r11);
        if (r0 == 0) goto L_0x0011;
    L_0x000b:
        r7 = r0.moveToFirst();	 Catch:{ all -> 0x0055 }
        if (r7 != 0) goto L_0x0017;
    L_0x0011:
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r0.close();
    L_0x0016:
        return r6;
    L_0x0017:
        r7 = 0;
        r4 = r0.getString(r7);	 Catch:{ all -> 0x0055 }
        r7 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0055 }
        if (r7 == 0) goto L_0x0028;
    L_0x0022:
        if (r0 == 0) goto L_0x0016;
    L_0x0024:
        r0.close();
        goto L_0x0016;
    L_0x0028:
        r6 = r10.service;	 Catch:{ all -> 0x0055 }
        r2 = r6.get(r4);	 Catch:{ all -> 0x0055 }
        r6 = r10.service;	 Catch:{ all -> 0x0055 }
        r6 = r6.exists(r2);	 Catch:{ all -> 0x0055 }
        if (r6 == 0) goto L_0x0046;	 Catch:{ all -> 0x0055 }
    L_0x0036:
        r6 = r10.service;	 Catch:{ all -> 0x0055 }
        r6 = r6.length(r2);	 Catch:{ all -> 0x0055 }
        r8 = 0;	 Catch:{ all -> 0x0055 }
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));	 Catch:{ all -> 0x0055 }
        if (r6 <= 0) goto L_0x0046;	 Catch:{ all -> 0x0055 }
    L_0x0042:
        r5 = android.net.Uri.fromFile(r2);	 Catch:{ all -> 0x0055 }
    L_0x0046:
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.close();
    L_0x004b:
        if (r5 == 0) goto L_0x0053;
    L_0x004d:
        r6 = r10.contentResolver;	 Catch:{ NullPointerException -> 0x005c }
        r3 = r6.openInputStream(r5);	 Catch:{ NullPointerException -> 0x005c }
    L_0x0053:
        r6 = r3;
        goto L_0x0016;
    L_0x0055:
        r6 = move-exception;
        if (r0 == 0) goto L_0x005b;
    L_0x0058:
        r0.close();
    L_0x005b:
        throw r6;
    L_0x005c:
        r1 = move-exception;
        r6 = new java.io.FileNotFoundException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "NPE opening uri: ";
        r7 = r7.append(r8);
        r7 = r7.append(r5);
        r7 = r7.toString();
        r6.<init>(r7);
        r6 = r6.initCause(r1);
        r6 = (java.io.FileNotFoundException) r6;
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.mediastore.ThumbnailStreamOpener.open(android.net.Uri):java.io.InputStream");
    }

    public ThumbnailStreamOpener(List<ImageHeaderParser> parsers, ThumbnailQuery query, ArrayPool byteArrayPool, ContentResolver contentResolver) {
        this(parsers, DEFAULT_SERVICE, query, byteArrayPool, contentResolver);
    }

    public ThumbnailStreamOpener(List<ImageHeaderParser> parsers, FileService service, ThumbnailQuery query, ArrayPool byteArrayPool, ContentResolver contentResolver) {
        this.service = service;
        this.query = query;
        this.byteArrayPool = byteArrayPool;
        this.contentResolver = contentResolver;
        this.parsers = parsers;
    }

    public int getOrientation(Uri uri) {
        Exception e;
        InputStream is = null;
        try {
            is = this.contentResolver.openInputStream(uri);
            int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, is, this.byteArrayPool);
            if (is == null) {
                return orientation;
            }
            try {
                is.close();
                return orientation;
            } catch (IOException e2) {
                return orientation;
            }
        } catch (Exception e3) {
            e = e3;
            try {
                if (Log.isLoggable("ThumbStreamOpener", 3)) {
                    Log.d("ThumbStreamOpener", "Failed to open uri: " + uri, e);
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e4) {
                    }
                }
                return -1;
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e5) {
                    }
                }
            }
        } catch (Exception e32) {
            e = e32;
            if (Log.isLoggable("ThumbStreamOpener", 3)) {
                Log.d("ThumbStreamOpener", "Failed to open uri: " + uri, e);
            }
            if (is != null) {
                is.close();
            }
            return -1;
        }
    }
}
