package com.example.baspana1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baspana1.authorization.AuthorizationActivity
import com.example.baspana1.main.MainActivity
import com.example.baspana1.network.BaspanaApi


class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.setup(applicationContext)
        setTheme(R.style.Theme_Baspana1)

        val intent = Intent(this, AuthorizationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        setContentView(R.layout.activity_authorization)
        startActivity(intent)

     /*   if(AppPreferences.userPhone == null || AppPreferences.userOtp == null) {
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setContentView(R.layout.activity_authorization)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setContentView(R.layout.activity_main)
            startActivity(intent)
        }*/
    }
}