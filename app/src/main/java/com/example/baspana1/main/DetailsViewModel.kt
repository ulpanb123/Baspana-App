package com.example.baspana1.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baspana1.model.adverts.AdvertItem
import com.example.baspana1.network.BaspanaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    var itemId : Int = 0

    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    var advertItem = MutableLiveData<AdvertItem>()


    fun getAdvertItemInfo(itemId : Int) {
        viewModelScope.launch  {
            try {
                val response = BaspanaApi.retrofitService.getAdvertById(itemId)
                advertItem.value = response
                loading.value = false
            } catch (t : Throwable) {
                onError(t.message!!)
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
        Log.d("DetailsViewmodel1", message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }



}