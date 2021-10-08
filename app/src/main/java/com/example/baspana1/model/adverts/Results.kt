package com.example.baspana1.model.adverts

import android.os.Parcelable
import com.example.baspana1.model.ConstructionType
import com.example.baspana1.model.RegionCityDistrict
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Adverts(
        val count: Int,
        val next: String?,
        val previous: Int?,
        val results: List<AdvertItem>
): Parcelable


/*
* Моделька адверта
*/
@Parcelize
data class AdvertItem(
        val id: Int,
        val region: RegionCityDistrict,
        val city: RegionCityDistrict,
        val district: RegionCityDistrict,
        val address: String,
        val room_count: Int,
        val price: String,
        val construction: ConstructionType,
        val construction_year: Int,
        val flat_floor: Int,
        val house_floor: Int,
        val height: String,
        val total_area: Double,
        val living_area: Double,
        val kitchen_area: Double,
        val bathroom_count: Int?,
//    val bathroom_type: Any?,
        val status: Boolean,
        val images: List<AdvertItemImage>,
        val created_at: String,
        val updated_at: String,
        val description: String
): Parcelable

@Parcelize
data class AdvertItemImage(
        val image: String
): Parcelable

/**
 *  После создание объявление на продажу получаем id Объявлений
 */
@Parcelize
data class CreateAdvertResult(
        val id: Int
): Parcelable
