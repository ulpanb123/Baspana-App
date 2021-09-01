package com.example.baspana1.authorization.enterNumber

import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.versionedparcelable.ParcelField
import com.example.baspana1.network.BaspanaApi
import com.example.baspana1.network.properties.auth.LoginWithPhoneRequest
import com.kusu.loadingbutton.LoadingButton
import kotlinx.android.parcel.Parcelize
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