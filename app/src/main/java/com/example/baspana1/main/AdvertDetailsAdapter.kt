package com.example.baspana1.main

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baspana1.R
import com.example.baspana1.databinding.ItemAdvertDetailsHeaderBinding
import com.example.baspana1.databinding.ItemAdvertDetailsHeaderImageBinding
import com.example.baspana1.databinding.ItemAdvertsBinding
import com.example.baspana1.databinding.ItemAdvertsDetailsGenInfoBinding
import com.example.baspana1.main.home.adapter.AdvertsAdapter
import com.example.baspana1.model.adverts.AdvertItem
import java.text.NumberFormat
import java.util.*

class AdvertDetailsAdapter : RecyclerView.Adapter<AdvertDetailsAdapter.AdvertGenInfo>(){

    private lateinit var chosenAdvert : AdvertItem

    class AdvertGenInfo(val binding : ItemAdvertsDetailsGenInfoBinding) : RecyclerView.ViewHolder(binding.root)

    fun setAdvert(advert : AdvertItem) {
        this.chosenAdvert = advert
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdvertsDetailsGenInfoBinding.inflate(inflater, parent, false)
        return AdvertDetailsAdapter.AdvertGenInfo(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AdvertGenInfo, position: Int) {
        bindPrice(holder.binding.priceTextView, chosenAdvert.price)
        holder.binding.locationTextView.text = "${chosenAdvert.address}, " +
                "${chosenAdvert.region.name}, " +
                "${chosenAdvert.city.name}"
        holder.binding.areaTextView.text = chosenAdvert.total_area.toString()
        holder.binding.roomsTextView.text = chosenAdvert.room_count.toString()
        holder.binding.floorTextView.text = "${chosenAdvert.flat_floor}/${chosenAdvert.house_floor}"
        holder.binding.descriptionTextView.text = chosenAdvert.description
        holder.binding.constructionYearTextView.text = chosenAdvert.construction_year.toString()
        holder.binding.constructionTypeTextView.text = chosenAdvert.construction.type
        holder.binding.bathroomTextView.text = chosenAdvert.bathroom_count.toString()
        holder.binding.livingAreaTextView.text = chosenAdvert.living_area.toString()
        holder.binding.kitchenAreaTextView.text = chosenAdvert.kitchen_area.toString()
        holder.binding.heightTextView.text = chosenAdvert.height
        holder.binding.houseFloorTextView.text = chosenAdvert.house_floor.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun bindPrice(textView: TextView, price: String) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("KZT"))

        val newPrice = format.format(price.toInt())
        textView.text = newPrice
    }

    fun bindImage(imagesRecyclerView: RecyclerView, images : List<String?>) {
        imagesRecyclerView.adapter = ImagesAdapter()


    }

}