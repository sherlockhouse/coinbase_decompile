package com.bluelinelabs.conductor.internal;

import android.os.Bundle;

public class TransactionIndexer {
    private int currentIndex;

    public int nextIndex() {
        int i = this.currentIndex + 1;
        this.currentIndex = i;
        return i;
    }

    public void saveInstanceState(Bundle outState) {
        outState.putInt("TransactionIndexer.currentIndex", this.currentIndex);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        this.currentIndex = savedInstanceState.getInt("TransactionIndexer.currentIndex");
    }
}
