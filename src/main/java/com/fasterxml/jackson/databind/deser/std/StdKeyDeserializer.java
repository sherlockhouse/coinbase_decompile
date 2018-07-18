package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    protected final FromStringDeserializer<?> _deser;
    protected final Class<?> _keyClass;
    protected final int _kind;

    static final class DelegatingKD extends KeyDeserializer implements Serializable {
        protected final JsonDeserializer<?> _delegate;
        protected final Class<?> _keyClass;

        protected DelegatingKD(Class<?> cls, JsonDeserializer<?> deser) {
            this._keyClass = cls;
            this._delegate = deser;
        }

        public final Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (key == null) {
                return null;
            }
            try {
                Object result = this._delegate.deserialize(ctxt.getParser(), ctxt);
                if (result != null) {
                    return result;
                }
                throw ctxt.weirdKeyException(this._keyClass, key, "not a valid representation");
            } catch (Exception re) {
                throw ctxt.weirdKeyException(this._keyClass, key, "not a valid representation: " + re.getMessage());
            }
        }
    }

    @JacksonStdImpl
    static final class EnumKD extends StdKeyDeserializer {
        protected final AnnotatedMethod _factory;
        protected final EnumResolver<?> _resolver;

        protected EnumKD(EnumResolver<?> er, AnnotatedMethod factory) {
            super(-1, er.getEnumClass());
            this._resolver = er;
            this._factory = factory;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws JsonMappingException {
            Object call1;
            if (this._factory != null) {
                try {
                    call1 = this._factory.call1(key);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
                return call1;
            }
            call1 = this._resolver.findEnum(key);
            if (call1 == null && !ctxt.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                throw ctxt.weirdKeyException(this._keyClass, key, "not one of values for Enum class");
            }
            return call1;
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> ctor) {
            super(-1, ctor.getDeclaringClass());
            this._ctor = ctor;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws Exception {
            return this._ctor.newInstance(new Object[]{key});
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method fm) {
            super(-1, fm.getDeclaringClass());
            this._factoryMethod = fm;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws Exception {
            return this._factoryMethod.invoke(null, new Object[]{key});
        }
    }

    @JacksonStdImpl
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sObject = new StringKD(Object.class);
        private static final StringKD sString = new StringKD(String.class);

        private StringKD(Class<?> nominalType) {
            super(-1, nominalType);
        }

        public static StringKD forType(Class<?> nominalType) {
            if (nominalType == String.class) {
                return sString;
            }
            if (nominalType == Object.class) {
                return sObject;
            }
            return new StringKD(nominalType);
        }

        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return key;
        }
    }

    protected StdKeyDeserializer(int kind, Class<?> cls) {
        this(kind, cls, null);
    }

    protected StdKeyDeserializer(int kind, Class<?> cls, FromStringDeserializer<?> deser) {
        this._kind = kind;
        this._keyClass = cls;
        this._deser = deser;
    }

    public static StdKeyDeserializer forType(Class<?> raw) {
        if (raw == String.class || raw == Object.class) {
            return StringKD.forType(raw);
        }
        int kind;
        if (raw == UUID.class) {
            kind = 12;
        } else if (raw == Integer.class) {
            kind = 5;
        } else if (raw == Long.class) {
            kind = 6;
        } else if (raw == Date.class) {
            kind = 10;
        } else if (raw == Calendar.class) {
            kind = 11;
        } else if (raw == Boolean.class) {
            kind = 1;
        } else if (raw == Byte.class) {
            kind = 2;
        } else if (raw == Character.class) {
            kind = 4;
        } else if (raw == Short.class) {
            kind = 3;
        } else if (raw == Float.class) {
            kind = 7;
        } else if (raw == Double.class) {
            kind = 8;
        } else if (raw == Locale.class) {
            return new StdKeyDeserializer(9, raw, FromStringDeserializer.findDeserializer(Locale.class));
        } else {
            return null;
        }
        return new StdKeyDeserializer(kind, raw);
    }

    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (key == null) {
            return null;
        }
        try {
            Object result = _parse(key, ctxt);
            if (result != null) {
                return result;
            }
            if (this._keyClass.isEnum() && ctxt.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            throw ctxt.weirdKeyException(this._keyClass, key, "not a valid representation");
        } catch (Exception re) {
            throw ctxt.weirdKeyException(this._keyClass, key, "not a valid representation: " + re.getMessage());
        }
    }

    protected Object _parse(String key, DeserializationContext ctxt) throws Exception {
        int value;
        switch (this._kind) {
            case 1:
                if ("true".equals(key)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(key)) {
                    return Boolean.FALSE;
                }
                throw ctxt.weirdKeyException(this._keyClass, key, "value not 'true' or 'false'");
            case 2:
                value = _parseInt(key);
                if (value >= -128 && value <= 255) {
                    return Byte.valueOf((byte) value);
                }
                throw ctxt.weirdKeyException(this._keyClass, key, "overflow, value can not be represented as 8-bit value");
            case 3:
                value = _parseInt(key);
                if (value >= -32768 && value <= 32767) {
                    return Short.valueOf((short) value);
                }
                throw ctxt.weirdKeyException(this._keyClass, key, "overflow, value can not be represented as 16-bit value");
            case 4:
                if (key.length() == 1) {
                    return Character.valueOf(key.charAt(0));
                }
                throw ctxt.weirdKeyException(this._keyClass, key, "can only convert 1-character Strings");
            case 5:
                return Integer.valueOf(_parseInt(key));
            case 6:
                return Long.valueOf(_parseLong(key));
            case 7:
                return Float.valueOf((float) _parseDouble(key));
            case 8:
                return Double.valueOf(_parseDouble(key));
            case 9:
                try {
                    return this._deser._deserialize(key, ctxt);
                } catch (IOException e) {
                    throw ctxt.weirdKeyException(this._keyClass, key, "unable to parse key as locale");
                }
            case 10:
                return ctxt.parseDate(key);
            case 11:
                Date date = ctxt.parseDate(key);
                if (date != null) {
                    return ctxt.constructCalendar(date);
                }
                return null;
            case 12:
                return UUID.fromString(key);
            default:
                return null;
        }
    }

    protected int _parseInt(String key) throws IllegalArgumentException {
        return Integer.parseInt(key);
    }

    protected long _parseLong(String key) throws IllegalArgumentException {
        return Long.parseLong(key);
    }

    protected double _parseDouble(String key) throws IllegalArgumentException {
        return NumberInput.parseDouble(key);
    }
}
