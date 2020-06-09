package com.project.mypantry.managers

import android.content.Context
import com.project.mypantry.objects.IngredientType

/**
 * to access whether an item in the list is currently checked, see the `checkMap` var.
 * This map tracks each Type id and its current checkState
 */
class ShoppingListManagerStatic(private val context: Context):
    ShoppingListManager {
    override var shoppingList: MutableList<IngredientType> = mutableListOf()
    var checkMap: MutableMap<Int, Boolean> = mutableMapOf() // false means checkbox = unchecked

    init {
        // Making test data to see if shoppingListFragment works
        var testType = IngredientType(1000, "Ham", "PLACEHOLDER_IMG")
        var testType1 = IngredientType(2000, "Eggs", "PLACEHOLDER_IMG")
        var testType2 = IngredientType(3000, "Milk", "PLACEHOLDER_IMG")
        var testType3 = IngredientType(4000, "Potatoes", "PLACEHOLDER_IMG")
        add(testType)
        add(testType1)
        add(testType2)
        add(testType3)
        check(testType.id)
        check(testType2.id)
    }

    override fun add(type: IngredientType) {
        shoppingList.add(type)
        checkMap[type.id] = false
    }

    /** feel free to change arg if "type" would be more convenient as an InstanceID */
    override fun delete(type: IngredientType) {
        shoppingList.remove(type)
        checkMap.remove(type.id)
    }


    override fun update(type: IngredientType) {
        var didUpdate = false
        for (i in 0 until shoppingList.size) {
            if (shoppingList[i].id == type.id) {
                shoppingList[i] = type
                didUpdate = true
            }
        }

        if (!didUpdate) {
            throw IllegalArgumentException("IngredientType ID: ${type.id} is not already in shoppingList")
        }
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

    // phase 2. Let's work on this later
    override fun addToPantry() {}
}