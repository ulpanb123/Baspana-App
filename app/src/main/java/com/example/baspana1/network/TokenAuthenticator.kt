package com.example.baspana1.network

import android.content.Context
import com.example.baspana1.network.properties.auth.RefreshToken
import com.example.baspana1.network.properties.auth.RefreshTokenRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.Authenticator

class TokenAuthenticator(
        private val uiScope: CoroutineScope,
      private val refresh : String
) : okhttp3.Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        //implement refresh token
        val refreshTokenRequest = RefreshTokenRequest(refresh)
        var newAccess = RefreshToken("")
        uiScope.launch {
            newAccess = BaspanaApi.retrofitService.makeRefreshToken(refreshTokenRequest)
        }
        return response.request().newBuilder()
                .header("newAccessToken", newAccess.access)
                .build()

    }





}


