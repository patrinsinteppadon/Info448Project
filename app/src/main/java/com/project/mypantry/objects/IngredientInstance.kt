package com.project.mypantry.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
data class IngredientInstance (
    var instanceID: Int,
    var ingredientID: Int, // maybe doesn't have to be Int
    var amount: Int,
    var unit: String,
    var expiration: LocalDate // should it be date? Maybe some other type
): Parcelable