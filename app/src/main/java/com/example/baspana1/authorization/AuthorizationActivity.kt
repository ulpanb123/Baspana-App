package com.example.baspana1.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baspana1.AppPreferences
import com.example.baspana1.R

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Baspana1)
        setContentView(R.layout.activity_authorization)
    }
}