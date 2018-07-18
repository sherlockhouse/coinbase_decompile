package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import java.util.List;

public class BarBuffer extends AbstractBuffer<BarEntry> {
    protected float mBarSpace = 0.0f;
    protected boolean mContainsStacks = false;
    protected int mDataSetCount = 1;
    protected int mDataSetIndex = 0;
    protected float mGroupSpace = 0.0f;
    protected boolean mInverted = false;

    public BarBuffer(int size, float groupspace, int dataSetCount, boolean containsStacks) {
        super(size);
        this.mGroupSpace = groupspace;
        this.mDataSetCount = dataSetCount;
        this.mContainsStacks = containsStacks;
    }

    public void setBarSpace(float barspace) {
        this.mBarSpace = barspace;
    }

    public void setDataSet(int index) {
        this.mDataSetIndex = index;
    }

    public void setInverted(boolean inverted) {
        this.mInverted = inverted;
    }

    protected void addBar(float left, float top, float right, float bottom) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = left;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = top;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = right;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = bottom;
    }

    public void feed(List<BarEntry> entries) {
        float size = ((float) entries.size()) * this.phaseX;
        int dataSetOffset = this.mDataSetCount - 1;
        float barSpaceHalf = this.mBarSpace / 2.0f;
        float groupSpaceHalf = this.mGroupSpace / 2.0f;
        for (int i = 0; ((float) i) < size; i++) {
            BarEntry e = (BarEntry) entries.get(i);
            float x = (((float) ((e.getXIndex() + (e.getXIndex() * dataSetOffset)) + this.mDataSetIndex)) + (this.mGroupSpace * ((float) e.getXIndex()))) + groupSpaceHalf;
            float y = e.getVal();
            float[] vals = e.getVals();
            float left;
            float right;
            float bottom;
            float top;
            if (!this.mContainsStacks || vals == null) {
                left = (x - 0.5f) + barSpaceHalf;
                right = (x + 0.5f) - barSpaceHalf;
                if (this.mInverted) {
                    bottom = y >= 0.0f ? y : 0.0f;
                    top = y <= 0.0f ? y : 0.0f;
                } else {
                    top = y >= 0.0f ? y : 0.0f;
                    bottom = y <= 0.0f ? y : 0.0f;
                }
                if (top > 0.0f) {
                    top *= this.phaseY;
                } else {
                    bottom *= this.phaseY;
                }
                addBar(left, top, right, bottom);
            } else {
                float posY = 0.0f;
                float negY = -e.getNegativeSum();
                for (float value : vals) {
                    float yStart;
                    if (value >= 0.0f) {
                        y = posY;
                        yStart = posY + value;
                        posY = yStart;
                    } else {
                        y = negY;
                        yStart = negY + Math.abs(value);
                        negY += Math.abs(value);
                    }
                    left = (x - 0.5f) + barSpaceHalf;
                    right = (x + 0.5f) - barSpaceHalf;
                    if (this.mInverted) {
                        if (y >= yStart) {
                            bottom = y;
                        } else {
                            bottom = yStart;
                        }
                        if (y <= yStart) {
                            top = y;
                        } else {
                            top = yStart;
                        }
                    } else {
                        if (y >= yStart) {
                            top = y;
                        } else {
                            top = yStart;
                        }
                        if (y <= yStart) {
                            bottom = y;
                        } else {
                            bottom = yStart;
                        }
                    }
                    addBar(left, top * this.phaseY, right, bottom * this.phaseY);
                }
            }
        }
        reset();
    }
}
