package com.project.mypantry.objects

data class Recipe (
    var id: Int,
    var name: String,
    var img: Int, // unsure what data type should be
    val ingredients: List<IngredientType>
)