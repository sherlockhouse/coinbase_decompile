package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer {
    protected final String _typePropertyName;

    public AsPropertyTypeSerializer(TypeIdResolver idRes, BeanProperty property, String propName) {
        super(idRes, property);
        this._typePropertyName = propName;
    }

    public AsPropertyTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsPropertyTypeSerializer(this._idResolver, prop, this._typePropertyName);
    }

    public void writeTypePrefixForObject(Object value, JsonGenerator jgen) throws IOException {
        String typeId = idFromValue(value);
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            jgen.writeStartObject();
            return;
        }
        jgen.writeStartObject();
        jgen.writeStringField(this._typePropertyName, typeId);
    }

    public void writeTypeSuffixForObject(Object value, JsonGenerator jgen) throws IOException {
        jgen.writeEndObject();
    }

    public void writeCustomTypePrefixForObject(Object value, JsonGenerator jgen, String typeId) throws IOException {
        if (jgen.canWriteTypeId()) {
            jgen.writeTypeId(typeId);
            jgen.writeStartObject();
            return;
        }
        jgen.writeStartObject();
        jgen.writeStringField(this._typePropertyName, typeId);
    }

    public void writeCustomTypeSuffixForObject(Object value, JsonGenerator jgen, String typeId) throws IOException {
        jgen.writeEndObject();
    }
}
