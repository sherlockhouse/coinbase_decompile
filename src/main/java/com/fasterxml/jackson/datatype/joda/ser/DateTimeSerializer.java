package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTimeSerializer extends JodaDateSerializerBase<DateTime> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(ISODateTimeFormat.dateTime().withZoneUTC());

    public DateTimeSerializer() {
        this(DEFAULT_FORMAT);
    }

    public DateTimeSerializer(JacksonJodaFormat format) {
        super(DateTime.class, format);
    }

    public DateTimeSerializer withFormat(JacksonJodaFormat formatter) {
        if (this._format == formatter) {
            return this;
        }
        this(formatter);
        return this;
    }

    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (_useTimestamp(provider)) {
            jgen.writeNumber(value.getMillis());
        } else {
            jgen.writeString(this._format.createFormatter(provider).print(value));
        }
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode(_useTimestamp(provider) ? "number" : "string", true);
    }
}
