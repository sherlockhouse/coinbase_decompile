package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter implements PrettyPrinter, Serializable {
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    protected Indenter _arrayIndenter;
    protected transient int _nesting;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries;

    public interface Indenter {
        boolean isInline();

        void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;
    }

    public static class NopIndenter implements Indenter, Serializable {
        public static final NopIndenter instance = new NopIndenter();

        public void writeIndentation(JsonGenerator jg, int level) throws IOException, JsonGenerationException {
        }

        public boolean isInline() {
            return true;
        }
    }

    public static class FixedSpaceIndenter extends NopIndenter {
        public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();

        public void writeIndentation(JsonGenerator jg, int level) throws IOException, JsonGenerationException {
            jg.writeRaw(' ');
        }

        public boolean isInline() {
            return true;
        }
    }

    public static class Lf2SpacesIndenter extends NopIndenter {
        static final char[] SPACES = new char[64];
        private static final String SYS_LF;
        public static final Lf2SpacesIndenter instance = new Lf2SpacesIndenter();
        protected final String _lf;

        static {
            String lf = null;
            try {
                lf = System.getProperty("line.separator");
            } catch (Throwable th) {
            }
            if (lf == null) {
                lf = "\n";
            }
            SYS_LF = lf;
            Arrays.fill(SPACES, ' ');
        }

        public Lf2SpacesIndenter() {
            this(SYS_LF);
        }

        public Lf2SpacesIndenter(String lf) {
            this._lf = lf;
        }

        public boolean isInline() {
            return false;
        }

        public void writeIndentation(JsonGenerator jg, int level) throws IOException, JsonGenerationException {
            jg.writeRaw(this._lf);
            if (level > 0) {
                level += level;
                while (level > 64) {
                    jg.writeRaw(SPACES, 0, 64);
                    level -= SPACES.length;
                }
                jg.writeRaw(SPACES, 0, level);
            }
        }
    }

    public DefaultPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(SerializableString rootSeparator) {
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = Lf2SpacesIndenter.instance;
        this._spacesInObjectEntries = true;
        this._nesting = 0;
        this._rootSeparator = rootSeparator;
    }

    public void writeRootValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (this._rootSeparator != null) {
            jg.writeRaw(this._rootSeparator);
        }
    }

    public void writeStartObject(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            this._nesting++;
        }
    }

    public void beforeObjectEntries(JsonGenerator jg) throws IOException, JsonGenerationException {
        this._objectIndenter.writeIndentation(jg, this._nesting);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (this._spacesInObjectEntries) {
            jg.writeRaw(" : ");
        } else {
            jg.writeRaw(':');
        }
    }

    public void writeObjectEntrySeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw(',');
        this._objectIndenter.writeIndentation(jg, this._nesting);
    }

    public void writeEndObject(JsonGenerator jg, int nrOfEntries) throws IOException, JsonGenerationException {
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (nrOfEntries > 0) {
            this._objectIndenter.writeIndentation(jg, this._nesting);
        } else {
            jg.writeRaw(' ');
        }
        jg.writeRaw('}');
    }

    public void writeStartArray(JsonGenerator jg) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            this._nesting++;
        }
        jg.writeRaw('[');
    }

    public void beforeArrayValues(JsonGenerator jg) throws IOException, JsonGenerationException {
        this._arrayIndenter.writeIndentation(jg, this._nesting);
    }

    public void writeArrayValueSeparator(JsonGenerator jg) throws IOException, JsonGenerationException {
        jg.writeRaw(',');
        this._arrayIndenter.writeIndentation(jg, this._nesting);
    }

    public void writeEndArray(JsonGenerator jg, int nrOfValues) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            this._nesting--;
        }
        if (nrOfValues > 0) {
            this._arrayIndenter.writeIndentation(jg, this._nesting);
        } else {
            jg.writeRaw(' ');
        }
        jg.writeRaw(']');
    }
}
