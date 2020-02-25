package com.maxx.nordvaest.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.eightsquarei.eremitpay.service.LoginService
import com.maxx.nordvaest.data.prefs.component.PreferenceComponent_UserInfoComponent
import com.maxx.nordvaest.data.prefs.entity.Preference_UserInfo
import com.maxx.nordvaest.data.remote.User
import com.maxx.nordvaest.ui.login.LoginActivity
import com.skydoves.preferenceroom.InjectPreference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rakezb on 4/29/2019.
 */
@Singleton
class UserRepository @Inject
constructor() {

    @InjectPreference
    lateinit var userInfo: Preference_UserInfo

    @Inject
    lateinit var api: LoginService


    init {
        PreferenceComponent_UserInfoComponent.getInstance().inject(this)
    }

    fun getPinSetupStatus(): Boolean = userInfo.pinSetupStatus

    fun putPinSetupStatus(status: Boolean) = userInfo.putPinSetupStatus(status)

    fun getHasPasscode(): Boolean = userInfo.hasPasscode

    fun getLoginStatus(): Boolean? = userInfo.loginStatus

    fun putUserDetail(user: User?) = userInfo.putUser(user)

    fun putSessionToken(token: String?) = userInfo.putSessionToken(token)

    fun getSessionToken(): String? = userInfo.sessionToken

    fun getAlreadyRegisteredUser() = userInfo.alreadyRegisteredUser

    fun putAlreadyRegisteredUser(status: Boolean) = userInfo.putAlreadyRegisteredUser(status)

    fun putHasPasscode(status: Boolean) = userInfo.putHasPasscode(status)

    fun putLoginStatus(status: Boolean) = userInfo.putLoginStatus(status)

    fun getUserFullName() = userInfo.user?.name

    fun getFirebaseUserID() = userInfo.user?.userID

    fun putFirebaseUserID(userID: String) = userInfo.putUserID(userID)

    fun getMobileNumber() = userInfo.user?.phoneNumber

    fun isPasswordReset(): Boolean? = userInfo.isPasswordReset

    fun putPasswordResetStatus(status: Boolean) = userInfo.putIsPasswordReset(status)

    fun getUserEmailAddress() = userInfo.user?.emailAddress

    fun clearUserInfo() = userInfo.clear()

    fun doLogout(context: Context) {
        clearUserInfo()
        //disable app lock
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        (context as Activity).finish()
    }

}