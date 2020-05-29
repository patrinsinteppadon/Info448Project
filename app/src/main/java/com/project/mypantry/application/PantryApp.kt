package com.project.mypantry.application

import android.app.Application

class PantryApp: Application() {
    lateinit var pantryManager: PantryListManager
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: WorkManager
    lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        initManagers()

    }

    /**
     * Note for if you are testing the front-end Activities and Fragments:
     * In your branch, while others work on implementing the actual managers,
     * create a dummy manager with hardcode outputs to replace "Unit".
     * By using Interfaces, you'll still be able to dev & test code
     */
    private fun initManagers() {
        pantryManager = Unit as PantryListManager
        recipeListManager = Unit as RecipeListManager
        shoppingListManager = Unit as ShoppingListManager
        glossaryManager = Unit as GlossaryManager

        // for phase 2. Let's not focus on notifications for now
        workManager = Unit as WorkManager
        notificationManager = Unit as NotificationManager
    }
}