package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class TypefaceCompatUtil {
    public static File getTempFile(Context context) {
        String prefix = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), prefix + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    private static ByteBuffer mmap(File file) {
        Throwable th;
        try {
            Throwable th2;
            FileInputStream fis = new FileInputStream(file);
            Throwable th3 = null;
            try {
                FileChannel channel = fis.getChannel();
                ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                if (fis == null) {
                    return map;
                }
                if (null != null) {
                    try {
                        fis.close();
                        return map;
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                        return map;
                    }
                }
                fis.close();
                return map;
            } catch (Throwable th42) {
                Throwable th5 = th42;
                th42 = th2;
                th2 = th5;
            }
            if (fis != null) {
                if (th42 != null) {
                    try {
                        fis.close();
                    } catch (Throwable th6) {
                        th42.addSuppressed(th6);
                    }
                } else {
                    fis.close();
                }
            }
            throw th2;
            throw th2;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) {
        Throwable th;
        try {
            ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            Throwable th2 = null;
            Throwable th3;
            Throwable th4;
            try {
                FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
                Throwable th5 = null;
                try {
                    FileChannel channel = fis.getChannel();
                    ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                    if (fis != null) {
                        if (th5 != null) {
                            fis.close();
                        } else {
                            fis.close();
                        }
                    }
                    if (pfd == null) {
                        return map;
                    }
                    if (th2 != null) {
                        try {
                            pfd.close();
                            return map;
                        } catch (Throwable th32) {
                            th2.addSuppressed(th32);
                            return map;
                        }
                    }
                    pfd.close();
                    return map;
                } catch (Throwable th322) {
                    th = th322;
                    th322 = th4;
                    th4 = th;
                }
                if (fis != null) {
                    if (th322 != null) {
                        fis.close();
                    } else {
                        fis.close();
                    }
                }
                throw th4;
                throw th4;
                throw th4;
                if (pfd != null) {
                    if (th322 != null) {
                        try {
                            pfd.close();
                        } catch (Throwable th6) {
                            th322.addSuppressed(th6);
                        }
                    } else {
                        pfd.close();
                    }
                }
                throw th4;
            } catch (Throwable th3222) {
                th = th3222;
                th3222 = th4;
                th4 = th;
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        ByteBuffer byteBuffer = null;
        File tmpFile = getTempFile(context);
        if (tmpFile != null) {
            try {
                if (copyToFile(tmpFile, res, id)) {
                    byteBuffer = mmap(tmpFile);
                    tmpFile.delete();
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream is) {
        IOException e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream os = new FileOutputStream(file, false);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen != -1) {
                        os.write(buffer, 0, readLen);
                    } else {
                        closeQuietly(os);
                        fileOutputStream = os;
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = os;
                try {
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = os;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            return false;
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream inputStream = null;
        try {
            inputStream = res.openRawResource(id);
            boolean copyToFile = copyToFile(file, inputStream);
            return copyToFile;
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
