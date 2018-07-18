package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectNode extends ContainerNode<ObjectNode> {
    protected final Map<String, JsonNode> _children = new LinkedHashMap();

    public ObjectNode(JsonNodeFactory nc) {
        super(nc);
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    public JsonNode get(String fieldName) {
        return (JsonNode) this._children.get(fieldName);
    }

    public JsonNode path(String fieldName) {
        JsonNode n = (JsonNode) this._children.get(fieldName);
        return n != null ? n : MissingNode.getInstance();
    }

    public Iterator<Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    public void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeStartObject();
        for (Entry<String, JsonNode> en : this._children.entrySet()) {
            jg.writeFieldName((String) en.getKey());
            ((BaseJsonNode) en.getValue()).serialize(jg, provider);
        }
        jg.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jg, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        typeSer.writeTypePrefixForObject(this, jg);
        for (Entry<String, JsonNode> en : this._children.entrySet()) {
            jg.writeFieldName((String) en.getKey());
            ((BaseJsonNode) en.getValue()).serialize(jg, provider);
        }
        typeSer.writeTypeSuffixForObject(this, jg);
    }

    public JsonNode set(String fieldName, JsonNode value) {
        if (value == null) {
            value = nullNode();
        }
        this._children.put(fieldName, value);
        return this;
    }

    public JsonNode replace(String fieldName, JsonNode value) {
        if (value == null) {
            value = nullNode();
        }
        return (JsonNode) this._children.put(fieldName, value);
    }

    @Deprecated
    public JsonNode put(String fieldName, JsonNode value) {
        if (value == null) {
            value = nullNode();
        }
        return (JsonNode) this._children.put(fieldName, value);
    }

    public ArrayNode putArray(String fieldName) {
        ArrayNode n = arrayNode();
        _put(fieldName, n);
        return n;
    }

    public ObjectNode put(String fieldName, String v) {
        return _put(fieldName, v == null ? nullNode() : textNode(v));
    }

    public ObjectNode put(String fieldName, boolean v) {
        return _put(fieldName, booleanNode(v));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof ObjectNode)) {
            return false;
        }
        return _childrenEqual((ObjectNode) o);
    }

    protected boolean _childrenEqual(ObjectNode other) {
        return this._children.equals(other._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 32);
        sb.append("{");
        int count = 0;
        for (Entry<String, JsonNode> en : this._children.entrySet()) {
            if (count > 0) {
                sb.append(",");
            }
            count++;
            TextNode.appendQuoted(sb, (String) en.getKey());
            sb.append(':');
            sb.append(((JsonNode) en.getValue()).toString());
        }
        sb.append("}");
        return sb.toString();
    }

    protected ObjectNode _put(String fieldName, JsonNode value) {
        this._children.put(fieldName, value);
        return this;
    }
}
