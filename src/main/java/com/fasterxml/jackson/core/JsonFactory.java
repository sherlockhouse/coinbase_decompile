package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;

public class JsonFactory implements Serializable {
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = com.fasterxml.jackson.core.JsonGenerator.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = com.fasterxml.jackson.core.JsonParser.Feature.collectDefaults();
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal();
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final transient BytesToNameCanonicalizer _rootByteSymbols;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true);
        
        private final boolean _defaultState;

        public static int collectDefaults() {
            int flags = 0;
            for (Feature f : values()) {
                if (f.enabledByDefault()) {
                    flags |= f.getMask();
                }
            }
            return flags;
        }

        private Feature(boolean defaultState) {
            this._defaultState = defaultState;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int flags) {
            return (getMask() & flags) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this(null);
    }

    public JsonFactory(ObjectCodec oc) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = oc;
    }

    public boolean requiresPropertyOrdering() {
        return false;
    }

    public boolean canUseCharArrays() {
        return true;
    }

    public JsonFactory setCodec(ObjectCodec oc) {
        this._objectCodec = oc;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonParser createParser(Reader r) throws IOException, JsonParseException {
        IOContext ctxt = _createContext(r, false);
        return _createParser(_decorate(r, ctxt), ctxt);
    }

    public JsonParser createParser(String content) throws IOException, JsonParseException {
        int strLen = content.length();
        if (this._inputDecorator != null || strLen > 32768 || !canUseCharArrays()) {
            return createParser(new StringReader(content));
        }
        IOContext ctxt = _createContext(content, true);
        char[] buf = ctxt.allocTokenBuffer(strLen);
        content.getChars(0, strLen, buf, 0);
        return _createParser(buf, 0, strLen, ctxt, true);
    }

    public JsonGenerator createGenerator(Writer w) throws IOException {
        IOContext ctxt = _createContext(w, false);
        return _createGenerator(_decorate(w, ctxt), ctxt);
    }

    protected JsonParser _createParser(Reader r, IOContext ctxt) throws IOException {
        return new ReaderBasedJsonParser(ctxt, this._parserFeatures, r, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }

    protected JsonParser _createParser(char[] data, int offset, int len, IOContext ctxt, boolean recyclable) throws IOException {
        return new ReaderBasedJsonParser(ctxt, this._parserFeatures, null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), data, offset, offset + len, recyclable);
    }

    protected JsonGenerator _createGenerator(Writer out, IOContext ctxt) throws IOException {
        WriterBasedJsonGenerator gen = new WriterBasedJsonGenerator(ctxt, this._generatorFeatures, this._objectCodec, out);
        if (this._characterEscapes != null) {
            gen.setCharacterEscapes(this._characterEscapes);
        }
        SerializableString rootSep = this._rootValueSeparator;
        if (rootSep != DEFAULT_ROOT_VALUE_SEPARATOR) {
            gen.setRootValueSeparator(rootSep);
        }
        return gen;
    }

    protected final Reader _decorate(Reader in, IOContext ctxt) throws IOException {
        if (this._inputDecorator != null) {
            Reader in2 = this._inputDecorator.decorate(ctxt, in);
            if (in2 != null) {
                return in2;
            }
        }
        return in;
    }

    protected final Writer _decorate(Writer out, IOContext ctxt) throws IOException {
        if (this._outputDecorator != null) {
            Writer out2 = this._outputDecorator.decorate(ctxt, out);
            if (out2 != null) {
                return out2;
            }
        }
        return out;
    }

    public BufferRecycler _getBufferRecycler() {
        SoftReference<BufferRecycler> ref = (SoftReference) _recyclerRef.get();
        BufferRecycler br = ref == null ? null : (BufferRecycler) ref.get();
        if (br != null) {
            return br;
        }
        br = new BufferRecycler();
        _recyclerRef.set(new SoftReference(br));
        return br;
    }

    protected IOContext _createContext(Object srcRef, boolean resourceManaged) {
        return new IOContext(_getBufferRecycler(), srcRef, resourceManaged);
    }
}
