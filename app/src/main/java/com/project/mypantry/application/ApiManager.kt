package com.project.mypantry.application

import android.util.Log
import com.project.mypantry.objects.ResultsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(private val recipeService: PantryApp.RecipeService) {
    private val TAG = "pantryApp"
    fun getListOfRecipe(onRecipeReady: (List<ResultsModel>) -> Unit, onError: (() -> Unit)? = null) {
        recipeService.allRecipes(5,1,false,"apples,flour,sugar").enqueue(object : Callback<List<ResultsModel>> {
            override fun onResponse(call: Call<List<ResultsModel>>, response: Response<List<ResultsModel>>) {
                val allRecipes = response.body()
                if (allRecipes != null) {
                    onRecipeReady(allRecipes)
                } else {
                    onError?.invoke()
                }
            }
            override fun onFailure(call: Call<List<ResultsModel>>, t: Throwable) {
                t.printStackTrace();
                onError?.invoke()
            }
        })
    }

}