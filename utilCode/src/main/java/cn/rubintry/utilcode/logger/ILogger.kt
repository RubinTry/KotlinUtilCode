package cn.rubintry.utilcode.logger

/**
 * @author logcat
 */
interface ILogger {
    open fun debug(message: String?)
    open fun debug(message: String?, ex: Throwable?)
    open fun error(message: String?)
    open fun error(message: String?, ex: Throwable?)
    open fun warn(message: String?)
    open fun warn(message: String?, ex: Throwable?)
}