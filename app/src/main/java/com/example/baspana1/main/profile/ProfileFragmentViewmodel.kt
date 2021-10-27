package com.example.baspana1.main.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baspana1.model.adverts.AdvertItem
import com.example.baspana1.model.profile.Profile
import com.example.baspana1.network.BaspanaApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileFragmentViewmodel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val profile = MutableLiveData<Profile>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()


    fun getUserProfile() {
        viewModelScope.launch  {
            try {
                val response = BaspanaApi.retrofitService.getUserProfile()
                profile.postValue(response)
                loading.value = false
            } catch (t : Throwable) {
                onError(t.message!!)
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
        Log.d("ProfileViewmodel", message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}