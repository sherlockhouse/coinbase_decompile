package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata implements Serializable {
    public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null);
    public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null);
    public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null);
    protected final String _description;
    protected final Integer _index;
    protected final Boolean _required;

    protected PropertyMetadata(Boolean req, String desc, Integer index) {
        this._required = req;
        this._description = desc;
        this._index = index;
    }

    public static PropertyMetadata construct(boolean req, String desc, Integer index) {
        PropertyMetadata md = req ? STD_REQUIRED : STD_OPTIONAL;
        if (desc != null) {
            md = md.withDescription(desc);
        }
        if (index != null) {
            return md.withIndex(index);
        }
        return md;
    }

    public PropertyMetadata withDescription(String desc) {
        return new PropertyMetadata(this._required, desc, this._index);
    }

    public PropertyMetadata withIndex(Integer index) {
        return new PropertyMetadata(this._required, this._description, index);
    }

    public boolean isRequired() {
        return this._required != null && this._required.booleanValue();
    }
}
