package com.ozvale.entity.read;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据列表查询通用参数对象
 *
 * @author ozvale
 */
public class QueryParameter implements Serializable {
    /**
     * 查询返回的最大数据条数,默认为10,-1(负数)表示不限制
     */
    private Integer limit = 10;

    /**
     * 查询时从第几条开始读取.一般为limit*(index-1)
     */
    private Integer offset;

    /**
     * 分页查询时的页数
     */
    private Integer pageIndex;

    /**
     * 排序规则asc/desc
     */
    private String order;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 要查询的字段(多个逗号隔开)
     */
    private String columns;

    /**
     * 关键字
     */
    private String search;
    private Set<String> columnArray;

    public Integer getLimit() {
        //默认查10条
        return limit == null ? 10 : limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset == null ? 0 : offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 只允许asc或desc，默认desc
     */
    public String getOrder() {
        if (order != null && !order.equals("")) {
            if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
                order = "desc";
            }
        } else {
            return "desc";
        }
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public Set<String> getColumnArray() {
        return columnArray;
    }

    public void setColumnArray(Set<String> columnArray) {
        this.columnArray = columnArray;
    }

    /**
     * 是否可以作为分页排序等查询参数
     */
    public boolean pageable() {
        return getLimit() != null && getLimit() > 0;
    }

    /**
     * 是否可以排序
     */
    public boolean sortable() {
        if (sort != null && !sort.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取要查询的对象字段名称数组
     */
    public Set<String> getQueryColumns() {
        if (columnArray == null) {
            columnArray = new HashSet<>();
            if (columns != null && !columns.trim().isEmpty()) {
                String[] array = columns.split(",");
                for (String s : array) {
                    columnArray.add(s);
                }
            }
        }
        return columnArray;
    }

    // 合法字段命名,屏蔽非法字符
    private boolean isLegalColumn(String column) {
        return column == null ? true : column.matches("^[\\w\\,\\.\\[\\]]+$");
    }
}
