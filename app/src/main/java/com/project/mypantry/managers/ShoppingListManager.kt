package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientType

interface ShoppingListManager {
    var shoppingList: MutableList<IngredientType>

    fun add(type: IngredientType)
    fun delete(type: IngredientType)
    fun check(index: Int)
    fun isChecked(id: Int): Boolean
    fun clearList()
}