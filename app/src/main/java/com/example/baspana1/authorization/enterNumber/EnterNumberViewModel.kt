package com.example.baspana1.authorization.enterNumber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baspana1.network.BaspanaApi
import com.example.baspana1.model.auth.LoginWithPhoneRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EnterNumberViewModel : ViewModel() {

    var phoneNumber : String = ""

    private val viewmodelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }

    private var _navigateToSmsCode = MutableLiveData<String>()
    val navigateToSmsCode : LiveData<String>
        get() = _navigateToSmsCode

    fun doneNavigating() {
        _navigateToSmsCode.value = ""
    }

    //implement sign in button click handler
    fun onSignIn() {
        _navigateToSmsCode.value = phoneNumber

        uiScope.launch {
            val loginRequest =  LoginWithPhoneRequest(phoneNumber)
            try {
                BaspanaApi.retrofitService.makeLogInWithPhone(
                    loginRequest
                )
                Log.d("EnterNumberViewModel", "Success")
            } catch (t : Throwable) {
                Log.d("EnterNumberViewModel", t.message.toString())
            }

        }
    }




}