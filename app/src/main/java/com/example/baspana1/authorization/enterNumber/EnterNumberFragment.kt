package com.example.baspana1.authorization.enterNumber

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthEnterNumberBinding
import com.google.android.material.textfield.TextInputEditText
import com.kusu.loadingbutton.LoadingButton

class EnterNumberFragment : Fragment() {
    private lateinit var sigInButton : LoadingButton
    private lateinit var phoneNumberEditText : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAuthEnterNumberBinding>(inflater, R.layout.fragment_auth_enter_number, container, false)
        sigInButton = binding.signInButton
        phoneNumberEditText = binding.editTextPhoneNumber

        binding.signInButton.setOnClickListener {
            sigInButton.showLoading()
            it.findNavController().navigate(R.id.action_enterNumberFragment_to_enterSmsFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        phoneNumberEditText.doOnTextChanged { text, start, before, count ->
            sigInButton.buttonColor = R.color.colorButtonPrimary
            sigInButton.setTextColor(Color.WHITE)
            sigInButton.isEnabled = true
        }


        super.onViewCreated(view, savedInstanceState)
    }
}