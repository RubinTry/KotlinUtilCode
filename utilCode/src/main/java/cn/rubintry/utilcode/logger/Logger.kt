package cn.rubintry.utilcode.logger

import android.util.Log

/**
 * @author logcat
 */
class Logger(private val TAG: String?) : ILogger {
    override fun debug(message: String?) {
        Log.d(TAG, message)
    }

    override fun debug(message: String?, ex: Throwable?) {
        Log.d(TAG, message, ex)
    }

    override fun error(message: String?) {
        Log.e(TAG, message)
    }

    override fun error(message: String?, ex: Throwable?) {
        Log.e(TAG, message, ex)
    }

    override fun warn(message: String?) {
        Log.w(TAG, message)
    }

    override fun warn(message: String?, ex: Throwable?) {
        Log.w(TAG, message, ex)
    }
}