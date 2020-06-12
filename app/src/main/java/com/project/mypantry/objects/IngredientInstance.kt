package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientInstance (
    var instanceID: Int,
    var ingredientID: Int,
    var amount: Int,
    var unit: String,
    var expiration: String
): Parcelable