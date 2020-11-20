/**
 * 
 */
package com.ozvale.extension.java.utils;

import java.util.UUID;

/**
 * UUIDExtension
 * <p>
 * <Strong>Date: </Strong> 2015年6月3日 下午2:56:28
 * 
 * @author Spartacus
 */
public class UUIDExtension {

	/**
	 * 获取一个随机生成的uuid字符串(原始)
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:07:26</h1>
	 * <p>
	 * 
	 * @return
	 */
	public static String uuidString() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取一个随机生成的uuid字符串(不带'-'符号,较常用)
	 * <p>
	 * <h1>created by Spartacus at 2015年4月3日 下午2:07:26</h1>
	 * <p>
	 * 
	 * @return
	 */
	public static String uuid() {
		return uuidString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(uuid());
	}
}
