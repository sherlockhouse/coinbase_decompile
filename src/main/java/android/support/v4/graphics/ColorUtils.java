package android.support.v4.graphics;

import android.graphics.Color;

public final class ColorUtils {
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal();

    public static int compositeColors(int foreground, int background) {
        int bgAlpha = Color.alpha(background);
        int fgAlpha = Color.alpha(foreground);
        int a = compositeAlpha(fgAlpha, bgAlpha);
        return Color.argb(a, compositeComponent(Color.red(foreground), fgAlpha, Color.red(background), bgAlpha, a), compositeComponent(Color.green(foreground), fgAlpha, Color.green(background), bgAlpha, a), compositeComponent(Color.blue(foreground), fgAlpha, Color.blue(background), bgAlpha, a));
    }

    private static int compositeAlpha(int foregroundAlpha, int backgroundAlpha) {
        return 255 - (((255 - backgroundAlpha) * (255 - foregroundAlpha)) / 255);
    }

    private static int compositeComponent(int fgC, int fgA, int bgC, int bgA, int a) {
        if (a == 0) {
            return 0;
        }
        return (((fgC * 255) * fgA) + ((bgC * bgA) * (255 - fgA))) / (a * 255);
    }

    public static int setAlphaComponent(int color, int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            return (16777215 & color) | (alpha << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}
