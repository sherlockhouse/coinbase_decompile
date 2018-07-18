package com.fasterxml.jackson.databind.node;

public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode {
    protected final JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory nc) {
        this._nodeFactory = nc;
    }

    public String asText() {
        return "";
    }

    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    public final BooleanNode booleanNode(boolean v) {
        return this._nodeFactory.booleanNode(v);
    }

    public final TextNode textNode(String text) {
        return this._nodeFactory.textNode(text);
    }
}
