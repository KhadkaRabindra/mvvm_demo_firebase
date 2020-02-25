package com.maxx.nordvaest.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.eightsquarei.eremitpay.repository.ApiRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.data.local.HomeItem
import com.maxx.nordvaest.data.remote.Brukere
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject


class HomeFragmentViewModel(app: Application) : BaseViewModel(app) {

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
    }

    val qrDataList: MutableLiveData<MutableList<Brukere?>?> = MutableLiveData()

    var homeDataList: MutableLiveData<List<HomeItem?>?> = MutableLiveData()

    fun setHomeData(homeDataList: List<HomeItem?>?) {
        this.homeDataList.postValue(homeDataList)
    }

    fun setQRData(qrDataList: List<Brukere?>?) {
        this.qrDataList.postValue(qrDataList as MutableList<Brukere?>?)
    }

    fun getFireBaseData() {
        onApiRequestStart()

        mFirebaseDatabase?.keepSynced(true)
        val query = mFirebaseDatabase?.orderByChild("user")?.equalTo(
            userRepository.getUserEmailAddress()
        )
        query?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                onApiRequestFinish()

                val ingredients = ArrayList<Brukere>()
                for (childDataSnapshot in dataSnapshot.children) {
                    val stempelDato = childDataSnapshot.child("stempelDato").value.toString()
                    val stempelIndex = childDataSnapshot.child("stempelIndex").value.toString()
                    val stempelNumber = childDataSnapshot.child("stempelNumber").value.toString()
                    val user = childDataSnapshot.child("user").value.toString()

                    ingredients.add(
                        Brukere(
                            StempelDato = stempelDato,
                            StempelIndex = stempelIndex,
                            StempelNumber = stempelNumber,
                            User = user
                        )
                    )
                }
                val gson = Gson()
                setQRData(ingredients)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onApiRequestError(databaseError.toException())
            }
        })
    }

}