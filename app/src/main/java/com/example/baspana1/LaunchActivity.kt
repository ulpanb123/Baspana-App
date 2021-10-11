package com.example.baspana1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baspana1.authorization.AuthorizationActivity
import com.example.baspana1.main.MainActivity


class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(AppPreferences != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        } else  {
            val intent = Intent(this, AuthorizationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        }


    }
}