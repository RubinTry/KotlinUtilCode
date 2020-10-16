package cn.rubintry.utilcode

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author logcat
 */
class GsonUtils {
    private val defaultConverter = Gson()

    fun <T>fromJson (json: String, type: Type): T {
        return defaultConverter.fromJson(json, type)
    }

    fun <T> fromJson(json : String , clazz: Class<T>) : T{
        return defaultConverter.fromJson(json , clazz)
    }

    companion object {
       @Volatile
       var instance: GsonUtils ?= null
        get() {
            if(field == null){
                synchronized(GsonUtils::class.java){
                    if(field == null){
                        field = GsonUtils()
                    }
                }
            }
            return field
        }
    }
}