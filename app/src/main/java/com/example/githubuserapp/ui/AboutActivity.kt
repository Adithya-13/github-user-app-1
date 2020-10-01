package com.example.githubuserapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserapp.R

class AboutActivity : AppCompatActivity() {

    private lateinit var usernameMe: TextView
    private lateinit var nameMe: TextView
    private lateinit var companyMe: TextView
    private lateinit var locationMe: TextView
    private lateinit var repositoryMe: TextView
    private lateinit var followersMe: TextView
    private lateinit var followingMe: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        usernameMe = findViewById(R.id.tv_username_me_recieved)
        nameMe = findViewById(R.id.tv_name_me_recieved)
        companyMe = findViewById(R.id.tv_company_me_recieved)
        locationMe = findViewById(R.id.tv_location_me_recieved)
        repositoryMe = findViewById(R.id.tv_repository_me_recieved)
        followersMe = findViewById(R.id.tv_followers_me_recieved)
        followingMe = findViewById(R.id.tv_following_me_recieved)
        ratingBar = findViewById(R.id.ratingstar)
        btnBack = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        getUserData()

    }

    fun sendRating(view: View?) {

        val ratingvalue: Float = ratingBar.rating
        Toast.makeText(this, "You give : $ratingvalue", Toast.LENGTH_SHORT).show()

        try {

            val text =
                "Hi, I have tried your GitHub User Application, and this is my rating for you! : $ratingvalue"
            val number = "+6281289798423"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$number&text=$text")
            startActivity(intent)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Share fail! Please download WhatsApp Application",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getUserData() {
        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

        val getUsername = sharedPreferences.getString("usernameData", "")
        val getName = sharedPreferences.getString("nameData", "")
        val getCompany = sharedPreferences.getString("companyData", "")
        val getLocation = sharedPreferences.getString("locationData", "")
        val getRepository = 0
        val getFollowers = 0
        val getFollowing = 0

        usernameMe.text = getUsername
        nameMe.text = getName
        companyMe.text = getCompany
        locationMe.text = getLocation
        repositoryMe.text = getRepository.toString()
        followersMe.text = getFollowers.toString()
        followingMe.text = getFollowing.toString()
    }
}
