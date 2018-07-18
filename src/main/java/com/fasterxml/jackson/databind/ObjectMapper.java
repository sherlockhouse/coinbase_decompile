package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper extends ObjectCodec implements Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final BaseSettings DEFAULT_BASE = new BaseSettings(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), TimeZone.getTimeZone("GMT"), Base64Variants.getDefaultVariant());
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER = Std.defaultInstance();
    protected static final PrettyPrinter _defaultPrettyPrinter = new DefaultPrettyPrinter();
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jf) {
        this(jf, null, null);
    }

    public ObjectMapper(JsonFactory jf, DefaultSerializerProvider sp, DefaultDeserializationContext dc) {
        this._rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);
        if (jf == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jf;
            if (jf.getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        this._rootNames = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        Map mixins = new HashMap();
        this._mixInAnnotations = mixins;
        this._serializationConfig = new SerializationConfig(DEFAULT_BASE, this._subtypeResolver, mixins);
        this._deserializationConfig = new DeserializationConfig(DEFAULT_BASE, this._subtypeResolver, mixins);
        boolean needOrder = this._jsonFactory.requiresPropertyOrdering();
        if ((this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY) ^ needOrder) != 0) {
            configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, needOrder);
        }
        if (sp == null) {
            sp = new Impl();
        }
        this._serializerProvider = sp;
        if (dc == null) {
            dc = new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
        }
        this._deserializationContext = dc;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public ObjectMapper registerModule(Module module) {
        if (module.getModuleName() == null) {
            throw new IllegalArgumentException("Module without defined name");
        } else if (module.version() == null) {
            throw new IllegalArgumentException("Module without defined version");
        } else {
            final ObjectMapper mapper = this;
            module.setupModule(new SetupContext() {
                public void addDeserializers(Deserializers d) {
                    DeserializerFactory df = mapper._deserializationContext._factory.withAdditionalDeserializers(d);
                    mapper._deserializationContext = mapper._deserializationContext.with(df);
                }

                public void addKeyDeserializers(KeyDeserializers d) {
                    DeserializerFactory df = mapper._deserializationContext._factory.withAdditionalKeyDeserializers(d);
                    mapper._deserializationContext = mapper._deserializationContext.with(df);
                }

                public void addBeanDeserializerModifier(BeanDeserializerModifier modifier) {
                    DeserializerFactory df = mapper._deserializationContext._factory.withDeserializerModifier(modifier);
                    mapper._deserializationContext = mapper._deserializationContext.with(df);
                }

                public void addSerializers(Serializers s) {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalSerializers(s);
                }

                public void addKeySerializers(Serializers s) {
                    mapper._serializerFactory = mapper._serializerFactory.withAdditionalKeySerializers(s);
                }

                public void addBeanSerializerModifier(BeanSerializerModifier modifier) {
                    mapper._serializerFactory = mapper._serializerFactory.withSerializerModifier(modifier);
                }

                public void addAbstractTypeResolver(AbstractTypeResolver resolver) {
                    DeserializerFactory df = mapper._deserializationContext._factory.withAbstractTypeResolver(resolver);
                    mapper._deserializationContext = mapper._deserializationContext.with(df);
                }

                public void addValueInstantiators(ValueInstantiators instantiators) {
                    DeserializerFactory df = mapper._deserializationContext._factory.withValueInstantiators(instantiators);
                    mapper._deserializationContext = mapper._deserializationContext.with(df);
                }

                public void registerSubtypes(NamedType... subtypes) {
                    mapper.registerSubtypes(subtypes);
                }

                public void setMixInAnnotations(Class<?> target, Class<?> mixinSource) {
                    mapper.addMixInAnnotations(target, mixinSource);
                }

                public void setNamingStrategy(PropertyNamingStrategy naming) {
                    mapper.setPropertyNamingStrategy(naming);
                }
            });
            return this;
        }
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public final void addMixInAnnotations(Class<?> target, Class<?> mixinSource) {
        this._mixInAnnotations.put(new ClassKey(target), mixinSource);
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy s) {
        this._serializationConfig = this._serializationConfig.with(s);
        this._deserializationConfig = this._deserializationConfig.with(s);
        return this;
    }

    public ObjectMapper setSerializationInclusion(Include incl) {
        this._serializationConfig = this._serializationConfig.withSerializationInclusion(incl);
        return this;
    }

    public void registerSubtypes(NamedType... types) {
        getSubtypeResolver().registerSubtypes(types);
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public ObjectMapper configure(MapperFeature f, boolean state) {
        SerializationConfig with;
        DeserializationConfig with2;
        if (state) {
            with = this._serializationConfig.with(f);
        } else {
            with = this._serializationConfig.without(f);
        }
        this._serializationConfig = with;
        if (state) {
            with2 = this._deserializationConfig.with(f);
        } else {
            with2 = this._deserializationConfig.without(f);
        }
        this._deserializationConfig = with2;
        return this;
    }

    public ObjectMapper configure(SerializationFeature f, boolean state) {
        this._serializationConfig = state ? this._serializationConfig.with(f) : this._serializationConfig.without(f);
        return this;
    }

    public ObjectMapper configure(DeserializationFeature f, boolean state) {
        this._deserializationConfig = state ? this._deserializationConfig.with(f) : this._deserializationConfig.without(f);
        return this;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public <T extends TreeNode> T readTree(JsonParser jp) throws IOException, JsonProcessingException {
        DeserializationConfig cfg = getDeserializationConfig();
        if (jp.getCurrentToken() == null && jp.nextToken() == null) {
            return null;
        }
        T n = (JsonNode) _readValue(cfg, jp, JSON_NODE_TYPE);
        if (n == null) {
            n = getNodeFactory().nullNode();
        }
        return n;
    }

    public JsonNode readTree(String content) throws IOException, JsonProcessingException {
        JsonNode n = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(content), JSON_NODE_TYPE);
        return n == null ? NullNode.instance : n;
    }

    public void writeValue(JsonGenerator jgen, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig config = getSerializationConfig();
        if (config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            jgen.useDefaultPrettyPrinter();
        }
        if (config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (value instanceof Closeable)) {
            _writeCloseableValue(jgen, value, config);
            return;
        }
        _serializerProvider(config).serializeValue(jgen, value);
        if (config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jgen.flush();
        }
    }

    public <T> T readValue(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(content), this._typeFactory.constructType((Type) valueType));
    }

    public <T> T readValue(String content, TypeReference valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(content), this._typeFactory.constructType(valueTypeRef));
    }

    public <T> T readValue(String content, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(content), valueType);
    }

    public String writeValueAsString(Object value) throws JsonProcessingException {
        SegmentedStringWriter sw = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator(sw), value);
            return sw.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    protected DefaultSerializerProvider _serializerProvider(SerializationConfig config) {
        return this._serializerProvider.createInstance(config, this._serializerFactory);
    }

    protected final void _configAndWriteValue(JsonGenerator jgen, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig cfg = getSerializationConfig();
        if (cfg.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            jgen.useDefaultPrettyPrinter();
        }
        if (cfg.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN)) {
            jgen.enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        }
        if (cfg.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (value instanceof Closeable)) {
            _configAndWriteCloseable(jgen, value, cfg);
            return;
        }
        boolean closed = false;
        try {
            _serializerProvider(cfg).serializeValue(jgen, value);
            closed = true;
            jgen.close();
            if (1 == null) {
                jgen.disable(Feature.AUTO_CLOSE_JSON_CONTENT);
                try {
                    jgen.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (!closed) {
                jgen.disable(Feature.AUTO_CLOSE_JSON_CONTENT);
                try {
                    jgen.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    private final void _configAndWriteCloseable(JsonGenerator jgen, Object value, SerializationConfig cfg) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable toClose = (Closeable) value;
        try {
            _serializerProvider(cfg).serializeValue(jgen, value);
            JsonGenerator tmpJgen = jgen;
            jgen = null;
            tmpJgen.close();
            Closeable tmpToClose = toClose;
            toClose = null;
            tmpToClose.close();
            if (jgen != null) {
                jgen.disable(Feature.AUTO_CLOSE_JSON_CONTENT);
                try {
                    jgen.close();
                } catch (IOException e) {
                }
            }
            if (toClose != null) {
                try {
                    toClose.close();
                } catch (IOException e2) {
                }
            }
        } catch (Throwable th) {
            if (jgen != null) {
                jgen.disable(Feature.AUTO_CLOSE_JSON_CONTENT);
                try {
                    jgen.close();
                } catch (IOException e3) {
                }
            }
            if (toClose != null) {
                try {
                    toClose.close();
                } catch (IOException e4) {
                }
            }
        }
    }

    private final void _writeCloseableValue(JsonGenerator jgen, Object value, SerializationConfig cfg) throws IOException, JsonGenerationException, JsonMappingException {
        Closeable toClose = (Closeable) value;
        try {
            _serializerProvider(cfg).serializeValue(jgen, value);
            if (cfg.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jgen.flush();
            }
            Closeable tmpToClose = toClose;
            toClose = null;
            tmpToClose.close();
            if (toClose != null) {
                try {
                    toClose.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (toClose != null) {
                try {
                    toClose.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jp, DeserializationConfig cfg) {
        return this._deserializationContext.createInstance(cfg, jp, this._injectableValues);
    }

    protected Object _readValue(DeserializationConfig cfg, JsonParser jp, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        Object nullValue;
        JsonToken t = _initForReading(jp);
        if (t == JsonToken.VALUE_NULL) {
            nullValue = _findRootDeserializer(createDeserializationContext(jp, cfg), valueType).getNullValue();
        } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
            nullValue = null;
        } else {
            DeserializationContext ctxt = createDeserializationContext(jp, cfg);
            JsonDeserializer<Object> deser = _findRootDeserializer(ctxt, valueType);
            if (cfg.useRootWrapping()) {
                nullValue = _unwrapAndDeserialize(jp, ctxt, cfg, valueType, deser);
            } else {
                nullValue = deser.deserialize(jp, ctxt);
            }
        }
        jp.clearCurrentToken();
        return nullValue;
    }

    protected Object _readMapAndClose(JsonParser jp, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
        try {
            Object nullValue;
            JsonToken t = _initForReading(jp);
            if (t == JsonToken.VALUE_NULL) {
                nullValue = _findRootDeserializer(createDeserializationContext(jp, getDeserializationConfig()), valueType).getNullValue();
            } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
                nullValue = null;
            } else {
                DeserializationConfig cfg = getDeserializationConfig();
                DeserializationContext ctxt = createDeserializationContext(jp, cfg);
                JsonDeserializer<Object> deser = _findRootDeserializer(ctxt, valueType);
                if (cfg.useRootWrapping()) {
                    nullValue = _unwrapAndDeserialize(jp, ctxt, cfg, valueType, deser);
                } else {
                    nullValue = deser.deserialize(jp, ctxt);
                }
                ctxt.checkUnresolvedObjectId();
            }
            jp.clearCurrentToken();
            return nullValue;
        } finally {
            try {
                jp.close();
            } catch (IOException e) {
            }
        }
    }

    protected JsonToken _initForReading(JsonParser jp) throws IOException, JsonParseException, JsonMappingException {
        JsonToken t = jp.getCurrentToken();
        if (t == null) {
            t = jp.nextToken();
            if (t == null) {
                throw JsonMappingException.from(jp, "No content to map due to end-of-input");
            }
        }
        return t;
    }

    protected Object _unwrapAndDeserialize(JsonParser jp, DeserializationContext ctxt, DeserializationConfig config, JavaType rootType, JsonDeserializer<Object> deser) throws IOException, JsonParseException, JsonMappingException {
        String expName = config.getRootName();
        if (expName == null) {
            expName = this._rootNames.findRootName(rootType, (MapperConfig) config).getSimpleName();
        }
        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
            throw JsonMappingException.from(jp, "Current token not START_OBJECT (needed to unwrap root name '" + expName + "'), but " + jp.getCurrentToken());
        } else if (jp.nextToken() != JsonToken.FIELD_NAME) {
            throw JsonMappingException.from(jp, "Current token not FIELD_NAME (to contain expected root name '" + expName + "'), but " + jp.getCurrentToken());
        } else {
            String actualName = jp.getCurrentName();
            if (expName.equals(actualName)) {
                jp.nextToken();
                Object result = deser.deserialize(jp, ctxt);
                if (jp.nextToken() == JsonToken.END_OBJECT) {
                    return result;
                }
                throw JsonMappingException.from(jp, "Current token not END_OBJECT (to match wrapper object with root name '" + expName + "'), but " + jp.getCurrentToken());
            }
            throw JsonMappingException.from(jp, "Root name '" + actualName + "' does not match expected ('" + expName + "') for type " + rootType);
        }
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt, JavaType valueType) throws JsonMappingException {
        JsonDeserializer<Object> deser = (JsonDeserializer) this._rootDeserializers.get(valueType);
        if (deser != null) {
            return deser;
        }
        deser = ctxt.findRootValueDeserializer(valueType);
        if (deser == null) {
            throw new JsonMappingException("Can not find a deserializer for type " + valueType);
        }
        this._rootDeserializers.put(valueType, deser);
        return deser;
    }
}
