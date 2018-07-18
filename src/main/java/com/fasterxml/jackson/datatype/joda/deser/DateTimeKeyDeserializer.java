package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeKeyDeserializer extends KeyDeserializer {
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (key.length() == 0) {
            return null;
        }
        return new DateTime(key, DateTimeZone.forTimeZone(ctxt.getTimeZone()));
    }
}
