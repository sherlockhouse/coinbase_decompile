package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer instance = new NullifyingDeserializer();

    public NullifyingDeserializer() {
        super(Object.class);
    }

    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        jp.skipChildren();
        return null;
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        switch (jp.getCurrentToken()) {
            case START_ARRAY:
            case START_OBJECT:
            case FIELD_NAME:
                return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
            default:
                return null;
        }
    }
}
