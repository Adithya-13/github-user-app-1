package com.example.githubuserapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserapp.R

class GetUserDataActivity : AppCompatActivity() {

    private lateinit var usernameSubmited: EditText
    private lateinit var nameSubmited: EditText
    private lateinit var companySubmited: EditText
    private lateinit var locationSubmited: EditText
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_user_data)

        usernameSubmited = findViewById(R.id.edt_text_username)
        nameSubmited = findViewById(R.id.edt_text_name)
        companySubmited = findViewById(R.id.edt_text_company)
        locationSubmited = findViewById(R.id.edt_text_location)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            if (usernameSubmited.text.toString().isEmpty() ||
                nameSubmited.text.toString().isEmpty() ||
                companySubmited.text.toString().isEmpty() ||
                locationSubmited.text.toString().isEmpty()
            ) {

                Toast.makeText(this, "Please fill in all", Toast.LENGTH_SHORT).show()

            } else {

                startActivity(Intent(this, MainActivity::class.java))
                finish()
                userData()

            }
        }
    }

    private fun userData() {

        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()

        editor.putString("usernameData", usernameSubmited.text.toString())
        editor.putString("nameData", nameSubmited.text.toString())
        editor.putString("companyData", companySubmited.text.toString())
        editor.putString("locationData", locationSubmited.text.toString())
        editor.apply()
        editor.commit()

    }
}
