package cn.rubintry.utilcode.logger

/**
 * @author logcat
 */
object OkLogger {
    var logger: ILogger? = Logger(OkLogger::class.java.simpleName)
    fun debug(message: String?) {
        logger.debug(message)
    }

    fun debug(message: String?, ex: Throwable?) {
        logger.debug(message, ex)
    }

    fun error(message: String?) {
        logger.error(message)
    }

    fun error(message: String?, ex: Throwable?) {
        logger.error(message, ex)
    }

    fun warn(message: String?) {
        logger.warn(message)
    }

    fun warn(message: String?, ex: Throwable?) {
        logger.warn(message, ex)
    }
}