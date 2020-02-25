package com.maxx.nordvaest.ui.forgot_password

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.eightsquarei.eremitpay.repository.ApiRepository
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.remote.ForgotPasswordPostData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForgotPasswordFragmentViewModel(app: Application) : BaseViewModel(app) {

    var newPassword: MutableLiveData<String?> = MutableLiveData()
    var confirmPassword: MutableLiveData<String?> = MutableLiveData()
    var isPasswordResetSuccess: MutableLiveData<Boolean> = MutableLiveData()


    @Inject
    lateinit var apiRepository: ApiRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    fun requestForgotPassword() {
        val request = ForgotPasswordPostData(newPassword.value.toString(), confirmPassword.value.toString())
        compositeDisposable.add(apiRepository.requestResetPassword(request)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { onApiRequestStart() }
            .doOnTerminate { onApiRequestFinish() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isPasswordResetSuccess.postValue(true)
            }, {
                onApiRequestError(it)
            }))
    }
}