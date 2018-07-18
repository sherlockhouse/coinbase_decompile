package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.Instant;

public class InstantDeserializer extends JodaDeserializerBase<Instant> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public InstantDeserializer() {
        super(Instant.class);
    }

    public Instant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new Instant(jp.getLongValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            if (str.length() == 0) {
                return null;
            }
            return new Instant(str);
        }
        throw ctxt.mappingException(Instant.class);
    }
}
