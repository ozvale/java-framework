package com.ozvale.utils.id.generator;

import com.ozvale.utils.random.RandomStringGenerator;

/**
 * 随机ID生成器
 *
 * @author ozvale
 */
public class RandomIDGenerator extends RandomStringGenerator implements IDGenerator {

    public RandomIDGenerator(char[] chars) {
        super(chars);
    }

    public RandomIDGenerator(String string) {
        super(string);
    }
}
