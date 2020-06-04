package com.project.mypantry.application

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.mypantry.objects.IngredientType
import java.io.IOException

class GlossaryManagerStatic(context: Context):GlossaryManager
{
    override lateinit var glossary: List<IngredientType>

    init {
        val jsonFileString = getJsonDataFromAsset(context, "json/IngredientType.json")
        val g = Gson()
        val listType = object : TypeToken<List<IngredientType>>() {}.type
        glossary = g.fromJson(jsonFileString, listType)
    }

    override fun search(regex: String): List<IngredientType> {
        return glossary.filter {
            it.ingredientName.contains(regex, true)
        }
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
}