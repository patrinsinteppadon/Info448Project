package com.project.mypantry.application

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe
import com.project.mypantry.objects.ResultsModel

interface RecipeListManager {
    var recipes: MutableList<ResultsModel>

    fun add(recipe: ResultsModel)
    fun delete(recipe: ResultsModel)
    fun update(recipeID: Int, recipe: ResultsModel)

    /** returns whether all required ingredients for the recipe are in the pantry */
    fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: ResultsModel): Boolean
}