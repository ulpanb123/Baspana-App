package com.example.baspana1.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baspana1.R
import com.example.baspana1.databinding.ItemAdvertDetailsHeaderImageBinding
import com.example.baspana1.databinding.ItemAdvertsDetailsGenInfoBinding
import com.example.baspana1.model.adverts.AdvertItemImage

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.AdvertImageViewholder>() {
    class AdvertImageViewholder(val binding : ItemAdvertDetailsHeaderImageBinding) : RecyclerView.ViewHolder(binding.root)

    private var images = mutableListOf<AdvertItemImage>()

    fun setImages(images : List<AdvertItemImage>) {
        this.images = images.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return images.size
    }



    override fun onBindViewHolder(holder: AdvertImageViewholder, position: Int) {
        bindImage(holder.binding.headerImageView, images[position].image)
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {

        Glide.with(imgView.context)
                .load("https://api.oz-uyim.kz$imgUrl")
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertImageViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdvertDetailsHeaderImageBinding.inflate(inflater, parent, false)
        return ImagesAdapter.AdvertImageViewholder(binding)
    }


}