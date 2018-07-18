package com.github.mikephil.charting.components;

import com.github.mikephil.charting.formatter.DefaultXAxisValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class XAxis extends AxisBase {
    private boolean mAvoidFirstLastClipping;
    public int mAxisLabelModulus;
    private boolean mIsAxisModulusCustom;
    public int mLabelHeight;
    public int mLabelRotatedHeight;
    public int mLabelRotatedWidth;
    protected float mLabelRotationAngle;
    public int mLabelWidth;
    private XAxisPosition mPosition;
    private int mSpaceBetweenLabels;
    protected List<String> mValues;
    protected XAxisValueFormatter mXAxisValueFormatter;
    public int mYAxisLabelModulus;

    public enum XAxisPosition {
        TOP,
        BOTTOM,
        BOTH_SIDED,
        TOP_INSIDE,
        BOTTOM_INSIDE
    }

    public XAxis() {
        this.mValues = new ArrayList();
        this.mLabelWidth = 1;
        this.mLabelHeight = 1;
        this.mLabelRotatedWidth = 1;
        this.mLabelRotatedHeight = 1;
        this.mLabelRotationAngle = 0.0f;
        this.mSpaceBetweenLabels = 4;
        this.mAxisLabelModulus = 1;
        this.mIsAxisModulusCustom = false;
        this.mYAxisLabelModulus = 1;
        this.mAvoidFirstLastClipping = false;
        this.mXAxisValueFormatter = new DefaultXAxisValueFormatter();
        this.mPosition = XAxisPosition.TOP;
        this.mYOffset = Utils.convertDpToPixel(4.0f);
    }

    public XAxisPosition getPosition() {
        return this.mPosition;
    }

    public void setPosition(XAxisPosition pos) {
        this.mPosition = pos;
    }

    public float getLabelRotationAngle() {
        return this.mLabelRotationAngle;
    }

    public void setSpaceBetweenLabels(int spaceCharacters) {
        this.mSpaceBetweenLabels = spaceCharacters;
    }

    public boolean isAxisModulusCustom() {
        return this.mIsAxisModulusCustom;
    }

    public int getSpaceBetweenLabels() {
        return this.mSpaceBetweenLabels;
    }

    public boolean isAvoidFirstLastClippingEnabled() {
        return this.mAvoidFirstLastClipping;
    }

    public void setValues(List<String> values) {
        this.mValues = values;
    }

    public List<String> getValues() {
        return this.mValues;
    }

    public void setValueFormatter(XAxisValueFormatter formatter) {
        if (formatter == null) {
            this.mXAxisValueFormatter = new DefaultXAxisValueFormatter();
        } else {
            this.mXAxisValueFormatter = formatter;
        }
    }

    public XAxisValueFormatter getValueFormatter() {
        return this.mXAxisValueFormatter;
    }

    public String getLongestLabel() {
        String longest = "";
        for (int i = 0; i < this.mValues.size(); i++) {
            String text = (String) this.mValues.get(i);
            if (longest.length() < text.length()) {
                longest = text;
            }
        }
        return longest;
    }
}
