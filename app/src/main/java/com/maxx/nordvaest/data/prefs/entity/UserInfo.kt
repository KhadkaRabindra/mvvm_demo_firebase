package com.maxx.nordvaest.data.prefs.entity

import com.maxx.nordvaest.data.prefs.converters.BaseGsonConverter
import com.maxx.nordvaest.data.remote.User
import com.skydoves.preferenceroom.KeyName
import com.skydoves.preferenceroom.PreferenceEntity
import com.skydoves.preferenceroom.TypeConverter


@PreferenceEntity(name = "UserInfo")
open class UserInfo {

    @KeyName(name = "user")
    @TypeConverter(converter = BaseGsonConverter::class)
    @JvmField
    val user: User? = null

    @JvmField
    val isPasswordReset:Boolean = false

    @JvmField
    val loginStatus: Boolean = false

    @JvmField
    val sessionToken: String? = null

    @JvmField
    val alreadyRegisteredUser : Boolean = false

    @JvmField
    val hasPasscode: Boolean = false

    @JvmField
    val pinSetupStatus: Boolean = false

    @JvmField
    val userID : String = ""
}