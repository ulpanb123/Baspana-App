package com.example.baspana1.main.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baspana1.model.adverts.AdvertItem
import com.example.baspana1.network.BaspanaApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewFragmentViewmodel : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val advertsList = MutableLiveData<List<AdvertItem>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()


    fun getAdvertsList() {
        viewModelScope.launch  {
            try {
                val response = BaspanaApi.retrofitService.getAdverts()
                advertsList.postValue(response.results)
                loading.value = false
            } catch (t : Throwable) {
                onError(t.message!!)
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
        Log.d("OverviewViewmodel", message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}