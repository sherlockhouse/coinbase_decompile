package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.LocalDate;

public final class LocalDateSerializer extends JodaDateSerializerBase<LocalDate> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(DEFAULT_DATEONLY_FORMAT);

    public LocalDateSerializer() {
        this(DEFAULT_FORMAT);
    }

    public LocalDateSerializer(JacksonJodaFormat format) {
        super(LocalDate.class, format);
    }

    public LocalDateSerializer withFormat(JacksonJodaFormat formatter) {
        if (this._format == formatter) {
            return this;
        }
        this(formatter);
        return this;
    }

    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (_useTimestamp(provider)) {
            jgen.writeStartArray();
            jgen.writeNumber(value.year().get());
            jgen.writeNumber(value.monthOfYear().get());
            jgen.writeNumber(value.dayOfMonth().get());
            jgen.writeEndArray();
            return;
        }
        jgen.writeString(this._format.createFormatter(provider).print(value));
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode(_useTimestamp(provider) ? "array" : "string", true);
    }
}
