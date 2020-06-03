package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    var id: Int,
    var name: String,
    var img: Int, // unsure what data type should be
    val ingredients: List<IngredientType>
): Parcelable