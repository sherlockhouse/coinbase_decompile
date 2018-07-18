package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;

public abstract class TypeDeserializer {
    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract Class<?> getDefaultImpl();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract As getTypeInclusion();

    public static Object deserializeIfNatural(JsonParser jp, DeserializationContext ctxt, JavaType baseType) throws IOException {
        return deserializeIfNatural(jp, ctxt, baseType.getRawClass());
    }

    public static Object deserializeIfNatural(JsonParser jp, DeserializationContext ctxt, Class<?> base) throws IOException {
        JsonToken t = jp.getCurrentToken();
        if (t == null) {
            return null;
        }
        switch (t) {
            case VALUE_STRING:
                if (base.isAssignableFrom(String.class)) {
                    return jp.getText();
                }
                return null;
            case VALUE_NUMBER_INT:
                if (base.isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jp.getIntValue());
                }
                return null;
            case VALUE_NUMBER_FLOAT:
                if (base.isAssignableFrom(Double.class)) {
                    return Double.valueOf(jp.getDoubleValue());
                }
                return null;
            case VALUE_TRUE:
                if (base.isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                return null;
            case VALUE_FALSE:
                if (base.isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
}
