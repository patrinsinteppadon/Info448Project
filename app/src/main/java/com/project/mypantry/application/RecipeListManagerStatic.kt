package com.project.mypantry.application

import android.content.Context
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe

class RecipeListManagerStatic(private val context: Context): RecipeListManager {
    override var recipes: MutableList<Recipe> = mutableListOf()

    override fun add(recipe: Recipe) {
        recipes.add(recipe)
    }

    /**
     * Patrin made this. Feel free to change the argument type if it's more convenient to
     * delete based on recipeID
     */
    override fun delete(recipe: Recipe) {
        recipes.remove(recipe)
    }
    override fun update(recipeID: Int, recipe: Recipe) {
        for (i in 0 until recipes.size) {
            if (recipes[i].id == recipeID) {
                recipes[i] = recipe
            }
        }
    }

    /** returns whether all required ingredients for the recipe are in the pantry */
    override fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean {
        return true
    }
}