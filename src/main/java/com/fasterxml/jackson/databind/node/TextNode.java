package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    protected final String _value;

    public TextNode(String v) {
        this._value = v;
    }

    public static TextNode valueOf(String v) {
        if (v == null) {
            return null;
        }
        if (v.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(v);
    }

    public String textValue() {
        return this._value;
    }

    public String asText() {
        return this._value;
    }

    public int asInt(int defaultValue) {
        return NumberInput.parseAsInt(this._value, defaultValue);
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException {
        if (this._value == null) {
            jg.writeNull();
        } else {
            jg.writeString(this._value);
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof TextNode)) {
            return false;
        }
        return ((TextNode) o)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        int len = this._value.length();
        StringBuilder sb = new StringBuilder((len + 2) + (len >> 4));
        appendQuoted(sb, this._value);
        return sb.toString();
    }

    protected static void appendQuoted(StringBuilder sb, String content) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, content);
        sb.append('\"');
    }
}
