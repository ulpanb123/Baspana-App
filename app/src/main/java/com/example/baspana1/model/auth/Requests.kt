package com.example.baspana1.model.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefreshTokenRequest(
    val refresh : String
) : Parcelable

@Parcelize
data class LoginWithPhoneRequest(
    val phone : String
) : Parcelable

@Parcelize
data class VerifyUserRequest(
    val phone : String,
    val otp : String
) : Parcelable
