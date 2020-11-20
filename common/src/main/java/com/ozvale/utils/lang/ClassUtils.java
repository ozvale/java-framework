/**
 *
 */
package com.ozvale.utils.lang;

import com.ozvale.extension.java.utils.ArrayExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassExtension
 * <p>
 * <Strong>Date: </Strong> 2014年10月10日 上午10:32:15
 *
 * @author Spartacus
 */
public class ClassUtils {

    /**
     * 判断clazz是否实现了superInterfaceClass接口（父类实现也返回true）
     * <p>
     * <h1>created by Spartacus at 2014年10月10日 上午10:34:06</h1>
     * <p>
     *
     * @param superClass
     * @param clazz
     * @return
     */
    public static boolean isSuperInterface(Class<?> superInterfaceClass, Class<?> clazz) {
        Class<?> up = clazz;
        while (up != null && !up.equals(Object.class)) {
            if (up.equals(superInterfaceClass)) {
                return true;
            }
            for (Class<?> inter : up.getInterfaces()) {
                if (isSuperInterface(superInterfaceClass, inter)) {
                    return true;
                }
            }
            up = up.getSuperclass();
        }
        return false;
    }

    /**
     * 返回接口实现深度
     * <p>
     * <h1>created by Spartacus at 2014年11月3日 下午8:06:36</h1>
     * <p>
     *
     * @param superInterfaceClass
     * @param clazz
     * @return
     */
    public static int superInterfaceDepth(Class<?> superInterfaceClass, Class<?> clazz) {
        int depth = 0;
        Class<?> up = clazz;
        while (up != null && !up.equals(Object.class)) {
            if (up.equals(Object.class)) {
                depth = -1;
                break;
            }
            if (up.equals(superInterfaceClass)) {
                return depth;
            }
            for (Class<?> inter : up.getInterfaces()) {
                int dep = superInterfaceDepth(superInterfaceClass, inter);
                if (dep >= 0) {
                    return depth + dep;
                }
            }
            up = up.getSuperclass();
            depth++;
        }
        return depth;
    }

    /**
     * 返回类继承深度
     * <p>
     * <h1>created by Spartacus at 2014年11月3日 下午8:06:45</h1>
     * <p>
     *
     * @param superClass
     * @param clazz
     * @return
     */
    public static int superClassDepth(Class<?> superClass, Class<?> clazz) {
        int depth = 0;
        Class<?> up = clazz;
        while (up != null) {
            if (up.equals(superClass)) {
                return depth;
            }
            if (up.equals(Object.class)) {

                return -1;
            } else {
                up = up.getSuperclass();
                depth++;
            }
        }
        return depth;
    }

    /**
     * 判断clazz是否是superClass的子类或本身
     * <p>
     * <h1>created by Spartacus at 2014年10月11日 上午11:31:41</h1>
     * <p>
     *
     * @param superClass
     * @param clazz
     * @return
     */
    public static boolean isSuperClass(Class<?> superClass, Class<?> clazz) {
        Class<?> up = clazz;
        while (up != null && !up.equals(Object.class)) {
            if (up.equals(superClass)) {
                return true;
            }
            up = up.getSuperclass();
        }
        return false;
    }

    /**
     * 获取类实现的接口,包含父类接口
     * <p>
     * <h1>created by Spartacus at 2014年8月26日 下午5:18:03</h1>
     * <p>
     *
     * @param clazz
     * @return
     */

    public static Set<Class<?>> getAllInterfaces(Class<?> clazz) {
        Set<Class<?>> list = new HashSet<Class<?>>();
        Class<?> up = clazz;
        while (up != null && !up.equals(Object.class)) {
            if (up.isInterface()) {
                list.add(up);
            }
            for (Class<?> in : up.getInterfaces()) {
                list.addAll(getAllInterfaces(in));
            }
            up = up.getSuperclass();
        }
        return list;
    }

    /**
     * 使用实现类直接创建对象
     * <p>
     * <h1>created by Spartacus at 2014年10月14日 下午5:56:01</h1>
     * <p>
     *
     * @param implClazz
     * @param objects
     * @return
     */
    public static <T> T createInstance(Class<? extends T> implClazz, Object... objects) throws Exception {
        Class<?>[] params = ArrayExtension.getTypeArray(objects);
        Constructor<? extends T> con = implClazz.getConstructor(params);
        return (T) con.newInstance(objects);
    }

    /**
     * 获取实现类的深度
     * <p>
     * <h1>created by Spartacus at 2014年11月3日 下午7:10:41</h1>
     * <p>
     *
     * @param superClass
     * @param clazz
     * @return
     */
    public static int getClassDepth(Class<?> superClass, Class<?> clazz) {
        int classDepth = superClassDepth(superClass, clazz);
        int interfaceDepth = superInterfaceDepth(superClass, clazz);
        if (classDepth != -1 && interfaceDepth != -1) {
            return classDepth <= interfaceDepth ? classDepth : interfaceDepth;
        } else {
            return classDepth >= interfaceDepth ? classDepth : interfaceDepth;
        }
    }

    /**
     * 获取类的公共方法(包含父类).如果有多个合适的方法，则匹配参数最接近的方法(声明参数类型与传入参数最接近)
     * <p>
     * <h1>created by Spartacus at 2014年11月3日 下午7:32:28</h1>
     * <p>
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
        Method method = null;
        Method[] mArray = clazz.getMethods();
        List<Method> matchMethods = new ArrayList<>();
        if (mArray != null && mArray.length > 0) {
            for (Method m : mArray) {
                if (m.getName().equals(methodName)) {
                    Class<?>[] paramTypes = m.getParameterTypes();
                    // 无参函数
                    if (parameterTypes == null || parameterTypes.length == 0) {
                        if (paramTypes.length == 0) {
                            method = m;
                            break;
                        }
                    }
                    // 有参函数
                    else {
                        // 参数个数是否相等
                        if (parameterTypes.length == paramTypes.length) {
                            boolean match = false;
                            for (int i = 0; i < paramTypes.length; i++) {
                                // 输出参数是函数参数的子类或实现类
                                if (ClassUtils.isSuperClass(paramTypes[i], parameterTypes[i]) || ClassUtils.isSuperInterface(paramTypes[i], parameterTypes[i])) {
                                    match = true;
                                    continue;
                                } else {
                                    match = false;
                                    break;
                                }
                            }
                            // 匹配则添加
                            if (match) {
                                matchMethods.add(m);
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            }

            // 对符合条件的方法进行筛选
            if (matchMethods.size() == 0) {
                return null;
            } else if (matchMethods.size() == 1) {
                method = matchMethods.get(0);
            }
            // 至少有两个方法。需判断参数类的权值，越靠近方法声明类并且位置越靠前的优先返回
            else {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < parameterTypes.length; i++) {
                    int size = matchMethods.size();
                    for (int j = 0; j < size; j++) {
                        // 传入参数类与匹配方法参数类的实现深度
                        int depth = getClassDepth(matchMethods.get(j).getParameterTypes()[i], parameterTypes[i]);
                        if (depth != -1 && depth <= min) {
                            min = depth;
                        } else {
                            matchMethods.remove(j);
                        }
                    }
                    // 已经找到了最匹配方法
                    if (matchMethods.size() == 1) {
                        method = matchMethods.get(0);
                    }
                }
                // 如果还无法区分。如参数是两个接口的直接实现类，则抛出异常
                if (matchMethods.size() > 1) {
                    throw new RuntimeException("无法精确匹配到要调用的方法" + matchMethods.get(0).getReturnType().getName() + "." + matchMethods.get(0).getName());
                }
            }
        }
        return method;
    }

    /**
     * 获取所有声明的字段，包含父类
     *
     * @param clazz
     * @return
     */
    public static Set<Field> getAllDeclaredFields(Class<?> clazz) {
        Set<Field> fields = new HashSet<>();
        Class<?> up = clazz;
        while (up != null && !up.equals(Object.class)) {
            for (Field f : up.getDeclaredFields()) {
                fields.add(f);
            }
            up = up.getSuperclass();
        }
        return fields;
    }

}
