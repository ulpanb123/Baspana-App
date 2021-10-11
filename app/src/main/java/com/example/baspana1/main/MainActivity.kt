package com.example.baspana1.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baspana1.AppPreferences
import com.example.baspana1.R
import com.example.baspana1.authorization.AuthorizationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.setup(applicationContext)
        setTheme(R.style.Theme_Baspana1)
        if(AppPreferences.userPhone == null || AppPreferences.userOtp == null) {
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setContentView(R.layout.activity_authorization)
            startActivity(intent)
        }
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment1)

        bottomNavigationView.setupWithNavController(navController)
    }
}