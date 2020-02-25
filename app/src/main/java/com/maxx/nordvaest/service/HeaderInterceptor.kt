package com.maxx.nordvaest.service

import com.maxx.nordvaest.utils.Constants
import com.maxx.nordvaest.BuildConfig
import com.maxx.nordvaest.data.prefs.component.PreferenceComponent_UserInfoComponent
import com.maxx.nordvaest.data.prefs.entity.Preference_UserInfo
import com.maxx.nordvaest.utils.extensions.isNotEmptyorNull
import com.maxx.nordvaest.utils.extensions.logV
import com.skydoves.preferenceroom.InjectPreference
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

class HeaderInterceptor() : Interceptor {


    @InjectPreference
    lateinit var userInfo: Preference_UserInfo

    init {
        PreferenceComponent_UserInfoComponent.getInstance().inject(this)
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val deviceType = "CCPApp"

        val original = chain.request()
        val loginStatus = userInfo.loginStatus
        if (BuildConfig.DEBUG)
            logV("testing intercepting header....111: $loginStatus")


        val requestBuilder = original.newBuilder()
                .header("Content", "application/json")

        val token = userInfo.user?.deviceKey
        val sessionToken = userInfo.sessionToken

        requestBuilder.header("DeviceType", deviceType)
        if(token.isNotEmptyorNull())
            requestBuilder.addHeader("DeviceKey", token.toString())
        if(sessionToken.isNotEmptyorNull())
            requestBuilder.addHeader("SessionToken", sessionToken.toString())

        val request = requestBuilder.build()
        val response: Response = chain.proceed(request)
        if (response.code() == 403 || response.code() == 401) {
            val refreshToken = userInfo.user?.refreshToken as String
            val requestBody = FormBody.Builder()
                    .add("RefreshToken", refreshToken)
                    .build()

            val newRequest = request?.newBuilder()
                    ?.header("Content-Type", "application/json")
                    ?.header("DeviceKey", token as String)
                    ?.header("DeviceType", deviceType)
                    ?.url(Constants.API_BASE_URL + Constants.REFRESH_TOKEN_URL)
                    ?.post(requestBody)
                    ?.build()

            val unauthorisedResponse = chain.proceed(newRequest!!)
            val bodyString = unauthorisedResponse.body()?.string()
            var unauthorisedObject: JSONObject? = null
            try {
                unauthorisedObject = JSONObject(bodyString)

                if (unauthorisedResponse.code() == 200) {
//                    val token = unauthorisedObject.getString("data")
//                    requestBuilder.removeHeader("Authorization")
//                    requestBuilder.addHeader("Authorization", "Bearer $token")
//                            ?.header("device_id", Utils.getDeviceIdentifier(context))


                    updateUserInfo(unauthorisedObject)
                    val newDeviceKey = userInfo.user?.deviceKey as String
                    requestBuilder.removeHeader("DeviceKey")
                    requestBuilder.addHeader("DeviceKey", newDeviceKey)


                    val newOriginalRequest = requestBuilder.build() as Request
                    return chain.proceed(newOriginalRequest)
                } else {
//                    context.doLogOut()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
//                context.doLogOut()
            }
        }
        return response

    }

    private fun updateUserInfo(obj: JSONObject) {
        userInfo.user?.deviceKey = obj.getString("DeviceKey")
        userInfo.user?.deviceKey = obj.getString("RefreshToken")
    }
}