package com.example.baspana1.authorization.registration

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthRegistrationBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
import java.io.File

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var avatarImageView : ShapeableImageView
    private lateinit var avatarImageFile : File

    private val viewModel : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAuthRegistrationBinding>(inflater, R.layout.fragment_auth_registration, container, false)

        binding.lifecycleOwner = this
        binding.registrationViewmodel = viewModel

        avatarImageView = binding.avatarImageView

        binding.loadImageView.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                .start()
        }

        binding.toolBarView.setNavigationOnClickListener {
            this.findNavController().navigate(R.id.action_registrationFragment_to_enterSmsFragment)
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


            //Image Uri will not be null for RESULT_OK
            val uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
            avatarImageView.setImageURI(uri)

            avatarImageFile = uri.toFile()
            viewModel.postAvatar(avatarImageFile)

    }



}