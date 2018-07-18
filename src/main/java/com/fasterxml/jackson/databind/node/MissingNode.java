package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class MissingNode extends ValueNode {
    private static final MissingNode instance = new MissingNode();

    private MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    public String asText() {
        return "";
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeNull();
    }

    public void serializeWithType(JsonGenerator jg, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        jg.writeNull();
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public String toString() {
        return "";
    }
}
