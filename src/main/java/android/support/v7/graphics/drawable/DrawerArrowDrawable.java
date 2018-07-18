package android.support.v7.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

public class DrawerArrowDrawable extends Drawable {
    private static final float ARROW_HEAD_ANGLE = ((float) Math.toRadians(45.0d));
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    public void setVerticalMirror(boolean verticalMirror) {
        if (this.mVerticalMirror != verticalMirror) {
            this.mVerticalMirror = verticalMirror;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        boolean flipToPointRight;
        float f;
        float f2;
        Rect bounds = getBounds();
        switch (this.mDirection) {
            case 0:
                flipToPointRight = false;
                break;
            case 1:
                flipToPointRight = true;
                break;
            case 3:
                flipToPointRight = DrawableCompat.getLayoutDirection(this) == 0;
                break;
            default:
                if (DrawableCompat.getLayoutDirection(this) != 1) {
                    flipToPointRight = false;
                    break;
                } else {
                    flipToPointRight = true;
                    break;
                }
        }
        float arrowHeadBarLength = lerp(this.mBarLength, (float) Math.sqrt((double) ((this.mArrowHeadLength * this.mArrowHeadLength) * 2.0f)), this.mProgress);
        float arrowShaftLength = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        float arrowShaftCut = (float) Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float rotation = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        if (flipToPointRight) {
            f = 0.0f;
        } else {
            f = -180.0f;
        }
        if (flipToPointRight) {
            f2 = 180.0f;
        } else {
            f2 = 0.0f;
        }
        float canvasRotate = lerp(f, f2, this.mProgress);
        float arrowWidth = (float) Math.round(((double) arrowHeadBarLength) * Math.cos((double) rotation));
        float arrowHeight = (float) Math.round(((double) arrowHeadBarLength) * Math.sin((double) rotation));
        this.mPath.rewind();
        float topBottomBarOffset = lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        float arrowEdge = (-arrowShaftLength) / 2.0f;
        this.mPath.moveTo(arrowEdge + arrowShaftCut, 0.0f);
        this.mPath.rLineTo(arrowShaftLength - (2.0f * arrowShaftCut), 0.0f);
        this.mPath.moveTo(arrowEdge, topBottomBarOffset);
        this.mPath.rLineTo(arrowWidth, arrowHeight);
        this.mPath.moveTo(arrowEdge, -topBottomBarOffset);
        this.mPath.rLineTo(arrowWidth, -arrowHeight);
        this.mPath.close();
        canvas.save();
        float barThickness = this.mPaint.getStrokeWidth();
        canvas.translate((float) bounds.centerX(), ((float) ((((int) ((((float) bounds.height()) - (3.0f * barThickness)) - (this.mBarGap * 2.0f))) / 4) * 2)) + ((1.5f * barThickness) + this.mBarGap));
        if (this.mSpin) {
            canvas.rotate(((float) ((this.mVerticalMirror ^ flipToPointRight) != 0 ? -1 : 1)) * canvasRotate);
        } else if (flipToPointRight) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public void setAlpha(int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    public void setProgress(float progress) {
        if (this.mProgress != progress) {
            this.mProgress = progress;
            invalidateSelf();
        }
    }

    private static float lerp(float a, float b, float t) {
        return ((b - a) * t) + a;
    }
}
