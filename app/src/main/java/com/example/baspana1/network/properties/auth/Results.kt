package com.example.baspana1.network.properties.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefreshToken(
    val access : String
) : Parcelable

@Parcelize
data class VerifiedUser(
    val authenticated : Boolean,
    val completed : Boolean,
    val access : String,
    val refresh : String
) : Parcelable
