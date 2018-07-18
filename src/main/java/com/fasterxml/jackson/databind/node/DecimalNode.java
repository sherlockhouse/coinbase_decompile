package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;

public class DecimalNode extends NumericNode {
    private static final BigDecimal MAX_INTEGER = BigDecimal.valueOf(2147483647L);
    private static final BigDecimal MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
    private static final BigDecimal MIN_INTEGER = BigDecimal.valueOf(-2147483648L);
    private static final BigDecimal MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
    public static final DecimalNode ZERO = new DecimalNode(BigDecimal.ZERO);
    protected final BigDecimal _value;

    public DecimalNode(BigDecimal v) {
        this._value = v;
    }

    public static DecimalNode valueOf(BigDecimal d) {
        return new DecimalNode(d);
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

    public final void serialize(JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeNumber(this._value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof DecimalNode)) {
            return false;
        }
        if (((DecimalNode) o)._value.compareTo(this._value) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Double.valueOf(doubleValue()).hashCode();
    }
}
