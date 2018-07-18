package com.coinbase.android.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

public class AvatarDrawable extends Drawable {
    private final Bitmap mOriginalBitmap;
    private final Paint mPaint;
    private final RectF mRect = new RectF();

    public AvatarDrawable(Bitmap bitmap) {
        this.mOriginalBitmap = bitmap;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mPaint.setShader(new BitmapShader(Bitmap.createScaledBitmap(this.mOriginalBitmap, bounds.width(), bounds.height(), true), TileMode.CLAMP, TileMode.CLAMP));
        this.mRect.set(0.0f, 0.0f, (float) bounds.width(), (float) bounds.height());
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.mRect, this.mRect.width() / 2.0f, this.mRect.height() / 2.0f, this.mPaint);
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }
}
