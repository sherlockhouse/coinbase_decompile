package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import java.util.List;

public class BeanSerializerBuilder {
    private static final BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];
    protected AnyGetterWriter _anyGetter;
    protected final BeanDescription _beanDesc;
    protected SerializationConfig _config;
    protected Object _filterId;
    protected BeanPropertyWriter[] _filteredProperties;
    protected ObjectIdWriter _objectIdWriter;
    protected List<BeanPropertyWriter> _properties;
    protected AnnotatedMember _typeId;

    public BeanSerializerBuilder(BeanDescription beanDesc) {
        this._beanDesc = beanDesc;
    }

    protected void setConfig(SerializationConfig config) {
        this._config = config;
    }

    public void setProperties(List<BeanPropertyWriter> properties) {
        this._properties = properties;
    }

    public void setFilteredProperties(BeanPropertyWriter[] properties) {
        this._filteredProperties = properties;
    }

    public void setAnyGetter(AnyGetterWriter anyGetter) {
        this._anyGetter = anyGetter;
    }

    public void setFilterId(Object filterId) {
        this._filterId = filterId;
    }

    public void setTypeId(AnnotatedMember idProp) {
        if (this._typeId != null) {
            throw new IllegalArgumentException("Multiple type ids specified with " + this._typeId + " and " + idProp);
        }
        this._typeId = idProp;
    }

    public void setObjectIdWriter(ObjectIdWriter w) {
        this._objectIdWriter = w;
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public List<BeanPropertyWriter> getProperties() {
        return this._properties;
    }

    public AnyGetterWriter getAnyGetter() {
        return this._anyGetter;
    }

    public Object getFilterId() {
        return this._filterId;
    }

    public AnnotatedMember getTypeId() {
        return this._typeId;
    }

    public ObjectIdWriter getObjectIdWriter() {
        return this._objectIdWriter;
    }

    public JsonSerializer<?> build() {
        BeanPropertyWriter[] properties;
        if (this._properties != null && !this._properties.isEmpty()) {
            properties = (BeanPropertyWriter[]) this._properties.toArray(new BeanPropertyWriter[this._properties.size()]);
        } else if (this._anyGetter == null && this._objectIdWriter == null) {
            return null;
        } else {
            properties = NO_PROPERTIES;
        }
        return new BeanSerializer(this._beanDesc.getType(), this, properties, this._filteredProperties);
    }

    public BeanSerializer createDummy() {
        return BeanSerializer.createDummy(this._beanDesc.getType());
    }
}
