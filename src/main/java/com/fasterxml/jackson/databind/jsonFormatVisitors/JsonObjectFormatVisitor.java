package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface JsonObjectFormatVisitor extends JsonFormatVisitorWithSerializerProvider {
    void optionalProperty(BeanProperty beanProperty) throws JsonMappingException;

    void property(BeanProperty beanProperty) throws JsonMappingException;

    void property(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;
}
