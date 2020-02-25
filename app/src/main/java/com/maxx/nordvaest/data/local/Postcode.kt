package com.maxx.nordvaest.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Postcode(
    @SerializedName("Id") var id: String?,
    @SerializedName("City") var city: String?,
    @SerializedName("State") var state: String?,
    @SerializedName("Text") var text: String?
) : Parcelable