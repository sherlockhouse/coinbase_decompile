package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public final class MenuItemCompat {
    static final MenuVersionImpl IMPL;

    interface MenuVersionImpl {
        void setAlphabeticShortcut(MenuItem menuItem, char c, int i);

        void setContentDescription(MenuItem menuItem, CharSequence charSequence);

        void setIconTintList(MenuItem menuItem, ColorStateList colorStateList);

        void setIconTintMode(MenuItem menuItem, Mode mode);

        void setNumericShortcut(MenuItem menuItem, char c, int i);

        void setTooltipText(MenuItem menuItem, CharSequence charSequence);
    }

    static class MenuItemCompatBaseImpl implements MenuVersionImpl {
        MenuItemCompatBaseImpl() {
        }

        public void setContentDescription(MenuItem item, CharSequence contentDescription) {
        }

        public void setTooltipText(MenuItem item, CharSequence tooltipText) {
        }

        public void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
        }

        public void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
        }

        public void setIconTintList(MenuItem item, ColorStateList tint) {
        }

        public void setIconTintMode(MenuItem item, Mode tintMode) {
        }
    }

    static class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl {
        MenuItemCompatApi26Impl() {
        }

        public void setContentDescription(MenuItem item, CharSequence contentDescription) {
            item.setContentDescription(contentDescription);
        }

        public void setTooltipText(MenuItem item, CharSequence tooltipText) {
            item.setTooltipText(tooltipText);
        }

        public void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
            item.setAlphabeticShortcut(alphaChar, alphaModifiers);
        }

        public void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
            item.setNumericShortcut(numericChar, numericModifiers);
        }

        public void setIconTintList(MenuItem item, ColorStateList tint) {
            item.setIconTintList(tint);
        }

        public void setIconTintMode(MenuItem item, Mode tintMode) {
            item.setIconTintMode(tintMode);
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            IMPL = new MenuItemCompatApi26Impl();
        } else {
            IMPL = new MenuItemCompatBaseImpl();
        }
    }

    @Deprecated
    public static View getActionView(MenuItem item) {
        return item.getActionView();
    }

    public static MenuItem setActionProvider(MenuItem item, ActionProvider provider) {
        if (item instanceof SupportMenuItem) {
            return ((SupportMenuItem) item).setSupportActionProvider(provider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return item;
    }

    public static void setContentDescription(MenuItem item, CharSequence contentDescription) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setContentDescription(contentDescription);
        } else {
            IMPL.setContentDescription(item, contentDescription);
        }
    }

    public static void setTooltipText(MenuItem item, CharSequence tooltipText) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setTooltipText(tooltipText);
        } else {
            IMPL.setTooltipText(item, tooltipText);
        }
    }

    public static void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setNumericShortcut(numericChar, numericModifiers);
        } else {
            IMPL.setNumericShortcut(item, numericChar, numericModifiers);
        }
    }

    public static void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setAlphabeticShortcut(alphaChar, alphaModifiers);
        } else {
            IMPL.setAlphabeticShortcut(item, alphaChar, alphaModifiers);
        }
    }

    public static void setIconTintList(MenuItem item, ColorStateList tint) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setIconTintList(tint);
        } else {
            IMPL.setIconTintList(item, tint);
        }
    }

    public static void setIconTintMode(MenuItem item, Mode tintMode) {
        if (item instanceof SupportMenuItem) {
            ((SupportMenuItem) item).setIconTintMode(tintMode);
        } else {
            IMPL.setIconTintMode(item, tintMode);
        }
    }
}
