package com.fasterxml.jackson.databind.node;

public abstract class NumericNode extends ValueNode {
    public abstract int intValue();

    protected NumericNode() {
    }

    public final int asInt() {
        return intValue();
    }

    public final int asInt(int defaultValue) {
        return intValue();
    }
}
