package com.maxx.nordvaest.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.maxx.nordvaest.data.prefs.component.PreferenceComponent_UserInfoComponent
import com.maxx.nordvaest.repository.UserRepository
import com.maxx.nordvaest.ui.login.LoginActivity
import java.util.*
import javax.inject.Inject

/**
 * Created by amanandhar on 2/20/18.
 */

/**
 * get device unique id for login
 */
class LoginUtils {
    @Inject
    lateinit var userRepository: UserRepository

    init {
        PreferenceComponent_UserInfoComponent.getInstance().inject(this)
    }

    /**
     * save passcode session token to shared prefs
     */
    fun savePasscodeToken(token: String?) {
        userRepository.putSessionToken(token)
    }

    fun doLogout(context: Context) {
        clearSavedData()
        //disable app lock
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        (context as Activity).finish()
    }

    private fun clearSavedData() {
        userRepository.clearUserInfo()
    }
}

fun getDeviceUniqueId(context: Context): String {
    var androidId: String = ""
    try {
        androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    } catch (e: Exception) {
        e.printStackTrace()
        androidId = UUID.randomUUID().toString()
    }

    return androidId
}


/**
 * save data to sharedpref from user authentification api
 */
/*fun saveUserInfoFromResponse(signInResponse: SignInResponse) {
    UserInfo.bulk {
        deviceKey = CommonUtils.getString_(signInResponse.deviceKey)
        refreshToken = CommonUtils.getString_(signInResponse.refreshToken)
        isNotificationActive = signInResponse.isNotificationActive as Boolean
        deviceIdentifier = CommonUtils.getString_(signInResponse.androidId)
        fullName = CommonUtils.getString_(signInResponse.name)
        email = CommonUtils.getString_(signInResponse.emailAddress)
        isPhoneNumberConfirmed = signInResponse.phoneNumberConfirmed as Boolean
        isFirstLoggedInSuccess = true
        languageCode = CommonUtils.getString_(signInResponse.language)
        email = CommonUtils.getString_(signInResponse.emailAddress)
        phoneNumber = CommonUtils.getString_(signInResponse.phoneNumber)
        referralCode = CommonUtils.getString_(signInResponse.referralCode)
        //isBankDetailRequired = CommonUtils.getBoolean_(signInResponse.isRequiredEkycBankDetail)
    }
}*/


