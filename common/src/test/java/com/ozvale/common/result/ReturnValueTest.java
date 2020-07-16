package com.ozvale.common.result;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReturnValueTest {
    @Test
    public void create() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        Assert.assertTrue(returnValue.getSuccess());
    }

    @Test
    public void success() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        returnValue.success();
        Assert.assertTrue(returnValue.getSuccess());
    }

    @Test
    public void fail() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        returnValue.fail();
        Assert.assertFalse(returnValue.getSuccess());
    }

    @Test
    public void reset() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        returnValue.exception(new Exception("error"));
        returnValue.value("abc");
        returnValue.reset();
        Assert.assertTrue(returnValue.getSuccess());
        Assert.assertNull(returnValue.getError());
        Assert.assertNull(returnValue.getException());
        Assert.assertNull(returnValue.getValue());
    }

    @Test
    public void value() throws Exception {
        Object value = 1;
        ReturnValue returnValue = ReturnValue.create();
        returnValue.value(value);
        Assert.assertTrue(returnValue.getSuccess());
        Assert.assertEquals(value, returnValue.getValue());
    }

    @Test
    public void error() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        String error = "error";
        returnValue.error(error);
        Assert.assertFalse(returnValue.getSuccess());
        Assert.assertEquals(error, returnValue.getError());
    }

    @Test
    public void errorReturnValue() throws Exception {
        ReturnValue source = ReturnValue.create();
        ReturnValue target = ReturnValue.create();
        String error = "error";
        Exception exceptionNullMessage = new Exception();
        Exception exceptionWithMessage = new Exception(error);
        //error==null,exception==null
        source.reset();
        source.setError(null);
        source.setException(null);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertNull(target.getException());
        Assert.assertNull(target.getError());

        //error==null,exception==exceptionNullMessage
        source.reset();
        source.setError(null);
        source.setException(exceptionNullMessage);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertEquals(exceptionNullMessage.toString(), target.getError());
        Assert.assertEquals(exceptionNullMessage, target.getException());

        //error==null,exception==exceptionWithMessage
        source.reset();
        source.setError(null);
        source.setException(exceptionWithMessage);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertEquals(exceptionWithMessage.getMessage(), target.getError());
        Assert.assertEquals(exceptionWithMessage, target.getException());

        //error==error,exception==null
        source.reset();
        source.setError(error);
        source.setException(null);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertNull(target.getException());
        Assert.assertEquals(error, target.getError());

        //error==error,exception==exceptionNullMessage
        source.reset();
        source.setError(error);
        source.setException(exceptionNullMessage);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertEquals(error, target.getError());
        Assert.assertEquals(exceptionNullMessage, target.getException());

        //error==error,exception==exceptionWithMessage
        source.reset();
        source.setError(error);
        source.setException(exceptionWithMessage);
        source.fail();
        target.reset().error(source);
        Assert.assertFalse(target.getSuccess());
        Assert.assertEquals(error, target.getError());
        Assert.assertEquals(exceptionWithMessage, target.getException());
    }

    @Test
    public void exception() throws Exception {
        ReturnValue returnValue = ReturnValue.create();
        Exception exceptionNullMessage = new Exception();
        Exception exceptionWithMessage = new Exception("error");

        //exception==exceptionNullMessage
        returnValue.reset().exception(exceptionNullMessage);
        Assert.assertFalse(returnValue.getSuccess());
        Assert.assertEquals(exceptionNullMessage, returnValue.getException());
        Assert.assertEquals(exceptionNullMessage.toString(), returnValue.getError());

        //exception==exceptionWithMessage
        returnValue.reset().exception(exceptionWithMessage);
        Assert.assertFalse(returnValue.getSuccess());
        Assert.assertEquals(exceptionWithMessage, returnValue.getException());
        Assert.assertEquals(exceptionWithMessage.getMessage(), returnValue.getError());
    }

}