package com.project.mypantry.managers

import com.project.mypantry.objects.ResultsModel
import com.project.mypantry.objects.pantryResultsMod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager(private val recipeService: RecipeService) {

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

    fun getListOfPantryRep(onEmailReady: (pantryResultsMod) -> Unit, onError: (() -> Unit)? = null) {

        recipeService.allPantryRep().enqueue(object : Callback<pantryResultsMod> {
            override fun onResponse(call: Call<pantryResultsMod>, response: Response<pantryResultsMod>) {
                val allEmails = response.body()
                if (allEmails != null) {
                    onEmailReady(allEmails)
                } else {
                    onError?.invoke()
                }
            }

            override fun onFailure(call: Call<pantryResultsMod>, t: Throwable) {
                onError?.invoke()
            }
        })
    }

}