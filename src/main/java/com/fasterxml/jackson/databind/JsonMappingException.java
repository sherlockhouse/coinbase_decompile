package com.fasterxml.jackson.databind;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class JsonMappingException extends JsonProcessingException {
    protected LinkedList<Reference> _path;

    public static class Reference implements Serializable {
        protected String _fieldName;
        protected Object _from;
        protected int _index = -1;

        protected Reference() {
        }

        public Reference(Object from, String fieldName) {
            this._from = from;
            if (fieldName == null) {
                throw new NullPointerException("Can not pass null fieldName");
            }
            this._fieldName = fieldName;
        }

        public Reference(Object from, int index) {
            this._from = from;
            this._index = index;
        }

        public String toString() {
            Class<?> cls;
            StringBuilder sb = new StringBuilder();
            if (this._from instanceof Class) {
                cls = (Class) this._from;
            } else {
                cls = this._from.getClass();
            }
            Package pkg = cls.getPackage();
            if (pkg != null) {
                sb.append(pkg.getName());
                sb.append(NumericKeypadConnector.DOT);
            }
            sb.append(cls.getSimpleName());
            sb.append('[');
            if (this._fieldName != null) {
                sb.append('\"');
                sb.append(this._fieldName);
                sb.append('\"');
            } else if (this._index >= 0) {
                sb.append(this._index);
            } else {
                sb.append('?');
            }
            sb.append(']');
            return sb.toString();
        }
    }

    public JsonMappingException(String msg) {
        super(msg);
    }

    public JsonMappingException(String msg, Throwable rootCause) {
        super(msg, rootCause);
    }

    public JsonMappingException(String msg, JsonLocation loc) {
        super(msg, loc);
    }

    public JsonMappingException(String msg, JsonLocation loc, Throwable rootCause) {
        super(msg, loc, rootCause);
    }

    public static JsonMappingException from(JsonParser jp, String msg) {
        return new JsonMappingException(msg, jp == null ? null : jp.getTokenLocation());
    }

    public static JsonMappingException from(JsonParser jp, String msg, Throwable problem) {
        return new JsonMappingException(msg, jp == null ? null : jp.getTokenLocation(), problem);
    }

    public static JsonMappingException fromUnexpectedIOE(IOException src) {
        return new JsonMappingException("Unexpected IOException (of type " + src.getClass().getName() + "): " + src.getMessage(), (JsonLocation) null, src);
    }

    public static JsonMappingException wrapWithPath(Throwable src, Object refFrom, String refFieldName) {
        return wrapWithPath(src, new Reference(refFrom, refFieldName));
    }

    public static JsonMappingException wrapWithPath(Throwable src, Object refFrom, int index) {
        return wrapWithPath(src, new Reference(refFrom, index));
    }

    public static JsonMappingException wrapWithPath(Throwable src, Reference ref) {
        JsonMappingException jme;
        if (src instanceof JsonMappingException) {
            jme = (JsonMappingException) src;
        } else {
            String msg = src.getMessage();
            if (msg == null || msg.length() == 0) {
                msg = "(was " + src.getClass().getName() + ")";
            }
            jme = new JsonMappingException(msg, null, src);
        }
        jme.prependPath(ref);
        return jme;
    }

    public StringBuilder getPathReference(StringBuilder sb) {
        _appendPathDesc(sb);
        return sb;
    }

    public void prependPath(Object referrer, String fieldName) {
        prependPath(new Reference(referrer, fieldName));
    }

    public void prependPath(Reference r) {
        if (this._path == null) {
            this._path = new LinkedList();
        }
        if (this._path.size() < 1000) {
            this._path.addFirst(r);
        }
    }

    public String getLocalizedMessage() {
        return _buildMessage();
    }

    public String getMessage() {
        return _buildMessage();
    }

    protected String _buildMessage() {
        String msg = super.getMessage();
        if (this._path == null) {
            return msg;
        }
        StringBuilder sb = msg == null ? new StringBuilder() : new StringBuilder(msg);
        sb.append(" (through reference chain: ");
        sb = getPathReference(sb);
        sb.append(')');
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    protected void _appendPathDesc(StringBuilder sb) {
        if (this._path != null) {
            Iterator<Reference> it = this._path.iterator();
            while (it.hasNext()) {
                sb.append(((Reference) it.next()).toString());
                if (it.hasNext()) {
                    sb.append("->");
                }
            }
        }
    }
}
