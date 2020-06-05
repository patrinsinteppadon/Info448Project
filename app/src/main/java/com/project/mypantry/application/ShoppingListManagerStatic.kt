package com.project.mypantry.application

import com.project.mypantry.objects.IngredientType

class ShoppingListManagerStatic: ShoppingListManager {
    override var shoppingList: MutableList<IngredientType> = mutableListOf()
    var checkMap: MutableMap<Int, Boolean> = mutableMapOf() // false means checkbox = unchecked

    override fun add(type: IngredientType) {
        shoppingList.add(type)
        checkMap[type.id] = false
    }

    /** feel free to change arg if "type" would be more convenient as an InstanceID */
    override fun delete(type: IngredientType) {
        shoppingList.remove(type)
    }


    override fun edit(id: Int, type: IngredientType) {
        for (i in 0 until shoppingList.size) {
            if (shoppingList[i].id == id) {
                shoppingList[i] = type
            }
        }
    }

    // toggles checkbox of shoppingList[index] in the recyclerview.
    override fun check(index: Int) {
        checkMap[index] = !(checkMap[index] != null && checkMap[index] == true)
    }

    override fun clearList() {
        shoppingList.clear()
        checkMap.clear()
    }

    // phase 2. Let's work on this later
    override fun addToPantry() {}
}