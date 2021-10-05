package com.example.baspana1.authorization.registration
import android.R.attr.name
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.core.net.toFile
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthRegistrationBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.kusu.loadingbutton.LoadingButton
import kotlinx.android.synthetic.main.fragment_auth_registration.*
import java.io.File


class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var avatarImageView : ShapeableImageView
    private lateinit var avatarImageFile : File
    private lateinit var loadImageView : TextView
    private lateinit var clearImageView: RelativeLayout
    private lateinit var chooseAnotherPhoto : TextView
    private lateinit var continueButton : LoadingButton
    private lateinit var nameInputEditText: TextInputEditText
    private lateinit var surnameInputEditText: TextInputEditText
    private lateinit var emailInputEditText: TextInputEditText

    private val viewModel : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAuthRegistrationBinding>(inflater, R.layout.fragment_auth_registration, container, false)

        binding.lifecycleOwner = this
        binding.registrationViewmodel = viewModel

        avatarImageView = binding.avatarImageView
        loadImageView = binding.loadImageView
        clearImageView = binding.clearImageView
        chooseAnotherPhoto = binding.chooseAnotherPhotoImageView
        continueButton = binding.ContinueButton


        nameInputEditText = binding.nameInputEditText
        surnameInputEditText = binding.surnameEditTextView
        emailInputEditText = binding.emailEditTextView


        nameInputEditText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if(!text.contains(Regex("[$&+,:;=?@#|'<>.^*()%!-123456789]"))) {
                    nameInputEditText.setTextColor(Color.BLACK)
                } else {
                    nameInputEditText.setTextColor(Color.RED)
                }
                checkRequiredFields()
            }
        }

        surnameInputEditText.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if(!text.contains(Regex("[$&+,:;=?@#|'<>.^*()%!-123456789]"))) {
                    surnameInputEditText.setTextColor(Color.BLACK)
                } else {
                    surnameInputEditText.setTextColor(Color.RED)
                }
                checkRequiredFields()
            }
        }

        emailInputEditText.doOnTextChanged { text, start, before, count ->
            if(text != null) {
                if(Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                    emailInputEditText.setTextColor(Color.BLACK)
                } else {
                    emailInputEditText.setTextColor(Color.RED)
                }
                checkRequiredFields()
            }
        }



        loadImageView.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                    .maxResultSize(1080, 1080)
                .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                .start()
            //make load image button invisible, and change image and clear image buttons visible
            Log.d("ImageTest", "set")

        }

        binding.clearImageView.setOnClickListener {
            avatarImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_camera))

            it.visibility = View.INVISIBLE
            binding.chooseAnotherPhotoImageView.visibility = View.GONE
            binding.loadImageView.visibility = View.VISIBLE
        }

        binding.chooseAnotherPhotoImageView.setOnClickListener {
            ImagePicker.with(this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                    .start()
        }

       viewModel.navigateToMainActivity.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                this.findNavController().navigate(RegistrationFragmentDirections.actionAuthActivityToMainActivity())
                viewModel.doneNavigating()
            }
        })

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

            //set visibilities
            loadImageView.visibility = View.GONE
            chooseAnotherPhotoImageView.visibility = View.VISIBLE
            clearImageView.visibility = View.VISIBLE

            avatarImageFile = uri.toFile()
            viewModel.postAvatar(avatarImageFile)

    }

    private fun checkRequiredFields() {
        val isInputValid : Boolean = nameInputEditText.currentTextColor == Color.BLACK
                && surnameInputEditText.currentTextColor == Color.BLACK
                && emailInputEditText.currentTextColor == Color.BLACK
        if(nameInputEditText.text.toString().isNotEmpty() && surnameInputEditText.text.toString().isNotEmpty()
                && emailInputEditText.text.toString().isNotEmpty() && isInputValid) {
                    //enable the button
            continueButton.isEnabled = true
            continueButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorButtonPrimary))
            continueButton.setTextColor(Color.WHITE)

            //change values in viewmodel
            viewModel.firstName = nameInputEditText.text.toString()
            viewModel.lastName = surnameInputEditText.text.toString()
            viewModel.email = emailInputEditText.text.toString()
        } else {
            continueButton.isEnabled = false
            continueButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray6))
            continueButton.setTextColor(resources.getColor(R.color.colorGray7))
        }
    }

}