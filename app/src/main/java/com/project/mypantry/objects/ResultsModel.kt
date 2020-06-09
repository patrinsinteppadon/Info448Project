package com.project.mypantry.objects

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ResultsModel(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("imageType")
    @Expose
    val imageType: String ,

    @SerializedName("usedIngredientCount")
    @Expose
    val usedIngredientCount: Int,

    @SerializedName("missedIngredientCount")
    @Expose
    val missedIngredientCount: Int,

    @SerializedName("missedIngredients")
    @Expose
    val missedIngredients: List<ResultsIngredients>,

    @SerializedName("unusedIngredients")
    @Expose
    val usedIngredients: List<ResultsIngredients>,

    @SerializedName("usedIngredients")
    @Expose
    val unusedIngredients: List<ResultsIngredients>,

    @SerializedName("likes")
    @Expose
    val likes: Int

): Parcelable
//Recipe/////
