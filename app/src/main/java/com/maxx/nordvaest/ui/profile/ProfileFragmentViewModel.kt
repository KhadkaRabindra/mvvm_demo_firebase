package com.maxx.nordvaest.ui.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.maxx.nordvaest.NorvaestApplication
import com.maxx.nordvaest.R
import com.maxx.nordvaest.core.BaseViewModel
import com.maxx.nordvaest.repository.UserRepository
import javax.inject.Inject

class ProfileFragmentViewModel(app: Application) : BaseViewModel(app) {

    @Inject
    lateinit var userRepository: UserRepository

    val userFullName: MutableLiveData<String> = MutableLiveData()
    val userName: MutableLiveData<String> = MutableLiveData()

    val registeredStamps: MutableLiveData<String> = MutableLiveData()
    val remainingStamps: MutableLiveData<String> = MutableLiveData()
    val currentLoyalityRanks: MutableLiveData<String> = MutableLiveData()
    val nextLoyalityRanks: MutableLiveData<String> = MutableLiveData()

    val currentLoyalityRankImage: MutableLiveData<Int> = MutableLiveData()
    val nextLoyalityRankImage: MutableLiveData<Int> = MutableLiveData()

    init {
        (app as? NorvaestApplication)?.component?.inject(this)
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

                setStampsData(dataSnapshot.childrenCount)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onApiRequestError(databaseError.toException())
            }
        })
    }

    private fun setStampsData(count: Long) {

        val totalCountInt = count.toInt()
        registeredStamps.postValue(totalCountInt.toString())

        if (totalCountInt >= 0 && totalCountInt < 50) {
            this.remainingStamps.postValue((50 - totalCountInt).toString())
            this.currentLoyalityRanks.postValue("Diamant")
            this.nextLoyalityRanks.postValue("Sølv")

            this.currentLoyalityRankImage.postValue(R.drawable.diamond)
            this.nextLoyalityRankImage.postValue(R.drawable.silver)

        } else if (totalCountInt >= 50 && totalCountInt < 120) {
            this.remainingStamps.postValue((120 - totalCountInt).toString())
            this.currentLoyalityRanks.postValue("Sølv")
            this.nextLoyalityRanks.postValue("Gull")

            this.currentLoyalityRankImage.postValue(R.drawable.silver)
            this.nextLoyalityRankImage.postValue(R.drawable.gold)
        } else if (totalCountInt >= 120) {
            //this.remainingStamps.postValue((120 - totalCountInt).toString())
            this.currentLoyalityRanks.postValue("Gull")
            this.nextLoyalityRanks.postValue("Gull")

            this.currentLoyalityRankImage.postValue(R.drawable.gold)
            this.nextLoyalityRankImage.postValue(R.drawable.gold)
        }
    }

    fun setData() {

        userFullName.postValue(userRepository.getUserEmailAddress())
    }

}