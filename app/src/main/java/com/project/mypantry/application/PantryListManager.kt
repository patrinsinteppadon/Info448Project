package com.project.mypantry.application

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType

interface PantryListManager {
    var pantry: MutableList<IngredientInstance>

    fun add(ing: IngredientInstance)
    fun delete(instanceID: Int)
    fun updateInstance(instanceID: Int, ing: IngredientInstance)
    fun addToGroceries(ing: IngredientType)
    fun sort() // always sorts by expiration date
    fun getPantryList(): List<IngredientInstance>
    fun getSize(): Int

    // phase 2 functions: let's work on these later
    fun sendNotification(ing: IngredientType)
    fun aboutToExpire(): List<IngredientInstance>
    fun get(id: Int): IngredientInstance?
}