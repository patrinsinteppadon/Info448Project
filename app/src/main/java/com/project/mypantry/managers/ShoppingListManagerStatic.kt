package com.project.mypantry.managers

import android.content.Context
import com.project.mypantry.objects.IngredientType

/**
 * To access whether an item in the list is currently checked, see the `checkMap` var.
 * This map tracks each Type id and its current checkState
 */
class ShoppingListManagerStatic(private val context: Context) :
    ShoppingListManager {
    override var shoppingList: MutableList<IngredientType> = mutableListOf()
    private var checkMap: MutableMap<Int, Boolean> =
        mutableMapOf() // false means checkbox = unchecked

    override fun add(type: IngredientType) {
        shoppingList.add(type)
        checkMap[type.id] = false
    }

    /** feel free to change arg if "type" would be more convenient as an InstanceID */
    override fun delete(type: IngredientType) {
        shoppingList.remove(type)
        checkMap.remove(type.id)
    }

    // toggles checkbox of shoppingList[index] in the recyclerview.
    override fun check(index: Int) {
        checkMap[index] = !(checkMap[index] != null && checkMap[index] == true)
    }

    override fun isChecked(id: Int): Boolean {
        return (checkMap != null && checkMap[id]!!)
    }

    override fun clearList() {
        shoppingList.clear()
        checkMap.clear()
    }
}