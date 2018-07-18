package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class LocalDateTimeDeserializer extends JodaDeserializerBase<LocalDateTime> {
    static final DateTimeFormatter parser = ISODateTimeFormat.localDateOptionalTimeParser();

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (jp.getCurrentToken()) {
            case START_ARRAY:
                if (jp.isExpectedStartArrayToken()) {
                    jp.nextToken();
                    int year = jp.getIntValue();
                    jp.nextToken();
                    int month = jp.getIntValue();
                    jp.nextToken();
                    int day = jp.getIntValue();
                    jp.nextToken();
                    int hour = jp.getIntValue();
                    jp.nextToken();
                    int minute = jp.getIntValue();
                    jp.nextToken();
                    int second = jp.getIntValue();
                    jp.nextToken();
                    int millisecond = 0;
                    if (jp.getCurrentToken() != JsonToken.END_ARRAY) {
                        millisecond = jp.getIntValue();
                        jp.nextToken();
                    }
                    if (jp.getCurrentToken() == JsonToken.END_ARRAY) {
                        return new LocalDateTime(year, month, day, hour, minute, second, millisecond);
                    }
                    throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after LocalDateTime ints");
                }
                break;
            case VALUE_NUMBER_INT:
                return new LocalDateTime(jp.getLongValue());
            case VALUE_STRING:
                String str = jp.getText().trim();
                if (str.length() == 0) {
                    return null;
                }
                return parser.parseLocalDateTime(str);
        }
        throw ctxt.wrongTokenException(jp, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
    }
}
