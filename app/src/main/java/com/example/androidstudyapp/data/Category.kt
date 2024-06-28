package com.example.androidstudyapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
) : Parcelable
