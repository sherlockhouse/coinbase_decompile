package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.Entry;
import java.util.List;

public class LineBuffer extends AbstractBuffer<Entry> {
    public LineBuffer(int size) {
        if (size < 4) {
            size = 4;
        }
        super(size);
    }

    public void moveTo(float x, float y) {
        if (this.index == 0) {
            float[] fArr = this.buffer;
            int i = this.index;
            this.index = i + 1;
            fArr[i] = x;
            fArr = this.buffer;
            i = this.index;
            this.index = i + 1;
            fArr[i] = y;
            this.buffer[this.index] = x;
            this.buffer[this.index + 1] = y;
        }
    }

    public void lineTo(float x, float y) {
        if (this.index == 2) {
            float[] fArr = this.buffer;
            int i = this.index;
            this.index = i + 1;
            fArr[i] = x;
            fArr = this.buffer;
            i = this.index;
            this.index = i + 1;
            fArr[i] = y;
            return;
        }
        float prevX = this.buffer[this.index - 2];
        float prevY = this.buffer[this.index - 1];
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = prevX;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = prevY;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = x;
        fArr = this.buffer;
        i = this.index;
        this.index = i + 1;
        fArr[i] = y;
    }

    public void feed(List<Entry> entries) {
        moveTo((float) ((Entry) entries.get(this.mFrom)).getXIndex(), ((Entry) entries.get(this.mFrom)).getVal() * this.phaseY);
        int size = (int) Math.ceil((double) ((((float) (this.mTo - this.mFrom)) * this.phaseX) + ((float) this.mFrom)));
        for (int i = this.mFrom + 1; i < size; i++) {
            Entry e = (Entry) entries.get(i);
            lineTo((float) e.getXIndex(), e.getVal() * this.phaseY);
        }
        reset();
    }
}
