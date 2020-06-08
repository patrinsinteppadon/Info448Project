package com.project.mypantry.objects

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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