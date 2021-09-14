package com.example.baspana1.authorization.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.baspana1.network.BaspanaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI.create


class RegistrationViewModel : ViewModel() {
    var email : String = ""
    var firstName : String = ""
    var lastName : String = ""


    private val viewmodelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }

    fun postAvatar(imageFile: File) {
        val requestFile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
        val filePart : MultipartBody.Part = MultipartBody.Part.createFormData(
            "image", imageFile.name, requestFile)

        uiScope.launch {
            try {
                BaspanaApi.retrofitService.makeUpdateUserAvatar(filePart)
            } catch (t: Throwable) {
                Log.d("EnterNumberViewModel", t.message.toString())
            }
        }
    }


}