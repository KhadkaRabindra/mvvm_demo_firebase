package com.maxx.nordvaest.ui.home.adapter

import android.app.Application
import androidx.databinding.ObservableField
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.remote.Brukere

class QRAdapterViewModel(app: Application) : BaseViewModel(app) {

    var stempelDato: ObservableField<String> = ObservableField()
    var stempelIndex: ObservableField<String> = ObservableField()
    var stempelNumber: ObservableField<String> = ObservableField()
    var user: ObservableField<String> = ObservableField()

    fun setQRData(item: Brukere) {
        stempelDato.set(item.StempelDato)
        stempelIndex.set(item.StempelIndex)
        stempelNumber.set(item.StempelNumber)
        user.set(item.User)
    }

}