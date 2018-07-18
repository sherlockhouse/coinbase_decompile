package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class LocalTimeDeserializer extends JodaDeserializerBase<LocalTime> {
    static final DateTimeFormatter parser = ISODateTimeFormat.localTimeParser();

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public LocalTimeDeserializer() {
        super(LocalTime.class);
    }

    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (jp.getCurrentToken()) {
            case START_ARRAY:
                if (jp.isExpectedStartArrayToken()) {
                    jp.nextToken();
                    int hour = jp.getIntValue();
                    jp.nextToken();
                    int minute = jp.getIntValue();
                    jp.nextToken();
                    int second = jp.getIntValue();
                    jp.nextToken();
                    int millis = 0;
                    if (jp.getCurrentToken() != JsonToken.END_ARRAY) {
                        millis = jp.getIntValue();
                        jp.nextToken();
                    }
                    if (jp.getCurrentToken() == JsonToken.END_ARRAY) {
                        return new LocalTime(hour, minute, second, millis);
                    }
                    throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after LocalTime ints");
                }
                break;
            case VALUE_NUMBER_INT:
                return new LocalTime(jp.getLongValue());
            case VALUE_STRING:
                String str = jp.getText().trim();
                if (str.length() == 0) {
                    return null;
                }
                return parser.parseLocalTime(str);
        }
        throw ctxt.wrongTokenException(jp, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
    }
}
