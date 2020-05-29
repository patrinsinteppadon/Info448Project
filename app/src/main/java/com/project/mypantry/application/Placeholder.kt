package com.project.mypantry.application

import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe

/**
 * ~~~~~~ This class has no behavior ~~~~~~
 * As we implement each manager, we should replace the placeholders in PantryApp.
 *
 * If you're designing the front end and need to use some of the managers, then replace Placeholder
 * with a dummy manager (e.g. PantryListManagerDummy) that returns hardcoded outputs for each function
 */
class Placeholder {
    var pantry: List<IngredientInstance> = emptyList()
    fun add(ing: IngredientInstance){}
    fun delete(ing: IngredientInstance) {}
    fun updateInstance(instanceID: Int, ing: IngredientInstance) {}
    fun addToGroceries(ing: IngredientType){}
    fun sendNotification(ing: IngredientType){}
    fun aboutToExpire(): List<IngredientInstance>{return emptyList()}
    var recipes: List<Recipe> = emptyList()
    fun add(recipe: Recipe){}
    fun delete(recipe: Recipe){}
    fun update(recipe: Recipe){}
    fun canBeCreated(recipe: Recipe): Boolean {return false}
    var shoppingList: List<IngredientType> = emptyList()
    fun add(type: IngredientType){}
    fun delete(type: IngredientType){}
    fun edit(type: IngredientType){}
    fun check(index: Int){}
    fun clearList(){}
    fun addToPantry(){}
    var glossary: List<IngredientType> = emptyList()
    fun search(regex: String): List<IngredientType> {return emptyList()}
    fun stopWork(workID: Int){}
    fun postItNote(){}
}