package com.example.baspana1.authorization.smsCode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthEnterSmsBinding

class EnterSmsFragment : Fragment() {

    private val viewModel : EnterSmsViewModel by lazy {
        ViewModelProvider(this).get(EnterSmsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAuthEnterSmsBinding>(
            inflater,
            R.layout.fragment_auth_enter_sms,
            container,
            false
        )

        //get phone number argument from previous fragment
        val args = arguments
        val phoneNumber = args!!.getString("phoneNumber", "")
        viewModel.phoneNumber = phoneNumber

        binding.lifecycleOwner = this
        binding.enterSmsViewmodel = viewModel

        val smsCodeView = binding.smsVerifView


        smsCodeView.doAfterTextChanged {
            if(it?.length == 4) {
                viewModel.otp = it.toString()
                Log.d("debug", viewModel.phoneNumber + "\n"+ viewModel.otp)
                viewModel.onOtpEntered()
            }
        }

        viewModel.navigateToRegistration.observe(this.viewLifecycleOwner, Observer { otp ->
            otp.let {
                if (this.findNavController().currentDestination?.id == R.id.enterSmsFragment) {
                    this.findNavController()
                        .navigate(EnterSmsFragmentDirections.actionEnterSmsFragmentToRegistrationFragment(phoneNumber, it))
                    viewModel.doneNavigating()
                }
            }

        })

        binding.toolBarView.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_enterSmsFragment_to_enterNumberFragment)
        }
        return binding.root
    }

}