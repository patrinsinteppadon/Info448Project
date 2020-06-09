package com.project.mypantry.managers

import com.project.mypantry.objects.ResultsModel
import com.project.mypantry.objects.pantryResultsMod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {

    @Headers("x-rapidapi-host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com", "x-rapidapi-key: 6d0dc23c60msh9a34ddaa986f734p12fe5ajsn57b0f1b0f01b")
    @GET("/recipes/findByIngredients")
    fun allRecipes(
        @Query("number") numRet: Int,
        @Query("ranking") showRank: Int,
        @Query("ignorePantry") pantryYN: Boolean,
        @Query("ingredients") allIngred: String
    ): Call<List<ResultsModel>>

    @GET("LiamAlbright/codesnippetspantry/master/Recipes.json")
    fun allPantryRep (): Call<pantryResultsMod>
}