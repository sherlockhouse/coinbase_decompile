package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

public class TypedArrayUtils {
    public static boolean hasAttribute(XmlPullParser parser, String attrName) {
        return parser.getAttributeValue("http://schemas.android.com/apk/res/android", attrName) != null;
    }

    public static float getNamedFloat(TypedArray a, XmlPullParser parser, String attrName, int resId, float defaultValue) {
        return !hasAttribute(parser, attrName) ? defaultValue : a.getFloat(resId, defaultValue);
    }

    public static boolean getNamedBoolean(TypedArray a, XmlPullParser parser, String attrName, int resId, boolean defaultValue) {
        return !hasAttribute(parser, attrName) ? defaultValue : a.getBoolean(resId, defaultValue);
    }

    public static int getNamedInt(TypedArray a, XmlPullParser parser, String attrName, int resId, int defaultValue) {
        return !hasAttribute(parser, attrName) ? defaultValue : a.getInt(resId, defaultValue);
    }

    public static int getNamedColor(TypedArray a, XmlPullParser parser, String attrName, int resId, int defaultValue) {
        return !hasAttribute(parser, attrName) ? defaultValue : a.getColor(resId, defaultValue);
    }

    public static int getNamedResourceId(TypedArray a, XmlPullParser parser, String attrName, int resId, int defaultValue) {
        return !hasAttribute(parser, attrName) ? defaultValue : a.getResourceId(resId, defaultValue);
    }

    public static String getNamedString(TypedArray a, XmlPullParser parser, String attrName, int resId) {
        if (hasAttribute(parser, attrName)) {
            return a.getString(resId);
        }
        return null;
    }

    public static TypedValue peekNamedValue(TypedArray a, XmlPullParser parser, String attrName, int resId) {
        if (hasAttribute(parser, attrName)) {
            return a.peekValue(resId);
        }
        return null;
    }

    public static TypedArray obtainAttributes(Resources res, Theme theme, AttributeSet set, int[] attrs) {
        if (theme == null) {
            return res.obtainAttributes(set, attrs);
        }
        return theme.obtainStyledAttributes(set, attrs, 0, 0);
    }
}
