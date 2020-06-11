package com.project.mypantry.managers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.mypantry.objects.IngredientType
import java.io.IOException

class GlossaryManagerStatic(context: Context): GlossaryManager {
    override lateinit var glossary: List<IngredientType>
    private val appContext = context

    init {
//        val jsonFileString = getJsonDataFromAsset(context, "json/IngredientType.json")
//        val g = Gson()
//        Log.i("GlossaryManager", jsonFileString)
//        val listType = object : TypeToken<List<IngredientType>>() {}.type
//        glossary = g.fromJson(jsonFileString, listType)
        getJsonFromOnline()
    }

    override fun search(regex: String): List<IngredientType> {
        return glossary.filter {
            it.ingredientName.contains(regex, true)
        }
    }

    override fun getIngredientType(id: Int): IngredientType? {
        for (i in glossary) {
            if (i.id == id) {
                return i
            }
        }

        return null
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun getJsonFromOnline() {
        val queue = Volley.newRequestQueue(appContext)

        val request = StringRequest(
            Request.Method.GET,
            "https://raw.githubusercontent.com/ThomasThat467/PantryAppJSONs/master/IngredientType.json",
            { response ->
                val gson = Gson()
                val glossary = gson.fromJson(response, Array<IngredientType>::class.java).asList()
                this.glossary = glossary
            },
            {
                Log.i("GlossaryManager", "Could not find JSON")
            }
        )

        queue.add(request)
    }
}