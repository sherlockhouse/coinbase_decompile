package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeBase extends JavaType implements JsonSerializable {
    volatile transient String _canonicalName;

    protected abstract String buildCanonicalName();

    protected TypeBase(Class<?> raw, int hash, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(raw, hash, valueHandler, typeHandler, asStatic);
    }

    public String toCanonical() {
        String str = this._canonicalName;
        if (str == null) {
            return buildCanonicalName();
        }
        return str;
    }

    public <T> T getValueHandler() {
        return this._valueHandler;
    }

    public <T> T getTypeHandler() {
        return this._typeHandler;
    }

    public void serializeWithType(JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        typeSer.writeTypePrefixForScalar(this, jgen);
        serialize(jgen, provider);
        typeSer.writeTypeSuffixForScalar(this, jgen);
    }

    public void serialize(JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(toCanonical());
    }
}
