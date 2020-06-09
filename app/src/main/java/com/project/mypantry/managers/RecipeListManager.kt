package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe
import com.project.mypantry.objects.pantryResultsMod

interface RecipeListManager {
    var recipes: MutableList<Recipe>

    fun add(recipe: Recipe)
    fun delete(recipe: Recipe)
    fun update(recipe: Recipe)

    /** returns whether all required ingredients for the recipe are in the pantry */
    fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean
}