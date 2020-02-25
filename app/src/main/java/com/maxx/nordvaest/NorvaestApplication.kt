package com.maxx.nordvaest

import androidx.multidex.MultiDexApplication
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.maxx.nordvaest.data.prefs.component.PreferenceComponent_UserInfoComponent
import com.maxx.nordvaest.di.component.DaggerApplicationComponent
import com.maxx.nordvaest.di.module.ApplicationModule
import com.maxx.nordvaest.utils.Constants


class NorvaestApplication : MultiDexApplication() {
    private var mFirebaseDatabase: DatabaseReference? = null

    fun getFirebaseDatabase() : DatabaseReference?{
        return mFirebaseDatabase
    }

    val component by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceComponent_UserInfoComponent.init(this)
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Brukere")
    }

}

