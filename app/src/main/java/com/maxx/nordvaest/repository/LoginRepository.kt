package com.maxx.nordvaest.repository


import com.eightsquarei.eremitpay.service.LoginService
import com.maxx.nordvaest.data.prefs.component.PreferenceComponent_UserInfoComponent
import com.maxx.nordvaest.data.prefs.entity.Preference_UserInfo
import com.maxx.nordvaest.data.prefs.entity.UserInfo
import com.skydoves.preferenceroom.InjectPreference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject
constructor() {

    @InjectPreference
    lateinit var userInfo: Preference_UserInfo

    @Inject
    lateinit var api: LoginService


    init {
        PreferenceComponent_UserInfoComponent.getInstance().inject(this)
    }

    fun getUserInfo(): UserInfo? = userInfo

    fun getLoginStatus(): Boolean? = userInfo.loginStatus

    fun putLoginStatus(status: Boolean) = userInfo.putLoginStatus(status)

    fun isPasswordReset(): Boolean? = userInfo.isPasswordReset

    fun putPasswordResetStatus(status: Boolean) = userInfo.putIsPasswordReset(status)


}