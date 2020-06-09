package com.project.mypantry.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import com.project.mypantry.objects.pantryResultsMod

/**
 * Used to request *static JSON* data, for the sake
 * of building a minimum product.
 */
class HTTPManagerStatic(private val context: Context):
    HTTPManager {
    override lateinit var pantryResults: pantryResultsMod

    companion object {
        const val PANTRY_JSON_URL = "NO URL YET"
        const val TAG = "MyPantry"
    }

    override fun downloadLists() {
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            PANTRY_JSON_URL,
            { response -> // success
//                responseAsObject = Gson().fromJson(response, JsonResponse::class.java)
                // TODO: How can I send the results of responseAsObject to the other managers?
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