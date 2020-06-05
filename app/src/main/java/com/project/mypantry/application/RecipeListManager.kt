package com.project.mypantry.application

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe

interface RecipeListManager {
    var recipes: MutableList<Recipe>

    fun add(recipe: Recipe)
    fun delete(recipe: Recipe)
    fun update(recipeID: Int, recipe: Recipe)

    /** returns whether all required ingredients for the recipe are in the pantry */
    fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean
}