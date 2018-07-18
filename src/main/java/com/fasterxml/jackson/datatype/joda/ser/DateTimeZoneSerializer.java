package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.DateTimeZone;

public class DateTimeZoneSerializer extends JodaSerializerBase<DateTimeZone> {
    public DateTimeZoneSerializer() {
        super(DateTimeZone.class);
    }

    public void serialize(DateTimeZone value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.getID());
    }
}
