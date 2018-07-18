package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.CandleEntry;
import java.util.List;

public class CandleBodyBuffer extends AbstractBuffer<CandleEntry> {
    private float mBodySpace = 0.0f;

    public CandleBodyBuffer(int size) {
        super(size);
    }

    public void setBodySpace(float bodySpace) {
        this.mBodySpace = bodySpace;
    }

    private void addBody(float left, float top, float right, float bottom) {
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

    public void feed(List<CandleEntry> entries) {
        int size = (int) Math.ceil((double) ((((float) (this.mTo - this.mFrom)) * this.phaseX) + ((float) this.mFrom)));
        for (int i = this.mFrom; i < size; i++) {
            CandleEntry e = (CandleEntry) entries.get(i);
            addBody((((float) e.getXIndex()) - 0.5f) + this.mBodySpace, e.getClose() * this.phaseY, (((float) e.getXIndex()) + 0.5f) - this.mBodySpace, e.getOpen() * this.phaseY);
        }
        reset();
    }
}
