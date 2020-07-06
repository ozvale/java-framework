package com.ozvale.common.result;

/**
 * 返回值封装
 */
public class ReturnValue<T> {

    private Boolean success = true;

    private String error;

    private Exception exception;

    private T value;

    public static <T> ReturnValue<T> create() {
        return new ReturnValue<T>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ReturnValue<T> fail() {
        this.setSuccess(false);
        return this;
    }

    public ReturnValue<T> error(String error) {
        this.setError(error);
        this.setSuccess(false);
        return this;
    }

    public ReturnValue<T> error(ReturnValue returnValue) {
        this.setException(returnValue.getException());
        this.setError(returnValue.getError());
        this.setSuccess(false);
        return this;
    }

    public ReturnValue<T> value(T value) {
        this.setValue(value);
        return this;
    }

    public ReturnValue<T> exception(Exception exception) {
        if (exception == null) return this;
        this.setException(exception);
        if (this.getError() == null) {
            this.setError(exception.getMessage());
        }
        this.setSuccess(false);
        return this;
    }
}
