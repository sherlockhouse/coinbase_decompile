package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

final class BarcodeMatrix {
    private int currentRow;
    private final int height;
    private final BarcodeRow[] matrix;
    private final int width;

    BarcodeMatrix(int height, int width) {
        this.matrix = new BarcodeRow[(height + 2)];
        int matrixLength = this.matrix.length;
        for (int i = 0; i < matrixLength; i++) {
            this.matrix[i] = new BarcodeRow(((width + 4) * 17) + 1);
        }
        this.width = width * 17;
        this.height = height + 2;
        this.currentRow = 0;
    }

    void startRow() {
        this.currentRow++;
    }

    BarcodeRow getCurrentRow() {
        return this.matrix[this.currentRow];
    }

    byte[][] getScaledMatrix(int xScale, int yScale) {
        byte[][] matrixOut = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.height * yScale, this.width * xScale});
        int yMax = this.height * yScale;
        for (int ii = 0; ii < yMax; ii++) {
            matrixOut[(yMax - ii) - 1] = this.matrix[ii / yScale].getScaledRow(xScale);
        }
        return matrixOut;
    }
}
