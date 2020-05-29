package com.project.mypantry.application

import com.project.mypantry.objects.Recipe

interface RecipeListManager {
    var recipes: List<Recipe>

    fun add(recipe: Recipe)
    fun delete(recipe: Recipe)
    fun update(recipe: Recipe)

    /** returns whether all required ingredients for the recipe are in the pantry */
    fun canBeCreated(recipe: Recipe): Boolean
}