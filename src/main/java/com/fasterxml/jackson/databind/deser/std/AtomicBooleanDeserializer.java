package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    public AtomicBoolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return new AtomicBoolean(_parseBooleanPrimitive(jp, ctxt));
    }
}
