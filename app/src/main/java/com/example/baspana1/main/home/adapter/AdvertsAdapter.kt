package com.example.baspana1.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baspana1.R
import com.example.baspana1.model.adverts.AdvertItem
import com.example.baspana1.model.adverts.Adverts

class AdvertsAdapter(var adverts: Adverts) : RecyclerView.Adapter<AdvertsAdapter.AdvertsViewholder>() {

    class AdvertsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var createdAt : TextView
        var imageCount : TextView
        var price : TextView
        var shortInfo : TextView
        var address : TextView

        init{
            image = itemView.findViewById(R.id.buildingImageView)
            createdAt = itemView.findViewById(R.id.createdDateTextView)
            imageCount = itemView.findViewById(R.id.imagesCountTextView)
            price = itemView.findViewById(R.id.titleTextView)
            shortInfo = itemView.findViewById(R.id.shortInfoTextView)
            address = itemView.findViewById(R.id.addressTextView)
        }
    }



    override fun onBindViewHolder(holder: AdvertsAdapter.AdvertsViewholder, position: Int) {
        bindImage(holder.image, adverts.results[position].images[0].image)
        holder.createdAt.text = adverts.results[position].created_at
        holder.imageCount.text = "1/${adverts.results[position].images.size}"
        holder.price.text = adverts.results[position].price
        holder.shortInfo.text = "${adverts.results[position].room_count}-комн. дом " +
                "· ${adverts.results[position].total_area} м² " +
                "· ${adverts.results[position].flat_floor}/${adverts.results[position].house_floor} этаж"
        holder.address.text = "${adverts.results[position].address}, " +
                "${adverts.results[position].region}, " +
                "${adverts.results[position].city}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertsAdapter.AdvertsViewholder {
        val itemView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_adverts, parent,false)
        return AdvertsViewholder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgView)
        }
    }

}