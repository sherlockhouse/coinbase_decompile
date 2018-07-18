package com.fasterxml.jackson.databind.node;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory implements Serializable {
    private static final JsonNodeFactory decimalsAsIs = new JsonNodeFactory(true);
    private static final JsonNodeFactory decimalsNormalized = new JsonNodeFactory(false);
    public static final JsonNodeFactory instance = decimalsNormalized;
    private final boolean _cfgBigDecimalExact;

    public JsonNodeFactory(boolean bigDecimalExact) {
        this._cfgBigDecimalExact = bigDecimalExact;
    }

    protected JsonNodeFactory() {
        this(false);
    }

    public BooleanNode booleanNode(boolean v) {
        return v ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(int v) {
        return IntNode.valueOf(v);
    }

    public NumericNode numberNode(long v) {
        if (_inIntRange(v)) {
            return IntNode.valueOf((int) v);
        }
        return LongNode.valueOf(v);
    }

    public NumericNode numberNode(BigInteger v) {
        return BigIntegerNode.valueOf(v);
    }

    public NumericNode numberNode(double v) {
        return DoubleNode.valueOf(v);
    }

    public NumericNode numberNode(BigDecimal v) {
        if (this._cfgBigDecimalExact) {
            return DecimalNode.valueOf(v);
        }
        return v.compareTo(BigDecimal.ZERO) == 0 ? DecimalNode.ZERO : DecimalNode.valueOf(v.stripTrailingZeros());
    }

    public TextNode textNode(String text) {
        return TextNode.valueOf(text);
    }

    public BinaryNode binaryNode(byte[] data) {
        return BinaryNode.valueOf(data);
    }

    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    public ValueNode pojoNode(Object pojo) {
        return new POJONode(pojo);
    }

    protected boolean _inIntRange(long l) {
        return ((long) ((int) l)) == l;
    }
}
