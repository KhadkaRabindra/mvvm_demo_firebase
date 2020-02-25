package com.maxx.nordvaest.ui.home.adapter

import android.app.Application
import androidx.databinding.ObservableField
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.local.HomeItem

class HomeAdapterViewModel(app: Application) : BaseViewModel(app) {

    var homeData: ObservableField<HomeItem> = ObservableField()
    val name: ObservableField<String> = ObservableField()
    val icon: ObservableField<Int> = ObservableField()
    val showIcon : ObservableField<Boolean> = ObservableField()

    fun setHomeData(item: HomeItem) {
        homeData.set(item)
        icon.set(item.icon)
        showIcon.set(item.showIcon)
    }

}