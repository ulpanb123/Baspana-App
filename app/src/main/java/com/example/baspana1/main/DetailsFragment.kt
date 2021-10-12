package com.example.baspana1.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentDetailsBinding
import com.example.baspana1.model.adverts.AdvertItem

class DetailsFragment : Fragment() {

    private val viewmodel : DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }
    private lateinit var binding : FragmentDetailsBinding

    private val adapter = AdvertDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.advertDetailsRecyclerView.adapter = adapter

        val args = arguments
        val itemId = args!!.getInt("itemId", 0)

        binding.lifecycleOwner = this

        viewmodel.itemId = itemId
        viewmodel.getAdvertItemInfo(itemId)

        viewmodel.advertItem.observe(this, Observer {
            val chosenAdvert = mutableListOf<AdvertItem>()
            chosenAdvert[0] = it
            adapter.setAdvert(chosenAdvert)
        })

        return binding.root
    }

}