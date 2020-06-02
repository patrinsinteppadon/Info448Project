package com.project.mypantry.application

import android.app.Application

class PantryApp: Application() {
    lateinit var pantryManager: PantryListManager
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: WorkManager
    lateinit var notificationManager: NotificationManager
    lateinit var httpManager: HTTPManager

    override fun onCreate() {
        super.onCreate()
        initManagers()

    }

    /**
     * As we implement each manager, we should replace the placeholders in PantryApp.
     *
     * If you're designing the front end and need to use some of the managers, then replace Placeholder
     * with a dummy manager (e.g. PantryListManagerDummy) that returns hardcoded outputs for each function
     */
    private fun initManagers() {
        pantryManager = PantryListManagerStatic(this) as PantryListManager
        recipeListManager = Placeholder() as RecipeListManager
        shoppingListManager = Placeholder() as ShoppingListManager
        glossaryManager = Placeholder() as GlossaryManager
        httpManager = HTTPManagerStatic(this) as HTTPManager // work in progress

        // for phase 2. Let's not focus on notifications for now
        workManager = Placeholder() as WorkManager
        notificationManager = Placeholder() as NotificationManager
    }
}