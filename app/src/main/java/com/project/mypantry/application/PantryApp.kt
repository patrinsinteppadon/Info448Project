package com.project.mypantry.application

import android.app.Application
import android.util.Log
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe

class PantryApp: Application() {
    lateinit var pantryManager: PantryListManager
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: ExpireWorkManager
    lateinit var notificationManager: MessageNotificationManager
    lateinit var httpManager: HTTPManager

    override fun onCreate() {
        super.onCreate()
        initManagers()

        // recipe test code
        var testIngredientType = IngredientType(1, "Ham", "ham_img")
        var testIngredientList = listOf(testIngredientType)
        var testRecipe = Recipe(111, "Ham sandwich", 0, testIngredientList)
        recipeListManager.add(testRecipe)
        recipeListManager.update(testRecipe.id, testRecipe)
        Log.i("patrin", recipeListManager.recipes.toString())
    }

    /**
     * As we implement each manager, we should replace the placeholders in PantryApp.
     *
     * If you're designing the front end and need to use some of the managers, then replace Placeholder
     * with a dummy manager (e.g. PantryListManagerDummy) that returns hardcoded outputs for each function
     */
    private fun initManagers() {
        pantryManager = PantryListManagerStatic(this) as PantryListManager
        recipeListManager = RecipeListManagerStatic(this) as RecipeListManager
        shoppingListManager = Placeholder() as ShoppingListManager
        glossaryManager = GlossaryManagerStatic(this) as GlossaryManager
        httpManager = HTTPManagerStatic(this) as HTTPManager // work in progress

        // for phase 2. Let's not focus on notifications for now
        workManager = ExpireWorkManager(this)
        notificationManager = MessageNotificationManager(this)
    }
}