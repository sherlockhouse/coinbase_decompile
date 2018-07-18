package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigInteger;

public class BigIntegerNode extends NumericNode {
    private static final BigInteger MAX_INTEGER = BigInteger.valueOf(2147483647L);
    private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger MIN_INTEGER = BigInteger.valueOf(-2147483648L);
    private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected final BigInteger _value;

    public BigIntegerNode(BigInteger v) {
        this._value = v;
    }

    public static BigIntegerNode valueOf(BigInteger v) {
        return new BigIntegerNode(v);
    }

    public int intValue() {
        return this._value.intValue();
    }

    public long longValue() {
        return this._value.longValue();
    }

    public double doubleValue() {
        return this._value.doubleValue();
    }

    public String asText() {
        return this._value.toString();
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNumber(this._value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof BigIntegerNode)) {
            return false;
        }
        return ((BigIntegerNode) o)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }
}
