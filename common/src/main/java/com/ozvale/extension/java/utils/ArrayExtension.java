/**
 * 
 */
package com.ozvale.extension.java.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 数据列表拓展类
 * <p>
 * <Strong>Date: </Strong> 2014年12月11日 下午7:00:27
 * 
 * @author Spartacus
 * @param <E>
 */
public class ArrayExtension<E> implements Iterable<E> {

	private List<E> array;

	public ArrayExtension() {
		this.array = new ArrayList<E>();
	}

	public ArrayExtension(E[] array) {
		this.array = new ArrayList<E>();
		for (E e : array) {
			this.array.add(e);
		}
	}

	public ArrayExtension(Collection<E> array) {
		this.array = new ArrayList<E>(array);
	}

	@Override
	public Iterator<E> iterator() {
		return array.iterator();
	}

	/**
	 * 返回传入的对象数组转换的列表
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午4:27:26</h1>
	 * <p>
	 * 
	 * @return
	 */
	public List<E> getArray() {
		return array;
	}

	/**
	 * 添加元素
	 * <p>
	 * <h1>created by Spartacus at 2015年4月7日 上午9:33:21</h1>
	 * <p>
	 * 
	 * @param e
	 * @return
	 */
	public ArrayExtension<E> add(E e) {
		array.add(e);
		return this;
	}

	/**
	 * 添加元素集合
	 * <p>
	 * <h1>created by Spartacus at 2015年4月7日 上午9:33:21</h1>
	 * <p>
	 * 
	 * @param e
	 * @return
	 */
	public ArrayExtension<E> add(Collection<E> elements) {
		array.addAll(elements);
		return this;
	}

	/**
	 * 添加元素数组
	 * <p>
	 * <h1>created by Spartacus at 2015年4月7日 上午9:33:21</h1>
	 * <p>
	 * 
	 * @param e
	 * @return
	 */
	public ArrayExtension<E> add(E[] elements) {
		if (elements != null && elements.length > 0) {
			for (E e : elements) {
				array.add(e);
			}
		}
		return this;
	}

	/**
	 * 获取指定索引的元素
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午1:55:50</h1>
	 * <p>
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {
		return array.get(index);
	}

	/**
	 * 获取数组的首个元素.如果没有则返回空
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午1:58:11</h1>
	 * <p>
	 * 
	 * @return 数组的首个元素
	 */
	public E first() {
		return array.size() > 0 ? array.get(0) : null;
	}

	/**
	 * 获取数组的最后一个元素.如果没有则返回空
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午2:01:49</h1>
	 * <p>
	 * 
	 * @return 数组的最后一个元素
	 */
	public E last() {
		return array.size() > 0 ? array.get(array.size() - 1) : null;
	}

	/**
	 * 设置指定索引的元素
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午1:57:15</h1>
	 * <p>
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public ArrayExtension<E> set(int index, E element) {
		array.set(index, element);
		return this;
	}

	/**
	 * 获取当前数组的元素个数
	 * <p>
	 * <h1>created by Spartacus at 2015年7月8日 上午11:21:27</h1>
	 * <p>
	 * 
	 * @return
	 */
	public int size() {
		return array.size();
	}

	/**
	 * 返回一个字符串。该字符串是通过把 array的每个元素转换为字符串，然后把这些字符串连接起来，在两个元素之间插入 separator
	 * 字符串而生成的。
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午5:52:24</h1>
	 * <p>
	 * 
	 * @param separator
	 * @return
	 */
	public String join(String separator) {
		return join(array, separator);
	}

	/**
	 * 将此对象数组的所有值替换为指定值.返回一个新的对象。不影响之前的对象
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午5:46:11</h1>
	 * <p>
	 * 
	 * @param array
	 * @param replace
	 * @param separator
	 * @return
	 */
	public <T> ArrayExtension<T> replaceAll(T replace) {
		return replaceAll(array, replace);
	}

	/**
	 * 是否包含元素
	 * <p>
	 * <h1>created by Spartacus at 2015年4月7日 上午9:45:20</h1>
	 * <p>
	 * 
	 * @param e
	 * @return
	 */
	public boolean contains(E e) {
		return this.array.contains(e);
	}

	/**
	 * 移除数组的所有元素
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午2:04:15</h1>
	 * <p>
	 * 
	 * @return
	 */
	public ArrayExtension<E> clear() {
		this.array.clear();
		return this;
	}

	/**
	 * 数组是否不包含任何元素
	 * <p>
	 * <h1>created by Spartacus at 2015年7月29日 下午2:05:09</h1>
	 * <p>
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.array.isEmpty();
	}

	/**
	 * 循环处理元素
	 * <p>
	 * <h1>created by Spartacus at 2015年4月10日 下午7:50:40</h1>
	 * <p>
	 * 
	 * @param excutor
	 */
	public void loop(IReWriter<E> excutor) {
		for (int i = 0; i < this.getArray().size(); i++) {
			this.getArray().set(i, excutor.reWrite(this.getArray().get(i)));
		}
	}

	/**
	 * 转为object数组
	 * <p>
	 * <h1>created by Spartacus at 2015年4月29日 下午8:04:20</h1>
	 * <p>
	 * 
	 * @return
	 */
	public Object[] toArray() {
		return this.getArray().toArray();
	}

	/**
	 * 替换数组所有元素为指定值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月29日 下午8:35:42</h1>
	 * <p>
	 * 
	 * @param array
	 * @param relpace
	 * @return
	 */
	public static <T> ArrayExtension<T> replaceAll(Object[] array, T replace) {
		ArrayExtension<T> _array = new ArrayExtension<T>();
		for (int i = 0; i < array.length; i++) {
			_array.add(replace);
		}
		return _array;
	}

	/**
	 * 替换集合所有元素为指定值
	 * <p>
	 * <h1>created by Spartacus at 2015年4月29日 下午8:35:42</h1>
	 * <p>
	 * 
	 * @param array
	 * @param relpace
	 * @return
	 */
	public static <T> ArrayExtension<T> replaceAll(Iterable<?> iterable, T replace) {
		ArrayExtension<T> array = new ArrayExtension<T>();
		Iterator<?> it = iterable.iterator();
		while (it.hasNext()) {
			array.add(replace);
			it.next();
		}
		return array;
	}

	/**
	 * 将数组转为 字符串 ,separator符号隔开
	 * 
	 * @authoor wuzhiwen 2014年4月11日 下午1:42:53
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		StringBuffer _sb = new StringBuffer();
		for (Object o : array) {
			_sb.append(o.toString()).append(separator);
		}
		return _sb.substring(0, _sb.lastIndexOf(separator));
	}

	/**
	 * 将集合转为 字符串 ,separator符号隔开
	 * 
	 * @authoor wuzhiwen 2014年4月11日 下午1:42:53
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String join(Iterable<?> iterable, String separator) {
		StringBuffer _sb = new StringBuffer();
		for (Object o : iterable) {
			_sb.append(o.toString()).append(separator);
		}
		return _sb.substring(0, _sb.lastIndexOf(separator));
	}

	/**
	 * 返回目标对象在指定数组中的索引。如果没有包含在指定数组中。则返回-1
	 * <p>
	 * <h1>created by Spartacus at 2015年8月5日 上午10:15:36</h1>
	 * <p>
	 * 
	 * @param array
	 * @param obj
	 * @return
	 */
	public static int indexOf(Object[] array, Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(obj)) {
				return i;
			}
			if (array[i] == null && obj == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取对象数组的类数组
	 * <p>
	 * <h1>created by Spartacus at 2014年8月26日 下午3:26:52</h1>
	 * <p>
	 * 
	 * @param args
	 * @return
	 */
	public static Class<?>[] getTypeArray(Object... args) {
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			params[i] = args[i].getClass();
		}
		return params;
	}

	/**
	 * 元素重写器
	 * <p>
	 * <Strong>Date: </Strong> 2015年4月10日 下午7:38:00
	 * 
	 * @author Spartacus
	 */
	public interface IReWriter<T> {
		T reWrite(T element);
	}
}
