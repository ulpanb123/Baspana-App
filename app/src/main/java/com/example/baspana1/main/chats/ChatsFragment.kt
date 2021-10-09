package com.example.baspana1.main.chats

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentChatsBinding
import com.example.baspana1.databinding.FragmentHomeBinding
import com.example.baspana1.main.home.HomeFragmentViewmodel

class ChatsFragment : Fragment() {

    private val viewModel : ChatsViewModel by lazy {
        ViewModelProvider(this).get(ChatsViewModel::class.java)
    }

    private lateinit var binding : FragmentChatsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chats, container, false)
        return binding.root
    }



}