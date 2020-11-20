package com.ozvale.utils.random;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringGeneratorTest {
    @Test
    public void generate() throws Exception {
        char[] chars = "01".toCharArray();
        int length = 10;
        RandomStringGenerator generator = new RandomStringGenerator(chars);
        String string = generator.generate(length);
        //length equals
        Assert.assertEquals(string.length(), length);
    }
}