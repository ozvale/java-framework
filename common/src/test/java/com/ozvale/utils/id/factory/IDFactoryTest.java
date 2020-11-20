package com.ozvale.utils.id.factory;

import com.ozvale.utils.id.generator.RandomIDGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IDFactoryTest {
    private IDFactory factory;

    @Test
    public void contains() throws Exception {
        String id = factory.create(2);
        Assert.assertTrue(factory.contains(id));
    }

    @Test
    public void push() throws Exception {
        String id = "123";
        factory.push(id);
        Assert.assertTrue(factory.contains(id));
    }

    @Test
    public void create() throws Exception {
        factory.push("0");
        String id = factory.create(1);
        Assert.assertEquals("1", id);
    }

    @Before
    public void setUp() throws Exception {
        factory = new IDFactory(new RandomIDGenerator("01"));
    }
}