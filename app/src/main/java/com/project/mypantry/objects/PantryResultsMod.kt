package com.project.mypantry.objects

import com.project.mypantry.objects.IngredientType

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.mypantry.objects.Recipe
import kotlinx.android.parcel.Parcelize


@Parcelize
data class pantryResultsMod(
    @SerializedName("recipe")
    @Expose
    var recipe: List<Recipe>

): Parcelable
