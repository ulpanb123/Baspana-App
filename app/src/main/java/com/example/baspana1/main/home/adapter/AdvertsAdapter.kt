package com.example.baspana1.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baspana1.R
import com.example.baspana1.databinding.ItemAdvertsBinding
import com.example.baspana1.model.adverts.AdvertItem
import com.example.baspana1.model.adverts.Adverts

class AdvertsAdapter : RecyclerView.Adapter<AdvertsAdapter.AdvertsViewholder>() {

    private var adverts = mutableListOf<AdvertItem>()

    class AdvertsViewholder(val binding: ItemAdvertsBinding) : RecyclerView.ViewHolder(binding.root)  {

    }

    fun setAdverts(adverts : List<AdvertItem>) {
        this.adverts = adverts.toMutableList()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: AdvertsAdapter.AdvertsViewholder, position: Int) {
        val advert = adverts[position]
        bindImage(holder.binding.buildingImageView, advert.images[0].image)
        holder.binding.createdDateTextView.text = advert.created_at
        holder.binding.imagesCountTextView.text = "1/${advert.images.size}"
        holder.binding.titleTextView.text = advert.price
        holder.binding.shortInfoTextView.text = "${advert.room_count}-комн. дом " +
                "· ${advert.total_area} м² " +
                "· ${advert.flat_floor}/${advert.house_floor} этаж"
        holder.binding.addressTextView.text = "${advert.address}, " +
                "${advert.region.name}, " +
                "${advert.city.name}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertsAdapter.AdvertsViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdvertsBinding.inflate(inflater, parent, false)
        return AdvertsViewholder(binding)
    }

    override fun getItemCount(): Int {
        return adverts.size
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {

            Glide.with(imgView.context)
                    .load("https://api.oz-uyim.kz$imgUrl")
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgView)

    }

}