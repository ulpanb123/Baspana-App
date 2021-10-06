package com.example.baspana1.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.baspana1.R
import com.example.baspana1.authorization.registration.RegistrationViewModel
import com.example.baspana1.databinding.FragmentHomeBinding
import com.example.baspana1.main.home.adapter.AdvertsAdapter
import com.example.baspana1.model.adverts.Adverts

class HomeFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
   private lateinit var adapter : AdvertsAdapter

   private val viewModel : HomeFragmentViewmodel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewmodel::class.java)
    }

   private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.homeFragmentViewmodel = viewModel
        binding.lifecycleOwner = this
        recyclerView = binding.homeRecyclerView

        viewModel.adverts.observe(viewLifecycleOwner, Observer {
            val adverts = it
            adapter = AdvertsAdapter(adverts)
            recyclerView.adapter = adapter
        })

        return binding.root
    }






}