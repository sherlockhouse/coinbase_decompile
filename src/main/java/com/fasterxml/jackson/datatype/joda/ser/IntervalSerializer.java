package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.Interval;

public class IntervalSerializer extends JodaSerializerBase<Interval> {
    public IntervalSerializer() {
        super(Interval.class);
    }

    public void serialize(Interval interval, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeString(interval.getStartMillis() + "-" + interval.getEndMillis());
    }
}
