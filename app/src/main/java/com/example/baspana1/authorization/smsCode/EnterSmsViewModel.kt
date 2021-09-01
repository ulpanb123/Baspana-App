package com.example.baspana1.authorization.smsCode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baspana1.network.BaspanaApi
import com.example.baspana1.network.properties.auth.RefreshToken
import com.example.baspana1.network.properties.auth.RefreshTokenRequest
import com.example.baspana1.network.properties.auth.VerifiedUser
import com.example.baspana1.network.properties.auth.VerifyUserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EnterSmsViewModel : ViewModel() {

    var otp : String = ""
    var phoneNumber : String = ""

    private val viewmodelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }

    private var _navigateToRegistration = MutableLiveData<String>()
    val navigateToRegistration : LiveData<String>
        get() = _navigateToRegistration

    fun doneNavigating() {
        _navigateToRegistration.value = ""
    }

    //called after otp code was entered
    fun onOtpEntered() {
        _navigateToRegistration.value = otp
        uiScope.launch {
            val verifyRequest  = VerifyUserRequest(phoneNumber, otp)
            try {
                val verifiedUser : VerifiedUser = BaspanaApi.retrofitService.makeVerifyUser(verifyRequest)
                if(verifiedUser.completed) {
                    val refreshToken : RefreshToken = BaspanaApi.retrofitService.makeRefreshToken(RefreshTokenRequest(verifiedUser.refresh))
                }
                Log.d("EnterSmsViewModel", "Success")
            } catch (t : Throwable) {
                Log.d("EnterSmsViewModel", t.message.toString())

            }

        }

    }
}