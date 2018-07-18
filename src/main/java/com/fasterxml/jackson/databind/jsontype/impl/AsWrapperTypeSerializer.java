package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsWrapperTypeSerializer extends TypeSerializerBase {
    public AsWrapperTypeSerializer(TypeIdResolver idRes, BeanProperty property) {
        super(idRes, property);
    }

    public AsWrapperTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsWrapperTypeSerializer(this._idResolver, prop);
    }

    public void writeTypePrefixForObject(Object value, JsonGenerator jgen) throws IOException {
        String typeId = idFromValue(value);
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            jgen.writeStartObject();
            return;
        }
        jgen.writeStartObject();
        jgen.writeObjectFieldStart(typeId);
    }

    public void writeTypePrefixForArray(Object value, JsonGenerator jgen) throws IOException {
        String typeId = idFromValue(value);
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            jgen.writeStartObject();
            return;
        }
        jgen.writeStartObject();
        jgen.writeArrayFieldStart(typeId);
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator jgen) throws IOException {
        String typeId = idFromValue(value);
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            return;
        }
        jgen.writeStartObject();
        jgen.writeFieldName(typeId);
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator jgen, Class<?> type) throws IOException {
        String typeId = idFromValueAndType(value, type);
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            return;
        }
        jgen.writeStartObject();
        jgen.writeFieldName(typeId);
    }

    public void writeTypeSuffixForObject(Object value, JsonGenerator jgen) throws IOException {
        jgen.writeEndObject();
        if (!jgen.canWriteTypeId()) {
            jgen.writeEndObject();
        }
    }

    public void writeTypeSuffixForArray(Object value, JsonGenerator jgen) throws IOException {
        jgen.writeEndArray();
        if (!jgen.canWriteTypeId()) {
            jgen.writeEndObject();
        }
    }

    public void writeTypeSuffixForScalar(Object value, JsonGenerator jgen) throws IOException {
        if (!jgen.canWriteTypeId()) {
            jgen.writeEndObject();
        }
    }

    public void writeCustomTypePrefixForObject(Object value, JsonGenerator jgen, String typeId) throws IOException {
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            jgen.writeStartObject();
            return;
        }
        jgen.writeStartObject();
        jgen.writeObjectFieldStart(typeId);
    }

    public void writeCustomTypeSuffixForObject(Object value, JsonGenerator jgen, String typeId) throws IOException {
        if (!jgen.canWriteTypeId()) {
            writeTypeSuffixForObject(value, jgen);
        }
    }
}
