package com.example.londondestination

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyData(
    val nama: String,
    val description: String,
    val descriptionsingkat: String,
    val year: Int,
    val image:Int,
    val link: String
): Parcelable
