package com.example.baspana1.authorization.smsCode

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baspana1.AppPreferences
import com.example.baspana1.network.BaspanaApi
import com.example.baspana1.model.auth.VerifiedUser
import com.example.baspana1.model.auth.VerifyUserRequest
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
                val verifiedUser  = BaspanaApi.retrofitService.makeVerifyUser(verifyRequest)
                AppPreferences.accessToken = verifiedUser.access
                AppPreferences.refreshToken = verifiedUser.refresh

                /*
                 * for later implementation:
                 * (if verifiedUser.completed) -> navigate to main menu
                 * else -> navigate to registration page
                 */

                Log.d("EnterSmsViewModel", "Success")
            } catch (t : Throwable) {
                Log.d("EnterSmsViewModel", t.message.toString())

            }

        }

        fun passPreferences(userInfo : VerifiedUser) : VerifiedUser{
            return userInfo;
        }

    }
}