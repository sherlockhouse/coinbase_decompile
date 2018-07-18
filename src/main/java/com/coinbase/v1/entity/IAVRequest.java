package com.coinbase.v1.entity;

import com.coinbase.v1.entity.PaymentMethod.VerificationMethod;
import com.coinbase.v1.serializer.IAVRequestSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.HashMap;

@JsonSerialize(using = IAVRequestSerializer.class)
public class IAVRequest implements Serializable {
    private static final long serialVersionUID = 3286918689461549816L;
    private HashMap<String, String> _iavFields;
    private VerificationMethod _verificationMethod;

    public VerificationMethod getVerificationMethod() {
        return this._verificationMethod;
    }

    public void setVerificationMethod(VerificationMethod verificationMethod) {
        this._verificationMethod = verificationMethod;
    }

    public void setIavFields(HashMap<String, String> iavFields) {
        this._iavFields = iavFields;
    }

    public HashMap<String, String> getIavFields() {
        return this._iavFields;
    }
}
