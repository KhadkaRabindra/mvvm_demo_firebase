package com.maxx.nordvaest.data.prefs.converters

import com.google.gson.Gson
import com.skydoves.preferenceroom.PreferenceTypeConverter


class BaseGsonConverter<T>(clazz: Class<T>) : PreferenceTypeConverter<T>(clazz) {

    private val gson: Gson = Gson()

    override fun convertObject(obj: T?): String {
        return gson.toJson(obj)
    }

    override fun convertType(string: String?): T? {
        when (string == null) {
            true -> return null
            else -> return gson.fromJson(string, clazz)
        }
    }
}