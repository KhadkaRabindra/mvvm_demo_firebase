package com.maxx.nordvaest.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.eightsquarei.eremitpay.repository.ApiRepository
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.remote.User
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject

class LoginActivityViewModel(app: Application) : BaseViewModel(app) {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    fun getLoginStatus(): Boolean? {
        return userRepository.getLoginStatus()
    }

    fun requestLogin() {
        //loginSuccess.postValue(true)
        onApiRequestStart()

        mFirebaseAuth.signInWithEmailAndPassword(email.value?.trim().toString(), password.value?.trim().toString())
            .addOnCompleteListener {
                onApiRequestFinish()
                loginSuccess.postValue(it.isSuccessful)
                if (it.isSuccessful) {
                    handleLoginStatus(email.value?.trim().toString())
                }
            }
            .addOnFailureListener {
                onApiRequestError(it)
            }
    }


    private fun handleLoginStatus(emailAddress: String) {
        userRepository.putUserDetail(
            User(
                emailAddress = emailAddress.toLowerCase()
            )
        )
        userRepository.putLoginStatus(true)
        loginSuccess.postValue(true)
    }


}