package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateMidnightDeserializer extends JodaDeserializerBase<DateMidnight> {
    static final DateTimeFormatter parser = ISODateTimeFormat.localDateParser();

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public DateMidnightDeserializer() {
        super(DateMidnight.class);
    }

    public DateMidnight deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.isExpectedStartArrayToken()) {
            jp.nextToken();
            int year = jp.getIntValue();
            jp.nextToken();
            int month = jp.getIntValue();
            jp.nextToken();
            int day = jp.getIntValue();
            if (jp.nextToken() == JsonToken.END_ARRAY) {
                return new DateMidnight(year, month, day);
            }
            throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after DateMidnight ints");
        }
        switch (jp.getCurrentToken()) {
            case VALUE_NUMBER_INT:
                return new DateMidnight(jp.getLongValue());
            case VALUE_STRING:
                String str = jp.getText().trim();
                if (str.length() == 0) {
                    return null;
                }
                LocalDate local = parser.parseLocalDate(str);
                if (local != null) {
                    return local.toDateMidnight();
                }
                return null;
            default:
                throw ctxt.wrongTokenException(jp, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
        }
    }
}
