package com.ozvale.utils.id.generator;

/**
 * ID生成器
 *
 * @author ozvale
 */
public interface IDGenerator {

    /**
     * 生成一个指定长度的ID
     *
     * @param length
     * @return
     */
    String generate(int length);
}
