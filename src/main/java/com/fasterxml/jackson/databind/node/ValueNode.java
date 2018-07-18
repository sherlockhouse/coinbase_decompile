package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class ValueNode extends BaseJsonNode {
    protected ValueNode() {
    }

    public void serializeWithType(JsonGenerator jg, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        typeSer.writeTypePrefixForScalar(this, jg);
        serialize(jg, provider);
        typeSer.writeTypeSuffixForScalar(this, jg);
    }

    public String toString() {
        return asText();
    }

    public final JsonNode get(String fieldName) {
        return null;
    }

    public final JsonNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    public final boolean has(String fieldName) {
        return false;
    }
}
