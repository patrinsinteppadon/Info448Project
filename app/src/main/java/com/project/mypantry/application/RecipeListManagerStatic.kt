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
     * Feel free to change the argument type if it's more convenient to
     * delete based on recipeID
     */
    override fun delete(recipe: Recipe) {
        recipes.remove(recipe)
    }

    /**
     * checks if recipe's id is in the recipeList.
     * If so, replaces that element with the argument.
     */
    override fun update(recipe: Recipe) {
        var didUpdate = false
        for (i in 0 until recipes.size) {
            if (recipes[i].id == recipe.id) {
                recipes[i] = recipe
                didUpdate = true
            }
        }

        if (!didUpdate) {
            throw IllegalArgumentException("Recipe ID number: ${recipe.id} is not already in recipeList")
        }
    }

    /** returns whether all required ingredients for the recipe are in the pantry */
    override fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean {
        return true
    }
}