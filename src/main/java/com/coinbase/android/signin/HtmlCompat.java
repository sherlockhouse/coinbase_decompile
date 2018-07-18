package com.coinbase.android.signin;

import android.os.Build.VERSION;
import android.text.Html;

public class HtmlCompat {
    public static CharSequence fromHtml(String source) {
        if (VERSION.SDK_INT < 24) {
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, 63);
    }
}
