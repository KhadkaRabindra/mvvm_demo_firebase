package com.maxx.nordvaest.core

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.data.local.ViewType
import com.maxx.nordvaest.utils.parseError
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel(app: Application) : AndroidViewModel(app) {

    var compositeDisposable = CompositeDisposable()

    /**
     * Usage: norvaestApplication:mutableVisibility="@{viewModel.getLoadingVisibility()}"
     */
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    /**
     * Usage norvaestApplication:visibility="@{viewModel.emptyVisibility}"
     */
    val emptyVisibility: MutableLiveData<Boolean> = MutableLiveData()

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val toastMessage: MutableLiveData<String> = MutableLiveData()
    val openView: MutableLiveData<ViewType?> = MutableLiveData()
    val errorDialog: MutableLiveData<String?> = MutableLiveData()

    val progressDialogMessage: MutableLiveData<String> = MutableLiveData()

    protected val mFirebaseAuth = FirebaseAuth.getInstance()

    protected var mFirebaseDatabase: DatabaseReference? = (app as NorvaestApplication).getFirebaseDatabase()
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable?.isDisposed)
            compositeDisposable?.dispose()
    }

    private fun onEmptyData() {
        emptyVisibility.postValue(true)
    }

    fun onApiRequestError(it: Throwable?) {
        errorDialog.postValue(it?.parseError())
    }

    fun onApiRequestStart() {
        loadingVisibility.postValue(View.VISIBLE)
    }

    fun onApiRequestFinish() {
        loadingVisibility.postValue(View.GONE)
    }

    fun resetOpenView() {
        openView.postValue(null)
    }
}
