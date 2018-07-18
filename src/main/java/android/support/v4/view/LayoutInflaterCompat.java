package android.support.v4.view;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
    static final LayoutInflaterCompatBaseImpl IMPL;
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    static class LayoutInflaterCompatBaseImpl {
        LayoutInflaterCompatBaseImpl() {
        }

        public void setFactory2(LayoutInflater inflater, Factory2 factory) {
            inflater.setFactory2(factory);
            Factory f = inflater.getFactory();
            if (f instanceof Factory2) {
                LayoutInflaterCompat.forceSetFactory2(inflater, (Factory2) f);
            } else {
                LayoutInflaterCompat.forceSetFactory2(inflater, factory);
            }
        }
    }

    static class LayoutInflaterCompatApi21Impl extends LayoutInflaterCompatBaseImpl {
        LayoutInflaterCompatApi21Impl() {
        }

        public void setFactory2(LayoutInflater inflater, Factory2 factory) {
            inflater.setFactory2(factory);
        }
    }

    static void forceSetFactory2(LayoutInflater inflater, Factory2 factory) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", e);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field != null) {
            try {
                sLayoutInflaterFactory2Field.set(inflater, factory);
            } catch (IllegalAccessException e2) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + inflater + "; inflation may have unexpected results.", e2);
            }
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            IMPL = new LayoutInflaterCompatApi21Impl();
        } else {
            IMPL = new LayoutInflaterCompatBaseImpl();
        }
    }

    public static void setFactory2(LayoutInflater inflater, Factory2 factory) {
        IMPL.setFactory2(inflater, factory);
    }
}
