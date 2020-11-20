package com.ozvale.http.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Http一般响应对象封装
 *
 * @author ozvale
 */
public class HttpResponse implements Serializable {
    /**
     * 默认成功状态码
     */
    public static Integer DEFAULT_SUCCESS_CODE = 200;
    /**
     * 默认失败的状态码
     */
    public static Integer DEFAULT_ERROR_CODE = 400;

    private Integer code;
    private Map<String, Object> data = new HashMap<>();
    private String error;
    private String message;
    private Throwable exception;


    public HttpResponse() {
        code(DEFAULT_SUCCESS_CODE);
    }

    public static HttpResponse create() {
        return new HttpResponse();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpResponse data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public HttpResponse error(String error) {
        setError(error);
        code(DEFAULT_ERROR_CODE);
        return this;
    }

    public HttpResponse exception(Throwable exception) {
        if (exception == null) return this;
        setException(exception);
        code(DEFAULT_ERROR_CODE);
        if (getError() == null) {
            setError(exception.getMessage());
        }
        return this;
    }

    public HttpResponse message(String message) {
        setMessage(message);
        return this;
    }

    public HttpResponse code(Integer code) {
        setCode(code);
        return this;
    }

    public HttpResponse reset() {
        setCode(DEFAULT_SUCCESS_CODE);
        setMessage(null);
        setError(null);
        setException(null);
        getData().clear();
        return this;
    }
}
