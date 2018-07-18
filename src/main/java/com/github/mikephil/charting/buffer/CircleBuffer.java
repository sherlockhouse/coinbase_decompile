package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.Entry;
import java.util.List;

public class CircleBuffer extends AbstractBuffer<Entry> {
    public CircleBuffer(int size) {
        super(size);
    }

    protected void addCircle(float x, float y) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = x;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = y;
    }

    public void feed(List<Entry> entries) {
        int size = (int) Math.ceil((double) ((((float) (this.mTo - this.mFrom)) * this.phaseX) + ((float) this.mFrom)));
        for (int i = this.mFrom; i < size; i++) {
            Entry e = (Entry) entries.get(i);
            addCircle((float) e.getXIndex(), e.getVal() * this.phaseY);
        }
        reset();
    }
}
