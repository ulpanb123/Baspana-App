package com.example.baspana1

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    // TODO step 1: call `AppPreferences.setup(applicationContext)` in your MainActivity's `onCreate` method
    fun setup(context: Context) {
        // TODO step 2: set your app name here
        sharedPreferences = context.getSharedPreferences("Baspana.sharedprefs", MODE_PRIVATE)
    }

    // TODO step 4: replace these example attributes with your stored values
    var accessToken: String?
        get() = Key.ACCESS.getString()
        set(value) = Key.ACCESS.setString(value)

    var refreshToken: String?
        get() = Key.REFRESH.getString()
        set(value) = Key.REFRESH.setString(value)

    var userPhone: String?
        get() = Key.USERPHONE.getString()
        set(value) = Key.USERPHONE.setString(value)

    var userOtp : String?
        get() = Key.USEROTP.getString()
        set(value) = Key.USEROTP.setString(value)

    private enum class Key {
        ACCESS, REFRESH, USERPHONE, USEROTP; // TODO step 3: replace these cases with your stored values keys

        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null

        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}