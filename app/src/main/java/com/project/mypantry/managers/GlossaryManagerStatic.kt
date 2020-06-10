package com.project.mypantry.managers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.mypantry.objects.IngredientType
import java.io.IOException

class GlossaryManagerStatic(context: Context):
    GlossaryManager
{
    override var glossary: List<IngredientType> = listOf()

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
}