package com.maxx.nordvaest.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.maxx.nordvaest.R
import com.maxx.nordvaest.NorvaestApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


@Module
class ApplicationModule(var norvaestApplication: NorvaestApplication) {


    @Provides
    @Singleton
    fun provideApp(): NorvaestApplication = norvaestApplication

    @Provides
    @Singleton
    fun provideContext(): Context = norvaestApplication.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(norvaestApplication)

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Gotham Rounded Book.otf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }
}
