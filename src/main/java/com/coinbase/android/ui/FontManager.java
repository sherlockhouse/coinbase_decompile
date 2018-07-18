package com.coinbase.android.ui;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static Map<String, Typeface> mFonts = new HashMap();

    public static Typeface getFont(Context c, String name) {
        if (mFonts.containsKey(name)) {
            return (Typeface) mFonts.get(name);
        }
        mFonts.put(name, Typeface.createFromAsset(c.getAssets(), name + ".ttf"));
        return (Typeface) mFonts.get(name);
    }
}
