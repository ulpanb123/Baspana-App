package com.example.baspana1.model.profile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    @SerializedName("id") val id: Int,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("email") val email: String?,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("photo") val photo: String?,
    @SerializedName("onesignal_id") val oneSignalId: String?
) : Parcelable

@Parcelize
data class Avatar(
    val photo : String
) : Parcelable