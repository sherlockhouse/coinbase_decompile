package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.Duration;

public final class DurationSerializer extends JodaDateSerializerBase<Duration> {
    protected static final JacksonJodaFormat DEFAULT_FORMAT = new JacksonJodaFormat(DEFAULT_DATEONLY_FORMAT);

    public DurationSerializer() {
        this(DEFAULT_FORMAT);
    }

    public DurationSerializer(JacksonJodaFormat formatter) {
        super(Duration.class, formatter);
    }

    public DurationSerializer withFormat(JacksonJodaFormat formatter) {
        if (this._format == formatter) {
            return this;
        }
        this(formatter);
        return this;
    }

    public void serialize(Duration value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
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
