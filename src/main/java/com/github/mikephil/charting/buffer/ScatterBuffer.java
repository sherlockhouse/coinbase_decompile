package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.Entry;
import java.util.List;

public class ScatterBuffer extends AbstractBuffer<Entry> {
    public ScatterBuffer(int size) {
        super(size);
    }

    protected void addForm(float x, float y) {
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
        float size = ((float) entries.size()) * this.phaseX;
        for (int i = 0; ((float) i) < size; i++) {
            Entry e = (Entry) entries.get(i);
            addForm((float) e.getXIndex(), e.getVal() * this.phaseY);
        }
        reset();
    }
}
