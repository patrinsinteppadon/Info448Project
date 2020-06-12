package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    var id: Int,
    var name: String,
    var img: String,
    val link: String
): Parcelable