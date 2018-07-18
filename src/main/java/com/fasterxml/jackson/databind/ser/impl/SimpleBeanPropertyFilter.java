package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter, PropertyFilter {
    protected abstract boolean include(BeanPropertyWriter beanPropertyWriter);

    protected abstract boolean include(PropertyWriter propertyWriter);

    public static PropertyFilter from(final BeanPropertyFilter src) {
        return new PropertyFilter() {
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
                src.serializeAsField(pojo, jgen, prov, (BeanPropertyWriter) writer);
            }

            public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
                src.depositSchemaProperty((BeanPropertyWriter) writer, propertiesNode, provider);
            }

            public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
                src.depositSchemaProperty((BeanPropertyWriter) writer, objectVisitor, provider);
            }
        };
    }

    @Deprecated
    public void serializeAsField(Object bean, JsonGenerator jgen, SerializerProvider provider, BeanPropertyWriter writer) throws Exception {
        if (include(writer)) {
            writer.serializeAsField(bean, jgen, provider);
        } else if (!jgen.canOmitFields()) {
            writer.serializeAsOmittedField(bean, jgen, provider);
        }
    }

    @Deprecated
    public void depositSchemaProperty(BeanPropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
        if (include(writer)) {
            writer.depositSchemaProperty(propertiesNode, provider);
        }
    }

    @Deprecated
    public void depositSchemaProperty(BeanPropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
        if (include(writer)) {
            writer.depositSchemaProperty(objectVisitor);
        }
    }

    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if (include(writer)) {
            writer.serializeAsField(pojo, jgen, provider);
        } else if (!jgen.canOmitFields()) {
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }
    }

    @Deprecated
    public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
        if (include(writer)) {
            writer.depositSchemaProperty(propertiesNode, provider);
        }
    }

    public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
        if (include(writer)) {
            writer.depositSchemaProperty(objectVisitor);
        }
    }
}
