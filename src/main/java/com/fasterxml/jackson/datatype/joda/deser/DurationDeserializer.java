package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import org.joda.time.Duration;

public final class DurationDeserializer extends StdScalarDeserializer<Duration> {
    public DurationDeserializer() {
        super(Duration.class);
    }

    public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case VALUE_NUMBER_INT:
                return new Duration(jsonParser.getLongValue());
            case VALUE_STRING:
                return new Duration(jsonParser.getText());
            default:
                throw deserializationContext.mappingException("expected JSON Number or String");
        }
    }
}
