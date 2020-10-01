package com.example.githubuserapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.R
import com.example.githubuserapp.models.User
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var getUserData: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val usernameRecieved: TextView = findViewById(R.id.tv_username_recieved)
        val nameRecieved: TextView = findViewById(R.id.tv_name_recieved)
        val avatarRecieved: CircleImageView = findViewById(R.id.img_avatar_recieved)
        val companyRecieved: TextView = findViewById(R.id.tv_company_recieved)
        val locationRecieved: TextView = findViewById(R.id.tv_location_recieved)
        val repositoryRecieved: TextView = findViewById(R.id.tv_repository_recieved)
        val followersRecieved: TextView = findViewById(R.id.tv_followers_recieved)
        val followingRecieved: TextView = findViewById(R.id.tv_following_recieved)
        val headerAvatar: RoundedImageView = findViewById(R.id.header_avatar)

        getUserData = intent.getParcelableExtra(EXTRA_USER) as User

        usernameRecieved.text = getUserData.username
        nameRecieved.text = getUserData.name
        companyRecieved.text = getUserData.company
        locationRecieved.text = getUserData.location
        repositoryRecieved.text = getUserData.repository
        followersRecieved.text = getUserData.followers
        followingRecieved.text = getUserData.following
        Glide.with(this)
            .load(getUserData.avatar)
            .apply(RequestOptions())
            .into(avatarRecieved)
        Glide.with(this)
            .load(getUserData.avatar)
            .apply(RequestOptions())
            .into(headerAvatar)

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun share(view: View) {
        try {
            val text = """
                    I'm using the GitHub User Application and i found :
                    
                    ${getUserData.username} 
                    work at ${getUserData.company} 
                    and located at ${getUserData.location}.
                    
                    Install the GitHub User Application on Playstore to find out more Users!
                """.trimIndent()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Share fail! Please download WhatsApp Application",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

}
