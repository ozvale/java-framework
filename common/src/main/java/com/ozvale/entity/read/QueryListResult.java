/**
 *
 */
package com.ozvale.entity.read;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询结果-列表
 *
 * @author ozvale
 */
public class QueryListResult<T> implements Serializable {

    /**
     * 数据列表
     */
    private List<T> rows = new ArrayList<>();

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 添加一行数据
     */
    public int push(T row) {
        rows.add(row);
        return rows.size();
    }

    /**
     * 当前数据缓存集合的条数
     */
    public int size() {
        return rows == null ? 0 : rows.size();
    }
}
