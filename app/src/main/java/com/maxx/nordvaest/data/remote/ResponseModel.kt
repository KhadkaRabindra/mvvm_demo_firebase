package com.maxx.nordvaest.data.remote

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by rakezb on 4/19/2019.
 */
data class PostCodeResponse(
    @SerializedName("State") val state: String? = null,
    @SerializedName("City") val city: String? = null,
    @SerializedName("Id") val id: String? = null,
    @SerializedName("Text") val text: String? = null
)

data class LoginResponse(
    @SerializedName("DeviceKey") var deviceKey: String?,
    @SerializedName("RefreshToken") var refreshToken: String?,
    @SerializedName("Name") var name: String?,
    @SerializedName("PhoneNumber") var phoneNumber: String?,
    @SerializedName("EmailAddress") var emailAddress: String?,
    @SerializedName("ForceChangePassword") var forceChangePassword: Boolean?,
    @SerializedName("EmailAddressConfirmed") var emailAddressConfirmed: Boolean?,
    @SerializedName("LastLoginDate") var lastLoginDate: String?,
    @SerializedName("DeviceKeyExpiryDate") var deviceKeyExpiryDate: String?,
    @SerializedName("IPAddress") var iPAddress: String?,
    @SerializedName("UserDetail") var userDetail: UserDetail?
)

data class UserDetail(
    @SerializedName("UserName") var userName: String?,
    @SerializedName("CCPName") var cCPName: String?,
    @SerializedName("BranchName") var branchName: String?,
    @SerializedName("Name") var name: String?,
    @SerializedName("Email") var email: String?,
    @SerializedName("MobileNumber") var mobileNumber: String?
)

class CheckPasscodeResponse(
    @SerializedName("AccessFailedCount")
    @Expose
    val accessFailedCount: Int?,
    @SerializedName("Message")
    @Expose
    val message: String?,
    @SerializedName("Date")
    @Expose
    val date: String?,
    @SerializedName("Status")
    @Expose
    val status: Boolean?,
    @SerializedName("SessionToken")
    @Expose
    val sessionToken: String?
)

data class FingerPrintSessonTokenResponse(
    @SerializedName("SessionToken") val sessionToken: String?
)

/**
 * mobile/passcode/exist response
 */
class PasscodeExistResponse(
    @SerializedName("IsAvailable") val isAvailable: Boolean?
)

@Parcelize
data class InitTransactionDetailResponse(
    @SerializedName("OtpId") val otpId: String?,
    @SerializedName("Key") val key: String?,
    @SerializedName("CustomerName") val customerName: String?,
    @SerializedName("CustomerPhoneNumber") val customerPhoneNumber: String?,
    @SerializedName("CustomerEmail") val customerEmail: String?,
    @SerializedName("MerchantName") val merchantName: String?,
    @SerializedName("TotalAmount") val totalAmount: Double?,
    @SerializedName("TransactionDate") val transactionDate: String?,
    @SerializedName("RefId") val refId: String?,
    @SerializedName("TxnId") val txnId: String?,
    @SerializedName("Status") val state: Boolean?,
    @SerializedName("ExpiryDate") val expiryDate: String?,
    @SerializedName("PayoutCountry") val payoutCountry: String
) : Parcelable

data class PrefundHistoryResponse(
    @SerializedName("TerminalId") val terminalId: String?,
    @SerializedName("TerminalName") val terminalName: String?,
    @SerializedName("TerminalAddress") val terminalAddress: String?,
    @SerializedName("Balance") val balance: String?,
    @SerializedName("HistoryDetails") val historyDetails: List<PrefundHistoryDetail?>?
)

data class PrefundHistoryDetail(
    @SerializedName("Date") val date: String?,
    @SerializedName("ReferenceId") val referenceId: String?,
    @SerializedName("Remarks") val remarks: String?,
    @SerializedName("Amount") val amount: Double?,
    @SerializedName("Type") val type: String?
)

/*Firebase*/
@IgnoreExtraProperties
@Keep
data class Brukere(
    @SerializedName("StempelDato") val StempelDato: String?,
    @SerializedName("StempelIndex") val StempelIndex: String?,
    @SerializedName("StempelNumber") val StempelNumber: String?,
    @SerializedName("User") val User: String?
)