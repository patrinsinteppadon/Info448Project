package com.project.mypantry.managers

import com.project.mypantry.managers.JsonResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * responseAsObject will be null until the results are loaded in
 */
interface HTTPManager {
    var responseAsObject: JsonResponse?

    fun downloadLists()

    @GET("LiamAlbright/codesnippetspantry/master/Recipes.json")
    fun allPantryRep (): Call<pantryResultsMod>

}