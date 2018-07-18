package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class JodaDateSerializerBase<T> extends JodaSerializerBase<T> implements ContextualSerializer {
    protected static final DateTimeFormatter DEFAULT_DATEONLY_FORMAT = ISODateTimeFormat.date().withZoneUTC();
    protected static final DateTimeFormatter DEFAULT_LOCAL_DATETIME_FORMAT = ISODateTimeFormat.dateTime().withZoneUTC();
    protected static final DateTimeFormatter DEFAULT_TIMEONLY_FORMAT = ISODateTimeFormat.time().withZoneUTC();
    protected final JacksonJodaFormat _format;

    public abstract JodaDateSerializerBase<T> withFormat(JacksonJodaFormat jacksonJodaFormat);

    public /* bridge */ /* synthetic */ void serializeWithType(Object x0, JsonGenerator x1, SerializerProvider x2, TypeSerializer x3) throws IOException, JsonProcessingException {
        super.serializeWithType(x0, x1, x2, x3);
    }

    protected JodaDateSerializerBase(Class<T> type, JacksonJodaFormat format) {
        super(type);
        this._format = format;
    }

    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property == null) {
            return this;
        }
        Value ann = prov.getAnnotationIntrospector().findFormat(property.getMember());
        if (ann == null) {
            return this;
        }
        Boolean useTimestamp;
        JacksonJodaFormat format = this._format;
        if (ann.getShape().isNumeric()) {
            useTimestamp = Boolean.TRUE;
        } else if (ann.getShape() == Shape.STRING) {
            useTimestamp = Boolean.FALSE;
        } else {
            useTimestamp = null;
        }
        if (useTimestamp != null) {
            format = format.withUseTimestamp(useTimestamp);
        }
        format = format.withFormat(ann.getPattern().trim()).withLocale(ann.getLocale()).withTimeZone(ann.getTimeZone());
        if (format != this._format) {
            return withFormat(format);
        }
        return this;
    }

    protected boolean _useTimestamp(SerializerProvider provider) {
        return this._format.useTimestamp(provider);
    }
}
