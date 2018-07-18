package com.coinbase.v1.entity;

import com.coinbase.v1.deserializer.ErrorsCollectorV1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ResponseV1 extends Response {
    private static final long serialVersionUID = -3392031826211907894L;
    private int _currentPage;
    private String _error;
    private String _errors;
    private int _numPages;
    private Boolean _success;
    private int _totalCount;

    public void setError(String error) {
        this._error = error;
    }

    public String getErrors() {
        if (this._error == null) {
            return this._errors;
        }
        if (this._errors != null) {
            return this._error + ", " + this._errors;
        }
        return this._error;
    }

    @JsonDeserialize(converter = ErrorsCollectorV1.class)
    public void setErrors(String errors) {
        this._errors = errors;
    }

    public int getTotalCount() {
        return this._totalCount;
    }

    public void setTotalCount(int totalCount) {
        this._totalCount = totalCount;
    }

    public int getNumPages() {
        return this._numPages;
    }

    public void setNumPages(int numPages) {
        this._numPages = numPages;
    }

    public int getCurrentPage() {
        return this._currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this._currentPage = currentPage;
    }

    public Boolean isSuccess() {
        return this._success;
    }

    public void setSuccess(Boolean success) {
        this._success = success;
    }

    public boolean hasErrors() {
        return (this._error == null && this._errors == null) ? false : true;
    }
}
