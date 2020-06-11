package com.project.mypantry.managers

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.project.mypantry.application.PantryApp
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import java.time.LocalDate


class PantryListManagerStatic(context: Context) : PantryListManager {
    override var pantry: MutableList<IngredientInstance> = mutableListOf()
    private val appContext = context

    init {
        getJsonFromOnline()
        Log.i("PantryFetch", pantry.toString())
    }

    override fun add(ing: IngredientInstance) {
        ing.instanceID = pantry.size
        pantry.add(ing)

        sort()
    }

    override fun delete(instanceID: Int) {
        for (i in 0 until pantry.size) {
            if (pantry[i].instanceID == instanceID) {
                pantry.removeAt(i)
                return
            }
        }
    }

    override fun getPantryList(): MutableList<IngredientInstance> {
        return this.pantry
    }

    override fun updateInstance(instanceID: Int, ing: IngredientInstance) {
        for (i in 0 until pantry.size) {
            if (pantry[i].instanceID == instanceID) {
                pantry[i] = ing
            }
        }
        sort()
    }

    override fun getSize(): Int {
        return pantry.size
    }

    override fun get(id: Int): IngredientInstance? {
        for (i in pantry) {
            if(i.instanceID == id) {
                return i
            }
        }
        return null
    }

    override fun addToGroceries(ing: IngredientType) {
        (appContext as PantryApp).shoppingListManager.add(ing)
    }

    // sorts by expiration date
    override fun sort() {
        pantry.sortBy { it.expiration}
    }

    override fun getCount(ing: IngredientType): Int {
        var count = 0
        for (i in pantry) {
            if(i.ingredientID == ing.id) {
                count += i.amount
            }
        }
        return count
    }

    override fun sendNotification(ing: IngredientType) {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun aboutToExpire(): List<IngredientInstance> {
        val result = mutableListOf<IngredientInstance>()
        for (ing in pantry) {
            if (ing.expiration < LocalDate.now().plusDays(4)) {
                result.add(ing)
            }
        }
        return result.toList()
    }

    private fun getJsonFromOnline() {
        val queue = Volley.newRequestQueue(appContext)

        val request = StringRequest(
            Request.Method.GET,
            "https://raw.githubusercontent.com/ThomasThat467/PantryAppJSONs/master/IngredientInstance.json",
            { response ->
                val gson = Gson()
                val ingredients = gson.fromJson(response, Array<IngredientInstance>::class.java).toMutableList()
                pantry = ingredients
            },
            {
                Log.i("PantryManager", "Could not find JSON")
            }
        )

        queue.add(request)
    }
}