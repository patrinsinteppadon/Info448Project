package com.project.mypantry.application

import com.project.mypantry.objects.IngredientType

interface ShoppingListManager {
    var shoppingList: MutableList<IngredientType>

    fun add(type: IngredientType)
    fun delete(type: IngredientType)
    fun edit(id: Int, type: IngredientType)
    fun check(index: Int) // toggles checkbox of shoppingList[index] in the recyclerview. Chat w/ Patrin bout this
    fun clearList()
    fun addToPantry() // phase 2. Let's work on this later
}