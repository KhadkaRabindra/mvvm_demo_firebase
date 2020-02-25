package com.maxx.nordvaest.ui.register

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.eightsquarei.eremitpay.repository.ApiRepository
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.remote.LoginResponse
import com.maxx.nordvaest.data.remote.User
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject

class RegistrationFragmentViewModel(app: Application) : BaseViewModel(app) {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword : MutableLiveData<String> = MutableLiveData()
    val registerSuccess: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    fun register() {
        onApiRequestStart()

        mFirebaseAuth.createUserWithEmailAndPassword(email.value?.trim().toString(), password.value?.trim().toString())
            .addOnCompleteListener {
                onApiRequestFinish()
                registerSuccess.postValue(it.isSuccessful)
                if (it.isSuccessful){
                    registerSuccess.postValue(it.isSuccessful)
                    handleRegisterStatus(email.value?.trim().toString())
                }
            }
            .addOnFailureListener {
                onApiRequestError(it)
            }
    }

    private fun handleRegisterStatus(emailAddress: String) {
        userRepository.putUserDetail(
            User(
                emailAddress = emailAddress.toLowerCase()
            )
        )
        userRepository.putLoginStatus(true)
    }



}