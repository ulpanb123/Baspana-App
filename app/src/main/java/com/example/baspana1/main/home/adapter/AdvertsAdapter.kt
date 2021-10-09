package com.example.baspana1.main.home.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baspana1.R
import com.example.baspana1.databinding.ItemAdvertsBinding
import com.example.baspana1.generated.callback.OnClickListener
import com.example.baspana1.model.adverts.AdvertItem
import kotlinx.android.synthetic.main.item_adverts.view.*
import java.text.NumberFormat
import java.util.*

class AdvertsAdapter(private val onClick: (AdvertItem) -> Unit) : RecyclerView.Adapter<AdvertsAdapter.AdvertsViewholder>() {

    private var adverts = mutableListOf<AdvertItem>()

    class AdvertsViewholder(val binding: ItemAdvertsBinding) : RecyclerView.ViewHolder(binding.root)  {
    }

    fun setAdverts(adverts: List<AdvertItem>) {
        this.adverts = adverts.toMutableList()
        notifyDataSetChanged()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AdvertsAdapter.AdvertsViewholder, position: Int) {
        val advert = adverts[position]
        bindImage(holder.binding.buildingImageView, advert.images[0].image)
        holder.binding.createdDateTextView.text = advert.created_at.subSequence(0, 10)
        holder.binding.imagesCountTextView.text = "1/${advert.images.size}"
        bindPrice(holder.binding.titleTextView, advert.price)
        holder.binding.shortInfoTextView.text = "${advert.room_count}-комн. дом " +
                "· ${advert.total_area} м² " +
                "· ${advert.flat_floor}/${advert.house_floor} этаж"
        holder.binding.addressTextView.text = "${advert.address}, " +
                "${advert.region.name}, " +
                "${advert.city.name}"
        holder.binding.titleTextView.setOnClickListener {
            onClick(advert)
        }
        holder.binding.favoriteButton.setOnClickListener {
            if(it.id == R.id.favorite_button) {
                it.setBackgroundDrawable(R.drawable.ic_favorite_pressed.toDrawable())
            }
        }

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
    @RequiresApi(Build.VERSION_CODES.N)
    fun bindPrice(textView: TextView, price: String) {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(0)
        format.setCurrency(Currency.getInstance("KZT"))

        val newPrice = format.format(price.toInt())
        textView.text = newPrice
    }






}