package com.maxx.nordvaest.ui.settings

import android.app.Application
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject

class SettingsViewModel(app: Application) : BaseViewModel(app) {

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

}