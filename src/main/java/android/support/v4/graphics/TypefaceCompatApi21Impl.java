package android.support.v4.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return new File(path);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontInfo[] fonts, int style) {
        Throwable th;
        if (fonts.length < 1) {
            return null;
        }
        FontInfo bestFont = findBestInfo(fonts, style);
        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(bestFont.getUri(), "r", cancellationSignal);
            Throwable th2 = null;
            try {
                File file = getFile(pfd);
                Typeface createFromInputStream;
                if (file == null || !file.canRead()) {
                    FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
                    th = null;
                    createFromInputStream = super.createFromInputStream(context, fis);
                    if (fis != null) {
                        if (th != null) {
                            fis.close();
                        } else {
                            fis.close();
                        }
                    }
                    if (pfd == null) {
                        return createFromInputStream;
                    }
                    if (th2 != null) {
                        try {
                            pfd.close();
                            return createFromInputStream;
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                            return createFromInputStream;
                        }
                    }
                    pfd.close();
                    return createFromInputStream;
                }
                createFromInputStream = Typeface.createFromFile(file);
                if (pfd == null) {
                    return createFromInputStream;
                }
                if (th2 != null) {
                    try {
                        pfd.close();
                        return createFromInputStream;
                    } catch (Throwable th32) {
                        th2.addSuppressed(th32);
                        return createFromInputStream;
                    }
                }
                pfd.close();
                return createFromInputStream;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                th32 = th2;
                if (pfd != null) {
                    if (th32 == null) {
                        pfd.close();
                    } else {
                        try {
                            pfd.close();
                        } catch (Throwable th22) {
                            th32.addSuppressed(th22);
                        }
                    }
                }
                throw th5;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
