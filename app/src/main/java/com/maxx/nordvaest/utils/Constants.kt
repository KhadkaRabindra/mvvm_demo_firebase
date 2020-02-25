package com.maxx.nordvaest.utils

import com.maxx.nordvaest.BuildConfig


class Constants {

    companion object {
        val API_BASE_URL = BuildConfig.SERVER_URL
        val REFRESH_TOKEN_URL = "api/account/token/refresh"

        //keys
        val KEY_SELECTED_COUNTRY = "selected_country"
        val KEY_SELECTED_PAYOUT = "selected_payout"
        val KEY_SELECTED_BRANCH = "selected_branch"
        val KEY_RECIPIENT_DETAIL = "intent_recipient_detail"

        //blank otp id
        val BLANK_OTP_ID = "00000000-0000-0000-0000-000000000000"

        //payout ids
        //making jvm static helps to take reference in data binding
        @JvmStatic
        val CASH_PICK_UP: Int = 2
        @JvmStatic
        val ACCOUNT_CREDIT: Int = 1
        @JvmStatic
        val UNION_PAY: Int = 21

        const val PasscodeLength = 6
        const val PasscodeTimeOut: Long = 3 * 1000
        const val PASSCODE_STATUS = "passcode_status"
        const val KEY_PASSWORD = "password"

        const val RELOAD_TOPUP = "TopUp"


        const val NotificationChannelId = "com.eightsquarei.eremitpay.ANDROID"
        const val NotificationChannelName = "ANDROID CHANNEL"

        const val KEY_SUCCESS = "key_success"
    }
}