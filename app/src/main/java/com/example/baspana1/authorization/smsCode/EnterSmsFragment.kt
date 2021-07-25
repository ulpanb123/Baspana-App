package com.example.baspana1.authorization.smsCode

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthEnterSmsBinding

class EnterSmsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAuthEnterSmsBinding>(inflater, R.layout.fragment_auth_enter_sms, container, false)
        val smsCodeView = binding.smsVerifView
        smsCodeView.doAfterTextChanged {
            if(it?.length == 4)
                this.findNavController().navigate(R.id.action_enterSmsFragment_to_registrationFragment)
        }

        binding.toolBarView.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_enterSmsFragment_to_enterNumberFragment)
        }
        return binding.root
    }

}