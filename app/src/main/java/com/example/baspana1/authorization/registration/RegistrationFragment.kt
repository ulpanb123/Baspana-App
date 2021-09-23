package com.example.baspana1.authorization.registration
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toFile
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baspana1.R
import com.example.baspana1.databinding.FragmentAuthRegistrationBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
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

        var isInputValid : Boolean = true
        var isInputNonEmpty1 : Boolean = false
        var isInputNonEmpty2 : Boolean = false
        var isInputNonEmpty3 : Boolean = false

        binding.nameInputEditText.doAfterTextChanged {
            isInputNonEmpty1 = true
            if (it != null) {
                if(it.contains(Regex("[$&+,:;=?@#|'<>.^*()%!-123456789]")) || it.isEmpty()) {
                    isInputValid = false
                    nameInputEditText.background = resources.getDrawable(R.drawable.bg_edit_text_invalid)
                } else {
                    nameInputEditText.background = resources.getDrawable(R.drawable.bg_selector_edit_text)
                }
            }
        }

        binding.surnameEditTextView.doAfterTextChanged {
            isInputNonEmpty2 = true
            if (it != null) {
                if(it.contains(Regex("[$&+,:;=?@#|'<>.^*()%!-123456789]")) || it.isEmpty()) {
                    isInputValid = false
                    surnameEditTextView.background = resources.getDrawable(R.drawable.bg_edit_text_invalid)
                } else {
                    surnameEditTextView.background = resources.getDrawable(R.drawable.bg_selector_edit_text)
                }
            }

        }

        binding.emailEditTextView.doAfterTextChanged {
            isInputNonEmpty3 = true
            if (it != null) {
                if(it.contains(Regex("[$&+,:;=?@#|'<>.^*()%!-123456789]")) || it.isEmpty()) {
                    isInputValid = false
                    emailEditTextView.background = resources.getDrawable(R.drawable.bg_edit_text_invalid)
                } else {
                    emailEditTextView.background = resources.getDrawable(R.drawable.bg_selector_edit_text)
                }
            }
        }

        updateButton(isInputValid, isInputNonEmpty1, isInputNonEmpty2, isInputNonEmpty3)

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


        binding.ContinueButton.setOnClickListener {
            if(!it.isEnabled) {
                Toast.makeText(context, "Сначала заполните все поля", Toast.LENGTH_LONG).show()
            } else {
                viewModel.email = binding.emailEditTextView.text.toString()
                viewModel.firstName = binding.nameInputEditText.text.toString()
                viewModel.lastName = binding.surnameEditTextView.text.toString()
            }
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

            //set visibilities
            loadImageView.visibility = View.GONE
            chooseAnotherPhotoImageView.visibility = View.VISIBLE
            clearImageView.visibility = View.VISIBLE

            avatarImageFile = uri.toFile()
            viewModel.postAvatar(avatarImageFile)

    }
     
    fun updateButton(isInputValid : Boolean, isInputNonEmpty1 : Boolean, isInputNonEmpty2 : Boolean, isInputNonEmpty3 : Boolean) {
        if(isInputValid && (isInputNonEmpty1 && isInputNonEmpty2 && isInputNonEmpty3)) {
            continueButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorButtonPrimary))
            continueButton.setTextColor(Color.WHITE)
            continueButton.isEnabled = true
        }
    }



}