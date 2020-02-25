package com.maxx.nordvaest.service

import com.maxx.nordvaest.data.remote.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*


interface ApiService {

    /*Tutorials Start*/
    @POST("/api/account/login")
    fun postLoginData(@Body loginPostData: LoginPostData): Completable

    @POST("/api/account/registration")
    fun postRegistrationData(@Body registrationPostData : RegistrationPostData): Completable

    @POST("")
    fun postForgotPasswordData(@Body forgotPasswordPostData: ForgotPasswordPostData) : Completable

    /*Tutorials End*/


    @GET("/api/list/postcode")
    fun getPostCodeList(): Observable<List<PostCodeResponse?>?>

    /*passcode crud*/
    @POST("/api/mobile/passcode/change")
    fun changePasscode(@Body postData: ChangePasscodePostData): Single<FingerPrintSessonTokenResponse>

    @GET("/api/mobile/passcode/exist")
    fun passcodeExist(): Observable<PasscodeExistResponse>

    @GET("/api/mobile/getsession")
    fun getSessionTokenOnFingerPrintScan(): Observable<FingerPrintSessonTokenResponse>

    @POST("/api/mobile/passcode/add")
    fun createPasscode(@Body postData: PasscodePostData): Observable<FingerPrintSessonTokenResponse>

    @POST("/api/mobile/passcode/check")
    fun checkPasscode(@Body postData: CheckPasscodePostData): Observable<CheckPasscodeResponse>

    @POST("/api/account/changepassword")
    fun postChangePasswordData(@Body changePasswordPostData: ChangePasswordPostData): Completable

    @POST("/api/ccpapp/inittxndetail")
    fun initTransactionDetail(@Body initTransactionPostData: InitTransactionPostData): Observable<InitTransactionDetailResponse>

    @POST("api/ccpapp/txndetail")
    fun requestTransactionDetail(@Body transactionDetailPostData: TransactionDetailPostData): Observable<InitTransactionDetailResponse>

    @POST("api/ccpapp/processtxn")
    fun requestProcessTransaction(@Body processTransactionPostData: ProcessTransactionPostData): Completable

    @GET("api/ccpapp/balanceenquiry")
    fun getCurrentBalance(): Observable<BalanceEnquiryResponse>

    @GET("api/ccpapp/mtabankaccdetails")
    fun getBankList(): Observable<List<BankDetail?>?>

    @Multipart
    @POST("api/ccpapp/topuprequest")
    fun requestPrefund(@Part("PrefundAmount") prefundAmount: String,
                        @Part("TransactionDate") transactionDate: String,
                        @Part("ToAccountNumber") toAccountNumber: String,
                        @Part("ToBank") toBank: String,
                        @Part file: MultipartBody.Part?): Completable

    @POST("api/ccpapp/prefundhistory")
    fun requestPrefundHistory(@Body historyPostData: HistoryPostData): Observable<PrefundHistoryResponse>

    @POST("api/ccpapp/partnerstatement")
    fun requestStatement(@Body historyPostData: HistoryPostData): Observable<PrefundHistoryResponse>
}