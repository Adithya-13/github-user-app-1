package com.example.githubuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var username: String?,
    var name: String?,
    var avatar: Int?,
    var company: String?,
    var location: String?,
    var repository: String?,
    var followers: String?,
    var following: String?
) : Parcelable