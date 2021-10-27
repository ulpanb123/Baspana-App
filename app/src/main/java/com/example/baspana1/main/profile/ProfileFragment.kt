package com.example.baspana1.main.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baspana1.AppPreferences
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentChatsBinding
import com.example.baspana1.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private val viewModel : ProfileFragmentViewmodel by lazy {
        ViewModelProvider(this).get(ProfileFragmentViewmodel::class.java)
    }

    private lateinit var binding : FragmentProfileBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        binding.lifecycleOwner = this

        viewModel.getUserProfile()

        binding.userNameTextView.text = AppPreferences.userPhone

        return binding.root
    }
}