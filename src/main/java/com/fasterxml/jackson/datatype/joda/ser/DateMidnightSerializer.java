package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.DateMidnight;

public final class DateMidnightSerializer extends JodaDateSerializerBase<DateMidnight> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(DEFAULT_DATEONLY_FORMAT);

    public DateMidnightSerializer() {
        this(DEFAULT_FORMAT);
    }

    public DateMidnightSerializer(JacksonJodaFormat format) {
        super(DateMidnight.class, format);
    }

    public DateMidnightSerializer withFormat(JacksonJodaFormat formatter) {
        return this._format == formatter ? this : new DateMidnightSerializer(this._format);
    }

    public void serialize(DateMidnight value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
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
