package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    private static final CompoundButtonCompatBaseImpl IMPL;

    static class CompoundButtonCompatBaseImpl {
        private static Field sButtonDrawableField;
        private static boolean sButtonDrawableFieldFetched;

        CompoundButtonCompatBaseImpl() {
        }

        public void setButtonTintList(CompoundButton button, ColorStateList tint) {
            if (button instanceof TintableCompoundButton) {
                ((TintableCompoundButton) button).setSupportButtonTintList(tint);
            }
        }

        public void setButtonTintMode(CompoundButton button, Mode tintMode) {
            if (button instanceof TintableCompoundButton) {
                ((TintableCompoundButton) button).setSupportButtonTintMode(tintMode);
            }
        }

        public Drawable getButtonDrawable(CompoundButton button) {
            if (!sButtonDrawableFieldFetched) {
                try {
                    sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    sButtonDrawableField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", e);
                }
                sButtonDrawableFieldFetched = true;
            }
            if (sButtonDrawableField != null) {
                try {
                    return (Drawable) sButtonDrawableField.get(button);
                } catch (IllegalAccessException e2) {
                    Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", e2);
                    sButtonDrawableField = null;
                }
            }
            return null;
        }
    }

    static class CompoundButtonCompatApi21Impl extends CompoundButtonCompatBaseImpl {
        CompoundButtonCompatApi21Impl() {
        }

        public void setButtonTintList(CompoundButton button, ColorStateList tint) {
            button.setButtonTintList(tint);
        }

        public void setButtonTintMode(CompoundButton button, Mode tintMode) {
            button.setButtonTintMode(tintMode);
        }
    }

    static class CompoundButtonCompatApi23Impl extends CompoundButtonCompatApi21Impl {
        CompoundButtonCompatApi23Impl() {
        }

        public Drawable getButtonDrawable(CompoundButton button) {
            return button.getButtonDrawable();
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            IMPL = new CompoundButtonCompatApi23Impl();
        } else if (VERSION.SDK_INT >= 21) {
            IMPL = new CompoundButtonCompatApi21Impl();
        } else {
            IMPL = new CompoundButtonCompatBaseImpl();
        }
    }

    public static void setButtonTintList(CompoundButton button, ColorStateList tint) {
        IMPL.setButtonTintList(button, tint);
    }

    public static void setButtonTintMode(CompoundButton button, Mode tintMode) {
        IMPL.setButtonTintMode(button, tintMode);
    }

    public static Drawable getButtonDrawable(CompoundButton button) {
        return IMPL.getButtonDrawable(button);
    }
}
