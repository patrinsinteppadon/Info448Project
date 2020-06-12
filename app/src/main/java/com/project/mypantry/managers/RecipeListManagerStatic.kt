package com.project.mypantry.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.Recipe

class RecipeListManagerStatic(context: Context) :
    RecipeListManager {
    override var recipes: MutableList<Recipe> = mutableListOf()
    private val appContext = context

    init {
        getJsonFromOnline()
    }

    override fun add(recipe: Recipe) {
        recipes.add(recipe)
    }

    override fun delete(recipe: Recipe) {
        recipes.remove(recipe)
    }

    /** returns whether all required ingredients for the recipe are in the pantry */
    override fun canBeCreated(pantry: MutableList<IngredientInstance>, recipe: Recipe): Boolean {
        return true
    }

    private fun getJsonFromOnline() {
        val queue = Volley.newRequestQueue(appContext)

        val request = StringRequest(
            Request.Method.GET,
            "https://raw.githubusercontent.com/ThomasThat467/PantryAppJSONs/master/Recipes.json",
            { response ->
                val gson = Gson()
                val recipeList = gson.fromJson(response, Array<Recipe>::class.java).toMutableList()
                recipes = recipeList
            },
            {
                Log.i("PantryManager", "Could not find JSON")
            }
        )

        queue.add(request)
    }
}