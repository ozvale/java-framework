/**
 *
 */
package com.ozvale.entity.read;

/**
 * 列表查询结果-分页查询
 *
 * @author ozvale
 */
public class QueryPageResult<T> extends QueryListResult<T> {

    /**
     * 总条数
     */
    private long total;


    public long getTotal() {
        if (total == 0) {
            return size();
        }
        return total;
    }

    public QueryPageResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }
}
