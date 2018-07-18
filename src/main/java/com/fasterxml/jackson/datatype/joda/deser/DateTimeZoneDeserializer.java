package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.DateTimeZone;

public class DateTimeZoneDeserializer extends JodaDeserializerBase<DateTimeZone> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public DateTimeZoneDeserializer() {
        super(DateTimeZone.class);
    }

    public DateTimeZone deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return DateTimeZone.forOffsetHours(jp.getIntValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            return DateTimeZone.forID(jp.getText().trim());
        }
        throw ctxt.mappingException(DateTimeZone.class, JsonToken.VALUE_STRING);
    }
}
