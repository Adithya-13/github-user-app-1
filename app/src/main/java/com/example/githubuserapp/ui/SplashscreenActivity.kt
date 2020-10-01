package com.example.githubuserapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserapp.R

class SplashscreenActivity : AppCompatActivity() {

    private val timeOut: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        if (sharedPreferences.all.isNotEmpty()) {
            mainActivity()
        } else {
            getUserActivity()
        }
    }

    private fun getUserActivity() {
        Handler().postDelayed({
            val intent = Intent(this, GetUserDataActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }

    private fun mainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }
}