package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.LocalDateTime;

public final class LocalDateTimeSerializer extends JodaDateSerializerBase<LocalDateTime> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(DEFAULT_LOCAL_DATETIME_FORMAT);

    public LocalDateTimeSerializer() {
        this(DEFAULT_FORMAT);
    }

    public LocalDateTimeSerializer(JacksonJodaFormat format) {
        super(LocalDateTime.class, format);
    }

    public LocalDateTimeSerializer withFormat(JacksonJodaFormat formatter) {
        if (this._format == formatter) {
            return this;
        }
        this(formatter);
        return this;
    }

    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (_useTimestamp(provider)) {
            jgen.writeStartArray();
            jgen.writeNumber(value.year().get());
            jgen.writeNumber(value.monthOfYear().get());
            jgen.writeNumber(value.dayOfMonth().get());
            jgen.writeNumber(value.hourOfDay().get());
            jgen.writeNumber(value.minuteOfHour().get());
            jgen.writeNumber(value.secondOfMinute().get());
            jgen.writeNumber(value.millisOfSecond().get());
            jgen.writeEndArray();
            return;
        }
        jgen.writeString(this._format.createFormatter(provider).print(value));
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode(_useTimestamp(provider) ? "array" : "string", true);
    }
}
