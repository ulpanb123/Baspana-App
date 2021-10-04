package com.example.baspana1.authorization.enterNumber

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.com.sapereaude.maskedEditText.MaskedEditText
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthEnterNumberBinding
import com.google.android.material.textfield.TextInputEditText
import com.kusu.loadingbutton.LoadingButton

class EnterNumberFragment : Fragment() {

    private val viewmodel : EnterNumberViewModel by lazy {
        ViewModelProvider(this).get(EnterNumberViewModel::class.java)
    }

    private lateinit var sigInButton : LoadingButton
    private lateinit var phoneNumberEditText : MaskedEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAuthEnterNumberBinding>(inflater, R.layout.fragment_auth_enter_number, container, false)
        sigInButton = binding.signInButton
        phoneNumberEditText = binding.editTextPhoneNumber

        binding.lifecycleOwner = this
        binding.enterNumberViewModel = viewmodel


        viewmodel.navigateToSmsCode.observe(this.viewLifecycleOwner, Observer { phoneNumber ->
            binding.signInButton.showLoading()
            phoneNumber?.let {
                if(this.findNavController().currentDestination?.id == R.id.enterNumberFragment) {
                    this.findNavController().navigate(EnterNumberFragmentDirections.actionEnterNumberFragmentToEnterSmsFragment(phoneNumber))
                    viewmodel.doneNavigating()
                }
            }
        })



        phoneNumberEditText.doOnTextChanged { text, start, before, count ->
            if(text?.length!! == 16) {
                sigInButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorButtonPrimary))
                sigInButton.setTextColor(Color.WHITE)
                sigInButton.isEnabled = true
                viewmodel.phoneNumber = text.toString()
            }
        }

        return binding.root
    }
    
}