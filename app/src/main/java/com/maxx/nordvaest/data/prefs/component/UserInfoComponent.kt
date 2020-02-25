package com.maxx.nordvaest.data.prefs.component

import com.maxx.nordvaest.data.prefs.entity.UserInfo
import com.maxx.nordvaest.repository.LoginRepository
import com.maxx.nordvaest.repository.UserRepository
import com.maxx.nordvaest.service.HeaderInterceptor
import com.maxx.nordvaest.utils.LoginUtils
import com.skydoves.preferenceroom.PreferenceComponent

@PreferenceComponent(entities = [(UserInfo::class)])
interface UserInfoComponent {
    fun inject(target: LoginRepository)

    fun inject(target: UserRepository)

    fun inject(target: HeaderInterceptor)

    fun inject(target: LoginUtils)

}