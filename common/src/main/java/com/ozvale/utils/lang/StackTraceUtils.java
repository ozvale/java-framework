/**
 * 
 */
package com.ozvale.utils.lang;

/**
 * java 运行堆栈拓展类
 * <p>
 * <Strong>Date: </Strong> 2015年4月7日 上午11:39:31
 * 
 * @author Spartacus
 */
public class StackTraceUtils {

	/**
	 * Description:获取异常堆栈追踪 作者 wuzhiwen 2014年5月28日 上午10:26:27
	 * 
	 * @return
	 */
	public static String getStackTrace() {

		StackTraceElement stack[] = Thread.currentThread().getStackTrace();

		return getStackTrace(stack);
	}

	/**
	 * Description:获取非java系统的异常堆栈 作者 wuzhiwen 2014年6月12日 下午2:09:20
	 * 
	 * @param pArrayStackTraceElement
	 * @return
	 */
	public static String getStackTrace(StackTraceElement[] pArrayStackTraceElement) {
		String _stackTrace = "";
		for (StackTraceElement ste : pArrayStackTraceElement) {
			if (ste.getClassName().indexOf(StackTraceUtils.class.getName()) != -1 || ste.getClassName().startsWith("sun") || ste.getClassName().startsWith("java")
					|| ste.getClassName().startsWith("org")) {
				continue;
			}
			_stackTrace += "*** " + ste.getClassName() + "." + ste.getMethodName() + "   line:" + ste.getLineNumber() + "-_-newLine-_-";
		}
		return _stackTrace;
	}

	/**
	 * 获取调用者 被排除的项（className.methodName）
	 * <p>
	 * <h1>created by Spartacus at 2014年10月20日 上午11:14:15</h1>
	 * <p>
	 * 
	 * @param exclusion
	 * @return
	 */
	public static StackTraceElement getCallerStackTraceElement(String... exclusion) {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		StackTraceElement result = null;
		for (StackTraceElement ste : stack) {
			result = ste;

			boolean exist = false;
			// 排除条件
			if (exclusion != null) {
				for (String s : exclusion) {
					if ((ste.getClassName() + "." + ste.getMethodName()).equals(s)) {
						exist = true;
						break;
					}
				}
			}

			if (exist) {
				continue;
			}

			// 筛选
			if (ste.getClassName().indexOf(StackTraceUtils.class.getName()) != -1 || ste.getClassName().startsWith("sun") || ste.getClassName().startsWith("java")
					|| ste.getClassName().startsWith("org")) {
				continue;
			}
			break;
		}
		return result;
	}

	/**
	 * 获取调用者 被排除的项（className.methodName）
	 * <p>
	 * <h1>created by Spartacus at 2014年10月20日 上午11:14:15</h1>
	 * <p>
	 * 
	 * @param exclusion
	 * @return
	 */
	public static StackTraceElement getCallerStackTraceElement(Class<?>... exclusion) {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		StackTraceElement result = null;
		for (StackTraceElement ste : stack) {
			result = ste;

			boolean exist = false;
			// 排除条件
			if (exclusion != null) {
				for (Class<?> c : exclusion) {
					if ((ste.getClassName().equals(c.getName()))) {
						exist = true;
						break;
					}
				}
			}

			if (exist) {
				continue;
			}

			// 筛选
			if (ste.getClassName().indexOf(StackTraceUtils.class.getName()) != -1 || ste.getClassName().startsWith("sun") || ste.getClassName().startsWith("java")
					|| ste.getClassName().startsWith("org")) {
				continue;
			}
			break;
		}
		return result;
	}

	/**
	 * 获取thread stacktrace中的最后一个
	 * <p>
	 * <h1>created by Spartacus at 2014年10月28日 下午1:15:07</h1>
	 * <p>
	 * 
	 * @return
	 */
	public static StackTraceElement getLastCallerStackTraceElement() {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		if (stack.length > 0) {
			return stack[stack.length - 1];
		}
		return null;
	}
}
