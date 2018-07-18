package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    protected final byte[] _data;

    public BinaryNode(byte[] data) {
        this._data = data;
    }

    public static BinaryNode valueOf(byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length == 0) {
            return EMPTY_BINARY_NODE;
        }
        return new BinaryNode(data);
    }

    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeBinary(provider.getConfig().getBase64Variant(), this._data, 0, this._data.length);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof BinaryNode)) {
            return false;
        }
        return Arrays.equals(((BinaryNode) o)._data, this._data);
    }

    public int hashCode() {
        return this._data == null ? -1 : this._data.length;
    }

    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
