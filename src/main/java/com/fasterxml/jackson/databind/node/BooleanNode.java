package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class BooleanNode extends ValueNode {
    public static final BooleanNode FALSE = new BooleanNode(false);
    public static final BooleanNode TRUE = new BooleanNode(true);
    private final boolean _value;

    private BooleanNode(boolean v) {
        this._value = v;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public String asText() {
        return this._value ? "true" : "false";
    }

    public int asInt(int defaultValue) {
        return this._value ? 1 : 0;
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeBoolean(this._value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof BooleanNode)) {
            return false;
        }
        if (this._value != ((BooleanNode) o)._value) {
            return false;
        }
        return true;
    }
}
