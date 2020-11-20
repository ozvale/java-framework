package com.ozvale.utils.lang;

import java.io.*;

/**
 * ObjectExtension
 * <p>
 * <Strong>Date: </Strong> 2015年9月7日 下午1:39:07
 * 
 * @author Spartacus
 */
public class ObjectUtils {

	/**
	 * 深度克隆对象
	 * <p>
	 * <h1>created by Spartacus at 2015年9月7日 下午1:40:14</h1>
	 * <p>
	 * 
	 * @param 要克隆的对象
	 * @return 深度克隆后的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deepClone(T obj) {
		T t = null;
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			oos.writeObject(obj);

			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bin);
			t = (T) ois.readObject();
			// 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
			// 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return t;
	}

}
