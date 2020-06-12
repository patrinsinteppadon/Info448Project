package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe

interface RecipeListManager {
    var recipes: MutableList<Recipe>

    fun add(recipe: Recipe)
    fun delete(recipe: Recipe)

    /** returns whether all required ingredients for the recipe are in the pantry */
    fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean
}