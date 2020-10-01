package com.example.githubuserapp.ui

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.R
import com.example.githubuserapp.adapter.RecyclerViewUserAdapter
import com.example.githubuserapp.models.User
import com.makeramen.roundedimageview.RoundedImageView


class MainActivity : AppCompatActivity() {

    private lateinit var getUsernameData: Array<String>
    private lateinit var getNameData: Array<String>
    private lateinit var getAvatarData: TypedArray
    private lateinit var getCompanyData: Array<String>
    private lateinit var getLocationData: Array<String>
    private lateinit var getRepositoryData: Array<String>
    private lateinit var getFollowersData: Array<String>
    private lateinit var getFollowingData: Array<String>

    private lateinit var usernameMe: TextView
    private lateinit var companyMe: TextView
    private lateinit var btnAbout: Button
    private lateinit var usernameMeActionBar: TextView
    private lateinit var companyMeActionBar: TextView
    private lateinit var btnAboutActionBar: Button

    private lateinit var headerContainer: RelativeLayout
    private lateinit var actionbarContainer: RelativeLayout
    private lateinit var scrollView: ScrollView
    private lateinit var headerColor: ImageView
    private lateinit var headerImageview: RoundedImageView

    private lateinit var rvUser: RecyclerView
    private lateinit var adapter: RecyclerViewUserAdapter
    private var listUser: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_all_user)
        rvUser.setHasFixedSize(true)

        //getData from SharedPreferences
        getUserData()

        //RecyclerView
        prepare()
        addItem()
        showRecyclerViewUser()

        //GOTO AboutActivity
        btnAbout = findViewById(R.id.btn_about)
        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        btnAboutActionBar = findViewById(R.id.btn_about_actionbar)
        btnAboutActionBar.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        headerContainer = findViewById(R.id.header_container)
        actionbarContainer = findViewById(R.id.actionbar_container)
        scrollView = findViewById(R.id.scrollview)
        headerColor = findViewById(R.id.header_color)
        headerImageview = findViewById(R.id.header_imageview)

        actionbarContainer.alpha = 0F

        scrollView.viewTreeObserver
            .addOnScrollChangedListener {
                /* get the maximum height which we have scroll before performing any action */
                val maxDistance: Int = headerColor.height
                /* how much we have scrolled */
                val movement = scrollView.scrollY
                /*finally calculate the alpha factor and set on the view */
                val alphaFactor: Float = movement * 1.0f / (maxDistance - actionbarContainer.height)

                if (movement in 0..maxDistance) { /*for image parallax with scroll */
                    headerColor.translationY = (-movement / 2).toFloat()
                    headerImageview.translationY = (-movement / 2).toFloat()
                    /* set visibility */actionbarContainer.alpha = alphaFactor
                }
            }
    }

    private fun getUserData() {

        val sharedPreferences = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        )

        val getUsername = sharedPreferences.getString("usernameData", "")
        val getCompany = sharedPreferences.getString("companyData", "")
        val getUsernameActionBar = sharedPreferences.getString("usernameData", "")
        val getCompanyActionBar = sharedPreferences.getString("companyData", "")

        usernameMe = findViewById(R.id.username_me)
        usernameMe.text = getUsername
        companyMe = findViewById(R.id.company_me)
        companyMe.text = getCompany
        usernameMeActionBar = findViewById(R.id.username_me_actionbar)
        usernameMeActionBar.text = getUsernameActionBar
        companyMeActionBar = findViewById(R.id.company_me_actionbar)
        companyMeActionBar.text = getCompanyActionBar

    }

    private fun showRecyclerViewUser() {
        rvUser.layoutManager = GridLayoutManager(this, 2)
        adapter = RecyclerViewUserAdapter(this, listUser)
        rvUser.adapter = adapter

        adapter.setOnItemClickCallback(object : RecyclerViewUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Toast.makeText(this@MainActivity, "${data.username}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, data)
                ContextCompat.startActivity(this@MainActivity, intent, Bundle())
            }
        })
    }

    private fun prepare() {
        getUsernameData = resources.getStringArray(R.array.username)
        getNameData = resources.getStringArray(R.array.name)
        getAvatarData = resources.obtainTypedArray(R.array.avatar)
        getCompanyData = resources.getStringArray(R.array.company)
        getLocationData = resources.getStringArray(R.array.location)
        getRepositoryData = resources.getStringArray(R.array.repository)
        getFollowersData = resources.getStringArray(R.array.followers)
        getFollowingData = resources.getStringArray(R.array.following)
    }

    private fun addItem() {
        listUser = arrayListOf()
        for (i in getUsernameData.indices) {
            val user = User(
                username = null,
                name = null,
                avatar = null,
                company = null,
                location = null,
                repository = null,
                followers = null,
                following = null
            )
            user.username = getUsernameData[i]
            user.name = getNameData[i]
            user.avatar = getAvatarData.getResourceId(i, 0)
            user.company = getCompanyData[i]
            user.location = getLocationData[i]
            user.repository = getRepositoryData[i]
            user.followers = getFollowersData[i]
            user.following = getFollowingData[i]
            listUser.add(user)
        }
    }
}
