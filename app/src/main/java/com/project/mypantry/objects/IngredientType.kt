package com.project.mypantry.objects

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientType (
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("ingredientName")
    @Expose
    var ingredientName: String,

    @SerializedName("ingredientImg")
    @Expose
    var ingredientImg: String // unclear what this datatype should be
): Parcelable