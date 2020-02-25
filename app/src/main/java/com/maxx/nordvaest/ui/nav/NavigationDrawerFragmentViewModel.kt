package com.maxx.nordvaest.ui.nav

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.local.NavigationItem
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject

class NavigationDrawerFragmentViewModel(app: Application) : BaseViewModel(app) {

    val userFullName: ObservableField<String> = ObservableField()
    val userName: ObservableField<String> = ObservableField()
    var navigationList: MutableLiveData<List<NavigationItem>> = MutableLiveData()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    fun setNavigationList(navigationList: List<NavigationItem>) {
        this.navigationList.postValue(navigationList)
    }

    fun setFullNameAndUserName() {
        this.userFullName.set(userRepository.getUserEmailAddress())
        this.userName.set(userRepository.getFirebaseUserID())

        /*this.userFullName.set("Rabindra Khadka")
        this.userName.set("Rabindra")*/
    }
}