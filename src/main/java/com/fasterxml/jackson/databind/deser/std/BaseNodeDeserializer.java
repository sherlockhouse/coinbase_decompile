package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/* compiled from: JsonNodeDeserializer */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    public BaseNodeDeserializer(Class<T> vc) {
        super((Class) vc);
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
    }

    protected void _reportProblem(JsonParser jp, String msg) throws JsonMappingException {
        throw new JsonMappingException(msg, jp.getTokenLocation());
    }

    @Deprecated
    protected void _handleDuplicateField(String fieldName, ObjectNode objectNode, JsonNode oldValue, JsonNode newValue) throws JsonProcessingException {
    }

    protected void _handleDuplicateField(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory, String fieldName, ObjectNode objectNode, JsonNode oldValue, JsonNode newValue) throws JsonProcessingException {
        if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            _reportProblem(jp, "Duplicate field '" + fieldName + "' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled");
        }
        _handleDuplicateField(fieldName, objectNode, oldValue, newValue);
    }

    protected final ObjectNode deserializeObject(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException, JsonProcessingException {
        ObjectNode node = nodeFactory.objectNode();
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = jp.nextToken();
        }
        while (t == JsonToken.FIELD_NAME) {
            JsonNode value;
            String fieldName = jp.getCurrentName();
            switch (jp.nextToken().id()) {
                case 1:
                    value = deserializeObject(jp, ctxt, nodeFactory);
                    break;
                case 3:
                    value = deserializeArray(jp, ctxt, nodeFactory);
                    break;
                case 6:
                    value = nodeFactory.textNode(jp.getText());
                    break;
                case 7:
                    value = _fromInt(jp, ctxt, nodeFactory);
                    break;
                case 9:
                    value = nodeFactory.booleanNode(true);
                    break;
                case 10:
                    value = nodeFactory.booleanNode(false);
                    break;
                case 11:
                    value = nodeFactory.nullNode();
                    break;
                default:
                    value = deserializeAny(jp, ctxt, nodeFactory);
                    break;
            }
            JsonNode old = node.replace(fieldName, value);
            if (old != null) {
                _handleDuplicateField(jp, ctxt, nodeFactory, fieldName, node, old, value);
            }
            t = jp.nextToken();
        }
        return node;
    }

    protected final ArrayNode deserializeArray(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException, JsonProcessingException {
        ArrayNode node = nodeFactory.arrayNode();
        while (true) {
            JsonToken t = jp.nextToken();
            if (t != null) {
                switch (t.id()) {
                    case 1:
                        node.add(deserializeObject(jp, ctxt, nodeFactory));
                        break;
                    case 3:
                        node.add(deserializeArray(jp, ctxt, nodeFactory));
                        break;
                    case 4:
                        return node;
                    case 6:
                        node.add(nodeFactory.textNode(jp.getText()));
                        break;
                    case 7:
                        node.add(_fromInt(jp, ctxt, nodeFactory));
                        break;
                    case 9:
                        node.add(nodeFactory.booleanNode(true));
                        break;
                    case 10:
                        node.add(nodeFactory.booleanNode(false));
                        break;
                    case 11:
                        node.add(nodeFactory.nullNode());
                        break;
                    default:
                        node.add(deserializeAny(jp, ctxt, nodeFactory));
                        break;
                }
            }
            throw ctxt.mappingException("Unexpected end-of-input when binding data into ArrayNode");
        }
    }

    protected final JsonNode deserializeAny(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
        switch (jp.getCurrentTokenId()) {
            case 1:
            case 2:
                return deserializeObject(jp, ctxt, nodeFactory);
            case 3:
                return deserializeArray(jp, ctxt, nodeFactory);
            case 5:
                return deserializeObject(jp, ctxt, nodeFactory);
            case 6:
                return nodeFactory.textNode(jp.getText());
            case 7:
                return _fromInt(jp, ctxt, nodeFactory);
            case 8:
                return _fromFloat(jp, ctxt, nodeFactory);
            case 9:
                return nodeFactory.booleanNode(true);
            case 10:
                return nodeFactory.booleanNode(false);
            case 11:
                return nodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jp, ctxt, nodeFactory);
            default:
                throw ctxt.mappingException(handledType());
        }
    }

    protected final JsonNode _fromInt(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
        NumberType nt = jp.getNumberType();
        if (nt == NumberType.BIG_INTEGER || ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
            return nodeFactory.numberNode(jp.getBigIntegerValue());
        }
        if (nt == NumberType.INT) {
            return nodeFactory.numberNode(jp.getIntValue());
        }
        return nodeFactory.numberNode(jp.getLongValue());
    }

    protected final JsonNode _fromFloat(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
        if (jp.getNumberType() == NumberType.BIG_DECIMAL || ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            return nodeFactory.numberNode(jp.getDecimalValue());
        }
        return nodeFactory.numberNode(jp.getDoubleValue());
    }

    protected final JsonNode _fromEmbedded(JsonParser jp, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
        Object ob = jp.getEmbeddedObject();
        if (ob == null) {
            return nodeFactory.nullNode();
        }
        Class<?> type = ob.getClass();
        if (type == byte[].class) {
            return nodeFactory.binaryNode((byte[]) ob);
        }
        if (JsonNode.class.isAssignableFrom(type)) {
            return (JsonNode) ob;
        }
        return nodeFactory.pojoNode(ob);
    }
}
