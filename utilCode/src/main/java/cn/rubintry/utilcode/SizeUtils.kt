package cn.rubintry.utilcode

import android.content.res.Resources

/**
 * @author logcat
 */
object SizeUtils {
    fun dp2Px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f) as Int
    }
}