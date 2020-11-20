package com.ozvale.utils.random;

/**
 * 基于0-9，a-Z的一般id生成器
 *
 * @author ozvale
 */
public class RandomStringGeneratorOfLetterAndNumber extends RandomStringGenerator {

    public RandomStringGeneratorOfLetterAndNumber() {
        super("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }
}
