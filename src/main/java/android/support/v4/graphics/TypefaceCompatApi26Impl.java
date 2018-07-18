package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.Typeface.Builder;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method addFromBufferMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod;
        ReflectiveOperationException e;
        try {
            fontFamilyClass = Class.forName("android.graphics.FontFamily");
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            addFontMethod = fontFamilyClass.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
            addFromBufferMethod = fontFamilyClass.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
            freezeMethod = fontFamilyClass.getMethod("freeze", new Class[0]);
            abortCreationMethod = fontFamilyClass.getMethod("abortCreation", new Class[0]);
            createFromFamiliesWithDefaultMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(fontFamilyClass, 1).getClass(), Integer.TYPE, Integer.TYPE});
            createFromFamiliesWithDefaultMethod.setAccessible(true);
        } catch (ClassNotFoundException e2) {
            e = e2;
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            sFontFamilyCtor = fontFamilyCtor;
            sFontFamily = fontFamilyClass;
            sAddFontFromAssetManager = addFontMethod;
            sAddFontFromBuffer = addFromBufferMethod;
            sFreeze = freezeMethod;
            sAbortCreation = abortCreationMethod;
            sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
        } catch (NoSuchMethodException e3) {
            e = e3;
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            sFontFamilyCtor = fontFamilyCtor;
            sFontFamily = fontFamilyClass;
            sAddFontFromAssetManager = addFontMethod;
            sAddFontFromBuffer = addFromBufferMethod;
            sFreeze = freezeMethod;
            sAbortCreation = abortCreationMethod;
            sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
        }
        sFontFamilyCtor = fontFamilyCtor;
        sFontFamily = fontFamilyClass;
        sAddFontFromAssetManager = addFontMethod;
        sAddFontFromBuffer = addFromBufferMethod;
        sFreeze = freezeMethod;
        sAbortCreation = abortCreationMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        ReflectiveOperationException e;
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InstantiationException e3) {
            e = e3;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e4) {
            e = e4;
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) sAddFontFromAssetManager.invoke(family, new Object[]{context.getAssets(), fileName, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), null})).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) sAddFontFromBuffer.invoke(family, new Object[]{buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style)})).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) {
        ReflectiveOperationException e;
        try {
            Array.set(Array.newInstance(sFontFamily, 1), 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, new Object[]{familyArray, Integer.valueOf(-1), Integer.valueOf(-1)});
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object family) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) sFreeze.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    private static boolean abortCreation(Object family) {
        ReflectiveOperationException e;
        try {
            return ((Boolean) sAbortCreation.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        FontFileResourceEntry[] entries = entry.getEntries();
        int length = entries.length;
        int i = 0;
        while (i < length) {
            int i2;
            FontFileResourceEntry fontFile = entries[i];
            String fileName = fontFile.getFileName();
            int weight = fontFile.getWeight();
            if (fontFile.isItalic()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (addFontFromAssetManager(context, fontFamily, fileName, 0, weight, i2)) {
                i++;
            } else {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontInfo[] fonts, int style) {
        ParcelFileDescriptor pfd;
        Throwable th;
        Throwable th2;
        if (fonts.length < 1) {
            return null;
        }
        if (isFontFamilyPrivateAPIAvailable()) {
            Map<Uri, ByteBuffer> uriBuffer = FontsContractCompat.prepareFontData(context, fonts, cancellationSignal);
            Object fontFamily = newFamily();
            boolean atLeastOneFont = false;
            for (FontInfo font : fonts) {
                ByteBuffer fontBuffer = (ByteBuffer) uriBuffer.get(font.getUri());
                if (fontBuffer != null) {
                    if (addFontFromBuffer(fontFamily, fontBuffer, font.getTtcIndex(), font.getWeight(), font.isItalic() ? 1 : 0)) {
                        atLeastOneFont = true;
                    } else {
                        abortCreation(fontFamily);
                        return null;
                    }
                }
            }
            if (!atLeastOneFont) {
                abortCreation(fontFamily);
                return null;
            } else if (freeze(fontFamily)) {
                return createFromFamiliesWithDefault(fontFamily);
            } else {
                return null;
            }
        }
        FontInfo bestFont = findBestInfo(fonts, style);
        try {
            pfd = context.getContentResolver().openFileDescriptor(bestFont.getUri(), "r", cancellationSignal);
            th = null;
            try {
                Typeface build = new Builder(pfd.getFileDescriptor()).setWeight(bestFont.getWeight()).setItalic(bestFont.isItalic()).build();
                if (pfd == null) {
                    return build;
                }
                if (th != null) {
                    try {
                        pfd.close();
                        return build;
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                        return build;
                    }
                }
                pfd.close();
                return build;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                th4 = th2;
                th2 = th5;
            }
        } catch (IOException e) {
            return null;
        }
        if (pfd != null) {
            if (th4 != null) {
                try {
                    pfd.close();
                } catch (Throwable th32) {
                    th4.addSuppressed(th32);
                }
            } else {
                pfd.close();
            }
        }
        throw th2;
        throw th2;
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        } else if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        } else {
            return null;
        }
    }
}
