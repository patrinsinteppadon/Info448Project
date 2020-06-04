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
class Placeholder: PantryListManager, ShoppingListManager, RecipeListManager,
                    WorkManager, GlossaryManager, NotificationManager {
    override var pantry: List<IngredientInstance> = emptyList()
    override fun add(ing: IngredientInstance){}
    override fun delete(ing: IngredientInstance) {}
    override fun updateInstance(instanceID: Int, ing: IngredientInstance) {}
    override fun addToGroceries(ing: IngredientType){}
    override fun sendNotification(ing: IngredientType){}
    override fun aboutToExpire(): List<IngredientInstance>{return emptyList()}
    override var recipes: List<Recipe> = emptyList()
    override fun add(recipe: Recipe){}
    override fun delete(recipe: Recipe){}
    override fun update(recipe: Recipe){}
    override fun canBeCreated(recipe: Recipe): Boolean {return false}
    override var shoppingList: List<IngredientType> = emptyList()
    override fun add(type: IngredientType){}
    override fun delete(type: IngredientType){}
    override fun edit(type: IngredientType){}
    override fun check(index: Int){}
    override fun clearList(){}
    override fun addToPantry(){}
    override var glossary: List<IngredientType> = emptyList()
    override fun search(regex: String): List<IngredientType> {return emptyList()}
    override fun stopWork(workID: Int){}
    override fun postItNote(){}
    override fun sort(){}
}