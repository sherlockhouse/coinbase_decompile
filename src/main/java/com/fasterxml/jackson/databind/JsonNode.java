package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class JsonNode implements TreeNode, Iterable<JsonNode> {
    public abstract String asText();

    public abstract JsonNode path(String str);

    public abstract String toString();

    protected JsonNode() {
    }

    public int size() {
        return 0;
    }

    public JsonNode get(String fieldName) {
        return null;
    }

    public String textValue() {
        return null;
    }

    public int intValue() {
        return 0;
    }

    public long longValue() {
        return 0;
    }

    public double doubleValue() {
        return 0.0d;
    }

    public int asInt() {
        return asInt(0);
    }

    public int asInt(int defaultValue) {
        return defaultValue;
    }

    public boolean has(String fieldName) {
        return get(fieldName) != null;
    }

    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public Iterator<JsonNode> elements() {
        return EmptyIterator.instance();
    }

    public Iterator<Entry<String, JsonNode>> fields() {
        return EmptyIterator.instance();
    }
}
