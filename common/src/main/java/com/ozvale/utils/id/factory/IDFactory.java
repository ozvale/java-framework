package com.ozvale.utils.id.factory;

import com.ozvale.utils.id.generator.IDGenerator;
import com.ozvale.utils.id.generator.RandomIDGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * ID 生产管理工厂
 *
 * @author ozvale
 */
public class IDFactory {
    /**
     * 已生成的id列表
     */
    private Set<String> ids = new HashSet<>();

    /**
     * id生成器
     */
    private IDGenerator idGenerator;

    /**
     * 子类需要实例化此构造函数
     *
     * @param idGenerator
     */
    protected IDFactory(RandomIDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * 生成的id列表中是否已包含id
     *
     * @param id
     * @return
     */
    public boolean contains(String id) {
        return this.ids.contains(id);
    }

    /**
     * 添加已有id,以防止生成相同id
     *
     * @param id
     * @return
     */
    public void push(String id) {
        if (id != null && !this.contains(id)) {
            this.ids.add(id);
        }
    }

    /**
     * 生成一个指定长度的id
     *
     * @param length 要生成id的长度
     * @return
     */
    public String create(int length) {
        String id;
        do {
            id = idGenerator.generate(length);
        }
        while (this.contains(id));
        this.push(id);
        return id;
    }
}
