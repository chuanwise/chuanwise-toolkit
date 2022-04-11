package cn.chuanwise.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 *
 * @author Chuanwise
 */
public class Exceptions
        extends StaticUtilities {
    
    /**
     * 将异常堆栈信息输出到字符串
     *
     * @param throwable 异常
     * @return 堆栈信息字符串
     */
    public static String readStackTrace(Throwable throwable) {
        Preconditions.objectNonNull(throwable, "throwable");
    
        final StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }
    
    /**
     * 将异常重新抛出
     *
     * @param cause 异常
     * @throws Exception 重新抛出的异常
     */
    public static void rethrow(Throwable cause) throws Exception {
        Preconditions.objectNonNull(cause, "cause");
    
        if (cause instanceof RuntimeException) {
            throw (RuntimeException) cause;
        }
        if (cause instanceof Exception) {
            throw (Exception) cause;
        }
        if (cause instanceof Error) {
            throw (Error) cause;
        }
        
        throw new Exception(cause);
    }
}
