package com.ozvale.utils.random;

import java.util.Random;

/**
 * 随机字符串生成器基类
 *
 * @author ozvale
 */
public class RandomStringGenerator {
    private Random random = new Random();
    /**
     * 可选的字符元素
     */
    private char[] chars;

    public RandomStringGenerator(char[] chars) {
        this.chars = chars;
    }

    public RandomStringGenerator(String string) {
        this.chars = string.toCharArray();
    }

    /**
     * 生成指定长度的字符串
     *
     * @param length,必须大于0
     * @return
     */
    public String generate(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < length) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
            i++;
        }
        return stringBuilder.toString();
    }
}
