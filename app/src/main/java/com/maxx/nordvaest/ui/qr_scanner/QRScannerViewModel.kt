package com.maxx.nordvaest.ui.qr_scanner

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.Result
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.remote.Brukere
import com.maxx.nordvaest.repository.UserRepository
import com.maxx.nordvaest.utils.extensions.getDateAndTime
import javax.inject.Inject

class QRScannerViewModel(app: Application) : BaseViewModel(app) {
    private var mFirebaseInstance: FirebaseDatabase? = null

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    @Inject
    lateinit var userRepository: UserRepository

    val sendDataToFirebaseSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun sendDataToFireBaseServer(rawResult: String?) {
        /*if (TextUtils.isEmpty(userRepository.getFirebaseUserID())) {
            createData()
        } else {
            updateData()
        }*/

        createData(rawResult)
    }

    private fun createData(rawResult: String?) {
        /*if (TextUtils.isEmpty(userRepository.getFirebaseUserID())) {
            val userId = mFirebaseDatabase?.push()?.key
            userRepository.putFirebaseUserID(userId.toString())
        }*/
        onApiRequestStart()
        val userId = mFirebaseDatabase?.push()?.key
        userRepository.putFirebaseUserID(userId.toString())

        val brukere = Brukere(
            StempelDato = getDateAndTime(),
            StempelIndex = 0.toString(),
            StempelNumber = 1.toString(),
            User = userRepository.getUserEmailAddress()
        )
        mFirebaseInstance?.getReference("app_title")?.setValue("Realtime Database")
        mFirebaseDatabase?.child(userId.toString())?.setValue(brukere)?.addOnSuccessListener {
            onApiRequestFinish()
            sendDataToFirebaseSuccess.postValue(true)
        }?.addOnFailureListener({
            onApiRequestError(it)
        })

        //
    }

    private fun updateData() {

        mFirebaseDatabase?.child(userRepository.getFirebaseUserID().toString())?.child("stempelDato")?.setValue("")
        mFirebaseDatabase?.child(userRepository.getFirebaseUserID().toString())?.child("stempelIndex")
            ?.setValue(0.toString())
        mFirebaseDatabase?.child(userRepository.getFirebaseUserID().toString())?.child("stempelNumber")
            ?.setValue(1.toString())
        mFirebaseDatabase?.child(userRepository.getFirebaseUserID().toString())?.child("user")
            ?.setValue(userRepository.getUserEmailAddress())

        //sendDataToFirebaseSuccess.postValue(true)

    }


}