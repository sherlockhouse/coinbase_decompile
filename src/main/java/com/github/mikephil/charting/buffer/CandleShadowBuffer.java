package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.CandleEntry;
import java.util.List;

public class CandleShadowBuffer extends AbstractBuffer<CandleEntry> {
    public CandleShadowBuffer(int size) {
        super(size);
    }

    private void addShadow(float x1, float y1, float x2, float y2) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = x1;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = y1;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = x2;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = y2;
    }

    public void feed(List<CandleEntry> entries) {
        int size = (int) Math.ceil((double) ((((float) (this.mTo - this.mFrom)) * this.phaseX) + ((float) this.mFrom)));
        for (int i = this.mFrom; i < size; i++) {
            CandleEntry e = (CandleEntry) entries.get(i);
            addShadow((float) e.getXIndex(), e.getHigh() * this.phaseY, (float) e.getXIndex(), e.getLow() * this.phaseY);
        }
        reset();
    }
}
