package com.project.mypantry.application

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import java.time.LocalDate


class PantryListManagerStatic(private val context: Context) : PantryListManager {

    override var pantry: MutableList<IngredientInstance> = mutableListOf()

    override fun add(ing: IngredientInstance) {
        ing.instanceID = pantry.size
        pantry.add(ing)

        sort()
    }

    override fun delete(instanceID: Int) {
        for (i in 0 until pantry.size) {
            if (pantry[i].instanceID == instanceID) {
                pantry.removeAt(i)
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
        (context as PantryApp).shoppingListManager.add(ing)
    }

    // sorts by expiration date
    override fun sort() {
        pantry.sortBy { it.expiration}
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
}