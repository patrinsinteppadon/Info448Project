package com.project.mypantry.managers

import com.project.mypantry.objects.IngredientType

interface ShoppingListManager {
    var shoppingList: MutableList<IngredientType>

    fun add(type: IngredientType)
    fun delete(type: IngredientType)
    fun update(type: IngredientType)
    fun check(index: Int) // toggles checkbox of shoppingList[index] in the recyclerview. Chat w/ Patrin bout this
    fun isChecked(id: Int): Boolean // returns if an ingredientType is already checked
    fun clearList()
    fun addToPantry() // phase 2. Let's work on this later
}