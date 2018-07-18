package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends ContainerNode<ArrayNode> {
    private final List<JsonNode> _children = new ArrayList();

    public ArrayNode(JsonNodeFactory nc) {
        super(nc);
    }

    public int size() {
        return this._children.size();
    }

    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    public JsonNode get(String fieldName) {
        return null;
    }

    public JsonNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    public void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException, JsonProcessingException {
        jg.writeStartArray();
        for (JsonNode n : this._children) {
            ((BaseJsonNode) n).serialize(jg, provider);
        }
        jg.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jg, SerializerProvider provider, TypeSerializer typeSer) throws IOException, JsonProcessingException {
        typeSer.writeTypePrefixForArray(this, jg);
        for (JsonNode n : this._children) {
            ((BaseJsonNode) n).serialize(jg, provider);
        }
        typeSer.writeTypeSuffixForArray(this, jg);
    }

    public ArrayNode add(JsonNode value) {
        if (value == null) {
            value = nullNode();
        }
        _add(value);
        return this;
    }

    public ArrayNode addNull() {
        _add(nullNode());
        return this;
    }

    public ArrayNode add(String v) {
        if (v == null) {
            return addNull();
        }
        return _add(textNode(v));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof ArrayNode)) {
            return false;
        }
        return this._children.equals(((ArrayNode) o)._children);
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 16);
        sb.append('[');
        int len = this._children.size();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(((JsonNode) this._children.get(i)).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    protected ArrayNode _add(JsonNode node) {
        this._children.add(node);
        return this;
    }
}
