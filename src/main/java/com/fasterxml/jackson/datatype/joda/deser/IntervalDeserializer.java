package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.Interval;

public class IntervalDeserializer extends JodaDeserializerBase<Interval> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public IntervalDeserializer() {
        super(Interval.class);
    }

    public Interval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String v = jsonParser.getText();
            int dashIndex = v.indexOf(45);
            return new Interval(Long.valueOf(v.substring(0, dashIndex)).longValue(), Long.valueOf(v.substring(dashIndex + 1)).longValue());
        }
        throw deserializationContext.mappingException("expected JSON String");
    }
}
