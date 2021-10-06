package com.example.baspana1.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baspana1.R
import com.example.baspana1.model.adverts.Adverts
import com.example.baspana1.network.BaspanaApi
import kotlinx.coroutines.*

class HomeFragmentViewmodel : ViewModel() {

    private val viewmodelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    private var _adverts = MutableLiveData<Adverts>()
    val adverts : LiveData<Adverts>
        get() = _adverts

    init {
        getAdvertsList()
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }


    private fun getAdvertsList() {
        uiScope.launch {
            try {
                _adverts.value = BaspanaApi.retrofitService.getAdverts()
            } catch (t: Throwable) {
                Log.d("HomeFragment.ViewModel", t.message.toString())
            }
        }
    }
}