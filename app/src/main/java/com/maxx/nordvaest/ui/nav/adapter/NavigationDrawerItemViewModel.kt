package com.maxx.nordvaest.ui.nav.adapter

import android.app.Application
import androidx.databinding.ObservableField
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.local.NavigationItem

class NavigationDrawerItemViewModel (app: Application): BaseViewModel(app){

    val title: ObservableField<String> = ObservableField()
    val iconRes: ObservableField<Int> = ObservableField()

    lateinit var navigationItem: NavigationItem

    fun setItem(item: NavigationItem) {
        this.navigationItem = item
        title.set(item.title)
        iconRes.set(item.menuIcon)
    }
}