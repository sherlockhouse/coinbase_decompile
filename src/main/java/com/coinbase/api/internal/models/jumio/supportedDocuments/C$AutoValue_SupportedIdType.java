package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_SupportedIdType extends SupportedIdType {
    private final Boolean backsideImageRequired;
    private final Type type;

    C$AutoValue_SupportedIdType(Type type, Boolean backsideImageRequired) {
        if (type == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type;
        this.backsideImageRequired = backsideImageRequired;
    }

    public Type getType() {
        return this.type;
    }

    @SerializedName("backside_image_required")
    public Boolean getBacksideImageRequired() {
        return this.backsideImageRequired;
    }

    public String toString() {
        return "SupportedIdType{type=" + this.type + ", backsideImageRequired=" + this.backsideImageRequired + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SupportedIdType)) {
            return false;
        }
        SupportedIdType that = (SupportedIdType) o;
        if (this.type.equals(that.getType())) {
            if (this.backsideImageRequired == null) {
                if (that.getBacksideImageRequired() == null) {
                    return true;
                }
            } else if (this.backsideImageRequired.equals(that.getBacksideImageRequired())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.type.hashCode()) * 1000003) ^ (this.backsideImageRequired == null ? 0 : this.backsideImageRequired.hashCode());
    }
}
