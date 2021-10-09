package com.example.baspana1.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentNetworkFailBinding

class NetworkFailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentNetworkFailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_network_fail, container, false)
        return binding.root
    }
}