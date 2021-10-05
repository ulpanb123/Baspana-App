package com.example.baspana1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionCityDistrict(
        val id: String,
        val name: String
): Parcelable

/*
* Моделька типа конструкции
*/
@Parcelize
data class ConstructionType(
        val id: String,
        val type: String,
        val title: String
): Parcelable
