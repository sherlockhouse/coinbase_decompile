package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    public static Drawable getDrawable(Resources res, int id, Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    public static Typeface getFont(Context context, int id, TypedValue value, int style, TextView targetView) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, targetView);
    }

    private static Typeface loadFont(Context context, int id, TypedValue value, int style, TextView targetView) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, targetView);
        if (typeface != null) {
            return typeface;
        }
        throw new NotFoundException("Font resource ID #0x" + Integer.toHexString(id));
    }

    private static Typeface loadFont(Context context, Resources wrapper, TypedValue value, int id, int style, TextView targetView) {
        if (value.string == null) {
            throw new NotFoundException("Resource \"" + wrapper.getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Font: " + value);
        }
        String file = value.string.toString();
        if (!file.startsWith("res/")) {
            return null;
        }
        Typeface cached = TypefaceCompat.findFromCache(wrapper, id, style);
        if (cached != null) {
            return cached;
        }
        try {
            if (!file.toLowerCase().endsWith(".xml")) {
                return TypefaceCompat.createFromResourcesFontFile(context, wrapper, id, file, style);
            }
            FamilyResourceEntry familyEntry = FontResourcesParserCompat.parse(wrapper.getXml(id), wrapper);
            if (familyEntry != null) {
                return TypefaceCompat.createFromResourcesFamilyXml(context, familyEntry, wrapper, id, style, targetView);
            }
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            return null;
        } catch (XmlPullParserException e) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + file, e);
            return null;
        } catch (IOException e2) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + file, e2);
            return null;
        }
    }
}
