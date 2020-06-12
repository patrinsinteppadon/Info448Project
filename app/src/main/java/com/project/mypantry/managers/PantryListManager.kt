package com.project.mypantry.managers

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
    fun getCount(ing: IngredientType): Int
    fun aboutToExpire(): List<IngredientInstance>
    fun get(id: Int): IngredientInstance?
}