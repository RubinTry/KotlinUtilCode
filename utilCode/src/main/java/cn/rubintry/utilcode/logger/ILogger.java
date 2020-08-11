package cn.rubintry.utilcode.logger;

/**
 * @author logcat
 */
public interface ILogger {
    void debug(String message);

    void debug(String message , Throwable ex);

    void error(String message);

    void error(String message , Throwable ex);


    void warn(String message);

    void warn(String message , Throwable ex);
}