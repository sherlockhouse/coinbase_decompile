package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class LongNode extends NumericNode {
    protected final long _value;

    public LongNode(long v) {
        this._value = v;
    }

    public static LongNode valueOf(long l) {
        return new LongNode(l);
    }

    public int intValue() {
        return (int) this._value;
    }

    public long longValue() {
        return this._value;
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
        if (!(o instanceof LongNode)) {
            return false;
        }
        if (((LongNode) o)._value != this._value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((int) this._value) ^ ((int) (this._value >> 32));
    }
}
