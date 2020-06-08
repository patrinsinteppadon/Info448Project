package com.project.mypantry.application

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.project.mypantry.objects.IngredientInstance
import com.project.mypantry.objects.IngredientType
import com.project.mypantry.objects.Recipe
import java.time.LocalDate
import com.project.mypantry.managers.*
import java.lang.ref.WeakReference

class PantryApp: Application() {
    lateinit var pantryManager: PantryListManager
    lateinit var recipeListManager: RecipeListManager
    lateinit var shoppingListManager: ShoppingListManager
    lateinit var glossaryManager: GlossaryManager
    lateinit var workManager: ExpireWorkManager
    lateinit var notificationManager: MessageNotificationManager
    lateinit var httpManager: HTTPManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initManagers()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    /**
     * As we implement each manager, we should replace the placeholders in PantryApp.
     *
     * If you're designing the front end and need to use some of the managers, then replace Placeholder
     * with a dummy manager (e.g. PantryListManagerDummy) that returns hardcoded outputs for each function
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initManagers() {
        pantryManager = PantryListManagerStatic(
            this
        ) as PantryListManager
        recipeListManager = RecipeListManagerStatic(
            this
        ) as RecipeListManager
        shoppingListManager = ShoppingListManagerStatic(
            this
        ) as ShoppingListManager
        glossaryManager = GlossaryManagerStatic(
            this
        ) as GlossaryManager
        httpManager = HTTPManagerStatic(this) as HTTPManager // work in progress

        // for phase 2. Let's not focus on notifications for now
        workManager = ExpireWorkManager(this)
        notificationManager = MessageNotificationManager(this)

        pantryManager.add(
            IngredientInstance(pantryManager.getSize(), "Pizza", 1, 1, "lbs",
            LocalDate.now().plusDays(6))
        )
        pantryManager.add(
            IngredientInstance(pantryManager.getSize(),"Burger",  2, 2, "lbs",
            LocalDate.now().plusDays(5))
        )
        pantryManager.add(
            IngredientInstance(pantryManager.getSize(),"Hot Dog",  3, 1, "lbs",
            LocalDate.now().plusDays(4))
        )
        pantryManager.add(
            IngredientInstance(pantryManager.getSize(),"Soup",  4, 5, "lbs",
            LocalDate.now().plusDays(3))
        )
        pantryManager.add(
            IngredientInstance(pantryManager.getSize(),"Sauce",  5, 3, "lbs",
            LocalDate.now().plusDays(2))
        )
        pantryManager.add(
            IngredientInstance(pantryManager.getSize(),"I don't know",  6, 5, "lbs",
            LocalDate.now().plusDays(1))
        )
    }

    private val activityLifecycleCallbacks = object: ActivityLifecycleCallbacks {
        private var activityReferences = 0
        private var isActivityConfigurationChanging = false
        private var weakActivity = WeakReference<Activity?>(null)
        var isAppForegrounded = false

        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStarted(activity: Activity) {
            if (++activityReferences == 1 && !isActivityConfigurationChanging) {
                // app enters foreground
                isAppForegrounded = true
                workManager.stopWork()
            }
            weakActivity = WeakReference(activity)
        }
        override fun onActivityDestroyed(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        override fun onActivityStopped(activity: Activity) {
            isActivityConfigurationChanging = activity.isChangingConfigurations
            if (--activityReferences == 0 && !isActivityConfigurationChanging) {
                // App enters background
                isAppForegrounded = false
                workManager.startWork()
            }
            weakActivity.clear()
        }
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
        override fun onActivityResumed(activity: Activity) {}
    }
}