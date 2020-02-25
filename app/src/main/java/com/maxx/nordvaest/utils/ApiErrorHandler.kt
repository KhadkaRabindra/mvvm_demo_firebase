package com.maxx.nordvaest.utils

import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

class ApiErrorHandler {
    companion object {


        fun parseBodyError(errorBody: ResponseBody?): String? {
            var errorMessage = ""
            try {
                val errorStr = errorBody?.string()
                val jsonArray = JSONArray(errorStr)
                for (i in 0..jsonArray.length()) {
                    if (i == 0) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        errorMessage = jsonObject.getString("Message")
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return errorMessage
        }

        fun parseErrorFromJava(it: Throwable?): String?{
            return if (it is HttpException) {
                val body = (it as HttpException).response().errorBody()
                parseBodyError(body as ResponseBody)
            } else {
                var errorMessage: String? = it?.message
                if (errorMessage.isNullOrEmpty())
                    errorMessage = "Please check your internet connection."
                else if (errorMessage?.contains(Constants.API_BASE_URL) == true)
                    errorMessage = "Failed to connect to server."
                errorMessage as String
            }
        }


    }
}

fun Throwable?.parseError(): String? {
    return if (this is HttpException) {
        val body = (this as HttpException).response().errorBody()
        ApiErrorHandler.parseBodyError(body as ResponseBody)
    } else {
        var errorMessage: String? = this?.message
        if (errorMessage.isNullOrEmpty())
            errorMessage = "Please check your internet connection."
        else if (errorMessage?.contains(Constants.API_BASE_URL) == true)
            errorMessage = "Failed to connect to server."
        errorMessage as String
    }
}