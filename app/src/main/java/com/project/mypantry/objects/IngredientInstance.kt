package com.project.mypantry.objects

import java.util.*

data class IngredientInstance (
    var instanceID: Int,
    var ingredientID: Int, // maybe doesn't have to be Int
    var amount: Int,
    var unit: String,
    var expiration: Date // should it be date? Maybe some other type
)