package com.example.baspana1.network

import com.example.baspana1.model.auth.*
import com.example.baspana1.model.profile.Avatar
import com.example.baspana1.model.profile.Profile
import com.example.baspana1.model.profile.UpdateProfileRequest
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.oz-uyim.kz/api/"

private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)


private val okHttpBuilder = okhttp3.OkHttpClient.Builder()
        .authenticator(TokenAuthenticator())
        .addInterceptor(logging)

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okHttpBuilder.build())
        .build()


interface ApiService {

    // Auth

    @POST("v1/account/login/")
    suspend fun makeLogInWithPhone(
            @Body param:  LoginWithPhoneRequest
    )


    @POST("token/refresh/")
    suspend fun makeRefreshToken(
            @Body param : RefreshTokenRequest
    ) : RefreshToken

    @POST("v1/account/login/verify/")
    suspend fun makeVerifyUser(
            @Body param : VerifyUserRequest
    ) : VerifiedUser

    //Profile
    @PATCH("v1/account/profile/")
    suspend fun updateProfile(
            @Body param: UpdateProfileRequest
    ) : Profile

    @GET("v1/account/profile/")
    suspend fun getUserProfile() : Profile

    @Multipart
    @POST("v1/account/avatar/")
    suspend fun makeUpdateUserAvatar(@Part file: MultipartBody.Part?)
}

object BaspanaApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}