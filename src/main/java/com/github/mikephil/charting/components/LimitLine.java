package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.utils.Utils;

public class LimitLine extends ComponentBase {
    private DashPathEffect mDashPathEffect = null;
    private String mLabel = "";
    private LimitLabelPosition mLabelPosition = LimitLabelPosition.RIGHT_TOP;
    private float mLimit = 0.0f;
    private int mLineColor = Color.rgb(237, 91, 91);
    private float mLineWidth = 2.0f;
    private Style mTextStyle = Style.FILL_AND_STROKE;

    public enum LimitLabelPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    public LimitLine(float limit) {
        this.mLimit = limit;
    }

    public float getLimit() {
        return this.mLimit;
    }

    public void setLineWidth(float width) {
        if (width < 0.2f) {
            width = 0.2f;
        }
        if (width > 12.0f) {
            width = 12.0f;
        }
        this.mLineWidth = Utils.convertDpToPixel(width);
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setLineColor(int color) {
        this.mLineColor = color;
    }

    public int getLineColor() {
        return this.mLineColor;
    }

    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        this.mDashPathEffect = new DashPathEffect(new float[]{lineLength, spaceLength}, phase);
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public Style getTextStyle() {
        return this.mTextStyle;
    }

    public LimitLabelPosition getLabelPosition() {
        return this.mLabelPosition;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
