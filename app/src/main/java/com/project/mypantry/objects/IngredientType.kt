package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientType (
    var id: Int,
    var ingredientName: String,
    var ingredientImg: Int // unclear what this datatype should be
): Parcelable