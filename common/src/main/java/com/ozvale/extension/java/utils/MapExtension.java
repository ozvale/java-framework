/**
 * 
 */
package com.ozvale.extension.java.utils;

import com.ozvale.utils.date.DateUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * MapExtension
 * <p>
 * <Strong>Date: </Strong> 2014年12月2日 下午1:36:01
 * 
 * @author Spartacus
 */
public class MapExtension<K, V> {

	private Map<K, V> map;

	public MapExtension(Map<K, V> map) {
		this.map = map;
	}

	/**
	 * 原始map
	 * <p>
	 * <h1>created by Spartacus at 2014年12月2日 下午2:24:02</h1>
	 * <p>
	 * 
	 * @return
	 */
	public Map<K, V> map() {
		return this.map();
	}

	/**
	 * 忽略大小写取值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:22:34</h1>
	 * <p>
	 * 
	 * @param key
	 * @return
	 */
	private Object getObjectIgnoreCase(String key) {
		Object _value = null;
		for (Object k : map.keySet()) {
			if (k != null && k instanceof String && k.toString().equalsIgnoreCase(key)) {
				_value = map.get(k);
				break;
			}
		}
		return _value;
	}

	/**
	 * 取值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:22:56</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Object getObject(K key, MapValueGetterOption... options) {
		Object value = map.get(key);
		if (options != null && options.length > 0) {
			// 取值设置
			ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
			// 值不能为空
			if (optionArray.contains(MapValueGetterOption.NotAllowedNullValue)) {
				if (value == null) {
					throw new NullPointerException("在map中key " + key + "的值不能为空");
				}
			}
			// 忽略大小写取值
			if (optionArray.contains(MapValueGetterOption.GetValueIgnoreCase)) {
				if (key != null && key instanceof String)
					value = this.getObjectIgnoreCase((String) key);
			}
		}
		return value;
	}

	/**
	 * 取 String 值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:36:06</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public String getString(K key, MapValueGetterOption... options) {
		Object value = this.getObject(key, options);
		if (options != null && options.length > 0) {
			// 取值设置
			ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
			// 值不能为空字符串
			if (optionArray.contains(MapValueGetterOption.NotAllowedEmpty)) {
				if (value == null || value.equals("")) {
					throw new RuntimeException("在map中key " + key + "的值不能为空字符串");
				}
			}
			// 值不能为空白字符串
			if (optionArray.contains(MapValueGetterOption.NotAllowedBlank)) {
				if (value == null || value != null && value.toString().trim().equals("")) {
					throw new RuntimeException("在map中key " + key + "的值不能为空白字符串");
				}
			}
		}
		return String.valueOf(value);
	}

	/**
	 * 取整数值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:36:28</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Integer getInteger(K key, MapValueGetterOption... options) {
		Object value = this.getObject(key, options);
		Integer num = null;
		if (value != null) {
			// 是数字
			if (value instanceof Integer) {
				num = (Integer) value;
			}
			// 不是数字
			else {
				try {
					num = Integer.parseInt(value.toString());
				}
				catch (Exception e) {
					if (options != null && options.length > 0) {
						// 取值设置
						ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
						// 转换数字失败返回0
						if (optionArray.contains(MapValueGetterOption.ParseFailedReturnZero)) {
							num = 0;
						}
						// 转数字失败返回null，不抛出异常
						else if (!optionArray.contains(MapValueGetterOption.ParseFailedReturnNull)) {
							throw e;
						}
					}
				}
			}
		}
		return num;
	}

	/**
	 * 取长整数值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:36:28</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Long getLong(K key, MapValueGetterOption... options) {
		Object value = this.getObject(key, options);
		Long num = null;
		if (value != null) {
			// 是数字
			if (value instanceof Long) {
				num = (Long) value;
			}
			// 不是数字
			else {
				try {
					num = Long.parseLong(value.toString());
				}
				catch (Exception e) {
					if (options != null && options.length > 0) {
						// 取值设置
						ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
						// 转换数字失败返回0
						if (optionArray.contains(MapValueGetterOption.ParseFailedReturnZero)) {
							num = new Long(0);
						}
						// 转数字失败返回null，不抛出异常
						else if (!optionArray.contains(MapValueGetterOption.ParseFailedReturnNull)) {
							throw e;
						}
					}
				}
			}
		}
		return num;
	}

	/**
	 * 取布尔值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:57:33</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public Boolean getBoolean(K key, MapValueGetterOption... options) {

		Object value = this.getObject(key, options);

		Boolean _v = null;

		if (value != null) {
			if (value instanceof Boolean) {
				_v = (Boolean) value;
			} else {
				_v = Boolean.parseBoolean(value.toString());
			}
		} else {
			if (options != null && options.length > 0) {
				// 取值设置
				ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
				// 如果取到的结果为null则自动转为false
				if (optionArray.contains(MapValueGetterOption.ReturnFalseIfNull)) {
					_v = false;
				}
			}
		}
		return _v;
	}

	/**
	 * 取 Map 值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午3:21:32</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <KEY, VALUE> Map<KEY, VALUE> getMap(K key, MapValueGetterOption... options) {
		Object value = this.getObject(key, options);
		Map<KEY, VALUE> _v = null;

		if (value != null) {
			if (value instanceof Map) {
				_v = (Map<KEY, VALUE>) value;
			} else {
				throw new RuntimeException("Map类型不匹配");
			}
		} else {
			if (options != null && options.length > 0) {
				// 取值设置
				ArrayExtension<MapValueGetterOption> optionArray = new ArrayExtension<>(options);
				// 如果取到的结果为null则返回一个空hashMap
				if (optionArray.contains(MapValueGetterOption.ReturnEmptyMapIfNull)) {
					_v = new HashMap<>();
				}
			}
		}
		return _v;
	}

	/**
	 * 取 MapExtension 值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午3:23:43</h1>
	 * <p>
	 * 
	 * @param key
	 * @param options
	 * @return
	 */
	public <KEY, VALUE> MapExtension<KEY, VALUE> getMapExtension(K key, MapValueGetterOption... options) {
		Map<KEY, VALUE> _v = getMap(key, options);
		return _v == null ? null : new MapExtension<>(_v);
	}

	/**
	 * 将 map 反序列化为对象
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午3:25:47</h1>
	 * <p>
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> T deserialize(Class<T> clazz, Map<String, Object> map) {
		T _t = null;
		try {
			_t = clazz.newInstance();

			List<Field> _fieldArray = new ArrayList<Field>();

			// 添加本类的所有属性
			for (Field f : clazz.getDeclaredFields()) {

				int _mod = f.getModifiers();
				// final修饰符的不赋值
				if (Modifier.isFinal(_mod)) {
					continue;
				}

				// 判断是否需要跳过
				if (f.getAnnotation(Skip.class) != null) {
					continue;
				}

				_fieldArray.add(f);
			}
			// 添加继承的类的所有属性
			Class<?> _super = clazz;
			while (!(_super = _super.getSuperclass()).equals(Object.class)) {
				for (Field f : _super.getDeclaredFields()) {
					int _mod = f.getModifiers();
					// final修饰符的不赋值
					if (Modifier.isFinal(_mod)) {
						continue;
					}
					// 判断是否需要跳过
					if (f.getAnnotation(Skip.class) != null) {
						continue;
					}

					_fieldArray.add(f);
				}
			}

			// 所有属性
			for (Field f : _fieldArray) {
				// 要转换的所有key
				for (Object key : map.keySet()) {
					if (f.getName().equalsIgnoreCase(key.toString())) {
						f.setAccessible(true);
						Object _value = map.get(key);

						// 为空时
						if (_value == null) {
							// 整形不能为空，默认置为-1
							if (f.getType().equals(int.class)) {
								f.set(_t, -1);
							} else if (f.getType().equals(boolean.class)) {
								// boolean 默认置为false
								f.set(_t, false);
							} else {
								f.set(_t, null);
							}
							// 找到匹配字段则跳出，开始找下一个字段
							break;
						}

						// 如果类型匹配则直接赋值
						if (f.getType().equals(_value.getClass())) {
							f.set(_t, _value);
						}
						// 否则需要转换
						else {

							// String
							if (f.getType().equals(String.class)) {
								f.set(_t, _value.toString());
							}

							// Integer
							else if (f.getType().equals(Integer.class)) {
								try {
									f.set(_t, Integer.parseInt(_value.toString()));
								}
								catch (Exception e) {
									// 转换失败设置为null
									f.set(_t, null);
									e.printStackTrace();
								}
							}
							// Date
							else if (f.getType().equals(Date.class)) {
								try {
									f.set(_t, DateUtils.parse(_value.toString()));
								}
								catch (Exception e) {
									f.set(_t, null);
									e.printStackTrace();
								}
							}

							// Boolean
							else if (f.getType().equals(Boolean.class) || f.getType().equals(boolean.class)) {
								try {
									f.set(_t, Boolean.parseBoolean(_value.toString()));
								}
								catch (Exception e) {
									f.set(_t, null);
									e.printStackTrace();
								}
							}
							// Enum
							else if (f.getType().isEnum()) {
								Class<? extends Enum> type = (Class<? extends Enum>) f.getType();
								try {
									f.set(_t, Enum.valueOf(type, _value.toString()));
								}
								catch (Exception e) {
									f.set(_t, null);
									e.printStackTrace();
								}
							}
							// 其他，待扩充
							else {
								// 跳过，不进行转换
								// f.set(_t, _value);
							}
						}
					} else {
						continue;
					}
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return _t;
	}

	/**
	 * 将list<Map>反序列化为对象
	 * <p>
	 * <h1>created by Spartacus at 2014年11月21日 下午4:19:31</h1>
	 * <p>
	 *
	 * @param clazz
	 * @param mapList
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> deserialize(Class<T> clazz, Collection<Map<String, Object>> mapCollection) {
		List<T> list = new ArrayList<T>();
		for (Map<String, Object> m : mapCollection) {
			T _t = deserialize(clazz, m);
			list.add(_t);
		}
		return list;
	}

	/**
	 * 将对象转为map
	 * <p>
	 * <h1>created by Spartacus at 2014年10月22日 上午11:35:28</h1>
	 * <p>
	 *
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> serialize(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Class<?> clazz = obj.getClass();

			List<Field> _fieldArray = new ArrayList<Field>();

			// 添加本类的所有属性
			for (Field f : clazz.getDeclaredFields()) {

				int _mod = f.getModifiers();
				// final修饰符的不赋值
				if (Modifier.isFinal(_mod)) {
					continue;
				}
				_fieldArray.add(f);
			}
			// 添加继承的类的所有属性
			Class<?> _super = clazz;
			while (!(_super = _super.getSuperclass()).equals(Object.class)) {
				for (Field f : _super.getDeclaredFields()) {
					int _mod = f.getModifiers();
					// final修饰符的不赋值
					if (Modifier.isFinal(_mod)) {
						continue;
					}
					_fieldArray.add(f);
				}
			}

			// 所有属性
			for (Field f : _fieldArray) {
				// 判断该字段是否有get方法。如果有则添加。没有则不添加
				Method readMethod = null;
				try {
					String name = f.getName();
					// 处理以is开头的boolean字段
					if (f.getName().startsWith("is") && f.getType().equals(boolean.class)) {
						name = name.substring(2);
					}
					PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
					readMethod = pd.getReadMethod();
				}
				catch (Exception e) {
				}
				if (readMethod != null) {
					f.setAccessible(true);
					map.put(f.getName(), f.get(obj));
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	/**
	 * MapExtension 方法的取值扩展参数
	 * <p>
	 * <Strong>Date: </Strong> 2014年12月2日 下午1:48:45
	 * 
	 * @author Spartacus
	 */
	public enum MapValueGetterOption {

		/**
		 * 不允许取值为空。如果为空则抛出异常
		 */
		NotAllowedNullValue,
		/**
		 * 不允许字符串为空
		 */
		NotAllowedEmpty,
		/**
		 * 不允许字符串为空白
		 */
		NotAllowedBlank,
		/**
		 * 转换为String,Integer,Boolean...等类型失败时不抛出异常，直接返回null
		 */
		ParseFailedReturnNull,
		/**
		 * 转换为Integer失败时返回0
		 */
		ParseFailedReturnZero,
		/**
		 * 获取Boolean如果为空，则返回false
		 */
		ReturnFalseIfNull,
		/**
		 * 获取map如果为空，则返回空的map
		 */
		ReturnEmptyMapIfNull,
		/**
		 * 忽略key大小写取值.只支持key类型为String
		 */
		GetValueIgnoreCase,
	}

	/**
	 * 表示在map经过反序列化成对象时忽略 的属性
	 * <p>
	 * <Strong>Date: </Strong> 2014年10月31日 下午2:22:07
	 * 
	 * @author Spartacus
	 */
	@Target({ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Inherited
	public @interface Skip {
	}
}
