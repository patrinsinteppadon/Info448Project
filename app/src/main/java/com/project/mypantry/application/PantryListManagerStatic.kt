package com.project.mypantry.application

import android.content.Context
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType


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

    override fun addToGroceries(ing: IngredientType) {
        (context as PantryApp).shoppingListManager.add(ing)
    }

    // sorts by expiration date
    override fun sort() {
        pantry.sortByDescending { it.expiration}
    }

    override fun sendNotification(ing: IngredientType) {
        TODO("Not yet implemented")
    }

    override fun aboutToExpire(): List<IngredientInstance> {
        TODO("Not yet implemented")
    }
}