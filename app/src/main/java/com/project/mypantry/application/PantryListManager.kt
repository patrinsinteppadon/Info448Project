package com.project.mypantry.application

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType

interface PantryListManager {
    var pantry: List<IngredientInstance>

    fun add(ing: IngredientInstance)
    fun delete(ing: IngredientInstance)
    fun updateInstance(instanceID: Int, ing: IngredientInstance)
    fun addToGroceries(ing: IngredientType)
    fun sort() // always sorts by expiration date

    // phase 2 functions: let's work on these later
    fun sendNotification(ing: IngredientType)
    fun aboutToExpire(): List<IngredientInstance>
}