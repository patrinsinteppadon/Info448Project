package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class ApiManager(private val ingredientTypeService: IngredientTypeService) {

    fun fetchGlossary(onGlossaryReady: (List<IngredientType>) -> Unit, onError: (() -> Unit)? = null) {
        ingredientTypeService.getGlossary().enqueue(object:Callback<List<IngredientType>> {
            override fun onFailure(call: Call<List<IngredientType>>, t: Throwable) {
                onError?.invoke()
            }

            override fun onResponse(
                call: Call<List<IngredientType>>,
                response: Response<List<IngredientType>>
            ) {
                val glossary = response.body()
                if (glossary != null) {
                    onGlossaryReady(glossary)
                } else {
                    onError?.invoke()
                }
            }

        })
    }
}


interface IngredientTypeService {
    @GET("towm1204/codesnippet/master/IngredientType.json")
    fun getGlossary(): Call<List<IngredientType>>
}