package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class Legend extends ComponentBase {
    private Boolean[] mCalculatedLabelBreakPoints;
    private FSize[] mCalculatedLabelSizes;
    private FSize[] mCalculatedLineSizes;
    private int[] mColors;
    private LegendDirection mDirection;
    private int[] mExtraColors;
    private String[] mExtraLabels;
    private float mFormSize;
    private float mFormToTextSpace;
    private boolean mIsLegendCustom;
    private String[] mLabels;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendPosition mPosition;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    public Legend() {
        this.mIsLegendCustom = false;
        this.mPosition = LegendPosition.BELOW_CHART_LEFT;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new FSize[0];
        this.mCalculatedLabelBreakPoints = new Boolean[0];
        this.mCalculatedLineSizes = new FSize[0];
        this.mFormSize = Utils.convertDpToPixel(8.0f);
        this.mXEntrySpace = Utils.convertDpToPixel(6.0f);
        this.mYEntrySpace = Utils.convertDpToPixel(0.0f);
        this.mFormToTextSpace = Utils.convertDpToPixel(5.0f);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mStackSpace = Utils.convertDpToPixel(3.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(7.0f);
    }

    public void setComputedColors(List<Integer> colors) {
        this.mColors = Utils.convertIntegers(colors);
    }

    public void setComputedLabels(List<String> labels) {
        this.mLabels = Utils.convertStrings(labels);
    }

    public float getMaximumEntryWidth(Paint p) {
        float max = 0.0f;
        for (int i = 0; i < this.mLabels.length; i++) {
            if (this.mLabels[i] != null) {
                float length = (float) Utils.calcTextWidth(p, this.mLabels[i]);
                if (length > max) {
                    max = length;
                }
            }
        }
        return (this.mFormSize + max) + this.mFormToTextSpace;
    }

    public float getMaximumEntryHeight(Paint p) {
        float max = 0.0f;
        for (int i = 0; i < this.mLabels.length; i++) {
            if (this.mLabels[i] != null) {
                float length = (float) Utils.calcTextHeight(p, this.mLabels[i]);
                if (length > max) {
                    max = length;
                }
            }
        }
        return max;
    }

    public int[] getColors() {
        return this.mColors;
    }

    public String[] getLabels() {
        return this.mLabels;
    }

    public int[] getExtraColors() {
        return this.mExtraColors;
    }

    public String[] getExtraLabels() {
        return this.mExtraLabels;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendPosition getPosition() {
        return this.mPosition;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm shape) {
        this.mShape = shape;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public float getFullWidth(Paint labelpaint) {
        float width = 0.0f;
        for (int i = 0; i < this.mLabels.length; i++) {
            if (this.mLabels[i] != null) {
                if (this.mColors[i] != -2) {
                    width += this.mFormSize + this.mFormToTextSpace;
                }
                width += (float) Utils.calcTextWidth(labelpaint, this.mLabels[i]);
                if (i < this.mLabels.length - 1) {
                    width += this.mXEntrySpace;
                }
            } else {
                width += this.mFormSize;
                if (i < this.mLabels.length - 1) {
                    width += this.mStackSpace;
                }
            }
        }
        return width;
    }

    public float getFullHeight(Paint labelpaint) {
        float height = 0.0f;
        for (int i = 0; i < this.mLabels.length; i++) {
            if (this.mLabels[i] != null) {
                height += (float) Utils.calcTextHeight(labelpaint, this.mLabels[i]);
                if (i < this.mLabels.length - 1) {
                    height += this.mYEntrySpace;
                }
            }
        }
        return height;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public FSize[] getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public Boolean[] getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public FSize[] getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {
        if (this.mPosition == LegendPosition.RIGHT_OF_CHART || this.mPosition == LegendPosition.RIGHT_OF_CHART_CENTER || this.mPosition == LegendPosition.LEFT_OF_CHART || this.mPosition == LegendPosition.LEFT_OF_CHART_CENTER || this.mPosition == LegendPosition.PIECHART_CENTER) {
            this.mNeededWidth = getMaximumEntryWidth(labelpaint);
            this.mNeededHeight = getFullHeight(labelpaint);
            this.mTextWidthMax = this.mNeededWidth;
            this.mTextHeightMax = getMaximumEntryHeight(labelpaint);
        } else if (this.mPosition == LegendPosition.BELOW_CHART_LEFT || this.mPosition == LegendPosition.BELOW_CHART_RIGHT || this.mPosition == LegendPosition.BELOW_CHART_CENTER || this.mPosition == LegendPosition.ABOVE_CHART_LEFT || this.mPosition == LegendPosition.ABOVE_CHART_RIGHT || this.mPosition == LegendPosition.ABOVE_CHART_CENTER) {
            int labelCount = this.mLabels.length;
            float labelLineHeight = Utils.getLineHeight(labelpaint);
            float labelLineSpacing = Utils.getLineSpacing(labelpaint) + this.mYEntrySpace;
            float contentWidth = viewPortHandler.contentWidth();
            ArrayList<FSize> calculatedLabelSizes = new ArrayList(labelCount);
            ArrayList<Boolean> calculatedLabelBreakPoints = new ArrayList(labelCount);
            ArrayList<FSize> calculatedLineSizes = new ArrayList();
            float maxLineWidth = 0.0f;
            float currentLineWidth = 0.0f;
            float requiredWidth = 0.0f;
            int stackedStartIndex = -1;
            int i = 0;
            while (i < labelCount) {
                boolean drawingForm = this.mColors[i] != -2;
                calculatedLabelBreakPoints.add(Boolean.valueOf(false));
                if (stackedStartIndex == -1) {
                    requiredWidth = 0.0f;
                } else {
                    requiredWidth += this.mStackSpace;
                }
                if (this.mLabels[i] != null) {
                    calculatedLabelSizes.add(Utils.calcTextSize(labelpaint, this.mLabels[i]));
                    requiredWidth = (requiredWidth + (drawingForm ? this.mFormToTextSpace + this.mFormSize : 0.0f)) + ((FSize) calculatedLabelSizes.get(i)).width;
                } else {
                    calculatedLabelSizes.add(new FSize(0.0f, 0.0f));
                    requiredWidth += drawingForm ? this.mFormSize : 0.0f;
                    if (stackedStartIndex == -1) {
                        stackedStartIndex = i;
                    }
                }
                if (this.mLabels[i] != null || i == labelCount - 1) {
                    float requiredSpacing;
                    if (currentLineWidth == 0.0f) {
                        requiredSpacing = 0.0f;
                    } else {
                        requiredSpacing = this.mXEntrySpace;
                    }
                    if (!this.mWordWrapEnabled || currentLineWidth == 0.0f || contentWidth - currentLineWidth >= requiredSpacing + requiredWidth) {
                        currentLineWidth += requiredSpacing + requiredWidth;
                    } else {
                        int i2;
                        calculatedLineSizes.add(new FSize(currentLineWidth, labelLineHeight));
                        maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                        if (stackedStartIndex > -1) {
                            i2 = stackedStartIndex;
                        } else {
                            i2 = i;
                        }
                        calculatedLabelBreakPoints.set(i2, Boolean.valueOf(true));
                        currentLineWidth = requiredWidth;
                    }
                    if (i == labelCount - 1) {
                        calculatedLineSizes.add(new FSize(currentLineWidth, labelLineHeight));
                        maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                    }
                }
                if (this.mLabels[i] != null) {
                    stackedStartIndex = -1;
                }
                i++;
            }
            this.mCalculatedLabelSizes = (FSize[]) calculatedLabelSizes.toArray(new FSize[calculatedLabelSizes.size()]);
            this.mCalculatedLabelBreakPoints = (Boolean[]) calculatedLabelBreakPoints.toArray(new Boolean[calculatedLabelBreakPoints.size()]);
            this.mCalculatedLineSizes = (FSize[]) calculatedLineSizes.toArray(new FSize[calculatedLineSizes.size()]);
            this.mTextWidthMax = getMaximumEntryWidth(labelpaint);
            this.mTextHeightMax = getMaximumEntryHeight(labelpaint);
            this.mNeededWidth = maxLineWidth;
            this.mNeededHeight = (((float) (this.mCalculatedLineSizes.length == 0 ? 0 : this.mCalculatedLineSizes.length - 1)) * labelLineSpacing) + (labelLineHeight * ((float) this.mCalculatedLineSizes.length));
        } else {
            this.mNeededWidth = getFullWidth(labelpaint);
            this.mNeededHeight = getMaximumEntryHeight(labelpaint);
            this.mTextWidthMax = getMaximumEntryWidth(labelpaint);
            this.mTextHeightMax = this.mNeededHeight;
        }
    }
}
