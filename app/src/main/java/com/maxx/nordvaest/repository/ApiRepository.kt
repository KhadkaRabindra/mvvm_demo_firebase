package com.eightsquarei.eremitpay.repository

import com.maxx.nordvaest.service.ApiService
import com.maxx.nordvaest.data.remote.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject
constructor() {

    @Inject
    lateinit var api: ApiService

    fun postRegistrationData(registrationPostData : RegistrationPostData) = api.postRegistrationData(registrationPostData)

    fun postLoginData(loginPostData: LoginPostData) = api.postLoginData(loginPostData)

    fun requestResetPassword(forgotPasswordPostData: ForgotPasswordPostData) = api.postForgotPasswordData(forgotPasswordPostData)

    fun getPostCodeList() = api.getPostCodeList()

    fun changePasscode(changePasscodePostData: ChangePasscodePostData) = api.changePasscode(changePasscodePostData)

    fun passcodeExist() = api.passcodeExist()

    fun getSessionTokenOnFingerPrintScan() = api.getSessionTokenOnFingerPrintScan()

    fun createPasscode(passcodePostData: PasscodePostData) = api.createPasscode(passcodePostData)

    fun checkPasscode(checkPasscodePostData: CheckPasscodePostData) = api.checkPasscode(checkPasscodePostData)

    fun postChangePasswordData(changePasswordPostData: ChangePasswordPostData) = api.postChangePasswordData(changePasswordPostData)

    fun initTransactionDetail(initTransactionPostData: InitTransactionPostData) = api.initTransactionDetail(initTransactionPostData)

    fun requestTransactionDetail(transactionDetailPostData: TransactionDetailPostData) = api.requestTransactionDetail(transactionDetailPostData)

    fun requestProcessTransaction(processTransactionPostData: ProcessTransactionPostData) = api.requestProcessTransaction(processTransactionPostData)

    fun getCurrentBalance() = api.getCurrentBalance()

    fun getBankList() = api.getBankList()

    fun requestPrefund(prefundPostData: PrefundPostData) = api.requestPrefund(prefundPostData.prefundAmount, prefundPostData.transactionDate, prefundPostData.toAccountNumber, prefundPostData.toBank, prefundPostData.file)

    fun requestPrefundHistory(historyPostData: HistoryPostData) = api.requestPrefundHistory(historyPostData)

    fun requestStatement(historyPostData: HistoryPostData) = api.requestStatement(historyPostData)

}