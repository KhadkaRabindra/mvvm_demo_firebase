package com.maxx.nordvaest.ui.home

import android.app.Application
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel

class HomeActivityViewModel(app : Application) : BaseViewModel(app){
    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

}