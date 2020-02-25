package com.maxx.nordvaest.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

/**
 * Created by rakezb on 4/29/2019.
 */

data class RegistrationPostData(
    @SerializedName("FullName") val fullName: String?,
    @SerializedName("PhoneNumber") val email: String?,
    @SerializedName("Email") val mobileNumber: String?,
    @SerializedName("PassWord") val password: String?,
    @SerializedName("ConfirmPassWord") val confirmPassword: String?
)

data class ForgotPasswordPostData(
    @SerializedName("NewPassWord") val password: String?  ,
    @SerializedName("ConfirmPassWord") val confirmPassword: String?
)

/**
 * passcode check post data
 */
class CheckPasscodePostData(
    @SerializedName("Code")
    @Expose
    val code: String?
)

/**
 * device log post model
 */
class DeviceLogPostData(

    @SerializedName("DeviceToken")
    @Expose
    var deviceToken: String? = null
)


/**
 * change passcode post data
 */
class ChangePasscodePostData(
    @SerializedName("OldPasscode")
    @Expose
    val oldPasscode: String?,
    @SerializedName("NewPasscode")
    @Expose
    val newPasscode: String?,
    @SerializedName("ConfirmPasscode")
    @Expose
    val confirmPasscode: String?,
    @SerializedName("Password")
    @Expose
    val password: String?
)

/**
 * passcode add post data
 */
class PasscodePostData(
    @SerializedName("Code") val code: String?
) {
    @SerializedName("ConfirmCode")
    var confirmCode: String? = null
}

data class LoginPostData(
    @SerializedName("Email") val userName: String?,
    @SerializedName("Password") val password: String?,
    @SerializedName("DeviceIdentifier") val deviceIdentifier: String?
)

data class ChangePasswordPostData(
    @SerializedName("NewPassword") val newPassword: String?,
    @SerializedName("ConfirmPassword") val confirmPassword: String?,
    @SerializedName("OldPassword") val oldPassword: String?
)

data class InitTransactionPostData(
    @SerializedName("RefId") val referenceId: String?
)

data class TransactionDetailPostData(
    @SerializedName("RefId") val refId: String?,
    @SerializedName("Otp") val otp: String?
)

data class ProcessTransactionPostData(
    @SerializedName("RefId") val refId: String?,
    @SerializedName("Status") val status: String = "00"
)

data class BalanceEnquiryResponse(
    @SerializedName("Balance") val balance: Double?
)

data class BankDetail(
    @SerializedName("AccountNumber") val accountNumber: String?,
    @SerializedName("Id") val id: String?,
    @SerializedName("BankName") val text: String?,
    @SerializedName("IconUrl") val iconurl: String?
)

data class PrefundPostData(
    @SerializedName("PrefundAmount") val prefundAmount: String,
    @SerializedName("TransactionDate") val transactionDate: String,
    @SerializedName("ToAccountNumber") val toAccountNumber: String,
    @SerializedName("ToBank") val toBank: String,
    @SerializedName("File") val file: MultipartBody.Part?
)

data class HistoryPostData(
    @SerializedName("Year") val year: Int?,
    @SerializedName("Month") val month: Int?
)