package com.coinbase.android.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.squareup.picasso.Transformation;

public class RoundedTransformation implements Transformation {
    private final int margin;
    private final int radius;

    public RoundedTransformation(int radius, int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    public Bitmap transform(Bitmap source) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Config.ARGB_8888);
        new Canvas(output).drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (source.getWidth() - this.margin), (float) (source.getHeight() - this.margin)), (float) this.radius, (float) this.radius, paint);
        if (source != output) {
            source.recycle();
        }
        return output;
    }

    public String key() {
        return "rounded(radius=" + this.radius + ", margin=" + this.margin + ")";
    }
}
