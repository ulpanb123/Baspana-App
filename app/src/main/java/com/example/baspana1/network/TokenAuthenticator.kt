package com.example.baspana1.network

import android.content.Context
import com.example.baspana1.model.auth.RefreshToken
import com.example.baspana1.model.auth.RefreshTokenRequest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.Authenticator

class TokenAuthenticator(

) : okhttp3.Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
         return runBlocking {
            val refreshTokenRequest = RefreshTokenRequest(response.header("Authorization", "").toString())
            val refreshToken = BaspanaApi.retrofitService.makeRefreshToken(refreshTokenRequest)

            response.request.newBuilder()
                  .header("Authorization", refreshToken.access)
                    .build()
        }
    }


}