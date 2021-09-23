package com.example.baspana1.network

import com.example.baspana1.AppPreferences
import com.example.baspana1.model.auth.RefreshTokenRequest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class TokenInterceptor :
    okhttp3.Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();

        //get response after trying given request
        val response = chain.proceed(request);


        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            //close prev. response
            response.close()

            //get a new token
            var newAccessToken : String
            runBlocking {
                val refreshToken = AppPreferences.refreshToken

                val refreshRequest = refreshToken?.let { RefreshTokenRequest(it) }
                newAccessToken = (refreshRequest?.let { BaspanaApi.retrofitService.makeRefreshToken(it) })?.access.toString()
            }

            val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $newAccessToken")
                    .build()

            //retry with new request
            return chain.proceed(newRequest)
        }
        return response
    }
}