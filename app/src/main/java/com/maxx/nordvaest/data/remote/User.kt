package com.maxx.nordvaest.data.remote


import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("DeviceKey") var deviceKey: String? = null,
    @SerializedName("RefreshToken") var refreshToken: String? = null,
    @SerializedName("Name") var name: String? = null,
    @SerializedName("PhoneNumber") var phoneNumber: String? = null,
    @SerializedName("EmailAddress") var emailAddress: String? = null,
    @SerializedName("UserDetail") var userDetail: UserDetail? = null,
    @SerializedName("UserID") var userID: String? = null,
    @SerializedName("DeviceIdentifier") var deviceIdentifier: String? = null
)
