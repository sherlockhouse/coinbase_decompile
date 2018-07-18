package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.Instant;

public final class InstantSerializer extends JodaDateSerializerBase<Instant> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(DEFAULT_DATEONLY_FORMAT);

    public InstantSerializer() {
        this(DEFAULT_FORMAT);
    }

    public InstantSerializer(JacksonJodaFormat format) {
        super(Instant.class, format);
    }

    public InstantSerializer withFormat(JacksonJodaFormat formatter) {
        if (this._format == formatter) {
            return this;
        }
        this(formatter);
        return this;
    }

    public void serialize(Instant value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        if (_useTimestamp(provider)) {
            jgen.writeNumber(value.getMillis());
        } else {
            jgen.writeString(value.toString());
        }
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode(_useTimestamp(provider) ? "number" : "string", true);
    }
}
