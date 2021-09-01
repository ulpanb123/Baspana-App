package com.example.baspana1.authorization.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthRegistrationBinding

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private val viewModel : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAuthRegistrationBinding>(inflater, R.layout.fragment_auth_registration, container, false)

        binding.lifecycleOwner = this
        binding.registrationViewmodel = viewModel

        binding.toolBarView.setNavigationOnClickListener {
            this.findNavController().navigate(R.id.action_registrationFragment_to_enterSmsFragment)
        }
        return binding.root
    }



}