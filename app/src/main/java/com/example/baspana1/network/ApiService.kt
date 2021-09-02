package com.example.baspana1.network

import com.example.baspana1.network.properties.auth.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://api.oz-uyim.kz/api/"

private val okHttpClient = okhttp3.OkHttpClient()

private val tokenAuthenticator = TokenAuthenticator()

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()


interface ApiService {

    @POST("v1/account/login/")
    suspend fun makeLogInWithPhone(
            @Body param : LoginWithPhoneRequest
    )

    @POST("token/refresh/")
    suspend fun makeRefreshToken(
            @Body param : RefreshTokenRequest
    ) : RefreshToken

    @POST("v1/account/login/verify/")
    suspend fun makeVerifyUser(
            @Body param : VerifyUserRequest
    ) : VerifiedUser

}

object BaspanaApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}