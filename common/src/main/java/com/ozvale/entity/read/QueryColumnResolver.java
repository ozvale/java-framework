package com.ozvale.entity.read;


import com.ozvale.utils.lang.ClassUtils;
import com.ozvale.utils.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueryColumnResolver {

    private static Map<Class<?>, QueryColumnResolver> cache = new HashMap<>();
    private Class<?> clazz;
    //列表查询字段
    private Set<String> includes = new HashSet<>();
    //排序字段
    private Map<String, String> sorts = new HashMap<>();
    //模糊查询字段
    private Set<String> searches = new HashSet<>();

    public QueryColumnResolver(Class<?> clazz) {
        this.clazz = clazz;
        this.resolve();
    }

    public static <T> QueryColumnResolver resolve(Class<T> clazz) {
        QueryColumnResolver resolver = cache.get(clazz);
        if (resolver == null) {
            resolver = new QueryColumnResolver(clazz);
            cache.put(clazz, resolver);
        }
        return resolver;
    }

    //解析
    private void resolve() {
        Set<Field> fields = ClassUtils.getAllDeclaredFields(clazz);
        for (Field field : fields) {
            String name = field.getName();
            QueryColumn queryColumn = field.getAnnotation(QueryColumn.class);
            if (queryColumn != null && !StringUtils.isNullOrEmpty(queryColumn.name())) {
                name = queryColumn.name();
            }
            if (queryColumn == null || queryColumn.include()) {
                includes.add(name);
            }
            if (queryColumn != null && queryColumn.sort()) {
                sorts.put(name, queryColumn.order());
            }
            if (queryColumn != null && queryColumn.search()) {
                searches.add(name);
            }
        }
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public Map<String, String> getSorts() {
        return sorts;
    }

    public Set<String> getSearches() {
        return searches;
    }
}
