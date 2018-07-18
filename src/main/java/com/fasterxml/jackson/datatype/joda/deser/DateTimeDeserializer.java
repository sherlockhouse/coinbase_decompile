package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

public class DateTimeDeserializer extends JodaDeserializerBase<ReadableInstant> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public DateTimeDeserializer(Class<? extends ReadableInstant> cls) {
        super(cls);
    }

    public static <T extends ReadableInstant> JsonDeserializer<T> forType(Class<T> cls) {
        return new DateTimeDeserializer(cls);
    }

    public ReadableDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        TimeZone tz = ctxt.getTimeZone();
        DateTimeZone dtz = tz == null ? DateTimeZone.UTC : DateTimeZone.forTimeZone(tz);
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new DateTime(jp.getLongValue(), dtz);
        }
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            if (str.length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)) {
                return new DateTime(str, dtz);
            }
            return DateTime.parse(str);
        }
        throw ctxt.mappingException(getValueClass());
    }
}
