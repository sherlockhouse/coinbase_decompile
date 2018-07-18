package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class IntNode extends NumericNode {
    private static final IntNode[] CANONICALS = new IntNode[12];
    protected final int _value;

    static {
        for (int i = 0; i < 12; i++) {
            CANONICALS[i] = new IntNode(i - 1);
        }
    }

    public IntNode(int v) {
        this._value = v;
    }

    public static IntNode valueOf(int i) {
        if (i > 10 || i < -1) {
            return new IntNode(i);
        }
        return CANONICALS[i + 1];
    }

    public int intValue() {
        return this._value;
    }

    public long longValue() {
        return (long) this._value;
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public String asText() {
        return NumberOutput.toString(this._value);
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNumber(this._value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof IntNode)) {
            return false;
        }
        if (((IntNode) o)._value != this._value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this._value;
    }
}
