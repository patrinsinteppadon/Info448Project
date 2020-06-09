package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Parcelize
data class Recipe (
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("img")
    @Expose
    var img: String,

    @SerializedName("ingredients")
    @Expose
    val ingredients: List<IngredientType>
): Parcelable