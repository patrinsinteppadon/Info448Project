package com.project.mypantry.objects

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultsIngredients(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("amount")
    @Expose
    val amount: Double,

    @SerializedName("unit")
    @Expose
    val unit: String,

    @SerializedName("unitLong")
    @Expose
    val unitLong: String,

    @SerializedName("unitShort")
    @Expose
    val unitShort: String,

    val aisle: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("original")
    @Expose
    val original: String,

    @SerializedName("originalString")
    @Expose
    val originalString: String,

    @SerializedName("originalName")
    @Expose
    val originalName: String,

    @SerializedName("metaInformation")
    @Expose
    val metaInformation: List<String>,

    @SerializedName("meta")
    @Expose
    val meta: List<String>,

    @SerializedName("image")
    @Expose
    val image: String

): Parcelable
