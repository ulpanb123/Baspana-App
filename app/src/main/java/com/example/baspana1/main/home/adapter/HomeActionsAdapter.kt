package com.example.baspana1.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baspana1.R
import com.example.baspana1.main.home.model.HomeActionItems
import kotlinx.android.synthetic.main.fragment_auth_registration.view.*

class HomeActionsAdapter : RecyclerView.Adapter<HomeActionsAdapter.HomeActionViewHolder>(){

   private lateinit var list : List<HomeActionItems>

    class HomeActionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var img_icon : ImageView
        var text_icon : TextView

        init {
            super.itemView
            img_icon = itemView.findViewById(R.id.home_action_search_image)
            text_icon = itemView.findViewById(R.id.home_action_search_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeActionViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_view, parent, false)
        return HomeActionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeActionViewHolder, position: Int) {
        holder.img_icon.setImageResource(list[position].getIcon())
        holder.text_icon.setText(list[position].getText())
    }

}