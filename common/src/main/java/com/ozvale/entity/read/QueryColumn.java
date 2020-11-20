package com.ozvale.entity.read;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface QueryColumn {
    //在列表查询中返回
    boolean include() default true;

    //作为排序字段
    boolean sort() default false;

    //排序顺序desc/asc
    String order() default "desc";

    //匹配模糊搜索
    boolean search() default false;

    //对应的数据库字段名称
    String name() default "";
}
