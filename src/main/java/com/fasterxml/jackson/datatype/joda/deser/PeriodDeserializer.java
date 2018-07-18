package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.Period;

public class PeriodDeserializer extends JodaDeserializerBase<Period> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public PeriodDeserializer() {
        super(Period.class);
    }

    public Period deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (jp.getCurrentToken()) {
            case VALUE_NUMBER_INT:
                return new Period(jp.getLongValue());
            case VALUE_STRING:
                return new Period(jp.getText());
            default:
                throw ctxt.wrongTokenException(jp, JsonToken.START_ARRAY, "expected JSON Number or String");
        }
    }
}
