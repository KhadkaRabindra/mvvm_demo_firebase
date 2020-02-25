package com.maxx.nordvaest.core

import android.app.Application
import com.maxx.nordvaest.NorvaestApplication

class ToolbarCommonActivityViewModel(app: Application) : BaseViewModel(app) {

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

}