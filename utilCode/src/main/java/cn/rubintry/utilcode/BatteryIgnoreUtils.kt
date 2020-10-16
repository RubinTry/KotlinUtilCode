package cn.rubintry.utilcode

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.annotation.RequiresApi

class BatteryIgnoreUtils {

    companion object{

        /**
         * 判断当前应用是否在系统白名单中
         *
         * @return
         */
        @RequiresApi(Build.VERSION_CODES.M)
        fun isIgnoringBatteryOptimizations() : Boolean{
            var isIgnoring = false
            val powerManager: PowerManager = Utils.applicationContext?.getSystemService(Context.POWER_SERVICE) as PowerManager
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(Utils.applicationContext?.packageName)
            return isIgnoring
        }


        /**
         * 请求将当前应用加入系统白名单
         *
         */
        @RequiresApi(Build.VERSION_CODES.M)
        fun requestIgnoreBatteryOptimizations(){
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:" + Utils.applicationContext?.packageName)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            Utils.applicationContext?.startActivity(intent)
        }
    }
}