package com.maxx.nordvaest.di.component

import android.content.Context
import android.content.SharedPreferences
import com.maxx.nordvaest.di.module.ApplicationModule
import com.maxx.nordvaest.di.module.NetModule
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.ToolbarCommonActivityViewModel
import com.maxx.nordvaest.ui.forgot_password.ForgotPasswordFragmentViewModel
import com.maxx.nordvaest.ui.home.HomeActivity
import com.maxx.nordvaest.ui.home.HomeActivityViewModel
import com.maxx.nordvaest.ui.home.HomeFragmentViewModel
import com.maxx.nordvaest.ui.login.LoginActivityViewModel
import com.maxx.nordvaest.ui.nav.NavigationDrawerFragmentViewModel
import com.maxx.nordvaest.ui.nav.adapter.NavigationDrawerItemViewModel
import com.maxx.nordvaest.ui.profile.ProfileFragmentViewModel
import com.maxx.nordvaest.ui.qr_scanner.QRScannerViewModel
import com.maxx.nordvaest.ui.register.RegistrationFragmentViewModel
import com.maxx.nordvaest.ui.settings.SettingsViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))


interface ApplicationComponent {
    fun app(): NorvaestApplication


    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(target: ToolbarCommonActivityViewModel)

    fun inject(target: HomeActivityViewModel)

    fun inject(target: NavigationDrawerFragmentViewModel)

    fun inject(target: NavigationDrawerItemViewModel)

    fun inject(target: HomeActivity)

    fun inject(target: HomeFragmentViewModel)

    fun inject(target: LoginActivityViewModel)

    fun inject(target: RegistrationFragmentViewModel)

    fun inject(target: ForgotPasswordFragmentViewModel)

    fun inject(target: QRScannerViewModel)

    fun inject(target: SettingsViewModel)

    fun inject(target: ProfileFragmentViewModel)

}
