package com.project.mypantry.application

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe

/**
 * Used to request *static JSON* data, for the sake
 * of building a minimum product.
 */
class HTTPManagerStatic(private val context: Context) {
    var responseAsObject: JsonResponse? = null
    companion object {
        const val PANTRY_JSON_URL = "NO URL YET"
        const val TAG = "MyPantry"
    }

    fun downloadLists() {
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET, PANTRY_JSON_URL,
            { response -> // success
                responseAsObject = Gson().fromJson(response, JsonResponse::class.java)
            },
            { response -> // error
                Log.e(TAG, "Error occurred: ${response.networkResponse.statusCode}")
            }
        )
        queue.add(request)
    }
}

data class JsonResponse(
    val pantryList: List<IngredientInstance>,
    val shoppingList: List<IngredientType>,
    val recipeList: List<Recipe>,
    val glossary: List<IngredientType>
    )