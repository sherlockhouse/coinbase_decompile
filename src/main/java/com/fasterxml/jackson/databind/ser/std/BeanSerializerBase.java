package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements JsonFormatVisitable, SchemaAware, ContextualSerializer, ResolvableSerializer {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    protected abstract BeanSerializerBase asArraySerializer();

    protected abstract BeanSerializerBase withFilterId(Object obj);

    protected abstract BeanSerializerBase withIgnorals(String[] strArr);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    protected BeanSerializerBase(JavaType type, BeanSerializerBuilder builder, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        Shape shape = null;
        super(type);
        this._props = properties;
        this._filteredProps = filteredProperties;
        if (builder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = builder.getTypeId();
        this._anyGetterWriter = builder.getAnyGetter();
        this._propertyFilterId = builder.getFilterId();
        this._objectIdWriter = builder.getObjectIdWriter();
        Value format = builder.getBeanDescription().findExpectedFormat(null);
        if (format != null) {
            shape = format.getShape();
        }
        this._serializationShape = shape;
    }

    public BeanSerializerBase(BeanSerializerBase src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        super(src._handledType);
        this._props = properties;
        this._filteredProps = filteredProperties;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter) {
        this(src, objectIdWriter, src._propertyFilterId);
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter, Object filterId) {
        super(src._handledType);
        this._props = src._props;
        this._filteredProps = src._filteredProps;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = filterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, String[] toIgnore) {
        BeanPropertyWriter[] beanPropertyWriterArr = null;
        super(src._handledType);
        HashSet<String> ignoredSet = ArrayBuilders.arrayToSet(toIgnore);
        BeanPropertyWriter[] propsIn = src._props;
        BeanPropertyWriter[] fpropsIn = src._filteredProps;
        int len = propsIn.length;
        ArrayList<BeanPropertyWriter> propsOut = new ArrayList(len);
        ArrayList<BeanPropertyWriter> fpropsOut = fpropsIn == null ? null : new ArrayList(len);
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter bpw = propsIn[i];
            if (!ignoredSet.contains(bpw.getName())) {
                propsOut.add(bpw);
                if (fpropsIn != null) {
                    fpropsOut.add(fpropsIn[i]);
                }
            }
        }
        this._props = (BeanPropertyWriter[]) propsOut.toArray(new BeanPropertyWriter[propsOut.size()]);
        if (fpropsOut != null) {
            beanPropertyWriterArr = (BeanPropertyWriter[]) fpropsOut.toArray(new BeanPropertyWriter[fpropsOut.size()]);
        }
        this._filteredProps = beanPropertyWriterArr;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, NameTransformer unwrapper) {
        this(src, rename(src._props, unwrapper), rename(src._filteredProps, unwrapper));
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] props, NameTransformer transformer) {
        if (props == null || props.length == 0 || transformer == null || transformer == NameTransformer.NOP) {
            return props;
        }
        int len = props.length;
        BeanPropertyWriter[] result = new BeanPropertyWriter[len];
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter bpw = props[i];
            if (bpw != null) {
                result[i] = bpw.rename(transformer);
            }
        }
        return result;
    }

    public void resolve(SerializerProvider provider) throws JsonMappingException {
        int filteredCount;
        if (this._filteredProps == null) {
            filteredCount = 0;
        } else {
            filteredCount = this._filteredProps.length;
        }
        int len = this._props.length;
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter w2;
            BeanProperty prop = this._props[i];
            if (!(prop.willSuppressNulls() || prop.hasNullSerializer())) {
                JsonSerializer<Object> nullSer = provider.findNullValueSerializer(prop);
                if (nullSer != null) {
                    prop.assignNullSerializer(nullSer);
                    if (i < filteredCount) {
                        w2 = this._filteredProps[i];
                        if (w2 != null) {
                            w2.assignNullSerializer(nullSer);
                        }
                    }
                }
            }
            if (!prop.hasSerializer()) {
                JsonSerializer<Object> ser = findConvertingSerializer(provider, prop);
                if (ser == null) {
                    JavaType type = prop.getSerializationType();
                    if (type == null) {
                        type = provider.constructType(prop.getGenericPropertyType());
                        if (!type.isFinal()) {
                            if (type.isContainerType() || type.containedTypeCount() > 0) {
                                prop.setNonTrivialBaseType(type);
                            }
                        }
                    }
                    ser = provider.findValueSerializer(type, prop);
                    if (type.isContainerType()) {
                        TypeSerializer typeSer = (TypeSerializer) type.getContentType().getTypeHandler();
                        if (typeSer != null && (ser instanceof ContainerSerializer)) {
                            ser = ((ContainerSerializer) ser).withValueTypeSerializer(typeSer);
                        }
                    }
                }
                prop.assignSerializer(ser);
                if (i < filteredCount) {
                    w2 = this._filteredProps[i];
                    if (w2 != null) {
                        w2.assignSerializer(ser);
                    }
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(provider);
        }
    }

    protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider provider, BeanPropertyWriter prop) throws JsonMappingException {
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if (intr != null) {
            Object convDef = intr.findSerializationConverter(prop.getMember());
            if (convDef != null) {
                Converter<Object, Object> conv = provider.converterInstance(prop.getMember(), convDef);
                JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
                return new StdDelegatingSerializer(conv, delegateType, provider.findValueSerializer(delegateType, (BeanProperty) prop));
            }
        }
        return null;
    }

    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        ObjectIdWriter oiw = this._objectIdWriter;
        String[] ignorals = null;
        Object newFilterId = null;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember accessor = (property == null || intr == null) ? null : property.getMember();
        if (accessor != null) {
            ignorals = intr.findPropertiesToIgnore(accessor);
            ObjectIdInfo objectIdInfo = intr.findObjectIdInfo(accessor);
            if (objectIdInfo != null) {
                objectIdInfo = intr.findObjectReferenceInfo(accessor, objectIdInfo);
                Class<?> implClass = objectIdInfo.getGeneratorType();
                JavaType idType = provider.getTypeFactory().findTypeParameters(provider.constructType(implClass), ObjectIdGenerator.class)[0];
                if (implClass == PropertyGenerator.class) {
                    String propName = objectIdInfo.getPropertyName().getSimpleName();
                    int i = 0;
                    int len = this._props.length;
                    while (i != len) {
                        BeanPropertyWriter prop = this._props[i];
                        if (propName.equals(prop.getName())) {
                            BeanPropertyWriter idProp = prop;
                            if (i > 0) {
                                System.arraycopy(this._props, 0, this._props, 1, i);
                                this._props[0] = idProp;
                                if (this._filteredProps != null) {
                                    BeanPropertyWriter fp = this._filteredProps[i];
                                    System.arraycopy(this._filteredProps, 0, this._filteredProps, 1, i);
                                    this._filteredProps[0] = fp;
                                }
                            }
                            oiw = ObjectIdWriter.construct(idProp.getType(), (PropertyName) null, new PropertyBasedObjectIdGenerator(objectIdInfo, idProp), objectIdInfo.getAlwaysAsId());
                        } else {
                            i++;
                        }
                    }
                    throw new IllegalArgumentException("Invalid Object Id definition for " + this._handledType.getName() + ": can not find property with name '" + propName + "'");
                }
                oiw = ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), provider.objectIdGeneratorInstance(accessor, objectIdInfo), objectIdInfo.getAlwaysAsId());
            } else if (oiw != null) {
                oiw = this._objectIdWriter.withAlwaysAsId(intr.findObjectReferenceInfo(accessor, new ObjectIdInfo(NAME_FOR_OBJECT_REF, null, null, null)).getAlwaysAsId());
            }
            Object filterId = intr.findFilterId(accessor);
            if (filterId != null && (this._propertyFilterId == null || !filterId.equals(this._propertyFilterId))) {
                newFilterId = filterId;
            }
        }
        BeanSerializerBase contextual = this;
        if (oiw != null) {
            oiw = oiw.withSerializer(provider.findValueSerializer(oiw.idType, property));
            if (oiw != this._objectIdWriter) {
                contextual = withObjectIdWriter(oiw);
            }
        }
        if (!(ignorals == null || ignorals.length == 0)) {
            contextual = contextual.withIgnorals(ignorals);
        }
        if (newFilterId != null) {
            contextual = contextual.withFilterId(newFilterId);
        }
        Shape shape = null;
        if (accessor != null) {
            Value format = intr.findFormat(accessor);
            if (format != null) {
                shape = format.getShape();
            }
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == Shape.ARRAY) {
            return contextual.asArraySerializer();
        }
        return contextual;
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public void serializeWithType(Object bean, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonGenerationException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(bean, jgen, provider, typeSer);
            return;
        }
        String typeStr = this._typeId == null ? null : _customTypeId(bean);
        if (typeStr == null) {
            typeSer.writeTypePrefixForObject(bean, jgen);
        } else {
            typeSer.writeCustomTypePrefixForObject(bean, jgen, typeStr);
        }
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(bean, jgen, provider);
        } else {
            serializeFields(bean, jgen, provider);
        }
        if (typeStr == null) {
            typeSer.writeTypeSuffixForObject(bean, jgen);
        } else {
            typeSer.writeCustomTypeSuffixForObject(bean, jgen, typeStr);
        }
    }

    protected final void _serializeWithObjectId(Object bean, JsonGenerator jgen, SerializerProvider provider, boolean startEndObject) throws IOException, JsonGenerationException {
        ObjectIdWriter w = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w.generator);
        if (!objectId.writeAsId(jgen, provider, w)) {
            Object id = objectId.generateId(bean);
            if (w.alwaysAsId) {
                w.serializer.serialize(id, jgen, provider);
                return;
            }
            if (startEndObject) {
                jgen.writeStartObject();
            }
            objectId.writeAsField(jgen, provider, w);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(bean, jgen, provider);
            } else {
                serializeFields(bean, jgen, provider);
            }
            if (startEndObject) {
                jgen.writeEndObject();
            }
        }
    }

    protected final void _serializeWithObjectId(Object bean, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonGenerationException {
        ObjectIdWriter w = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w.generator);
        if (!objectId.writeAsId(jgen, provider, w)) {
            Object id = objectId.generateId(bean);
            if (w.alwaysAsId) {
                w.serializer.serialize(id, jgen, provider);
            } else {
                _serializeObjectId(bean, jgen, provider, typeSer, objectId);
            }
        }
    }

    protected void _serializeObjectId(Object bean, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer, WritableObjectId objectId) throws IOException, JsonProcessingException, JsonGenerationException {
        ObjectIdWriter w = this._objectIdWriter;
        String typeStr = this._typeId == null ? null : _customTypeId(bean);
        if (typeStr == null) {
            typeSer.writeTypePrefixForObject(bean, jgen);
        } else {
            typeSer.writeCustomTypePrefixForObject(bean, jgen, typeStr);
        }
        objectId.writeAsField(jgen, provider, w);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(bean, jgen, provider);
        } else {
            serializeFields(bean, jgen, provider);
        }
        if (typeStr == null) {
            typeSer.writeTypeSuffixForObject(bean, jgen);
        } else {
            typeSer.writeCustomTypeSuffixForObject(bean, jgen, typeStr);
        }
    }

    private final String _customTypeId(Object bean) {
        Object typeId = this._typeId.getValue(bean);
        if (typeId == null) {
            return "";
        }
        return typeId instanceof String ? (String) typeId : typeId.toString();
    }

    protected void serializeFields(Object bean, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] props;
        if (this._filteredProps == null || provider.getActiveView() == null) {
            props = this._props;
        } else {
            props = this._filteredProps;
        }
        int i = 0;
        try {
            int len = props.length;
            while (i < len) {
                BeanPropertyWriter prop = props[i];
                if (prop != null) {
                    prop.serializeAsField(bean, jgen, provider);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(bean, jgen, provider);
            }
        } catch (Exception e) {
            wrapAndThrow(provider, (Throwable) e, bean, i == props.length ? "[anySetter]" : props[i].getName());
        } catch (Throwable e2) {
            JsonMappingException mapE = new JsonMappingException("Infinite recursion (StackOverflowError)", e2);
            mapE.prependPath(new Reference(bean, i == props.length ? "[anySetter]" : props[i].getName()));
            throw mapE;
        }
    }

    protected void serializeFieldsFiltered(Object bean, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] props;
        if (this._filteredProps == null || provider.getActiveView() == null) {
            props = this._props;
        } else {
            props = this._filteredProps;
        }
        PropertyFilter filter = findPropertyFilter(provider, this._propertyFilterId, bean);
        if (filter == null) {
            serializeFields(bean, jgen, provider);
            return;
        }
        int i = 0;
        try {
            int len = props.length;
            while (i < len) {
                BeanPropertyWriter prop = props[i];
                if (prop != null) {
                    filter.serializeAsField(bean, jgen, provider, prop);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndFilter(bean, jgen, provider, filter);
            }
        } catch (Exception e) {
            wrapAndThrow(provider, (Throwable) e, bean, 0 == props.length ? "[anySetter]" : props[0].getName());
        } catch (Throwable e2) {
            JsonMappingException mapE = new JsonMappingException("Infinite recursion (StackOverflowError)", e2);
            mapE.prependPath(new Reference(bean, 0 == props.length ? "[anySetter]" : props[0].getName()));
            throw mapE;
        }
    }

    @Deprecated
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        PropertyFilter filter;
        ObjectNode o = createSchemaNode("object", true);
        JsonSerializableSchema ann = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (ann != null) {
            String id = ann.id();
            if (id != null && id.length() > 0) {
                o.put("id", id);
            }
        }
        JsonNode propertiesNode = o.objectNode();
        if (this._propertyFilterId != null) {
            filter = findPropertyFilter(provider, this._propertyFilterId, null);
        } else {
            filter = null;
        }
        for (PropertyWriter prop : this._props) {
            if (filter == null) {
                prop.depositSchemaProperty(propertiesNode, provider);
            } else {
                filter.depositSchemaProperty(prop, (ObjectNode) propertiesNode, provider);
            }
        }
        o.put("properties", propertiesNode);
        return o;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (visitor != null) {
            JsonObjectFormatVisitor objectVisitor = visitor.expectObjectFormat(typeHint);
            if (objectVisitor == null) {
                return;
            }
            if (this._propertyFilterId != null) {
                PropertyFilter filter = findPropertyFilter(visitor.getProvider(), this._propertyFilterId, null);
                for (PropertyWriter depositSchemaProperty : this._props) {
                    filter.depositSchemaProperty(depositSchemaProperty, objectVisitor, visitor.getProvider());
                }
                return;
            }
            for (BeanPropertyWriter depositSchemaProperty2 : this._props) {
                depositSchemaProperty2.depositSchemaProperty(objectVisitor);
            }
        }
    }
}
