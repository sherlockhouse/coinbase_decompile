package com.coinbase.android.idology;

import android.text.TextUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;
import rx.subjects.PublishSubject;

@ControllerScope
public class IdologyFormErrorMatcher {
    private final List<String> mHandledFields = new LinkedList();
    private final PublishSubject<List<ErrorBody>> mSubject = PublishSubject.create();

    public enum IdologyKnownFields {
        FIRST_NAME(ApiConstants.FIRST_NAME),
        LAST_NAME(ApiConstants.LAST_NAME),
        ADDRESS1(ApiConstants.ADDRESS1),
        ADDRESS2(ApiConstants.ADDRESS2),
        CITY(ApiConstants.CITY),
        STATE("state"),
        ZIP(ApiConstants.ZIP),
        DOB_MONTH(ApiConstants.DOB_MONTH),
        DOB_DAY(ApiConstants.DOB_DAY),
        DOB_YEAR(ApiConstants.DOB_YEAR),
        SSN_LAST4(ApiConstants.SSN_LAST4),
        USES_COINBASE_FOR(ApiConstants.USES_COINBASE_FOR),
        PRIMARY_SOURCE_OF_FUNDS(ApiConstants.PRIMARY_SOURCE_OF_FUNDS),
        CURRENT_JOB_TITLE(ApiConstants.CURRENT_JOB_TITLE),
        CURRENT_EMPLOYER(ApiConstants.CURRENT_EMPLOYER);
        
        static final Map<String, IdologyKnownFields> IDOLOGY_KNOWN_FIELDS = null;
        private final String mFieldName;

        static {
            IDOLOGY_KNOWN_FIELDS = new HashMap();
            IdologyKnownFields[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                IdologyKnownFields idologyKnownFields = values[i];
                IDOLOGY_KNOWN_FIELDS.put(idologyKnownFields.getName(), idologyKnownFields);
                i++;
            }
        }

        private IdologyKnownFields(String fieldName) {
            this.mFieldName = fieldName;
        }

        public String getName() {
            return this.mFieldName;
        }

        public static boolean isValid(String field) {
            return IDOLOGY_KNOWN_FIELDS.containsKey(field);
        }

        public static IdologyKnownFields from(String field) {
            return (IdologyKnownFields) IDOLOGY_KNOWN_FIELDS.get(field);
        }
    }

    @Inject
    public IdologyFormErrorMatcher(@IdologyHandledFields List<String> handledFields) {
        this.mHandledFields.addAll(handledFields);
    }

    public boolean isIdologyFormError(Errors errors) {
        return (errors == null || errors.getErrors() == null || errors.getErrors().isEmpty() || !hasIdologyErrors(errors.getErrors())) ? false : true;
    }

    public boolean hasIdologyErrors(List<ErrorBody> errors) {
        for (ErrorBody errorBody : errors) {
            if (!TextUtils.isEmpty(errorBody.getField()) && IdologyKnownFields.isValid(errorBody.getField())) {
                return true;
            }
        }
        return false;
    }

    public Observable<List<ErrorBody>> getFormErrors() {
        return this.mSubject;
    }

    public boolean filterErrorMessages(List<ErrorBody> errors) {
        if (errors == null || errors.isEmpty()) {
            return false;
        }
        List<ErrorBody> filteredErrors = new LinkedList();
        for (ErrorBody errorBody : errors) {
            if (this.mHandledFields.contains(errorBody.getField())) {
                filteredErrors.add(errorBody);
            }
        }
        if (!filteredErrors.isEmpty()) {
            this.mSubject.onNext(filteredErrors);
        }
        if (filteredErrors.isEmpty()) {
            return false;
        }
        return true;
    }
}
