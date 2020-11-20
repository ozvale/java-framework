package com.ozvale.utils.exception;

/**
 * 尝试执行函数,不抛出异常
 *
 * @author ozvale
 */
public class Try {

    public static void execute(Function function) {
        try {
            function.execute();
        } catch (Exception e) {
        }
    }

    public static <T> T execute(FunctionWithOutput<T> function) {
        try {
            return function.execute();
        } catch (Throwable e) {
            return null;
        }
    }

    public interface Function {
        public void execute() throws Exception;
    }


    public interface FunctionWithOutput<T> {
        public T execute() throws Exception;
    }
}
