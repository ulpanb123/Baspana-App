package com.example.baspana1.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
   private lateinit var adapter : HomeActionsAdapter
   private var list = ArrayList<HomeActionItems>()

   private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        Log.d("HomeFragment", "here works")

      /*  binding.collapsingToolbar.isTitleEnabled = false
        binding.collapsingToolbar.title = resources.getString(R.string.navigation_home)*/

        return binding.root
    }






}