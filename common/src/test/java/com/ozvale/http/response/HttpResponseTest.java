package com.ozvale.http.response;


import org.junit.Assert;
import org.junit.Test;

public class HttpResponseTest {

    @Test
    public void code() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();

        //default success code
        Assert.assertEquals(HttpResponse.DEFAULT_SUCCESS_CODE, httpResponse.getCode());
        //custom code
        int code = 100;
        Assert.assertTrue(code == httpResponse.code(code).getCode());
    }

    @Test
    public void message() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();

        //message
        String message = "123";
        Assert.assertEquals(message, httpResponse.message(message).getMessage());
    }

    @Test
    public void error() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();

        String error = "error";
        httpResponse.error(error);
        Assert.assertEquals(HttpResponse.DEFAULT_ERROR_CODE, httpResponse.getCode());
        Assert.assertEquals(error, httpResponse.getError());
    }

    @Test
    public void exception() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();

        String error = "error";
        Throwable throwable = new Exception(error);
        httpResponse.exception(throwable);
        Assert.assertNotNull(httpResponse.getException());
        Assert.assertEquals(error, httpResponse.getError());
    }

    @Test
    public void data() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();

        String key = "n";
        String value = "v";
        httpResponse.data(key, value);
        Assert.assertEquals(value, httpResponse.getData().get(key));
    }

    @Test
    public void reset() throws Exception {
        HttpResponse httpResponse = HttpResponse.create();
        httpResponse.data("k", "v").message("123").exception(new Exception("error"));

        Assert.assertNotNull(httpResponse.getCode());
        Assert.assertNotNull(httpResponse.getException());
        Assert.assertNotNull(httpResponse.getError());
        Assert.assertNotNull(httpResponse.getData());
        Assert.assertNotNull(httpResponse.getMessage());

        httpResponse.reset();
        Assert.assertEquals(HttpResponse.DEFAULT_SUCCESS_CODE, httpResponse.getCode());
        Assert.assertNull(httpResponse.getException());
        Assert.assertNull(httpResponse.getError());
        Assert.assertTrue(httpResponse.getData().isEmpty());
        Assert.assertNull(httpResponse.getMessage());
    }
}
